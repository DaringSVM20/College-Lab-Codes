/*

Create an employee record with emp-no, name, age,
date-of-joining (year), and salary. If there is 20% hike
on salary per annum, compute the retirement year
of each employee and the salary at that time.
[standard age of retirement is 55]

*/

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

typedef struct employee {
    int emp_no;
    char name[60];
    int age;
    int year_join;
    float salary;
} Employee;

int main()
{

    Employee emp;
    int yearsLeft, retirementYear;
    double retireSalary;

    printf("Enter Employee Data:\n");

    printf("Enter Employee ID: ");
    scanf("%d", &emp.emp_no);

    printf("Enter Name: ");
    scanf(" %[^\n]", emp.name);

    printf("Enter age: ");
    scanf("%d", &emp.age);

    printf("Enter Year of Joining: ");
    scanf("%d", &emp.year_join);

    printf("Enter Current Salary: ");
    scanf("%f", &emp.salary);

    yearsLeft = 55 - emp.age;
    retirementYear = emp.year_join + yearsLeft;
    retireSalary = emp.salary + yearsLeft/4*emp.salary*0.2;

    printf("\nEmployee Record:\n");
    printf("Employee No: %d\n", emp.emp_no);
    printf("Employee Name: %s\n", emp.name);
    printf("Employee Age: %d\n", emp.age);
    printf("Employee Year of Joining: %d\n", emp.year_join);
    printf("Employee Salary: %f\n", emp.salary);

    printf("\n\nRetirement Details:\n");
    printf("Retirement Year: %d\n", retirementYear);
    printf("Retirement Salary: %.2f\n", retireSalary);

    return 0;
}
