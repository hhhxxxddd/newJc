package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsBasicInactionStockDeadline;
import com.jc.api.entity.SwmsBasicMaterialSubType;
import com.jc.api.entity.SwmsBasicMaterialType;
import com.jc.api.exception.custom.DataAssociationException;
import com.jc.api.exception.custom.DataDuplicateException;
import com.jc.api.exception.custom.ParamVerifyException;
import com.jc.api.mapper.SwmsBasicInactionStockDeadlineMapper;
import com.jc.api.mapper.SwmsBasicMaterialSubTypeMapper;
import com.jc.api.mapper.SwmsBasicMaterialTypeMapper;
import com.jc.api.service.restservice.ISwmsBasicInactionStockDeadlineService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Auther: River
 * @Date: 2020/1/12 18:16
 * @Description:    呆滞期限Service实现层
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class SwmsBasicInactionStockDeadlineService implements ISwmsBasicInactionStockDeadlineService {

    @Autowired
    private SwmsBasicInactionStockDeadlineMapper swmsBasicInactionStockDeadlineMapper;

    @Autowired
    private SwmsBasicMaterialTypeMapper swmsBasicMaterialTypeMapper;

    @Autowired
    private SwmsBasicMaterialSubTypeMapper swmsBasicMaterialSubTypeMapper;

    /**
     * @Description:    呆滞期限-不分页
     * @Author: River
     * @Date: 2020/1/12 18:40
     {@link List< SwmsBasicInactionStockDeadline>}
     java.util.List<com.jc.api.entity.SwmsBasicInactionStockDeadline>
     **/
    @Override
    public List<SwmsBasicInactionStockDeadline> getAll(String subTypeName) {
        return swmsBasicInactionStockDeadlineMapper.findList(subTypeName);
    }

    /**
     * @Description:    呆滞期限-分页
     * @Author: River
     * @Date: 2020/1/12 18:40
     {@link IPage< SwmsBasicInactionStockDeadline>}
     com.baomidou.mybatisplus.core.metadata.IPage<com.jc.api.entity.SwmsBasicInactionStockDeadline>
     **/
    @Override
    public IPage<SwmsBasicInactionStockDeadline> getAllByPage(Page<SwmsBasicInactionStockDeadline> page, String subTypeName) {
        IPage<SwmsBasicInactionStockDeadline> pageInfo = swmsBasicInactionStockDeadlineMapper.selectPage(page, subTypeName);
        return pageInfo;
    }

    /**
     * @Description:    呆滞期限-新增
     * @Author: River
     * @Date: 2020/1/12 18:40
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean add(SwmsBasicInactionStockDeadline swmsBasicInactionStockDeadline) {
        if (swmsBasicInactionStockDeadline.getMaterialTypeId() == null) {
            throw new ParamVerifyException("新增失败，物料类型未选择");
        }
        SwmsBasicMaterialType queryTypeById = swmsBasicMaterialTypeMapper.selectById(swmsBasicInactionStockDeadline.getMaterialTypeId());
        if (queryTypeById == null) {
            throw new DataDuplicateException("新增失败，物料类型不存在：" + swmsBasicInactionStockDeadline.getMaterialTypeId());
        }
        if (swmsBasicInactionStockDeadline.getSubTypeId() == null) {
            throw new ParamVerifyException("新增失败，物料子类型未选择");
        }
        SwmsBasicMaterialSubType querySubTypeById = swmsBasicMaterialSubTypeMapper.selectById(swmsBasicInactionStockDeadline.getSubTypeId());
        if (querySubTypeById == null) {
            throw new DataDuplicateException("新增失败，物料子类型不存在：" + swmsBasicInactionStockDeadline.getSubTypeId());
        }
        if (swmsBasicInactionStockDeadline.getDeadline() == null) {
            throw new DataDuplicateException("新增失败，未填写呆滞天数");
        }
        return swmsBasicInactionStockDeadlineMapper.insert(swmsBasicInactionStockDeadline) > 0;
    }

    /**
     * @Description:    呆滞期限-更新
     * @Author: River
     * @Date: 2020/1/12 18:41
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean update(SwmsBasicInactionStockDeadline swmsBasicInactionStockDeadline) {
        if (StringUtils.isEmpty(swmsBasicInactionStockDeadline.getId())) {
            throw new ParamVerifyException("更新失败，未传入呆滞期限ID，请检查后重新输入");
        }
        SwmsBasicInactionStockDeadline oldDeadline = swmsBasicInactionStockDeadlineMapper.selectById(swmsBasicInactionStockDeadline.getId());
        if (oldDeadline == null) {
            throw new DataDuplicateException("更新失败，呆滞期限不存在，请刷新后重试");
        }
        SwmsBasicMaterialType queryTypeById = swmsBasicMaterialTypeMapper.selectById(swmsBasicInactionStockDeadline.getMaterialTypeId());
        if (queryTypeById == null) {
            throw new DataDuplicateException("更新失败，物料类型不存在：" + swmsBasicInactionStockDeadline.getMaterialTypeId());
        }
        if (swmsBasicInactionStockDeadline.getSubTypeId() == null) {
            throw new ParamVerifyException("更新失败，物料子类型未选择");
        }
        SwmsBasicMaterialSubType querySubTypeById = swmsBasicMaterialSubTypeMapper.selectById(swmsBasicInactionStockDeadline.getSubTypeId());
        if (querySubTypeById == null) {
            throw new DataDuplicateException("更新失败，物料子类型不存在：" + swmsBasicInactionStockDeadline.getSubTypeId());
        }
        return swmsBasicInactionStockDeadlineMapper.updateById(swmsBasicInactionStockDeadline) > 0;
    }

    /**
     * @Description:    呆滞期限-根据ID删除
     * @Author: River
     * @Date: 2020/1/12 18:41
     {@link Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean delete(Integer id) {
        try {
            return swmsBasicInactionStockDeadlineMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DataAssociationException(String.format("删除失败,该数据正在被使用:%d,如要删除请选择强力删除,将删除包括关联的台账,出库信息", id));
        }
    }

    /**
     * @Description:    批量删除
     * @Author: River
     * @Date: 2020/1/12 18:41
     {@link java.lang.Boolean}
     java.lang.Boolean
     **/
    @Override
    public Boolean batchDelete(Set<String> ids) {
        try {
            return swmsBasicInactionStockDeadlineMapper.deleteBatchIds(ids) == ids.size();
        } catch (Exception e) {
            throw new DataAssociationException("删除失败,数据正在被使用");
        }
    }
}
