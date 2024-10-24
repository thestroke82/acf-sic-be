-- Create the 'vehicle' table
CREATE TABLE vehicle (
    id INTEGER PRIMARY KEY AUTOINCREMENT,          -- Primary key with INTEGER auto-increment
    plate VARCHAR(255) NOT NULL,                   -- Non-nullable plate
    owner VARCHAR(255),                            -- Owner field, nullable
    insurance_coverage_end TIMESTAMP,              -- Insurance coverage end date
    registrations JSON,                            -- Registrations stored as JSON
    insurance_policies JSON,                       -- Insurance policies stored as JSON
    risk_certificates JSON,                        -- Risk certificates stored as JSON
    raw_data_ids VARCHAR(255),                     -- Comma-separated list of raw data IDs

    -- Fields inherited from AuditedEntityBase
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,  -- Creation timestamp
    last_updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,  -- Last updated timestamp
    created_by VARCHAR(255),                        -- Created by user
    last_updated_by VARCHAR(255)                  -- Last updated by user
);

-- Add indexes for searchable fields (plate, owner)
CREATE INDEX idx_vehicle_plate ON vehicle (plate);
CREATE INDEX idx_vehicle_owner ON vehicle (owner);
