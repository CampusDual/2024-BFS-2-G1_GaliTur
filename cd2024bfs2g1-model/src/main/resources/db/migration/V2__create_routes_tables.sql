CREATE TABLE route (
    route_id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(70) NOT NULL,
    description TEXT,
    estimated_duration NUMERIC,
    difficulty NUMERIC(2,1) NOT NULL CHECK (difficulty BETWEEN 1 AND 4)
);

CREATE TABLE landmark (
    landmark_id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(70),
    description TEXT,
    opening_time TIME,
    closing_time TIME,
    coordinates VARCHAR
);

CREATE TABLE route_landmark (
    route_landmark_id SERIAL PRIMARY KEY NOT NULL,
    route_id INTEGER NOT NULL REFERENCES route(route_id),
    landmark_id INTEGER NOT NULL REFERENCES landmark(landmark_id)
);

CREATE TABLE image (
    image_id SERIAL PRIMARY KEY NOT NULL,
    img_code VARCHAR(255) NOT NULL
);

CREATE TABLE image_route (
    image_route_id SERIAL PRIMARY KEY NOT NULL,
    route_id INTEGER NOT NULL REFERENCES route(route_id),
    image_id INTEGER NOT NULL REFERENCES image(image_id)
);

CREATE TABLE image_landmark (
    image_landmark_id SERIAL PRIMARY KEY NOT NULL,
    image_id INTEGER NOT NULL REFERENCES image(image_id),
    landmark_id INTEGER NOT NULL REFERENCES landmark(landmark_id)
);