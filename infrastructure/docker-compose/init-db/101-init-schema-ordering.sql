DROP SCHEMA IF EXISTS ordering;
CREATE SCHEMA ordering;

DROP TYPE IF EXISTS order_status;
CREATE TYPE order_status AS ENUM ('PENDING', 'PAID', 'APPROVED', 'CANCELLED', 'CANCELLING');

DROP TABLE IF EXISTS ordering.customers;
DROP TABLE IF EXISTS ordering.orders;
DROP TABLE IF EXISTS ordering.items;

CREATE TABLE ordering.customers
(
    id UUID PRIMARY KEY NOT NULL,
    firstname VARCHAR NOT NULL,
    lastname VARCHAR NOT NULL
);

CREATE TABLE ordering.orders
(
    id UUID PRIMARY KEY NOT NULL,
    customer_id UUID NOT NULL,
    restaurant_id UUID NOT NULL,
    street VARCHAR NOT NULL,
    city VARCHAR NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    tracking_id UUID NOT NULL,
    status order_status NOT NULL,
    failure_messages VARCHAR NOT NULL
);

CREATE TABLE ordering.items
(
    id INTEGER NOT NULL,
    order_id UUID NOT NULL,
    product_id UUID NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    sub_total NUMERIC(10,2) NOT NULL,
    PRIMARY KEY (id, order_id)
);

ALTER TABLE ordering.orders
ADD CONSTRAINT orders_customer_id_customers_id FOREIGN KEY (customer_id) REFERENCES ordering.customers(id);

ALTER TABLE ordering.items
ADD CONSTRAINT items_order_id_orders_id FOREIGN KEY (order_id) REFERENCES ordering.orders(id)
ON DELETE CASCADE ON UPDATE CASCADE;