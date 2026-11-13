#include <stdio.h>
#include <stdlib.h>

typedef struct Student{
    int roll;
    char name[50];
    int marks;
} Student;

int main()
{
    Student s1 = {11, "Shlok", 50};
    printf("Roll No: %d\n", s1.roll);
    printf("Name: %s\n", s1.name);
    printf("Marks: %d\n", s1.marks);
    return 0;
}
