package com.jinchi.common.service;

import com.jinchi.common.constant.BatchStatusEnum;
import com.jinchi.common.constant.BatchTypeEnum;
import com.jinchi.common.domain.CommonBatchNumber;
import com.jinchi.common.domain.RepoBaseSerialNumber;
import com.jinchi.common.domain.RepoRedTable;
import com.jinchi.common.domain.RepoStock;
import com.jinchi.common.dto.CommonBatchNumberDTO;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RedTableDTO;
import com.jinchi.common.exceptions.EnumExceptions;
import com.jinchi.common.exceptions.JcExceptions;
import com.jinchi.common.mapper.CommonBatchNumberMapper;
import com.jinchi.common.mapper.RepoBaseSerialNumberMapper;
import com.jinchi.common.mapper.RepoRedTableMapper;
import com.jinchi.common.mapper.RepoStockMapper;
import com.jinchi.common.utils.NumberGenerator;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ZSCDumin
 * @version 1.0
 * @description: 红单管理服务实现类
 * @date 2018/11/27 15:37
 */
@Service
public class RepoRedTableServiceImp implements RepoRedTableService {

    private static final Logger logger = LoggerFactory.getLogger(RepoRedTableServiceImp.class);
    @Autowired
    private RepoRedTableMapper repoRedTableMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private AuthUserService authUserService;
    @Autowired
    private CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    private RepoStockMapper repoStockMapper;

    /**
     * 添加一个红单记录
     *
     * @param commonBatchNumberDTO
     * @return RepoRedTable
     */
    @Override
    public CommonBatchNumberDTO<RepoRedTable> insert(CommonBatchNumberDTO<RepoRedTable> commonBatchNumberDTO) {
        //1、添加一条批号记录
        //设置自动生成的批号
        commonBatchNumberDTO.getCommonBatchNumber().setBatchNumber(NumberGenerator.batchNumberGenerator(BatchTypeEnum.REPO_RED_TABLE.typeCode()));
        commonBatchNumberDTO.getCommonBatchNumber().setDescription("红单管理");
        //设置时间
        commonBatchNumberDTO.getCommonBatchNumber().setCreateTime(new Date());
        commonBatchNumberDTO.getCommonBatchNumber().setStatus(BatchStatusEnum.SAVE.status());
        commonBatchNumberDTO.getCommonBatchNumber().setDataType(BatchTypeEnum.REPO_RED_TABLE.typeCode());
        commonBatchNumberDTO.getCommonBatchNumber().setBatchNumber(NumberGenerator.batchNumberGenerator(BatchTypeEnum.REPO_RED_TABLE.typeCode()));
        //打印commonBatchNumber
        logger.info("红单：" + commonBatchNumberDTO.getCommonBatchNumber().toString());
        commonBatchNumberMapper.insert(commonBatchNumberDTO.getCommonBatchNumber());
        //2. 添加一条红单记录
        RepoRedTable repoRedTable = commonBatchNumberDTO.getDetails();
        //打印commonBatchNumber的Id
        logger.info("红单：" + commonBatchNumberDTO.getCommonBatchNumber().getId());
        repoRedTable.setBatchNumberId(commonBatchNumberDTO.getCommonBatchNumber().getId());
        repoRedTableMapper.insert(repoRedTable);
        return commonBatchNumberDTO;
    }

    /**
     * 更新一个红单记录
     *
     * @param commonBatchNumberDTO 红单记录实体
     * @return RepoRedTable
     */
    @Override
    public CommonBatchNumberDTO<RepoRedTable> update(CommonBatchNumberDTO<RepoRedTable> commonBatchNumberDTO) {
        Assert.notNull(commonBatchNumberDTO.getDetails().getId(), "红单Id为空，缺少参数");
        //1、更新一条批号记录
        //设置状态
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(commonBatchNumberDTO.getCommonBatchNumber().getId());
        commonBatchNumberDTO.getCommonBatchNumber().setCreateTime(commonBatchNumber.getCreateTime());
        commonBatchNumberDTO.getCommonBatchNumber().setMemo(commonBatchNumber.getMemo());
        commonBatchNumberMapper.update(commonBatchNumber);
        //2. 更新一条红单记录
        RepoRedTable repoRedTable = repoRedTableMapper.getById(commonBatchNumberDTO.getDetails().getId());
        commonBatchNumberDTO.getDetails().setBatchNumberId(repoRedTable.getBatchNumberId());
        repoRedTable.setNote(commonBatchNumberDTO.getDetails().getNote());
        repoRedTable.setWeightLoss(commonBatchNumberDTO.getDetails().getWeightLoss());
        repoRedTable.setSerialNumberId(commonBatchNumberDTO.getDetails().getSerialNumberId());
        repoRedTableMapper.update(repoRedTable);
        return commonBatchNumberDTO;
    }

    /**
     * 根据ID删除红单记录
     *
     * @param id 记录ID
     */
    @Override
    public void deleteById(Integer id) {
        if (null == repoRedTableMapper.getById(id)) {
            logger.info("此id = " + id + "不存在，查找失败！");
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            repoRedTableMapper.deleteById(id);
            logger.info("此id = " + id + "，删除成功！");
        }
    }

    /**
     * 根据IDS删除红单记录
     *
     * @param ids 记录ID数组
     */
    @Transactional
    @Override
    public void deleteByIds(Integer[] ids) {
        if (null == ids || ids.length == 0) {
            logger.info("此ids = " + ids + "为空，删除失败！");
            throw new JcExceptions(EnumExceptions.DELETE_FAILED_NOT_EXISTS);
        } else {
            repoRedTableMapper.deleteByIds(ids);
        }
    }

