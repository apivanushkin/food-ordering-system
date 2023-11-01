DROP SCHEMA IF EXISTS restaurant;
CREATE SCHEMA restaurant;

DROP TABLE IF EXISTS restaurant.restaurants;
DROP TABLE IF EXISTS restaurant.products;

CREATE TABLE restaurant.restaurants
(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE restaurant.products
(
    id UUID PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,
    price NUMERIC(10, 2)
);

CREATE TABLE restaurant.restaurants_products
(
    restaurant_id UUID NOT NULL,
    product_id UUID NOT NULL,
    available BOOLEAN NOT NULL,
    PRIMARY KEY (restaurant_id, product_id)
);

ALTER TABLE restaurant.restaurants_products
ADD CONSTRAINT restaurants_products_restaurant_id_restaurants_id
FOREIGN KEY (restaurant_id) REFERENCES restaurant.restaurants(id)
ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE restaurant.restaurants_products
ADD CONSTRAINT restaurants_products_product_id_products_id
FOREIGN KEY (product_id) REFERENCES restaurant.products(id)
ON DELETE CASCADE ON UPDATE CASCADE;

DROP MATERIALIZED VIEW IF EXISTS restaurant.restaurants_products_m_view;

CREATE MATERIALIZED VIEW restaurant.restaurants_products_m_view
AS
    SELECT
        r.id AS restaurant_id,
        r.name AS restaurant_name,
        r.active AS restaurant_active,
        p.id AS product_id,
        p.name AS product_name,
        p.price AS product_price
    FROM
        restaurant.restaurants r,
        restaurant.products p,
        restaurant.restaurants_products rp
    WHERE r.id = rp.restaurant_id AND p.id = rp.product_id
WITH DATA;

REFRESH MATERIALIZED VIEW restaurant.restaurants_products_m_view;

DROP function IF EXISTS restaurant.refresh_restaurants_products_m_view;

CREATE OR replace function restaurant.refresh_restaurants_products_m_view()
returns trigger
AS '
    BEGIN
        refresh materialized VIEW restaurant.restaurants_products_m_view;
        return null;
    END; '
LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_restaurants_products_m_view ON restaurant.restaurants_products;

CREATE trigger refresh_restaurants_products_m_view
after INSERT OR UPDATE OR DELETE OR truncate
ON restaurant.restaurants_products FOR each statement
EXECUTE PROCEDURE restaurant.refresh_restaurants_products_m_view();