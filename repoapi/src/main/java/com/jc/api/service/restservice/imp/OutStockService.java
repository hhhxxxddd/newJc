package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockOutRecordDetail;
import com.jc.api.entity.SwmsStockOutRecordHead;
import com.jc.api.mapper.*;
import com.jc.api.service.feignservice.ICommonService;
import com.jc.api.service.restservice.IOutStockService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OutStockService implements IOutStockService {
    @Autowired
    SwmsStockOutRecordHeadMapper outRecordHeadMapper;
    @Autowired
    SwmsStockOutRecordDetailMapper outRecordDetailMapper;
    @Autowired
    ICommonService iCommonService;
    @Autowired
    SwmsBasicDeliveryTypeInfoMapper typeInfoMapper;
    @Autowired
    SwmsBasicDeliveryAddressInfoMapper addressInfoMapper;
    @Autowired
    SwmsBasicSupplierInfoMapper supplierInfoMapper;
    @Autowired
    SwmsBasicMaterialTypeMapper typeMapper;
    @Autowired
    SwmsBasicMaterialSubTypeMapper subTypeMapper;
    @Override
    public IPage getPage(Integer deptCode, String date, Page page) {
        QueryWrapper<SwmsStockOutRecordHead> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(deptCode != null,"dept_code",deptCode)
                .gt(StringUtils.isNotBlank(date),"completion_time",date+" 00:00:00")
                .lt(StringUtils.isNotBlank(date),"completion_time",date+" 23:59:59");
        IPage ans = outRecordHeadMapper.selectPage(page,queryWrapper);
        List<SwmsStockOutRecordHead> heads = ans.getRecords();
        List<Map> list = new ArrayList<>();
        for(int i=0;i<heads.size();i++){
            Map<String,Object> map = new HashMap<>();
            map.put("head",heads.get(i));
            map.put("dept",iCommonService.deptName(heads.get(i).getDeptCode()));
            if(heads.get(i).getHfLineCode() == null){
                map.put("line",iCommonService.line(heads.get(i).getSfLineCode()));
            }
            if(heads.get(i).getSfLineCode() == null){
                map.put("line",iCommonService.fireLine(heads.get(i).getHfLineCode()));
            }
            //map.put("type",typeMapper.selectById(heads.get(i).getMaterialTypeId()));
            //map.put("subtype",subTypeMapper.selectById(heads.get(i).getMaterialSubTypeId()));
            map.put("outType",typeInfoMapper.selectById(heads.get(i).getDeliveryTypeCode()));
            map.put("address",addressInfoMapper.selectById(heads.get(i).getDeliveryAddressCode()));
            Integer status = heads.get(i).getMaterialStatus();
            switch (status){
                case 0:
                    map.put("status","未出库");
                    break;
                case 1:
                    map.put("status","进行中");
                    break;
                case 2:
                    map.put("status","已完成");
                    break;
                default:
                    break;
            }

            list.add(map);
        }
        ans.setRecords(list);
        return ans;
    }

    @Override
    public List detail(Long headId) {
        List<Map<String,Object>> ans = new ArrayList<>();
        QueryWrapper<SwmsStockOutRecordDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stock_out_record_head_id",headId);
        List<SwmsStockOutRecordDetail> details = outRecordDetailMapper.selectList(queryWrapper);

        for(int i=0;i<details.size();i++){
            Map<String,Object> map = new HashMap<>();
            map.put("head",details.get(i));
            map.put("type",typeMapper.selectById(details.get(i).getMaterialTypeId()));
            map.put("subType",subTypeMapper.selectById(details.get(i).getMaterialSubTypeId()));
            map.put("supplier",supplierInfoMapper.selectById(details.get(i).getMaterialSupplierCode()));
            map.put("status",details.get(i).getCompletionFlag()?"已出库":"未出库");
            ans.add(map);
        }
        return ans;
    }

    @Override
    public Map getByCommonBatchId(Integer cId) {
        Map map = new HashMap();
        QueryWrapper<SwmsStockOutRecordHead> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("application_form_id",cId);
        List<SwmsStockOutRecordHead> heads = outRecordHeadMapper.selectList(queryWrapper);
        if (heads.size() == 0){
            return map;
        }
        SwmsStockOutRecordHead head = heads.get(0);
        map.put("head",head);
        map.put("dept",iCommonService.deptName(head.getDeptCode()));
        if (head.getHfLineCode() != null)
            map.put("line",iCommonService.fireLine(head.getHfLineCode()));
        if (head.getSfLineCode() != null)
            map.put("line",iCommonService.line(head.getSfLineCode()));
        //map.put("type",typeMapper.selectById(heads.get(i).getMaterialTypeId()));
        //map.put("subtype",subTypeMapper.selectById(heads.get(i).getMaterialSubTypeId()));
        map.put("outType",typeInfoMapper.selectById(head.getDeliveryTypeCode()));
        map.put("address",addressInfoMapper.selectById(head.getDeliveryAddressCode()));
        map.put("detail",detail(Long.valueOf(head.getId())));
        Integer status = head.getMaterialStatus();
        switch (status){
            case 0:
                map.put("status","未出库");
                break;
            case 1:
                map.put("status","进行中");
                break;
            case 2:
                map.put("status","已完成");
                break;
            default:
                break;
        }
        return map;
    }
}
