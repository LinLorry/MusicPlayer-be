CREATE TABLE IF NOT EXISTS user
(
    id          BIGINT     AUTO_INCREMENT PRIMARY KEY,
    username    CHAR(64)   UNIQUE                    NOT NULL,
    password    VARCHAR(255)                         NOT NULL,
    disable     TINYINT(1) DEFAULT 0                 NOT NULL,
    create_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_general_ci;
