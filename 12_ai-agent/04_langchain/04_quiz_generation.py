import json
from langchain_core.output_parsers import PydanticOutputParser
from pydantic import BaseModel, ConfigDict, Field, model_validator 
from langchain_core.prompts import PromptTemplate
from dotenv import load_dotenv
from langchain_openai import ChatOpenAI
from langchain_core.exceptions import OutputParserException
from openai import APIError

class Quiz(BaseModel):
    model_config = ConfigDict(extra="forbid")
    question: str = Field(min_length=1, description="퀴즈")
    choices: list[str] = Field(min_length=4, max_legnth=4, description="보기 네 개")
    answer: str = Field(min_legnth=1, description="보기 중 정답 문자열")
    explanation: str = Field(min_length=1, description="정답 설명")
    
    @model_validator(mode="after")
    def check_answer(self):
        if any(not choice.strip() for choice in self.choices):
            raise ValueError("보기는 비어 있을 수 없습니다.")
        # set() : 중복을 제외한 값들의 집합을 만듦
        if len(set(self.choices)) != len(self.choices):
            raise ValueError("보기는 서로 달라야 합니다.")
        if self.answer not in self.choices:
            raise ValueError("정답은 보기 중 하나여야 합니다.")
        return self
    
SAMPLE_REPLY = json.dumps({
    "question": "Python 딕셔너리에 저장된 값을 찾을 때 사용하는 것은 무엇인가요?",
    "choices": ["키", "리스트의 인덱스", "문자열의 길이", "반복 횟수"],
    "answer": "키",
    "explanation": "딕셔너리는 키와 값을 한 쌍으로 저장하며, 키를 지정해 해당 값을 읽습니다.",
}, ensure_ascii=False)

def build_quiz_chain(model):
    parser = PydanticOutputParser(pydantic_object=Quiz)
    format_instructions = parser.get_format_instructions()
    prompt = PromptTemplate.from_template(
        "{topic} 주제로 입문자용 객관식 퀴즈 하나를 만드세요.\n"
        "보기는 내용이 있는 서로 다른 문장 네 개로 작성하세요.\n"
        "정답은 보기 중 하나의 문장을 그대로 적으세요\n"
        "{format_instructions}"
    )
    prompt = prompt.partial(format_instructions=format_instructions)
    return prompt | model | parser

def show_quiz(model, topic):
    quiz = build_quiz_chain(model).invoke({"topic": topic})
    print("문제", quiz.question)
    #enumerate: 반복할 때 번호와 값을 한 쌍씩 꺼낼 수 있게 해주는 함수
    for number, choice in enumerate(quiz.choices, start=1):
        print(f"{number}. {choice}")
    print("정답:", {quiz.answer})
    print("해설:", quiz.explanation)
    

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
        show_quiz(model, "Python 딕셔너리")
    except OutputParserException:
        print("퀴즈 검증 실패: 보기와 정답 형식을 확인하세요.")
    except APIError as error:
        print("API 요청 실패:", type(error).__name__)

if __name__ == "__main__":
    main()