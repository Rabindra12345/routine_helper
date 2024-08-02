INSERT INTO teacher (name, email, department, hire_date) VALUES
('Nabaraj', 'nabaraj@routinehelper.com', 'Mathematics', '2010-08-15'),
('Avinash', 'avinash@routinehelper.com', 'Science', '2012-06-25'),
('Rabindra', 'rabindra@routinehelper.com', 'History', '2015-03-10');

INSERT INTO `group` (name, details) VALUES
('Group A', 'Details about Group A'),
('Group B', 'Details about Group B'),
('Group C', 'Details about Group C');

INSERT INTO routine (start_time, end_time, routine_date, teacher_id, group_id) VALUES
('09:00:00', '10:00:00', '2023-09-01', 1, 1),
('10:00:00', '11:00:00', '2023-09-01', 2, 2),
('11:00:00', '12:00:00', '2023-09-01', 3, 3),
('09:00:00', '10:30:00', '2023-09-02', 1, 2),
('10:00:00', '12:30:00', '2023-09-02', 2, 3),
('11:00:00', '12:00:00', '2023-09-02', 3, 1);
