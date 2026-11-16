#include <stdio.h>
#include <stdlib.h>

struct Node {
    int data;           // Changed to int for evaluation
    struct Node* next;
};

void push(struct Node** top, int value) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = value;
    newNode->next = *top;
    *top = newNode;
}

int pop(struct Node** top) {
    if (*top == NULL) return 0;
    struct Node* temp = *top;
    int val = temp->data;
    *top = temp->next;
    free(temp);
    return val;
}

int peek(struct Node* top) {
    if (top == NULL) return 0;
    return top->data;
}

int isEmpty(struct Node* top) {
    return top == NULL;
}

int precedence(char op) {
    if (op == '+' || op == '-') return 1;
    if (op == '*' || op == '/') return 2;
    return 0;
}

int isAlpha(char ch) {
    return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
}


int isDigit(char ch) {
    return ch >= '0' && ch <= '9';
}


int isAlnum(char ch) {
    return isAlpha(ch) || isDigit(ch);
}


char toUpper(char ch) {
    if (ch >= 'a' && ch <= 'z') return ch - 'a' + 'A';
    return ch;
}

void infixToPostfix(char* infix, char* postfix) {
    struct Node* stack = NULL;
    int i = 0, k = 0;

    while (infix[i] != '\0') {
        char ch = infix[i];

        if (isAlnum(ch)) {
            postfix[k++] = ch;
        }
        else if (ch == '(') {
            push(&stack, ch);
        }
        else if (ch == ')') {
            while (!isEmpty(stack) && peek(stack) != '(') {
                postfix[k++] = pop(&stack);
            }
            pop(&stack);
        }
        else {  // operator
            while (!isEmpty(stack) && precedence(peek(stack)) >= precedence(ch)) {
                postfix[k++] = pop(&stack);
            }
            push(&stack, ch);
        }
        i++;
    }

    while (!isEmpty(stack)) {
        postfix[k++] = pop(&stack);
    }
    postfix[k] = '\0';
}

int operandValue(char op) {
    if (isDigit(op)) return op - '0';

    if (isAlpha(op)) {
        op = toUpper(op);
        return op - 'A' + 1;
    }
    return 0;
}

int evaluatePostfix(char* postfix) {
    struct Node* stack = NULL;
    int i = 0;

    while (postfix[i] != '\0') {
        char ch = postfix[i];

        if (isAlnum(ch)) {
            push(&stack, operandValue(ch));
        } else {
            int val2 = pop(&stack);
            int val1 = pop(&stack);
            int res = 0;

            switch(ch) {
                case '+': res = val1 + val2; break;
                case '-': res = val1 - val2; break;
                case '*': res = val1 * val2; break;
                case '/': res = val1 / val2; break;
            }
            push(&stack, res);
        }
        i++;
    }

    return pop(&stack);
}

int main() {
    char infix[100], postfix[100];

    printf("Enter an infix expression (single letter/digit operands): ");
    scanf("%s", infix);

    infixToPostfix(infix, postfix);
    printf("Postfix expression: %s\n", postfix);

    int result = evaluatePostfix(postfix);
    printf("Evaluation result: %d\n", result);

    return 0;
}
