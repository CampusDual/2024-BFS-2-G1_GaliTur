DO
$$
    BEGIN
        FOR i IN 1..4
            LOOP
                WITH inserted_id AS (
                    INSERT INTO public.pack_date (pd_date_begin, pd_date_end, pcs_id, pck_id, creation_date)
                        VALUES (TO_TIMESTAMP(1718003635), TO_TIMESTAMP(1718522035), 2, (SELECT p.pck_id
                                                                                        FROM pack p
                                                                                        ORDER BY pck_id DESC
                                                                                        LIMIT 1), NOW())
                        RETURNING pd_id)
                INSERT
                INTO public.pack_booking(client_id, pbk_down_date, pd_id, pbk_stars, pbk_comment, pbk_rating_date)
                SELECT (SELECT c.client_id client_id
                        FROM client c
                        WHERE c.usr_id = (SELECT u.usr_id
                                          FROM usr_user u
                                          WHERE u.usr_login =
                                                'Client.1')),
                       NULL,
                       inserted_id.pd_id,
                       NULL,
                       NULL,
                       NULL
                FROM inserted_id;
            END LOOP;
    END
$$;

WITH inserted_id AS (
    INSERT INTO public.pack_date (pd_date_begin, pd_date_end, pcs_id, pck_id, creation_date)
        VALUES (TO_TIMESTAMP(1718003635), TO_TIMESTAMP(1718522035), 2, 4, NOW())
        RETURNING pd_id)
INSERT
INTO public.pack_booking(client_id, pbk_down_date, pd_id, pbk_stars, pbk_comment, pbk_rating_date)
SELECT (SELECT c.client_id client_id
        FROM client c
        WHERE c.usr_id = (SELECT u.usr_id
                          FROM usr_user u
                          WHERE u.usr_login =
                                'Client.1')),
       NULL,
       inserted_id.pd_id,
       NULL,
       NULL,
       NULL
FROM inserted_id;

WITH inserted_id AS (
    INSERT INTO public.pack_date (pd_date_begin, pd_date_end, pcs_id, pck_id, creation_date)
        VALUES (TO_TIMESTAMP(1718003635), TO_TIMESTAMP(1718522035), 2, 11, NOW())
        RETURNING pd_id)
INSERT
INTO public.pack_booking(client_id, pbk_down_date, pd_id, pbk_stars, pbk_comment, pbk_rating_date)
SELECT (SELECT c.client_id client_id
        FROM client c
        WHERE c.usr_id = (SELECT u.usr_id
                          FROM usr_user u
                          WHERE u.usr_login =
                                'Client.1')),
       NULL,
       inserted_id.pd_id,
       NULL,
       NULL,
       NULL
FROM inserted_id;

WITH inserted_id AS (
    INSERT INTO public.pack_date (pd_date_begin, pd_date_end, pcs_id, pck_id, creation_date)
        VALUES (TO_TIMESTAMP(1718003635), TO_TIMESTAMP(1718522035), 2, 12, NOW())
        RETURNING pd_id)
INSERT
INTO public.pack_booking(client_id, pbk_down_date, pd_id, pbk_stars, pbk_comment, pbk_rating_date)
SELECT (SELECT c.client_id client_id
        FROM client c
        WHERE c.usr_id = (SELECT u.usr_id
                          FROM usr_user u
                          WHERE u.usr_login =
                                'Client.1')),
       NULL,
       inserted_id.pd_id,
       NULL,
       NULL,
       NULL
FROM inserted_id;
