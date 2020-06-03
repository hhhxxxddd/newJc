package com.jinchi.common.service;

import com.jinchi.common.constant.ProcessEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-10 11:23
 * @description:
 **/
@Service
public class PrecursorCostAccountServiceImp implements PrecursorCostAccountService {

    @Autowired
    GoodsInProcessStatisticHeadMapper headMapper1;
    @Autowired
    MaterialDeliveryStatisticHeadMapper headMapper2;
    @Autowired
    ProductStorageStatisticHeadMapper headMapper3;
    @Autowired
    CostAccountingStatisticHeadMapper costHeadMapper;
    @Autowired
    CostAccountingStatisticTotalsMapper totalsMapper;
    @Autowired
    CostAccountingStatisticByLineDetailMapper lineDetailMapper;
    @Autowired
    BasicInfoPrecursorProductionLineMapper lineMapper;
    @Autowired
    MaterialDeliveryStatisticByLineDetailMapper materialMapper;
    @Autowired
    GoodsInProcessStatisticByLineDetailMapper goodsMapper;
    @Autowired
    ProductStorageStatisticByLineDetailMapper productMapper;
    @Autowired
    AuxiliaryMaterialsStatisticHeadMapper auxiliaryMapper;
    @Autowired
    AuxiliaryMaterialsStatisticDataDetailsDeliveryMapper deliveryMapper;
    @Autowired
    BasicInfoPrecursorMaterialLineWeightMapper lineWeightMapper;
    @Autowired
    AuxiliaryMaterialsStatisticByLineDetailMapper auxiliaryLineMapper;
    @Autowired
    GoodsInProcessStatisticByProcessDetailMapper processMapper;
    @Autowired
    PrecursorStatPeriodService periodService;
    @Autowired
    PrecursorProductionLineService lineService;

    @Override
    public List getStartDate(Integer periodCode) {

        String dataFormat = "yyyyMMdd";

        Map<Integer, String> map = new HashMap<>();

        List<String> res = new ArrayList<>();

        GoodsInProcessStatisticHeadExample example1 = new GoodsInProcessStatisticHeadExample();
        example1.createCriteria().andPeriodCodeEqualTo(periodCode).andFlagEqualTo((byte) 1);
        int count1 = headMapper1.selectByExample(example1).size();
        map.put(count1, "a");

        MaterialDeliveryStatisticHeadExample example2 = new MaterialDeliveryStatisticHeadExample();
        example2.createCriteria().andPeriodCodeEqualTo(periodCode).andFlagEqualTo((byte) 1);
        int count2 = headMapper2.selectByExample(example2).size();
        map.put(count2, "b");

        ProductStorageStatisticHeadExample example3 = new ProductStorageStatisticHeadExample();
        example3.createCriteria().andPeriodCodeEqualTo(periodCode).andFlagEqualTo((byte) 1);
        int count3 = headMapper3.selectByExample(example3).size();
        map.put(count3, "c");

        int temp = count1 >= count2 ? count1 : count2;
        int max = count3 >= temp ? count3 : temp;

        switch (map.get(max)) {
            case "a":
                headMapper1.selectByExample(example1).forEach(item1 -> res.add(ComUtil.dateToString(item1.getStartTime(), dataFormat)));
                break;
            case "b":
                headMapper2.selectByExample(example2).forEach(item2 -> res.add(ComUtil.dateToString(item2.getStartTime(), dataFormat)));
                break;
            case "c":
                headMapper3.selectByExample(example3).forEach(item3 -> res.add(ComUtil.dateToString(item3.getStartTime(), dataFormat)));
        }
        return res;
    }

    @Override
    public List mainMaterialConfirm(Integer lineCode, Integer periodCode, String startTime) {

        Date startDate = ComUtil.getDate(startTime, "yyyyMMdd");

        LocalDateTime localDateTime = ComUtil.dateToLocalDateTime(startDate).plusDays(1).minusSeconds(1);

        LocalDateTime firstDayOfThisYear = LocalDateTime.now().withDayOfYear(1);
        Date firstDate = ComUtil.localDateTimeToDate(firstDayOfThisYear);

        Date endDate = ComUtil.localDateTimeToDate(localDateTime);

        CostAccountingStatisticHeadExample example = new CostAccountingStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodCode).andStartTimeBetween(startDate, endDate);
        List<CostAccountingStatisticHead> heads = costHeadMapper.selectByExample(example);

        List<BasicInfoPrecursorPeriod> all = periodService.getAll();
        Map<Integer, String> periodMap = new HashMap<>();
        all.forEach(item -> periodMap.put(item.getCode(), item.getName()));

        List<BasicInfoPrecursorProductionLine> lines = lineService.getAll();
        Map<Integer, String> lineMap = new HashMap<>();
        lines.forEach(item -> lineMap.put(item.getCode(), item.getName()));

