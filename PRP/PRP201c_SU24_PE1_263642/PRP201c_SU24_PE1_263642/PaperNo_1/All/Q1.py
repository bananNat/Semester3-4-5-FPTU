import random

start = int(input("Enter the start of the range: "))
end = int(input("Enter the end of the range: "))
count = int(input("How many unique integers would you like to generate? "))

unique_integers = random.sample(range(start, end), count)
print("Generated list of unique integers: ", unique_integers)
print("Average of the integers: ", round(sum(unique_integers)/count, 1))
print("Minimum value in the list: ", min(unique_integers))