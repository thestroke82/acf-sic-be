-- V2__Delete_Old_Settings_And_Create_New_Settings_Table.sql

-- Drop the old settings table if it exists
DROP TABLE IF EXISTS settings;

-- Create the new settings_entity table
CREATE TABLE settings (
    id BIGINT PRIMARY KEY NOT NULL,
    data_path VARCHAR(255),

    -- Fields inherited from AuditedEntityBase
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(255),
    last_updated_by VARCHAR(255)
);