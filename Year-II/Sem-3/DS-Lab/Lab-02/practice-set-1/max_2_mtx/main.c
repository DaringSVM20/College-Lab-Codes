// Write a C program to read two matrices A & B, create and display a third matrix C such that C (i, j) = max (A(i, j) , B(i, j))

#include <stdio.h>
#include <stdlib.h>
#define MAXR 10
#define MAXC 10

int main() {

    int a[MAXR][MAXC], b[MAXR][MAXC], c[MAXR][MAXC];
    int rows, cols, i, j;

    printf("Enter the number of rows (max %d): ", MAXR);
    scanf("%d", &rows);
    printf("Enter the number of columns (max %d): ", MAXC);
    scanf("%d", &cols);

    if (rows > MAXR || cols > MAXC) {
        printf("Error: Dimensions exceeded maximum allowed.\n");
        return 1;
    }

    printf("\nEnter Matrix A Elements:\n");
    for (int i=0; i<rows; i++) {
        for (int j=0; j<cols; j++) {
            printf("A[%d][%d]: ", i, j);
            scanf("%d", &a[i][j]);
        } printf("\n");
    }

    printf("\nEnter Matrix B Elements:\n");
    for (int i=0; i<rows; i++) {
        for (int j=0; j<cols; j++) {
            printf("B[%d][%d]: ", i, j);
            scanf("%d", &b[i][j]);
        } printf("\n");
    }

    printf("\n--- Matrix A ---\n");
    for (i = 0; i < rows; i++) {
        for (j = 0; j < cols; j++) {
            printf("%d\t", a[i][j]);
        }
        printf("\n");
    }

    printf("\n--- Matrix B ---\n");
    for (i = 0; i < rows; i++) {
        for (j = 0; j < cols; j++) {
            printf("%d\t", b[i][j]);
        }
        printf("\n");
    }

    for (int i=0; i<rows; i++) {
        for (int j=0; j<cols; j++) {
            c[i][j] = ( a[i][j] > b[i][j] ) ? a[i][j] : b[i][j];
        }
    }

    printf("\n--- Matrix C (max of A and B) ---\n");
    for (i = 0; i < rows; i++) {
        for (j = 0; j < cols; j++) {
            printf("%d\t", c[i][j]);
        }
        printf("\n");
    }

    return 0;
}
