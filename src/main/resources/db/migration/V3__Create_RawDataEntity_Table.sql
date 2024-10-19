-- V3__Create_RawDataEntity_Table.sql

-- Create table for RawDataEntity
CREATE TABLE IF NOT EXISTS raw_data (
    id BIGINT PRIMARY KEY NOT NULL,

    -- Source type is stored as a simple VARCHAR
    source VARCHAR(50) NOT NULL,

    version INT NOT NULL,
    url VARCHAR(255),
    timestamp TIMESTAMP NOT NULL,

    -- Payload is stored as a large object
    payload TEXT,

    -- Fields inherited from AuditedEntityBase
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    last_updated_by VARCHAR(255)
);
