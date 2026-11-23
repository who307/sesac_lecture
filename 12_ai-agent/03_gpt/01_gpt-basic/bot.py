import json
import os

from dotenv import load_dotenv
from openai import APIError, OpenAI

# .env 파일의 OPENAI_API_KEY, OPENAI_MODEL 을 환경변수로 읽어온다
load_dotenv()

MODEL = os.getenv("OPENAI_MODEL", "gpt-5-mini")


# 메세지 목록을 화면에 보여 주고 OpenAI 응답을 돌려준다
def get_reply(client, messages):
    print(json.dumps(messages, ensure_ascii=False))
    response = client.chat.completions.create(
        model=MODEL,
        messages=messages,
    )
    return response.choices[0].message.content


def main():
    api_key = os.getenv("OPENAI_API_KEY")
    if not api_key or api_key == "sk-your-key-here":
        print("OPENAI_API_KEY 가 없습니다. .env 파일에 키를 넣어 주세요.")
        return

    client = OpenAI(api_key=api_key)
    messages = [{"role": "user", "content": "python 리스트가 뭐에요?"}]

    try:
        print("Bot:", get_reply(client, messages))
    except APIError as e:
        print("API 호출 실패:", e)


if __name__ == "__main__":
    main()
