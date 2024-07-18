package com.mycompany.library;

/**
 * Represents a generic element in the library. This class
 * provides common properties for library elements.
 */
public class Element {

    private String ID;
    private String name;

    /**
     * Constructs a new Element with the specified ID and name.
     *
     * @param ID the unique identifier of the element
     * @param name the name of the element
     */
    public Element(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    /**
     * Gets the unique identifier of the element.
     *
     * @return the unique identifier of the element
     */
    public String getID() {
        return ID;
    }

    /**
     * Gets the name of the element.
     *
     * @return the name of the element
     */
    public String getName() {
        return name;
    }

}
