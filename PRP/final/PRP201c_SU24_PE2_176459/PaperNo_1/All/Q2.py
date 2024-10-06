def is_prime(n):
    if n <= 1:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False
    for i in range(3, int(n**0.5) + 1, 2):
        if n % i == 0:
            return False
    return True

def filter_prime_numbers():
    with open('integers_data.txt', 'r') as infile:
        numbers = infile.read().split()
    numbers = list(map(int, numbers))
    print("Original list of numbers:", numbers)
    prime_numbers = [num for num in numbers if is_prime(num)]
    print("List of prime numbers:", prime_numbers)
    with open('prime_numbers.txt', 'w') as outfile:
        outfile.write(" ".join(map(str, prime_numbers)))

filter_prime_numbers()