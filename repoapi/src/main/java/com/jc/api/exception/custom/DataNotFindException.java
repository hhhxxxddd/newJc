package com.jc.api.exception.custom;

import com.jc.api.exception.RepoErrorTypeEnum;
import com.jinchi.common.core.exception.BaseException;

/**
 * @author XudongHu
 * @apiNote 数据未找到类型异常
 * @className NotFindDuplicateException
 * @modifier
 * @since 2019/11/3日1:10
 */
public class DataNotFindException extends BaseException {
    public DataNotFindException() {
        super(RepoErrorTypeEnum.DATA_NOT_FIND_);
    }

    public DataNotFindException(String message) {
        super(RepoErrorTypeEnum.DATA_NOT_FIND_, message);
    }
}
