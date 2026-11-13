// Insertion Sort

#include <stdio.h>
#include <stdlib.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void insertionSort(int a[], int n) {
    int j;

    for (int i=1; i<n; i++) {
        j=i;
        while (j>0 && a[j-1] > a[j]) {
            swap(&a[j], &a[j-1]);
            j--;
        }
    }


    printf("\nList of Sorted Elements: ");
    for (int i=0; i<n; i++) {
        printf("%d\t", a[i]);
    }

}

int main() {
    int n;
    printf("Enter Number of Elements: ");
    scanf("%d", &n);
    int a[n];
    printf("Enter values:-\n");
    for (int i=0; i<n; i++) {
        printf("Enter value %d: ", i+1);
        scanf("%d", &a[i]);
    }

    insertionSort(a, n);

    return 0;
}
