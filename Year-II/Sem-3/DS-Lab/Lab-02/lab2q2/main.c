/*
Write a recursive C program to implement Selection Sort using pointers.
• The recursive function should sort the array using the Selection Sort algorithm.
• Access and manipulate the array elements using pointers (i.e., avoid using arr[i]
style directly).
• The program should read the array from the user in the main function, call the
recursive sorting function, and display the sorted array.
*/

#include <stdio.h>
#include <stdlib.h>

// Function to swap two integers using pointers
void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

// Recursive Selection Sort using pointers
void recursiveSelectionSort(int *arr, int n, int index) {
    if (index == n - 1)
        return; // Base case: when only one element is left

    // Find the index of the minimum element in the unsorted part
    int *minPtr = arr + index;
    for (int *ptr = arr + index + 1; ptr < arr + n; ptr++) {
        if (*ptr < *minPtr) {
            minPtr = ptr;
        }
    }

    // Swap the found minimum element with the element at current index
    swap(arr + index, minPtr);

    // Recur for the remaining unsorted array
    recursiveSelectionSort(arr, n, index + 1);
}

int main() {
    int n;
    printf("Enter the number of elements: ");
    scanf("%d", &n);

    // Dynamically allocate memory for the array
    int *arr = (int *)malloc(n * sizeof(int));
    if (arr == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    printf("Enter %d elements:\n", n);
    for (int *ptr = arr; ptr < arr + n; ptr++) {
        scanf("%d", ptr);
    }

    // Perform recursive selection sort
    recursiveSelectionSort(arr, n, 0);

    printf("Sorted array:\n");
    for (int *ptr = arr; ptr < arr + n; ptr++) {
        printf("%d ", *ptr);
    }
    printf("\n");

    // Free allocated memory
    free(arr);

    return 0;
}
