from doctest import Example
from pydoc import cli

from lesson_support import lesson_client, show_prompt

RULES = """수강생 피드백을 다음 세 분류 중 하나로 분류하세요.
ACADEMIC: 수업 진도나 코드 질문
ADMIN: 출석, 행정, 시설 문의
CHAT: 인사나 일상 대화
분류 이름 하나만 출력하세요."""
EXAMPLES = """입력: 코드가 자꾸 오류 나요
출력: ACADEMIC
입력: 출석 확인 부탁드려요
출력: ADMIN
입력: 오늘도 화이팅!
출력: CHAT"""
SENTENCE = "강의실 와이파이가 안 잡혀요"
EXPECTED = "ADMIN"

def main():
    with lesson_client() as client:
        question = f"\n[판정할 문장]\n{SENTENCE}"
        zero = show_prompt(client, "Zero-shot", RULES + question)
        few = show_prompt(client, "Few-shot", RULES + "\n[예시]\n"+ EXAMPLES + question)
        if zero is not None and few is not None:
            print("이 사례와 일치:", {"zero": zero == EXPECTED, "few": few == EXPECTED})

if __name__=="__main__":
    main()