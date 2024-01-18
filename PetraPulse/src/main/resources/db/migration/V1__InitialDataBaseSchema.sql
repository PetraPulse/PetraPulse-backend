CREATE TABLE UsersDetails (
    user_id BIGSERIAL NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address VARCHAR(500) NOT NULL,
    phone_number INTEGER NOT NULL,
    role_id INTEGER constraint fk_role_id references Role-Types NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE AdminsDetails (
    admin_id BIGSERIAL NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number INTEGER NOT NULL,
    PRIMARY KEY (admin_id)
);

CREATE TABLE Role-Types(
  role_id BIGSERIAL NOT NULL,
  role_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TABLE BookedHotel(
   booked_hotel_id BIGSERIAL NOT NULL,
   name VARCHAR(255),
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
    FOREIGN KEY (user_id) REFERENCES OurUsers(user_id)
);


CREATE TABLE BookedCar(
   booked_car_id BIGSERIAL NOT NULL,
   name VARCHAR(255),
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
    FOREIGN KEY (user_id) REFERENCES OurUsers(user_id)
);


CREATE TABLE BookedAttraction(
   booked_attraction_id BIGSERIAL NOT NULL,
   name VARCHAR(255),
);
CREATE TABLE AttractionsBookings (
    attraction_booking_id BIGSERIAL NOT NULL,
    attraction_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    price INTEGER NOT NULL,
    PRIMARY KEY (attraction_booking_id),
    FOREIGN KEY (attraction_id) REFERENCES BookedAttraction(booked_attraction_id),
    FOREIGN KEY (user_id) REFERENCES OurUsers(user_id)
);



CREATE TABLE TripDetails (
    trip_id BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    duration INTEGER NOT NULL,
    price INTEGER NOT NULL,
    rating INTEGER NOT NULL,
    PRIMARY KEY (trip_id),
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
    FOREIGN KEY (trip_id) REFERENCES Trips(trip_id),
    FOREIGN KEY (user_id) REFERENCES OurUsers(user_id)
);




