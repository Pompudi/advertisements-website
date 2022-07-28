INSERT INTO avito_schema.tb_user (login, password, first_name, last_name, phone_number, create_timestamp) VALUES ('balashovs', '$2a$10$yjyGZzH9XKsjQL9ipYpoW.issd/7dc5iRzw4lO7r2wBZUoVl8iR/y', 'Савелий', 'Балашов', '+7(802) 839-40-93', '2022-07-25 13:01:55.902350');
INSERT INTO avito_schema.tb_user (login, password, first_name, last_name, phone_number, create_timestamp) VALUES ('kondratyevae', '$2a$10$G4vvnp6IP6aqNBOX0ZGl7.QcYI16NdCRnrvGD2tTsFsboPxNYhrQm', 'Елизавета', 'Кондратьева', '+7(935) 372-64-78', '2022-07-25 13:03:22.246098');
INSERT INTO avito_schema.tb_user (login, password, first_name, last_name, phone_number, create_timestamp) VALUES ('ustinovv', '$2a$10$6wGZaOkLYr5wAJB2cjuMTufA.bldAK/EzJn2Aq6wJYss9Nh8m5qmS', 'Виктор', 'Устинов', '+7(823) 984-09-28', '2022-07-25 13:04:00.467930');
INSERT INTO avito_schema.tb_user (login, password, first_name, last_name, phone_number, create_timestamp) VALUES ('yakovleva', '$2a$10$8jqGni7oukyGY4Z61Ko1weikmZIU/DdQKxcGznntY.j7f/d/GhWB6', 'Алексей', 'Яковлев', '+7(952) 498-37-92', '2022-07-25 13:05:10.287223');


INSERT INTO avito_schema.tb_image (image_url) VALUES ('/minio/4f430ece-e17e-4cc0-b033-01c36650bbfd.jpg');
INSERT INTO avito_schema.tb_image (image_url) VALUES ('/minio/c8262640-33da-4c79-bb9e-17cbd0025e8e.jpg');
INSERT INTO avito_schema.tb_image (image_url) VALUES ('/minio/607e3496-a070-433f-9619-0444f17bba07.jpg');
INSERT INTO avito_schema.tb_image (image_url) VALUES ('/minio/50b74eed-9c3d-4441-a2e1-3759c3da7c36.jpg');
INSERT INTO avito_schema.tb_image (image_url) VALUES ('/minio/d2db9198-9a1f-40ca-bec9-c1a76c38b707.jpg');


INSERT INTO avito_schema.tb_product (seller_id, category_id, image_id, customer_id, name, price, address, active, create_timestamp, deal_timestamp, description) VALUES (1, 6, 1, null, 'Xiaomi Poco X4 Pro 5G 8 256Gb В наличии', 20990, 'Москва, ул. Сущёвский Вал, 5с1А', true, '2022-07-25 13:15:21.663245', null, 'Состояние: Новое
Производитель: Xiaomi
Модель: Poco X4 Pro 5G
Встроенная память: 256 ГБ
Цвет: черный
Оперативная память: 8 ГБ');
INSERT INTO avito_schema.tb_product (seller_id, category_id, image_id, customer_id, name, price, address, active, create_timestamp, deal_timestamp, description) VALUES (1, 2, 2, null, 'Купить бу аккумулятор 60Ач', 1800, 'Москва, Черницынский пр., 3с6', true, '2022-07-25 13:17:13.976282', null, 'Акция! Делаем скидку за старый до 2500 рублей!

Доставка по Москве и МО. По РФ отправляем ПЭК.

Преимущества покупки аккумулятора в магазине AKBMOSCOW

- Скидка за старый аккумулятор

- Оперативная доставка за 2 часа

- Собственный гарантийный сервис

- Сертифицированная продукция

- Низкие цены

- Подарок при покупке

- Скидки для постоянных клиентов

Работаем с Юр.лицами с НДС.

Мелкий и крупный опт аккумуляторов.

Цена указана при сдаче старого акб ');
INSERT INTO avito_schema.tb_product (seller_id, category_id, image_id, customer_id, name, price, address, active, create_timestamp, deal_timestamp, description) VALUES (1, 6, 3, null, '"Тактическая" 2-диапазонная антенна для раций', 900, 'Москва, Арбатско-Покровская линия', true, '2022-07-25 13:18:40.823934', null, 'Плоская гибкая 2-диапазонная антенна для портативных радиостанций.

Диапазон частот : 136-520 МГц
Разъём : SMA-male
Длина : 46 см

В наличии 2 типа разъёмов - SMA-штекер (в этом объявлении) и SMA-гнездо.



Также в наличии различные радиостанции, аккумуляторы, зарядные устройства.

Большой ассортимент радиостанций и аксессуаров на нашем сайте (есть в профиле).');
INSERT INTO avito_schema.tb_product (seller_id, category_id, image_id, customer_id, name, price, address, active, create_timestamp, deal_timestamp, description) VALUES (4, 1, 4, null, 'Гараж, 18 м²', 950000, 'Москва, Полесский пр., 1с5', true, '2022-07-25 13:26:10.403019', null, 'Продам тёплый гараж 18кв.м . Имеются стелажи, столы. Имеется погреб так же со стеллажами, удобен для дачников хранить консервированные банки, картошку и т.д Гараж отапливаемый, территория охраняемая, дороги чистятся. Круглогодичный подъезд. освещение и видеонаблюдение по всей территории кооператива. Гаражный кооператив сносу не подлежит. Продаю за ненадобность, не спеша. Возможна рассрочка с первоначальным взносом 300000. Собственник .');
INSERT INTO avito_schema.tb_product (seller_id, category_id, image_id, customer_id, name, price, address, active, create_timestamp, deal_timestamp, description) VALUES (4, 8, 5, null, 'Ящерица Парусная', 18000, 'Москва, Калужско-Рижская линия', true, '2022-07-25 13:29:14.095641', null, 'Агамы Парусные - малыши, пока без определения пола. У вас есть все возможности вырастить такого ящера, как на фото (фото взрослого из интернета).');