        if (heads.size() > 0) {

            System.out.println("***********现成的************");
            CostAccountingStatisticHead costAccountingStatisticHead = heads.get(0);
            Long statHeadCode = costAccountingStatisticHead.getCode();
            if (lineCode == 0) {
                CostAccountingStatisticTotalsExample totalsExample = new CostAccountingStatisticTotalsExample();
                totalsExample.createCriteria().andStatisticCodeEqualTo(statHeadCode).andMaterialTypeEqualTo((byte) 0);
                List<CostAccountingStatisticTotals> totals = totalsMapper.selectByExample(totalsExample);
                return genFormatMap1(totals, costAccountingStatisticHead, periodMap);
            }
            if (lineCode != 0) {
                CostAccountingStatisticByLineDetailExample lineDetailExample = new CostAccountingStatisticByLineDetailExample();
                lineDetailExample.createCriteria().andStatisticCodeEqualTo(statHeadCode).andLineCodeEqualTo(lineCode).andMaterialTypeEqualTo((byte) 0);
                List<CostAccountingStatisticByLineDetail> lineDetails = lineDetailMapper.selectByExample(lineDetailExample);
                return genFormatMap2(lineDetails, costAccountingStatisticHead, lineMap, periodMap);
            }
        } else {

            System.out.println("***********生成的************");
            GoodsInProcessStatisticHeadExample example1 = new GoodsInProcessStatisticHeadExample();
            example1.createCriteria().andPeriodCodeEqualTo(periodCode).andStartTimeBetween(startDate, endDate).andFlagEqualTo((byte) 1);
            List<GoodsInProcessStatisticHead> list1 = headMapper1.selectByExample(example1);

            MaterialDeliveryStatisticHeadExample example2 = new MaterialDeliveryStatisticHeadExample();
            example2.createCriteria().andPeriodCodeEqualTo(periodCode).andStartTimeBetween(startDate, endDate).andFlagEqualTo((byte) 1);
            List<MaterialDeliveryStatisticHead> list2 = headMapper2.selectByExample(example2);

            ProductStorageStatisticHeadExample example3 = new ProductStorageStatisticHeadExample();
            example3.createCriteria().andPeriodCodeEqualTo(periodCode).andStartTimeBetween(startDate, endDate).andFlagEqualTo((byte) 1);
            List<ProductStorageStatisticHead> list3 = headMapper3.selectByExample(example3);

            if (list1.size() > 0 && list2.size() > 0 && list3.size() > 0) {
                Integer periodNum = list1.get(0).getLineName() - 1;
                GoodsInProcessStatisticHeadExample headExample = new GoodsInProcessStatisticHeadExample();
                headExample.createCriteria().andPeriodCodeEqualTo(periodCode).andFlagEqualTo((byte) 1).andLineNameEqualTo(periodNum).andStartTimeGreaterThanOrEqualTo(firstDate);
                List<GoodsInProcessStatisticHead> list = headMapper1.selectByExample(headExample);
                if (list.size() >= 0) {
                    GoodsInProcessStatisticHead goodsHead = list1.get(0);
                    //生成成本核算统计头表
                    CostAccountingStatisticHead head = new CostAccountingStatisticHead();
                    head.setPeriodCode(goodsHead.getPeriodCode());
                    head.setPeriods(goodsHead.getLineName());
                    head.setStartTime(goodsHead.getStartTime());
                    head.setEndTime(goodsHead.getEndTime());
                    costHeadMapper.insertSelective(head);

                    //原料领用统计编码
                    Long matStatCode = list2.get(0).getCode();
                    //在制品本期统计编码
                    Long goodsStatCode = list1.get(0).getCode();
                    //在制品上期统计编码
                    Long goodsLastStatCode = list.size() == 0 ? 0 : list.get(0).getCode();
                    //成品入库统计编码
                    Long productStatCode = list3.get(0).getCode();

                    float productStorage0 = 0;
                    float currentGoodsInProcess0 = 0;
                    float lastGoodsInProcess0 = 0;
                    float materialRequisitions0 = 0;
                    float unitConsumption0 = 0;

                    float productStorage1 = 0;
                    float currentGoodsInProcess1 = 0;
                    float lastGoodsInProcess1 = 0;
                    float materialRequisitions1 = 0;
                    float unitConsumption1 = 0;

                    float productStorage2 = 0;
                    float currentGoodsInProcess2 = 0;
                    float lastGoodsInProcess2 = 0;
                    float materialRequisitions2 = 0;
                    float unitConsumption2 = 0;

                    //获取用到的产线 也就是获取

                    //生成主材成本核算按产线统计明细数据表
                    BasicInfoPrecursorProductionLineExample lineExample = new BasicInfoPrecursorProductionLineExample();
                    lineExample.createCriteria();
                    List<BasicInfoPrecursorProductionLine> productionLines = lineMapper.selectByExample(lineExample);
                    for (BasicInfoPrecursorProductionLine line : productionLines) {

                        MaterialDeliveryStatisticByLineDetailExample materialExample = new MaterialDeliveryStatisticByLineDetailExample();
                        materialExample.createCriteria().andStatisticCodeEqualTo(matStatCode).andLineCodeEqualTo(line.getCode());
                        List<MaterialDeliveryStatisticByLineDetail> lineDetails2 = materialMapper.selectByExample(materialExample);
                        MaterialDeliveryStatisticByLineDetail lineDetail2 = new MaterialDeliveryStatisticByLineDetail();
                        if (lineDetails2.size() == 0) {
                            lineDetail2.setMnValue((float) 0);
                            lineDetail2.setCoValue((float) 0);
                            lineDetail2.setNiValue((float) 0);
                        } else {
                            lineDetail2 = lineDetails2.get(0);
                        }


                        GoodsInProcessStatisticByLineDetailExample goodsExample = new GoodsInProcessStatisticByLineDetailExample();
                        goodsExample.createCriteria().andStatisticCodeEqualTo(goodsStatCode).andLineCodeEqualTo(line.getCode());
                        List<GoodsInProcessStatisticByLineDetail> lineDetails1 = goodsMapper.selectByExample(goodsExample);
                        GoodsInProcessStatisticByLineDetail lineDetail1 = new GoodsInProcessStatisticByLineDetail();
                        if (lineDetails1.size() == 0) {
                            lineDetail1.setNiValue((float) 0);
                            lineDetail1.setCoValue((float) 0);
                            lineDetail1.setMnValue((float) 0);
                        } else {
                            lineDetail1 = lineDetails1.get(0);
                        }

                        GoodsInProcessStatisticByLineDetailExample goodsLastExample = new GoodsInProcessStatisticByLineDetailExample();
                        goodsLastExample.createCriteria().andStatisticCodeEqualTo(goodsLastStatCode).andLineCodeEqualTo(line.getCode());
                        List<GoodsInProcessStatisticByLineDetail> lineDetails11 = goodsMapper.selectByExample(goodsExample);
                        GoodsInProcessStatisticByLineDetail lineDetail11 = new GoodsInProcessStatisticByLineDetail();

                        if (lineDetails11.size() == 0) {
                            lineDetail11.setNiValue((float) 0);
                            lineDetail11.setCoValue((float) 0);
                            lineDetail11.setMnValue((float) 0);
                        } else {
                            lineDetail11 = lineDetails11.get(0);
                        }


                        ProductStorageStatisticByLineDetailExample productExample = new ProductStorageStatisticByLineDetailExample();
                        productExample.createCriteria().andStatisticCodeEqualTo(productStatCode).andLineCodeEqualTo(line.getCode());
                        List<ProductStorageStatisticByLineDetail> lineDetails3 = productMapper.selectByExample(productExample);
                        ProductStorageStatisticByLineDetail lineDetail3 = new ProductStorageStatisticByLineDetail();
                        if (lineDetails3.size() == 0) {
                            lineDetail3.setCoValue((float) 0);
                            lineDetail3.setNiValue((float) 0);
                            lineDetail3.setMnValue((float) 0);
                        } else {
                            lineDetail3 = lineDetails3.get(0);
                        }


                        for (int elementCode = 0; elementCode < 3; elementCode++) {
                            CostAccountingStatisticByLineDetail byLineDetail = new CostAccountingStatisticByLineDetail();
                            byLineDetail.setStatisticCode(head.getCode());
                            byLineDetail.setPeriods(head.getPeriods());
                            byLineDetail.setLineCode(line.getCode());
                            byLineDetail.setMaterialType((byte) 0);
                            byLineDetail.setElementType((byte) elementCode);
                            byLineDetail.setIntermediateProductsVariation((float) 0);
                            if (elementCode == 0) {
                                byLineDetail.setProductStorage(lineDetail3.getNiValue());
                                byLineDetail.setCurrentGoodsInProcess(lineDetail1.getNiValue());
                                byLineDetail.setLastGoodsInProcess(lineDetail11.getNiValue());
                                byLineDetail.setMaterialRequisitions(lineDetail2.getNiValue());
                                productStorage0 += byLineDetail.getProductStorage();
                                currentGoodsInProcess0 += byLineDetail.getCurrentGoodsInProcess();
                                lastGoodsInProcess0 += byLineDetail.getLastGoodsInProcess();
                                materialRequisitions0 += byLineDetail.getMaterialRequisitions();
                                byLineDetail.setUnitConsumption(byLineDetail.getProductStorage() == 0 ?
                                        -1 : (byLineDetail.getMaterialRequisitions() + byLineDetail.getLastGoodsInProcess() - byLineDetail.getCurrentGoodsInProcess()) / byLineDetail.getProductStorage());
                                unitConsumption0 += byLineDetail.getUnitConsumption();
                            }
                            if (elementCode == 1) {
                                byLineDetail.setProductStorage(lineDetail3.getCoValue());
                                byLineDetail.setCurrentGoodsInProcess(lineDetail1.getCoValue());
                                byLineDetail.setLastGoodsInProcess(lineDetail11.getCoValue());
                                byLineDetail.setMaterialRequisitions(lineDetail2.getCoValue());
                                productStorage1 += byLineDetail.getProductStorage();
                                currentGoodsInProcess1 += byLineDetail.getCurrentGoodsInProcess();
                                lastGoodsInProcess1 += byLineDetail.getLastGoodsInProcess();
                                materialRequisitions1 += byLineDetail.getMaterialRequisitions();
                                byLineDetail.setUnitConsumption(byLineDetail.getProductStorage() == 0 ?
                                        -1 : (byLineDetail.getMaterialRequisitions() + byLineDetail.getLastGoodsInProcess() - byLineDetail.getCurrentGoodsInProcess()) / byLineDetail.getProductStorage());
                                unitConsumption1 += byLineDetail.getUnitConsumption();
                            }
                            if (elementCode == 2) {
                                byLineDetail.setProductStorage(lineDetail3.getMnValue());
                                byLineDetail.setCurrentGoodsInProcess(lineDetail1.getMnValue());
                                byLineDetail.setLastGoodsInProcess(lineDetail11.getMnValue());
                                byLineDetail.setMaterialRequisitions(lineDetail2.getMnValue());
                                productStorage2 += byLineDetail.getProductStorage();
                                currentGoodsInProcess2 += byLineDetail.getCurrentGoodsInProcess();
                                lastGoodsInProcess2 += byLineDetail.getLastGoodsInProcess();
                                materialRequisitions2 += byLineDetail.getMaterialRequisitions();
                                byLineDetail.setUnitConsumption(byLineDetail.getProductStorage() == 0 ?
                                        -1 : (byLineDetail.getMaterialRequisitions() + byLineDetail.getLastGoodsInProcess() - byLineDetail.getCurrentGoodsInProcess()) / byLineDetail.getProductStorage());
                                unitConsumption2 += byLineDetail.getUnitConsumption();
                            }
                            lineDetailMapper.insertSelective(byLineDetail);
                        }
                    }

                    List<Float> materialRequisitions = Arrays.asList(materialRequisitions0, materialRequisitions1, materialRequisitions2);
                    List<Float> currentGoodsInProcess = Arrays.asList(currentGoodsInProcess0, currentGoodsInProcess1, currentGoodsInProcess2);
                    List<Float> productStorage = Arrays.asList(productStorage0, productStorage1, productStorage2);
                    List<Float> lastGoodsInProcess = Arrays.asList(lastGoodsInProcess0, lastGoodsInProcess1, lastGoodsInProcess2);
                    List<Float> unitConsumption = Arrays.asList(unitConsumption0, unitConsumption1, unitConsumption2);
                    //整体统计
                    for (int elementCode = 0; elementCode < 3; elementCode++) {
                        CostAccountingStatisticTotals totals = new CostAccountingStatisticTotals();
                        totals.setStatisticCode(head.getCode());
                        totals.setPeriods(head.getPeriods());
                        totals.setMaterialType((byte) 0);
                        totals.setElementType((byte) elementCode);
                        totals.setMaterialRequisitions(materialRequisitions.get(elementCode));
                        totals.setCurrentGoodsInProcess(currentGoodsInProcess.get(elementCode));
                        totals.setProductStorage(productStorage.get(elementCode));
                        totals.setLastGoodsInProcess(lastGoodsInProcess.get(elementCode));
                        totals.setUnitConsumption(unitConsumption.get(elementCode));
                        totals.setIntermediateProductsVariation((float) 0);
                        totalsMapper.insertSelective(totals);
                    }

                    CostAccountingStatisticHeadExample example0 = new CostAccountingStatisticHeadExample();
                    example0.createCriteria().andPeriodCodeEqualTo(periodCode).andStartTimeBetween(startDate, endDate);
                    List<CostAccountingStatisticHead> statisticHeads = costHeadMapper.selectByExample(example);

                    Long statHeadCode = statisticHeads.get(0).getCode();
                    CostAccountingStatisticHead costAccountingStatisticHead = heads.get(0);

                    if (lineCode == 0) {
                        CostAccountingStatisticTotalsExample totalsExample = new CostAccountingStatisticTotalsExample();
                        totalsExample.createCriteria().andStatisticCodeEqualTo(statHeadCode).andMaterialTypeEqualTo((byte) 0);
                        List<CostAccountingStatisticTotals> totals = totalsMapper.selectByExample(totalsExample);
                        return genFormatMap1(totals, costAccountingStatisticHead, periodMap);
                    }
                    if (lineCode != 0) {
                        CostAccountingStatisticByLineDetailExample lineDetailExample = new CostAccountingStatisticByLineDetailExample();
                        lineDetailExample.createCriteria().andStatisticCodeEqualTo(statHeadCode).andLineCodeEqualTo(lineCode).andMaterialTypeEqualTo((byte) 0);
                        List<CostAccountingStatisticByLineDetail> lineDetails = lineDetailMapper.selectByExample(lineDetailExample);
                        return genFormatMap2(lineDetails, costAccountingStatisticHead, lineMap, periodMap);
                    }
                } else {
                    return new ArrayList();
                }
            } else {
                return new ArrayList();
            }
        }

