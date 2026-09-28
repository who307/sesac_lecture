import { create } from "zustand";

export const useUserStore = create((set) => ({
    user: null,
    loading: false,

    setUser: (user) => set({ user }),
    // login: (userData) => set({ user: userData }),
    // logout: () => set({ user: null }),

    // 비동기 액션
    fetchUser: async () => {
        set({ loading: true });

        try {
            const res = await fetch("https://jsonplaceholder.typicode.com/users/1");
            const data = await res.json();

            set({ user: data });
        } catch (error) {
            console.error("데이터 가져오기 실패", error);
        } finally {
            set({ loading: false });
        }
    },
}));
