package com.cashviewer.domain.category;

class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long categoryId) {
        super("Category with id: " + categoryId + " not found");
    }
}
