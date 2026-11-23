# 함수와 타입 힌트
def create_greeting(name: str, age: int = 20) -> str:
    return f"안녕하세요, {age}세 {name}님."

print(create_greeting("홍길동", 30))
print(create_greeting("유관순"))
print(create_greeting(age=45, name="신사임당")) # 키워드 인자는 순서가 바뀌어도 된다

greeting = create_greeting("영희")
print(greeting)
