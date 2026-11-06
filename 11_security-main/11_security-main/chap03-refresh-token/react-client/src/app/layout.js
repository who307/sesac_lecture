import "./globals.css";

export const metadata = {
  title: "Spring Security 브라우저 연동",
  description: "Access Token과 HttpOnly Refresh Token의 역할 확인",
};

export default function RootLayout({ children }) {
  return (
    <html lang="ko">
      <body>{children}</body>
    </html>
  );
}
