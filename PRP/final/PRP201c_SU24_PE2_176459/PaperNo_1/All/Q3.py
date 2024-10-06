import sqlite3
import csv

def create_tables(connection):
    with connection:
        connection.execute('''
            CREATE TABLE IF NOT EXISTS Books (
                BookID TEXT PRIMARY KEY,
                BookTitle TEXT NOT NULL UNIQUE
            )
        ''')
        connection.execute('''
            CREATE TABLE IF NOT EXISTS Members (
                MemberID TEXT PRIMARY KEY,
                MemberName TEXT NOT NULL UNIQUE
            )
        ''')
        connection.execute('''
            CREATE TABLE IF NOT EXISTS Borrowings (
                MemberID TEXT,
                BookID TEXT,
                BorrowDate TEXT,
                PRIMARY KEY (MemberID, BookID),
                FOREIGN KEY (MemberID) REFERENCES Members(MemberID),
                FOREIGN KEY (BookID) REFERENCES Books(BookID)
            )
        ''')

def insert_data_from_csv(connection, members_csv, books_csv, borrowings_csv):
    with connection:
        with open(members_csv, 'r') as file:
            reader = csv.reader(file)
            next(reader)
            for row in reader:
                connection.execute('''
                    INSERT OR IGNORE INTO Members (MemberID, MemberName) VALUES (?, ?)
                ''', (row[0], row[1]))

        with open(books_csv, 'r') as file:
            reader = csv.reader(file)
            next(reader)
            for row in reader:
                connection.execute('''
                    INSERT OR IGNORE INTO Books (BookID, BookTitle) VALUES (?, ?)
                ''', (row[0], row[1]))

        with open(borrowings_csv, 'r') as file:
            reader = csv.reader(file)
            next(reader)
            for row in reader:
                connection.execute('''
                    INSERT OR IGNORE INTO Borrowings (MemberID, BookID, BorrowDate) VALUES (?, ?, ?)
                ''', (row[0], row[1], row[2]))

def get_employee_projects(connection, member_name):
    cursor = connection.cursor()
    cursor.execute('''
        SELECT Books.BookID, Books.BookTitle, Borrowings.BorrowDate
        FROM Books
        JOIN Borrowings ON Books.BookID = Borrowings.BookID
        JOIN Members ON Members.MemberID = Borrowings.MemberID
        WHERE Members.MemberName = ?
    ''', (member_name,))
    
    books = cursor.fetchall()
    if books:
        print(f"Books borrowed by {member_name}:")
        for book in books:
            print(f"{book[0]} - {book[1]} (Borrowed on {book[2]})")
    else:
        print(f"No books found for the member {member_name}")

def main():
    connection = sqlite3.connect('library_borrowing.sqlite')

    create_tables(connection)

    insert_data_from_csv(connection, 'members.csv', 'books.csv', 'borrowings.csv')
    member_name = input("Enter a member's name to find their borrowed books: ")
    get_employee_projects(connection, member_name)

    connection.close()

if __name__ == "__main__":
    main()