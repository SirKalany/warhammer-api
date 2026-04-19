CREATE TABLE unit (
    id                      BIGSERIAL PRIMARY KEY,
    race_id                 BIGINT NOT NULL REFERENCES race(id),
    name                    VARCHAR(150) NOT NULL,
    slug                    VARCHAR(150) NOT NULL UNIQUE,
    picture                 VARCHAR(255),
    tier                    SMALLINT NOT NULL CHECK (tier BETWEEN 1 AND 5),
    category                VARCHAR(100),
    description             TEXT,

    -- Overview
    size                    VARCHAR(10) NOT NULL CHECK (size IN ('small', 'large')),
    entities                INTEGER,
    mass                    NUMERIC(8, 2),
    campaign_cost           INTEGER,
    base_upkeep             INTEGER,
    multiplayer_cost        INTEGER,

    -- Survivability
    health                  INTEGER,
    health_per_entity       INTEGER,
    barrier                 INTEGER,
    armour                  INTEGER,
    parry                   NUMERIC(5, 2),
    ward_save               NUMERIC(5, 2),
    physical_resistance     NUMERIC(5, 2),
    missile_resistance      NUMERIC(5, 2),
    spell_resistance        NUMERIC(5, 2),
    fire_resistance         NUMERIC(5, 2),
    leadership              INTEGER,

    -- Mobility
    speed                   INTEGER,
    charge_speed            INTEGER,

    -- Melee Combat
    melee_attack            INTEGER,
    melee_imbuement_id      BIGINT REFERENCES imbuement(id),
    attack_interval         NUMERIC(5, 2),
    high_threat             BOOLEAN DEFAULT FALSE,
    splash_target_size      VARCHAR(20) CHECK (splash_target_size IN ('small', 'medium', 'large', 'very_large')),
    splash_max_attacks      INTEGER,
    melee_defense           INTEGER,
    weapon_strength         INTEGER,
    melee_base_damage       INTEGER,
    melee_ap_damage         INTEGER,
    melee_bonus_vs_large    INTEGER,
    melee_bonus_vs_infantry INTEGER,
    charge_bonus            INTEGER,

    -- Ranged weapon mode: none | single | dual
    ranged_mode             VARCHAR(10) NOT NULL DEFAULT 'none'
                                CHECK (ranged_mode IN ('none', 'single', 'dual'))
);