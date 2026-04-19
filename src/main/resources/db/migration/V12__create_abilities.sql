CREATE TABLE ability (
    id                  BIGSERIAL PRIMARY KEY,
    name                VARCHAR(150) NOT NULL,
    slug                VARCHAR(150) NOT NULL UNIQUE,
    type                ability_type NOT NULL,
    effect              TEXT,
    target              VARCHAR(255),
    range               NUMERIC(8, 2),
    radius              NUMERIC(8, 2),
    duration            NUMERIC(8, 2),
    cooldown            NUMERIC(8, 2),
    affected_units      INTEGER,
    uses                INTEGER,
    conditions          TEXT,

    -- Offensive (mutually exclusive with damage_per_second)
    base_damage         INTEGER,
    explosive_damage    INTEGER,
    damage_per_second   NUMERIC(8, 2),

    -- Movement types (Vortex, Wind, Breath)
    movement_speed      NUMERIC(8, 2)
);