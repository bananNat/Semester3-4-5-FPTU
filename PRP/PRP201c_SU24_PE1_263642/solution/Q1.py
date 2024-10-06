import random

start = int(input("Enter the start of the range: "))
end = int(input("Enter the end of the range: "))
number = int(input("How many unique numbers do you want to generate: "))

array = set()
while len(array) < number:
    array.add(random.randint(start, end))

print("Generated list of unique numbers: ", array)
print("Average of the generated list: ", round(sum(array)/len(array), 1))
print("Minimum number in the list: ", min(array))
