create table if not exists USERS
(
    user_id integer     NOT NULL GENERATED ALWAYS AS IDENTITY (START 1 INCREMENT 1 ),
    name    varchar(45) not null,
    CONSTRAINT customer_pkey PRIMARY KEY (user_id)
)