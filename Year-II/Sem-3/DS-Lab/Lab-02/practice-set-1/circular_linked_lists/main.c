#include <stdio.h>
#include <stdlib.h>

// 1. Write a C program to implement a Circular SLL using First and Last pointers.

// Then implement the following:
// 1. Insertion at the end of the list using First and Last pointers
// 2. Deletion from the beginning or end using First and Last pointers
// 3. Display the list after each operation

typedef struct Node {
    int data;
    struct Node *link;
} Node;

Node* first = NULL;
Node* last = NULL;

Node* createNode(int data) {
    Node* n = (Node*)malloc(sizeof(Node));
    n->data=data;
    n->link=NULL;
    return n;
}

void insertEnd(int data) {
    Node* n = createNode(data);

    if (first == NULL) { // First Node
        first = n;
        last = n;
        n->link = first; // Circular link
    } else {
        last->link = n;
        last = n;
        last->link = first; // Maintain circular link
    }
}

void deleteBeginning() {
    if (first == NULL) {
        printf("List is empty!\n");
        return;
    }
    if (first == last) { // Only one node
        free (first);
        first = last = NULL;
    } else {
        Node* temp = first;
        first = first->link;
        last->link = first;
        free(temp);
    }
}

void deleteEnd() {
    if (first == NULL) {
        printf("List is empty!\n");
        return;
    }
    if (first == last) {
        free (first);
        first = last = NULL;
    } else {
        Node* temp = first;
        while (temp->link != last)
            temp=temp->link;
        temp->link = first;
        free(last);
        last=temp;
    }
}

void display() {
    if (first == NULL) {
        printf("List is empty!\n");
        return;
    }
    Node* temp = first;
    printf("CSLL: ");
    do{
        printf("%d -> ", temp->data);
        temp=temp->link;
    } while (temp != first);
    printf("\n");
}

int main()
{
    int choice, val;
    while (1) {
        printf("\nCircular Singly Linked List Menu\n");
        printf("1. Insert at End\n");
        printf("2. Delete from Beginning\n");
        printf("3. Delete from End\n");
        printf("4. Display\n");
        printf("5. Exit\n");
        printf("Enter Choice: ");
        scanf("%d", &choice);

        switch (choice) {
        case 1:
            printf("Enter Value: ");
            scanf("%d", &val);
            insertEnd(val);
            display();
            break;
        case 2:
            deleteBeginning();
            display();
            break;
        case 3:
            deleteEnd();
            display();
            break;
        case 4:
            display();
            break;
        case 5:
            exit(0);
        default:
            printf("Invalid Choice, please try again!\n");
        }

    }
    return 0;
}
