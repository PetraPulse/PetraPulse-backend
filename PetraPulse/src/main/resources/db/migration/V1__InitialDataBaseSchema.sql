CREATE TABLE roletypesentity (
    role_id BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE
);

CREATE TABLE appusersentity (
    user_details_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    confirm_password VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    image VARCHAR(500),
    date_of_birth DATE NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roletypesentity (role_id)
);

CREATE TABLE attraction_entity (
    attraction_id BIGSERIAL PRIMARY KEY,
    attraction_name VARCHAR(255) NOT NULL,
    attraction_description VARCHAR(500) NOT NULL,
    attraction_place VARCHAR(255) NOT NULL,
    availability BOOLEAN NOT NULL,
    attraction_company VARCHAR(255) NOT NULL,
    attraction_price FLOAT NOT NULL,
    duration INT NOT NULL,
    attraction_image VARCHAR(500) NOT NULL,
    attraction_type VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE
);
CREATE TABLE attractions_booking_entity (
    attraction_booking_id BIGSERIAL PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    attraction_time TIME NOT NULL,
    booking_status VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE,
    attraction_id BIGINT,
    user_details_id BIGINT,
    FOREIGN KEY (attraction_id) REFERENCES attraction_entity (attraction_id),
    FOREIGN KEY (user_details_id) REFERENCES appusersentity (user_details_id)
);

CREATE TABLE car_entity (
    car_id BIGSERIAL PRIMARY KEY,
    model_year INT NOT NULL,
    availability BOOLEAN NOT NULL,
    model VARCHAR(255) NOT NULL,
    miles FLOAT NOT NULL,
    car_company VARCHAR(255) NOT NULL,
    car_image VARCHAR(500) NOT NULL,
    seat_number INT NOT NULL,
    price FLOAT NOT NULL,
    trunk_size VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255) ,
    updated_at DATE
);
CREATE TABLE car_bookings_entity (
    booking_id BIGSERIAL PRIMARY KEY,
    pick_up_date DATE NOT NULL,
    drop_off_date DATE NOT NULL,
    booking_status VARCHAR(255) NOT NULL,
    extras VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE,
    car_id BIGINT,
    user_details_id BIGINT,
    FOREIGN KEY (car_id) REFERENCES car_entity (car_id),
    FOREIGN KEY (user_details_id) REFERENCES appusersentity (user_details_id)
);

CREATE TABLE hotel_entity (
    hotel_id BIGSERIAL PRIMARY KEY,
    hotel_name VARCHAR(255) NOT NULL,
    hotel_location VARCHAR(255) NOT NULL,
    hotel_image VARCHAR(500) NOT NULL,
    hotel_description VARCHAR(500) NOT NULL,
    hotel_availability BOOLEAN NOT NULL,
    hotel_rating INT NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE
);
CREATE TABLE hotel_rooms_entity (
    room_id BIGSERIAL PRIMARY KEY,
    rooms_type VARCHAR(255) NOT NULL,
    hotel_rooms_price FLOAT NOT NULL,
    available_room BOOLEAN NOT NULL,
    room_capacity INT NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE,
    hotel_id BIGINT,
    FOREIGN KEY (hotel_id) REFERENCES hotel_entity (hotel_id)
);
CREATE TABLE hotel_bookings_entity (
    booking_id BIGSERIAL PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    booking_status VARCHAR(255) NOT NULL,
    extras VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE,
    user_details_id BIGINT,
    room_id BIGINT,
    FOREIGN KEY (user_details_id) REFERENCES appusersentity (user_details_id),
    FOREIGN KEY (room_id) REFERENCES hotel_rooms_entity (room_id)
);


CREATE TABLE trip_details_entity (
    trip_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    duration VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    availability BOOLEAN NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE,
    hotel_id BIGINT,
    car_id BIGINT,
    attraction_id BIGINT,
    FOREIGN KEY (hotel_id) REFERENCES hotel_entity (hotel_id),
    FOREIGN KEY (car_id) REFERENCES car_entity (car_id),
    FOREIGN KEY (attraction_id) REFERENCES attraction_entity (attraction_id)
);
CREATE TABLE trips_bookings_entity (
    booking_id BIGSERIAL PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    trip_time TIME NOT NULL,
    booking_status VARCHAR(255) NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE,
    user_details_id BIGINT,
    trip_id BIGINT,
    FOREIGN KEY (user_details_id) REFERENCES appusersentity (user_details_id),
    FOREIGN KEY (trip_id) REFERENCES trip_details_entity (trip_id)
);

CREATE TABLE token_entity (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(255) NOT NULL,
    revoked BOOLEAN NOT NULL,
    expired BOOLEAN NOT NULL,
    created_by VARCHAR(255),
    created_at DATE,
    updated_by VARCHAR(255),
    updated_at DATE,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES appusersentity (user_details_id)
);

CREATE SEQUENCE hibernate_sequence START 1;
