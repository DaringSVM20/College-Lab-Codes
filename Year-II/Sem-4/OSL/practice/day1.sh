#!/usr/bin/env bash

read -p "Enter your country: " country

if [[ "$country" == "India" ]]
then
    echo "Namaste"

elif [[ "$country" == "USA" ]]
then
    echo "Hello"

else
    echo "Greetings from Earth"

fi

echo

echo "Hey choose an option"
echo "a - See current date"
echo "b - List all files in current directory"

read choice

echo

case $choice in
    a) date ;;
    b) ls -la ;;
    *) echo "Invalid choice" ;;

esac

echo

for i in {5..15}
do
    echo "Number is $i"
done

echo

for j in Raju Shaam Baburao
do
    echo "Hello $j"
done

# items="/C:/Users/Admin/Documents/MIT MANIPAL/YEAR II/Sem 4/OSL/practice/file.txt"

# for item in $(cat $items)
# do
#     echo $item
# done

echo

count=0
num=10

while [ $count -le $num ]
do
    echo "Numbers are $count"
    let count++
done

# while true
# do
#     echo
#     echo "Hi"
#     sleep 0.5s
# done


echo 


num1=10
until [ $num1 -eq 1 ]
do
    echo "Number is $num1"
    num1=`expr $num1 - 1`
done

echo

for i in 1 2 3
do
    echo $i
    continue
    echo "This will not be printed"
done

echo
echo "The name of the script is ${0}"

echo

function myfun() {
    echo "Hi"
}

myfun

echo

addition() {
    local num2=$1
    local num3=$2
    let sum=$num2+$num3
    echo "Sum of $num2 and $num3 is $sum"
}

addition 5 10


echo


echo "Number of arguments passed is $#"
echo
echo "All arguments passed are $*"
echo
echo "First argument is $1"
echo "Second argument is $2"

echo
echo UID is $UID