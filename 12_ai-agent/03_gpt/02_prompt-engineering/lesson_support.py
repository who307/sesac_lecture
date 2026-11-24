"""프롬프트 비교용 공통 실행 코드. 기본 실행은 실제 모델을 호출합니다."""

import os
from contextlib import contextmanager

from dotenv import load_dotenv
from openai import APIError, OpenAI


# 실제 API 클라이언트를 제공하고, JSON 검증 연습에서만 로컬 실행을 지원
# with에 들어갈 때 yield까지 실행하고, 블록을 나올 때 그 자리에서 재개한다.
@contextmanager
def lesson_client(local=False):
    if local:
        yield None
        return
    load_dotenv()
    if not os.getenv("OPENAI_API_KEY"):
        raise ValueError("OPENAI_API_KEY를 환경변수 또는 수업 루트의 .env에 설정하세요.")
    with OpenAI(timeout=20.0, max_retries=0) as client:
        # as client에 객체를 건네고 멈춘다. 재개되면 안쪽 with가 끝나 클라이언트가 닫힌다.
        yield client


# 프롬프트를 출력하고, 클라이언트가 있으면 실제 응답을 받아 돌려준다
def show_prompt(client: OpenAI | None, label, prompt, **options):
    print(f"\n[{label}]\n{prompt}")
    if client is None:
        print("[로컬 확인] 전송하지 않았습니다.")
        return None
    try:
        response = client.chat.completions.create(
            model="gpt-4o",
            messages=[{"role": "user", "content": prompt}],
            temperature=0,
            max_completion_tokens=700,
            **options,
        )
    except APIError as error:
        print("API 요청 실패:", type(error).__name__)
        return None
    choice = response.choices[0]
    reply = (choice.message.content or "").strip()
    if choice.finish_reason != "stop" or not reply:
        print("완료된 텍스트 응답이 없습니다.")
        return None
    print("응답:", reply)
    return reply
