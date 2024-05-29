ALTER TABLE route
    ADD COLUMN creation_date  timestamp NULL DEFAULT now();

ALTER TABLE pack
    ADD COLUMN creation_date  timestamp NULL DEFAULT now();