CREATE TABLE pack_state
(
    pcs_id    serial      NOT NULL PRIMARY KEY,
    pcs_state varchar(50) NOT NULL
);
CREATE TABLE pack
(
    pck_id           serial      NOT NULL,
    pck_name         varchar(50) NOT NULL,
    pck_description  text        NOT NULL,
    pck_date_begin   timestamp   NOT NULL,
    pck_date_end     timestamp   NOT NULL,
    pck_price        decimal(10, 2) DEFAULT 0,
    pck_participants integer,
    gui_c_id         integer     NOT NULL REFERENCES gui_cities (gui_c_id),
    pcs_id           integer     NOT NULL DEFAULT 1 REFERENCES pack_state (pcs_id),
    CONSTRAINT pack_pk PRIMARY KEY (pck_id)
);

CREATE TABLE image_pack
(
    imp_id serial,
    pck_id integer NOT NULL REFERENCES pack (pck_id),
    img_id integer NOT NULL REFERENCES image (image_id),
    CONSTRAINT image_pack_pk PRIMARY KEY (imp_id)
);

INSERT INTO pack_state(pcs_state)
VALUES ('active');
INSERT INTO pack_state(pcs_state)
VALUES ('reserved');

INSERT INTO usr_server_permission(srp_name)
VALUES ('com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackService/packInsert');

INSERT INTO usr_role_server_permission(rol_id, srp_id)
VALUES ((SELECT usr_role.rol_id FROM usr_role WHERE rol_name = 'manager'),
        (SELECT usr_server_permission.srp_id
         FROM usr_server_permission
         WHERE srp_name = 'com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackService/packInsert'));
INSERT INTO usr_role_server_permission(rol_id, srp_id)
VALUES ((SELECT usr_role.rol_id FROM usr_role WHERE rol_name = 'admin'),
        (SELECT usr_server_permission.srp_id
         FROM usr_server_permission
         WHERE srp_name = 'com.campusdual.cd2024bfs2g1.api.core.service.pack.IPackService/packInsert'));
