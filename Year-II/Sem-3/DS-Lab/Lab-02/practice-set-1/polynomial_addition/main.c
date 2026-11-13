// Add 2 polynomials represented as DLL

// 1. Represent each polunomial using a doubly linked list, nodes contain coeff and exponent of a term.
// 2. WAF to add 2 polynomials by merging terms with equal exponents. The resulting polynomial should be stored in a new DLL, maintaining the order of terms in descending powers of exponents.
// 3. Display all 2 polynomials and their sum. Ensure malloc, and pointers are maintained properly.

#include <stdio.h>
#include <stdlib.h>

typedef struct PolyNode {
    int coeff, exp;
    struct PolyNode* prev;
    struct PolyNode* next;
} PolyNode;

PolyNode* createNode(int coeff, int exp) {
    PolyNode* n = (PolyNode*)malloc(sizeof(PolyNode));
    n->coeff = coeff;
    n->exp = exp;
    n->prev = n->next = NULL;
    return n;
}

void insertEnd(PolyNode** head, int coeff, int exp) {
    PolyNode* n = createNode(coeff, exp);
    if (*head == NULL) {
        *head = n;
        return;
    }
    PolyNode* temp = *head;
    while (temp->next != NULL)
        temp = temp->next;
    temp->next=n;
    n->prev=temp;
}

void displayPoly(PolyNode* head) {
    if (!head) {
        printf("0");
        return;
    }
    PolyNode* temp = head;
    while (temp != NULL) {
        printf("%dx^%d", temp->coeff, temp->exp);
        if (temp->next != NULL)
            printf(" + ");
        temp = temp->next;
    }
    printf("\n");
}

PolyNode* addPoly(PolyNode* p1, PolyNode* p2) {
    PolyNode* result = NULL;

    while (p1 != NULL && p2 != NULL) {
        if (p1->exp == p2->exp) {
            int sumCoeff = p1->coeff + p2->coeff;
            if (sumCoeff != 0)
                insertEnd(&result, sumCoeff, p1->exp);
            p1 = p1->next;
            p2 = p2->next;
        } else if (p1->exp > p2->exp) {
            insertEnd(&result, p1->coeff, p1->exp);
            p1 = p1->next;
        } else {
            insertEnd(&result, p2->coeff, p2->exp);
            p2 = p2->next;
        }
    }
    while (p1 != NULL) {
        insertEnd(&result, p1->coeff, p1->exp);
        p1 = p1->next;
    }
    while (p2 != NULL) {
        insertEnd(&result, p2->coeff, p2->exp);
        p2 = p2->next;
    }
    return result;
}

int main()
{
    PolyNode* poly1 = NULL;
    PolyNode* poly2 = NULL;
    PolyNode* sum = NULL;

    // Example: P1 = 5x^2 + 4x^1 + 2
    insertEnd(&poly1, 5, 2);
    insertEnd(&poly1, 4, 1);
    insertEnd(&poly1, 2, 0);

    // Example: P2 = 3x^2 + 1
    insertEnd(&poly2, 3, 2);
    insertEnd(&poly2, 1, 0);

    printf("Polynomial 1: ");
    displayPoly(poly1);
    printf("Polynomial 2: ");
    displayPoly(poly2);

    sum = addPoly(poly1, poly2);

    printf("Sum: ");
    displayPoly(sum);

    return 0;
}
