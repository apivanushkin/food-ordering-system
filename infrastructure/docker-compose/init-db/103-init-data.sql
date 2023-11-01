insert into ordering.customers values('f329a137-d769-4d07-9a1b-33b2b5094d19', 'John', 'Daw');

insert into restaurant.restaurants values('db1c4de6-6e9a-4072-bdc7-31470d046d18', 'Bob Burgers', true);
insert into restaurant.restaurants values('d1fd701b-5868-4a3f-a3eb-50d691f4361b', 'Joe Pizza', false);

insert into restaurant.products values('6eb9f2f1-47b8-4e9b-afc9-5682b2e99115', 'Big Burger', 15.00);
insert into restaurant.products values('bd7be067-7c00-466b-af34-135aa246da3d', 'Cheeseburger', 10.00);
insert into restaurant.products values('b707a648-b11d-4f45-a55d-c6ef0300e4bc', 'Lemonade', 2.00);
insert into restaurant.products values('32258a80-4f03-451e-92bc-9a56ebdbe299', 'Veggie Pizza', 18.00);
insert into restaurant.products values('017ceec7-407b-49e9-80e1-1368a7aa72f7', 'Meat Pizza', 25.00);

insert into restaurant.restaurants_products values('db1c4de6-6e9a-4072-bdc7-31470d046d18', '6eb9f2f1-47b8-4e9b-afc9-5682b2e99115', false);
insert into restaurant.restaurants_products values('db1c4de6-6e9a-4072-bdc7-31470d046d18', 'bd7be067-7c00-466b-af34-135aa246da3d', true);
insert into restaurant.restaurants_products values('db1c4de6-6e9a-4072-bdc7-31470d046d18', 'b707a648-b11d-4f45-a55d-c6ef0300e4bc', true);
insert into restaurant.restaurants_products values('d1fd701b-5868-4a3f-a3eb-50d691f4361b', '32258a80-4f03-451e-92bc-9a56ebdbe299', true);
insert into restaurant.restaurants_products values('d1fd701b-5868-4a3f-a3eb-50d691f4361b', '017ceec7-407b-49e9-80e1-1368a7aa72f7', true);