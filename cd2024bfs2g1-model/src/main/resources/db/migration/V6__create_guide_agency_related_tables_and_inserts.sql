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


ALTER TABLE gui_language ADD CONSTRAINT gui_l_id_fk FOREIGN KEY (gui_id) REFERENCES bsn_guide_agency(gui_id);
ALTER TABLE gui_zone ADD CONSTRAINT gui_z_id_fk FOREIGN KEY (gui_id) REFERENCES bsn_guide_agency(gui_id);
ALTER TABLE gui_cities ADD CONSTRAINT gui_c_id_fk FOREIGN KEY (gui_z_id) REFERENCES gui_zone(gui_z_id);