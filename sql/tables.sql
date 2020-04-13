CREATE TABLE flight (
    flight_number TEXT PRIMARY_KEY,
    airline TEXT,
    dest_to TEXT,
    dest_from TEXT, 
    departure_time DATETIME,
    arrival_time DATETIME,
    price INTEGER, 
    luggage_price INTEGER, 
    duration INTEGER
);

CREATE TABLE seat (
    id TEXT PRIMARY_KEY,
    availability BOOLEAN,
    price INTEGER,
    flight_number TEXT,
    FOREIGN KEY(flight_number) REFERENCES flight(flight_number)
);

CREATE TABLE passenger (
    ssn INTEGER PRIMARY_KEY,
    name TEXT,
    gender TEXT,
    email TEXT,
    phone_number INTEGER
);

CREATE TABLE group_booking (
    group_number TEXT PRIMARY_KEY
);

CREATE TABLE booking (
    booking_number TEXT PRIMARY_KEY,
    group_number TEXT,
    luggage BOOLEAN,
    paid BOOLEAN,
    refunded BOOLEAN,
    flight_number STRING,
    passenger_ssn INTEGER,
    seat_id INTEGER,
    FOREIGN KEY(group_number) REFERENCES group_booking(group_number),
    FOREIGN KEY(flight_number) REFERENCES flight(flight_number),
    FOREIGN KEY(passenger_ssn) REFERENCES passenger(ssn),
    FOREIGN KEY(seat_id) REFERENCES seat(id)
);

