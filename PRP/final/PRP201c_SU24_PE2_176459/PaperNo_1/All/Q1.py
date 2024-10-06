import random

start = int(input("Enter the start of the range: "))
end = int(input("Enter the end of the range: "))
count = int(input("How many unique integers would you like to generate? "))

unique_integers = random.sample(range(start, end), count)
print("Generated list of unique integers:", unique_integers)
even_count = len([num for num in unique_integers if num % 2 == 0])
odd_count = len([num for num in unique_integers if num % 2 != 0])
print("Number of even numbers:", even_count)
print("Number of odd numbers:", odd_count)