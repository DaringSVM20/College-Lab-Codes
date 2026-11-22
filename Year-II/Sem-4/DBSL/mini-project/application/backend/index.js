const express = require("express");
const cors    = require("cors");
const db      = require("./db");

const app  = express();
app.use(cors());
app.use(express.json());

// ============================================================
// AUTH
// ============================================================

// POST /api/login
// Body: { email, password, role: "student" | "instructor" }
app.post("/api/login", async (req, res) => {
  const { email, password, role } = req.body;
  const table = role === "instructor" ? "instructor" : "student";
  const idCol = role === "instructor" ? "instructor_id" : "student_id";
  try {
    const [rows] = await db.query(
      `SELECT ${idCol} AS id, name, email FROM ${table} WHERE email = ? AND password = ?`,
      [email, password]
    );
    if (rows.length === 0) return res.status(401).json({ error: "Invalid credentials" });
    res.json({ user: { ...rows[0], role } });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// ============================================================
// COURSES
// ============================================================

// GET /api/courses — all courses with instructor name
app.get("/api/courses", async (req, res) => {
  try {
    const [rows] = await db.query(`
      SELECT c.course_id, c.course_name, c.credits, c.instructor_id, i.name AS instructor_name
      FROM course c
      JOIN instructor i ON c.instructor_id = i.instructor_id
      ORDER BY c.course_name
    `);
    res.json(rows);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// GET /api/courses/instructor/:id — courses by instructor
app.get("/api/courses/instructor/:id", async (req, res) => {
  try {
    const [rows] = await db.query(
      `SELECT * FROM course WHERE instructor_id = ?`, [req.params.id]
    );
    res.json(rows);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// GET /api/courses/student/:id — courses a student is enrolled in
app.get("/api/courses/student/:id", async (req, res) => {
  try {
    const [rows] = await db.query(`
      SELECT c.course_id, c.course_name, c.credits, i.name AS instructor_name
      FROM enrollment e
      JOIN course c     ON e.course_id = c.course_id
      JOIN instructor i ON c.instructor_id = i.instructor_id
      WHERE e.student_id = ?
    `, [req.params.id]);
    res.json(rows);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// POST /api/courses — create course (instructor)
app.post("/api/courses", async (req, res) => {
  const { course_name, credits, instructor_id } = req.body;
  try {
    const [result] = await db.query(
      `INSERT INTO course (course_name, credits, instructor_id) VALUES (?, ?, ?)`,
      [course_name, credits, instructor_id]
    );
    res.json({ course_id: result.insertId, course_name, credits, instructor_id });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// ============================================================
// ENROLLMENT
// ============================================================

// POST /api/enroll — uses stored procedure
app.post("/api/enroll", async (req, res) => {
  const { student_id, course_id } = req.body;
  try {
    await db.query(`CALL EnrollStudent(?, ?)`, [student_id, course_id]);
    res.json({ message: "Enrolled successfully" });
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// ============================================================
// ASSIGNMENTS
// ============================================================

// GET /api/assignments/course/:id
app.get("/api/assignments/course/:id", async (req, res) => {
  try {
    const [rows] = await db.query(
      `SELECT * FROM assignment WHERE course_id = ? ORDER BY due_date`,
      [req.params.id]
    );
    res.json(rows);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// GET /api/assignments/student/:id — all assignments from enrolled courses
app.get("/api/assignments/student/:id", async (req, res) => {
  try {
    const [rows] = await db.query(`
      SELECT a.assignment_id, a.title, a.due_date, c.course_name,
             s.submission_id,
             IF(s.submission_id IS NOT NULL, 1, 0) AS submitted
      FROM enrollment e
      JOIN course c     ON e.course_id = c.course_id
      JOIN assignment a ON a.course_id = c.course_id
      LEFT JOIN submission s ON s.assignment_id = a.assignment_id AND s.student_id = e.student_id
      WHERE e.student_id = ?
      ORDER BY a.due_date
    `, [req.params.id]);
    res.json(rows);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// GET /api/assignments/instructor/:id — all assignments for courses taught by instructor
app.get("/api/assignments/instructor/:id", async (req, res) => {
  try {
    const [rows] = await db.query(`
      SELECT a.assignment_id, a.title, a.due_date, c.course_name
      FROM assignment a
      JOIN course c ON a.course_id = c.course_id
      WHERE c.instructor_id = ?
      ORDER BY a.due_date
    `, [req.params.id]);
    res.json(rows);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// POST /api/assignments — create assignment (instructor)
app.post("/api/assignments", async (req, res) => {
  const { title, due_date, course_id } = req.body;
  try {
    const [result] = await db.query(
      `INSERT INTO assignment (title, due_date, course_id) VALUES (?, ?, ?)`,
      [title, due_date, course_id]
    );
    res.json({ assignment_id: result.insertId, title, due_date, course_id });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// ============================================================
// SUBMISSIONS
// ============================================================

// POST /api/submit — uses stored procedure
app.post("/api/submit", async (req, res) => {
  const { student_id, assignment_id } = req.body;
  try {
    await db.query(`CALL SubmitAssignment(?, ?)`, [student_id, assignment_id]);
    res.json({ message: "Submitted successfully" });
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// ============================================================
// GRADES
// ============================================================

// GET /api/grades/student/:id — uses stored procedure
app.get("/api/grades/student/:id", async (req, res) => {
  try {
    const [rows] = await db.query(`CALL GetStudentGrades(?)`, [req.params.id]);
    res.json(rows[0]);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// GET /api/grades/instructor/:id — uses stored procedure
app.get("/api/grades/instructor/:id", async (req, res) => {
  try {
    const [rows] = await db.query(`CALL GetInstructorSubmissions(?)`, [req.params.id]);
    res.json(rows[0]);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// POST /api/grade — grade or update a submission (uses stored procedure with TRANSACTION)
app.post("/api/grade", async (req, res) => {
  const { submission_id, marks, feedback } = req.body;
  try {
    await db.query(`CALL GradeSubmission(?, ?, ?)`, [submission_id, marks, feedback]);
    res.json({ message: "Graded successfully" });
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// ============================================================
// START
// ============================================================
const PORT = 5000;
app.listen(PORT, () => console.log(`CMS Backend running on http://localhost:${PORT}`));