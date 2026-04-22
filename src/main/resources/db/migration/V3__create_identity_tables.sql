-- Races
CREATE TABLE race (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL UNIQUE,
    slug    VARCHAR(100) NOT NULL UNIQUE
);

-- Lores of Magic
CREATE TABLE lore_of_magic (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(150) NOT NULL UNIQUE,
    slug    VARCHAR(150) NOT NULL UNIQUE
);

-- Abilities
CREATE TABLE ability (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(150) NOT NULL UNIQUE,
    slug    VARCHAR(150) NOT NULL UNIQUE,
    type    ability_type NOT NULL
);

-- Spells
CREATE TABLE spell (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(150) NOT NULL UNIQUE,
    slug    VARCHAR(150) NOT NULL UNIQUE,
    type    ability_type NOT NULL,
    lore_id BIGINT NOT NULL REFERENCES lore_of_magic(id)
);

-- Items
CREATE TABLE item (
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(150) NOT NULL UNIQUE,
    slug     VARCHAR(150) NOT NULL UNIQUE,
    category item_category NOT NULL,
    rarity   item_rarity NOT NULL
);

-- Imbuements
CREATE TABLE imbuement (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    slug VARCHAR(100) NOT NULL UNIQUE
);

-- Unit Attributes (Flying, Daemonic etc.)
CREATE TABLE unit_attribute (
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Factions
CREATE TABLE faction (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(150) NOT NULL UNIQUE,
    slug    VARCHAR(150) NOT NULL UNIQUE,
    race_id BIGINT NOT NULL REFERENCES race(id)
);

-- Building Chains
CREATE TABLE building_chain (
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(150) NOT NULL UNIQUE,
    slug     VARCHAR(150) NOT NULL UNIQUE,
    race_id  BIGINT NOT NULL REFERENCES race(id),
    category building_category NOT NULL
);

-- Buildings
CREATE TABLE building (
    id                BIGSERIAL PRIMARY KEY,
    name              VARCHAR(150) NOT NULL UNIQUE,
    slug              VARCHAR(150) NOT NULL UNIQUE,
    race_id           BIGINT NOT NULL REFERENCES race(id),
    building_chain_id BIGINT REFERENCES building_chain(id) ON DELETE SET NULL,
    category          building_category NOT NULL
);

-- Units
CREATE TABLE unit (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(150) NOT NULL,
    slug    VARCHAR(150) NOT NULL UNIQUE,
    race_id BIGINT NOT NULL REFERENCES race(id)
);