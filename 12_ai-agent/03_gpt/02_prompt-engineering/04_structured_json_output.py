import json
import argparse

from lesson_support import lesson_client, show_prompt

FEEDBACKS = [
    {"id": 1, "text": "진도가 조금 빠르지만 실습으로 이해하고 있습니다."},
    {"id": 2, "text": "API 키 설정에서 막혀 수업을 거의 못 따라갔습니다."},
    {"id": 3, "text": "예제를 더 연습하고 싶습니다."},
]
SAMPLE_REPLY = json.dumps({"items": [
    {"id": 1, "sentiment": "mixed", "score": 75, "key_reason": "진도는 빠르지만 실습이 도움이 됩니다."},
    {"id": 2, "sentiment": "negative", "score": 35, "key_reason": "환경 설정이 학습을 막았습니다."},
    {"id": 3, "sentiment": "mixed", "score": 65, "key_reason": "추가 연습이 필요합니다."},
]}, ensure_ascii=False)

def build_json_prompt():
    feedbacks = json.dumps(FEEDBACKS, ensure_ascii=False)
    return (
            '아래의 수강평을 분석해 JSON 객체만 출력하세요. 최상위 키는 "items"입니다.\n'
            '각 항목은 id, sentiment, score, key_reason을 포함하세요.\n'
            'id는 원본과 동일하게, sentiment는 positive/negative/mixed중 하나,\n'
            'score는 0~100 정수, key_reason은 한 문장으로 작성하세요.\n'
            f'수강평: {feedbacks}'
    )

def validate_items(items):
    ids = []
    for item in items:
        if not isinstance(item, dict) or set(item) != {"id", "sentiment", "score", "key_reason"}:
            raise ValueError("항목의 필드가 맞지 않습니다.")
        if type(item["id"]) is not int:
            raise ValueError("id는 정수여야 합니다.")
        ids.append(item["id"])
        if item["sentiment"] not in ("positive", "negative", "mixed"):
            raise ValueError("sentiment가 허용된 값이 아닙니다.")
        if type(item["score"]) is not int or not 0 <= item["score"] <= 100:
            raise ValueError("score는 0~100 정수여야 합니다.")
        if not isinstance(item["key_reason"], str) or not item["key_reason"].strip():
            raise ValueError("key_reason은 비어 있지 않은 문자열이어야 합니다.")
    if sorted(ids) != [row["id"] for row in FEEDBACKS]:
        raise ValueError("원본 id에 대응하는 항목이 하나씩 필요합니다.")

def parse_feedback(reply):
    payload = json.loads(reply)
    if not isinstance(payload, dict):
        raise ValueError("응답은 JSON 객체여야 합니다.")
    if "items" not in payload:
        raise ValueError("items 필드가 필요합니다.")
    items = payload["items"]
    if not isinstance(items, list):
        raise ValueError("items는 리스트여야 합니다")
    validate_items(items)
    return items

def main():
    parser = argparse.ArgumentParser()
    parser.add_argument("--local", action="store_true", help="준비된 JSON 문자열로 검증 연습")
    args = parser.parse_args()
    with lesson_client(local=args.local) as client:
        reply = show_prompt(client, "JSON 객체 요청", build_json_prompt(), response_format={"type": "json_object"})
    # 로컬 실행 - 직접 정한 예시 응답으로 검사
        if client is None:
            reply = SAMPLE_REPLY
        if reply is None:
            return
        try:
            items = parse_feedback(reply)
        except ValueError as error:
            print("응답 검증 실패: ", error)
            return
        for item in items:
            print(f"{item['id']}: {item['sentiment']} / {item['score']}점")

if __name__ == "__main__":
    main()