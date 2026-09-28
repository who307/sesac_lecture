import { create } from "zustand";

export const useUIStore = create((set) => ({
    isModalOpen: false,
    isSidebarOpen: false,

    openModal: () => set({ isModalOpen: true }),
    closeModal: () => set({ isModalOpen: false }),
    openSidebar: () => set({ isSidebarOpen: true }),

    toggleSidebar: () => set((state) => ({ isSidebarOpen: !state.isSidebarOpen })),
}));
