CREATE TABLE game_version (
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL UNIQUE,
    slug    VARCHAR(100) NOT NULL UNIQUE,
    icon    VARCHAR(255)
);

INSERT INTO game_version (name, slug) VALUES ('Vanilla', 'vanilla');