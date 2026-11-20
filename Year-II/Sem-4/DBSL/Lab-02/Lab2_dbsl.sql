SQL> create table Employee(
  2  EmpNO number(10),
  3   EmpName varchar (20),
  4   Gender char (10),
  5   salary decimal(7,2),
  6   address varchar(50),
  7   DNo number(15));

Table created.

SQL> alter table Employee add constraint pk_emp primary key (EmpNo);

Table altered.

SQL> alter table Employee modify EmpName Varchar(20) not null;

Table altered.

SQL>  alter table Employee modify Gender char(10) not null;

Table altered.

SQL>  alter table Employee modify Salary decimal(7,2) not null;

Table altered.

SQL>  alter table Employee modify address Varchar(50) not null;
Table altered.

SQL> alter table Employee add constraint chk_gender check (Gender in ('M', 'F'));

Table altered.

SQL> create table Department(
  2  DeptNo number(20) primary key,
  3  DeptName varchar(50) unique,
  4  Location varchar(50));

Table created.

SQL> alter table Employee add constraint fk_dept foreign key (DNo) references Department(DeptNo);

Table altered.

SQL> insert into Department (DeptNo, DeptName, Location) values (10,'HR', 'Mumbai');

1 row created.

SQL> insert into Department (DeptNo, DeptName, Location) values (20,'finance', 'Delhi');

1 row created.

SQL> insert into Department (DeptNo, DeptName, Location) values (30,'IT', 'Bangalore');

1 row created.

SQL> insert into Department (DeptNo, DeptName, Location) values (40,'Marketing', 'Chennai');

1 row created.

SQL> insert into Employee (EmpNo, EmpName, Gender, salary, address, DNo) values (101, 'Amit Sharma', 'M', 55000.00, 'Andheri, Mumbai', 10);

1 row created.

SQL>  insert into Employee (EmpNo, EmpName, Gender, salary, address, DNo) values (102, 'Priya Mehta', 'F', 60000.00, 'Connaught Place, Delhi', 20);

1 row created.

SQL>  insert into Employee (EmpNo, EmpName, Gender, salary, address, DNo) values (103, 'Ravi Kumar', 'M', 75000.00, 'Whitefield, Bangalore', 30);

1 row created.

SQL>  insert into Employee (EmpNo, EmpName, Gender, salary, address, DNo) values (104, 'Sneha Iyer', 'F', 50000.00, 'T Nagar, Chennai', 40);

1 row created.

SQL>  insert into Employee (EmpNo, EmpName, Gender, salary, address, DNo) values (105, 'Arjun Singh', 'M', 65000.00, 'Powai, Mumbai', 10);

1 row created.

SQL> insert into Department (DeptNo, DeptName, Location) values (10, 'Operations', 'Hyderabad');
insert into Department (DeptNo, DeptName, Location) values (10, 'Operations', 'Hyderabad')
*
ERROR at line 1:
ORA-00001: unique constraint (DS17LAB12.SYS_C009470) violated


SQL> INSERT INTO Employee (EmpNo, EmpName, Gender, Salary, Address, DNo) VALUES (202, 'Kavita Rao', 'X', 55000.00, 'Park Street, Kolkata', 20);
INSERT INTO Employee (EmpNo, EmpName, Gender, Salary, Address, DNo) VALUES (202, 'Kavita Rao', 'X', 55000.00, 'Park Street, Kolkata', 20)
*
ERROR at line 1:
ORA-02290: check constraint (DS17LAB12.CHK_GENDER) violated

SQL> alter table Employee drop constraint fk_dept;

Table altered.

SQL> alter table Employee add constraint fk_dept foreign key (DNo) references Department(DeptNo) on delete cascade;

Table altered.

SQL> select * from Employee;

     EMPNO EMPNAME              GENDER         SALARY
---------- -------------------- ---------- ----------
ADDRESS                                                   DNO
-------------------------------------------------- ----------
       101 Amit Sharma          M               55000
Andheri, Mumbai                                            10

       102 Priya Mehta          F               60000
Connaught Place, Delhi                                     20

       103 Ravi Kumar           M               75000
Whitefield, Bangalore                                      30


     EMPNO EMPNAME              GENDER         SALARY
---------- -------------------- ---------- ----------
ADDRESS                                                   DNO
-------------------------------------------------- ----------
       104 Sneha Iyer           F               50000
T Nagar, Chennai                                           40

       105 Arjun Singh          M               65000
Powai, Mumbai                                              10

SQL> select * from Department;

    DEPTNO DEPTNAME
