-- V4__Create_raw_data_seq.sql

-- Create the sequence table for raw_data
CREATE TABLE raw_data_seq (
    next_val BIGINT
);

-- Insert the initial value into the sequence table
INSERT INTO raw_data_seq (next_val)
VALUES (1);
