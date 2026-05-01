CREATE TABLE unit_variant (
    id                  BIGSERIAL PRIMARY KEY,
    unit_id             BIGINT NOT NULL REFERENCES unit(id) ON DELETE CASCADE,
    game_version_id     BIGINT NOT NULL REFERENCES game_version(id),

    -- Display
    display_name        VARCHAR(150),
    picture             VARCHAR(255),
    tier                SMALLINT NOT NULL CHECK (tier BETWEEN 1 AND 5),
    category            VARCHAR(100),
    category_type       unit_category_type NOT NULL DEFAULT 'REGULAR',
    role                unit_role NOT NULL,
    description         TEXT,

    -- Overview
    size                VARCHAR(10) NOT NULL CHECK (size IN ('small', 'large')),
    entities            INTEGER,
    mass                NUMERIC(8,2),
    campaign_cost       INTEGER,
    base_upkeep         INTEGER,
    multiplayer_cost    INTEGER,

    -- Survivability
    health              INTEGER,
    health_per_entity   INTEGER,
    barrier             INTEGER,
    armour              INTEGER,
    parry               NUMERIC(5,2),
    ward_save           NUMERIC(5,2),
    physical_resistance NUMERIC(5,2),
    missile_resistance  NUMERIC(5,2),
    spell_resistance    NUMERIC(5,2),
    fire_resistance     NUMERIC(5,2),
    leadership          INTEGER,

    -- Mobility
    speed               INTEGER,
    charge_speed        INTEGER,

    -- Melee
    melee_attack                INTEGER,
    melee_imbuement_id          BIGINT REFERENCES imbuement_variant(id) ON DELETE SET NULL,
    attack_interval             NUMERIC(5,2),
    high_threat                 BOOLEAN DEFAULT FALSE,
    splash_target_size          VARCHAR(20),
    splash_max_attacks          INTEGER,
    melee_defense               INTEGER,
    weapon_strength             INTEGER,
    melee_base_damage           INTEGER,
    melee_ap_damage             INTEGER,
    melee_bonus_vs_large        INTEGER,
    melee_bonus_vs_infantry     INTEGER,
    charge_bonus                INTEGER,

    -- Ranged
    ranged_mode         ranged_mode NOT NULL DEFAULT 'NONE',

    -- Building requirements
    unlock_building_id  BIGINT REFERENCES building_variant(id) ON DELETE SET NULL,
    allow_building_id   BIGINT REFERENCES building_variant(id) ON DELETE SET NULL,

    UNIQUE (unit_id, game_version_id)
);

-- Ranged weapons
CREATE TABLE ranged_weapon (
    id                      BIGSERIAL PRIMARY KEY,
    unit_variant_id         BIGINT NOT NULL REFERENCES unit_variant(id) ON DELETE CASCADE,
    weapon_slot             VARCHAR(10) NOT NULL DEFAULT 'primary'
                                CHECK (weapon_slot IN ('primary', 'secondary')),
    imbuement_variant_id    BIGINT REFERENCES imbuement_variant(id) ON DELETE SET NULL,
    ammunition              INTEGER,
    range                   INTEGER,
    missile_base_damage     INTEGER,
    missile_ap_damage       INTEGER,
    missile_bonus_vs_large  INTEGER,
    missile_bonus_vs_infantry INTEGER,
    explosion_damage        INTEGER,
    explosion_ap_damage     INTEGER,
    detonation_radius       NUMERIC(6,2),
    shots_per_volley        INTEGER,
    projectile_number       INTEGER,
    projectile_category     VARCHAR(100),
    reload_time             NUMERIC(6,2),
    total_accuracy          NUMERIC(5,2),
    calibration_distance    NUMERIC(8,2),
    calibration_area        NUMERIC(8,2),
    penetration_size_cap    VARCHAR(20),
    max_penetration         INTEGER
);

-- Unit variant attribute lines
CREATE TABLE unit_variant_attribute_line (
    id               BIGSERIAL PRIMARY KEY,
    unit_variant_id  BIGINT NOT NULL REFERENCES unit_variant(id) ON DELETE CASCADE,
    position         SMALLINT NOT NULL CHECK (position BETWEEN 1 AND 4),
    content          TEXT NOT NULL,
    UNIQUE (unit_variant_id, position)
);

-- Unit variant relations
CREATE TABLE unit_variant_unit_attribute (
    unit_variant_id   BIGINT NOT NULL REFERENCES unit_variant(id) ON DELETE CASCADE,
    unit_attribute_id BIGINT NOT NULL REFERENCES unit_attribute(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_variant_id, unit_attribute_id)
);

CREATE TABLE unit_variant_ability (
    unit_variant_id    BIGINT NOT NULL REFERENCES unit_variant(id) ON DELETE CASCADE,
    ability_variant_id BIGINT NOT NULL REFERENCES ability_variant(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_variant_id, ability_variant_id)
);

CREATE TABLE unit_variant_passive_ability (
    unit_variant_id    BIGINT NOT NULL REFERENCES unit_variant(id) ON DELETE CASCADE,
    ability_variant_id BIGINT NOT NULL REFERENCES ability_variant(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_variant_id, ability_variant_id)
);

CREATE TABLE unit_variant_spell (
    unit_variant_id  BIGINT NOT NULL REFERENCES unit_variant(id) ON DELETE CASCADE,
    spell_variant_id BIGINT NOT NULL REFERENCES spell_variant(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_variant_id, spell_variant_id)
);

CREATE TABLE unit_variant_item (
    unit_variant_id BIGINT NOT NULL REFERENCES unit_variant(id) ON DELETE CASCADE,
    item_variant_id BIGINT NOT NULL REFERENCES item_variant(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_variant_id, item_variant_id)
);

-- Building garrison links to unit variants
CREATE TABLE building_variant_garrison (
    id                  BIGSERIAL PRIMARY KEY,
    building_variant_id BIGINT NOT NULL REFERENCES building_variant(id) ON DELETE CASCADE,
    unit_variant_id     BIGINT NOT NULL REFERENCES unit_variant(id) ON DELETE CASCADE,
    quantity            INTEGER NOT NULL DEFAULT 1,
    UNIQUE (building_variant_id, unit_variant_id)
);