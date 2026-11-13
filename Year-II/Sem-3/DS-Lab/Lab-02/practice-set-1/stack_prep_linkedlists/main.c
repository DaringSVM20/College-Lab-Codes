#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int data;
    struct Node* link;
} Node;

Node* top = NULL;

// Push operation
void push(int value) {
    Node* n = (Node*)malloc(sizeof(Node));

    if (!n) {
        printf("Stack Overflow!\n");
        return;
    }
    n->data=value;
    n->link=top;
    top=n;
    printf("%d pushed to stack\n", value);
}

// Pop operation
void pop() {
    if (top == NULL) {
        printf("Stack Underflow!\n");
        return;
    }

    Node* temp = top;
    printf("%d popped from stack\n", top->data);
    top=top->link;
    free(temp);
}

// Peek operation
void peek() {
    if (top == NULL) {
        printf("Stack is empty\n");
        return;
    }
    printf("Top element: %d\n", top->data);
}

// isEmpty operation
int isEmpty() {
    return (top == NULL);
}

// Display stack
void display() {
    Node* temp = top;
    if (temp == NULL) {
        printf("Stack is empty!\n");
        return;
    }
    printf("Stack:\n");
    while (temp != NULL) {
        printf("%d\n", temp->data);
        temp=temp->link;
    }
    printf("\n");
}

int main() {
    int choice, value;

    printf("--- Linked List Stack Demonstration ---\n");

    do {
        printf("\nMenu:\n");
        printf("1. Push\n");
        printf("2. Pop\n");
        printf("3. Peek\n");
        printf("4. Display stack\n");
        printf("5. Check if stack is empty\n");
        printf("6. Exit\n");
        printf("Enter your choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter value to push: ");
                scanf("%d", &value);
                push(value);
                break;
            case 2:
                pop();
                break;
            case 3:
                peek();
                break;
            case 4:
                display();
                break;
            case 5:
                if (isEmpty()) {
                    printf("Stack is empty.\n");
                } else {
                    printf("Stack is not empty.\n");
                }
                break;
            case 6:
                printf("Exiting program.\n");
                break;
            default:
                printf("Invalid choice. Please try again.\n");
        }
    } while (choice != 6);

    // Free any remaining memory before exiting.
    while (!isEmpty()) {
        pop();
    }

    return 0;
}


