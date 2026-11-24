from lesson_support import lesson_client, show_prompt

def main():
    with lesson_client() as client:
        show_prompt(client, "짧은 요청", "Python 리스트를 설명해 줘.")
        prompt = build_prompt(
            role="Python 기초 수업 강사",
            goal="Python 리스트의 쓰임을 설명한다",
            audience="Python을 처음 배우는 사람",
            constraints=["전체 답변을 세 문장 이내로 작성한다."],
            output_format="한 문장 정의 -> 점수 세 개를 리스트에 담는 코드 예시"
        )
        show_prompt(client, "조건을 구체화한 요청", prompt)

def build_prompt(role, goal, audience, constraints, output_format):
    lines = [
        f"[역할] {role}",
        f"[목표] {goal}",
        f"[독자] {audience}",
        "[제약]"
    ]
    for contraint in constraints:
        lines.append(f"- {contraint}")
    # extend()는 념겨준 목록의 항목을 하나씩 기존 리스트 끝에 추가한다
    lines.extend(["[출력 형식]", output_format])
    # join()은 문자열 사이에 구분자를 넣어 새 문자열을 만든다
    return "\n".join(lines)

if __name__ == "__main__":
    main()