-- V1__create_settings_table.sql
-- This migration script creates the 'settings' table

CREATE TABLE IF NOT EXISTS settings (
    id INTEGER PRIMARY KEY,  -- SQLite uses INTEGER as the type for primary key autoincrement columns
    data_path TEXT
);