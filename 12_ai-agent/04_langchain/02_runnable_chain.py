from dotenv import load_dotenv
from openai import APIError
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser
from langchain_openai import ChatOpenAI

def build_chain(model):
    prompt = ChatPromptTemplate.from_messages([
        ("system", "LLM 활용을 가르치는 강사입니다. 세 문장 이내로 설명하세요"),
        ("user", "{audience}에게 {topic}을 쉽게 설명하세요")
    ])
    # StrOutputParser() :  모델의 응답 메시지(AIMessage)에서 텍스트를 꺼내 문자열로 반환
    # | (파이프 연산자) :  왼쪽의 작업 결과를 오른쪽 작업의 입력으로 넘기도록 연결해준다
    return prompt | model | StrOutputParser()

def main():
    load_dotenv()
    model = ChatOpenAI(
        model="gpt-4.1-mini",
        temperature=0.7,
        max_completion_tokens=300,
        timeout=20,
        max_retries=0,
        # use_responses_api = True 
        # -> Response API 방식 사용, OpenAI 내장 웹 검색, 파일 검색이 필요하면 검토해볼 수 있다
        use_responses_api=False
    )
    chain = build_chain(model)
    try:
        result = chain.invoke({
            "audience": "Python 입문자",
            "topic": "LangChain"
        })
    except APIError as error:
        print("요청 실패", type(error))
        return
    print("결과", result)

if __name__ == "__main__":
    main()