CREATE TABLE RoleTypesEntity (
    role_id BIGINT,
    roleName VARCHAR(255) NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    PRIMARY KEY(role_id)
);

CREATE TABLE UsersDetailsEntity (
    user_details_id BIGINT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    role_id BIGINT REFERENCES RoleTypesEntity(role_id),
    PRIMARY KEY(user_details_id)
);

CREATE TABLE HotelEntity (
    hotel_id BIGINT,
    hotelName VARCHAR(255) NOT NULL,
    hotelLocation VARCHAR(255) NOT NULL,
    hotelImage VARCHAR(255) NOT NULL,
    hotelDescription VARCHAR(500) NOT NULL,
    hotelAvailability BOOLEAN NOT NULL,
    hotelRating INT NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    PRIMARY KEY(hotel_id)
);
CREATE TABLE HotelRoomsEntity (
    hotel_rooms_id BIGINT,
    roomsType VARCHAR(255) NOT NULL,
    hotelRoomsPrice FLOAT NOT NULL,
    availableRoom BOOLEAN NOT NULL,
    roomCapacity INT NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    hotel_id BIGINT REFERENCES HotelEntity(hotel_id),
    PRIMARY KEY(hotel_rooms_id)
);
CREATE TABLE HotelBookingsEntity (
    hotel_bookings_id BIGINT,
    startDate TIMESTAMP NOT NULL,
    endDate TIMESTAMP NOT NULL,
    bookingStatus VARCHAR(255) NOT NULL,
    extras VARCHAR(255) NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    user_id BIGINT REFERENCES UsersDetailsEntity(user_details_id),
    room_id BIGINT REFERENCES HotelRoomsEntity(hotel_rooms_id),
    PRIMARY KEY(hotel_bookings_id)
);
CREATE TABLE CarEntity (
    car_id BIGINT,
    year INT NOT NULL,
    availability BOOLEAN NOT NULL,
    model VARCHAR(255) NOT NULL,
    miles FLOAT NOT NULL,
    carCompany VARCHAR(255) NOT NULL,
    carImage VARCHAR(500) NOT NULL,
    seatNumber INT NOT NULL,
    price FLOAT NOT NULL,
    trunkSize VARCHAR(255) NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    PRIMARY KEY(car_id)
);
CREATE TABLE CarBookingsEntity (
    car_booking_id BIGINT,
    pickUpDate TIMESTAMP NOT NULL,
    dropOffDate TIMESTAMP NOT NULL,
    bookingStatus VARCHAR(255) NOT NULL,
    extras VARCHAR(255) NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    car_id BIGINT REFERENCES CarEntity(car_id),
    user_id BIGINT REFERENCES UsersDetailsEntity(user_details_id),
    PRIMARY KEY(car_booking_id)
);

CREATE TABLE AttractionEntity (
    attraction_id BIGINT,
    attractionName VARCHAR(255) NOT NULL,
    attractionDescription VARCHAR(500),
    attractionPlace VARCHAR(255),
    availability BOOLEAN,
    attractionCompany VARCHAR(255),
    attractionPrice FLOAT,
    duration INT,
    attractionImage VARCHAR(500),
    attractionType VARCHAR(255),
    createdBy VARCHAR(255),
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255),
    updatedAt TIMESTAMP,
    PRIMARY KEY(attraction_id)
);
CREATE TABLE AttractionsBookingEntity (
    attraction_booking_id BIGINT,
    startDate TIMESTAMP NOT NULL,
    endDate TIMESTAMP NOT NULL,
    attractionTime TIMESTAMP NOT NULL,
    bookingStatus VARCHAR(255) NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP NOT NULL,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    attraction_id BIGINT REFERENCES AttractionEntity(attraction_id),
    user_id BIGINT REFERENCES UsersDetailsEntity(user_details_id),
    PRIMARY KEY(attraction_booking_id)
);

CREATE TABLE TripDetailsEntity (
    trip_details_id BIGINT,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    duration VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    availability BOOLEAN NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    hotel_id BIGINT REFERENCES HotelEntity(hotel_id),
    car_id BIGINT REFERENCES CarEntity(car_id),
    attraction_id BIGINT REFERENCES AttractionEntity(attraction_id),
    PRIMARY KEY(trip_details_id)
);
CREATE TABLE TripsBookingsEntity (
    trip_bookings_id BIGINT,
    startDate TIMESTAMP NOT NULL,
    endDate TIMESTAMP NOT NULL,
    tripTime TIMESTAMP NOT NULL,
    bookingStatus VARCHAR(255) NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    user_id BIGINT REFERENCES UsersDetailsEntity(user_details_id),
    trip_id BIGINT REFERENCES TripDetailsEntity(trip_details_id),
    PRIMARY KEY(trip_bookings_id)
);

CREATE TABLE TokenEntity (
    token_id BIGINT,
    token VARCHAR(255) UNIQUE NOT NULL,
    tokenType VARCHAR(255) NOT NULL,
    revoked BOOLEAN NOT NULL,
    expired BOOLEAN NOT NULL,
    createdBy VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP,
    updatedBy VARCHAR(255) NOT NULL,
    updatedAt TIMESTAMP,
    user_id BIGINT REFERENCES UsersDetailsEntity(user_details_id),
    PRIMARY KEY(token_id)
);