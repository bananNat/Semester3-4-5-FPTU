def filter_even_numbers():
    with open('integers_data.txt', 'r') as infile:
        contents = infile.read()
        numbers = contents.split()
        
        numbers = [int(num) for num in numbers]
        
        even_numbers = [num for num in numbers if num % 2 == 0]
        
    with open('even_numbers.txt', 'w') as outfile:
        outfile.write(' '.join(map(str, even_numbers)))

filter_even_numbers()
