#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 10

struct stack{
char items[MAX_SIZE];
int top ; };


int isEmpty(struct stack stack ) {
    return stack.top == -1;
}


int isFull(struct stack stack ) {
    return stack.top == MAX_SIZE - 1;
}

void push(struct stack *stack, char value) {
    if (isFull(*stack)) {
        printf("Stack Overflow! Cannot push \n", value);
        return;
    }
    stack->items[++stack->top] = value;
    printf("\n %c pushed to stack.\n", value);
}

void pop(struct stack *stack) {

    if (isEmpty(*stack)) {
        printf("Stack Underflow! Cannot pop from an empty stack.\n");
    }
    stack->items[stack->top--];
}

int peek(struct stack *stack) {
    if (isEmpty(*stack)) {
        printf("Stack is empty! No top element.\n");
        return -1;
    }
    return stack->items[stack->top];
}


void compare(struct stack *stack )
{   int j=stack->top;
    for(int i=0;i<j;i++,j--)
    {
        if (stack->items[i]!=stack->items[j])
        {
            printf("The word is not a palindrome ");
            return;
        }
    }
    printf("\nIt is a palindrome yay !");
}

// function in c checking for parenthesis

void parent(char exp[])
{   struct stack s; s.top=-1;
    for(int i=0;exp[i]!='\0';i++)
    {
        if(exp[i]=='('||exp[i]=='{'||exp[i]=='[')
            {
                push(&s,exp[i]);
            }
         else if (exp[i] == ')' || exp[i] == '}' || exp[i] == ']') {
            if (isEmpty(s)) {
                printf("Not a matching expression: stack empty but closing bracket found.\n");
                return;
            }
            char topChar = peek(&s);

            if ((exp[i] == ')' && topChar == '(') ||
                (exp[i] == '}' && topChar == '{') ||
                (exp[i] == ']' && topChar == '[')) {
                pop(&s);
            } else {
                printf("Not a matching expression: mismatched brackets.\n");
                return;
            }
        if(exp[i+1]=='\0')
        {
            if (isEmpty(s))
            {
                printf("It's a matching expression\n");
            }
            else
            {
                printf("Not a matching expression: stack not empty after processing.\n");
            }
        }
    }
}
}

int main()
{
    struct stack s1;
    s1.top=-1;
    push(&s1,'h');
    push(&s1,'e');
    push(&s1,'h');
    pop(&s1);
    push(&s1,'h');
    compare(&s1);
    char word[]="(2+(3*5+[7%6]))";
    parent(word);
    return 0;

}

