SQL> select course_id from takes where semester = 'Fall' and year = 2009
  2  union all
  3  select course_id from takes where semester = 'Spring' and year = 2010;

COURSE_I
--------
CS-101
CS-347
CS-101
CS-347
PHY-101
CS-101
CS-101
CS-101
CS-101
CS-315
HIS-351

COURSE_I
--------
FIN-201
CS-101
CS-319
MU-199
CS-319
CS-315

17 rows selected.


SQL> select course_id from takes where semester = 'Fall' and year = 2009
  2  intersect
  3  select course_id from takes where semester = 'Spring' and year = 2010;

COURSE_I
--------
CS-101

SQL> select course_id from takes where semester = 'Fall' and year = 2009
  2  minus
  3  select course_id from takes where semester = 'Spring' and year = 2010;

COURSE_I
--------
CS-347
PHY-101

SQL> select title from course where course_id not in (select course_id from takes);

TITLE
--------------------------------------------------
Computational Biology

SQL> select course_id from section where semester = 'Fall' and year = 2009
  2  and course_id in ( select course_id from section where semester = 'Spring' and year = 2010);

COURSE_I
--------
CS-101

SQL>  select count(distinct id) as total_students
  2  from takes
  3  where course_id in (
  4  select course_id from teaches where ID = 10101
  5  );

TOTAL_STUDENTS
--------------
             6


SQL> select course_id from teaches where semester = 'Fall' and year = 2009 and course_id not in (select course_id from teaches where semester = 'Spring' and year = 2010);

COURSE_I
--------
CS-347
PHY-101

SQL> select name from student where name in (select name from instructor);

NAME
--------------------
Brandt

SQL> select name from instructor where salary > any ( select salary from instructor where dept_name = 'Biology');

NAME
--------------------
Einstein
Brandt
Wu
Gold
Kim
Singh
Katz

7 rows selected.

SQL> select dept_name from instructor group by dept_name having avg(salary)>= all ( select avg(salary)from instructor group by dept_name);

DEPT_NAME
--------------------
Physics

SQL> select dept_name from department where budget < all ( select avg(salary) from instructor);

DEPT_NAME
--------------------
History
Physics

SQL> select distinct course_id from section s1 where s1.semester = 'Fall' and s1.year = 2009 and exists ( select * from section s2 where s2.course_id = s1.course_id and s2.semester = 'Spring' and s2.year = 2010 );

COURSE_I
--------
CS-101


SQL> SELECT s.id, s.name FROM student s WHERE NOT EXISTS ( SELECT c.course_id FROM course c WHERE c.dept_name = 'Biology' MINUS SELECT t.course_id FROM takes t WHERE t.id = s.id );

no rows selected

SQL> select course_id from section where year = 2009 group by course_id having count(*) <=1;

COURSE_I
--------
BIO-101
CS-101
CS-347
EE-181
PHY-101

SQL> SELECT s.id, s.name FROM student s WHERE ( SELECT COUNT(DISTINCT t.course_id) FROM takes t JOIN course c ON t.course_id = c.course_id WHERE t.id = s.id AND c.dept_name = 'Comp. Sci.' ) >= 2;

ID    NAME
----- --------------------
00128 Zhang
12345 Shankar
45678 Levy
54321 Williams
76543 Brown
98765 Bourikas

6 rows selected.

SQL> SELECT AVG(avg_sal) AS overall_avg_salary FROM ( SELECT dept_name, AVG(salary) AS avg_sal FROM instructor GROUP BY dept_name HAVING AVG(salary) > 42000 );

OVERALL_AVG_SALARY
------------------
        77722.2222


SQL> create view all_courses as
  2  select s.course_id,s.sec_id,s.semester,s.year,s.building,s.room_number from section s
  3  join course c on s.course_id = c.course_id
  4  where c.dept_name = 'Physics'
  5  and s.semester = 'Fall'
  6  and s.year = 2009;

View created.

SQL> select * from all_courses;

COURSE_I SEC_ID   SEMEST       YEAR BUILDING        ROOM_NU
-------- -------- ------ ---------- --------------- -------
PHY-101  1        Fall         2009 Watson          100


SQL> CREATE VIEW department_total_salary AS SELECT dept_name, SUM(salary) AS total_salary FROM instructor GROUP BY dept_name;

View created.

SQL> select * from department_total_salary;

DEPT_NAME            TOTAL_SALARY
-------------------- ------------
Comp. Sci.                 232000
Biology                     72000
History                    122000
Finance                    170000
Elec. Eng.                  80000
Music                       40000
Physics                    182000

7 rows selected.