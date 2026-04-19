-- Unit ↔ Unit Attributes (many-to-many)
CREATE TABLE unit_unit_attribute (
    unit_id             BIGINT NOT NULL REFERENCES unit(id) ON DELETE CASCADE,
    unit_attribute_id   BIGINT NOT NULL REFERENCES unit_attribute(id) ON DELETE CASCADE,
    PRIMARY KEY (unit_id, unit_attribute_id)
);

-- Attributes text lines (max 4, ordered)
CREATE TABLE unit_attribute_line (
    id          BIGSERIAL PRIMARY KEY,
    unit_id     BIGINT NOT NULL REFERENCES unit(id) ON DELETE CASCADE,
    position    SMALLINT NOT NULL CHECK (position BETWEEN 1 AND 4),
    content     TEXT NOT NULL,
    UNIQUE (unit_id, position)
);