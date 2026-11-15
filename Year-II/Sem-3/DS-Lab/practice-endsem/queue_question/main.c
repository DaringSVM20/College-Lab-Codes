/*
Implement a queue-based system for managing hospital patients waiting for treatment. Each patient has a name and a token number.
The system should support adding a patient, serving the next patient, and displaying the queue.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 5

typedef struct {
    char name[50];
    int token;
} Patient;

typedef struct {
    Patient arr[MAX];
    int front; rear;
} Queue;

void init(Queue *q) {
    q->front = q->rear = -1;
}

int isFull(Queue *q) {
    return q->rear == MAX-1;
}

int isEmpty(Queue *q) {
    return q->front == -1;
}

void enqueue(Queue *q, char *name, int token) {
    if (isFull(q)) {
        printf("Queue full!\n");
        return;
    }
    if (isEmpty(q))
        q->front = 0;
    q->rear++;
}

int main()
{
    printf("Hello world!\n");
    return 0;
}
