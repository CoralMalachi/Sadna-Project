CREATE TABLE IF NOT EXISTS recording ( -- replicate (verbose)
    id                  SERIAL,
    gid                 CHAR(36) NOT NULL,
    name                VARCHAR(255) NOT NULL,
    artist_credit       INTEGER NOT NULL, -- references artist_credit.id
    length              INTEGER CHECK (length IS NULL OR length > 0),
    comment             VARCHAR(255) NOT NULL DEFAULT '',
    edits_pending       INTEGER NOT NULL DEFAULT 0 CHECK (edits_pending >= 0),
    last_updated        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    video               CHAR(1) NOT NULL DEFAULT FALSE
) CHARACTER SET utf8 COLLATE utf8_general_ci;
