CREATE TABLE public.temperature
(
    id bigserial NOT NULL,
    celsius_value float NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL,
    created_at timestamp with time zone NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.temperature
    OWNER to postgres;