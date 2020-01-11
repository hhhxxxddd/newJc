package com.jinchi.common.web.entity.form;

import com.jinchi.common.web.entity.po.BasePo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @className BaseForm
 * @author XudongHu
 * @apiNote 新增/更新参数提交
 * @since 2019/10/29日21:11
 * @modifer
 */
@ApiModel
@Slf4j
@Data
public class BaseForm<T extends BasePo> {
//    private String username;

    /**
     * From转化为Po，进行后续业务处理
     *
     * @param clazz
     * @return
     */
    public T toPo(Class<T> clazz) {
        T t = BeanUtils.instantiateClass(clazz);
        BeanUtils.copyProperties(this, t);
        return t;
    }
}
