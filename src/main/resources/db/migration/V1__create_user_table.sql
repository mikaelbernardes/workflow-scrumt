CREATE TABLE tb_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    failed_login_attempts SMALLINT NOT NULL DEFAULT 0,
    account_locked BOOLEAN NOT NULL DEFAULT FALSE,
    lock_time TIMESTAMP,
    two_factor_enabled BOOLEAN NOT NULL DEFAULT FALSE,
    role VARCHAR(50) NOT NULL
)