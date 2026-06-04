CREATE TABLE sub_categories
(
    id   BIGSERIAL      PRIMARY KEY ,
    name               VARCHAR(255) NOT NULL,
    category_id BIGINT,
    owner VARCHAR(255) NOT NULL,
    user_id BIGINT
);

ALTER TABLE sub_categories
    ADD CONSTRAINT FK_SUB_CATEGORIES_ON_CATEGORY_ENTITY FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE sub_categories
    ADD CONSTRAINT FK_SUB_CATEGORIES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);