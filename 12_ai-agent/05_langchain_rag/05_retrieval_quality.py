from pathlib import Path

from dotenv import load_dotenv
from langchain_core.documents import Document
from langchain_core.vectorstores import InMemoryVectorStore
from langchain_openai import ChatOpenAI, OpenAIEmbeddings
from langchain_text_splitters import RecursiveCharacterTextSplitter
from openai import APIError

DATA_DIR = Path(__file__).resolve().parent / "data"
TEST_CASES = [
    ("강의를 취소하면 낸 돈을 돌려받을 수 있나요?", "center_policy.txt"),
    ("주말에 실습실은 몇 시까지 열려 있나요?", "center_policy.txt"),
    ("질문과 관련된 자료를 찾아 모델에게 주는 방식은>\?", "course_guide.txt")
]

def main():
    load_dotenv()
    documents=[]
    for filename in ["center_policy.txt", "course_guide.txt"]:
        path = DATA_DIR / filename
        document = Document(
            page_content=path.read_text(encoding="utf-8"),
            metadata={"source": path.name},
        )
        documents.append(document)
    splitter = RecursiveCharacterTextSplitter(chunk_size=200, chunk_overlap=40)
    chunks = splitter.split_documents(documents)
    embeddings = OpenAIEmbeddings(model="text-embedding-3-small", request_timeout=20, max_retries=0)
    store = InMemoryVectorStore(embeddings)
    store.add_documents(chunks)
    
    for k in [1, 2]:
        retriever = store.as_retriever(search_kwargs={"k": k})
        hits = 0 # 기대 출처를 찾은 질문 수를 세기 위한 변수
        for question, expected_source in TEST_CASES:
            results = retriever.invoke(question)
            found = expected_source in [document.metadata["source"] for document in results]
            hits += int(found) # int(True) -> 1 int(False) -> 0
            print(f"k={k} / 기대 출처 포함={found} / {question}")
        print(f"출처 적중: {hits}/{len(TEST_CASES)}\n")
        
if __name__ == "__main__":
    try:
        main()
    except APIError as error:
        print("API 요청 실패", type(error))