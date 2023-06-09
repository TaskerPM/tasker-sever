package com.example.tasker.domain.category.exception;

import com.example.tasker.domain.report.exception.ReportException;

public class NoModifiedCategoryException extends ReportException {
    public NoModifiedCategoryException() {
        super(CategoryExceptionList.NO_MODIFIED_CATEGORY.getCODE(),
                CategoryExceptionList.NO_MODIFIED_CATEGORY.getHttpStatus(),
                CategoryExceptionList.NO_MODIFIED_CATEGORY.getMESSAGE()
        );
    }
}
