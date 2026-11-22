# 📚 Course Management System (CMS)

A full-stack web-based CMS built using **MySQL, Node.js (Express), and React (Vite + Tailwind)**.
Supports role-based access for **Students** and **Instructors** with real-time database operations.

---

## 🚀 Tech Stack

* **Frontend:** React + Tailwind CSS (Vite)
* **Backend:** Node.js + Express
* **Database:** MySQL (via XAMPP)
* **DB Features:** Stored Procedures, Transactions, Joins, Constraints

---

## 📂 Project Structure

```
cms-project/
├── backend/
│   ├── db.js
│   └── index.js
└── frontend/
    └── src/
```

---

## ⚙️ Setup Instructions (Lab System)

### 1. Database Setup (XAMPP)

1. Start **MySQL** in XAMPP
2. Open: `http://localhost/phpmyadmin`
3. Create DB → `cms_db`
4. Import `cms_schema.sql`

---

### 2. Backend Setup

```bash
cd backend
npm install
npm run dev
```

Runs on: `http://localhost:5000`

---

### 3. Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

Runs on: `http://localhost:5173`

---

## 🔄 System Flow

```
Frontend → Backend API → MySQL Database
```

* React sends API requests
* Express backend handles logic
* MySQL executes queries / stored procedures

---

## 🧠 Database Usage (Core Operations)

### Authentication

* Reads from `student` / `instructor` tables

### Enrollment

* Stored Procedure: `EnrollStudent`
* Updates `enrollment` table

### Assignment Submission

* Stored Procedure: `SubmitAssignment`
* Updates `submission` table

### Grading

* Stored Procedure: `GradeSubmission`
* Uses **TRANSACTION**
* Updates `grade` table

### Viewing Grades

* Stored Procedure: `GetStudentGrades`
* Uses **JOIN across 5 tables**

---

## 🗄️ Database Tables

* instructor
* student
* course
* enrollment
* assignment
* submission
* grade

---

## ⚡ Stored Procedures

* `EnrollStudent` → prevents duplicate enrollments
* `SubmitAssignment` → prevents duplicate submissions
* `GradeSubmission` → uses transaction for atomic updates
* `GetStudentGrades` → multi-table join
* `GetInstructorSubmissions` → fetch grading data

---

## 👀 Demo Guide (IMPORTANT)

### 🔹 Show Tables

1. Open phpMyAdmin
2. Select `cms_db`
3. Click any table → **Browse**

---

### 🔥 Live Demo Trick

Perform action in app → refresh table in phpMyAdmin

| Action | Table      |
| ------ | ---------- |
| Enroll | enrollment |
| Submit | submission |
| Grade  | grade      |

---

## 🎤 Demo Flow (1-Minute Pitch)

### Student:

* Login
* View courses
* Enroll → show DB update
* Submit assignment → show DB
* View grades

### Instructor:

* Login
* Create course
* Create assignment
* Grade submission → show DB

---

## 🧠 Key Concepts Used

* Foreign Keys
* Constraints (CHECK, UNIQUE)
* Stored Procedures
* Transactions
* Multi-table JOINs

---

## 🧪 Test Endpoints

* `GET /api/courses`
* `POST /api/login`
* `POST /api/enroll`
* `POST /api/submit`
* `GET /api/grades/student/:id`

---

## 🚨 Troubleshooting

* MySQL not running → Start in XAMPP
* Port issue → change backend port
* DB error → check `db.js` config
* Frontend not connecting → check API URL

---

## 🏁 Final Note

This system demonstrates a complete **full-stack workflow with strong database integration**, focusing on data integrity using stored procedures and transactions.

---
