
CREATE TABLE IF NOT EXISTS api_call_log
(
    id serial,
    city character varying(85),
    latitude numeric,
    longitude numeric,
    temperature integer,
    created_on timestamp with time zone NOT NULL,
    country character varying(20)
);

ALTER TABLE api_call_log
    OWNER to postgres;

