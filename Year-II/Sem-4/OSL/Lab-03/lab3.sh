# 1. Write a shell script to find whether a given file is the directory or regular file.

echo "Enter the file path:"
read file_path

if [ -d "$file_path" ]; then
    echo "$file_path is a directory."
elif [ -f "$file_path" ]; then
    echo "$file_path is a regular file."
else
    echo "$file_path is neither a directory nor a regular file."
fi

# 2. Write a shell script to list all files (only file names) containing the input pattern (string) in the folder entered by the user.
echo
echo "Enter folder path:"
read folder
echo "Enter pattern to search:"
read pattern
if [ -d "$folder" ]; then
    grep -rl -- "$pattern" "$folder" 2>/dev/null | while IFS= read -r f; do
        echo "$(basename "$f")"
    done
else
    echo "Folder not found."
fi

# 3. Write a shell script to replace all files with .txt extension with .text in the current directory. This has to be done recursively i.e if the current folder contains a folder “OS” with abc.txt then it has to be changed to abc.text ( Hint: use find, mv )
echo
echo "Renaming .txt to .text recursively in current directory..."
find . -type f -name "*.txt" -print0 | while IFS= read -r -d '' f; do
    new="${f%.txt}.text"
    mv -- "$f" "$new"
    echo "Renamed: $f -> $new"
done

# 4. Write a shell script to calculate the gross salary. GS=Basics + TA + 10% of Basics. Floating point calculations has to be performed.
echo
echo "Enter Basics:"
read basics
echo "Enter TA:"
read ta
gs=$(echo "$basics + $ta + (0.10 * $basics)" | bc -l)
printf "Gross Salary: %.2f\n" "$gs"

# 5. Write a program to copy all the files (having file extension input by the user) in the current folder to the new folder input by the user. ex: user enter .text TEXT then all files with .text should be moved to TEXT folder. This should be done only at single level. i.e if the current folder contains a folder name ABC which has .txt files then these files should not be copied to TEXT. Write a shellscript to modify all occurrences of “ex:” with “Example:” in all the files present in current folder only if “ex:” occurs at the start of the line or after a period (.). Example: if a file contains a line: “ex: this is first occurrence so should be re- placed” and “second ex: should not be replaced as it occurs in the middle of the sen-tence.”
echo
echo "Enter file extension (e.g., .text):"
read ext
echo "Enter destination folder name:"
read dest
mkdir -p -- "$dest"
# copy only files in current directory (single level)
for f in *"$ext"; do
    [ -f "$f" ] && cp -- "$f" "$dest"/
done
echo "Copied files with $ext to $dest (single level)."

# Replace 'ex:' with 'Example:' only at start of line or after a period in files of current folder
for f in ./*; do
    [ -f "$f" ] || continue
    # Only start-of-line 'ex:' or after '.' followed by optional space
    sed -i -E 's/^ex:/Example:/g; s/(\.)[[:space:]]*ex:/\1 Example:/g' "$f"
done
echo "Performed 'ex:' -> 'Example:' replacements where applicable."

# 6. Write a shell script which deletes all the even numbered lines in a text file.
echo
echo "Enter text file path to delete even lines:"
read even_file
if [ -f "$even_file" ]; then
    awk 'NR % 2 == 1' "$even_file"
else
    echo "File not found."
fi