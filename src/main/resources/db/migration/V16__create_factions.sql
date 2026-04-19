CREATE TYPE climate_status AS ENUM (
    'HABITABLE',
    'UNPLEASANT',
    'UNINHABITABLE'
);

CREATE TABLE faction (
    id                          BIGSERIAL PRIMARY KEY,
    race_id                     BIGINT NOT NULL REFERENCES race(id),
    name                        VARCHAR(150) NOT NULL UNIQUE,
    slug                        VARCHAR(150) NOT NULL UNIQUE,
    banner                      VARCHAR(255),
    leader                      VARCHAR(150),
    faction_effect              TEXT,
    is_horde                    BOOLEAN NOT NULL DEFAULT FALSE,

    -- Climates
    climate_chaotic_wasteland   climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_frozen              climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_mountain            climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_temperate           climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_temperate_island    climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_magical_forest      climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_jungle              climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_savannah            climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_desert              climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_wasteland           climate_status NOT NULL DEFAULT 'HABITABLE',
    climate_ocean               climate_status NOT NULL DEFAULT 'HABITABLE'
);