
SQL> select course_id, count(id) as num_students
  2  from takes
  3  group by course_id;

COURSE_I NUM_STUDENTS
-------- ------------
FIN-201             1
CS-190              2
MU-199              1
CS-101              7
HIS-351             1
CS-319              2
CS-347              2
PHY-101             1
EE-181              1
CS-315              2
BIO-101             1

COURSE_I NUM_STUDENTS
-------- ------------
BIO-301             1

12 rows selected.


SQL> select dept_name
  2  from (
  3  select dept_name, count(id) as count
  4  from course natural join takes
  5  group by dept_name, course_id
  6  ) x
  7  group by dept_name
  8  having avg(count) > 10;

no rows selected



SQL> SELECT dept_name, COUNT(course_id) AS total_courses
  2  FROM course
  3  GROUP BY dept_name;

DEPT_NAME            TOTAL_COURSES
-------------------- -------------
Comp. Sci.                       5
Biology                          3
History                          1
Elec. Eng.                       1
Finance                          1
Music                            1
Physics                          1

7 rows selected.



SQL> SELECT dept_name, AVG(salary) AS avg_salary
  2  FROM instructor
  3  GROUP BY dept_name
  4  HAVING AVG(salary) > 42000;

DEPT_NAME            AVG_SALARY
-------------------- ----------
Comp. Sci.           77333.3333
Biology                   72000
History                   61000
Finance                   85000
Elec. Eng.                80000
Physics                   91000

6 rows selected.



SQL> SELECT course_id, sec_id, COUNT(ID) AS enrollment
  2  FROM takes
  3  WHERE semester = 'Spring' AND year = 2009
  4  GROUP BY course_id, sec_id;

COURSE_I SEC_ID   ENROLLMENT
-------- -------- ----------
CS-190   2                 2
EE-181   1                 1




SQL> SELECT DISTINCT course_id
  2  FROM prereq
  3  ORDER BY course_id ASC;

COURSE_I
--------
BIO-301
BIO-399
CS-190
CS-315
CS-319
CS-347
EE-181

7 rows selected.



SQL> SELECT *
  2  FROM instructor
  3  ORDER BY salary DESC;

ID    NAME                 DEPT_NAME                SALARY
----- -------------------- -------------------- ----------
22222 Einstein             Physics                   95000
83821 Brandt               Comp. Sci.                92000
12121 Wu                   Finance                   90000
33456 Gold                 Physics                   87000
98345 Kim                  Elec. Eng.                80000
76543 Singh                Finance                   80000
45565 Katz                 Comp. Sci.                75000
76766 Crick                Biology                   72000
10101 Srinivasan           Comp. Sci.                65000
58583 Califieri            History                   62000
32343 El Said              History                   60000

ID    NAME                 DEPT_NAME                SALARY
----- -------------------- -------------------- ----------
15151 Mozart               Music                     40000

12 rows selected.



SQL> SELECT MAX(total_salary)
  2  FROM (
  3      SELECT dept_name, SUM(salary) AS total_salary
  4      FROM instructor
  5      GROUP BY dept_name
  6  ) x;

MAX(TOTAL_SALARY)
-----------------
           232000



SQL> SELECT dept_name, AVG(salary) AS avg_salary
  2  FROM instructor
  3  GROUP BY dept_name
  4  HAVING AVG(salary) > 42000;

DEPT_NAME            AVG_SALARY
-------------------- ----------
Comp. Sci.           77333.3333
Biology                   72000
History                   61000
Finance                   85000
Elec. Eng.                80000
Physics                   91000

6 rows selected.





SQL> SELECT course_id, sec_id
  2  FROM takes
  3  WHERE semester = 'Spring' AND year = 2010
  4  GROUP BY course_id, sec_id
  5  HAVING COUNT(ID) = (
  6      SELECT MAX(cnt)
  7      FROM (
  8          SELECT COUNT(ID) AS cnt
  9          FROM takes
 10          WHERE semester = 'Spring' AND year = 2010
 11          GROUP BY course_id, sec_id
 12      ) x
 13  );

COURSE_I SEC_ID
-------- --------
CS-315   1



SQL> SELECT i.name
  2  FROM instructor i
  3  WHERE NOT EXISTS (
  4      SELECT s.ID
  5      FROM student s
  6      WHERE s.dept_name = 'Comp. Sci.'
  7        AND NOT EXISTS (
  8            SELECT *
  9            FROM teaches t
 10            JOIN takes tk
 11              ON t.course_id = tk.course_id
 12             AND t.sec_id = tk.sec_id
 13             AND t.semester = tk.semester
 14             AND t.year = tk.year
 15            WHERE t.ID = i.ID
 16              AND tk.ID = s.ID
 17        )
 18  );

NAME
--------------------
Srinivasan



SQL> SELECT dept_name, AVG(salary) AS avg_salary
  2  FROM instructor
  3  GROUP BY dept_name
  4  HAVING AVG(salary) > 50000
  5     AND COUNT(ID) > 5;

no rows selected



SQL> WITH max_budget AS (
  2      SELECT MAX(budget) AS max_bud
  3      FROM department
  4  )
  5  SELECT dept_name, budget
  6  FROM department, max_budget
  7  WHERE department.budget = max_budget.max_bud;

DEPT_NAME                BUDGET
-------------------- ----------
Finance                  120000


SQL> WITH dept_total AS (
  2      SELECT dept_name, SUM(salary) AS total_salary
  3      FROM instructor
  4      GROUP BY dept_name
  5  ),
  6  avg_total AS (
  7      SELECT AVG(total_salary) AS avg_salary
  8      FROM dept_total
  9  )
 10  SELECT dept_name
 11  FROM dept_total, avg_total
 12  WHERE dept_total.total_salary > avg_total.avg_salary;

DEPT_NAME
--------------------
Physics
Finance
Comp. Sci.


SQL> SAVEPOINT before_transfer;

Savepoint created.

SQL>
SQL> UPDATE student
  2  SET dept_name = 'IT'
  3  WHERE dept_name = 'Comp. Sci.';
UPDATE student
*
ERROR at line 1:
ORA-02291: integrity constraint (DS17LAB12.SYS_C0010172) violated - parent key
not found


SQL>
SQL> ROLLBACK TO before_transfer;

Rollback complete.



SQL>
SQL> ROLLBACK TO before_transfer;

Rollback complete.

SQL>
SQL> COMMIT;

Commit complete.

SQL> UPDATE instructor
  2  SET salary =
  3      CASE
  4          WHEN salary > 100000 THEN salary * 1.03
  5          ELSE salary * 1.05
  6      END;

12 rows updated.








