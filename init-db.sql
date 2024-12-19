CREATE SCHEMA "electrikar";
SET SCHEMA 'electrikar';
CREATE TABLE "Car"(
    reg_num VARCHAR(50) UNIQUE NOT NULL PRIMARY KEY,
    color VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    make VARCHAR(50) NOT NULL,
    type INT NOT NULL,
    price INT NOT NULL,
    image varchar(1024) NOT NULL
);

CREATE TABLE "User"(
    id SERIAL PRIMARY KEY,
    legal_name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    cpr VARCHAR(50) UNIQUE NOT NULL,
    phone VARCHAR(50) NOT NULL,
    is_admin BOOLEAN NOT NULL,
    is_banned BOOLEAN NOT NULL
);

CREATE TABLE "Rental"(
    id SERIAL PRIMARY KEY,
    car_reg varchar(50) NOT NULL,
    customer_id INT NOT NULL,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL CHECK (end_date > start_date),
    drop_date TIMESTAMP NOT NULL CHECK (drop_date > start_date),
    status INT NOT NULL,
    customer_comment VARCHAR(50),
    organizer_comment VARCHAR(50),
    FOREIGN KEY (car_reg) REFERENCES "Car"(reg_num) ON UPDATE CASCADE,
    FOREIGN KEY (customer_id) REFERENCES "User"(id)
);

