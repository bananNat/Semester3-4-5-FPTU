def filter_even_numbers ():
    with open("integers_data.txt", "r") as f:
        data = f.read()
        print("Content of the input file (integers_data.txt):")
        print(data)
        print("Content of the output file (even_numbers.txt):")
        list_data = data.split(" ")
        real_data = []
        even_list = []
        for i in list_data:
            real_data.append(int(i))
        for i in real_data:
            if( i % 2 == 0 ):
                even_list.append(i)
        even_str = " ".join(map(str, even_list))
        print(even_str)
        with open("even_numbers.txt", "w") as file:
            file.write(even_str)


filter_even_numbers()