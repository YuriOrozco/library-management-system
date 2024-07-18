package com.mycompany.library;

import com.mycompany.library.Branch;

import java.util.*;
import java.text.*;

/**
 * Manages the library system operations, including adding branches, books,
 * users, lending and returning books, and handling user debts.
 */
public class LibraryManagement {

    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Main method to run the library management system.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        LibraryManagement management = new LibraryManagement();
        management.run();
    }

    /**
     * Runs the main loop to display the menu and process user options.
     */
    private void run() {
        int option = 0;
        while (option != 8) {
            showMenu();
            try {
                option = scanner.nextInt();
                processOption(option);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());

            }
        }
    }

    /**
     * Displays the options menu.
     */
    private void showMenu() {
        System.out.println("----------------------------------");
        System.out.println("OPTIONS MENU");
        System.out.println("1. Add a Branch");
        System.out.println("2. Add a Book");
        System.out.println("3. Add an User");
        System.out.println("4. Lend a Book");
        System.out.println("5. Return a Book");
        System.out.println("6. Get debts");
        System.out.println("7. View loans");
        System.out.println("8. Exit");
        System.out.print("Please, select an option: ");
    }

    /**
     * Processes the selected menu option.
     *
     * @param option the selected option by the user
     */
    private void processOption(int option) {
        switch (option) {
            case 1 ->
                addBranch();
            case 2 ->
                addBook();
            case 3 ->
                addUsers();
            case 4 ->
                lendBook();
            case 5 ->
                returnBook();
            case 6 ->
                getDebts();
            case 7 ->
                viewLoans();
            case 8 ->
                System.out.println("Exiting the system...");
            default ->
                System.out.println("Invalid option, please try again.");

        }
    }

    /**
     * Adds new branches to the library.
     */
    private void addBranch() {
        System.out.print("How many branches would you like to add? ");
        int branches = scanner.nextInt();
        for (int i = 1; i <= branches; i++) {
            System.out.println("Enter details for branch " + i + ":");
            System.out.print("ID: ");
            String ID = scanner.next();
            scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();

            Branch branch = new Branch(ID, name);
            library.addBranch(branch);
        }
        System.out.println("*****All good!!*****");
    }

    /**
     * Adds new books to a branch in the library.
     */
    private void addBook() {
        if (!hasBranches()) {
            return;
        }

        String branch = getValidBranch();

        System.out.print("How many books would you like to add to this branch? ");
        int books = scanner.nextInt();
        for (int i = 1; i <= books; i++) {
            System.out.print("ID: ");
            String ID = scanner.next();
            scanner.nextLine();
            System.out.print("Book's title: ");
            String title = scanner.nextLine();
            System.out.print("Author's name: ");
            String author = scanner.nextLine();

            Book book = new Book(ID, title, author);
            library.searchBranchByID(branch).addBook(book);
        }

        System.out.println("Do you like to add books in another branch? (Y/N)");
        char selection = scanner.next().charAt(0);
        if (selection == 'Y' || selection == 'y') {
            addBook();
        }
        System.out.println("*****All good!!*****");
    }

    /**
     * Prompts the user to enter a valid branch ID.
     *
     * @return the valid branch ID
     */
    private String getValidBranch() {
        System.out.println("In which branch would you like to add the books?");
        System.out.println(library.toStringBranches());
        System.out.print("BranchID: ");
        String branchID = scanner.next();
        scanner.nextLine();

        Branch branch = library.searchBranchByID(branchID);
        while (branch == null) {
            System.out.println("Please enter an existing branch id!!");
            branchID = scanner.next();
            scanner.nextLine();
            branch = library.searchBranchByID(branchID);
        }
        return branchID;
    }

    /**
     * Adds new users to the library.
     */
    private void addUsers() {
        System.out.print("How many users would you like to add?");
        int users = scanner.nextInt();
        for (int i = 1; i <= users; i++) {
            addUser();
        }
    }

    /**
     * Adds a single user to the library.
     *
     * @return the ID of the added user
     */
    private String addUser() {
        System.out.print("ID: ");
        String ID = scanner.next();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();

        User user = new User(ID, name);
        library.addUser(user);
        System.out.println("*****All good!!*****");
        return ID;
    }

    /**
     * Lends books to a user from a branch.
     */
    private void lendBook() {
        if (!hasBranches()) {
            return;
        }
        String branchID = selectBranch();
        if (branchID == null) {
            return;
        }
        List<Book> availableBooks = library.getAvailableBooks(branchID);
        if (availableBooks.isEmpty()) {
            System.out.println("No books available!");
            return;
        }
        String userID = selectOrAddUser();
        if (userID == null) {
            return;
        }
        lendBooksToUser(userID, branchID, availableBooks);
    }

    /**
     * Checks if there are any branches in the library.
     *
     * @return true if there are branches, false otherwise
     */
    private boolean hasBranches() {
        if (library.getBranches().isEmpty()) {
            System.out.println("There must be at least one branch!");
            return false;
        }
        return true;
    }

    /**
     * Prompts the user to select a branch by ID.
     *
     * @return the selected branch ID, or null if the branch is not found
     */
    private String selectBranch() {
        System.out.print("Enter branch ID: ");
        String branchID = scanner.next();
        if (library.searchBranchByID(branchID) == null) {
            System.out.println("Branch not found.");
            return null;
        }
        return branchID;
    }

    /**
     * Prompts the user to select or add a user by ID.
     *
     * @return the user ID, or null if the user is not found and not added
     */
    private String selectOrAddUser() {
        System.out.print("Enter the user ID: ");
        String userID = scanner.next();
        User user = library.searchUserByID(userID);
        if (user == null) {
            System.out.println("The user with this ID does not exist, would you like to add a new user?(Y/N)");
            char selection = scanner.next().charAt(0);
            if (selection == 'Y' || selection == 'y') {
                return addUser();
            } else {
                System.out.println("Returning to the options menu...");
                return null;
            }
        }
        return userID;
    }

    /**
     * Lends selected books to a user.
     *
     * @param userID the ID of the user borrowing the books
     * @param branchID the ID of the branch lending the books
     * @param availableBooks the list of available books in the branch
     */
    private void lendBooksToUser(String userID, String branchID, List<Book> availableBooks) {
        System.out.println("Excellent, books are available!\n");
        System.out.println("Available books: ");
        for (Book book : availableBooks) {
            System.out.println("ID: " + book.getID() + ", title: " + book.getName() + ", author: " + book.getAuthor());
        }
        System.out.print("How many books would you like to acquire? ");
        int numberOfBooks = scanner.nextInt();
        while (numberOfBooks > availableBooks.size()) {
            System.out.println("The number should be according to the availability of books.");
            numberOfBooks = scanner.nextInt();
        }
        for (int i = 1; i <= numberOfBooks; i++) {
            System.out.print("Enter the ID of the " + i + " to acquire: ");
            String ID = scanner.next();
            library.lendBook(userID, ID, branchID);
            System.out.println("*****All good!!*****");
        }

    }

    /**
     * Returns a book from a user to a branch.
     */
    private void returnBook() {
        if (!hasBranches()) {
            return;
        }
        String branchID = selectBranch();
        if (branchID == null) {
            return;
        }
        String userID = selectUser();
        if (userID == null) {
            return;
        }
        String bookID = selectBook();
        if (bookID == null) {
            return;
        }
        if (!library.searchBookByID(bookID).isLoaned()) {
            System.out.println("This book wasn't loaned");
            return;
        }

        Date dateOfReturn = returnDate();
        processReturn(userID, bookID, branchID, dateOfReturn);
    }

    /**
     * Prompts the user to select a user by ID.
     *
     * @return the selected user ID
     */
    private String selectUser() {
        System.out.print("Enter the user ID: ");
        String userID = scanner.next();
        User user = library.searchUserByID(userID);
        while (user == null) {
            System.out.print("User not found, please enter a valid ID");
            userID = scanner.next();
            user = library.searchUserByID(userID);
        }
        return userID;
    }

    /**
     * Prompts the user to select a book by ID.
     *
     * @return the selected book ID
     */
    private String selectBook() {
        System.out.print("Enter the book ID you want to return: ");
        String bookID = scanner.next();
        Book book = library.searchBookByID(bookID);
        while (book == null) {
            System.out.println("Book not found, please enter a valid ID");
            bookID = scanner.next();
            book = library.searchBookByID(bookID);
        }
        return bookID;
    }

    /**
     * Prompts the user to enter a valid return date.
     *
     * @return the return date
     */
    private Date returnDate() {
        Date dateOfReturn = null;
        boolean validDate = false;
        while (!validDate) {
            System.out.print("Enter the return date (dd/MM/yyyy): ");
            String dateInput = scanner.next();
            try {
                dateOfReturn = dateFormat.parse(dateInput);
                validDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in dd/MM/yyyy format.");
            }
        }
        return dateOfReturn;
    }

    /**
     * Processes the return of a book.
     *
     * @param userID the ID of the user returning the book
     * @param bookID the ID of the book being returned
     * @param branchID the ID of the branch receiving the book
     * @param dateOfReturn the date the book is returned
     */
    private void processReturn(String userID, String bookID, String branchID, Date dateOfReturn) {
        boolean success = library.returnBook(userID, bookID, branchID, dateOfReturn);
        if (success) {
            System.out.println("Book returned succesfully!!");
        } else {
            System.out.println("Error");
        }
        int fine = library.searchUserByID(userID).getAccumulatedDebts();
        if (fine > 0) {
            System.out.println("You have accumulated a fine of $" + fine);
        }
    }

    /**
     * Checks if there are any users in the library.
     *
     * @return true if there are users, false otherwise
     */
    private boolean hasUsers() {
        if (library.getUsers().isEmpty()) {
            System.out.println("There must be at least one user");
            return false;
        }
        return true;
    }

    /**
     * Retrieves and displays user debts.
     */
    private void getDebts() {
        if (!hasUsers()) {
            return;
        }
        int option = 0;
        while (option != 3) {
            debtsMenu();
            option = scanner.nextInt();
            debtsOptions(option);
        }
    }

    /**
     * Displays all user loans.
     */
    private void viewLoans() {
        if (!hasUsers()) {
            return;
        }
        for (User user : library.getUsers()) {
            String ID = user.getID();
            String name = user.getName();
            List<Loan> loans = user.getLoan();
            if (loans.isEmpty()) {
                System.out.println("No loans found for the user: " + name);
            } else {
                System.out.println("Loans for user " + name + ":");
                for (Loan loan : loans) {
                    System.out.println("Book ID: " + loan.getBook().getID()
                            + ", Title: " + loan.getBook().getName()
                            + ", Borrowed from: " + loan.getBranch().getName()
                            + ", Borrowed on: " + loan.getDateOfLoan()
                            + ", Returned on: " + (loan.getDateOfReturn() != null ? loan.getDateOfReturn() : "Not returned yet"));
                }
            }
        }

    }

    /**
     * Displays the debts menu.
     */
    private void debtsMenu() {
        System.out.println("------------------------------");
        System.out.println("1. Obtain debts from all users");
        System.out.println("2. Get user debts");
        System.out.println("3. Main menu");
        System.out.print("Select an option: ");
    }

    /**
     * Processes the selected debts menu option.
     *
     * @param option the selected option
     */
    private void debtsOptions(int option) {
        switch (option) {
            case 1 ->
                getAllDebts();
            case 2 ->
                getDebtsByUser();
            case 3 -> {
                System.out.println("Returning to the main menu...");
                return;
            }
            default ->
                System.out.println("Invalid option, please try again.");
        }
    }

    /**
     * Retrieves and displays debts for all users.
     */
    private void getAllDebts() {
        List<User> users = library.getUsers();
        for (User user : users) {
            String userID = user.getID();
            String userName = user.getName();
            int debt = library.calculatePenaltyByUser(userID);

            System.out.println("ID: " + userID + ", name: " + userName + ", debt: $" + debt);
        }

    }

    /**
     * Retrieves and displays debts for a specific user.
     */
    private void getDebtsByUser() {
        System.out.println("Please, provide the userID");
        String userID = scanner.next();
        User user = library.searchUserByID(userID);
        while (user == null) {
            System.out.println("Please, enter a valid ID");
            userID = scanner.next();
            user = library.searchUserByID(userID);
        }
        int debt = library.calculatePenaltyByUser(userID);
        System.out.println("ID: " + user.getID() + ", name: " + user.getName() + ", debt $" + debt);
    }

}
