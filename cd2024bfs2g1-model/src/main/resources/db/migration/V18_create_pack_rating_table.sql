CREATE TABLE pack_rating
(
    pcr_id           SERIAL PRIMARY KEY NOT NULL,
    pck_id           int4               not NULL,
    client_id        int4               not NULL,
    pcr_stars        int2               not NULL CHECK ( pcr_stars BETWEEN 1 AND 5),
    pcr_comment      varchar(255)
);
