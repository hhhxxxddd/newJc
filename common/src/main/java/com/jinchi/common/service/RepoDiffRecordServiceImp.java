package com.jinchi.common.service;

import com.jinchi.common.controller.SortingOutStockController;
import com.jinchi.common.domain.RepoBaseSerialNumber;
import com.jinchi.common.domain.RepoDiffRecord;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RepoDiffRecordDTO;
import com.jinchi.common.dto.repository.RepoStockDTO;
import com.jinchi.common.mapper.AuthUserMapper;
import com.jinchi.common.mapper.RepoBaseSerialNumberMapper;
import com.jinchi.common.mapper.RepoDiffRecordMapper;
import com.jinchi.common.mapper.RepoStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZhihao on 2018/12/17.
 */
@Service
public class RepoDiffRecordServiceImp implements RepoDiffRecordService {
    @Autowired
    private RepoDiffRecordMapper repoDiffRecordMapper;
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private AuthUserMapper authUserMapper;
    @Autowired
    private RepoStockMapper repoStockMapper;
    @Autowired
    private SortingOutStockController sortingOutStockController;

    /**
     * 待盘库页面
     *
     * @param materialClass
     * @param materialName
     * @return
     */
    @Override
    public List<RepoStockDTO> stockByMaterialClassAndName(Integer materialClass, String materialName) {
        List<RepoStockDTO> allByName = repoStockMapper.findAllByName(materialClass, materialName);
        if (null != allByName && !allByName.isEmpty()) {
            allByName.stream().forEach(e -> e.setSerialNumber(e.getSerialNumber().split("-")[0]));
            allByName.stream().forEach(e -> e.setRealWeight(sortingOutStockController.realStock(e.getSerialNumber(), e.getMaterialName()).getData().getRealWeight()));
        }
        return allByName;
    }

    /**
     * 根据物料类型查询所有盘库记录
     *
     * @param materialClass 物料类型
     * @return
     */
    @Override
    public List<RepoDiffRecordDTO> byMaterialClass(Integer materialClass) {
        List<RepoDiffRecord> repoDiffRecords = repoDiffRecordMapper.getAll();
        List<RepoDiffRecordDTO> repoDiffRecordDTOs = new ArrayList<>();
        for (int i = 0; i < repoDiffRecords.size(); i++) {
            int serialNumberId = repoDiffRecords.get(i).getSerialNumberId();
            RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(serialNumberId);
            if (repoBaseSerialNumber.getMaterialClass() == materialClass) {
                RepoDiffRecordDTO repoDiffRecordDTO = new RepoDiffRecordDTO();
                repoDiffRecordDTO.setRepoDiffRecord(repoDiffRecords.get(i));
                repoDiffRecordDTO.setMaterialName(repoBaseSerialNumber.getMaterialName());
                repoDiffRecordDTO.setSerialNumber(repoBaseSerialNumber.getSerialNumber().split("-")[0]);
                repoDiffRecordDTO.setCreator(authUserMapper.byId(repoDiffRecords.get(i).getCreator()).getName());
                repoDiffRecordDTO.setId(repoDiffRecordDTO.getId());
                repoDiffRecordDTOs.add(repoDiffRecordDTO);
            }
        }
        return repoDiffRecordDTOs;
    }

    /**
     * 根据物料类型和名称模糊查询
     *
     * @param materialClass 类型
     * @param materialName  名称
     * @return
     */
    @Override
    public List<RepoDiffRecordDTO> byMaterialClassByNameLike(Integer materialClass, String materialName) {
        List<RepoDiffRecordDTO> repoDiffRecordDTOs = byMaterialClass(materialClass);
        boolean flag = materialName == null ? true : false;
        for (int i = 0; i < repoDiffRecordDTOs.size(); i++) {
            if (!flag && !repoDiffRecordDTOs.get(i).getMaterialName().contains(materialName)) {
                repoDiffRecordDTOs.remove(i);
                i--;
            }
        }
        return repoDiffRecordDTOs;
    }

    /**
     * 查询所有盘库记录
     *
     * @param materialClass 类型
     * @param materialName  名称
     * @param pageBean      分页
     * @return
     */
    @Override
    public PageBean byPageByMaterialNameByNameLike(Integer materialClass, String materialName, PageBean pageBean) {
        List<RepoDiffRecord> repoDiffRecords = repoDiffRecordMapper.getAllByFactors(materialClass, materialName, pageBean);
        List<RepoDiffRecordDTO> repoDiffRecordDTOs = new ArrayList<>();
        for (int i = 0; i < repoDiffRecords.size(); i++) {
            int serialNumberId = repoDiffRecords.get(i).getSerialNumberId();
            RepoBaseSerialNumber repoBaseSerialNumber = repoBaseSerialNumberMapper.findById(serialNumberId);
            RepoDiffRecordDTO repoDiffRecordDTO = new RepoDiffRecordDTO();
            repoDiffRecordDTO.setRepoDiffRecord(repoDiffRecords.get(i));
            repoDiffRecordDTO.setMaterialName(repoBaseSerialNumber.getMaterialName());
            repoDiffRecordDTO.setSerialNumber(repoBaseSerialNumber.getSerialNumber().split("-")[0]);
            repoDiffRecordDTO.setCreator(authUserMapper.byId(repoDiffRecords.get(i).getCreator()).getName());
            repoDiffRecordDTO.setId(repoDiffRecordDTO.getId());
            repoDiffRecordDTOs.add(repoDiffRecordDTO);

        }
        pageBean.setList(repoDiffRecordDTOs);
        pageBean.setTotal(repoDiffRecordMapper.countSum(materialClass, materialName));
        return pageBean;
    }
}
