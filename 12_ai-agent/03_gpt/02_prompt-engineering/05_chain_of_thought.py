from lesson_support import lesson_client, show_prompt

PROBLEM = """내 차를 세차하고 싶다.
세차장은 50미터 거리에 있다.
걸어가야 하는가, 아니면 운전해서 가야 하는가?"""

def main():
    with lesson_client() as client:
        show_prompt(client, "짧은 답 요청", f"{PROBLEM}\n한 문장으로 답하세요.")
        show_prompt(
            client, "필요 조건과 짧은 근거 요청", 
            f"{PROBLEM}\n 세차를 해야 할 대상이 무엇인지 고려해서 잘 단계별로 생각해서 결론과 근거를 각각 한 문장으로 답하세요.")

if __name__ =="__main__":
    main()