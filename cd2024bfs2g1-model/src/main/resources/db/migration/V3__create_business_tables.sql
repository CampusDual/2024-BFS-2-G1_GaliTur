CREATE TABLE bsn_business (
	bsn_id serial NOT NULL,
	bsn_owner_id serial NOT NULL,
	bsn_name varchar(100) NOT NULL,
	bsn_description text NULL,
	bsn_cif varchar(100) NOT NULL,
	bsn_address varchar(250) NOT NULL,
	bsn_phone varchar(15) NOT NULL,
	bsn_email varchar(100) NOT NULL,
	bsn_creation_date  timestamp NULL DEFAULT now(),
	bsn_down_date timestamp NULL,
	bsn_photos varchar NOT NULL,
	bsn_website varchar(50) NULL,
	bsn_schedule varchar(255) NOT NULL,
	CONSTRAINT bsn_business_pk PRIMARY KEY (bsn_id),
	CONSTRAINT bsn_business_un UNIQUE (bsn_cif)
);

CREATE TABLE bsn_guide (
    gui_id serial NOT NULL,
	bsn_id serial NOT NULL,
	gui_language varchar(200) NOT NULL,
	gui_zone varchar(200) NOT NULL,
	gui_city varchar(200) NULL,
	CONSTRAINT bsn_guide_pk PRIMARY KEY (gui_id),
);

CREATE TABLE bsn_restaurant (
    rest_id serial NOT NULL,
	bsn_id serial NOT NULL,
	rest_menu varchar NOT NULL,
	CONSTRAINT bsn_restaurant_pk PRIMARY KEY (rest_id),
);

CREATE TABLE bsn_hotel (
    htl_id serial NOT NULL,
	bsn_id serial NOT NULL,
	CONSTRAINT bsn_hotel_pk PRIMARY KEY (htl_id),
);

CREATE TABLE bsn_hotel_rooms (
    htl_id serial NOT NULL,
	rm_id serial NOT NULL,
	rm_type varchar(50) NOT NULL,
	rm_cost double NOT NULL,
	CONSTRAINT bsn_hotel_rooms_pk PRIMARY KEY (rm_id),
);

CREATE TABLE bsn_hotel_services (
    htl_id serial NOT NULL,
	srv_id serial NOT NULL,
	srv_type varchar(50) NOT NULL,
	srv_cost double NOT NULL,
	CONSTRAINT bsn_hotel_services_pk PRIMARY KEY (srv_id),
);

--ALTER TABLE bsn_business ADD CONSTRAINT bsn_business_fk FOREIGN KEY (bsn_owner_id) REFERENCES ***(**);


ALTER TABLE bsn_guide ADD CONSTRAINT bsn_guide_fk FOREIGN KEY (bsn_id) REFERENCES bsn_business(bsn_id);
ALTER TABLE bsn_restaurant ADD CONSTRAINT bsn_restaurant_fk FOREIGN KEY (bsn_id) REFERENCES bsn_business(bsn_id);
ALTER TABLE bsn_hotel ADD CONSTRAINT bsn_hotel_fk FOREIGN KEY (bsn_id) REFERENCES bsn_business(bsn_id);
ALTER TABLE bsn_hotel_rooms ADD CONSTRAINT bsn_hotel_rooms_fk FOREIGN KEY (htl_id) REFERENCES bsn_hotel(htl_id);
ALTER TABLE bsn_hotel_services ADD CONSTRAINT bsn_hotel_services_fk FOREIGN KEY (htl_id) REFERENCES bsn_hotel(htl_id);