        return new ArrayList();
    }

    private List genFormatMap2(List<CostAccountingStatisticByLineDetail> lineDetails, CostAccountingStatisticHead costAccountingStatisticHead, Map<Integer, String> lineMap, Map<Integer, String> periodMap) {
        List<Map<String, Object>> list = new ArrayList<>();

        String sdf = "yyyy-MM-dd HH:mm:ss";

        for (CostAccountingStatisticByLineDetail record : lineDetails) {
            Map<String, Object> map = new HashMap<>();
            map.put("elementName", lineMap.get(record.getLineCode()));
            if (record.getElementType() == 0) {
                map.put("materialType", "Ni");
            }
            if (record.getElementType() == 1) {
                map.put("materialType", "Co");
            }
            if (record.getElementType() == 2) {
                map.put("materialType", "Mn");
            }
            if (record.getElementType() == 3) {
                map.put("materialType", "氨");
            }
            if (record.getElementType() == 4) {
                map.put("materialType", "碱");
            }
            map.put("period", periodMap.get(costAccountingStatisticHead.getPeriodCode()));
            map.put("periods", record.getPeriods());
            map.put("startTime", ComUtil.dateToString(costAccountingStatisticHead.getStartTime(), sdf));
            map.put("endTime", ComUtil.dateToString(costAccountingStatisticHead.getEndTime(), sdf));
            map.put("materialRequisitions", record.getMaterialRequisitions());
            map.put("currentGoodsInProcess", record.getCurrentGoodsInProcess());
            map.put("lastGoodsInProcess", record.getLastGoodsInProcess());
            map.put("productStorage", record.getProductStorage());
            map.put("intermediateProductsVariation", record.getIntermediateProductsVariation());
            map.put("unitConsumption", record.getUnitConsumption());

            list.add(map);
        }

        return list;
    }


    private List genFormatMap1(List<CostAccountingStatisticTotals> totals, CostAccountingStatisticHead costAccountingStatisticHead, Map<Integer, String> periodMap) {
        List<Map<String, Object>> list = new ArrayList<>();

        String sdf = "yyyy-MM-dd HH:mm:ss";

        for (CostAccountingStatisticTotals record : totals) {
            Map<String, Object> map = new HashMap<>();
            map.put("elementName", "全部产线");
            if (record.getElementType() == 0) {
                map.put("materialType", "Ni");
            }
            if (record.getElementType() == 1) {
                map.put("materialType", "Co");
            }
            if (record.getElementType() == 2) {
                map.put("materialType", "Mn");
            }
            if (record.getElementType() == 3) {
                map.put("materialType", "氨");
            }
            if (record.getElementType() == 4) {
                map.put("materialType", "碱");
            }

            map.put("period", periodMap.get(costAccountingStatisticHead.getPeriodCode()));
            map.put("periods", record.getPeriods());
            map.put("startTime", ComUtil.dateToString(costAccountingStatisticHead.getStartTime(), sdf));
            map.put("endTime", ComUtil.dateToString(costAccountingStatisticHead.getEndTime(), sdf));
            map.put("materialRequisitions", record.getMaterialRequisitions());
            map.put("currentGoodsInProcess", record.getCurrentGoodsInProcess());
            map.put("lastGoodsInProcess", record.getLastGoodsInProcess());
            map.put("productStorage", record.getProductStorage());
            map.put("intermediateProductsVariation", record.getIntermediateProductsVariation());
            map.put("unitConsumption", record.getUnitConsumption());

            list.add(map);
        }

        return list;
    }


    @Override
    public List auxiliaryConfirm(Integer lineCode, Integer periodCode, String startTime) {
        Date startDate = ComUtil.getDate(startTime, "yyyyMMdd");

        LocalDateTime localDateTime = ComUtil.dateToLocalDateTime(startDate).plusDays(1).minusSeconds(1);

        LocalDateTime firstDayOfThisYear = LocalDateTime.now().withDayOfYear(1);
        Date firstDate = ComUtil.localDateTimeToDate(firstDayOfThisYear);

        Date endDate = ComUtil.localDateTimeToDate(localDateTime);

        List<BasicInfoPrecursorPeriod> all = periodService.getAll();
        Map<Integer, String> periodMap = new HashMap<>();
        all.forEach(item -> periodMap.put(item.getCode(), item.getName()));

        List<BasicInfoPrecursorProductionLine> ans = lineService.getAll();
        Map<Integer, String> lineMap = new HashMap<>();
        ans.forEach(item -> lineMap.put(item.getCode(), item.getName()));

        CostAccountingStatisticHeadExample example = new CostAccountingStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodCode).andStartTimeBetween(startDate, endDate);
        List<CostAccountingStatisticHead> heads = costHeadMapper.selectByExample(example);
        if (heads.size() > 0) {
            System.out.println("*************现货*****************");
            Long statHeadCode = heads.get(0).getCode();
            CostAccountingStatisticHead costAccountingStatisticHead = heads.get(0);
            if (heads.get(0).getFlag()) {
                //flag == 1
                if (lineCode == 0) {
                    CostAccountingStatisticTotalsExample totalsExample = new CostAccountingStatisticTotalsExample();
                    totalsExample.createCriteria().andMaterialTypeEqualTo((byte) 1).andStatisticCodeEqualTo(statHeadCode);
                    List<CostAccountingStatisticTotals> totals = totalsMapper.selectByExample(totalsExample);
                    return genFormatMap1(totals, costAccountingStatisticHead, periodMap);
                }
                if (lineCode != 0) {
                    CostAccountingStatisticByLineDetailExample lineDetailExample = new CostAccountingStatisticByLineDetailExample();
                    lineDetailExample.createCriteria().andMaterialTypeEqualTo((byte) 1).andStatisticCodeEqualTo(statHeadCode).andLineCodeEqualTo(lineCode);
                    List<CostAccountingStatisticByLineDetail> lineDetails = lineDetailMapper.selectByExample(lineDetailExample);
                    return genFormatMap2(lineDetails, costAccountingStatisticHead, lineMap, periodMap);
                }
            } else {
                //flag == 0
                AuxiliaryMaterialsStatisticHeadExample headExample = new AuxiliaryMaterialsStatisticHeadExample();
                headExample.createCriteria().andPeriodCodeEqualTo(periodCode).andStartTimeBetween(startDate, endDate).andFlagEqualTo(true);
                List<AuxiliaryMaterialsStatisticHead> list1 = auxiliaryMapper.selectByExample(headExample);

                if (list1.size() > 0) {
                    Integer periods = list1.get(0).getPeriods() - 1;
                    AuxiliaryMaterialsStatisticHeadExample headLastExample = new AuxiliaryMaterialsStatisticHeadExample();
                    headLastExample.createCriteria().andPeriodCodeEqualTo(periodCode).andFlagEqualTo(true).andPeriodsEqualTo(periods).andStartTimeGreaterThanOrEqualTo(firstDate);

                    List<AuxiliaryMaterialsStatisticHead> list2 = auxiliaryMapper.selectByExample(headLastExample);
                    if (list2.size() > 0) {
                        Long statCurrentCode = list1.get(0).getCode();
                        Long statLastCode = list2.get(0).getCode();

                        //S4 生成成本核算相关统计数据
                        //头表flag更新为1
                        CostAccountingStatisticHead head = heads.get(0);
                        head.setFlag(true);
                        costHeadMapper.updateByPrimaryKeySelective(head);

                        BasicInfoPrecursorProductionLineExample productionLineExample = new BasicInfoPrecursorProductionLineExample();
                        productionLineExample.createCriteria();
                        List<BasicInfoPrecursorProductionLine> lines = lineMapper.selectByExample(productionLineExample);

                        AuxiliaryMaterialsStatisticDataDetailsDeliveryExample deliveryExample = new AuxiliaryMaterialsStatisticDataDetailsDeliveryExample();
                        deliveryExample.createCriteria().andStatisticCodeEqualTo(statCurrentCode);
                        List<AuxiliaryMaterialsStatisticDataDetailsDelivery> deliveries = deliveryMapper.selectByExample(deliveryExample);
                        Assert.notEmpty(deliveries, "本期入库量基础数据不存在");

                        AuxiliaryMaterialsStatisticDataDetailsDeliveryExample deliveryExample1 = new AuxiliaryMaterialsStatisticDataDetailsDeliveryExample();
                        deliveryExample1.createCriteria().andStatisticCodeEqualTo(statLastCode);
                        List<AuxiliaryMaterialsStatisticDataDetailsDelivery> deliveries1 = deliveryMapper.selectByExample(deliveryExample1);
                        Assert.notEmpty(deliveries1, "上期入库量基础数据不存在");

                        Map<Integer, Float> ammMap = new HashMap<>();
                        Map<Integer, Float> ammLastMap = new HashMap<>();

                        Map<Integer, Float> alkMap = new HashMap<>();
                        Map<Integer, Float> alkLastMap = new HashMap<>();
                        for (BasicInfoPrecursorProductionLine line : lines) {
                            ammMap.put(line.getCode(), (float) 0);
                            ammLastMap.put(line.getCode(), (float) 0);
                            alkMap.put(line.getCode(), (float) 0);
                            alkLastMap.put(line.getCode(), (float) 0);
                        }
                        for (AuxiliaryMaterialsStatisticDataDetailsDelivery delivery : deliveries1) {
                            Integer materialCode = delivery.getMaterialCode();

                            Float ammoniaValue = delivery.getAmmoniaValue();
                            Float alkaliValue = delivery.getAlkaliValue();
                            BasicInfoPrecursorMaterialLineWeightExample lineWeightExample = new BasicInfoPrecursorMaterialLineWeightExample();
                            lineWeightExample.createCriteria().andMaterialCodeEqualTo(materialCode);
                            List<BasicInfoPrecursorMaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(lineWeightExample);
                            for (BasicInfoPrecursorMaterialLineWeight lineWeight : lineWeights) {
                                ammLastMap.put(lineWeight.getLineCode(), ammLastMap.get(lineWeight.getLineCode()) + ammoniaValue * lineWeight.getWeightValue());
                                alkLastMap.put(lineWeight.getLineCode(), alkLastMap.get(lineWeight.getLineCode() + alkaliValue * lineWeight.getWeightValue()));
                            }
                        }

                        for (AuxiliaryMaterialsStatisticDataDetailsDelivery delivery : deliveries) {
                            Integer materialCode = delivery.getMaterialCode();

                            Float ammoniaValue = delivery.getAmmoniaValue();
                            Float alkaliValue = delivery.getAlkaliValue();
                            BasicInfoPrecursorMaterialLineWeightExample lineWeightExample = new BasicInfoPrecursorMaterialLineWeightExample();
                            lineWeightExample.createCriteria().andMaterialCodeEqualTo(materialCode);
                            List<BasicInfoPrecursorMaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(lineWeightExample);
                            for (BasicInfoPrecursorMaterialLineWeight lineWeight : lineWeights) {
                                ammMap.put(lineWeight.getLineCode(), ammMap.get(lineWeight.getLineCode()) + ammoniaValue * lineWeight.getWeightValue());
                                alkMap.put(lineWeight.getLineCode(), alkMap.get(lineWeight.getLineCode() + alkaliValue * lineWeight.getWeightValue()));
                            }
                        }

                        List<Integer> processList = Arrays
                                .asList(ProcessEnum.COMPOUND.getProcessId(),
                                        ProcessEnum.AGEING.getProcessId(),
                                        ProcessEnum.DRYING.getProcessId(),
                                        ProcessEnum.OTHER.getProcessId());
                        GoodsInProcessStatisticByProcessDetailExample processDetailExample = new GoodsInProcessStatisticByProcessDetailExample();
                        processDetailExample.createCriteria().andProcessCodeIn(processList).andLineNameEqualTo(head.getPeriods());
                        List<GoodsInProcessStatisticByProcessDetail> byProcessDetails = processMapper.selectByExample(processDetailExample);
                        processDetailExample.clear();
                        processDetailExample.createCriteria().andProcessCodeIn(processList).andLineNameEqualTo(head.getPeriods() - 1);
                        List<GoodsInProcessStatisticByProcessDetail> byProcessDetails1 = processMapper.selectByExample(processDetailExample);
                        float totalNi = 0;
                        float totalCo = 0;
                        float totalMn = 0;

                        float totalNiLast = 0;
                        float totalCoLast = 0;
                        float totalMnLast = 0;
                        for (GoodsInProcessStatisticByProcessDetail byProcessDetail : byProcessDetails) {
                            totalNi += byProcessDetail.getNiValue();
                            totalCo += byProcessDetail.getCoValue();
                            totalMn += byProcessDetail.getMnValue();
                        }
                        for (GoodsInProcessStatisticByProcessDetail byProcessDetail : byProcessDetails1) {
                            totalNiLast += byProcessDetail.getNiValue();
                            totalCoLast += byProcessDetail.getCoValue();
                            totalMnLast += byProcessDetail.getMnValue();
                        }
                        //按产线统计明细
                        for (BasicInfoPrecursorProductionLine line : lines) {
                            AuxiliaryMaterialsStatisticByLineDetailExample detailExample = new AuxiliaryMaterialsStatisticByLineDetailExample();
                            detailExample.createCriteria().andStatisticCodeEqualTo(statCurrentCode).andLineCodeEqualTo(line.getCode());
                            AuxiliaryMaterialsStatisticByLineDetail detail = auxiliaryLineMapper.selectByExample(detailExample).get(0);
                            detailExample.clear();
                            detailExample.createCriteria().andStatisticCodeEqualTo(statLastCode).andLineCodeEqualTo(line.getCode());
                            AuxiliaryMaterialsStatisticByLineDetail lastDetail = auxiliaryLineMapper.selectByExample(detailExample).get(0);

                            Float ammoniaValue = detail.getAmmoniaValue();
                            Float alkaliValue = detail.getAlkaliValue();

                            Float ammoniaValueLast = lastDetail.getAmmoniaValue();
                            Float alkaliValueLast = lastDetail.getAlkaliValue();

                            ProductStorageStatisticByLineDetailExample productExample = new ProductStorageStatisticByLineDetailExample();
                            productExample.createCriteria().andPeriodsEqualTo(head.getPeriods()).andLineCodeEqualTo(line.getCode());
                            ProductStorageStatisticByLineDetail lineDetail1 = productMapper.selectByExample(productExample).get(0);


                            for (int elementCode = 3; elementCode < 5; elementCode++) {
                                CostAccountingStatisticByLineDetail lineDetail = new CostAccountingStatisticByLineDetail();
                                lineDetail.setStatisticCode(head.getCode());
                                lineDetail.setMaterialType((byte) 1);
                                lineDetail.setPeriods(head.getPeriods());
                                lineDetail.setLineCode(line.getCode());
                                lineDetail.setElementType((byte) elementCode);
                                lineDetail.setProductStorage(lineDetail1.getMnValue()
                                        + lineDetail1.getCoValue() + lineDetail1.getNiValue());
                                lineDetail.setIntermediateProductsVariation(totalNi - totalNiLast + totalMn - totalMnLast
                                        + totalCo - totalCoLast);
                                if (elementCode == 3) {
                                    lineDetail.setMaterialRequisitions(ammMap.get(line.getCode()));
                                    lineDetail.setCurrentGoodsInProcess(ammoniaValue - ammMap.get(line.getCode()));
                                    lineDetail.setLastGoodsInProcess(ammoniaValueLast - ammLastMap.get(line.getCode()));
                                    lineDetail.setUnitConsumption((lineDetail.getProductStorage() + lineDetail.getIntermediateProductsVariation()) == 0 ? -1 :
                                            (lineDetail.getMaterialRequisitions() + lineDetail.getLastGoodsInProcess()
                                                    - lineDetail.getCurrentGoodsInProcess()) / (lineDetail.getProductStorage() + lineDetail.getIntermediateProductsVariation()));
                                }
                                if (elementCode == 4) {
                                    lineDetail.setMaterialRequisitions(alkMap.get(line.getCode()));
                                    lineDetail.setCurrentGoodsInProcess(alkaliValue - alkMap.get(line.getCode()));
                                    lineDetail.setLastGoodsInProcess(alkaliValueLast - alkLastMap.get(line.getCode()));
                                    lineDetail.setUnitConsumption((lineDetail.getProductStorage() + lineDetail.getIntermediateProductsVariation()) == 0 ? -1 :
                                            (lineDetail.getMaterialRequisitions() + lineDetail.getLastGoodsInProcess()
                                                    - lineDetail.getCurrentGoodsInProcess()) / (lineDetail.getProductStorage() + lineDetail.getIntermediateProductsVariation()));
                                }
                                lineDetailMapper.insertSelective(lineDetail);

                            }
                        }
                        //分氨 碱 合计
                        for (int elementCode = 3; elementCode < 5; elementCode++) {
                            CostAccountingStatisticByLineDetailExample example1 = new CostAccountingStatisticByLineDetailExample();
                            example1.createCriteria().andElementTypeEqualTo((byte) elementCode);
                            List<CostAccountingStatisticByLineDetail> byLineDetailList = lineDetailMapper.selectByExample(example1);
                            float totalInter = 0;
                            float totalPro = 0;
                            float totalCurr = 0;
                            float totalLast = 0;
                            float totalUnit = 0;
                            float totalMat = 0;
                            for (CostAccountingStatisticByLineDetail detail : byLineDetailList) {
                                totalInter += detail.getIntermediateProductsVariation();
                                totalPro += detail.getProductStorage();
                                totalCurr += detail.getCurrentGoodsInProcess();
                                totalLast += detail.getLastGoodsInProcess();
                                totalUnit += detail.getUnitConsumption();
                                totalMat += detail.getMaterialRequisitions();
                            }
                            CostAccountingStatisticTotals totals = new CostAccountingStatisticTotals();
                            totals.setStatisticCode(head.getCode());
                            totals.setPeriods(head.getPeriods());
                            totals.setMaterialType((byte) 1);
                            totals.setElementType((byte) elementCode);
                            totals.setMaterialRequisitions(totalMat);
                            totals.setCurrentGoodsInProcess(totalCurr);
                            totals.setProductStorage(totalPro);
                            totals.setLastGoodsInProcess(totalLast);
                            totals.setUnitConsumption(totalUnit);
                            totals.setIntermediateProductsVariation(totalInter);
                            totalsMapper.insertSelective(totals);
                        }

                    } else {
                        return new ArrayList();
                    }
                } else {
                    return new ArrayList();
                }

            }
        } else {
            // 若code不存在 转S6 先进行主材核算 再进行辅材核算
            mainMaterialConfirm(lineCode, periodCode, startTime);
            auxiliaryConfirm(lineCode, periodCode, startTime);
        }
        CostAccountingStatisticHeadExample example0 = new CostAccountingStatisticHeadExample();
        example0.createCriteria().andPeriodCodeEqualTo(periodCode).andStartTimeBetween(startDate, endDate);
        List<CostAccountingStatisticHead> statisticHeads = costHeadMapper.selectByExample(example);

        Long statHeadCode = statisticHeads.get(0).getCode();
        CostAccountingStatisticHead costAccountingStatisticHead = statisticHeads.get(0);
        if (lineCode == 0) {
            CostAccountingStatisticTotalsExample totalsExample = new CostAccountingStatisticTotalsExample();
            totalsExample.createCriteria().andMaterialTypeEqualTo((byte) 1).andStatisticCodeEqualTo(statHeadCode);
            List<CostAccountingStatisticTotals> totals = totalsMapper.selectByExample(totalsExample);
            return genFormatMap1(totals, costAccountingStatisticHead, periodMap);
        }
        if (lineCode != 0) {
            CostAccountingStatisticByLineDetailExample lineDetailExample = new CostAccountingStatisticByLineDetailExample();
            lineDetailExample.createCriteria().andMaterialTypeEqualTo((byte) 1).andStatisticCodeEqualTo(statHeadCode).andLineCodeEqualTo(lineCode);
            List<CostAccountingStatisticByLineDetail> lineDetails = lineDetailMapper.selectByExample(lineDetailExample);
            return genFormatMap2(lineDetails, costAccountingStatisticHead, lineMap, periodMap);
        }
        return new ArrayList();
    }
}
