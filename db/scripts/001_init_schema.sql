CREATE TABLE IF NOT EXISTS rule(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS accident_type(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS accident(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    text VARCHAR NOT NULL,
    address VARCHAR NOT NULL,
    type_id INT REFERENCES accident_type(id)
);

CREATE TABLE IF NOT EXISTS accident_rule(
    rule_id INT REFERENCES rule(id),
    accident_id INT REFERENCES accident(id)
);