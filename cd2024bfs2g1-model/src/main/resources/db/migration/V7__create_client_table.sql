CREATE TABLE client
(
    client_id  serial    NOT NULL,
    usr_id     integer   NOT NULL,
    birth_date timestamp NOT NULL,

    CONSTRAINT client_pk PRIMARY KEY (client_id)
);
ALTER TABLE client
    ADD CONSTRAINT client_fk FOREIGN KEY (usr_id) REFERENCES usr_user (usr_id);
