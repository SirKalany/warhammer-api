CREATE TABLE ranged_weapon (
    id                      BIGSERIAL PRIMARY KEY,
    unit_id                 BIGINT NOT NULL REFERENCES unit(id) ON DELETE CASCADE,

    -- 'primary' or 'secondary' — only relevant when ranged_mode = 'dual'
    weapon_slot             VARCHAR(10) NOT NULL DEFAULT 'primary'
                                CHECK (weapon_slot IN ('primary', 'secondary')),

    ammunition              INTEGER,
    range                   INTEGER,
    missile_base_damage     INTEGER,
    missile_ap_damage       INTEGER,
    missile_bonus_vs_large  INTEGER,
    missile_bonus_vs_infantry INTEGER,
    explosion_damage        INTEGER,
    explosion_ap_damage     INTEGER,
    detonation_radius       NUMERIC(6, 2),
    shots_per_volley        INTEGER,
    projectile_number       INTEGER,
    projectile_category     VARCHAR(100),
    reload_time             NUMERIC(6, 2),
    total_accuracy          NUMERIC(5, 2),
    calibration_distance    NUMERIC(8, 2),
    calibration_area        NUMERIC(8, 2),
    penetration_size_cap    VARCHAR(20) CHECK (penetration_size_cap IN ('small', 'medium', 'large', 'very_large')),
    max_penetration         INTEGER,

    imbuement_id BIGINT REFERENCES imbuement(id)
);