-- Table: public.roles

--DROP TABLE IF EXISTS public.roles;

CREATE TABLE IF NOT EXISTS public.roles
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.roles
    OWNER to postgres;
    
-- Table: public.users

--DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone,
    enabled boolean,
    identification character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

-- Table: public.users_roles

--DROP TABLE IF EXISTS public.users_roles;

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users_roles
    OWNER to postgres;

-- Table: public.clients

-- DROP TABLE IF EXISTS public.clients;

CREATE TABLE IF NOT EXISTS public.clients
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone,
    enabled boolean,
    address character varying(255) COLLATE pg_catalog."default",
    age integer,
    email character varying(255) COLLATE pg_catalog."default",
    identification character varying(255) COLLATE pg_catalog."default" NOT NULL,
    names character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(255) COLLATE pg_catalog."default" NOT NULL,
    user_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT clients_pkey PRIMARY KEY (id),
    CONSTRAINT fktiuqdledq2lybrds2k3rfqrv4 FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.clients
    OWNER to postgres;


-- Table: public.accounts

-- DROP TABLE IF EXISTS public.accounts;

CREATE TABLE IF NOT EXISTS public.accounts
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp without time zone,
    enabled boolean,
    account_number character varying(255) COLLATE pg_catalog."default" NOT NULL,
    actual_balance numeric(19,2) NOT NULL,
    opening_balance numeric(19,2) NOT NULL,
    daily_withdrawal_limit numeric(19,2) NOT NULL DEFAULT 1000,
    type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    client_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT accounts_pkey PRIMARY KEY (id),
    CONSTRAINT fkgymog7firrf8bnoiig61666ob FOREIGN KEY (client_id)
        REFERENCES public.clients (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.accounts
    OWNER to postgres;


-- Table: public.movements

-- DROP TABLE IF EXISTS public.movements;

CREATE TABLE IF NOT EXISTS public.movements
(
    id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    balance numeric(19,2) NOT NULL,
    movement_date timestamp without time zone,
    movement_value numeric(19,2) NOT NULL,
    type character varying(255) COLLATE pg_catalog."default" NOT NULL,
    account_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT movements_pkey PRIMARY KEY (id),
    CONSTRAINT fk1a6nru7corjv5b2vidld4ef5r FOREIGN KEY (account_id)
        REFERENCES public.accounts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.movements
    OWNER to postgres;