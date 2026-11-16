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

int search(struct node* root, int key) {
    if (!root) return 0;
    if (root->val == key) return 1;
    if (key < root->val) return search(root->left, key);
    return search(root->right, key);
}

int main() {
    struct node* root = NULL;
    int a[] = {40, 20, 60, 10, 30, 50, 70};
    for (int i = 0; i < 7; i++)
        root = insert(root, a[i]);
    int key = 30;
    if (search(root, key)) printf("%d found in tree", key);
    else printf("%d not found", key);
    return 0;
}
