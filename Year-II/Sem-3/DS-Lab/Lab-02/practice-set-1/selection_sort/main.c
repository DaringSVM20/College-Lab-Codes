#include <stdio.h>
#include <stdlib.h>

void swap(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

void selectionSort(int a[], int n) {
    int iMin=-1;
    for (int i=0; i<n-1; i++) {
        iMin = i;
        for (int j=i+1; j<n; j++) {
            if (a[j] < a[iMin]) {
                iMin = j;
            }
        }
        if (iMin != i) {
            swap(&a[i], &a[iMin]);
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

    selectionSort(a, n);

    return 0;
}
