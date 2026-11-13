/*
Write a small function to find the smallest element in an array using pointers.
In the main function, create a dynamically allocated array, read the values from the
keyboard, and pass the array to the function.
Display the result (smallest element) in the main function.
*/

#include <stdio.h>
#include <stdlib.h>

// Function to find the smallest element using pointers
int findSmallest(int *arr, int size) {
    int *ptr = arr;
    int smallest = *ptr;

    for (int i = 1; i < size; i++) {
        if (*(ptr + i) < smallest) {
            smallest = *(ptr + i);
        }
    }
    return smallest;
}

int main() {
    int n;
    printf("Enter the number of elements: ");
    scanf("%d", &n);

    // Dynamically allocate memory for n integers
    int *arr = (int *)malloc(n * sizeof(int));
    if (arr == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    // Read values into array
    printf("Enter %d elements:\n", n);
    for (int i = 0; i < n; i++) {
        scanf("%d", arr + i);
    }

    // Find smallest element
    int smallest = findSmallest(arr, n);

    printf("The smallest element is: %d\n", smallest);

    // Free the allocated memory
    free(arr);

    return 0;
}
