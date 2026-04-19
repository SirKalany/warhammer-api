CREATE TABLE building_chain (
    id          BIGSERIAL PRIMARY KEY,
    race_id     BIGINT NOT NULL REFERENCES race(id),
    name        VARCHAR(150) NOT NULL,
    slug        VARCHAR(150) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE building (
    id                  BIGSERIAL PRIMARY KEY,
    race_id             BIGINT NOT NULL REFERENCES race(id),
    building_chain_id   BIGINT REFERENCES building_chain(id) ON DELETE SET NULL,
    name                VARCHAR(150) NOT NULL,
    slug                VARCHAR(150) NOT NULL UNIQUE,
    picture             VARCHAR(255),
    tier                SMALLINT CHECK (tier BETWEEN 1 AND 5)
    -- further building-specific columns to be added later
);