ALTER TABLE unit
    ADD COLUMN unlock_building_id BIGINT REFERENCES building(id) ON DELETE SET NULL,
    ADD COLUMN allow_building_id  BIGINT REFERENCES building(id) ON DELETE SET NULL;