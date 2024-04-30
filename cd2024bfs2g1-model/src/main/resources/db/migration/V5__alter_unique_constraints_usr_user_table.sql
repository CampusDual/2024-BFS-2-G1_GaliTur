ALTER TABLE usr_user
ADD CONSTRAINT usr_user_usr_email_un
UNIQUE(usr_email);

ALTER TABLE usr_user
ADD CONSTRAINT usr_user_login_email_un
UNIQUE(usr_login);
