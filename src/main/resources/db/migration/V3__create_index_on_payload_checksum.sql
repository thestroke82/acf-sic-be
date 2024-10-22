-- V3 Flyway script to create an index on payload_checksum in raw_data table

-- Create a unique index on the payload_checksum column for faster retrieval
CREATE UNIQUE INDEX idx_payload_checksum ON raw_data(payload_checksum);