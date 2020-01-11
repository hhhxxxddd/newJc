package com.jinchi.auth.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.auth.domain.Operation;
import com.jinchi.auth.exceptions.EnumExceptions;
import com.jinchi.auth.exceptions.JcExceptions;
import com.jinchi.auth.mapper.OperationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明: <br> 操作服务接口的实现类
 *
 * @author ZSCDumin <br>
 * 邮箱: 2712220318@qq.com <br>
 * 日期: 2018/11/12 <br>
 * 版本: 1.0
 */
@Service
public class OperationServiceImp implements OperationService {

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImp.class);
    @Autowired
    private OperationMapper operationMapper;

    @Override
    public Operation add(Operation operation) {
        if(operation.getOperationName()==null){
            throw new JcExceptions(EnumExceptions.OPERATION_NAME_NOT_EXISTS);
        }
        operationMapper.insert(operation);
        return operation;
    }

    @Override
    public Operation update(Operation operation) {
        operationMapper.update(operation);
        return operation;
    }

    @Override
    public void deleteById(Integer id) {
        if (operationMapper.findById(id) == null) {
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            operationMapper.deleteById(id);
        }
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        operationMapper.deleteByIds(ids);
    }

    /**
     *
     * @param operationName 操作名
     * @param page          第几页
     * @param size          每页的数目
     * @param fieldName     字段名
     * @param orderType     排序类型
     * @return
     */
    @Override
    public PageInfo findByNameLikeByPage(String operationName, Integer page, Integer size, String fieldName,
                                         String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<Operation> operations = operationMapper.findByNameLike(operationName);
        PageInfo<Operation> pageInfo = new PageInfo<>(operations);
        return pageInfo;
    }

    @Override
    public PageInfo findAllByPage(Integer page, Integer size, String fieldName, String orderType) {
        String order = fieldName + " " + orderType;
        PageHelper.startPage(page, size, order);
        List<Operation> operations = operationMapper.findAll();
        PageInfo<Operation> pageInfo = new PageInfo<>(operations);
        return pageInfo;
    }

    @Override
    public List<Operation> findAll() {
        return operationMapper.findAll();
    }

}
