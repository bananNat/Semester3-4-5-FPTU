import sqlite3
import csv

def create_tables(connection):
    with connection:
        connection.execute('''
            CREATE TABLE IF NOT EXISTS Employees (
                EmployeeID TEXT PRIMARY KEY,
                EmployeeName TEXT NOT NULL UNIQUE
            )
        ''')
        connection.execute('''
            CREATE TABLE IF NOT EXISTS Projects (
                ProjectID TEXT PRIMARY KEY,
                ProjectName TEXT NOT NULL UNIQUE
            )
        ''')
        connection.execute('''
            CREATE TABLE IF NOT EXISTS EmployeeProjects (
                EmployeeID TEXT,
                ProjectID TEXT,
                PRIMARY KEY (EmployeeID, ProjectID),
                FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID),
                FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID)
            )
        ''')

def insert_data_from_csv(connection, employees_csv, projects_csv, employee_projects_csv):
    with connection:
        with open(employees_csv, 'r') as file:
            reader = csv.reader(file)
            next(reader)
            for row in reader:
                connection.execute('''
                    INSERT OR IGNORE INTO Employees (EmployeeID, EmployeeName) VALUES (?, ?)
                ''', (row[0], row[1]))

        with open(projects_csv, 'r') as file:
            reader = csv.reader(file)
            next(reader)
            for row in reader:
                connection.execute('''
                    INSERT OR IGNORE INTO Projects (ProjectID, ProjectName) VALUES (?, ?)
                ''', (row[0], row[1]))

        with open(employee_projects_csv, 'r') as file:
            reader = csv.reader(file)
            next(reader)
            for row in reader:
                connection.execute('''
                    INSERT OR IGNORE INTO EmployeeProjects (EmployeeID, ProjectID) VALUES (?, ?)
                ''', (row[0], row[1]))

def get_employee_projects(connection, employee_name):
    cursor = connection.cursor()
    cursor.execute('''
        SELECT Projects.ProjectID, Projects.ProjectName
        FROM Employees
        JOIN EmployeeProjects ON Employees.EmployeeID = EmployeeProjects.EmployeeID
        JOIN Projects ON EmployeeProjects.ProjectID = Projects.ProjectID
        WHERE Employees.EmployeeName = ?
    ''', (employee_name,))
    
    projects = cursor.fetchall()
    if projects:
        print(f"Projects assigned to {employee_name}:")
        for project in projects:
            print(f"{project[0]} - {project[1]}")
    else:
        print("No projects found for the employee")

def main():
    connection = sqlite3.connect('company_projects.sqlite')

    create_tables(connection)

    insert_data_from_csv(connection, 'employees.csv', 'projects.csv', 'employee_projects.csv')
    employee_name = input("Enter an employee's name to find their projects: ")
    get_employee_projects(connection, employee_name)

    connection.close()

if __name__ == "__main__":
    main()
