CREATE TABLE book (
                         id UUID PRIMARY KEY,
                         title VARCHAR(100) NOT NULL,
                         author VARCHAR(255),
                         type VARCHAR(100) NOT NULL,
                         edition VARCHAR(100),
                         publication_year INT,
                         base_price numeric(5,2) NOT NULL,
                         quantity INT NOT NULL DEFAULT 0,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT uq_book_title_author_edition_year UNIQUE (title, author, edition, publication_year)
);
CREATE INDEX idx_book_title ON book (title);

CREATE TABLE customer (id UUID PRIMARY KEY,
                                 name VARCHAR(150) NOT NULL,
                                 email VARCHAR(255) NOT NULL UNIQUE,
                                 phone VARCHAR(30),
                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP
);

CREATE TABLE purchase (id UUID PRIMARY KEY,
                                 customer_id UUID NOT NULL,
                                 total_price NUMERIC(10,2) NOT NULL,
                                 discount_applied NUMERIC(10,2) DEFAULT 0,
                                 loyalty_points_used INT DEFAULT 0,
                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 CONSTRAINT fk_purchase_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE);

CREATE TABLE purchase_item (id UUID PRIMARY KEY,
                                      purchase_id UUID NOT NULL REFERENCES purchase(id) ON DELETE CASCADE,
                                      book_id UUID NOT NULL REFERENCES book(id), quantity INT NOT NULL CHECK (quantity > 0),
                                      discount NUMERIC(8,2) DEFAULT 0,
                                      final_price NUMERIC(10,2) NOT NULL);

CREATE TABLE customer_loyalty (id UUID PRIMARY KEY,
                                         customer_id UUID NOT NULL UNIQUE,
                                         loyalty_points INT NOT NULL DEFAULT 0,
                                         last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         CONSTRAINT fk_loyalty_customer FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE);


INSERT INTO book (id, title, author, type, edition, publication_year, base_price, quantity)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'Clean Code', 'Robert C. Martin', 'REGULAR', '1st', 2008, 89.90, 10),
    ('22222222-2222-2222-2222-222222222222', 'Refactoring', 'Martin Fowler', 'OLD_EDITIONS', '2nd', 1999, 79.50, 5),
    ('33333333-3333-3333-3333-333333333333', 'Java Concurrency', 'Brian Goetz', 'NEW_RELEASES', '1st', 2023, 120.00, 8),
    ('44444444-4444-4444-4444-444444444444', 'Effective Java', 'Joshua Bloch', 'REGULAR', '3rd', 2018, 99.99, 12);


INSERT INTO customer (id, name, email, phone, created_at)
VALUES
    ('aaaa1111-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Alice Johnson', 'alice@example.com', '+1-202-555-0143', CURRENT_TIMESTAMP),
    ('bbbb2222-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Bob Smith', 'bob.smith@example.com', '+1-202-555-0192', CURRENT_TIMESTAMP),
    ('cccc3333-cccc-cccc-cccc-cccccccccccc', 'Carla Mendes', 'carla.m@example.com', NULL, CURRENT_TIMESTAMP),
    ('dddd4444-dddd-dddd-dddd-dddddddddddd', 'David Lee', 'david.lee@example.com', '+1-202-555-0188', CURRENT_TIMESTAMP);

INSERT INTO customer_loyalty (id, customer_id, loyalty_points, last_updated) VALUES
                                                                                 ('11111111-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'aaaa1111-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 5, CURRENT_TIMESTAMP),
                                                                                 ('22222222-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'bbbb2222-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 10, CURRENT_TIMESTAMP),
                                                                                 ('33333333-cccc-cccc-cccc-cccccccccccc', 'cccc3333-cccc-cccc-cccc-cccccccccccc', 2, CURRENT_TIMESTAMP),
                                                                                 ('44444444-dddd-dddd-dddd-dddddddddddd', 'dddd4444-dddd-dddd-dddd-dddddddddddd', 0, CURRENT_TIMESTAMP);