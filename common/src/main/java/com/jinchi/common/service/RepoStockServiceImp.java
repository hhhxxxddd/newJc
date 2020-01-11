package com.jinchi.common.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinchi.common.controller.SortingOutStockController;
import com.jinchi.common.domain.RepoBaseSerialNumber;
import com.jinchi.common.domain.RepoDiffRecord;
import com.jinchi.common.domain.RepoStock;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.Result;
import com.jinchi.common.dto.repository.RealStockDTO;
import com.jinchi.common.dto.repository.RepoStockDTO;
import com.jinchi.common.mapper.RepoBaseSerialNumberMapper;
import com.jinchi.common.mapper.RepoDiffRecordMapper;
import com.jinchi.common.mapper.RepoStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * @author:YuboWu
 * @description:库存管理服务实现类
 * @date:14:44 2018/11/29
 */
@Service
public class RepoStockServiceImp implements RepoStockService {
    @Autowired
    private RepoStockMapper repoStockMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private RepoDiffRecordMapper repoDiffRecordMapper;
    @Autowired
    private SortingOutStockController sortingOutStockController;

    /**
     * 根据id查询一个库存记录
     */
    @Override
    public RepoStock getById(Integer id) {
        return repoStockMapper.findById(id);
    }

    /**
     * 根据id查询库存-> 杜敏添加
     *
     * @param serialNumberId
     */
    @Override
    public RepoStock getBySerialNumberId(Integer serialNumberId) {
        return repoStockMapper.findBySerialNumberId(serialNumberId);
    }

    /**
     * 根据名称模糊查询所有库存记录-分页
     */
    @Override
    public PageBean findAllByNameByPage(Integer materialClass, String materialName, Integer page, Integer size, String fieldName, String orderType) {
        PageBean<RepoStockDTO> pageBean = new PageBean();
        pageBean.setPageNumber(page);
        pageBean.setPageSize(size);
        pageBean.setSortType(orderType);
        pageBean.setSortField(fieldName);
        //
        List<RepoStockDTO> repoStockDTOS = repoStockMapper.findAllByName(materialClass, materialName);
        //
        Integer total = repoStockMapper.countSum(materialClass, materialName);
        pageBean.setList(repoStockDTOS);
        pageBean.setTotal(total);
        return pageBean;
    }

    /**
     * 查询所有库存记录
     */
    @Override
    public List<RepoStockDTO> findAllByMaterialClass(Integer materialClass) {
        return repoStockMapper.findAllByMaterialClass(materialClass);
    }


    /**
     * 根据物料类型和物料名称查询所有库存记录
     */
    @Override
    public List<RepoStockDTO> findAllByClassAndName(Integer materialClass, String materialName) {
        return repoStockMapper.findAllByName(materialClass, materialName);
    }


    /**
     * 盘库
     *
     * @param id
     * @param creator  创建人
     * @return
     */
    @Override
    public Object oneKeyStock(Integer id, Integer creator) {
        RepoStock repoStock = repoStockMapper.findById(id);

        Assert.notNull(repoStock, String.format("不存在此id的库存%d", id));

        /**
         *  TODO 外来仓库接口
         */
        RepoBaseSerialNumber serialNumber = repoBaseSerialNumberMapper.findById(repoStock.getSerialNumberId());
        Result<RealStockDTO> resultRealStock = sortingOutStockController.realStock(serialNumber.getSerialNumber(), serialNumber.getMaterialName());

        Integer realWeight = resultRealStock.getData().getRealWeight();
        Integer oldWeight = repoStock.getWeight();

        repoStock.setWeight(realWeight);

        repoStockMapper.updateStock(repoStock);

        RepoDiffRecord repoDiffRecord = new RepoDiffRecord();

        repoDiffRecord.setRealWeight(realWeight);

        repoDiffRecord.setSupposedWeight(oldWeight);

        repoDiffRecord.setCreator(creator);

        repoDiffRecord.setSerialNumberId(repoStock.getSerialNumberId());

        repoDiffRecord.setCreateTime(new Date());

        repoDiffRecordMapper.insert(repoDiffRecord);

        return repoDiffRecord;
    }

    @Override
    public List<RepoDiffRecord> getAllRepoDiffRecord() {
        return repoDiffRecordMapper.getAll();
    }


}
