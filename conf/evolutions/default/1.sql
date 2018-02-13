# --- !Ups

CREATE TABLE RESOURCES (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(40) NOT NULL,
  "symbol" VARCHAR(5) NOT NULL
);

INSERT INTO RESOURCES("name", "symbol") values ('Hydrogenn', 'H');
INSERT INTO RESOURCES("name", "symbol") values ('Helium', 'He');

# --- !Downs

DROP TABLE RESOURCES;