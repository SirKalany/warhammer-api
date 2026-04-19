CREATE TYPE unit_category_type AS ENUM (
    'REGULAR',
    'LEGENDARY',
    'LANDMARK',
    'RENOWNED',
    'RAISED'
);

ALTER TABLE unit
    ADD COLUMN category_type unit_category_type NOT NULL DEFAULT 'REGULAR';