/*
    Write a menu driven C program using structures to implement the following operations on a Doubly Linked List.
    1. Insert a new element at the rear end of the list.
    2. Delete an element from the rear end of the list.
    3. Insert an element at a given position in the list.
    4. Delete an element from a given position in the list.
    5. Insert an element after a node containing a specific value.
    7. Insert an element before a node containing a specific value.
    8. Traverse the list in forward direction.
    9. Traverse the list in reverse direction.
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int data;
    struct Node* prev;
    struct Node* next;
} Node;

Node* head = NULL;
Node* tail = NULL;

Node* createNode(int data) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data=data;
    newNode->next=NULL;
    newNode->prev=NULL;
    return newNode;
}

void insertRead(int data) {
    Node* n = createNode(data);
    if (head==NULL) {
        head = tail = n;
    } else {
        tail->next=n; //Puts it at the end of the tail
        n->prev=tail; // Makes the tail node point to it
        tail=n; // Makes it the tail
    }
}

void deleteRear(int data) {
    if (tail==NULL) {
        printf("List is empty.");
        return;
    }
    Node* temp = tail;
    tail=tail->prev; // Last but one Node.
    if (tail != NULL)
        tail->next=NULL; // Deletes the pointer to the last element, essentially deleting it.
    else
        head=NULL; // Means there is only one Node in the entire list.
    free(temp);
}

void insertAtPos(int data, int pos) {
    if (pos < 1) {
        printf("Invalid position");
        return;
    }
    Node* n = createNode(data);
    if (pos == 1) {
        n->next=head; // Data becomes head
        if (head != NULL)
            head->prev=n; // head points back to new node.
        head = n;
        if (tail == NULL)
            tail=n; // If there is no tail, new node is tail.
        return;
    }
    Node* temp = head;
    for (int i=1; temp != NULL && i<pos-1; i++)
        temp=temp->next; // Goes till that position
    if (temp == NULL) {
        printf("Position out of range");
        free(n);
        return;
    }
    n->next=temp->next; // Links new node's next to temp's next, essentially placing new node at temp's place.
    if (temp->next != NULL)
        temp->next->prev=n; // Temp's original next points back to n, completing the insertion. Now the new points to the one after temp and not temp, successfully completing the link between the new node, the nodes before and and after it.
    else tail=n; // if temp is the last node, which is the tail, the tail becomes n.
    n->prev=temp; // prev of new node links to whatever temp was pointing to, the penultimate node in the series.
    temp->next=n; // the penultimate node links to new one (the new tail for this series)
}

void deleteAtPos(int pos) {
    if (head == 0 || pos < 1) {
        printf("No positions to delete");
        return;
    }
    Node* temp = head;
    if (pos == 1) {
        head=head->next;
        if (head != NULL)
            head->prev=NULL;
        else tail = NULL;
        free(temp);
        return;
    }
    for (int i=1; temp != NULL && i < pos; i++)
        temp=temp->next;
    if (temp==NULL) {
        printf("Position out of range.");
        return;
    }
    if (temp->next != NULL)
        temp->prev->next = temp->prev;
    else tail=tail->prev;
    if (temp->prev != NULL)
        temp->prev->next = temp->next;
    free (temp);
}

void traverseFwd() {
    if (head == NULL) {
        printf("List is empty.");
        return;
    }
    Node* temp = head;
    while (temp != NULL) {
        printf("%d ->", temp->data);
        temp=temp->next;
    }
    printf("\n");
}

void traverseRev() {
    if (tail == NULL) {
        printf("List is empty.");
        return;
    }
    Node* temp = tail;
    while (temp != NULL) {
        printf("%d ->", temp->data);
        temp=temp->prev;
    }
    printf("\n");
}

void reverseList() {
    Node* temp = NULL;
    Node* cur = head;

    while (cur != NULL) {
        cur->next=temp; // saving original next node in sequence
        cur->next=cur->prev; // swapping next and prev
        cur->prev=temp;
        cur=temp;
    }
    Node* swap = head;
    head = tail;
    tail = swap;
}

void sortList() {
    // We have to do a classic bubble sort. So we check data and keep swapping when we encounter a smaller value in front of a larger one.
    if (head == NULL) return;
    int swapped;
    Node* cur;
    Node* last = NULL;

    do {
        swapped = 0;
        cur = head;

        while (cur->next != last) {
            if (cur->data > cur->next->data) {
                int temp = cur->data;
                cur->data = cur->next->data;
                cur->next->data = temp;
            }
        cur = cur->next;
        }
    last = cur;
    } while (swapped);
}


int main()
{















    return 0;
}
