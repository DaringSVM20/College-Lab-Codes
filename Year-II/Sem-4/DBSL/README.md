# Database Systems Lab

Semester 4 - MIT Manipal, Data Science & Engineering.

## What this covers

Database Systems lab. SQL scripts working through table creation and constraints, inserts and updates, joins, aggregate and nested queries, and PL/SQL procedures with cursors. The mini project is a Course Management System: a relational schema plus an Express API and a React front end on top of it.

Topics: DDL, DML, joins, aggregate and nested queries, PL/SQL cursors, ER modelling, full-stack mini project.

## Contents

### Lab 1 and Lab 2 - DDL, DML and joins

The first two SQL scripts. Lab 1 creates and populates an Employee table and alters it to add a salary column. Lab 2 is substantially larger: a Department and Employee schema with a foreign key between them, bulk inserts, then queries exercising joins, aggregates and grouping. Also includes smallRelationsUniv.sql, the standard sample university schema used as a scratch database for later exercises.

### Lab 3 and Lab 4 - nested queries and updates

Lab 3 and Lab 4 continue into nested and correlated subqueries and conditional UPDATE statements over a student schema. Also includes the endsem question set, which asks for maximum-enrolment queries, credit-filtered joins and a PL/SQL cursor procedure that adjusts CGPA in bands.

## Folders

- [`Lab-01/`](Lab-01) - 1 SQL script, 1 PDF
- [`Lab-02/`](Lab-02) - 1 SQL script, 1 PDF
- [`Lab-03/`](Lab-03) - 1 SQL script
- [`sample-schema/`](sample-schema) - 1 SQL script

## Requirements

PostgreSQL (or Oracle - a few scripts use Oracle-style syntax such as the add-column form in Lab 1). The mini project needs Node.js 18+ and npm; its node_modules are not committed, so run npm install in each of backend/ and frontend/ first.

## How to run

```sh
psql -d labdb -f Lab1_dbsl.sql

# mini project
cd mini-project/application/backend && npm install && npm start
cd mini-project/application/frontend && npm install && npm run dev
```

`Lab-Manual.pdf` in this folder is the lab manual these exercises were set from.

---

Part of my college lab work, shared openly - juniors and classmates are welcome
to use it.
