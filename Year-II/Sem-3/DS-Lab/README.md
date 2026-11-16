# Data Structures Lab

Semester 3 - MIT Manipal, Data Science & Engineering.

## What this covers

Second-year Data Structures lab. C programs covering pointer arithmetic and dynamic memory, singly/doubly/circular linked lists, stacks and their applications, queues, elementary sorting algorithms and binary search trees. Several folders are Code::Blocks projects, so the same program often appears both as a loose .c file and inside a project directory.

Topics: Pointers, dynamic memory, linked lists, stacks, queues, sorting, binary search trees.

## Contents

### Lab 1 and Lab 2 - pointers and dynamic memory

Opens the Data Structures lab. Lab 1 is the introductory submission, kept as the written report. Lab 2 is the first set of C programs: finding the smallest element of an array through a pointer, a recursive selection sort that swaps via pointers, and dynamic allocation of two 2-D matrices followed by their multiplication - all three allocating their storage with malloc and freeing it before exit.

### Lab 2 practice set - linked lists, stacks and sorting

Sixteen standalone practice programs written alongside Lab 2, each in its own Code::Blocks project folder. They cover singly, doubly and circular linked lists, stack-based balanced-parenthesis checking and duplicate-parenthesis removal, postfix handling, insertion and selection sort, polynomial addition, finding the maximum of two matrices, and struct-based record exercises. Two folders (lab_midsem, lab_midsem_2) are midsem revision attempts.

### Labs 3-6 submissions and later practice

The Lab 3 to Lab 6 submissions, which exist only as the written reports - the code for those weeks was written in the lab and submitted as a document rather than kept as source files. Also includes two later practice projects: a December 2025 revision program and a queue implementation written while preparing for the endsem.

### Lab 7 and Lab 10 - stack applications and binary search trees

Lab 7 implements a character stack with push/pop/peek/isEmpty/isFull and uses it three ways: checking whether a word is a palindrome, validating bracket matching in an expression, and converting infix to postfix then evaluating the result with operator precedence. Lab 10 builds a binary search tree with recursive insert and prints inorder, preorder and postorder traversals; the numbered main variants are successive attempts at the same exercise.

## Folders

- [`Lab-01/`](Lab-01) - 1 Word document, 1 PDF
- [`Lab-02/`](Lab-02) - 22 C sources, 1 PDF
- [`Lab-03/`](Lab-03) - 1 PDF, 1 Word document
- [`Lab-04/`](Lab-04) - 1 PDF
- [`Lab-05/`](Lab-05) - 1 PDF
- [`Lab-06/`](Lab-06) - 1 PDF
- [`Lab-07/`](Lab-07) - 3 C sources, 1 Archive
- [`Lab-10/`](Lab-10) - 5 C sources, and a few others
- [`practice-december-2025/`](practice-december-2025) - 1 C source
- [`practice-endsem/`](practice-endsem) - 1 C source

## Requirements

A C compiler (GCC/MinGW, or MSVC). Code::Blocks is optional - folders containing a .cbp file can be opened directly in it. No external libraries are used; every program is plain C99 and reads its input from stdin.

## How to run

```sh
gcc main.c -o main
./main        # on Windows: main.exe

# or open the folder's .cbp file in Code::Blocks and press F9
```

`Lab-Manual.pdf` in this folder is the lab manual these exercises were set from.

---

Part of my college lab work, shared openly - juniors and classmates are welcome
to use it.
