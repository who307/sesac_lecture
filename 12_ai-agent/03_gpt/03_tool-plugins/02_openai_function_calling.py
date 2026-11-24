from email import message
import json
import os
from typing import assert_never

from dotenv import load_dotenv
from openai import APIError, OpenAI

# 검색 대상 수업 자료. 앞 실습의 검색 함수가 이 딕셔너리에서 설명을 찾는다.
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

# 모델에게 알려 줄 기능 목록
# API가 정한 형식으로 검색 함수의 이름과 입력 규칙을 모델에 알려 준다.
TOOLS = [{
    "type": "function", # 함수를 호출하는 도구이다
    "function": {
        "name": "search_lesson",    # 모델이 실행을 요청할 때 적을 이름
        "description": "수업 자료의 키워드를 검색합니다.",
        "strict": True,
        "parameters": {
            "type": "object",
            "properties": {"keyword": {"type": "string"}},
            "required": ["keyword"],
            "additionalProperties": False,
        },
    },
}]


# 수업 루트의 API 키를 확인하고 실제 클라이언트로 도구 호출 실습을 시작한다.
def main():
    load_dotenv()
    if not os.getenv("OPENAI_API_KEY"):
        print("OPENAI_API_KEY를 환경변수 또는 수업 루트의 .env에 설정하세요.")
        return

    try:
        # 대화에 필요한 요청을 보내고, 실습이 끝나면 클라이언트를 닫는다.
        with OpenAI(timeout=20.0, max_retries=0) as client:
            run_demo(client)
    except (APIError, ValueError) as error:
        print("도구 실습 중단:", type(error).__name__)

# 검색 기능을 모델에 알리고, 응답으로 받은 도구 요청을 화면에 보여 준다
def run_demo(client: OpenAI):
    messages = [{"role": "system", "content": "수업 내용을 물어보면 search_lesson 도구를 사용하세요."},
                {"role": "user", "content": "list 설명해줘"}]
    response = client.chat.completions.create(
        model="gpt-4.1-mini",
        messages=messages,
        tools = TOOLS,
        max_completion_tokens=300
    )
    assistant = response.choices[0].message
    if not assistant.tool_calls:
        print("도구 요청 없음", assistant.content or "(텍스트 없음)")
        return

    # 검색 결과를 돌려줄 때 함께 보낼 모델의 도구 요청을 대화에 기록
    messages.append({
        "role": "assistant",
        # model_dump(): 객체를 딕셔너리로 바꾼다
        "tool_calls": [call.model_dump() for call in assistant.tool_calls]
    })
    # 도구 요청을 검사/실행하고, 호출 ID로 연결한 결과를 대화에 추가
    append_tool_results(messages, assistant.tool_calls)

    response = client.chat.completions.create(
        model="gpt-4.1-mini",
        messages=messages,
        max_completion_tokens=300,
    )
    choice = response.choices[0]
    if choice.finish_reason != "stop" or not choice.message.content:
        print("완료된 최종 텍스트가 없습니다.")
        return
    print("최종 답변: ", choice.message.content)




# 도구 요청을 검사해 검색을 실행하고, 결과와 호출 ID를 출력한다
def append_tool_results(messages, tool_calls):
    for call in tool_calls:
        if call.type != "function" or call.function.name != "search_lesson":
            raise ValueError("등록하지 않은 도구는 실행할 수 없습니다.")
        args = json.loads(call.function.arguments)
        if not isinstance(args, dict):
            raise ValueError("인수는 JSON 객체여야 합니다.")
        # 검색어가 반드시 있고, 다른 인수는 붙지 않았는지 확인
        if "keyword" not in args:
            raise ValueError("keyword 인자가 필요합니다.")
        if len(args) != 1:
            raise ValueError("keyword 인자 하나만 허용합니다.")
        # 검색어가 문자열이고 공백을 제외한 내용이 있는지 확인
        if not isinstance(args["keyword"], str) or not args["keyword"].strip():
            raise ValueError("keyword는 비어있지 않은 문자열이어야 합니다.")

        print("요청한 함수", call.function.name)
        print("요청 인수 JSON", call.function.arguments)
        result = search_lesson(**args)
        print("결과를 돌려줄 요청 ID:", call.id)
        print("Python 실행 결과", result)
        messages.append({
            "role": "tool", # Python이 실행한 도구의 결과
            "tool_call_id": call.id, # 어느 실행 요청의 결과인지 ID로 연결
            "content": json.dumps(result, ensure_ascii=False) # 검색 결과를 JSON 문자열로 전달
        })
        
# 파일을 직접 실행하면 키를 확인한 뒤 실제 모델을 호출한다.
if __name__ == "__main__":
    main()