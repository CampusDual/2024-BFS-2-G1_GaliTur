ALTER TABLE PUBLIC.bsn_pack
drop column assigned_date;

ALTER TABLE PUBLIC.route_pack
drop column assigned_date;

ALTER TABLE PUBLIC.bsn_pack
add column assigned_date int4;

ALTER TABLE PUBLIC.route_pack
add column assigned_date int4;
