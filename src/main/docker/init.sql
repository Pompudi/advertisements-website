CREATE SCHEMA avito_schema
    AUTHORIZATION postgres;
CREATE TABLE IF NOT EXISTS avito_schema.tb_category
(
    category_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(128) COLLATE pg_catalog."default" NOT NULL,
    create_timestamp timestamp without time zone NOT NULL,
    CONSTRAINT category_pkey PRIMARY KEY (category_id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS avito_schema.tb_category
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS avito_schema.tb_image
(
    image_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    image_url character varying(512) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT image_pkey PRIMARY KEY (image_id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS avito_schema.tb_image
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS avito_schema.tb_user
(
    user_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    login character varying(64) COLLATE pg_catalog."default" NOT NULL,
    password character varying(128) COLLATE pg_catalog."default" NOT NULL,
    first_name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(64) COLLATE pg_catalog."default" NOT NULL,
    phone_number character varying(17) COLLATE pg_catalog."default" NOT NULL,
    create_timestamp timestamp without time zone NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (user_id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS avito_schema.tb_user
    OWNER to postgres;
CREATE TABLE IF NOT EXISTS avito_schema.tb_product
(
    product_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    seller_id bigint NOT NULL,
    category_id bigint NOT NULL,
    image_id bigint NOT NULL,
    customer_id bigint,
    name character varying(128) COLLATE pg_catalog."default" NOT NULL,
    price numeric,
    address character varying(512) COLLATE pg_catalog."default",
    active boolean NOT NULL,
    create_timestamp timestamp without time zone NOT NULL,
    deal_timestamp timestamp without time zone,
    description character varying(3500) COLLATE pg_catalog."default",
    CONSTRAINT product_pkey PRIMARY KEY (product_id),
    CONSTRAINT category_id_fk FOREIGN KEY (category_id)
        REFERENCES avito_schema.tb_category (category_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT customer_id_fk FOREIGN KEY (customer_id)
        REFERENCES avito_schema.tb_user (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT image_id_fk FOREIGN KEY (image_id)
        REFERENCES avito_schema.tb_image (image_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT seller_id_fk FOREIGN KEY (seller_id)
        REFERENCES avito_schema.tb_user (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS avito_schema.tb_product
    OWNER to postgres;


INSERT INTO avito_schema.tb_category (name, create_timestamp) VALUES ('Недвижимость', '2022-07-13 10:09:28.000000');
INSERT INTO avito_schema.tb_category (name, create_timestamp) VALUES ('Транспорт', '2022-07-13 10:09:52.000000');
INSERT INTO avito_schema.tb_category (name, create_timestamp) VALUES ('Работа', '2022-07-13 10:10:24.000000');
INSERT INTO avito_schema.tb_category (name, create_timestamp) VALUES ('Для дома и дачи', '2022-07-13 10:10:44.000000');
INSERT INTO avito_schema.tb_category (name, create_timestamp) VALUES ('Запчасти и аксессуары', '2022-07-13 10:11:04.000000');
INSERT INTO avito_schema.tb_category (name, create_timestamp) VALUES ('Электроника', '2022-07-13 10:11:40.000000');
INSERT INTO avito_schema.tb_category (name, create_timestamp) VALUES ('Хобби и отдых', '2022-07-13 10:12:01.000000');
INSERT INTO avito_schema.tb_category (name, create_timestamp) VALUES ('Животные', '2022-07-13 10:12:09.000000');
INSERT INTO avito_schema.tb_category (name, create_timestamp) VALUES ('Готовый бизнес и оборудование', '2022-07-13 10:12:27.000000');