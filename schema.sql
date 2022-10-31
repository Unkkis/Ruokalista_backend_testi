SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS food_item;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS recipe_ingredients;
DROP TABLE IF EXISTS application_user;
SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE category(
id BIGINT NOT NULL AUTO_INCREMENT, 
name VARCHAR(100) NOT NULL,
PRIMARY KEY (id)
);
INSERT INTO category (name)
VALUES (''), ('Grilli'), ('Keitto'), ('Uunivuoka'), ('Pataruoka'), ('Kastike lisäkkeellä');

CREATE TABLE food_item(
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR (100) NOT NULL,
PRIMARY KEY (id)
);
INSERT INTO food_item(name)
VALUES ('Peruna'), ('Jauheliha'), ('Spaghetti'), ('Tomaattimurska'), ('Tomaattipyre'), ('Jauhot'),
('Lihaliemikuutio'), ('Suola'), ('Pippuri'), ('Voi'), ('Punaviini');

CREATE TABLE recipe (
id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(100) NOT NULL,
cooking_time INT,
preparation VARCHAR(5000) NOT NULL,
link_to_webpage VARCHAR(500),
categoryid BIGINT,
PRIMARY KEY (id),
FOREIGN KEY (categoryid) REFERENCES category(id)
);
INSERT INTO recipe (name, cooking_time, preparation, link_to_webpage, categoryid)
VALUES
('Jauhelihakastiketta ja perunoita', 40, 'Laita perunat kiehumaan. Paista jauheliha ja mausta se. Lisää voi sekä jauhot ja ruskista. Lisää vesi ja sekoita tasaiseksi. Mausta pippurilla ja jauhetulla lihaliemikuutiolla. Anna tekeytyä ja syö.', 'www.jussin-reseptitaivas.fi/jauhelihakastike', 6),
('Tomaattikastiketta ja spaghettia', 60, 'Paista jauhelija ja mausta. Lisää tomaattimurska sekä punaviini ja anna kiehua vähän aikaa. Lisää tomaattipyre ja sekoita. Mausta suolalla ja pippurilla. Anna tekeytyä vähintään 30-40min (jos aikaa on niin vaikka muutaman tunnin). Keitä spaghetti pakkauksen ohjeen mukaan. Yhdistä spaghetti kastikkeeseen ja nauti', 'www.italialaisetherkut.net/bolognesentapainen_juttu', 6);

CREATE TABLE recipe_ingredients(
recipe_id BIGINT NOT NULL,
food_item_id BIGINT NOT NULL,
PRIMARY KEY (recipe_id, food_item_id),
UNIQUE INDEX (recipe_id, food_item_id),
FOREIGN KEY (recipe_id) REFERENCES recipe(id),
FOREIGN KEY (food_item_id) REFERENCES food_item(id)
);
INSERT INTO recipe_ingredients (recipe_id, food_item_id)
VALUES 
(1, 1), (1, 2), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10),
(2, 2), (2, 3), (2, 4), (2, 5), (2, 8), (2, 9), (2, 11);


CREATE TABLE application_user
(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(100) NOT NULL,
lastname VARCHAR(100) NOT NULL,
role VARCHAR(100) NOT NULL,
username VARCHAR(250) NOT NULL,
password_hash VARCHAR(250) NOT NULL);
INSERT INTO application_user (firstname, lastname, username, password_hash,
role)
VALUES ('Jussi', 'Kosonen', 'user',
'$2a$10$amK1FwmFcC5BctacyKiSW.3ZG5JTD3Jky0Usvjuli3m3EPVToasV6', 'USER'),
('Jussi', 'Kosonen', 'admin',
'$2a$10$FndAP653l6sY1f/UuWv/LeESJ1thxEEXx7Or/P1iR3aKZZiIBAtGC', 'ADMIN');

SELECT * FROM category;
SELECT * FROM food_item;
SELECT * FROM recipe_ingredients;
SELECT * FROM recipe;
SELECT * FROM application_user;