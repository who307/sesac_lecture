"use client";

import { useState } from "react";
import { signup, login, findMyProfile, refresh, logout } from "../lib/auth-api";

export default function Home() {
  const [userId, setUserId] = useState("user01");
  const [password, setPassword] = useState("pass1234");
  const [accessToken, setAccessToken] = useState("");
  const [result, setResult] = useState("로그인 전입니다.");
  const [busy, setBusy] = useState(false);

  async function runRequest(action) {
    setBusy(true);
    try {
      const value = await action();
      setResult(JSON.stringify(value, null, 2));
    } catch (error) {
      setResult(error.message);
    } finally {
      setBusy(false);
    }
  }

  async function handleLogin() {
    const tokens = await login(userId, password);
    setAccessToken(tokens.accessToken);
    return { message: "로그인 성공", tokenType: tokens.tokenType, expiresIn: tokens.expiresIn };
  }

  async function handleRefresh() {
    const tokens = await refresh();
    setAccessToken(tokens.accessToken);
    return { message: "재발급 성공", tokenType: tokens.tokenType, expiresIn: tokens.expiresIn };
  }

  async function handleLogout() {
    await logout();
    setAccessToken("");
    return { message: "로그아웃 완료" };
  }

  return (
    <main>
      <h1>Spring Security 브라우저 연동</h1>
      <p>Spring 8080 · Next.js 3000</p>
      <label>
        아이디
        <input value={userId} onChange={(event) => setUserId(event.target.value)} />
      </label>
      <label>
        비밀번호
        <input type="password" value={password} onChange={(event) => setPassword(event.target.value)} />
      </label>
      <p>Access Token 메모리 상태: {accessToken ? "있음" : "없음"}</p>
      <div className="actions">
        <button disabled={busy} onClick={() => runRequest(() => signup(userId, password))}>회원 가입</button>
        <button disabled={busy} onClick={() => runRequest(handleLogin)}>로그인</button>
        <button disabled={busy || !accessToken} onClick={() => runRequest(() => findMyProfile(accessToken))}>내 정보</button>
        <button disabled={busy} onClick={() => runRequest(handleRefresh)}>재발급</button>
        <button disabled={busy} onClick={() => runRequest(handleLogout)}>로그아웃</button>
      </div>
      <pre aria-live="polite">{result}</pre>
      <p>이미 가입한 계정이면 가입을 건너뛰고 로그인합니다.</p>
      <p>새로고침하면 Access Token 메모리는 비워집니다. Cookie가 유효하면 재발급 버튼으로 다시 준비할 수 있습니다.</p>
    </main>
  );
}
