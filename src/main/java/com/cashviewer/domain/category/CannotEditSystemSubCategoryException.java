package com.cashviewer.domain.category;

class CannotEditSystemSubCategoryException extends RuntimeException {

    CannotEditSystemSubCategoryException() {
        super("System subcategory cannot be edited");
    }
}