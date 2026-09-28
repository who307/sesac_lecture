'use client';

import { useState } from "react";
import GrandChild from "./components/GrandChild";
import Child from "./components/Child";

export default function Home() {
    const [count, setCount] = useState(0);
    return (
        <>
            <h1>Props Drilling</h1>
            <h2>Page에서 보는 count: {count}</h2>
            <Child count={count} setCount={setCount} />
        </>
    );
}
