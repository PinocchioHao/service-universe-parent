
CREATE TABLE IF NOT EXISTS parking_record (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              license_plate VARCHAR(20) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME DEFAULT NULL,
    fee DECIMAL(10,2) DEFAULT 0,
    paid TINYINT(1) DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_license_plate (license_plate),
    INDEX idx_end_time (end_time)
    );
