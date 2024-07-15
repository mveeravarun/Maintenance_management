

USE maintenance_management;

CREATE TABLE Equipment (
    equipment_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    purchase_date DATE,
    status ENUM('active', 'inactive') DEFAULT 'active'
);

CREATE TABLE MaintenanceSchedule (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,
    equipment_id INT,
    scheduled_date DATE,
    status ENUM('scheduled', 'completed', 'cancelled') DEFAULT 'scheduled',
    FOREIGN KEY (equipment_id) REFERENCES Equipment(equipment_id)
);

CREATE TABLE MaintenanceLog (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    equipment_id INT,
    maintenance_date DATE,
    description TEXT,
    technician_name VARCHAR(255),
    FOREIGN KEY (equipment_id) REFERENCES Equipment(equipment_id)
);
