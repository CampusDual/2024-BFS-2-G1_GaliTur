ALTER TABLE gui_language DROP CONSTRAINT gui_l_id_fk;
ALTER TABLE gui_zone DROP CONSTRAINT gui_z_id_fk;

ALTER TABLE gui_language DROP COLUMN gui_id;
ALTER TABLE gui_zone DROP COLUMN gui_id;

INSERT INTO public.gui_language
(gui_l_id, gui_l_name)
VALUES(nextval('gui_language_gui_l_id_seq'::regclass), 'SPANISH'),
 (nextval('gui_language_gui_l_id_seq'::regclass), 'GALICIAN'),
 (nextval('gui_language_gui_l_id_seq'::regclass), 'ENGLISH'),
 (nextval('gui_language_gui_l_id_seq'::regclass), 'GERMAN'),
 (nextval('gui_language_gui_l_id_seq'::regclass), 'PORTUGUESE'),
 (nextval('gui_language_gui_l_id_seq'::regclass), 'FRENCH');

INSERT INTO public.gui_zone
(gui_z_id, gui_z_name)
VALUES(nextval('gui_zone_gui_z_id_seq'::regclass), 'CORUNA'),
(nextval('gui_zone_gui_z_id_seq'::regclass), 'LUGO'),
(nextval('gui_zone_gui_z_id_seq'::regclass), 'OURENSE'),
(nextval('gui_zone_gui_z_id_seq'::regclass), 'PONTEVEDRA');

INSERT INTO public.gui_cities
(gui_z_id, gui_c_id, gui_c_name)
VALUES(1, nextval('gui_cities_gui_c_id_seq'::regclass), 'CORUNA'),
(1, nextval('gui_cities_gui_c_id_seq'::regclass), 'FERROL'),
(1, nextval('gui_cities_gui_c_id_seq'::regclass), 'SANTIAGO'),
(2, nextval('gui_cities_gui_c_id_seq'::regclass), 'LUGO'),
(2, nextval('gui_cities_gui_c_id_seq'::regclass), 'MONFORTE'),
(2, nextval('gui_cities_gui_c_id_seq'::regclass), 'VIVEIRO'),
(3, nextval('gui_cities_gui_c_id_seq'::regclass), 'OURENSE'),
(3, nextval('gui_cities_gui_c_id_seq'::regclass), 'VERIN'),
(3, nextval('gui_cities_gui_c_id_seq'::regclass), 'XINZO'),
(4, nextval('gui_cities_gui_c_id_seq'::regclass), 'PONTEVEDRA'),
(4, nextval('gui_cities_gui_c_id_seq'::regclass), 'VIGO'),
(4, nextval('gui_cities_gui_c_id_seq'::regclass), 'MARIN');