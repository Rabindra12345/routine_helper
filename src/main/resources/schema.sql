DROP TABLE IF EXISTS routine;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS `group`;

CREATE TABLE teacher (
    teacher_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(100),
    hire_date DATE
);

CREATE TABLE `group` (
    group_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    details TEXT
);

CREATE TABLE routine (
    routine_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    routine_date DATE NOT NULL,
    teacher_id BIGINT NOT NULL,
    group_id BIGINT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teacher (teacher_id),
    FOREIGN KEY (group_id) REFERENCES `group` (group_id)
);
