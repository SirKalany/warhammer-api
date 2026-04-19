CREATE TYPE building_category AS ENUM (
    'SETTLEMENT',
    'PORT',
    'MILITARY_RECRUITMENT',
    'MILITARY_SUPPORT',
    'DEFENSE',
    'INFRASTRUCTURE',
    'RESOURCE',
    'LANDMARK'
);

CREATE TABLE building_chain (
    id          BIGSERIAL PRIMARY KEY,
    race_id     BIGINT NOT NULL REFERENCES race(id),
    name        VARCHAR(150) NOT NULL,
    slug        VARCHAR(150) NOT NULL UNIQUE,
    category    building_category NOT NULL,
    description TEXT
);

CREATE TABLE building (
    id                  BIGSERIAL PRIMARY KEY,
    race_id             BIGINT NOT NULL REFERENCES race(id),
    building_chain_id   BIGINT REFERENCES building_chain(id) ON DELETE SET NULL,
    name                VARCHAR(150) NOT NULL,
    slug                VARCHAR(150) NOT NULL UNIQUE,
    picture             VARCHAR(255),
    tier                SMALLINT CHECK (tier BETWEEN 1 AND 5),
    category            building_category NOT NULL,
    effect              TEXT,
    cost                INTEGER,
    requirements        TEXT
);

-- Garrison (structured unit + quantity)
CREATE TABLE building_garrison (
    id          BIGSERIAL PRIMARY KEY,
    building_id BIGINT NOT NULL REFERENCES building(id) ON DELETE CASCADE,
    unit_id     BIGINT NOT NULL REFERENCES unit(id) ON DELETE CASCADE,
    quantity    INTEGER NOT NULL DEFAULT 1,
    UNIQUE (building_id, unit_id)
);