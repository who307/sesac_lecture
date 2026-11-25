from pathlib import Path
from langchain_community.document_loaders import TextLoader
from langchain_core.documents import Document

DATA_DIR = Path(__file__).resolve().parent / "data"
FILENAMES = ["center_policy.txt", "course_guide.txt"]

def main():
    documents = []
    for filename in FILENAMES:
        path = DATA_DIR / filename
        
        # Document: 본문과 부가 정보를 함께 보관하는 객체 생성
        document = Document(
            page_content=path.read_text(encoding="utf-8"),
            metadata={"source": path.name}
        )
        documents.append(document)

        # loaded = TextLoader(str(DATA_DIR / filename), encoding="utf-8")
        # documents.extend(loaded)
        
    for document in documents:
        document.metadata["source"] = Path(document.metadata["source"]).name
        print("타입:", type(document).__name__)
        print("출처:", document.metadata["source"])
        print("본문 앞부분:", document.page_content[:100])
        print()
        
if __name__ == "__main__":
    main()