CREATE TABLE TSETTING
(
    ID_SETTING      SERIAL PRIMARY KEY,
    SETTING_KEY     VARCHAR,
    SETTING_VALUE   VARCHAR,
    SETTING_COMMENT VARCHAR
);

INSERT INTO TSETTING (SETTING_KEY, SETTING_VALUE, SETTING_COMMENT)
VALUES ('mail_host', 'smtp.mailgun.org', 'Server host');
INSERT INTO TSETTING (SETTING_KEY, SETTING_VALUE, SETTING_COMMENT)
VALUES ('mail_port', '587', 'Email server port');
INSERT INTO TSETTING (SETTING_KEY, SETTING_VALUE, SETTING_COMMENT)
VALUES ('mail_protocol', 'smtp', 'Mailing protocol');
-- FALTA DATOS
INSERT INTO TSETTING (SETTING_KEY, SETTING_VALUE, SETTING_COMMENT)
VALUES ('mail_encoding', 'UTF-8', 'Encoding of mails');
INSERT INTO TSETTING (SETTING_KEY, SETTING_VALUE, SETTING_COMMENT)
VALUES ('mail_properties', 'mail.smtp.auth:true;mail.smtp.starttls.enable:true;', 'Mail properties');

INSERT INTO usr_server_permission (srp_name)
VALUES ('com.ontimize.jee.server.services.mail.IMailServiceServer/sendMail');

INSERT INTO usr_server_permission (srp_name)
VALUES ('com.ontimize.jee.server.services.mail.IMailServiceServer/sendMailWithoutAttach');


-- INSERT INTO usr_role_server_permission (rol_id, srp_id)
-- VALUES ((SELECT UR.rol_id FROM usr_role UR WHERE UR.rol_name = 'user'),
--         (SELECT USP.srp_id
--          FROM usr_server_permission USP
--          WHERE USP.srp_name = 'com.ontimize.jee.server.services.mail.IMailServiceServer/sendMail'));
--
-- INSERT INTO usr_role_server_permission (rol_id, srp_id)
-- VALUES ((SELECT UR.rol_id FROM usr_role UR WHERE UR.rol_name = 'user'),
--         (SELECT USP.srp_id
--          FROM usr_server_permission USP
--          WHERE USP.srp_name = 'com.ontimize.jee.server.services.mail.IMailServiceServer/sendMailWithoutAttach'));


INSERT INTO usr_role_server_permission (rol_id, srp_id)
VALUES ((SELECT UR.rol_id FROM usr_role UR WHERE UR.rol_name = 'client'),
        (SELECT USP.srp_id
         FROM usr_server_permission USP
         WHERE USP.srp_name = 'com.ontimize.jee.server.services.mail.IMailServiceServer/sendMail'));

INSERT INTO usr_role_server_permission (rol_id, srp_id)
VALUES ((SELECT UR.rol_id FROM usr_role UR WHERE UR.rol_name = 'client'),
        (SELECT USP.srp_id
         FROM usr_server_permission USP
         WHERE USP.srp_name = 'com.ontimize.jee.server.services.mail.IMailServiceServer/sendMailWithoutAttach'));
