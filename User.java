package com.mycompany.library;

import java.util.*;

/**
 * Represents a user of the library. Extends the Element class to inherit common
 * properties.
 */
public class User extends Element {

    private int accumulatedDebts;
    private List<Loan> loan;

    /**
     * Constructs a new User with the specified ID and name. Initializes the
     * user with no debts and an empty list of loans.
     *
     * @param ID the unique identifier of the user
     * @param name the name of the user
     */
    public User(String ID, String name) {
        super(ID, name);
        this.accumulatedDebts = 0;
        this.loan = new ArrayList<>();
    }

    /**
     * Adds a debt to the user's accumulated debts.
     *
     * @param debt the amount of debt to be added
     */
    public void addDebts(int debt) {
        this.accumulatedDebts += debt;
    }

    /**
     * Gets the user's accumulated debts.
     *
     * @return the user's accumulated debts
     */
    public int getAccumulatedDebts() {
        return accumulatedDebts;
    }

    /**
     * Adds a loan to the user's list of loans.
     *
     * @param loan the loan to be added
     */
    public void addLoan(Loan loan) {
        this.loan.add(loan);
    }

    /**
     * Gets the user's list of loans.
     *
     * @return the user's list of loans
     */
    public List<Loan> getLoan() {
        return loan;
    }
}
