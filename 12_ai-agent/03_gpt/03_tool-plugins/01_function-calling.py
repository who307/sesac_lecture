# 검색 대상 수업 자료. 키는 주제 이름이고 값은 검색으로 돌려줄 설명이다.
LESSONS = {
    "list": "Python 리스트는 여러 값을 순서대로 담으며, 값을 추가하거나 제거할 수 있습니다.",
    "dict": "Python 딕셔너리는 키와 값을 쌍으로 저장하며, 키로 값을 찾습니다.",
    "json": "JSON은 프로그램끼리 데이터를 주고받을 때 사용하는 텍스트 형식입니다.",
    "prompt": "프롬프트는 모델에게 전달하는 요청이며, 작업 지시와 참고할 데이터를 포함할 수 있습니다.",
}

def search_lesson(keyword):
    """ 로컬 수업 자료에서 키워드와 일치하는 설명을 찾는다. """

    keyword = keyword.strip().lower()

    for title, content in LESSONS.items():
        if keyword in title.lower() or keyword in content.lower():
            return {
                "found": True,
                "title": title,
                "content": content
            }

    return {
        "found": False,
        "title": None,
        "content": "관련 수업 내용을 찾지 못했습니다."
    }


# 주제 이름을 받아 고정된 퀴즈 문장과 힌트를 만든다.
def make_quiz(topic):
    """ 주제에 맞는 간단한 퀴즈를 만드는 tool """
    return {
        "topic": topic,
        "question": f"{topic}을 초보자에게 한 문장으로 설명해보세요.",
        "answer_hint": "정의 + 쉬운 예시를 포함하면 좋습니다."
    }

# 입력 문장의 키워드로 실행할 도구 이름과 인수만 정한다.
def fake_model_decide_tool(user_message):
    if "퀴즈" in user_message:
        topic = user_message.replace("퀴즈", "").strip() or "json"
        return {
            "tool_name": "make_quiz",
            "arguments": {"topic": topic}
        }
    
    if "찾아" in user_message or "설명" in user_message:
        keyword = user_message.replace("찾아", "").replace("설명", "").strip()
        return {
            "tool_name": "search_lesson",  # 실행할 함수 이름
            "arguments": {"keyword": keyword}  # 그 함수에 전달할 인수 묶음
        }
    
    return {
        "tool_name": None,
        "arguments": {}
    }
# 요청에 적힌 함수 이름을 확인하고 해당 함수에 인수를 넘겨 실행한다.
def run_tool(tool_call):
    tool_name = tool_call["tool_name"]
    arguments = tool_call["arguments"]

    if tool_name == "search_lesson":
        return search_lesson(**arguments)  # search_lesson(keyword="list") 와 같음

    if tool_name == "make_quiz":
        return make_quiz(**arguments)

    return {"error": "사용할 tool이 없습니다."}


# 도구 실행 결과를 읽어 화면에 보여 줄 답변 문자열을 만든다.
def answer_with_tool_result(user_message, tool_result):
    if "error" in tool_result:
        return "질문을 처리할 도구를 찾지 못했습니다."
    
    if "question" in tool_result:
        return f"퀴즈: {tool_result['question']}\n힌트 : {tool_result['answer_hint']}"
    
    if tool_result.get("found"):
        return f"{tool_result['title']} 관련 내용입니다.\n{tool_result['content']}"
    
    return tool_result['content']

# 요청 생성 → 도구 실행 → 답변 구성의 순서를 출력으로 보여 준다.
def demo(user_message):
    print('사용자:', user_message)

    tool_call = fake_model_decide_tool(user_message)
    print("로컬 규칙이 선택한 도구:", tool_call)

    tool_result = run_tool(tool_call)
    print("tool 실행결과:", tool_result)

    final_answer = answer_with_tool_result(user_message, tool_result)
    print("최종 답변:")
    print(final_answer)


# 파일을 직접 실행하면 검색 성공, 퀴즈 생성, 검색 실패 예시를 차례로 실행한다.
if __name__ == "__main__":
    demo("list 설명")
    demo("json 퀴즈")
    demo("없는내용 찾아")

