DROP TABLE IF EXISTS Schedule;
DROP TABLE IF EXISTS schedules;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE ,
    created_at DATE,
    updated_at DATE
);
CREATE TABLE IF NOT EXISTS schedules (
    id SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_id BIGINT,
    created_at DATE,
    updated_at DATE,
    CONSTRAINT sch_user_fk FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE SET NULL
);

-- 동명인 집어넣기
insert into users(name, email, created_at, updated_at)  values (
    'test',
    'test1@test.com',
    CURRENT_DATE,
    CURRENT_DATE
), (
    'test',
    'test2@test.com',
    CURRENT_DATE,
    CURRENT_DATE
);