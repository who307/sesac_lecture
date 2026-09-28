"use client";

import { postApi } from "@/api/postApi";
import { useEffect, useState } from "react";

export default function CRUDPage() {
    const [posts, setPosts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const loadPosts = async () => {
        try {
            setError(null);
            const data = await postApi.getPosts();
            setPosts(data);
        } catch (error) {
            setError(error.message);
        } finally {
            setLoading(false);
        }
    };

    const handleAdd = async () => {
        try {
            setError(null);
            await postApi.createPost({
                title: `새로운 게시글 #${posts.length + 1}`,
                author: "익명",
            });
            await loadPosts();
        } catch (error) {
            setError(error.message);
        }
    };

    const handleUpdate = async (id, currentTitle) => {
        const newTitle = prompt("수정할 게시글 제목을 입력하세요:", currentTitle);
        if (!newTitle) return;

        try {
            setError(null);
            await postApi.updatePost(id, newTitle);
            await loadPosts();
        } catch (error) {
            setError(error.message);
        }
    };

    const handleDelete = async (id) => {
        const shouldDelete = confirm("정말 삭제할까요?");
        if (!shouldDelete) return;

        try {
            setError(null);
            await postApi.delete(id);
            await loadPosts();
        } catch (error) {
            setError(error.message);
        }
    };

    useEffect(() => {
        loadPosts();
    }, []);

    if (loading) {
        return (
            <div style={centerContainer}>
                <div style={loadingBox}>
                    <p style={loadingText}>게시글을 불러오는 중입니다...</p>
                </div>
            </div>
        );
    }

    if (error) {
        return (
            <div style={centerContainer}>
                <div style={errorCard}>
                    <p style={errorText}>⚠️ 오류: {error}</p>
                    <button style={retryButton} onClick={loadPosts}>
                        다시 시도
                    </button>
                </div>
            </div>
        );
    }

    return (
        <div style={container}>
            {/* 상단 헤더 영역 */}
            <header style={header}>
                <div>
                    <h1 style={title}>게시글 관리</h1>
                    <p style={subtitle}>총 {posts.length}개의 게시글이 있습니다.</p>
                </div>
                <button style={addButton} onClick={handleAdd}>
                    + 새 글 등록
                </button>
            </header>

            {/* 게시글 목록 카드 영역 */}
            {posts.length === 0 ? (
                <div style={emptyCard}>
                    <p style={emptyText}>등록된 게시글이 없습니다. 새 글을 등록해보세요!</p>
                </div>
            ) : (
                <ul style={postList}>
                    {posts.map((post) => (
                        <li key={post.id} style={postCard}>
                            <div style={postContent}>
                                <h3 style={postTitle}>{post.title}</h3>
                                <div style={postMeta}>
                                    <span style={authorBadge}>👤 {post.author}</span>
                                    <div style={actionGroup}>
                                        <span style={editHint} onClick={() => handleUpdate(post.id, post.title)}>
                                            수정 ✏️
                                        </span>
                                        <button style={deleteButton} onClick={() => handleDelete(post.id)}>
                                            삭제 🗑️
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

const container = {
    maxWidth: "680px",
    margin: "40px auto",
    padding: "0 20px",
    fontFamily: "-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif",
    color: "#1f2937",
};

const header = {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: "28px",
    paddingBottom: "16px",
    borderBottom: "2px solid #f3f4f6",
};

const title = {
    fontSize: "24px",
    fontWeight: 700,
    margin: 0,
    color: "#111827",
};

const subtitle = {
    fontSize: "14px",
    color: "#6b7280",
    marginTop: "4px",
    margin: 0,
};

const addButton = {
    backgroundColor: "#4f46e5",
    color: "#ffffff",
    border: "none",
    padding: "10px 18px",
    borderRadius: "8px",
    fontWeight: 600,
    fontSize: "14px",
    cursor: "pointer",
    boxShadow: "0 2px 4px rgba(79, 70, 229, 0.2)",
};

const postList = {
    listStyle: "none",
    padding: 0,
    margin: 0,
    display: "flex",
    flexDirection: "column",
    gap: "12px",
};

const postCard = {
    backgroundColor: "#ffffff",
    border: "1px solid #e5e7eb",
    borderRadius: "12px",
    padding: "18px 20px",
    boxShadow: "0 1px 3px rgba(0, 0, 0, 0.05)",
};

const postContent = {
    display: "flex",
    flexDirection: "column",
    gap: "8px",
};

const postTitle = {
    margin: 0,
    fontSize: "16px",
    fontWeight: 600,
    color: "#111827",
};

const postMeta = {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    fontSize: "13px",
};

const authorBadge = {
    color: "#4b5563",
    backgroundColor: "#f3f4f6",
    padding: "2px 8px",
    borderRadius: "6px",
    fontWeight: 500,
};

const actionGroup = {
    display: "flex",
    alignItems: "center",
    gap: "12px",
};

const editHint = {
    color: "#4f46e5",
    cursor: "pointer",
    fontWeight: 500,
};

const deleteButton = {
    backgroundColor: "transparent",
    color: "#ef4444",
    border: "none",
    cursor: "pointer",
    fontSize: "13px",
    padding: 0,
    fontWeight: 500,
};

const centerContainer = {
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    minHeight: "300px",
};

const loadingBox = {
    padding: "20px 30px",
    backgroundColor: "#f9fafb",
    borderRadius: "10px",
    border: "1px solid #e5e7eb",
};

const loadingText = {
    margin: 0,
    color: "#6b7280",
    fontWeight: 500,
};

const errorCard = {
    padding: "20px",
    backgroundColor: "#fef2f2",
    border: "1px solid #fecaca",
    borderRadius: "10px",
    textAlign: "center",
};

const errorText = {
    color: "#dc2626",
    margin: "0 0 12px 0",
    fontWeight: 500,
};

const retryButton = {
    backgroundColor: "#dc2626",
    color: "#fff",
    border: "none",
    padding: "6px 14px",
    borderRadius: "6px",
    cursor: "pointer",
};

const emptyCard = {
    textAlign: "center",
    padding: "40px 20px",
    backgroundColor: "#f9fafb",
    border: "2px dashed #e5e7eb",
    borderRadius: "12px",
};

const emptyText = {
    color: "#9ca3af",
    margin: 0,
};