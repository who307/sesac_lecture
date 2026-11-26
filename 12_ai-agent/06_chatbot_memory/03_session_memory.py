from dotenv import load_dotenv
from langchain_openai import ChatOpenAI
from openai import APIError
from langchain.agents import create_agent
from langgraph.checkpoint.memory import InMemorySaver

def main():
    load_dotenv()
    model = ChatOpenAI(
            model="gpt-4.1-mini", temperature=0, max_completion_tokens=300,
            timeout=20, max_retries=0, use_responses_api=False
        )
    agent = create_agent(
        model=model,
        tools=[],
        system_prompt="친절한 한국어 도우미입니다. 기록에 없는 이 름은 추측하지 마세요",
        # checkpoint: 대화 기록을 포함한 실행 상태를 저장할 장치 지정
        checkpointer=InMemorySaver() # 실행 상태를 프로그램 메모리에 보관
    )
    
    sessions = [
        ("student-a", "내 이름은 민수야. 인사해줘"),
        ("student-b", "내 이름은 판다야. 인사해줘"),
        ("student-a", "내 이름이 뭐야?"),
        ("student-a", "내 이름이 뭐야?"),
    ]
    
    for session_id, question in sessions:
        # 이번 설정에 사용할 설정 딕셔너리
        # thread_id: 대화의 식별자
        config = {"configurable": {"thread_id": session_id}}
        result = agent.invoke({"messages": [{"role": "user", "content": question}]}, config=config)
        print(f"\n[{session_id}] User: {question}")
        print("Bot:", result["messages"][-1].content)
        
    for session_id in ("student-a", "student-b"):
        config = {"configurable": {"thread_id": session_id}}
        # 해당 thread_id의 현재 상태를 가져오고, 그 안의 메세지 목록을 꺼낸다
        messages = agent.get_state(config).values["messages"]
        print(f"{session_id}에 저장된 기록: {len(messages)}개")
        for message in messages:
            print(f"{message.type}: {message.content}")
            
    
if __name__ == "__main__":
    try:
        main()
    except APIError as error:
        print("API 요청 실패", type(error))