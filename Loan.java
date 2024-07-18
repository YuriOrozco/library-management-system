package com.mycompany.library;
import java.util.*;

/**
 * Represents a loan of a book from a branch to a user.
 */
public class Loan {

    private final int MILISECONDSBYDAY = 86400000;
    private final int PENALTY = 10;

    private Date dateOfReturn;
    private Date dateOfLoan;
    private User user;
    private Book book;
    private Branch branch;

    /**
     * Constructs a new Loan instance.
     *
     * @param user the user who borrows the book
     * @param book the book being borrowed
     * @param branch the branch from which the book is borrowed
     */
    public Loan(User user, Book book, Branch branch) {
        this.user = user;
        this.book = book;
        this.branch = branch;
        this.dateOfLoan = new Date();
    }

    /**
     * Gets the return date of the loan.
     *
     * @return the return date of the loan
     */
    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    /**
     * Sets the return date of the loan.
     *
     * @param dateOfReturn the return date to set
     */
    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    /**
     * Gets the loan date.
     *
     * @return the loan date
     */
    public Date getDateOfLoan() {
        return dateOfLoan;
    }

    /**
     * Gets the user who borrowed the book.
     *
     * @return the user who borrowed the book
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the book being borrowed.
     *
     * @return the book being borrowed
     */
    public Book getBook() {
        return book;
    }

    /**
     * Gets the branch from which the book is borrowed.
     *
     * @return the branch from which the book is borrowed
     */
    public Branch getBranch() {
        return branch;
    }

    /* ---------------------- METODS ------------------------*/
    /**
     * Calculate the penalty for late return of the book.
     *
     * @return The amount of the penalty. Returns 0 if the book has not been
     * returned or if the return is within the allowed period.
     */
    public int calculatePenalty() {
        if (dateOfReturn == null) {
            return 0;
        }
        int daysElapsed = (int) ((dateOfReturn.getTime() - dateOfLoan.getTime()) / MILISECONDSBYDAY);

        if (daysElapsed > 15) {
            return (daysElapsed - 15) * PENALTY;
        }
        return 0;
    }
}
