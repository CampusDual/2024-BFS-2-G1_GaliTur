CREATE TABLE public.bsn_pack (
	bsn_pack_id serial4 NOT NULL,
	bsn_id int4 NOT NULL,
	pck_id int4 NOT NULL,
	assigned_date date NOT NULL,
	CONSTRAINT bsn_pack_id_pk PRIMARY KEY (bsn_pack_id)
);

ALTER TABLE public.bsn_pack ADD CONSTRAINT bsn_pack_bs_fk FOREIGN KEY (bsn_id) REFERENCES public.bsn_business(bsn_id);
ALTER TABLE public.bsn_pack ADD CONSTRAINT bsn_pack_pc_fk FOREIGN KEY (pck_id) REFERENCES public.pack(pck_id);

CREATE TABLE public.route_pack (
	route_pack_id serial4 NOT NULL,
	route_id int4 NOT NULL,
	pck_id int4 NOT NULL,
	assigned_date date NOT NULL,
	CONSTRAINT route_pack_id_pk PRIMARY KEY (route_pack_id)
);

ALTER TABLE public.route_pack ADD CONSTRAINT route_pack_rou_fk FOREIGN KEY (route_id) REFERENCES public.route(route_id);
ALTER TABLE public.route_pack ADD CONSTRAINT route_pack_pc_fk FOREIGN KEY (pck_id) REFERENCES public.pack(pck_id);

ALTER TABLE PUBLIC.bsn_pack
drop column assigned_date;

ALTER TABLE PUBLIC.route_pack
drop column assigned_date;

ALTER TABLE PUBLIC.bsn_pack
add column assigned_date int4;

ALTER TABLE PUBLIC.route_pack
add column assigned_date int4;

ALTER TABLE PUBLIC.pack
add column pck_days int4;