#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define MAX_SIZE 100

// A global stack for simplicity, as per your original code's style
char stack[MAX_SIZE];
int top = -1;

bool isDuplicate(char s[]) {
    top = -1; // Reset stack for each function call
    int n = strlen(s);

    for (int i = 0; i < n; i++) {
        char current_char = s[i];

        if (current_char == ')') {
            // Check for immediate duplicate like `()`
            if (top != -1 && stack[top] == '(') {
                return true;
            }

            int elementsInside = 0;
            // Pop everything until the matching opening parenthesis
            while (top != -1 && stack[top] != '(') {
                elementsInside++;
                top--;
            }

            // Pop the matching '('
            if (top != -1) {
                top--;
            }

            // If no elements were inside, it's a duplicate
            if (elementsInside == 0) {
                return true;
            }
        } else {
            // Push characters onto the stack
            if (top < MAX_SIZE - 1) {
                stack[++top] = current_char;
            }
        }
    }

    // Check for unmatched opening parentheses at the end
    while (top != -1) {
        if (stack[top] == '(') {
            return true;
        }
        top--;
    }

    return false;
}

int main() {
    char s1[] = "(((a+(b))+(c+d)))";
    char s2[] = "(a + (b) * (c + d))"; // Example without duplicate
    char s3[] = "((a))"; // Another example with duplicate

    if (isDuplicate(s1)) {
        printf("Expression \"%s\" contains duplicate parentheses.\n", s1);
    } else {
        printf("Expression \"%s\" does not contain duplicate parentheses.\n", s1);
    }

    if (isDuplicate(s2)) {
        printf("Expression \"%s\" contains duplicate parentheses.\n", s2);
    } else {
        printf("Expression \"%s\" does not contain duplicate parentheses.\n", s2);
    }

    if (isDuplicate(s3)) {
        printf("Expression \"%s\" contains duplicate parentheses.\n", s3);
    } else {
        printf("Expression \"%s\" does not contain duplicate parentheses.\n", s3);
    }

    return 0;
}
