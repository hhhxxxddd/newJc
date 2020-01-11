package com.jc.api.service.restservice.imp;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.dto.MatDeliDTO;
import com.jc.api.entity.param.StockOutRecordQueryParam;
import com.jc.api.entity.po.StockOutRecord;
import com.jc.api.entity.po.StockOutRecordHead;
import com.jc.api.entity.vo.StockAccountJointVo;
import com.jc.api.entity.vo.StockOutRecordVo;
import com.jc.api.mapper.StockOutRecordHeadMapper;
import com.jc.api.mapper.StockOutRecordMapper;
import com.jc.api.service.restservice.IStockInRecordAccountService;
import com.jc.api.service.restservice.IStockOutRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Deprecated
public class StockOutRecordService implements IStockOutRecordService {
    @Autowired
    private StockOutRecordMapper stockOutRecordMapper;
    @Autowired
    private StockOutRecordHeadMapper stockOutRecordHeadMapper;
    @Autowired
    private IStockInRecordAccountService iStockInRecordAccountService;


    @Override
    public StockOutRecordVo getById(String id) {
        StockOutRecord record = stockOutRecordMapper.selectById(id);
        return toVo(record);
    }

    /**
     * 查询所有-条件
     * @param stockOutRecordQueryParam 出库记录参数
     * @return list
     */
    @Override
    public List<StockOutRecordVo> getAll(StockOutRecordQueryParam stockOutRecordQueryParam) {
        QueryWrapper<StockOutRecord> build = stockOutRecordQueryParam.build();
        List<StockOutRecord> stockOutRecords = stockOutRecordMapper.selectList(build);
        ArrayList<StockOutRecordVo> stockOutRecordVos = new ArrayList<>();
        stockOutRecords.forEach(e->stockOutRecordVos.add(toVo(e)));
        return stockOutRecordVos;
    }

    /**
     * 查询所有-条件/分页
     * @param page 分页参数
     * @param stockOutRecordQueryParam 出库记录参数
     * @return page
     */
    @Override
    public IPage<StockOutRecordVo> getAllByPage(Page page, StockOutRecordQueryParam stockOutRecordQueryParam) {
        QueryWrapper<StockOutRecord> build = stockOutRecordQueryParam.build();
        IPage iPage = stockOutRecordMapper.selectPage(page, build);
        List<StockOutRecord> records = iPage.getRecords();
        ArrayList<StockOutRecordVo> stockOutRecordVos = new ArrayList<>();
        records.forEach(e->stockOutRecordVos.add(toVo(e)));
        return iPage.setRecords(stockOutRecordVos);
    }

    @Override
    public List<MatDeliDTO> dateForMaterialDelivery(String startTime, String endTime, List<String> matNames) {
        List<MatDeliDTO> ans = new ArrayList<>();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date start = null;
//        Date end = null;
//
//        try {
//            start = df.parse(startTime);
//            end = df.parse(endTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//
//        StockOutRecordQueryParam param = new StockOutRecordQueryParam();
//
//        param.setSct(start);
//        param.setEct(end);
//
//
//        //===============================================================>
//        // fixme: 你的需求可能是查询时间段内,相应物料的出库数据
//        //可能是这样
//        QueryWrapper<MaterialInfo> byNameList = new QueryWrapper<>();
//        byNameList.in("material_name", matNames);
//        List<MaterialInfo> materialInfos = materialInfoMapper.selectList(byNameList);
//        Set<String> infoIds = new HashSet<>();
//        materialInfos.forEach(e -> infoIds.add(e.getId()));
//        //infoIds物料信息ids
//        QueryWrapper<MaterialStock> byInfoIds = new QueryWrapper<>();
//        byInfoIds.in("material_info_id", infoIds);
//        List<MaterialStock> materialStocks = materialStockMapper.selectList(byInfoIds);
//        Set<String> stockIds = new HashSet<>();
//        materialStocks.forEach(e -> stockIds.add(e.getId()));
//        //stockIds物料库存ids,时间已经build了
//        QueryWrapper<StockOutRecord> betweenDateTimeAndMaterialNames = param.build();
//        betweenDateTimeAndMaterialNames.in("material_stock_id", stockIds);
//        List<StockOutRecord> stockOutRecords = stockOutRecordMapper.selectList(betweenDateTimeAndMaterialNames);
//        //key=>info,value => list<StockOutRecord>
//        Map<MaterialInfo, List<StockOutRecord>> infoAndStockOutMap = new HashMap<>();
//        materialInfos.forEach(e -> infoAndStockOutMap.put(e, new ArrayList<>()));
//        infoAndStockOutMap.forEach((k, v) -> {
//            materialStocks.forEach(e -> {
//                if (e.getMaterialInfoId().toString().equals(k.getId())) {
//                    stockOutRecords.forEach(s -> {
//                    });
//                }
//            });
//        });
//        //return infoAndStockOutMap
//        //=====================================================================>>
//
//        MaterialInfoQueryParam param1 = new MaterialInfoQueryParam();
//        List<MaterialInfo> infos = materialInfoMapper.selectList(param1.build());
//
//        for (int i = 0; i < infos.size(); i++) {
//            MatDeliDTO deliDTO = new MatDeliDTO();
//            MaterialInfo materialInfo = infos.get(i);
//            MaterialStockQueryParam param2 = new MaterialStockQueryParam();
//            param2.setMaterialInfoId(materialInfo.getId());
//            param2.setRepoName("");
//            param2.setStatus(null);
//            List<MaterialStock> stocks = materialStockMapper.selectList(param2.build());
//            List<StockOutRecord> tempRecord = new ArrayList<>();
//            for (int l = 0; l < stocks.size(); l++) {
//                param.setMaterialStockId(stocks.get(l).getId());
//                List<StockOutRecord> records = stockOutRecordMapper.selectList(param.build());
//                if(records.size() != 0){
//                    tempRecord.add(records.get(0));
//                }
//            }
//            deliDTO.setMaterialInfo(infos.get(i));
//            deliDTO.setMaterialStock(stocks);
//            deliDTO.setStockOutRecord(tempRecord);
//            ans.add(deliDTO);
//        }
        return ans;
    }

    private StockOutRecordVo toVo(StockOutRecord stockOutRecord){
        StockOutRecordVo vo = new StockOutRecordVo();
        Long stockInRecordAccountId = stockOutRecord.getStockInRecordAccountId();
        StockAccountJointVo accountVo = iStockInRecordAccountService.getById(stockInRecordAccountId.toString());
        StockOutRecordHead head = stockOutRecordHeadMapper.selectById(stockOutRecord.getStockOutRecordHeadId());

        vo.setId(stockOutRecord.getId());
        vo.setGroupName(stockOutRecord.getGroupName());
        vo.setStockAccountJointVo(accountVo);
        vo.setCompletionTime(stockOutRecord.getCompletionTime());
        vo.setStockOutRecordHeadCode(head.getStockOutRecordHeadCode());
        return vo;
    }
}
