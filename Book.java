package com.mycompany.library;
/**
 * Represents a book that can be loaned
 * Extends the Element class to inherit common properties.
 */
public class Book extends Element{
     private String author;
     private boolean isLoaned;

    /**
     * Constructs a new Book with the specified ID, name and author.
     * @param ID, the unique identifier of the book
     * @param name, the name/title of the book
     * @param author, the book's author
      */
    public Book(String ID, String name, String author) {
        super(ID, name);
        this.author = author;
        this.isLoaned = false;
    }

     /**
     * Gets the author of the book.
     * 
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }
    
     /**
     * Checks if the book is currently loaned.
     * 
     * @return true if the book is loaned, false otherwise
     */
    public boolean isLoaned() {
        return isLoaned;
    }

      /**
     * Sets the loan status of the book.
     * 
     * @param isLoaned, true if the book is loaned false otherwise
     */
    public void setIsLoaned(boolean isLoaned) {
        this.isLoaned = isLoaned;
    }
    
    
}
