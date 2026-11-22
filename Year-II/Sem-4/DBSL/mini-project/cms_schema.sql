-- ============================================================
-- Web-Based Course Management System
-- CMS Database Schema + Stored Procedures
-- Run this in phpMyAdmin on database: cms_db
-- ============================================================

CREATE DATABASE IF NOT EXISTS cms_db;
USE cms_db;

-- Drop tables if they exist (for clean re-runs)
DROP TABLE IF EXISTS grade;
DROP TABLE IF EXISTS submission;
DROP TABLE IF EXISTS assignment;
DROP TABLE IF EXISTS enrollment;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS instructor;

-- ============================================================
-- TABLES
-- ============================================================

CREATE TABLE instructor (
    instructor_id INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    password      VARCHAR(100) NOT NULL DEFAULT 'pass123'
);

CREATE TABLE student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    password   VARCHAR(100) NOT NULL DEFAULT 'pass123'
);

CREATE TABLE course (
    course_id     INT AUTO_INCREMENT PRIMARY KEY,
    course_name   VARCHAR(150) NOT NULL,
    credits       INT NOT NULL CHECK (credits > 0),
    instructor_id INT NOT NULL,
    FOREIGN KEY (instructor_id) REFERENCES instructor(instructor_id) ON DELETE CASCADE
);

CREATE TABLE enrollment (
    student_id INT NOT NULL,
    course_id  INT NOT NULL,
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id)  REFERENCES course(course_id)  ON DELETE CASCADE
);

CREATE TABLE assignment (
    assignment_id INT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(200) NOT NULL,
    due_date      DATE NOT NULL,
    course_id     INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE
);

CREATE TABLE submission (
    submission_id INT AUTO_INCREMENT PRIMARY KEY,
    submit_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    student_id    INT NOT NULL,
    assignment_id INT NOT NULL,
    UNIQUE KEY uq_student_assignment (student_id, assignment_id),
    FOREIGN KEY (student_id)    REFERENCES student(student_id)       ON DELETE CASCADE,
    FOREIGN KEY (assignment_id) REFERENCES assignment(assignment_id) ON DELETE CASCADE
);

CREATE TABLE grade (
    grade_id      INT AUTO_INCREMENT PRIMARY KEY,
    marks         FLOAT NOT NULL CHECK (marks >= 0 AND marks <= 100),
    feedback      TEXT,
    submission_id INT NOT NULL UNIQUE,
    FOREIGN KEY (submission_id) REFERENCES submission(submission_id) ON DELETE CASCADE
);

-- ============================================================
-- SEED DATA
-- ============================================================

INSERT INTO instructor (name, email, password) VALUES
('Dr. Ramesh Kumar',  'ramesh@cms.edu',  'pass123'),
('Prof. Anita Singh', 'anita@cms.edu',   'pass123');

INSERT INTO student (name, email, password) VALUES
('Shlok Verma',      'shlok@cms.edu',   'pass123'),
('Isha Telikicherla','isha@cms.edu',    'pass123'),
('Arjun Mehta',      'arjun@cms.edu',   'pass123'),
('Priya Nair',       'priya@cms.edu',   'pass123'),
('Rahul Das',        'rahul@cms.edu',   'pass123');

INSERT INTO course (course_name, credits, instructor_id) VALUES
('Database Management Systems', 4, 1),
('Operating Systems',           3, 1),
('Artificial Intelligence',     4, 2);

INSERT INTO enrollment (student_id, course_id) VALUES
(1, 1), (1, 2),
(2, 1), (2, 3),
(3, 1), (3, 2), (3, 3),
(4, 3),
(5, 2);

INSERT INTO assignment (title, due_date, course_id) VALUES
('ER Diagram Design',          '2025-03-15', 1),
('SQL Query Practice Set',     '2025-03-22', 1),
('Process Scheduling Report',  '2025-03-18', 2),
('AI Search Algorithms Lab',   '2025-03-25', 3);

INSERT INTO submission (student_id, assignment_id) VALUES
(1, 1), (1, 2),
(2, 1),
(3, 1), (3, 3),
(5, 3);

