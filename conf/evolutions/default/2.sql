# --- !Ups

CREATE TABLE COLLECTORS (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(40) NOT NULL,
  "min_capacity" INT,
  "max_capacity" INT,
  "created_ts" TIMESTAMP NOT NULL
);

INSERT INTO COLLECTORS("id", "name", "min_capacity", "max_capacity", "created_ts") VALUES (1, 'Peter', 101, 500, (TIMESTAMP '2018-01-04 11:11:52.69'));
INSERT INTO COLLECTORS("id", "name", "min_capacity", "max_capacity", "created_ts") VALUES (2, 'Mike', 500, null, (TIMESTAMP '2018-02-02 8:54:52.69'));
INSERT INTO COLLECTORS("id", "name", "min_capacity", "max_capacity", "created_ts") VALUES (3, 'Travis', 50, 200, (TIMESTAMP '2018-02-01 14:47:52.69'));
INSERT INTO COLLECTORS("id", "name", "min_capacity", "max_capacity", "created_ts") VALUES (4, 'Bob', null, 50, (TIMESTAMP '2018-01-18 12:47:52.69'));
INSERT INTO COLLECTORS("id", "name", "min_capacity", "max_capacity", "created_ts") VALUES (5, 'Luke', 0, 100, (TIMESTAMP '2018-01-02 18:47:52.69'));


CREATE TABLE RESOURCE_TYPES (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(40) NOT NULL
);

INSERT INTO RESOURCE_TYPES("id", "name") VALUES (1, 'wastepaper');
INSERT INTO RESOURCE_TYPES("id", "name") VALUES (2, 'plastics');
INSERT INTO RESOURCE_TYPES("id", "name") VALUES (3, 'glass');
INSERT INTO RESOURCE_TYPES("id", "name") VALUES (4, 'steel');


CREATE TABLE COLLECTOR_PREFERENCES (
  "collector_id" INT NOT NULL,
  "resource_type_id" INT NOT NULL,
  "priority" INT NOT NULL,
  PRIMARY KEY("collector_id", "resource_type_id"),
  FOREIGN KEY("collector_id") REFERENCES COLLECTORS("id"),
  FOREIGN KEY("resource_type_id") REFERENCES RESOURCE_TYPES("id")
);

INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (1, 1, 1);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (1, 2, 2);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (1, 3, 3);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (1, 4, 4);

INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (2, 1, 2);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (2, 2, 2);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (2, 3, 3);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (2, 4, 1);

INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (3, 2, 1);

INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (4, 1, 1);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (4, 2, 2);

INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (5, 1, 2);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (5, 2, 4);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (5, 3, 1);
INSERT INTO COLLECTOR_PREFERENCES("collector_id", "resource_type_id", "priority") VALUES (5, 4, 3);


CREATE TABLE OWNERS (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(40) NOT NULL,
  "created_ts" TIMESTAMP NOT NULL
);

INSERT INTO OWNERS("id", "name", "created_ts") VALUES (1, 'Alice', (TIMESTAMP '2018-01-01 12:47:52.69'));
INSERT INTO OWNERS("id", "name", "created_ts") VALUES (2, 'John', (TIMESTAMP '2018-01-12 12:47:52.69'));


CREATE TABLE ADDRESSES (
  "id" SERIAL PRIMARY KEY,
  "city_name" VARCHAR(40) NOT NULL
);

INSERT INTO ADDRESSES("id", "city_name") VALUES (1, 'Warsaw');
INSERT INTO ADDRESSES("id", "city_name") VALUES (2, 'Lodz');
INSERT INTO ADDRESSES("id", "city_name") VALUES (3, 'Gdansk');
INSERT INTO ADDRESSES("id", "city_name") VALUES (4, 'Cracow');


TRUNCATE TABLE RESOURCES;

ALTER TABLE RESOURCES DROP "name";
ALTER TABLE RESOURCES DROP "symbol";

ALTER TABLE RESOURCES ADD "resource_type_id" INT NOT NULL;
ALTER TABLE RESOURCES ADD FOREIGN KEY ("resource_type_id") REFERENCES RESOURCE_TYPES("id");

ALTER TABLE RESOURCES ADD "price_per_kg" VARCHAR(10) NOT NULL;
ALTER TABLE RESOURCES ADD "weight" INT NOT NULL;

ALTER TABLE RESOURCES ADD "address_id" INT NOT NULL;
ALTER TABLE RESOURCES ADD FOREIGN KEY ("address_id") REFERENCES ADDRESSES("id");

ALTER TABLE RESOURCES ADD "owner_id" INT NOT NULL;
ALTER TABLE RESOURCES ADD FOREIGN KEY ("owner_id") REFERENCES OWNERS("id");

ALTER TABLE RESOURCES ADD "create_ts" TIMESTAMP NOT NULL;
ALTER TABLE RESOURCES ADD "collected_ts" TIMESTAMP;
ALTER TABLE RESOURCES ADD "deleted_ts" TIMESTAMP;

CREATE INDEX "resource_type_idx" ON RESOURCES("resource_type_id");
CREATE INDEX "address_idx" ON RESOURCES("resource_type_id");
CREATE INDEX "owner_idx" ON RESOURCES("resource_type_id");

INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (1, 1, '0.20', 1, 1, 1, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (2, 3, '2.00', 10, 2, 1, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (3, 2, '5.20', 1100, 1, 1, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (4, 4, '0.05', 50, 2, 1, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (5, 2, '0.01', 1100, 1, 1, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);

INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (6, 2, '1.20', 30, 1, 2, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (7, 2, '5.40', 20, 3, 2, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (8, 2, '10.00', 4, 4, 2, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (9, 2, '0.20', 23, 3, 2, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (10, 1, '2.00', 50, 3, 2, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (11, 3, '2.00', 50, 3, 2, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (12, 4, '0.03', 120, 3, 2, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);
INSERT INTO RESOURCES("id", "resource_type_id", "price_per_kg", "weight", "address_id", "owner_id", "create_ts", "collected_ts", "deleted_ts")
VALUES (13, 2, '3.40', 200, 3, 2, (TIMESTAMP '2018-01-02 12:47:52.69'), null, null);

# --- !Downs

DROP TABLE COLLECTOR_PREFERENCES;
DROP TABLE COLLECTORS;
DROP TABLE RESOURCES;
DROP TABLE RESOURCE_TYPES;
DROP TABLE ADDRESSES;
DROP TABLE OWNERS;
