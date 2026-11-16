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

struct node* minValNode(struct node* node) {
    struct node* cur = node;
    while (cur && cur->left) cur = cur->left;
    return cur;
}

struct node* delNode(struct node* root, int key) {
    if (!root) return root;
    if (key < root->val) root->left = delNode(root->left, key);
    else if (key > root->val) root->right = delNode(root->right, key);
    else {
        if (!root->left) {
            struct node* tmp = root->right;
            free(root);
            return tmp;
        } else if (!root->right) {
            struct node* tmp = root->left;
            free(root);
            return tmp;
        }
        struct node* tmp = minValNode(root->right);
        root->val = tmp->val;
        root->right = delNode(root->right, tmp->val);
    }
    return root;
}

void inorder(struct node* root) {
    if (!root) return;
    inorder(root->left);
    printf("%d ", root->val);
    inorder(root->right);
}

int main() {
    struct node* root = NULL;
    int a[] = {50, 30, 70, 20, 40, 60, 80};
    for (int i = 0; i < 7; i++)
        root = insert(root, a[i]);
    printf("Inorder before delete: ");
    inorder(root);
    root = delNode(root, 50);
    printf("\nInorder after delete: ");
    inorder(root);
    return 0;
}
