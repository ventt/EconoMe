# EconoMe
EconoMe is a personal finance management application that allows users to track their income, expenses, and investments. The application provides users with insights into their spending habits and helps them make informed financial decisions. EconoMe is designed to be user-friendly and intuitive, making it easy for users to manage their finances effectively.

**The application is CLI based**
- For help, type `help` in the terminal
- In list view, help shows the available commands for list view and sorting settings
## Features
- Add incomes and expenses
- Add categories transactions(income, expenses)
- List income, expenses, all transactions, all transactions by category
- Sort transactions by date or amount

## Additional information
- The application is CLI based and not using any external third-party libraries
- Persistence is achieved by using a file-based system (creates JSON files)
  - The first time you create income,expense or category it will create JSON files in the root directory after exiting the application with `exit` command
- Exit = save and exit

## TL;DR
- Currency is not supported
  - For a reason
- The application's structure is Domain-Driven Design (DDD)
- Own implementation of the repository pattern
- Own implementation of the command pattern (visitor pattern)
- Trying to follow SOLID and Clean Code principles
