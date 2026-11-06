-- MySQL 연결의 기본 DB를 먼저 확인한 뒤 실행한다.
USE menudb;
SELECT DATABASE();

-- 기존 tbl_menu / tbl_category는 생성하거나 비우지 않는다.
CREATE TABLE IF NOT EXISTS practice_order (
    id BIGINT NOT NULL AUTO_INCREMENT,
    customer VARCHAR(255) NOT NULL,
    created_at DATETIME(6),
    updated_at DATETIME(6),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS practice_order_line (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    menu_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_practice_line_order
        FOREIGN KEY (order_id) REFERENCES practice_order(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SHOW TABLES LIKE 'practice_order%';
SHOW CREATE TABLE practice_order;
SHOW CREATE TABLE practice_order_line;
