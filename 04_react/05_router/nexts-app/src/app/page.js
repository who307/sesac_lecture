"use client";
import Link from "next/link";
import styles from "./page.module.css";
import { useState } from "react";

export default function Home() {
    return (
        <>
            <h1 className={styles.title}>홈 페이지입니다.</h1>
        </>
    );
}