---------- --------------------------------------------------
LOCATION
--------------------------------------------------
        10 HR
Mumbai

        20 finance
Delhi

        30 IT
Bangalore


    DEPTNO DEPTNAME
---------- --------------------------------------------------
LOCATION
--------------------------------------------------
        40 Marketing
Chennai

SQL> alter table Employee modify salary default 10000;

Table altered.


University Database Schema:

SQL> select name, dept_name from Student;

NAME                 DEPT_NAME
-------------------- --------------------
Zhang                Comp. Sci.
Shankar              Comp. Sci.
Brandt               History
Chavez               Finance
Peltier              Physics
Levy                 Physics
Williams             Comp. Sci.
Sanchez              Music
Snow                 Physics
Brown                Comp. Sci.
Aoi                  Elec. Eng.

NAME                 DEPT_NAME
-------------------- --------------------
Bourikas             Elec. Eng.
Tanaka               Biology

13 rows selected.

SQL> select name from Instructor where dept_name = 'Comp. Sci.';

NAME
--------------------
Srinivasan
Katz
Brandt

SQL> select title from Course where dept_name = 'Comp. Sci.' and credits = 3;

TITLE
--------------------------------------------------
Robotics
Image Processing
Database System Concepts

SQL> select s.ID, s.name, c.course_id, c.title from Student s join takes t on s.ID = t.ID join Course c on t.course_id = c.course_id where s.ID = 12345;

ID    NAME                 COURSE_I
----- -------------------- --------
TITLE
--------------------------------------------------
12345 Shankar              CS-101
Intro. to Computer Science

12345 Shankar              CS-190
Game Design

12345 Shankar              CS-315
Robotics


ID    NAME                 COURSE_I
----- -------------------- --------
TITLE
--------------------------------------------------
12345 Shankar              CS-347
Database System Concepts

SQL> SELECT I.ID, I.name, I.dept_name, I.salary, D.building, D.budget FROM Instructor I JOIN Department D ON I.dept_name = D.dept_name WHERE I.salary BETWEEN 40000 AND 90000;

ID    NAME                 DEPT_NAME                SALARY BUILDING
----- -------------------- -------------------- ---------- ---------------
    BUDGET
----------
10101 Srinivasan           Comp. Sci.                65000 Taylor
    100000

12121 Wu                   Finance                   90000 Painter
    120000

15151 Mozart               Music                     40000 Packard
     80000


ID    NAME                 DEPT_NAME                SALARY BUILDING
----- -------------------- -------------------- ---------- ---------------
    BUDGET
----------
32343 El Said              History                   60000 Painter
     50000

33456 Gold                 Physics                   87000 Watson
     70000

45565 Katz                 Comp. Sci.                75000 Taylor
    100000


ID    NAME                 DEPT_NAME                SALARY BUILDING
----- -------------------- -------------------- ---------- ---------------
    BUDGET
----------
58583 Califieri            History                   62000 Painter
     50000

76543 Singh                Finance                   80000 Painter
    120000

76766 Crick                Biology                   72000 Watson
     90000


ID    NAME                 DEPT_NAME                SALARY BUILDING
----- -------------------- -------------------- ---------- ---------------
    BUDGET
----------
98345 Kim                  Elec. Eng.                80000 Taylor
     85000


10 rows selected.

SQL> SELECT I.ID FROM Instructor I LEFT JOIN Teaches T ON I.ID = T.ID WHERE T.ID IS NULL;

ID
-----
76543
58583
33456


SQL> SELECT S.name AS student_name,
  2         C.title AS course_name,
  3         T.year
  4  FROM Student S
  5  JOIN Takes T
  6      ON S.ID = T.ID
  7  JOIN Section Sec
  8      ON T.course_id = Sec.course_id
  9     AND T.sec_id = Sec.sec_id
 10     AND T.semester = Sec.semester
 11     AND T.year = Sec.year
 12  JOIN Course C
 13      ON T.course_id = C.course_id
 14  WHERE Sec.room_number = 303;

no rows selected

QL> SELECT S.name,
  2         C.course_id,
  3         C.title AS c_name
  4  FROM Student S
  5  JOIN Takes T
  6      ON S.ID = T.ID
  7  JOIN Course C
  8      ON T.course_id = C.course_id
  9  WHERE T.year = 2015;

no rows selected

SQL> SELECT I.name, I.salary AS inst_salary FROM Instructor I WHERE I.salary > ANY ( SELECT C.salary FROM Instructor C WHERE C.dept_name LIKE 'Comp. Sci.%' );

