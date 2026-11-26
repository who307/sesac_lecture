from dotenv import load_dotenv
from langchain_core.messages import HumanMessage
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder
from langchain_openai import ChatOpenAI
from openai import APIError

def main():
    load_dotenv()
    model = ChatOpenAI(
        model="gpt-4.1-mini", temperature=0, max_completion_tokens=300,
        timeout=20, max_retries=0, use_responses_api=False
    )
    # 요청 순서 : 공통지시(system) -> 이전 대화(history) -> 이번 질문(human)
    prompt = ChatPromptTemplate.from_messages([
        ("system", "친절한 한국어 도우미입니다. 전달된 기록에 없는 글은 추측하지 마세요"),
        MessagesPlaceholder("history"),
        ("human", "{question}")
    ])
    chain = prompt | model
    introduction = "내 이름은 민수야. 반갑다고 짧게 인사해줘."
    # 빈 이력과 자기소개를 보내고 첫 답변을 받는다. 반환값(first_reply)은 AIMessage 객체이다
    first_reply = chain.invoke({"history": [], "question": introduction})
    print("첫 답변:", first_reply.content)
    
    question = "내 이름이 뭐야?"
    without_history = chain.invoke({"history": [], "question": question})
    print("이력 없는 요청의 답변", without_history.content)
    
    history = [HumanMessage(content=introduction), first_reply]
    inputs = {"history": history, "question": question}
    print("\n[이력을 넣은 실제 요청 메세지]")
    for message in prompt.invoke(inputs).to_messages():
        print(f"{message.type}: {message.content}")    
    
    with_history = chain.invoke(inputs)
    print("\n[이력 있는 요청의 답변]", with_history.content)
    
if __name__ == "__main__":
    try:
        main()
    except APIError as error:
        print("API 요청 실패", type(error))