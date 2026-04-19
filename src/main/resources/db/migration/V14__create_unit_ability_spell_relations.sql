-- Unit ↔ Active Abilities
CREATE TABLE unit_ability (
    unit_id     BIGINT NOT NULL REFERENCES unit(id) ON DELETE CASCADE,
    ability_id  BIGINT NOT NULL REFERENCES ability(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_id, ability_id)
);

-- Unit ↔ Passive Abilities
CREATE TABLE unit_passive_ability (
    unit_id     BIGINT NOT NULL REFERENCES unit(id) ON DELETE CASCADE,
    ability_id  BIGINT NOT NULL REFERENCES ability(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_id, ability_id)
);

-- Unit ↔ Spells
CREATE TABLE unit_spell (
    unit_id     BIGINT NOT NULL REFERENCES unit(id) ON DELETE CASCADE,
    spell_id    BIGINT NOT NULL REFERENCES spell(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_id, spell_id)
);