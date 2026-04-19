CREATE TYPE item_category AS ENUM (
    'ARMOUR',
    'ENCHANTED_ITEM',
    'TALISMAN',
    'WEAPON',
    'ARCANE_ITEM'
);

CREATE TYPE item_rarity AS ENUM (
    'COMMON',
    'UNCOMMON',
    'RARE',
    'LEGENDARY',
    'CRAFTED'
);

CREATE TABLE item (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(150) NOT NULL UNIQUE,
    slug            VARCHAR(150) NOT NULL UNIQUE,
    picture         VARCHAR(255),
    category        item_category NOT NULL,
    rarity          item_rarity NOT NULL,
    effect          TEXT,

    -- Race restriction (null = available to all)
    race_id         BIGINT REFERENCES race(id) ON DELETE SET NULL
);

-- Item ↔ Abilities granted
CREATE TABLE item_ability (
    item_id     BIGINT NOT NULL REFERENCES item(id) ON DELETE CASCADE,
    ability_id  BIGINT NOT NULL REFERENCES ability(id) ON DELETE CASCADE,
    PRIMARY KEY (item_id, ability_id)
);

-- Item ↔ Spells granted
CREATE TABLE item_spell (
    item_id     BIGINT NOT NULL REFERENCES item(id) ON DELETE CASCADE,
    spell_id    BIGINT NOT NULL REFERENCES spell(id) ON DELETE CASCADE,
    PRIMARY KEY (item_id, spell_id)
);

-- Unit ↔ Items (for unit-specific restrictions)
CREATE TABLE unit_item (
    unit_id     BIGINT NOT NULL REFERENCES unit(id) ON DELETE CASCADE,
    item_id     BIGINT NOT NULL REFERENCES item(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_id, item_id)
);