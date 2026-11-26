from langchain_core.messages import AIMessage, HumanMessage, trim_messages

HISTORY = [
    HumanMessage(content="내 이름은 민수야."),
    AIMessage(content="민수님, 반갑습니다."),
    HumanMessage(content="Document Loader가 뭐야?"),
    AIMessage(content="원본에서 텍스트를 읽어 Document로 준비하는 도구입니다."),
    HumanMessage(content="Text Splitter는 뭐야?"),
    AIMessage(content="긴 Document를 검색할 작은 조각으로 나누는 도구입니다."),
]
QUESTION = "그 둘의 차이는 뭐야?"

def main():
    for count in (2, 4):
        recent = trim_messages(
            HISTORY,
            max_tokens=count,
            token_counter=len,
            strategy="last",
            start_on="human",
            allow_partial=False
        )
        print(f"[선택할 최근 메시지: 최대 {count}개]")
        for message in recent:
            print(f"{message.type}: {message.content}")
        print("현재 질문:", QUESTION)
        print("선택된 메세지 수:", len(recent))
        print("원본 메세지 수 : ", len(HISTORY))

if __name__ == "__main__":
    main()

