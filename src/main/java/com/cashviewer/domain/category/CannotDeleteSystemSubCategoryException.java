package com.cashviewer.domain.category;

class CannotDeleteSystemSubCategoryException extends RuntimeException {

    CannotDeleteSystemSubCategoryException() {
        super("System subcategory cannot be deleted");
    }
}
