# Operating Systems Lab

Semester 4 - MIT Manipal, Data Science & Engineering.

## What this covers

Operating Systems lab. Starts with Unix/Linux command-line and text-processing exercises recorded as transcripts, moves into shell scripting, then into C programs against POSIX system calls: fork/exec/wait and zombie processes, POSIX threads, simulations of CPU scheduling policies, and inter-process communication using message queues, pipes and shared memory.

Topics: Unix/Linux commands, shell scripting, process control system calls, pthreads, CPU scheduling simulation, IPC.

## Contents

### Lab 1, 2 and 3 - Unix commands, text processing and shell scripts

The command-line weeks. Lab 1 and Lab 2 are recorded exercise transcripts with the data files they operated on - file and directory commands, then text processing with grep, cut and sort over student record files. Lab 3 is the first shell script. The practice folder holds extra scripting attempts and the guidelines the lab was run under.

## Folders

- [`Lab-01/`](Lab-01) - 3 Texts, 1 PDF, 1 Word document, and a few others
- [`Lab-02/`](Lab-02) - 4 Texts, 3 Files, 1 PDF, and a few others
- [`Lab-03/`](Lab-03) - 1 PDF, 1 Shell script

## Requirements

Linux, or WSL on Windows - the system calls and SysV IPC headers used here are not available on native Windows. GCC, and -lpthread for the threaded programs. Compiled binaries are not committed, so build each program before running it.

## How to run

```sh
gcc q1.c -o q1
./q1

gcc q4.c -o q4 -lpthread     # threaded programs
bash lab3.sh                 # shell script exercises
```

`Lab-Manual.pdf` in this folder is the lab manual these exercises were set from.

---

Part of my college lab work, shared openly - juniors and classmates are welcome
to use it.
