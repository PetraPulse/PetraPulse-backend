CREATE TABLE RoleTypes(
  role_id BIGSERIAL NOT NULL,
  role_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TABLE UsersDetails (
    user_id BIGSERIAL NOT NULL,
    role_id BIGSERIAL NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(500) NOT NULL,
    phone_number INTEGER NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (role_id) REFERENCES RoleTypes(role_id)
);

CREATE TABLE TokenEntity (
    token_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    token VARCHAR(255) NOT NULL,
    token_type VARCHAR(50) DEFAULT 'BEARER',
    revoked boolean NOT NULL,
    expired boolean NOT NULL,
    FOREIGN KEY (user_id) REFERENCES UsersDetails(user_id),
    PRIMARY KEY (token_id)
);

CREATE TABLE BookedHotel(
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


CREATE TABLE BookedCar(
   booked_car_id BIGSERIAL NOT NULL,
   name VARCHAR(255),
   PRIMARY KEY (booked_car_id)
);
CREATE TABLE CarBookings (
    car_booking_id BIGSERIAL NOT NULL,
    car_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    pick_up_date DATE NOT NULL,
    drop_of_date DATE NOT NULL,
    price INTEGER NOT NULL,
    PRIMARY KEY (car_booking_id),
    FOREIGN KEY (car_id) REFERENCES BookedCar(booked_car_id),
    FOREIGN KEY (user_id) REFERENCES UsersDetails(user_id)
);


CREATE TABLE BookedAttraction(
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



CREATE TABLE TripDetails (
    trip_id BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    duration INTEGER NOT NULL,
    price INTEGER NOT NULL,
    rating INTEGER NOT NULL,
    PRIMARY KEY (trip_id)
);
CREATE TABLE TripBookings (
    trip_booking_id BIGSERIAL NOT NULL,
    trip_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    number_of_guests INTEGER NOT NULL,
    selected_date Date NOT NULL,
    availability BOOLEAN NOT NULL,
    cancellation BOOLEAN NOT NULL,
    PRIMARY KEY (trip_booking_id),
    FOREIGN KEY (trip_id) REFERENCES TripDetails(trip_id),
    FOREIGN KEY (user_id) REFERENCES UsersDetails(user_id)
);




