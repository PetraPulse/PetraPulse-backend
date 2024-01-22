CREATE TABLE AttractionEntity (
    attraction_id BIGSERIAL NOT NULL,
    attraction_name VARCHAR(255) NOT NULL,
    attraction_description VARCHAR(255) NOT NULL,
    attraction_place VARCHAR(255) NOT NULL,
    availability BOOLEAN NOT NULL,
    attraction_company VARCHAR(255) NOT NULL,
    attraction_price FLOAT NOT NULL,
    duration INTEGER NOT NULL,
    attraction_image VARCHAR(500) NOT NULL,
    PRIMARY KEY (attraction_id)
);

CREATE TABLE HotelEntity (
    hotel_id BIGSERIAL NOT NULL,
    hotel_name VARCHAR(255) NOT NULL,
    hotel_location VARCHAR(255) NOT NULL,
    hotel_image VARCHAR(500) NOT NULL,
    hotel_description VARCHAR(255) NOT NULL,
    hotel_availability BOOLEAN NOT NULL,
    hotel_rating INTEGER NOT NULL,
    PRIMARY KEY (hotel_id)
);

CREATE TABLE CarEntity (
    car_id BIGSERIAL NOT NULL,
    model_year INTEGER NOT NULL,
    availability BOOLEAN NOT NULL,
    model VARCHAR(255) NOT NULL,
    miles FLOAT NOT NULL,
    car_company VARCHAR(255) NOT NULL,
    car_Image VARCHAR(500) NOT NULL,
    seat_number INTEGER NOT NULL,
    car_price FLOAT NOT NULL,
    trunk_size VARCHAR(255) NOT NULL,
    PRIMARY KEY (car_id)
);

CREATE TABLE HotelRoomsEntity (
    hotelRooms_id BIGSERIAL NOT NULL,
    rooms_type VARCHAR(255) NOT NULL,
    beds_number INTEGER NOT NULL,
    hotel_rooms_price FLOAT NOT NULL,
    hotel_room_availability BOOLEAN NOT NULL,
    breakfast BOOLEAN NOT NULL,
    room_capacity INTEGER NOT NULL,
    PRIMARY KEY (hotelRooms_id)
);