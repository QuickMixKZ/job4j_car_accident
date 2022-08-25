INSERT INTO rule(name) VALUES ('Статья. 1');
INSERT INTO rule(name) VALUES ('Статья. 2');
INSERT INTO rule(name) VALUES ('Статья. 3');

INSERT INTO accident_type(name) VALUES('Одна машина');
INSERT INTO accident_type(name) VALUES('Две машины');
INSERT INTO accident_type(name) VALUES('Машина и человек');
INSERT INTO accident_type(name) VALUES('Машина и велосипед');

INSERT INTO accident(name, text, address, type_id) VALUES('ДТП', 'Небольшое ДТП', 'Центральный проспект', 1);
INSERT INTO accident_rule(rule_id, accident_id) VALUES(1, 1), (2, 1)