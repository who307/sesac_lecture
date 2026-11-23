# 리스트 컴프리헨션: 반복과 조건을 이용하여 새 리스트를 만든다
fruits = ["orange", "apple", "banana", "kiwi"]

long_fruits = []

# 다섯 글자 이상인 과일만 골라 대문자로 바꾸고 새로운 리스트에 담는다
for fruit in fruits:
    if len(fruit) >= 5:
        long_fruits.append(fruit.upper())

print(long_fruits)

compact_fruits = [fruit.upper() for fruit in fruits if len(fruit) >= 5]
print(compact_fruits)