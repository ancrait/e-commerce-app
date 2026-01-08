CREATE TABLE  IF NOT EXISTS category
(
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)

);

CREATE TABLE IF NOT EXISTS product
(
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    available_quantity DOUBLE PRECISION NOT NULL,
    price numeric(38,2),
    category_id BIGINT,
    CONSTRAINT fkey1_category FOREIGN KEY (category_id) REFERENCES category(id)
);

create SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 50;
create SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 50;
