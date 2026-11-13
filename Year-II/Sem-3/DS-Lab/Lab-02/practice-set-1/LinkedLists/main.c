/*

Write a menu-driven C program using structures to implement the following operations on a
singly linked list:
1. Insert an element before another specified element in the list
(Example: Insert 10 before 25)
2. Insert an element after another specified element in the list
(Example: Insert 40 after 25)
3. Delete a specified element from the list
(Example: Delete node containing 15)
4. Traverse the list and display all elements
5. Reverse the linked list
(Modify the links such that the list is reversed)
6. Sort the list in ascending order
(Using Bubble Sort or any appropriate algorithm on linked list)
7. Delete every alternate node in the list
(Starting from the second node)
8. Insert an element into a sorted linked list while maintaining the sorted order
(Example: Insert 28 into a list that is already sorted)

*/

#include <stdio.h>
#include <stdlib.h>


typedef struct Node{
    int data;
    struct Node* link;
} Node;

Node* head = NULL; //Global head pointer

Node* createNode(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data=data;
    newNode->link=NULL;
    return newNode;
}

void insertAtEnd(int data) {
    Node* n = createNode(data);
    if (head==NULL) {
        head = n;
        return; // This return ensures the rest of the code doesn't compile
    }
    Node* temp = head;
    while(temp->link != NULL) {
        temp = temp->link;
    }
    temp->link=n;
}

void insertBefore(int data, int target) {
    Node* n = createNode(data);
    if (head != NULL && head->data==target) { // If head itself is the target we need to insert the new node before
        n->link=head; // Connect this new node to head
        head=n; // Change the identity of head to be this new node
        return;
    }

    // If the above doesn't work, we create a temp and run it through the singly LL until it hits the targer:-
    Node* temp = head;
    while (temp != NULL && temp->link != NULL && temp->link->data != target) {
        temp=temp->link; // We go to the element just before our target, where we need to insert the new node
    }

    if (temp != NULL && temp->link != NULL) {
        n->link=temp->link; // New node points to target node
        temp->link=n; // Previous node points to new node
    } else {
        printf("Target %d not found!\n", target);
        free(n);
    }
}

void insertAfter(int data, int target) {
    Node* temp = head;
    while (temp != NULL && temp->data != target) {
        temp = temp->link;
    }
    if (temp != NULL) {
        Node* n = createNode(data);
        n->link=temp->link;
        temp->link=n;
    }
}

void deleteNode (int target) {
    if (head == NULL) return;

    if (head->data == target) {
        Node* temp = head;
        head = head->link;
        free(temp);
        return;
    }

    Node* temp = head;
    while (temp->link != NULL && temp->link->data != target) {
        temp = temp->link;
    }
    if (temp->link != NULL) {
        Node* del = temp->link;
        temp->link = temp->link->link;
        free(del);
    } else {
        printf("Element %d not found!\n", target);
    }
}

void traverse() {
    Node* temp = head;
    if (temp == NULL) {
        printf("List is empty.\n");
        return;
    }
    printf("\n");
    while (temp != NULL) {
        printf("%d -> ", temp->data);
        temp=temp->link;
    }
    printf("NULL\n");
    printf("\n");
}

void reverseList() {
    Node* prev = NULL; // New tail of reversed list
    Node* curr = head; // Working node
    Node* next = NULL; // For the saving data of the original next node
    while (curr != NULL) {
        next = curr->link; // Store original next node
        curr->link = prev; // Current points to NULL (tail
        prev = curr; // Tail becomes the current element (since we are reversing the order the list)
        curr = next; // Working node updated to saved data so cycle can repeat
    }
    head = prev; // When current becomes NULL, means it leaves the last original element, the first element in the reversed order would be prev, hence it will be the new head.
}

void sortList() {
    if (head == NULL) return;

    Node* i;
    Node* j;
    int temp;

    for (i=head; i->link != NULL; i = i->link) {
        for (j=head; j->link != NULL; j = j->link) {
            if (j->data > j->link->data) {
                temp = j->data;
                j->data = j->link->data;
                j->link->data = temp;
            }
        }
    }
}

void deleteAlternateNodes() {
    Node* curr = head;
    while (curr != NULL && curr->link != NULL) {
        Node* temp = curr->link;
        curr->link = temp->link;
        free(temp);
        curr=curr->link;
    }
}

void insertSorted(int value) {
    Node* n = createNode(value);

    if (head == NULL || head->data >= value) {
        n->link = head;
        head = n;
        return;
    }

    Node* temp = head;
    while (temp->link != NULL && temp->link->data < value) {
        temp = temp->link;
    }
    n->link = temp->link; // New node links to value after it
    temp->link = n; // Temp links to new node since it's value is lesser than new node
}

int main()
{
    int choice, val, target;

    while (1) {
        printf("\nMenu:\n");
        printf("1. Insert at End\n");
        printf("2. Insert Before\n");
        printf("3. Insert After\n");
        printf("4. Delete Node\n");
        printf("5. Traverse List\n");
        printf("6. Reverse List\n");
        printf("7. Sort List\n");
        printf("8. Delete Alternate Nodes\n");
        printf("9. Insert in Sorted List\n");
        printf("0. Exit\n");
        printf("Enter choice: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Enter value: ");
                scanf("%d", &val);
                insertAtEnd(val);
                break;
            case 2:
                printf("Enter value to insert and target: ");
                scanf("%d %d", &val, &target);
                insertBefore(val, target);
                break;
            case 3:
                printf("Enter value to insert and target: ");
                scanf("%d %d", &val, &target);
                insertAfter(val, target);
                break;
            case 4:
                printf("Enter element to delete: ");
                scanf("%d", &target);
                deleteNode(target);
                break;
            case 5:
                traverse();
                break;
            case 6:
                reverseList();
                break;
            case 7:
                sortList();
                break;
            case 8:
                deleteAlternateNodes();
                break;
            case 9:
                printf("Enter value to insert: ");
                scanf("%d", &val);
                insertSorted(val);
                break;
            case 0:
                exit(0);
            default:
                printf("\nInvalid choice! Please try that again.\n");
        }
    }
}





























