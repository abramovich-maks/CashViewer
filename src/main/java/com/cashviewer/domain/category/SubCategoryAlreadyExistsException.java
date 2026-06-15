package com.cashviewer.domain.category;

class SubCategoryAlreadyExistsException extends RuntimeException {

    public SubCategoryAlreadyExistsException(String name) {
        super("SubCategory with name " + name + " already exists");
    }
}
