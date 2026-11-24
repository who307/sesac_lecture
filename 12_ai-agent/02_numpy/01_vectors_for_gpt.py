# Numpy: 숫자 배열을 만들고 배열 전체를 계산하는 기능을 제공하는 Python 라이브러리
import numpy as np

left = [1, 2]
right = [3, 4]

print("리스트의 + :", left + right)

# Numpy 배열의 +는 같은 위치의 숫자끼리 계산한다.
print("Numpy 배열의 + : ", np.array(left) + np.array(right))

# 벡터: 같은 기준과 순서에 맞춰 나열한 숫자 목록
# 액션, 스릴러, 코미디, 감동
moana = np.array([0, 0, 0, 1])
toy_story = np.array([0, 0, 1, 1])
action_movie = np.array([1, 0, 0, 0])

print("\n모아나:", moana)
print("토이스토리:", toy_story)
print("액션영화:", action_movie)

# 코사인 유사도: 두 벡터의 방향이 얼마나 비슷한지 점수로 나타내는 방법 중 하나
def cosine_similarity(a, b):
    # 내적: 같은 위치의 숫자끼리 곱한 뒤 모두 더한 값
    dot = np.dot(a, b)

    # 벡터의 길이는 원점에서 해당 좌표까지의 길이
    length_a = np.linalg.norm(a)
    length_b = np.linalg.norm(b)

    # 내적을 두 길이의 곱으로 나눠 방향을 비교한다
    return dot / (length_a * length_b)


print(f"\n 모아나와 자기 자신의 유사도: {cosine_similarity(moana, moana)}")
print(f"\n 모아나와 토이스토리 유사도: {cosine_similarity(moana, toy_story)}")
print(f"\n 모아나와 액션 영화의 유사도: {cosine_similarity(moana, action_movie)}")

