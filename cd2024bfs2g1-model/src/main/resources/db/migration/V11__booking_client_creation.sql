CREATE TABLE pack_booking (
    pbk_booking_id SERIAL PRIMARY KEY NOT NULL,
    pck_id int4 not NULL,
    client_id int4 not NULL,
    pbk_booking_date timestamp not NULL DEFAULT now(),
    pbk_down_date timestamp NULL
);

ALTER TABLE pack_booking ADD CONSTRAINT pck_id_fk FOREIGN KEY (pck_id) REFERENCES pack(pck_id);
ALTER TABLE pack_booking ADD CONSTRAINT client_id_fk FOREIGN KEY (client_id) REFERENCES client(client_id);