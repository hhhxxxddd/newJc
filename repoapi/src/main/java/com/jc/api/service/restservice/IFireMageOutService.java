package com.jc.api.service.restservice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.dto.AuditDTO;
import com.jc.api.entity.vo.OutQueryVo;

import java.util.List;

public interface IFireMageOutService {

    OutQueryVo getData(Integer type,Integer subType,Integer matId,Integer supplierId);

    Boolean sendAudit(List<AuditDTO> mats, Integer deptCode, Integer lineCode,Integer outPoint,Integer outType,Integer isUrgent,Integer auditId,Integer userId);

    IPage getByPage(Integer deptCode, String date, Page page);

    OutQueryVo getDataByMatid(Integer type,Integer subType,Integer matId,Integer supplierCode);

    List detail(Long headId);

}