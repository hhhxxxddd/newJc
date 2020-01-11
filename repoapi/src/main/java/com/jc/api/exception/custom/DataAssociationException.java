package com.jc.api.exception.custom;

import com.jc.api.exception.RepoErrorTypeEnum;
import com.jinchi.common.core.exception.BaseException;

/**
 * @author XudongHu
 * @apiNote 外键依赖异常
 * @className AssociationDataException
 * @modifier
 * @since 2019/11/3日1:34
 */
public class DataAssociationException extends BaseException {
    public DataAssociationException(){super(RepoErrorTypeEnum.DATA_ASSOCIATION_);}
    public DataAssociationException(String mesg){super(RepoErrorTypeEnum.DATA_ASSOCIATION_,mesg);}
}
