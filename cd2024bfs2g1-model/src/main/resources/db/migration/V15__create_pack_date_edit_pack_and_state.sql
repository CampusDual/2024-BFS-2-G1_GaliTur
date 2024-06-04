ALTER TABLE PACK
DROP COLUMN pck_date_begin;

ALTER TABLE PACK
DROP COLUMN pck_date_end;

ALTER TABLE PACK
DROP COLUMN pcs_id;

ALTER TABLE PACK
DROP CONSTRAINT pack_pcs_id_fkey;

CREATE TABLE pack_date (
    pd_id SERIAL PRIMARY KEY NOT NULL,
    pd_date_begin TIMESTAMP NOT NULL,
    pd_date_end TIMESTAMP NOT NULL,
    pcs_id INT NOT NULL DEFAULT 1,
    pck_id INT NOT NULL,
    CONSTRAINT fk_pack_state
        FOREIGN KEY(pcs_id)
            REFERENCES pack_state(pcs_id),
    CONSTRAINT fk_pack
        FOREIGN KEY(pck_id)
            REFERENCES pack(pck_id)
);

ALTER TABLE pack_booking
DROP CONSTRAINT pck_id_fk;

ALTER TABLE pack_booking
DROP COLUMN pck_id;

ALTER TABLE pack_booking
ADD COLUMN pd_id INT;

ALTER TABLE pack_booking
ADD CONSTRAINT fk_pack_date
FOREIGN KEY (pd_id)
REFERENCES pack_date(pd_id);

ALTER TABLE
pack_date ADD COLUMN creation_date
TIMESTAMP DEFAULT CURRENT_TIMESTAMP;