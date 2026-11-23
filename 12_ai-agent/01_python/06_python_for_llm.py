import json

FEEDBACKS = [
    {'id': 1, 'text':'진도가 조금 빠르지만 실습으로 이해하고 있습니다.'},
    {'id': 2, 'text':'API 키 설정에서 막혀서 수업을 거의 못 따라갔습니다.'},
    {'id': 3, 'text':'예제를 더 연습하고 싶습니다.'},
]

SAMPLE_REPLY = '{"items": [{"id": 1, "sentiment": "mixed", "score": 75, "key_reason": "진도는 빠르지만 실습이 도움이 됩니다."},' \
'{"id": 2, "sentiment": "negative", "score": 35, "key_reason": "환경 설정이 학습을 막았습니다."},' \
'{"id": 3, "sentiment": "mixed", "score": 65, "key_reason": "추가 연습이 필요합니다."}]}'

BROKEN_JSON = '{"items": []'
MISSING_FIELD_REPLY = '{"items": [{"id": 1, "sentiment": "mixed", "score": 75, "key_reason": "진도는 빠르지만 실습이 도움이 됩니다."}]}'
RESPONSE_EXAMPLES = {
    "정상 응답" : SAMPLE_REPLY,
    "깨진 JSON" : BROKEN_JSON,
    "필수 필드 누락": MISSING_FIELD_REPLY
}

def build_prompt(feedbacks):
    feedbacks_json = json.dumps(feedbacks, ensure_ascii=False)
    return f"""
            아래의 수강평을 분석해 JSON 객체만 출력하세요. 최상위 키는 "items"입니다.
            각 항목은 id, sentiment, score, key_reason을 포함하세요.
            id는 원본과 동일하게, sentiment는 positive/negative/mixed중 하나,
            score는 0~100 정수, key_reason은 한 문장으로 작성하세요.
            수강평: {feedbacks_json}
            """

def parse_response(reply):
    payload = json.loads(reply)
    if not isinstance(payload, dict) or "items" not in payload:
        raise ValueError("응답은 items 키가 있는 JSON 객체여야 합니다.")

    items = payload["items"]
    if not isinstance(items, list):
        raise ValueError("items는 리스트여야 합니다.")

    required_keys = ["id", "sentiment", "score", "key_reason"]
    for item in items:
        if not isinstance(item, dict):
            raise ValueError("각 항목은 딕셔너리여야 합니다.")
        for key in required_keys:
            if key not in item:
                raise ValueError(f"필수 필드가 없습니다: {key}")
    return items

def main():
    prompt = build_prompt(FEEDBACKS)
    print("[모델에 보낼 프롬프트]")
    print(prompt)

if __name__ == "__main__":
    main()