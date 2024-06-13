CREATE TABLE IF NOT EXISTS public.job_offer
(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    added_date date DEFAULT CURRENT_DATE,
    applied_date date,
    description character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    url character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT job_offer_pkey PRIMARY KEY (id),
    CONSTRAINT job_offer_status_check CHECK (status::text = ANY (ARRAY['LISTED'::character varying, 'APPLIED'::character varying, 'REJECTED'::character varying, 'IN_PROCESS'::character varying, 'HIRED'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.job_offer
    OWNER to jobhunter;