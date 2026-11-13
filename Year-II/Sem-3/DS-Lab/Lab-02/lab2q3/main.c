/*
Implement a C program to read, display, and find the product of two matrices using
functions with appropriate parameters.
• The matrices must be created using dynamic memory allocation (malloc or
calloc).
• Access matrix elements using array dereferencing (i.e., *(*(mat + i) + j)
style).
*/

#include <stdio.h>
#include <stdlib.h>

// Function to allocate memory for a 2D matrix dynamically
int **allocateMatrix(int rows, int cols) {
    int **mat = (int **)malloc(rows * sizeof(int *));
    if (mat == NULL) {
        printf("Memory allocation failed!\n");
        exit(1);
    }

    for (int i = 0; i < rows; i++) {
        *(mat + i) = (int *)malloc(cols * sizeof(int));
        if (*(mat + i) == NULL) {
            printf("Memory allocation failed!\n");
            exit(1);
        }
    }

    return mat;
}

// Function to read matrix elements
void readMatrix(int **mat, int rows, int cols) {
    printf("Enter elements (%d x %d):\n", rows, cols);
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            scanf("%d", (*(mat + i) + j));
        }
    }
}

// Function to display matrix
void displayMatrix(int **mat, int rows, int cols) {
    printf("Matrix (%d x %d):\n", rows, cols);
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            printf("%d ", *(*(mat + i) + j));
        }
        printf("\n");
    }
}

// Function to multiply two matrices
int **multiplyMatrices(int **mat1, int **mat2, int r1, int c1, int c2) {
    int **result = allocateMatrix(r1, c2);

    for (int i = 0; i < r1; i++) {
        for (int j = 0; j < c2; j++) {
            *(*(result + i) + j) = 0;
            for (int k = 0; k < c1; k++) {
                *(*(result + i) + j) += (*(*(mat1 + i) + k)) * (*(*(mat2 + k) + j));
            }
        }
    }

    return result;
}

int main() {
    int r1, c1, r2, c2;

    // Read dimensions for Matrix 1
    printf("Enter rows and columns of Matrix 1: ");
    scanf("%d %d", &r1, &c1);

    // Read dimensions for Matrix 2
    printf("Enter rows and columns of Matrix 2: ");
    scanf("%d %d", &r2, &c2);

    // Validate matrix multiplication possibility
    if (c1 != r2) {
        printf("Matrix multiplication not possible (columns of Matrix 1 != rows of Matrix 2).\n");
        return 1;
    }

    // Allocate and read matrices
    int **mat1 = allocateMatrix(r1, c1);
    int **mat2 = allocateMatrix(r2, c2);

    printf("\nMatrix 1:\n");
    readMatrix(mat1, r1, c1);
    printf("\nMatrix 2:\n");
    readMatrix(mat2, r2, c2);

    // Display input matrices
    printf("\nMatrix 1:\n");
    displayMatrix(mat1, r1, c1);

    printf("\nMatrix 2:\n");
    displayMatrix(mat2, r2, c2);

    // Multiply matrices
    int **product = multiplyMatrices(mat1, mat2, r1, c1, c2);

    // Display product matrix
    printf("\nProduct of Matrix 1 and Matrix 2:\n");
    displayMatrix(product, r1, c2);

    // Free allocated memory
    for (int i = 0; i < r1; i++) free(*(mat1 + i));
    for (int i = 0; i < r2; i++) free(*(mat2 + i));
    for (int i = 0; i < r1; i++) free(*(product + i));

    free(mat1);
    free(mat2);
    free(product);

    return 0;
}
