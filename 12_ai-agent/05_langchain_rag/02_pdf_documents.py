from pathlib import Path
from langchain_core.documents import Document
from pypdf import PdfReader

DATA_FILE = Path(__file__).resolve().parent / "data" / "course_handbook.pdf"

def main():
    reader = PdfReader(DATA_FILE)
    documents: list[Document] = []
    
    for page_number, page in enumerate(reader.pages, start=1):
        text = page.extract_text() or "" # 현재 페이지 안의 텍스트를 문자열로 추출
        if not text.strip():
            continue
        
        document = Document(
            page_content=text.strip(),
            metadata={
                "source": DATA_FILE.name,
                "page": page_number
            }
        )
        documents.append(document)
        
    print("PDF 전체 페이지 수:", len(reader.pages))
    print("만들어진 DOcument 수:", len(documents))
    
    for document in documents:
        print(f"\n[{document.metadata['source']} / {document.metadata['page']}페이지]")
        print(document.page_content[:300])
        
if __name__ == "__main__":
    main()