INSERT INTO usr_role
    (rol_name, rol_xml_client_permission, rol_json_client_permission, rol_notes)
    VALUES (
            'client',
            '<?xml version="1.0" encoding="UTF-8"?><security><MENU><ELEMENT attr="admin"><Enabled restricted="yes"/><Visible restricted="yes"/></ELEMENT></MENU></security>',
            '{ "menu": [{ "attr": "admin", "visible": false, "enabled": false }] }',
            'This is the client role'
            );

INSERT INTO usr_role
(rol_name, rol_xml_client_permission, rol_json_client_permission, rol_notes)
VALUES (
           'manager',
           '<?xml version="1.0" encoding="UTF-8"?><security><MENU><ELEMENT attr="admin"><Enabled restricted="yes"/><Visible restricted="yes"/></ELEMENT></MENU></security>',
           '{ "menu": [{ "attr": "admin", "visible": false, "enabled": false }] }',
           'This is the manager role'
       );

INSERT INTO usr_role
(rol_name, rol_xml_client_permission, rol_json_client_permission, rol_notes)
VALUES (
           'merchant',
           '<?xml version="1.0" encoding="UTF-8"?><security><MENU><ELEMENT attr="admin"><Enabled restricted="yes"/><Visible restricted="yes"/></ELEMENT></MENU></security>',
           '{ "menu": [{ "attr": "admin", "visible": false, "enabled": false }] }',
           'This is the merchant role'
       );

INSERT INTO usr_role
(rol_name, rol_xml_client_permission, rol_json_client_permission, rol_notes)
VALUES (
           'guide',
           '<?xml version="1.0" encoding="UTF-8"?><security><MENU><ELEMENT attr="admin"><Enabled restricted="yes"/><Visible restricted="yes"/></ELEMENT></MENU></security>',
           '{ "menu": [{ "attr": "admin", "visible": false, "enabled": false }] }',
           'This is the guide role'
       );



CREATE TABLE merchant (
    merchant_id serial NOT NULL,
    usr_id integer NOT NULL,
    CONSTRAINT merchant_pk PRIMARY KEY (merchant_id)
);
ALTER TABLE merchant ADD CONSTRAINT merchant_fk FOREIGN KEY (usr_id) REFERENCES usr_user(usr_id);