INSERT INTO grade (marks, feedback, submission_id) VALUES
(88, 'Good work on the ERD. Relationships well defined.',   1),
(75, 'Queries mostly correct, watch out for NULL handling.', 2),
(91, 'Excellent submission!',                                3);

-- ============================================================
-- STORED PROCEDURES
-- ============================================================

DELIMITER $$

-- 1. Enroll a student in a course
DROP PROCEDURE IF EXISTS EnrollStudent $$
CREATE PROCEDURE EnrollStudent(IN p_student_id INT, IN p_course_id INT)
BEGIN
    DECLARE already_enrolled INT DEFAULT 0;
    SELECT COUNT(*) INTO already_enrolled
    FROM enrollment
    WHERE student_id = p_student_id AND course_id = p_course_id;

    IF already_enrolled > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Student already enrolled in this course.';
    ELSE
        INSERT INTO enrollment (student_id, course_id) VALUES (p_student_id, p_course_id);
    END IF;
END $$

-- 2. Submit an assignment
DROP PROCEDURE IF EXISTS SubmitAssignment $$
CREATE PROCEDURE SubmitAssignment(IN p_student_id INT, IN p_assignment_id INT)
BEGIN
    DECLARE already_submitted INT DEFAULT 0;
    SELECT COUNT(*) INTO already_submitted
    FROM submission
    WHERE student_id = p_student_id AND assignment_id = p_assignment_id;

    IF already_submitted > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Assignment already submitted.';
    ELSE
        INSERT INTO submission (student_id, assignment_id) VALUES (p_student_id, p_assignment_id);
    END IF;
END $$

-- 3. Grade a submission (uses TRANSACTION)
DROP PROCEDURE IF EXISTS GradeSubmission $$
CREATE PROCEDURE GradeSubmission(IN p_submission_id INT, IN p_marks FLOAT, IN p_feedback TEXT)
BEGIN
    DECLARE existing_grade INT DEFAULT 0;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;
        RESIGNAL;
    END;

    START TRANSACTION;

    SELECT COUNT(*) INTO existing_grade FROM grade WHERE submission_id = p_submission_id;

    IF existing_grade > 0 THEN
        UPDATE grade
        SET marks = p_marks, feedback = p_feedback
        WHERE submission_id = p_submission_id;
    ELSE
        INSERT INTO grade (marks, feedback, submission_id)
        VALUES (p_marks, p_feedback, p_submission_id);
    END IF;

    COMMIT;
END $$

-- 4. Get all grades for a student (JOIN across 5 tables)
DROP PROCEDURE IF EXISTS GetStudentGrades $$
CREATE PROCEDURE GetStudentGrades(IN p_student_id INT)
BEGIN
    SELECT
        c.course_name,
        a.title         AS assignment_title,
        a.due_date,
        sub.submit_date,
        g.marks,
        g.feedback
    FROM submission sub
    JOIN assignment a ON sub.assignment_id = a.assignment_id
    JOIN course c     ON a.course_id = c.course_id
    LEFT JOIN grade g ON sub.submission_id = g.submission_id
    WHERE sub.student_id = p_student_id
    ORDER BY c.course_name, a.due_date;
END $$

-- 5. Get all submissions for an instructor to grade
DROP PROCEDURE IF EXISTS GetInstructorSubmissions $$
CREATE PROCEDURE GetInstructorSubmissions(IN p_instructor_id INT)
BEGIN
    SELECT
        sub.submission_id,
        st.name          AS student_name,
        a.title          AS assignment_title,
        c.course_name,
        sub.submit_date,
        g.marks,
        g.feedback
    FROM submission sub
    JOIN student st   ON sub.student_id    = st.student_id
    JOIN assignment a ON sub.assignment_id = a.assignment_id
    JOIN course c     ON a.course_id       = c.course_id
    LEFT JOIN grade g ON sub.submission_id = g.submission_id
    WHERE c.instructor_id = p_instructor_id
    ORDER BY g.marks IS NOT NULL, sub.submit_date DESC;
END $$

DELIMITER ;

-- ============================================================
-- VERIFY
-- ============================================================
SELECT 'Schema created successfully!' AS status;
