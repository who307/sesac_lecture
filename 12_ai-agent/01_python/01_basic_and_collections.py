team_name = '새싹'  # str (문자열)
price = 123_456    # int (정수), 밑줄로 긴 숫자의 가독성을 높일 수 있음

print(team_name)

# f-string: 문자열 앞에 f를 붙이고 중괄호 안에 변수명을 넣으면 해당 값이 삽입된다
print(f"팀 이름: {team_name}")

# 리스트: 순서가 있고 내용을 변경할 수 있는 자료형
scores = [88, 90, 67]
print(scores[0])

# 값 추가, 제거
scores.append(100)      # 끝에 값 추가
scores.insert(2, 75)    # 2번 인덱스에 삽입
print(f"추가 후 점수 {scores}")
last_score = scores.pop()   # 인덱스를 주지 않으면 맨 뒤 요소를 꺼내고 제거
print(f"꺼낸 점수 : {last_score}, 수정된 점수: {scores}")

# 딕셔너리: 키와 값을 한 쌍으로 저장하는 자료형
user = {'name': '홍길동', 'age': 30}
print(f"사용자 정보:{user}, 이름: {user['name']}")

# 키가 없으면 새로 추가, 있으면 기존 값을 덮어씀
user['job'] = '의적'
user['age'] = 33
print(user)

# 존재하지 않는 키를 대괄호로 조회하면 KeyError 발생
# print(user['email'])

# get(키, 기본값)은 키가 없어도 오류 대신 기본값 반환
email = user.get('email', '정보 없음')
print(f"사용자 이메일: {email}")

# 리스트와 딕셔너리 조합
users = [
    {'name': '홍길동'},
    {'name': '신사임당', 'job':'화가'}
]

print(users[1]['name'])
print(users[0].get('job', '정보 없음'))


