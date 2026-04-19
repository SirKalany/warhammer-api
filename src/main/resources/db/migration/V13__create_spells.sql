CREATE TABLE lore_of_magic (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(150) NOT NULL UNIQUE,
    slug        VARCHAR(150) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE spell (
    id              BIGSERIAL PRIMARY KEY,
    lore_id         BIGINT NOT NULL REFERENCES lore_of_magic(id),
    name            VARCHAR(150) NOT NULL,
    slug            VARCHAR(150) NOT NULL UNIQUE,
    type            ability_type NOT NULL,

    -- Base values
    effect              TEXT,
    target              VARCHAR(255),
    range               NUMERIC(8, 2),
    radius              NUMERIC(8, 2),
    duration            NUMERIC(8, 2),
    cooldown            NUMERIC(8, 2),
    affected_units      INTEGER,
    uses                INTEGER,
    conditions          TEXT,
    base_damage         INTEGER,
    explosive_damage    INTEGER,
    damage_per_second   NUMERIC(8, 2),
    movement_speed      NUMERIC(8, 2),
    cost                INTEGER,
    miscast_chance      NUMERIC(5, 2),

    -- Overcast values (null = same as base, no change)
    overcast_effect             TEXT,
    overcast_target             VARCHAR(255),
    overcast_range              NUMERIC(8, 2),
    overcast_radius             NUMERIC(8, 2),
    overcast_duration           NUMERIC(8, 2),
    overcast_cooldown           NUMERIC(8, 2),
    overcast_affected_units     INTEGER,
    overcast_base_damage        INTEGER,
    overcast_explosive_damage   INTEGER,
    overcast_damage_per_second  NUMERIC(8, 2),
    overcast_movement_speed     NUMERIC(8, 2),
    overcast_cost               INTEGER,
    overcast_miscast_chance     NUMERIC(5, 2)
);