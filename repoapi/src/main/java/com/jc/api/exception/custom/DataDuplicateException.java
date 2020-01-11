package com.jc.api.exception.custom;


import com.jc.api.exception.RepoErrorTypeEnum;
import com.jinchi.common.core.exception.BaseException;

public class DataDuplicateException extends BaseException {
    public DataDuplicateException() {
        super(RepoErrorTypeEnum.DATA_DUPLICATE_);
    }

    public DataDuplicateException(String message) {
        super(RepoErrorTypeEnum.DATA_DUPLICATE_, message);
    }
}
