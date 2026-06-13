package com.cashviewer.domain.category;

class CannotEditSystemCategoryException extends RuntimeException {

    CannotEditSystemCategoryException() {
        super("System category cannot be edited");
    }
}
