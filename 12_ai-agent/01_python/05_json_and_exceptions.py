import json

# 요청에 붙일 JSON 문자열을 만든다.
feedback = {"id": 1, "text": "실습이 도움이 됐어요."}
# json.dumps(): JSON 문자열 반환(Python -> JSON)
# json.dump(): 파일에 직접 저장(Python -> JSON) 
request_json = json.dumps(feedback, ensure_ascii=False)
print(request_json)

# 응답 예시에서 필요한 값 얻기
reply = '{"id": 1, "score": 75}'
# json.loads(): JSON 문자열 읽기(JSON -> Python)
# json.load(): 파일에서 읽기(JSON -> Python)
result = json.loads(reply) 
print(result["score"])

broken_reply = '{"id": 1, "score": 75'
try:
    broken_result = json.loads(broken_reply)
except json.JSONDecodeError:
    print("응답을 JSON으로 읽을 수 없습니다.")