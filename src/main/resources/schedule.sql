CREATE TABLE Schedule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    password TEXT NOT NULL,
    createdAt DATE,
    updatedAt DATE
);