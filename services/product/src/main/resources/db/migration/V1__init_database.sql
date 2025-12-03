
CREATE TABLE category (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE product (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    available_quantity DOUBLE PRECISION NOT NULL,
    price NUMERIC(19,2),
    category_id BIGINT,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE SEQUENCE category_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE product_seq START WITH 1 INCREMENT BY 50;

