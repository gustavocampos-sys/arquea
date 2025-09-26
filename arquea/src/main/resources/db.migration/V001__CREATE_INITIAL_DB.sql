CREATE TABLE users
(
    id                serial       NOT NULL,
    username          varchar(50)  NOT NULL,
    "password"        varchar(255) NOT NULL,
    email             varchar(100) NOT NULL,
    first_name        varchar(50)  NOT NULL,
    last_name         varchar(50)  NOT NULL,
    profile_image_url varchar(255),
    is_active         boolean      NOT NULL,
    is_verified       boolean      NOT NULL DEFAULT false,
    created_at        timestamp             DEFAULT CURRENT_TIMESTAMP,
    updated_at        timestamp,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE email_verification_tokens
(
    id          serial       NOT NULL,
    user_id     integer      NOT NULL,
    token       varchar(255) NOT NULL,
    expire_date timestamp    NOT NULL,
    is_revoked  boolean DEFAULT false,
    CONSTRAINT email_verification_tokens_pkey PRIMARY KEY (id)
);

CREATE TABLE password_reset_tokens
(
    id          serial       NOT NULL,
    user_id     integer      NOT NULL,
    token       varchar(255) NOT NULL,
    expire_date timestamp    NOT NULL,
    is_used     boolean      NOT NULL DEFAULT false,
    CONSTRAINT password_reset_tokens_pkey PRIMARY KEY (id)
);

CREATE TABLE refresh_tokens
(
    id          serial       NOT NULL,
    user_id     integer      NOT NULL,
    token       varchar(255) NOT NULL,
    expire_date timestamp    NOT NULL,
    is_revoked  boolean      NOT NULL DEFAULT false,
    CONSTRAINT refresh_tokens_pkey PRIMARY KEY (id)
);

CREATE TABLE business
(
    id          serial       NOT NULL,
    "name"      varchar(255) NOT NULL,
    description varchar(255),
    created_at  timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at  timestamp,
    user_id     integer      NOT NULL,
    CONSTRAINT business_pkey PRIMARY KEY (id)
);

CREATE TABLE daily_sessions
(
    id           serial       NOT NULL,
    session_date timestamp    NOT NULL,
    opened_at    timestamp    NOT NULL,
    closed_at    timestamp    NOT NULL,
    status       varchar(255) NOT NULL,
    auto_open    boolean      NOT NULL DEFAULT false,
    business_id  integer      NOT NULL,
    CONSTRAINT daily_sessions_pkey PRIMARY KEY (id)
);

CREATE TABLE accounts
(
    id               serial         NOT NULL,
    "name"           varchar(255)   NOT NULL,
    description      varchar(255),
    initial_balance  numeric(15, 2) NOT NULL,
    final_balance    numeric(15, 2) NOT NULL,
    account_type     varchar(255)   NOT NULL,
    is_active        boolean        NOT NULL DEFAULT false,
    created_at       timestamp               DEFAULT CURRENT_TIMESTAMP,
    updated_at       timestamp,
    daily_session_id integer        NOT NULL,
    CONSTRAINT accounts_pkey PRIMARY KEY (id)
);

/* Falta los alter table */

ALTER TABLE
    business
    ADD
        CONSTRAINT business_id_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE
    daily_sessions
    ADD
        CONSTRAINT daily_sessions_business_id_fkey FOREIGN KEY (business_id) REFERENCES business (id);

ALTER TABLE
    accounts
    ADD
        CONSTRAINT accounts_daily_sessions_id_fkey FOREIGN KEY (daily_session_id) REFERENCES daily_sessions (id);

ALTER TABLE
    email_verification_tokens
    ADD
        CONSTRAINT email_verification_tokens_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE
    password_reset_tokens
    ADD
        CONSTRAINT password_reset_tokens_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE
    refresh_tokens
    ADD
        CONSTRAINT refresh_tokens_user_id_fkey FOREIGN KEY (user_id) REFERENCES users (id);