    /**
     * 根据ID查找红单记录
     *
     * @param id 记录ID
     * @return RepoRedTable 红单实体
     */
    @Override
    public RedTableDTO getById(Integer id) {
        if (null == repoRedTableMapper.getById(id)) {
            logger.info("此id = " + id + "不存在，查找失败！");
            throw new JcExceptions(EnumExceptions.FIND_FAILED_NOT_EXISTS);
        } else {
            logger.info("此id = " + id + "存在，查找成功！");
            RedTableDTO redTableDTO = new RedTableDTO();
            RepoRedTable repoRedTable = repoRedTableMapper.getById(id);
            redTableDTO.setCommonBatchNumber(commonBatchNumberMapper.byId(repoRedTable.getBatchNumberId()));
            redTableDTO.setCreatePersonName(authUserService.findById(commonBatchNumberMapper.byId(repoRedTable.getBatchNumberId()).getCreatePersonId()).getName());
            redTableDTO.setRepoRedTable(repoRedTable);
            redTableDTO.setRepoBaseSerialNumber(repoBaseSerialNumberMapper.findById(repoRedTable.getSerialNumberId()));
            return redTableDTO;
        }
    }

    @Override
    public List<RedTableDTO> getByBatchNumberId(Integer id) {
        CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(id);
        Assert.isTrue(null != commonBatchNumber, "该批号不存在!");
        List<RepoRedTable> repoRedTableList = new ArrayList<>();
        repoRedTableList.add(repoRedTableMapper.getByBatchNumberId(id));
        return setData(repoRedTableList);
    }

    /**
     * 设置DTO的数据
     *
     * @param list 红单列表
     * @return List<RedTableDTO> 红单DTO列表
     */
    public List<RedTableDTO> setData(List<RepoRedTable> list) {
        List<RedTableDTO> redTableDTOList = new ArrayList<>();
        for (RepoRedTable repoRedTable : list) {
            RedTableDTO redTableDTO = new RedTableDTO();
            redTableDTO.setCommonBatchNumber(commonBatchNumberMapper.byId(repoRedTable.getBatchNumberId()));
            redTableDTO.setCreatePersonName(authUserService.findById(commonBatchNumberMapper.byId(repoRedTable.getBatchNumberId()).getCreatePersonId()).getName());
            redTableDTO.setRepoRedTable(repoRedTable);
            redTableDTO.setRepoBaseSerialNumber(repoBaseSerialNumberMapper.findById(repoRedTable.getSerialNumberId()));
            redTableDTOList.add(redTableDTO);
        }
        return redTableDTOList;
    }

    /**
     * 查询所有的红单记录
     *
     * @param pageSize  每页个数
     * @param fieldName 字段名
     * @param orderType 排序规则
     * @return PageInfo 排序集合
     */
    @Override
    public PageBean<RedTableDTO> getAllByPage(Integer materialType, Integer pageNum, Integer pageSize, String fieldName, String orderType) {

        PageBean<RedTableDTO> pageBean = new PageBean<>();
        pageBean.setPageNumber(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setSortField(fieldName);
        pageBean.setSortType(orderType);
        pageBean.setTotal(repoRedTableMapper.getAllCount(materialType, pageBean));
        List<RepoRedTable> repoRedTableList = repoRedTableMapper.getAll(materialType, pageBean);
        pageBean.setList(setData(repoRedTableList));
        return pageBean;
    }

    /**
     * 通过材料编号进行模糊查询
     *
     * @param materialType 材料类型
     * @param serialNumber 材料编号
     * @param pageSize     每页个数
     * @param fieldName    字段名
     * @param fieldName    字段名
     * @param orderType    排序规则
     * @return PageInfo 排序集合
     */
    @Override
    public PageBean<RedTableDTO> getBySerialNumberLikeByPage(Integer materialType, String serialNumber, Integer pageNum, Integer pageSize, String fieldName, String orderType) {
        PageBean<RedTableDTO> pageBean = new PageBean<>();
        pageBean.setPageNumber(pageNum);
        pageBean.setPageSize(pageSize);
        pageBean.setSortField(fieldName);
        pageBean.setSortType(orderType);
        pageBean.setTotal(repoRedTableMapper.getBySerialNumberLikeCount(materialType, serialNumber, pageBean));
        List<RepoRedTable> repoRedTableList = repoRedTableMapper.getBySerialNumberLike(materialType, serialNumber, pageBean);
        pageBean.setList(setData(repoRedTableList));
        return pageBean;
    }

    @Override
    public void updateStock(Integer batchNumberId) {
        RepoRedTable redTable = repoRedTableMapper.getByBatchNumberId(batchNumberId);
        Integer weightLoss = redTable.getWeightLoss();
        RepoStock repoStock = repoStockMapper.findBySerialNumberId(redTable.getSerialNumberId());
        RepoBaseSerialNumber serialNumber = repoBaseSerialNumberMapper.findById(redTable.getSerialNumberId());
        Integer newWeight = repoStock.getWeight()-weightLoss;
        Assert.isTrue(newWeight>0,String.format("红单数据错误,%s损失数量大于已有数量",serialNumber.getMaterialName()));
        repoStockMapper.updateStock(repoStock.setWeight(newWeight));
    }
}
