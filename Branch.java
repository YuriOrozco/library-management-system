package com.mycompany.library;

import java.util.*;

/**
 * Represents a branch of the library. Extends the Element class to inherit
 * common properties.
 */
public class Branch extends Element {

    private List<Book> bookCollection;

    /**
     * Constructs a new Branch with the specified ID and name.
     *
     * @param ID the unique identifier of the branch
     * @param name the name of the branch
     */
    public Branch(String ID, String name) {
        super(ID, name);
        this.bookCollection = new ArrayList<>();
    }

    /**
     * Adds a book to the branch's book collection.
     *
     * @param book the book to be added to the collection
     */
    public void addBook(Book book) {
        this.bookCollection.add(book);
    }

    /**
     * Gets the book collection of the branch.
     *
     * @return the list of books in the branch's collection
     */
    public List<Book> getBookCollection() {
        return this.bookCollection;
    }

    /* ---------------------- METODS ------------------------*/
    /**
     * Search for a book in the collection by its ID.
     *
     * @param ID, the unique identifier of the book to search for.
     * @return the book with the specified ID, or null if not found
     */
    public Book searchBookByID(String ID) {
        for (Book book : bookCollection) {
            if (book.getID().equals(ID)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Loans a book to a user if the book is available
     *
     * @param user, user who wants to borrow the book
     * @param bookID, the unique identifier of the book to be loaned
     */
    public void loanBook(User user, String bookID) {
        Book book = searchBookByID(bookID);
        if (book != null && book.isLoaned() == false) {
            Loan loan = new Loan(user, book, this);
            book.setIsLoaned(true);
            user.addLoan(loan);

        }
    }

    /**
     * Processes the return of a lonaed book to a user
     *
     * @param user, the user returning the book
     * @param book, the book to return
     * @param dateOfReturn, the book's return date
     * @return boolean, true if it was possible to deliver the book, false otherwise
     */
    public boolean returnBook(User user, Book book, Date dateOfReturn) {
        if (book != null && book.isLoaned() == true) {
            boolean foundLoan = false;
            for (Loan loan : user.getLoan()) {
                if (loan.getBook().equals(book) && loan.getDateOfReturn() == null) {
                    loan.setDateOfReturn(dateOfReturn);
                    int penalty = loan.calculatePenalty();
                    if (penalty > 0) {
                        user.addDebts(penalty);
                    }
                    book.setIsLoaned(false);
                    loan.getBranch().removeBook(book);
                    this.addBook(book);
                    foundLoan = true;
                    break;
                }
            }
            return foundLoan;
        }
        return false;
    }

    /**
     * Removes a book from the branch's book collection.
     *
     * @param book the book to be removed from the collection
     * @return true if the book was successfully removed, code false otherwise
     */
    private boolean removeBook(Book book) {
        if (book != null) {
            return bookCollection.remove(book);
        }
        return false;
    }
}
