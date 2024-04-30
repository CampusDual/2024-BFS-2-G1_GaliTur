CREATE TABLE bsn_business (
	bsn_id serial NOT NULL,
	merchant_id int4 NOT NULL,
	bsn_name varchar(100) NOT NULL,
	bsn_type varchar(50) NOT NULL,
	bsn_description text NULL,
	bsn_cif varchar(100) NOT NULL,
	bsn_address varchar(250) NOT NULL,
	bsn_phone varchar(15) NOT NULL,
	bsn_email varchar(100) NOT NULL,
	bsn_creation_date  timestamp NULL DEFAULT now(),
	bsn_down_date timestamp NULL,
	bsn_photos varchar NOT NULL,
	bsn_website varchar(50) NULL,
	bsn_schedule text NOT NULL,
	CONSTRAINT bsn_business_pk PRIMARY KEY (bsn_id),
	CONSTRAINT bsn_business_un UNIQUE (bsn_cif)
);

CREATE TABLE bsn_guide_agency (
    gui_id serial NOT NULL,
	bsn_id int4 NOT NULL,
	gui_language varchar(200) NOT NULL,
	gui_zone varchar(200) NOT NULL,
	gui_city varchar(200) NULL,
	CONSTRAINT bsn_guide_pk PRIMARY KEY (gui_id)
);

CREATE TABLE bsn_restaurant (
    rest_id serial NOT NULL,
	bsn_id int4 NOT NULL,
	rest_menu varchar NOT NULL,
	CONSTRAINT bsn_restaurant_pk PRIMARY KEY (rest_id)
);

CREATE TABLE bsn_hotel (
    htl_id serial NOT NULL,
	bsn_id int4 NOT NULL,
	CONSTRAINT bsn_hotel_pk PRIMARY KEY (htl_id)
);

CREATE TABLE bsn_hotel_rooms (
    htl_id int4 NOT NULL,
	rm_id serial NOT NULL,
	rm_type varchar(50) NOT NULL,
	rm_cost decimal NOT NULL,
	CONSTRAINT bsn_hotel_rooms_pk PRIMARY KEY (rm_id)
);

CREATE TABLE bsn_hotel_services (
    htl_id int4 NOT NULL,
	srv_id serial NOT NULL,
	srv_type varchar(50) NOT NULL,
	srv_cost decimal NOT NULL,
	CONSTRAINT bsn_hotel_services_pk PRIMARY KEY (srv_id)
);

CREATE TABLE gui_language(
      gui_id int4 NOT NULL,
      gui_l_id serial NOT NULL,
      gui_l_name varchar(50) NOT NULL,
      CONSTRAINT gui_l_id_pk PRIMARY KEY (gui_l_id)
    );

CREATE TABLE gui_zone(
            gui_id int4 NOT NULL,
                  gui_z_id serial NOT NULL,
                  gui_z_name varchar(50) NOT NULL,
                  CONSTRAINT gui_z_id_pk PRIMARY KEY (gui_z_id)
    );

        CREATE TABLE gui_cities(
                   gui_z_id int4 NOT NULL,
                   gui_c_id serial NOT NULL,
                                     gui_c_name varchar(50) NOT NULL,
                                                CONSTRAINT gui_c_id_pk PRIMARY KEY (gui_c_id)

                );

ALTER TABLE bsn_business ADD CONSTRAINT bsn_business_fk FOREIGN KEY (merchant_id) REFERENCES merchant(merchant_id);


ALTER TABLE bsn_guide_agency ADD CONSTRAINT bsn_guide_agency_fk FOREIGN KEY (bsn_id) REFERENCES bsn_business(bsn_id);
ALTER TABLE bsn_restaurant ADD CONSTRAINT bsn_restaurant_fk FOREIGN KEY (bsn_id) REFERENCES bsn_business(bsn_id);
ALTER TABLE bsn_hotel ADD CONSTRAINT bsn_hotel_fk FOREIGN KEY (bsn_id) REFERENCES bsn_business(bsn_id);
ALTER TABLE bsn_hotel_rooms ADD CONSTRAINT bsn_hotel_rooms_fk FOREIGN KEY (htl_id) REFERENCES bsn_hotel(htl_id);
ALTER TABLE bsn_hotel_services ADD CONSTRAINT bsn_hotel_services_fk FOREIGN KEY (htl_id) REFERENCES bsn_hotel(htl_id);
ALTER TABLE gui_languages ADD CONSTRAINT gui_l_id_fk FOREIGN KEY (gui_id) REFERENCES bsn_guide_agency(gui_id);
ALTER TABLE gui_zones ADD CONSTRAINT gui_z_id_fk FOREIGN KEY (gui_id) REFERENCES bsn_guide_agency(gui_id);
ALTER TABLE gui_cities ADD CONSTRAINT gui_c_id_fk FOREIGN KEY (gui_z_id) REFERENCES gui_zones(gui_z_id);









