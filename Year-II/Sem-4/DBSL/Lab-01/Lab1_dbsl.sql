
SQL> create table Emplyee(
  2  emp_no number(10),
  3  emp_name varchar(20),
  4  emp_address clob(50));
emp_address clob(50))
                *
ERROR at line 4:
ORA-00907: missing right parenthesis


SQL> create table Emplyee(
  2  emp_no number(10),
  3  emp_name varchar(20),
  4  emp_address varchar(50));

Table created.

SQL> insert into Employee(emp_no,emp_name,emp_address)values(1,'Rahul','Delhi');
insert into Employee(emp_no,emp_name,emp_address)values(1,'Rahul','Delhi')
            *
ERROR at line 1:
ORA-00942: table or view does not exist


SQL> insert into Emplyee(emp_no,emp_name,emp_address)values(1,'Rahul','Delhi');

1 row created.

SQL> insert into Emplyee(emp_no,emp_name,emp_address)values(2,'Arjun','Mumbai');

1 row created.

SQL> insert into Emplyee(emp_no,emp_name,emp_address)values(3,'Karthik','Bangalore');

1 row created.

SQL> insert into Emplyee(emp_no,emp_name,emp_address)values(4,'Manav','Noida');

1 row created.

SQL> insert into Emplyee(emp_no,emp_name,emp_address)values(5,'Saurav','Ahemdabad');

1 row created.

SQL> SELECT * FROM Emplyee;

    EMP_NO EMP_NAME
---------- --------------------
EMP_ADDRESS
--------------------------------------------------
         1 Rahul
Delhi

         2 Arjun
Mumbai

         3 Karthik
Bangalore


    EMP_NO EMP_NAME
---------- --------------------
EMP_ADDRESS
--------------------------------------------------
         4 Manav
Noida

         5 Saurav
Ahemdabad


SQL> select emp_name from Emplyee;

EMP_NAME
--------------------
Rahul
Arjun
Karthik
Manav
Saurav

SQL> update Emplyee set emp_address='Delhi' where emp_no=4;

1 row updated.

SQL> select emp_name from Emplyee where emp_address='Delhi';

EMP_NAME
--------------------
Rahul
Manav

SQL> alter table Emplyee add(salary decimal[(7[,2])]);
alter table Emplyee add(salary decimal[(7[,2])])
                                      *
ERROR at line 1:
ORA-00907: missing right parenthesis


SQL> alter table Emplyee add(salary decimal(7,2));

Table altered.

SQL> update Emplyee set salary = 20000.25 where emp_no = 1;

1 row updated.

SQL> update Emplyee set salary = 28000.72 where emp_no = 2;

1 row updated.

SQL> update Emplyee set salary = 30000.45 where emp_no = 3;

1 row updated.

SQL> update Emplyee set salary = 32000.25 where emp_no = 4;

1 row updated.

SQL> update Emplyee set salary = 20000.65 where emp_no = 5;

1 row updated.

SQL> describe Emplyee
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 EMP_NO                                             NUMBER(10)
 EMP_NAME                                           VARCHAR2(20)
 EMP_ADDRESS                                        VARCHAR2(50)
 SALARY                                             NUMBER(7,2)

SQL> describe Emplyee;
 Name                                      Null?    Type
 ----------------------------------------- -------- ----------------------------
 EMP_NO                                             NUMBER(10)
 EMP_NAME                                           VARCHAR2(20)
 EMP_ADDRESS                                        VARCHAR2(50)
 SALARY                                             NUMBER(7,2)

SQL> update Emplyee set emp_address = 'Mangalore' where emp_no = 2;

1 row updated.

SQL> delete from Emplyee where emp_address = 'Mangalore';

1 row deleted.

SQL> rename Emplyee to Employee1;

Table renamed.

SQL> select * from Employee1;

    EMP_NO EMP_NAME
---------- --------------------
EMP_ADDRESS                                            SALARY
-------------------------------------------------- ----------
         1 Rahul
Delhi                                                20000.25

         3 Karthik
Bangalore                                            30000.45

         4 Manav
Delhi                                                32000.25


    EMP_NO EMP_NAME
---------- --------------------
EMP_ADDRESS                                            SALARY
-------------------------------------------------- ----------
         5 Saurav
Ahemdabad                                            20000.65


SQL> drop table Employee1;

Table dropped.