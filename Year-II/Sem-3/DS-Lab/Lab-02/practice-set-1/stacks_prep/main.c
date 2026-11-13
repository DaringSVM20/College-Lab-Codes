#include <stdio.h>
#include <stdlib.h>
#define MAX 5

int stack[MAX], top = -1;

// Push operation
void push(int item) {
    if (top == MAX - 1)
        printf("Stack Overflow!\n");
    else {
        stack[++top] = item;
        printf("%d pushed to stack.\n", item);
    }
}

// Pop operation
void pop() {
    if (top == -1)
        printf("Stack Underflow!\n");
    else
        printf("Popped: %d\n", stack[top--]);
}

// Peek function
void peek() {
    if (top == -1) {
        printf("Stack is empty!\n");
    } else {
        printf("Top element is: %d\n", stack[top]);
    }
}

// isEmpty function
void isEmpty() {
    if (top == -1) {
        printf("Stack is empty!\n");
    } else {
        printf("Stack is not empty!\n");
    }
}

// Display stack
void display() {
    if (top == -1) {
        printf("Stack is empty!\n");
    } else {
        printf("Stack:\n");
        for (int i=top; i>=0; i--) {
            printf("%d\n", stack[i]);
        }
    printf("\n");
    }
}

int main()
{
    int n, value;
    printf("How many values do you want to push to stack: ");
    scanf("%d", &n);
    for (int i=0; i<n; i++) {
        printf("Enter value to push to stack: ");
        scanf("%d", &value);
        push(value);
    }
    display();

    printf("\nPopping top value:\n");
    pop();
    display();

    printf("\nPeeking:\n");
    peek();

    printf("\nChecking if stack is empty:\n");
    isEmpty();

    printf("\nDisplaying stack once again:\n");
    display();

    return 0;
}
