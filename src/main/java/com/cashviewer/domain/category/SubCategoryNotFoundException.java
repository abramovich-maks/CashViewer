package com.cashviewer.domain.category;

class SubCategoryNotFoundException extends RuntimeException {

    public SubCategoryNotFoundException(Long subCategoryId) {
        super("SubCategory with id:" + subCategoryId + " not found");
    }
}