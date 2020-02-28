package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.*;
import com.jc.api.entity.dto.AuditDTO;
import com.jc.api.entity.form.MaterialInfoForm;
import com.jc.api.entity.vo.OutQueryVo;
import com.jc.api.mapper.*;
import com.jc.api.service.feignservice.ICommonService;
import com.jc.api.service.restservice.IFireMageOutService;
import com.jc.api.utils.ComUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class FireMageOutService implements IFireMageOutService {
    @Autowired
    SwmsStockInventoryReallyReportsMapper reallyReportsMapper;
    @Autowired
    SwmsBasicMaterialTypeMapper typeMapper;
    @Autowired
    SwmsBasicMaterialInfoMapper infoMapper;
    @Autowired
    SwmsBasicMaterialSubTypeMapper subTypeMapper;
    @Autowired
    SwmsStockInLedgersMapper ledgersMapper;
    @Autowired
    ICommonService iCommonService;
    @Autowired
    SwmsStockOutRecordHeadMapper outRecordHeadMapper;
    @Autowired
    SwmsStockOutRecordDetailMapper outRecordDetailMapper;
    @Autowired
    SwmsBasicDeliveryTypeInfoMapper typeInfoMapper;
    @Autowired
    SwmsBasicDeliveryAddressInfoMapper addressInfoMapper;

    @Override
    public Boolean sendAudit(List<AuditDTO> mats, Integer deptCode, Integer lineCode,
                             Integer outPoint, Integer outType, Integer isUrgent,
                             Integer auditId,Integer userId) {
        String common = iCommonService.send2audit(userId,isUrgent,auditId);
        if(common == null)
            return false;
        String[] s = common.split("-");
        AuditDTO auditDTO = mats.get(0);
        SwmsBasicMaterialInfo materialInfo = infoMapper.selectById(auditDTO.getMatId());
        SwmsStockOutRecordHead head = new SwmsStockOutRecordHead();
        head
                .setApplicationFormId(Long.parseLong(s[1]))
                .setStockOutRecordHeadCode(s[0])
                .setDeptCode(deptCode)
                .setHfLineCode(lineCode)
                .setDeliveryTypeCode(outType)
                .setDeliveryAddressCode(outPoint)
                .setMaterialTypeId(materialInfo.getMaterialTypeId())
                .setMaterialSubTypeId(materialInfo.getSubTypeId())
                .setCreatedTime(new Date())
                .setCreatedPersonId(userId)
                .setCreatedPerson(iCommonService.personName(userId))
                .setCompletionTime(new Date())
                .setMaterialStatus(0)
                .setSysFlag(false);//火法出库
        outRecordHeadMapper.insert(head);

        for(int i=0;i<mats.size();i++){
            SwmsStockOutRecordDetail detail = new SwmsStockOutRecordDetail();
            AuditDTO mat = mats.get(i);
            SwmsBasicMaterialInfo material = infoMapper.selectById(mat.getMatId());

            /**
             * 修改入库台账中的物料变成待出库
             */
            SwmsStockInLedgers ledgers = ledgersMapper.selectById(mat.getLedgersId());
            ledgers.setMaterialStatus(1);
            ledgersMapper.updateById(ledgers);

            /**
             * 修改可用库存
             */
            QueryWrapper<SwmsStockInventoryReallyReports> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("material_name_code",mat.getMatId())
                    .eq("material_supplier_code",ledgers.getMaterialSupplierCode());


            detail
                    .setStockOutRecordHeadId(Long.parseLong(head.getId()))
                    .setStockOutRecordHeadCode(head.getStockOutRecordHeadCode())
                    .setGroupName(mat.getGroup())
                    .setStockInRecordAccountId(mat.getLedgersId())
                    .setMaterialTypeId(material.getMaterialTypeId())
                    .setMaterialSubTypeId(material.getSubTypeId())
                    .setMaterialWorkshopId(ledgers.getMaterialWorkshopId())
                    .setMaterialNameCode(mat.getMatId())
                    .setMaterialSupplierCode(material.getSupplierId())
                    .setMaterialName(material.getMaterialName())
                    .setWeight(mat.getWeight())
                    .setMeasureUnit(material.getMeasureUnit())
                    .setCreatedTime(new Date())
                    .setCompletionFlag(false);
            outRecordDetailMapper.insert(detail);
        }

        return true;
    }

    @Override
    public IPage getByPage(Integer deptCode, String date, Page page) {
        QueryWrapper<SwmsStockOutRecordHead> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(deptCode != null,"dept_code",deptCode)
                .gt(StringUtils.isNotBlank(date),"completion_time",date+" 00:00:00")
                .lt(StringUtils.isNotBlank(date),"completion_time",date+" 23:59:59")
                .eq("sys_flag",0);
        IPage ans = outRecordHeadMapper.selectPage(page,queryWrapper);
        List<SwmsStockOutRecordHead> heads = ans.getRecords();
        List<Map> list = new ArrayList<>();
        for(int i=0;i<heads.size();i++){
            Map<String,Object> map = new HashMap<>();
            map.put("head",heads.get(i));
            map.put("dept",iCommonService.deptName(heads.get(i).getDeptCode()));
            map.put("line",iCommonService.fireLine(heads.get(i).getHfLineCode()));
            map.put("type",typeMapper.selectById(heads.get(i).getMaterialTypeId()));
            map.put("subtype",subTypeMapper.selectById(heads.get(i).getMaterialSubTypeId()));
            map.put("outType",typeInfoMapper.selectById(heads.get(i).getDeliveryTypeCode()));
            map.put("address",addressInfoMapper.selectById(heads.get(i).getDeliveryAddressCode()));
            list.add(map);
        }
        ans.setRecords(list);
        return ans;
    }

    @Override
    public OutQueryVo getData(Integer type, Integer subType, Integer matId, Integer supplierId) {
        OutQueryVo ans = new OutQueryVo();
        QueryWrapper<SwmsStockInventoryReallyReports> reallyReportsQueryWrapper = new QueryWrapper<>();
        reallyReportsQueryWrapper
                .eq(type!=null,"material_type_id",type)
                .eq(subType!=null,"material_sub_type_id",subType)
                .eq(matId!=null,"material_name_code",matId)
                .eq(supplierId!=null,"material_supplier_code",supplierId);
        List<SwmsStockInventoryReallyReports> reallyReports = reallyReportsMapper.selectList(reallyReportsQueryWrapper);

        //求和
        Map<String,SwmsStockInventoryReallyReports> map = new HashMap<>();
        for(int i=0;i<reallyReports.size();i++){
            SwmsStockInventoryReallyReports temp = reallyReports.get(i);
            if (!map.containsKey(temp.getMaterialNameCode()+"")){
                map.put(temp.getMaterialNameCode()+"",temp);
            }else{
                SwmsStockInventoryReallyReports reports = map.get(temp.getMaterialNameCode()+"");
                reports.setRealWeight(reports.getRealWeight() + temp.getRealWeight());
                reports.setUsefulWeight(reports.getUsefulWeight() + temp.getUsefulWeight());
                map.put(temp.getMaterialNameCode()+"",reports);
            }
        }

        List<SwmsStockInventoryReallyReports> reports = new ArrayList<>();
        map.forEach((k,v)->reports.add(v));
        ans.setUps(reports);

        IPage<SwmsStockInLedgers> page = new Page<SwmsStockInLedgers>();
        if(reports.size() != 0){
            Integer mat = reports.get(0).getMaterialNameCode();
            QueryWrapper<SwmsStockInLedgers> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("material_name_code",mat).eq("material_status",0);
            page = ledgersMapper.selectPage(new Page<SwmsStockInLedgers>(),queryWrapper);
        }
        ans.setDetails(page);
        return ans;
    }

    @Override
    public OutQueryVo getDataByMatid(Integer matId, Page page) {
        OutQueryVo ans = new OutQueryVo();
        QueryWrapper<SwmsStockInLedgers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("material_name_code",matId).eq("material_status",0);
        ans.setDetails(ledgersMapper.selectPage(page,queryWrapper));
        return ans;
    }

}
