from pathlib import Path
from langchain_core.documents import Document
from langchain_text_splitters import RecursiveCharacterTextSplitter

DATA_FILE = Path(__file__).resolve().parent / "data" / "course_guide.txt"

def print_chunks(documents, chunk_overlap):
    splitter = RecursiveCharacterTextSplitter(chunk_overlap=chunk_overlap, chunk_size=120)
    chunks = splitter.split_documents(documents)
    print(f"\n겹침 목표 {chunk_overlap}자 조각 {len(chunks)}개")
    for number, chunk in enumerate(chunks, start=1):
        print(f"\n조각 {number} / {len(chunk.page_content)}자 / {chunk.metadata}")
        print(chunk.page_content)
        
def main():
    document = Document(
        page_content=DATA_FILE.read_text(encoding="utf-8"),
        metadata={"source": DATA_FILE.name}
    )
    documents = [document]
    print_chunks(documents, chunk_overlap=0)
    print_chunks(documents, chunk_overlap=60)
    
if __name__ == "__main__":
    main()