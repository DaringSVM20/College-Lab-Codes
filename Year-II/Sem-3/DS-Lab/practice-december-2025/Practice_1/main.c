#include <stdio.h>
#include <stdlib.h>

int main()
{
    int choice, a, b;
    while (1) {
        printf("\nCalculator pro max:\n");
        printf("1. Add\n");
        printf("2. Subtract\n");
        printf("3. Multiply\n");
        printf("4. Divide\n");
        printf("0. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        printf("\nEnter numbers to operate on: ");
        printf("\nEnter a: ");
        scanf("%d", &a);
        printf("Enter b: ");
        scanf("%d", &b);

        switch (choice) {
        case 1:
            printf("Solution: %d\n", a+b);
            break;
        case 2:
            printf("Solution: %d\n", a-b);
            break;
        case 3:
            printf("Solution: %d\n", a*b);
            break;
        case 4:
            printf("Solution: %d\n", a/b);
            break;
        case 0:
            return;
        }
    }

    return 0;
}
