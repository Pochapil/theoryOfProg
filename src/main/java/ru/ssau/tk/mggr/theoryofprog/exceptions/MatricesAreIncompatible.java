package ru.ssau.tk.mggr.theoryofprog.exceptions;

public class MatricesAreIncompatible extends RuntimeException {

    private static final long serialVersionUID = -5748157992158849390L;

    public MatricesAreIncompatible() {
        super();
    }

    public MatricesAreIncompatible(String mess) {
        super(mess);
    }
}
