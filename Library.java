package com.mycompany.library;

import java.util.*;

/**
 * Represents a library system that manages users and branches.
 */
public class Library {

    private List<User> users;
    private List<Branch> branches;

    /**
     * Constructs a new Library instance. Initializes empty lists for users and
     * branches.
     */
    public Library() {
        this.users = new ArrayList<>();
        this.branches = new ArrayList<>();
    }

    /**
     * Adds a branch to the library.
     *
     * @param branch the branch to be added
     */
    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    /**
     * Adds a user to the library.
     *
     * @param user the user to be added
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Gets the list of users registered in the library.
     *
     * @return the list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Gets the list of branches in the library.
     *
     * @return the list of branches
     */
    public List<Branch> getBranches() {
        return branches;
    }

    /* ---------------------- METODS ------------------------*/
    /**
     * Searches for a branch by its ID.
     *
     * @param branchID the ID of the branch to search for
     * @return the branch with the specified ID, or null if not found
     */
    public Branch searchBranchByID(String branchID) {
        for (Branch branch : branches) {
            if (branch.getID().equals(branchID)) {
                return branch;
            }
        }
        return null;
    }

    /**
     * Searches for a user by their ID.
     *
     * @param userID the ID of the user to search for
     * @return the user with the specified ID, or null if not found
     */
    public User searchUserByID(String userID) {
        for (User user : users) {
            if (user.getID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Searches for a book by its ID across all branches.
     *
     * @param bookID The unique identifier of the book to search for.
     * @return The book with the specified ID, or null if no such book is found.
     */
    public Book searchBookByID(String bookID) {
        for (Branch branch : branches) {
            Book book = branch.searchBookByID(bookID);
            if (book != null) {
                return book;
            }
        }
        return null;
    }

    /**
     * Calculates the penalty accumulated by a user.
     *
     * @param userID the ID of the user
     * @return the accumulated penalty of the user, or 0 if the user is not
     * found
     */
    public int calculatePenaltyByUser(String userID) {
        User user = searchUserByID(userID);
        if (user != null) {
            return user.getAccumulatedDebts();
        }
        return 0;
    }

    /**
     * Lends a book to a user.
     *
     * @param userID the ID of the user
     * @param bookID the ID of the book
     * @param branchID the ID of the branch
     */
    public void lendBook(String userID, String bookID, String branchID) {
        Branch branch = searchBranchByID(branchID);
        User user = searchUserByID(userID);
        if (user != null && branch != null) {
            branch.loanBook(user, bookID);
        }
    }

   /**
     * Returns a book from a user to a branch.
     * 
     * @param userID the ID of the user returning the book
     * @param bookID the ID of the book being returned
     * @param branchID the ID of the branch to which the book is returned
     * @param dateOfReturn the date the book is returned
     * @return true if the book was successfully returned, false otherwise
     */
    public boolean returnBook(String userID, String bookID, String branchID, Date dateOfReturn) {
        Branch branch = searchBranchByID(branchID);
        User user = searchUserByID(userID);
        Book book = searchBookByID(bookID);
        if (user != null && branch != null && book != null) {
            return branch.returnBook(user, book, dateOfReturn);
        }
        return false;
    }

    /**
     * Returns a string representation of all branches in the library.
     *
     * @return a string with information about all branches
     */
    public String toStringBranches() {
        StringBuilder branchesAvailables = new StringBuilder();
        for (Branch branch : branches) {
            branchesAvailables.append("ID: ").append(branch.getID()).append(", name: ").append(branch.getName()).append("\n");
        }
        return branchesAvailables.toString();
    }

    /**
     * Returns a string representation of all users in the library.
     *
     * @return a string with information about all users
     */
    public String toStringUsers() {
        StringBuilder registeredUsers = new StringBuilder();
        for (User user : users) {
            registeredUsers.append("ID: ").append(user.getID()).append(", Name: ").append(user.getName()).append("\n");
        }
        return registeredUsers.toString();
    }

    /**
     * Gets a list of available (not loaned) books in a branch.
     *
     * @param branchID the ID of the branch
     * @return a list of available books in the branch, or an empty list if the
     * branch is not found
     */
    public List<Book> getAvailableBooks(String branchID) {
        Branch branch = searchBranchByID(branchID);
        List<Book> availableBooks = new ArrayList<>();
        if (branch == null) {
            return availableBooks;
        }
        for (Book book : branch.getBookCollection()) {
            if (!book.isLoaned()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
}
