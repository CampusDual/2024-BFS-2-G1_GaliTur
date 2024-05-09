CREATE TABLE pack
(
    pck_id           serial      NOT NULL,
    pck_name         varchar(50) NOT NULL,
    pck_description  text        NOT NULL,
    pck_date_begin   timestamp   NOT NULL,
    pck_date_end     timestamp   NOT NULL,
    pcs_state        integer     NOT NULL,
    pck_price        decimal(10, 2),
    pck_participants integer,
    gui_c_id         integer     NOT NULL,
    CONSTRAINT pack_pk PRIMARY KEY (pck_id)
);
ALTER TABLE pack
    ADD CONSTRAINT pack_pcs_state_fk FOREIGN KEY (pcs_state) REFERENCES pack_state (pcs_state);
ALTER TABLE pack
    ADD CONSTRAINT pack_gui_c_id_fk FOREIGN KEY (gui_c_id) REFERENCES gui_cities (gui_c_id);

CREATE TABLE pack_state
(
    pcs_id    serial NOT NULL,
    pcs_state varchar(50),
    CONSTRAINT pack_state PRIMARY KEY (pcs_id)
);
INSERT INTO pack_state VALUES ('active');
INSERT INTO pack_state VALUES ('reserved');

CREATE TABLE image_pack
(
    imp_id serial,
    pck_id integer NOT NULL,
    img_id integer NOT NULL,
    CONSTRAINT image_pack_pk PRIMARY KEY (imp_id)
);
ALTER TABLE image_pack
    ADD CONSTRAINT image_pack_pck_id_fk FOREIGN KEY (pck_id) REFERENCES pack (pck_id);
ALTER TABLE image_pack
    ADD CONSTRAINT image_pack_img_id_fk FOREIGN KEY (img_id) REFERENCES image (image_id);

