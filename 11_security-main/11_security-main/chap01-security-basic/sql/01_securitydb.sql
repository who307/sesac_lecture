-- MySQL Workbench의 관리자(root) 연결에서 실행합니다.
-- 앞선 MySQL 수업에서 만든 ohgiraffers 계정을 그대로 사용합니다.
CREATE DATABASE IF NOT EXISTS securitydb
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

GRANT ALL PRIVILEGES ON securitydb.* TO 'ohgiraffers'@'%';
SHOW GRANTS FOR 'ohgiraffers'@'%';

-- 이어서 Workbench의 ohgiraffers 연결에서 아래 두 문장을 실행합니다.
-- USE securitydb;
-- SELECT DATABASE(), CURRENT_USER();
