CREATE TABLE clients (
    id BINARY(16) NOT NULL,
    user_name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    created_at DATETIME NOT NULL,
    created_by BINARY(16) NULL,
    updated_at DATETIME NULL,
    updated_by BINARY(16) NULL,
    PRIMARY KEY (id),
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;