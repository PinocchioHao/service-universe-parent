
CREATE TABLE IF NOT EXISTS reporting_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- Auto-increment primary key for each row
    user_id VARCHAR(50),                           -- ID of the user who submitted the report (optional)
    location VARCHAR(255) NOT NULL,                -- Location where the outage occurred (required)
    time_reported DATETIME NOT NULL,               -- When the outage was reported (required, date and time)
    description TEXT,                              -- Free-text details about the outage
    contact_details VARCHAR(255),                  -- Reporter’s contact info (e.g., phone or email)
    report_id VARCHAR(100) UNIQUE NOT NULL,        -- Public-facing unique report identifier (required, unique)
    status VARCHAR(50) NOT NULL                    -- Current status of the report (e.g., RECEIVED/IN_PROGRESS/RESTORED)
);