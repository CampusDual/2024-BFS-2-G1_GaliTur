ALTER TABLE pack_booking
    ADD COLUMN pbk_stars int2 NULL CHECK ( pbk_stars BETWEEN 1 AND 5);
ALTER TABLE pack_booking
    ADD COLUMN pbk_comment varchar(255);
ALTER TABLE pack_booking
    ADD COLUMN pbk_rating_date timestamp NULL;
