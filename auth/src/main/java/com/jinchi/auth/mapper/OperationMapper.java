package com.jinchi.auth.mapper;

import com.jinchi.auth.domain.Operation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 说明: <br>
 *
 * @author ZSCDumin <br>
 * 邮箱: 2712220318@qq.com <br>
 * 日期: 2018/11/12 <br>
 * 版本: 1.0
 */
@Mapper
@Component
public interface OperationMapper {

    /**
     * 添加一个新操作
     *
     * @param Operation 操作
     */
    void insert(Operation Operation);

    /**
     * 更新一个操作
     *
     * @param Operation 操作
     */
    void update(Operation Operation);

    /**
     * 根据ID删除操作
     *
     * @param id 操作ID
     */
    void deleteById(Integer id);

    /**
     * 根据ids批量删除
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查找操作
     *
     */
    Operation findById(Integer id);

    /**
     * 查询所有的操作
     *
     * @return List<Operation>
     */
    List<Operation> findAll();

    /**
     * 根据名称查询所有的操作
     *
     * @param operationName 操作名称
     * @return List<Operation>
     */
    List<Operation> findByNameLike(@Param("operationName") String operationName);

}
