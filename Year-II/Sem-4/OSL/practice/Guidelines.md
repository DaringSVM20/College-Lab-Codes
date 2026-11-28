# Shell Scripting MO (Windows + VS Code)

A short, practical reference for running **Bash scripts on Windows** without breaking your sanity.

---

## Core Rule

**VS Code is for writing. Git Bash is for running.**

Do not rely on Code Runner for `.sh` execution.

---

## Standard Workflow (Always Follow)

1. **Write your script** in VS Code
2. **Save the file**
3. **Open Git Bash terminal** in VS Code
4. `cd` to the script directory
5. Run:

   ```bash
   ./script.sh
   ```

---

## Script Template (Use This)

```bash
#!/usr/bin/env bash
set -euo pipefail

# your code here
echo "Hello, World!"
```

---

## One-Time Setup per Script

Make the script executable:

```bash
chmod +x script.sh
```

---

## Debugging

If something fails:

```bash
bash -x script.sh
```

This shows every command as it runs.

---

## Path Rules (Windows Survival)

* Always quote paths:

  ```bash
  "/c/Users/Admin/Some Folder"
  ```
* Avoid hardcoding Windows paths inside logic

---

## Shebang Rule

Always use:

```bash
#!/usr/bin/env bash
```

Portable across Linux, macOS, WSL.

---

## Mental Model

You are learning **Linux Bash**, not Windows scripting.

Windows is just the launchpad.

---

## Known Limitation

* **Code Runner will fail** for `.sh` files on Windows
* Errors like:

  ```text
  The system cannot find the path specified
  ```

  usually mean environment issues, not script issues

---

## When to Level Up

Move to **WSL** when scripts become larger or system-level.

Same Bash. No compromises.

---

## Summary

* Write in VS Code
* Run in Git Bash
* Trust Bash, not Code Runner

Done.
