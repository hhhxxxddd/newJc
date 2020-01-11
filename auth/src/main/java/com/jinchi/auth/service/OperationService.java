package com.jinchi.auth.service;

import com.github.pagehelper.PageInfo;
import com.jinchi.auth.domain.Operation;

import java.util.List;

/**
 * 说明: <br>
 *
 * @author ZSCDumin <br>
 *         邮箱: 2712220318@qq.com <br>
 *         日期: 2018/11/12 <br>
 *         版本: 1.0
 */
public interface OperationService {

    /**
     * 添加新操作
     *
     * @param Operation 操作对象
     * @return Operation
     */
    Operation add(Operation Operation);

    /**
     * 更新操作
     *
     * @param Operation 操作对象
     * @return Operation
     */
    Operation update(Operation Operation);


    /**
     * 根据ID删除操作
     *
     * @param id 操作id
     */
    void deleteById(Integer id);

    /**
     * 根据ID删除操作
     *
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据操作名称模糊查询所有操作-分页
     *
     * @param operationName 操作名
     * @param page          第几页
     * @param size          每页的数目
     * @param fieldName     字段名
     * @param orderType     排序类型
     * @return PageInfo
     */
    PageInfo findByNameLikeByPage(String operationName, Integer page, Integer size, String fieldName, String orderType);

    /**
     * 查询所有操作-分页
     *
     * @param page      第几页
     * @param size      每页的数目
     * @param fieldName 字段名
     * @param orderType 排序类型
     * @return PageInfo
     */
    PageInfo findAllByPage(Integer page, Integer size, String fieldName, String orderType);

    /**
     * 查询所有操作
     */
    List<Operation> findAll();

}
