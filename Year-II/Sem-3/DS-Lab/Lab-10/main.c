#include <stdio.h>
#include <stdlib.h>

struct node {
    int val;
    struct node *left, *right;
};

struct node* newNode(int v) {
    struct node* t = (struct node*)malloc(sizeof(struct node));
    t->val = v;
    t->left = t->right = NULL;
    return t;
}

struct node* insert(struct node* root, int v) {
    if (!root) return newNode(v);
    if (v < root->val) root->left = insert(root->left, v);
    else root->right = insert(root->right, v);
    return root;
}

void inorder(struct node* root) {
    if (!root) return;
    inorder(root->left);
    printf("%d ", root->val);
    inorder(root->right);
}

void preorder(struct node* root) {
    if (!root) return;
    printf("%d ", root->val);
    preorder(root->left);
    preorder(root->right);
}

void postorder(struct node* root) {
    if (!root) return;
    postorder(root->left);
    postorder(root->right);
    printf("%d ", root->val);
}

int main() {
    struct node* root = NULL;
    int a[] = {50, 30, 70, 20, 40, 60, 80};
    for (int i = 0; i < 7; i++)
        root = insert(root, a[i]);

    printf("Inorder: "); inorder(root);
    printf("\nPreorder: "); preorder(root);
    printf("\nPostorder: "); postorder(root);
    return 0;
}
