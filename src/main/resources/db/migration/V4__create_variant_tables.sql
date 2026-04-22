-- Race variants
CREATE TABLE race_variant (
    id              BIGSERIAL PRIMARY KEY,
    race_id         BIGINT NOT NULL REFERENCES race(id) ON DELETE CASCADE,
    game_version_id BIGINT NOT NULL REFERENCES game_version(id),
    UNIQUE (race_id, game_version_id)
);

-- Faction variants
CREATE TABLE faction_variant (
    id                          BIGSERIAL PRIMARY KEY,
    faction_id                  BIGINT NOT NULL REFERENCES faction(id) ON DELETE CASCADE,
    game_version_id             BIGINT NOT NULL REFERENCES game_version(id),
    banner                      VARCHAR(255),
    leader                      VARCHAR(150),
    faction_effect              TEXT,
    is_horde                    BOOLEAN NOT NULL DEFAULT FALSE,
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
    climate_ocean               climate_status NOT NULL DEFAULT 'HABITABLE',
    UNIQUE (faction_id, game_version_id)
);

-- Imbuement variants
CREATE TABLE imbuement_variant (
    id              BIGSERIAL PRIMARY KEY,
    imbuement_id    BIGINT NOT NULL REFERENCES imbuement(id) ON DELETE CASCADE,
    game_version_id BIGINT NOT NULL REFERENCES game_version(id),
    description     TEXT,
    icon            VARCHAR(255),
    UNIQUE (imbuement_id, game_version_id)
);

-- Unit attribute variants
CREATE TABLE unit_attribute_variant (
    id                BIGSERIAL PRIMARY KEY,
    unit_attribute_id BIGINT NOT NULL REFERENCES unit_attribute(id) ON DELETE CASCADE,
    game_version_id   BIGINT NOT NULL REFERENCES game_version(id),
    description       TEXT,
    UNIQUE (unit_attribute_id, game_version_id)
);

-- Ability variants
CREATE TABLE ability_variant (
    id              BIGSERIAL PRIMARY KEY,
    ability_id      BIGINT NOT NULL REFERENCES ability(id) ON DELETE CASCADE,
    game_version_id BIGINT NOT NULL REFERENCES game_version(id),
    effect          TEXT,
    description     TEXT,
    target          VARCHAR(255),
    range           NUMERIC(8,2),
    radius          NUMERIC(8,2),
    duration        NUMERIC(8,2),
    cooldown        NUMERIC(8,2),
    affected_units  INTEGER,
    uses            INTEGER,
    conditions      TEXT,
    base_damage     INTEGER,
    explosive_damage INTEGER,
    damage_per_second NUMERIC(8,2),
    movement_speed  NUMERIC(8,2),
    UNIQUE (ability_id, game_version_id)
);

-- Spell variants
CREATE TABLE spell_variant (
    id                          BIGSERIAL PRIMARY KEY,
    spell_id                    BIGINT NOT NULL REFERENCES spell(id) ON DELETE CASCADE,
    game_version_id             BIGINT NOT NULL REFERENCES game_version(id),
    description                 TEXT,
    effect                      TEXT,
    target                      VARCHAR(255),
    range                       NUMERIC(8,2),
    radius                      NUMERIC(8,2),
    duration                    NUMERIC(8,2),
    cooldown                    NUMERIC(8,2),
    affected_units              INTEGER,
    uses                        INTEGER,
    conditions                  TEXT,
    base_damage                 INTEGER,
    explosive_damage            INTEGER,
    damage_per_second           NUMERIC(8,2),
    movement_speed              NUMERIC(8,2),
    cost                        INTEGER,
    miscast_chance              NUMERIC(5,2),
    overcast_effect             TEXT,
    overcast_target             VARCHAR(255),
    overcast_range              NUMERIC(8,2),
    overcast_radius             NUMERIC(8,2),
    overcast_duration           NUMERIC(8,2),
    overcast_cooldown           NUMERIC(8,2),
    overcast_affected_units     INTEGER,
    overcast_base_damage        INTEGER,
    overcast_explosive_damage   INTEGER,
    overcast_damage_per_second  NUMERIC(8,2),
    overcast_movement_speed     NUMERIC(8,2),
    overcast_cost               INTEGER,
    overcast_miscast_chance     NUMERIC(5,2),
    UNIQUE (spell_id, game_version_id)
);

-- Item variants
CREATE TABLE item_variant (
    id              BIGSERIAL PRIMARY KEY,
    item_id         BIGINT NOT NULL REFERENCES item(id) ON DELETE CASCADE,
    game_version_id BIGINT NOT NULL REFERENCES game_version(id),
    picture         VARCHAR(255),
    effect          TEXT,
    race_id         BIGINT REFERENCES race(id) ON DELETE SET NULL,
    UNIQUE (item_id, game_version_id)
);

-- Item variant abilities and spells
CREATE TABLE item_variant_ability (
    item_variant_id    BIGINT NOT NULL REFERENCES item_variant(id) ON DELETE CASCADE,
    ability_variant_id BIGINT NOT NULL REFERENCES ability_variant(id) ON DELETE CASCADE,
    PRIMARY KEY (item_variant_id, ability_variant_id)
);

CREATE TABLE item_variant_spell (
    item_variant_id  BIGINT NOT NULL REFERENCES item_variant(id) ON DELETE CASCADE,
    spell_variant_id BIGINT NOT NULL REFERENCES spell_variant(id) ON DELETE CASCADE,
    PRIMARY KEY (item_variant_id, spell_variant_id)
);

-- Building variants
CREATE TABLE building_variant (
    id              BIGSERIAL PRIMARY KEY,
    building_id     BIGINT NOT NULL REFERENCES building(id) ON DELETE CASCADE,
    game_version_id BIGINT NOT NULL REFERENCES game_version(id),
    picture         VARCHAR(255),
    tier            SMALLINT CHECK (tier BETWEEN 1 AND 5),
    effect          TEXT,
    cost            INTEGER,
    requirements    TEXT,
    UNIQUE (building_id, game_version_id)
);