#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 100

typedef struct Stack{
    char st[MAX];
    int top;
} Stack;

Stack s;

// Push function
void push (char item) {
    if (s.top == MAX-1) {
        printf("Stack is Full\n");
        return 0;
    } else {
        s.top++;
        s.st[s.top] = item;
        return 1;
    }
}

// Pop function
void pop() {
    if (s.top == -1) {
        printf("Stack is empty.\n");
    } else {
        s.top--;
    }
}

int checkPair(char val1, char val2) {
    return ( (val1 == '(' && val2 == ')') || (val1 == '{' && val2 == '}') || (val1 == '[' && val2 == ']') );
}

int checkBalanced(char exp[]) {
    int len = strlen(exp);

    for (int i=0; i<len; i++) {
        if (exp[i] == '(' || exp[i] == '{' || exp[i] == '[') {
            push(exp[i]);
        } else if (exp[i] == ')' || exp[i] == '}' ||exp[i] == ']') {
            if (s.top == -1) {
                return 0;
            } else if ( checkPair(s.st[s.top] , exp[i]) ) {
                pop();
            } else {
                return 0;
            }
        }
    }
    return (s.top == -1);
}

int main()
{
    char exp[MAX];
    printf("Enter an expression: ");
    fgets(exp, MAX, stdin);
    exp[strcspn(exp, "\n")] = '\0';
    s.top = -1;
    if (checkBalanced(exp))
        printf("Balanced");
    else
        printf("Not Balanced");


    return 0;
}
