from typing import Literal
from pydantic import BaseModel, ConfigDict, Field
from langchain_core.output_parsers import JsonOutputParser, PydanticOutputParser
from langchain_core.prompts import PromptTemplate
from dotenv import load_dotenv
from langchain_core.exceptions import OutputParserException
from langchain_openai import ChatOpenAI
from openai import APIError

# Pydantic: 클래스에 적은 타입과 규칙으로 입력 데이터를 검증하는 Python 라이브러리
# BaseModel을 상속해 필요한 항목을 선언하고, 검증을 통과한 값을 객체로 다룹니다
class SentimentAnalysis(BaseModel):
    model_config = ConfigDict(extra="forbid")
    label: Literal["긍정", "부정", "중립"] = Field(description="허용된 감정 분류")
    reason: str = Field(min_length=1, description="판단 근거")

SAMPLE_REPLY = '{"label":"중립", "reason":"어려움과 긍정적인 평가가 함께 있습니다."}'
SENTENCE = "수업이 어렵지만 예제가 있어서 따라갈 만하다."

def build_chain(model):
    # PydnaticOutputparser: JSON을 클래스 규칙으로 검증하고, 통과한 값을 그 클래스의 객체로 반환
    parser = PydanticOutputParser(pydantic_object=SentimentAnalysis)
    # 클래스의 항목과 규칙을 모델에게 전달할 JSON 중립 형식 안내로 만든다
    format_instructions = parser.get_format_instructions()
    
    prompt = PromptTemplate.from_template(
        "아래 수강평의 감정을 분류하고 이유를 적으세요.\n"
        "{format_instructions}\n"
        "수강평: {sentence}"
    )
    prompt = prompt.partial(format_instructions=format_instructions)
    
    return prompt | model | parser
    
def main():
    load_dotenv()
    model = ChatOpenAI(
        model="gpt-4.1-mini",
        temperature=0,
        max_completion_tokens=200,
        timeout=20,
        max_retries=0,
        use_responses_api=False
    )
    try:
        result = build_chain(model).invoke({"sentence": SENTENCE})
    except OutputParserException:
        print("응답 검증 실패: 감정 분류와 이유 형식을 확인하세요")
        return
    except APIError as error:
        print("API 요청 실패", type(error).__name__)
        return
    print("결과 타입: ", type(result).__name__)
    print("감정", result.label)
    print("이유:", result.reason)
    
    # JsonOUtputParser: JSON을 Python 값으로 읽는다.
    # format_only =JsonOutputParser()
    # data = format_only.invoke(SAMPLE_REPLY)
    # print("JSON 읽기만 한 값:", data["label"])
    
    # parser = PydanticOutputParser(pydantic_object=SentimentAnalysis)
    # result = parser.invoke(SAMPLE_REPLY)
    # print("결과 타입:", type(result).__name__)
    # print("감정:", result.label)
    # print("이유:", result.reason)

if __name__ == "__main__":
    main()