NAME                 INST_SALARY
-------------------- -----------
Einstein                   95000
Brandt                     92000
Wu                         90000
Gold                       87000
Kim                        80000
Singh                      80000
Katz                       75000
Crick                      72000

8 rows selected.


SQL> select name from Instructor where dept_name like '%ch%';

no rows selected

SQL> select name,length(name) from Student;

NAME                 LENGTH(NAME)
-------------------- ------------
Zhang                           5
Shankar                         7
Brandt                          6
Chavez                          6
Peltier                         7
Levy                            4
Williams                        8
Sanchez                         7
Snow                            4
Brown                           5
Aoi                             3

NAME                 LENGTH(NAME)
-------------------- ------------
Bourikas                        8
Tanaka                          6

13 rows selected.

SQL> select dept_name, substr(dept_name,3,3) from Department;

DEPT_NAME            SUBSTR(DEPT_
-------------------- ------------
Biology              olo
Comp. Sci.           mp.
Elec. Eng.           ec.
Finance              nan
History              sto
Music                sic
Physics              ysi

7 rows selected.


SQL> select upper(name) from Instructor;

UPPER(NAME)
--------------------
SRINIVASAN
WU
MOZART
EINSTEIN
EL SAID
GOLD
KATZ
CALIFIERI
SINGH
CRICK
BRANDT

UPPER(NAME)
--------------------
KIM

12 rows selected.

SQL> SELECT ID, name, NVL(salary, 0) FROM Instructor;

ID    NAME                 NVL(SALARY,0)
----- -------------------- -------------
10101 Srinivasan                   65000
12121 Wu                           90000
15151 Mozart                       40000
22222 Einstein                     95000
32343 El Said                      60000
33456 Gold                         87000
45565 Katz                         75000
58583 Califieri                    62000
76543 Singh                        80000
76766 Crick                        72000
83821 Brandt                       92000

ID    NAME                 NVL(SALARY,0)
----- -------------------- -------------
98345 Kim                          80000

12 rows selected.

SQL> SELECT salary, ROUND(salary/3, -2) FROM Instructor;

    SALARY ROUND(SALARY/3,-2)
---------- ------------------
     65000              21700
     90000              30000
     40000              13300
     95000              31700
     60000              20000
     87000              29000
     75000              25000
     62000              20700
     80000              26700
     72000              24000
     92000              30700

    SALARY ROUND(SALARY/3,-2)
---------- ------------------
     80000              26700

SQL> SELECT TO_CHAR(TO_DATE(dob, 'DD-MON-YYYY'), 'DD-MON-YYYY') AS format1, TO_CHAR(TO_DATE(dob, 'DD-MON-YYYY'), 'DD-MON-YY') AS format2, TO_CHAR(TO_DATE(dob, 'DD-MON-YYYY'), 'DD-MM-YY') AS format3 FROM Employee;

FORMAT1              FORMAT2            FORMAT3
-------------------- ------------------ --------
15-MAR-0090          15-MAR-90          15-03-90
22-JUL-0088          22-JUL-88          22-07-88
05-JAN-0092          05-JAN-92          05-01-92
30-SEP-0085          30-SEP-85          30-09-85


SQL> SELECT name, TO_CHAR(dob, 'YEAR') AS year_upper, TO_CHAR(dob, 'Year') AS year_capitalized, TO_CHAR(dob, 'year') AS year_lower FROM Employee;

NAME
--------------------------------------------------
YEAR_UPPER
------------------------------------------
YEAR_CAPITALIZED
------------------------------------------
YEAR_LOWER
------------------------------------------
Ramesh Kumar
NINETEEN NINETY
Nineteen Ninety
nineteen ninety


NAME
--------------------------------------------------
YEAR_UPPER
------------------------------------------
YEAR_CAPITALIZED
------------------------------------------
YEAR_LOWER
------------------------------------------
Priya Singh
NINETEEN EIGHTY-EIGHT
Nineteen Eighty-Eight
nineteen eighty-eight


NAME
--------------------------------------------------
YEAR_UPPER
------------------------------------------
YEAR_CAPITALIZED
------------------------------------------
YEAR_LOWER
------------------------------------------
Ananya Rao
NINETEEN NINETY-TWO
Nineteen Ninety-Two
nineteen ninety-two


NAME
--------------------------------------------------
YEAR_UPPER
------------------------------------------
YEAR_CAPITALIZED
------------------------------------------
YEAR_LOWER
------------------------------------------
Vikram Patel
NINETEEN EIGHTY-FIVE
Nineteen Eighty-Five
nineteen eighty-five


