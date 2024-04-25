ALTER TABLE usr_role ADD COLUMN end_point varchar;

INSERT INTO usr_role
    (rol_name, rol_xml_client_permission, rol_json_client_permission, rol_notes, end_point)
    VALUES (
            'client',
            '<?xml version="1.0" encoding="UTF-8"?><security><MENU><ELEMENT attr="admin"><Enabled restricted="yes"/><Visible restricted="yes"/></ELEMENT></MENU></security>',
            '{ "menu": [{ "attr": "admin", "visible": false, "enabled": false }] }',
            'This is the client role',
            'register-client'
            );

INSERT INTO usr_role
(rol_name, rol_xml_client_permission, rol_json_client_permission, rol_notes, end_point)
VALUES (
           'manager',
           '<?xml version="1.0" encoding="UTF-8"?><security><MENU><ELEMENT attr="admin"><Enabled restricted="yes"/><Visible restricted="yes"/></ELEMENT></MENU></security>',
           '{ "menu": [{ "attr": "admin", "visible": false, "enabled": false }] }',
           'This is the manager role',
           'register-manager'
       );

INSERT INTO usr_role
(rol_name, rol_xml_client_permission, rol_json_client_permission, rol_notes, end_point)
VALUES (
           'merchant',
           '<?xml version="1.0" encoding="UTF-8"?><security><MENU><ELEMENT attr="admin"><Enabled restricted="yes"/><Visible restricted="yes"/></ELEMENT></MENU></security>',
           '{ "menu": [{ "attr": "admin", "visible": false, "enabled": false }] }',
           'This is the client role',
           'register-merchant'
       );

CREATE TABLE merchant (
    merchant_id serial NOT NULL,
    usr_id integer NOT NULL,
    CONSTRAINT merchant_pk PRIMARY KEY (merchant_id)
);
ALTER TABLE merchant ADD CONSTRAINT merchant_fk FOREIGN KEY (usr_id) REFERENCES usr_user(usr_id);
