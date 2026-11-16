#include <stdio.h>
#include <stdlib.h>

struct node {
    int val;
    struct node *left, *right;
};

struct node* newNode(int v) {
    struct node* t = (struct node*)malloc(sizeof(struct node));
    t->val = v; t->left = t->right = NULL;
    return t;
}

struct node* insert(struct node* root, int v) {
    if (!root) return newNode(v);
    if (v < root->val) root->left = insert(root->left, v);
    else root->right = insert(root->right, v);
    return root;
}

int findMin(struct node* root) {
    while (root->left) root = root->left;
    return root->val;
}

int findMax(struct node* root) {
    while (root->right) root = root->right;
    return root->val;
}

int main() {
    struct node* root = NULL;
    int a[] = {40, 20, 60, 10, 30, 50, 70};
    for (int i = 0; i < 7; i++)
        root = insert(root, a[i]);
    printf("Min: %d\n", findMin(root));
    printf("Max: %d\n", findMax(root));
    return 0;
}
