#include <stdio.h>
#include <stdlib.h>

struct node {
    int val, ht;
    struct node *left, *right;
};

int max(int a, int b) { return (a > b) ? a : b; }

int getHt(struct node* n) {
    return n ? n->ht : 0;
}

int getBal(struct node* n) {
    return n ? getHt(n->left) - getHt(n->right) : 0;
}

struct node* newNode(int v) {
    struct node* t = (struct node*)malloc(sizeof(struct node));
    t->val = v;
    t->left = t->right = NULL;
    t->ht = 1;
    return t;
}

struct node* rightRot(struct node* y) {
    struct node* x = y->left;
    struct node* T2 = x->right;
    x->right = y;
    y->left = T2;
    y->ht = max(getHt(y->left), getHt(y->right)) + 1;
    x->ht = max(getHt(x->left), getHt(x->right)) + 1;
    return x;
}

struct node* leftRot(struct node* x) {
    struct node* y = x->right;
    struct node* T2 = y->left;
    y->left = x;
    x->right = T2;
    x->ht = max(getHt(x->left), getHt(x->right)) + 1;
    y->ht = max(getHt(y->left), getHt(y->right)) + 1;
    return y;
}

struct node* insert(struct node* root, int v) {
    if (!root) return newNode(v);
    if (v < root->val) root->left = insert(root->left, v);
    else if (v > root->val) root->right = insert(root->right, v);
    else return root;

    root->ht = 1 + max(getHt(root->left), getHt(root->right));
    int bal = getBal(root);

    if (bal > 1 && v < root->left->val) return rightRot(root);
    if (bal < -1 && v > root->right->val) return leftRot(root);
    if (bal > 1 && v > root->left->val) {
        root->left = leftRot(root->left);
        return rightRot(root);
    }
    if (bal < -1 && v < root->right->val) {
        root->right = rightRot(root->right);
        return leftRot(root);
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
    int a[] = {10, 20, 30, 40, 50, 25};
    for (int i = 0; i < 6; i++)
        root = insert(root, a[i]);
    printf("Inorder traversal of AVL: ");
    inorder(root);
    return 0;
}
