package com.cashviewer.domain.category;

class CannotDeleteSystemCategoryException extends RuntimeException {

    CannotDeleteSystemCategoryException() {
        super("System category cannot be deleted");
    }
}
