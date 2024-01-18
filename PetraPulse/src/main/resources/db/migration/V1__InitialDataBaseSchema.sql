CREATE TABLE RoleTypes (
    role_id BIGSERIAL NOT NULL,
    role_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (role_id)
);

CREATE TABLE UsersDetails (
    user_id BIGSERIAL NOT NULL,
    role_id BIGSERIAL NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(500) NOT NULL,
    phone_number INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES RoleTypes(role_id),
    PRIMARY KEY(user_id)
);

CREATE TABLE BookedHotel (
    booked_hotel_id BIGSERIAL NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (booked_hotel_id)
);

CREATE TABLE HotelBookings (
    hotel_booking_id BIGSERIAL NOT NULL,
    hotel_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    number_of_guests INTEGER NOT NULL,
    price INTEGER NOT NULL,
    PRIMARY KEY (hotel_booking_id),
    FOREIGN KEY (hotel_id) REFERENCES BookedHotel(booked_hotel_id),
    FOREIGN KEY (user_id) REFERENCES UsersDetails(user_id)
);

CREATE TABLE BookedCar (
    booked_car_id BIGSERIAL NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (booked_car_id)
);

CREATE TABLE CarBookings (
    car_booking_id BIGSERIAL NOT NULL,
    car_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    pick_up_date DATE NOT NULL,
    drop_off_date DATE NOT NULL,
    price INTEGER NOT NULL,
    PRIMARY KEY (car_booking_id),
    FOREIGN KEY (car_id) REFERENCES BookedCar(booked_car_id),
    FOREIGN KEY (user_id) REFERENCES UsersDetails(user_id)
);

CREATE TABLE BookedAttraction (
    booked_attraction_id BIGSERIAL NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (booked_attraction_id)
);

CREATE TABLE AttractionsBookings (
    attraction_booking_id BIGSERIAL NOT NULL,
    attraction_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    price INTEGER NOT NULL,
    PRIMARY KEY (attraction_booking_id),
    FOREIGN KEY (attraction_id) REFERENCES BookedAttraction(booked_attraction_id),
    FOREIGN KEY (user_id) REFERENCES UsersDetails(user_id)
);
