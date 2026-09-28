const BASE_URL = "http://localhost:4000/posts";

export const postApi = {
    getPosts: async () => {
        const response = await fetch(BASE_URL);

        if (!response.ok) {
            throw new Error("게시글 목록을 가져오지 못했습니다");
        }
        return response.json();
    },
    createPost: async (newPost) => {
        const response = await fetch(BASE_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            // stringify: JS 객체를 JSON 본문으로 보낼때 사용
            body: JSON.stringify(newPost),
        });
        if (!response.ok) {
            throw new Error("게시글 등록 실패");
        }
        return response.json();
    },
    updatePost: async (id, updatedTitle) => {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: "PATCH",
            headers: { "Content-type": "application/json" },
            body: JSON.stringify({ title: updatedTitle }),
        });
        if (!response.ok) {
            throw new Error("게시글 수정 실패");
        }
        return response.json();
    },
    delete: async (id) => {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: "DELETE",
        });
        if (!response.ok) {
            throw new Error("게시글 삭제 실패");
        }
        return true;
    },
};
