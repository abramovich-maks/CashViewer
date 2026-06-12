package com.cashviewer.domain.category;

public class CategoryAlreadyExistsException extends RuntimeException {

    public CategoryAlreadyExistsException(String name) {
        super("Category with name " + name + " already exists");
    }
}