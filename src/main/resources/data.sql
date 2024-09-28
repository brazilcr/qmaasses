INSERT INTO category (name, parent_id) VALUES ('Eletronicos', NULL);
INSERT INTO category (name, parent_id) VALUES ('Notebooks', 1);
INSERT INTO category (name, parent_id) VALUES ('Roupas', NULL);

INSERT INTO product (name, description, price, category_id) VALUES ('Laptop', 'Super Laptop.', 1200.00, 1);
INSERT INTO product (name, description, price, category_id) VALUES ('Camisa', 'Camisa linda', 25.00, 2);
