CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR (128),
    last_name VARCHAR (128),
    money INTEGER CHECK (money > 0)
);

CREATE TABLE houses (
    house_id SERIAL PRIMARY KEY,
    address VARCHAR (128),
    owner_id INTEGER,
    cost INTEGER
);