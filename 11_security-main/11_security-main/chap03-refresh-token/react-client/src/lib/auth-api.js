const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL;

async function readResponse(response) {
  if (response.status === 204) return null;

  const body = await response.json();
  if (!response.ok) {
    throw new Error(body.message ?? `요청 실패: ${response.status}`);
  }
  return body;
}

export async function signup(userId, password) {
  const response = await fetch(`${API_BASE_URL}/api/auth/signup`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      userId,
      password,
      userName: "홍길동",
      email: `${userId}@test.com`,
    }),
  });
  return readResponse(response);
}

export async function login(userId, password) {
  const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    credentials: "include",
    body: JSON.stringify({ userId, password }),
  });
  return readResponse(response);
}

export async function findMyProfile(accessToken) {
  const response = await fetch(`${API_BASE_URL}/api/users/me`, {
    headers: { Authorization: `Bearer ${accessToken}` },
  });
  return readResponse(response);
}

export async function refresh() {
  const response = await fetch(`${API_BASE_URL}/api/auth/refresh`, {
    method: "POST",
    credentials: "include",
  });
  return readResponse(response);
}

export async function logout() {
  const response = await fetch(`${API_BASE_URL}/api/auth/logout`, {
    method: "POST",
    credentials: "include",
  });
  return readResponse(response);
}
