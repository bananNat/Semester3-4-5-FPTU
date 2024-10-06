# import sqlite3
# import csv

# def create_tables(connection):
#     with connection:
#         connection.execute('''
#             CREATE TABLE IF NOT EXISTS Library (
#                 EmployeeID TEXT PRIMARY KEY,
#                 EmployeeName TEXT NOT NULL UNIQUE
#             )
#         ''')

# def insert_data_from_csv(connection, employees_csv, projects_csv, employee_projects_csv):
#     with connection:
#         with open(employees_csv, 'r') as file:
#             reader = csv.reader(file)
#             next(reader)
#             for row in reader:
#                 connection.execute('''
#                     INSERT OR IGNORE INTO Employees (EmployeeID, EmployeeName) VALUES (?, ?)
#                 ''', (row[0], row[1]))

# def get_employee_projects(connection, employee_name):
#     cursor = connection.cursor()
#     cursor.execute('''
#         SELECT Projects.ProjectID, Projects.ProjectName
#         FROM Employees
#         JOIN EmployeeProjects ON Employees.EmployeeID = EmployeeProjects.EmployeeID
#         JOIN Projects ON EmployeeProjects.ProjectID = Projects.ProjectID
#         WHERE Employees.EmployeeName = ?
#     ''', (employee_name,))
    
#     projects = cursor.fetchall()
#     if projects:
#         print(f"Projects assigned to {employee_name}:")
#         for project in projects:
#             print(f"{project[0]} - {project[1]}")
#     else:
#         print("No projects found for the employee")

# def main():
#     connection = sqlite3.connect('library.sqlite')

#     create_tables(connection)

#     insert_data_from_csv(connection, 'employees.csv', 'projects.csv', 'employee_projects.csv')
#     employee_name = input("Enter an employee's name to find their projects: ")
#     get_employee_projects(connection, employee_name)

#     connection.close()

# if __name__ == "__main__":
#     main()


import random

while True:
    try:
        number = int(input("How many random floating-point numbers would you like to generate? "))
    except:
        print("Invalid input. Please enter an integer.")
        continue
    if number <= 0:
        print("Please enter a number greater than 0.")
        continue
    break
while True:
    lower = float(input("Enter the lower bound of the range: "))
    upper = float(input("Enter the upper bound of the range: "))
    if lower >= upper:
        print("Invalid range. The lower bound must be less than the upper bound.")
        continue
    break

float_list = []
for i in range(number):
    float_list.append(round(random.uniform(lower, upper), 2))
print("Generated list of floating-point numbers:")
print(float_list)
print("Average of the numbers: ", round(sum(float_list)/len(float_list), 2))
print("Minimum value in the list: ", min(float_list))
