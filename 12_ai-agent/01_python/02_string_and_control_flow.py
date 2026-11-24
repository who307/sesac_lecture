# 문자열 핵심 기능
# 슬라이싱: 문자열의 일부를 읽는다. [시작 인덱스 : 끝 인덱스]
# 끝 인덱스는 포함되지 않는다

txt = 'helloworld'
print(f"슬라이싱[0:5]: {txt[:5]}")  # 시작을 생략하면 처음부터
print(txt[5:])                     # 끝을 생략하면 끝까지

# 제어문: if, for
score = 85

# Python은 중괄호 대신 들여쓰기로 코드 블록을 구분한다.
# if문이나 for문 끝에 콜론을 쓰고, 다음 줄에서 들여쓰기 한 부분을 실행 범위로 인식

if score >= 90:
    grade = 'A'
elif score >= 80:
    grade = 'B'
else:
    grade = 'F'
print(f"점수: {score}, 등급: {grade}")

# for in 반복문: 리스트의 값을 하나씩 꺼내 반복
foods = ["햄버거", "피자", "보쌈"]

for food in foods:
    if food != "피자":
        print(f"오늘의 메뉴: {food}")

