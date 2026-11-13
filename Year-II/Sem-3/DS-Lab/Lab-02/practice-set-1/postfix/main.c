#include <stdio.h>
#include <stdlib.h>
#define MAX 100

// INFIX: a*b + c*d - e
// POSTFIX: ab*cd*+e-

int isOperator(char c);
int isOperand(char c);
int predecence(char op);
int hasHigherOrEqualPrecedence(char op1, char op2);
void convert(char infix[], char postfixp[]);

int isOperator(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' );
}


int main()
{
    char infix[MAX], postfix[MAX];
    int ch;
    do {
        printf("Enter an infix expression: ");
        fgets(infix, MAX, stdin);
        infix[strcspn(infix, "\n")] = '\0';
        convert(infix, postfix);
        printf("\nInfix: %s", infix);
        printf("\nPostfix: %s", postfix);
        printf("\n\nDo you want to enter another expression? (1/0): ");
        scanf("%d", &ch);
        getchar();
    } while (ch == 1);
    return 0;
}
