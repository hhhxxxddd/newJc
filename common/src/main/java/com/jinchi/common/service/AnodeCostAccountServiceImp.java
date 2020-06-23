package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-29 00:06
 * @description:
 **/
@Service
@Transactional
public class AnodeCostAccountServiceImp implements AnodeCostAccountService {

    @Autowired
    AnodeGoodsInProcessStatisticByLineDetailsMapper detailsMapper;
    @Autowired
    AnodeGoodsInProcessStatisticHeadMapper headMapper;
    @Autowired
    AnodeCostAccountingStatisticHeadMapper costHeadMapper;
    @Autowired
    AnodeCostAccountingStatisticByLineDetailMapper byLineDetailMapper;
    @Autowired
    AnodeProductionLineService anodeProductionLineService;
    @Autowired
    AnodeStatPeriodService anodeStatPeriodService;
    @Autowired
    AnodeCostAccountingStatisticByLineOnlineRawMaterialMapper byLineOnlineRawMaterialMapper;
    @Autowired
    AnodeCostAccountingStatisticTotalsMapper totalsMapper;
    @Autowired
    AnodeCostAccountingStatisticTotalsOnlineRawMaterialMapper totalsOnlineRawMaterialMapper;
    @Autowired
    AnodeGoodsInProcessStatisticOnlineRawMaterialMapper rawMaterialMapper;
    @Autowired
    AnodeGoodsInProcessStatisticSingleMaterialWeightsMapper weightsMapper;
    @Autowired
    AnodeGoodsInProcessStatisticLineProcessProductionsMapper processProductionsMapper;
    @Autowired
    AnodeCalculateCoefficientService coefficientService;

    @Override
    public List getDate(Integer periodId) {

        String sql = "SELECT DISTINCT periods FROM anode_goods_in_process_statistic_by_line_details WHERE period_code = '" + periodId + "'";

        List<Integer> bySQL = detailsMapper.selectBySQL(sql);

        List<Map<String, Object>> res = new ArrayList<>();

        String format = "yyyyMMdd";

        for (Integer periods : bySQL) {
            AnodeGoodsInProcessStatisticHeadExample headExample = new AnodeGoodsInProcessStatisticHeadExample();
            headExample.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods);
            List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(headExample);

            AnodeGoodsInProcessStatisticHead head = heads.get(0);

            Map<String, Object> map = new HashMap<>();
            map.put("periods", head.getPeriods());
            map.put("startTime", ComUtil.dateToString(head.getBeginTime(), format));
            map.put("endTime", ComUtil.dateToString(head.getEndTime(), format));
            res.add(map);
        }

        return res;
    }

    @Override
    public Map getRecordByPeriod(Integer periodId, Integer periods) {
        AnodeCostAccountingStatisticHeadExample example = new AnodeCostAccountingStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods);
        List<AnodeCostAccountingStatisticHead> headList = costHeadMapper.selectByExample(example);

        if (headList.size() == 0) {
            return new HashMap();
        } else {
            String format = "yyyy-MM-dd HH:mm:ss";
            Map<String, String> map = new HashMap<>();

            AnodeCostAccountingStatisticHead temp = headList.get(0);

            map.put("periodName", anodeStatPeriodService.getNameById(temp.getPeriodCode()));
            map.put("periods", String.valueOf(temp.getPeriods()));
            map.put("startTime", ComUtil.dateToString(temp.getBeginTime(), format));
            map.put("endTime", ComUtil.dateToString(temp.getEndTime(), format));

            return map;
        }
    }

    @Override
    public List confirm(Integer lineCode, Integer periodId, Integer periods) {

        List<Map<String, Object>> res = new ArrayList<>();
        AnodeCostAccountingStatisticHeadExample headExample = new AnodeCostAccountingStatisticHeadExample();
        headExample.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods).andFlagEqualTo(true);
        List<AnodeCostAccountingStatisticHead> heads = costHeadMapper.selectByExample(headExample);

        if (heads.size() == 0) {
            AnodeGoodsInProcessStatisticByLineDetailsExample example1 = new AnodeGoodsInProcessStatisticByLineDetailsExample();
            example1.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods);
            List<AnodeGoodsInProcessStatisticByLineDetails> details = detailsMapper.selectByExample(example1);

//            if (details.size() == 0) {
//                return Arrays.asList(1);
//            }
            example1.clear();
            example1.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods - 1);
            List<AnodeGoodsInProcessStatisticByLineDetails> detailsLast = detailsMapper.selectByExample(example1);


            Map<Integer, Float> firstMap = new HashMap<>();
            Map<Integer, Float> secondMap = new HashMap<>();

            if (detailsLast.size() == 0) {
                List<BasicInfoAnodeProductionLine> lineServiceAll = anodeProductionLineService.getAll();
                lineServiceAll.forEach(item -> {
                    firstMap.put(item.getCode(), (float) 0);
                    secondMap.put(item.getCode(), (float) 0);
                });
            } else {
                detailsLast.forEach(item -> {
                    firstMap.put(item.getLineCode(), item.getFirstProcess());
                    secondMap.put(item.getLineCode(), item.getSecondProces());
                });
            }


            AnodeGoodsInProcessStatisticHeadExample example3 = new AnodeGoodsInProcessStatisticHeadExample();
            example3.createCriteria().andPeriodsEqualTo(periods).andPeriodCodeEqualTo(periodId);
            List<AnodeGoodsInProcessStatisticHead> statisticHeads = headMapper.selectByExample(example3);

            AnodeGoodsInProcessStatisticHead head = statisticHeads.get(0);

            AnodeCostAccountingStatisticHead statisticHead = new AnodeCostAccountingStatisticHead();
            statisticHead.setPeriodCode(head.getPeriodCode());
            statisticHead.setPeriods(head.getPeriods());
            statisticHead.setBeginTime(head.getBeginTime());
            statisticHead.setEndTime(head.getEndTime());
            statisticHead.setFlag(true);
            costHeadMapper.insertSelective(statisticHead);

            //获取配比系数
            BasicInfoAnodeCalculateCoefficient one = coefficientService.getOne();

            float x = one.getMatchingCoefficientPrecursors();
            float y = one.getMatchingCoefficientLithiumCarbonate();

            float c1 = x / (x + y);
            float c2 = y / (x + y);


            Map<Integer, Float> totalMap1 = new HashMap<>();
            totalMap1.put(1, (float) 0);
            totalMap1.put(2, (float) 0);
            totalMap1.put(3, (float) 0);
            totalMap1.put(4, (float) 0);

            Map<Integer, Float> totalMap2 = new HashMap<>();
            totalMap2.put(1, (float) 0);
            totalMap2.put(2, (float) 0);
            totalMap2.put(3, (float) 0);
            totalMap2.put(4, (float) 0);

            Map<Integer, Float> lineProductMap = new HashMap<>();

            Map<Integer, Float> qqtMap = new HashMap<>();
            Map<Integer, Float> tslMap = new HashMap<>();
            Map<Integer, Float> yhlMap = new HashMap<>();
            Map<Integer, Float> sjlMap = new HashMap<>();

            Map<Integer, Float> qqtjcMap = new HashMap<>();
            Map<Integer, Float> tsljcMap = new HashMap<>();

            for (AnodeGoodsInProcessStatisticHead record : statisticHeads) {
                Long statCode = record.getCode();
                AnodeGoodsInProcessStatisticOnlineRawMaterialExample example = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
                example.createCriteria().andStatisticCodeEqualTo(statCode);
                List<AnodeGoodsInProcessStatisticOnlineRawMaterial> rawMaterials = rawMaterialMapper.selectByExample(example);

                AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example2 = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                example2.createCriteria().andStatisticCodeEqualTo(statCode).andProcessCodeEqualTo(1);//工序为"在线原料"
                List<AnodeGoodsInProcessStatisticSingleMaterialWeights> materialWeights = weightsMapper.selectByExample(example2);

                AnodeGoodsInProcessStatisticLineProcessProductionsExample example6 = new AnodeGoodsInProcessStatisticLineProcessProductionsExample();
                example6.createCriteria().andStatisticCodeEqualTo(statCode);
                List<AnodeGoodsInProcessStatisticLineProcessProductions> productions = processProductionsMapper.selectByExample(example6);

                AnodeGoodsInProcessStatisticLineProcessProductions processProductions = productions.get(0);
                lineProductMap.put(processProductions.getLineCode(), processProductions.getProductionStorage());


                for (AnodeGoodsInProcessStatisticOnlineRawMaterial rawMaterial : rawMaterials) {
                    AnodeCostAccountingStatisticByLineOnlineRawMaterial re = new AnodeCostAccountingStatisticByLineOnlineRawMaterial();
                    re.setStatisticCode(statisticHead.getCode());
                    re.setLineCode(rawMaterial.getLineCode());
                    re.setMaterialCode(rawMaterial.getMaterialCode());
                    if (rawMaterial.getMaterialCode() == 1) {
                        qqtMap.put(rawMaterial.getLineCode(), rawMaterial.getFeedstock());
                        qqtjcMap.put(rawMaterial.getLineCode(), rawMaterial.getBalance());
                    }
                    if (rawMaterial.getMaterialCode() == 2) {
                        tslMap.put(rawMaterial.getLineCode(), rawMaterial.getFeedstock());
                        tsljcMap.put(rawMaterial.getLineCode(), rawMaterial.getBalance());
                    }
                    re.setTypeCode(rawMaterial.getTypeCode());
                    re.setFeedstock(rawMaterial.getFeedstock());
                    re.setBalance(rawMaterial.getBalance());
                    totalMap1.put(re.getMaterialCode(), totalMap1.get(re.getMaterialCode()) + re.getFeedstock());
                    totalMap2.put(re.getMaterialCode(), totalMap2.get(re.getMaterialCode()) + re.getBalance());
                    byLineOnlineRawMaterialMapper.insertSelective(re);
                }

                for (AnodeGoodsInProcessStatisticSingleMaterialWeights weight : materialWeights) {
                    AnodeCostAccountingStatisticByLineOnlineRawMaterial re = new AnodeCostAccountingStatisticByLineOnlineRawMaterial();
                    re.setStatisticCode(statisticHead.getCode());
                    re.setLineCode(weight.getLineCode());
                    re.setMaterialCode(weight.getMaterialCode());
                    if (weight.getMaterialCode() == 3) {
                        yhlMap.put(weight.getLineCode(), weight.getWeights());
                    }
                    if (weight.getMaterialCode() == 4) {
                        sjlMap.put(weight.getLineCode(), weight.getWeights());
                    }
                    re.setTypeCode(weight.getTypeCode());
                    re.setFeedstock(weight.getWeights());
                    re.setBalance((float) 0);
                    totalMap1.put(re.getMaterialCode(), totalMap1.get(re.getMaterialCode()) + re.getFeedstock());
                    totalMap2.put(re.getMaterialCode(), totalMap2.get(re.getMaterialCode()) + re.getBalance());
                    byLineOnlineRawMaterialMapper.insertSelective(re);
                }
            }
            //初始化总量
            float total1 = 0;
            float total2 = 0;
            float total3 = 0;
            float total4 = 0;
            float total5 = 0;
            float total6 = 0;
            float total7 = 0;

            float totalFeedstock = 0;
            float totalBalance = 0;


            for (int i = 1; i < 5; i++) {
                AnodeCostAccountingStatisticTotalsOnlineRawMaterial record = new AnodeCostAccountingStatisticTotalsOnlineRawMaterial();
                record.setStatisticCode(statisticHead.getCode());
                record.setMaterialCode(i);
                record.setFeedstock(totalMap1.get(i));
                record.setBalance(totalMap2.get(i));
                totalFeedstock += record.getFeedstock();
                totalBalance += record.getBalance();
                totalsOnlineRawMaterialMapper.insertSelective(record);
            }


            //产线统计明细数据表
            for (AnodeGoodsInProcessStatisticByLineDetails byLineDetails : details) {
                AnodeCostAccountingStatisticByLineDetail detail = new AnodeCostAccountingStatisticByLineDetail();
                detail.setStatisticCode(statisticHead.getCode());
                detail.setPeriods(statisticHead.getPeriods());
                detail.setLineCoed(byLineDetails.getLineCode());
                detail.setTypeCode(byLineDetails.getTypeCode());
                detail.setCurrentGoodsInProcessFirst(byLineDetails.getFirstProcess());
                total1 += detail.getCurrentGoodsInProcessFirst();
                detail.setCurrentGoodsInProcessSecond(byLineDetails.getSecondProces());
                total2 += detail.getCurrentGoodsInProcessSecond();
                detail.setLastMaterialInProcessFirst(firstMap.get(detail.getLineCoed()));
                total3 += detail.getLastMaterialInProcessFirst();
                detail.setLastGoodsInProcessSecond(secondMap.get(detail.getLineCoed()));
                total4 += detail.getLastGoodsInProcessSecond();
                detail.setProductStorage(lineProductMap.get(detail.getLineCoed()));
                total5 += detail.getProductStorage();
                Integer curLineCode = byLineDetails.getLineCode();
                float sa = (float) (qqtMap.get(curLineCode) + yhlMap.get(curLineCode) * c1 + sjlMap.get(curLineCode) * 1.0581 + detail.getLastMaterialInProcessFirst() * c1 - detail.getCurrentGoodsInProcessFirst() * c1 - qqtjcMap.get(curLineCode));
                float sb = detail.getCurrentGoodsInProcessSecond() - detail.getLastGoodsInProcessSecond();
                float ta = (float) (tslMap.get(curLineCode) + yhlMap.get(curLineCode) * c2 + sjlMap.get(curLineCode) * 0.428 + detail.getLastMaterialInProcessFirst() * c2 - detail.getCurrentGoodsInProcessFirst() * c2 - tsljcMap.get(curLineCode));
                detail.setUnitConsumptionPrecursor(sa / (sb + detail.getProductStorage()));
                total6 += detail.getUnitConsumptionPrecursor();
                detail.setUnitConsumptionNeurolithium(ta / (sb + detail.getProductStorage()));
                total7 += detail.getUnitConsumptionNeurolithium();
                byLineDetailMapper.insertSelective(detail);
            }

            //整体合计
            AnodeCostAccountingStatisticTotals totals = new AnodeCostAccountingStatisticTotals();
            totals.setStatisticCode(statisticHead.getCode());
            totals.setPeriods(periods);
            totals.setMaterialRequisitions(totalFeedstock);
            totals.setMaterialBalance(totalBalance);
            totals.setCurrentGoodsInProcessFirst(total1);
            totals.setCurrentGoodsInProcessSecond(total2);
            totals.setLastMaterialInProcessFirst(total3);
            totals.setLastGoodsInProcessSecond(total4);
            totals.setProductStorage(total5);
            totals.setUnitConsumptionPrecursor(total6);
            totals.setUnitConsumptionNeurolithium(total7);
            totalsMapper.insertSelective(totals);
        }

        List<BasicInfoAnodeProductionLine> all = anodeProductionLineService.getAll();
        Map<Integer, String> lineMap = new HashMap<>();
        all.forEach(item -> lineMap.put(item.getCode(), item.getName()));

        List<BasicInfoAnodePeriod> all1 = anodeStatPeriodService.getAll();
        Map<Integer, String> periodMap = new HashMap<>();
        all1.forEach(item -> periodMap.put(item.getCode(), item.getName()));

        headExample.clear();
        headExample.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods).andFlagEqualTo(true);
        heads = costHeadMapper.selectByExample(headExample);
        AnodeCostAccountingStatisticHead statisticHead = heads.get(0);

        Long headCode = statisticHead.getCode();

        if (lineCode == 0) {
            AnodeCostAccountingStatisticByLineDetailExample byLineDetailExample = new AnodeCostAccountingStatisticByLineDetailExample();
            byLineDetailExample.createCriteria().andStatisticCodeEqualTo(headCode).andPeriodsEqualTo(periods);
            List<AnodeCostAccountingStatisticByLineDetail> byLineDetails = byLineDetailMapper.selectByExample(byLineDetailExample);

            AnodeCostAccountingStatisticByLineOnlineRawMaterialExample byLineOnlineRawMaterialExample = new AnodeCostAccountingStatisticByLineOnlineRawMaterialExample();
            byLineOnlineRawMaterialExample.createCriteria().andStatisticCodeEqualTo(headCode);
            List<AnodeCostAccountingStatisticByLineOnlineRawMaterial> byLineOnlineRawMaterials = byLineOnlineRawMaterialMapper.selectByExample(byLineOnlineRawMaterialExample);

            generateMapFormat(res, byLineDetails, byLineOnlineRawMaterials, statisticHead, lineMap, periodMap);
        }

        if (lineCode == 1) {
            AnodeCostAccountingStatisticTotalsExample totalsExample = new AnodeCostAccountingStatisticTotalsExample();
            totalsExample.createCriteria().andStatisticCodeEqualTo(headCode).andPeriodsEqualTo(periods);
            List<AnodeCostAccountingStatisticTotals> totals = totalsMapper.selectByExample(totalsExample);

            AnodeCostAccountingStatisticTotalsOnlineRawMaterialExample totalsOnlineRawMaterialExample = new AnodeCostAccountingStatisticTotalsOnlineRawMaterialExample();
            totalsOnlineRawMaterialExample.createCriteria().andStatisticCodeEqualTo(headCode);
            List<AnodeCostAccountingStatisticTotalsOnlineRawMaterial> totalsOnlineRawMaterials = totalsOnlineRawMaterialMapper.selectByExample(totalsOnlineRawMaterialExample);

            generateMapFormat2(res, totals, totalsOnlineRawMaterials, statisticHead, periodMap);
        }
        return res;
    }

    private void generateMapFormat2(List<Map<String, Object>> res, List<AnodeCostAccountingStatisticTotals> totals, List<AnodeCostAccountingStatisticTotalsOnlineRawMaterial> totalsOnlineRawMaterials, AnodeCostAccountingStatisticHead statisticHead, Map<Integer, String> periodMap) {

        for (AnodeCostAccountingStatisticTotals statisticTotals : totals) {
            Map<String, Object> map = new HashMap<>();
            map.put("costObject", "车间");
            map.put("periodName", periodMap.get(statisticHead.getPeriodCode()));
            map.put("periods", statisticHead.getPeriods());
            map.put("startTime", ComUtil.dateToString(statisticHead.getBeginTime(), "yyyy-MM-dd HH:mm"));
            map.put("endTime", ComUtil.dateToString(statisticHead.getEndTime(), "yyyy-MM-dd HH:mm"));

            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();

            for (AnodeCostAccountingStatisticTotalsOnlineRawMaterial onlineRawMaterial : totalsOnlineRawMaterials) {
                if (onlineRawMaterial.getMaterialCode() == 1) {
                    stringBuffer.append("前驱体(kg):" + onlineRawMaterial.getFeedstock().toString() + " ");
                    stringBuffer2.append("前驱体(kg):" + onlineRawMaterial.getBalance().toString() + " ");
                }
                if (onlineRawMaterial.getMaterialCode() == 2) {
                    stringBuffer.append("碳酸锂(kg):" + onlineRawMaterial.getFeedstock().toString() + " ");
                    stringBuffer2.append("碳酸锂(kg):" + onlineRawMaterial.getBalance().toString() + " ");
                }
                if (onlineRawMaterial.getMaterialCode() == 3) {
                    stringBuffer.append("预混料(kg):" + onlineRawMaterial.getFeedstock().toString() + " ");
//                        stringBuffer.append("预混料(kg):" + onlineRawMaterial.getBalance().toString());
                }
                if (onlineRawMaterial.getMaterialCode() == 4) {
                    stringBuffer.append("烧结料(kg):" + onlineRawMaterial.getFeedstock().toString() + " ");
//                        stringBuffer.append("烧结料(kg):" + onlineRawMaterial.getBalance().toString());
                }
            }

            map.put("rawMaterialFeedStock", stringBuffer);
            map.put("rawMaterialBalance", stringBuffer2);

            map.put("lastGoodsInProcessFirst", statisticTotals.getLastMaterialInProcessFirst());
            map.put("currentGoodsInProcessFirst", statisticTotals.getCurrentGoodsInProcessFirst());
            map.put("lastGoodsInProcessSecond", statisticTotals.getLastGoodsInProcessSecond());
            map.put("currentGoodsInProcessSecond", statisticTotals.getCurrentGoodsInProcessSecond());

            map.put("productStorage", statisticTotals.getProductStorage());
            map.put("unitConsumption", "前驱体(kg):" + statisticTotals.getUnitConsumptionPrecursor().toString() + " " + "碳酸锂(kg):" + statisticTotals.getUnitConsumptionNeurolithium().toString());
            res.add(map);
        }
    }

    private void generateMapFormat(List<Map<String, Object>> res, List<AnodeCostAccountingStatisticByLineDetail> byLineDetails, List<AnodeCostAccountingStatisticByLineOnlineRawMaterial> byLineOnlineRawMaterials, AnodeCostAccountingStatisticHead statisticHead, Map<Integer, String> lineMap, Map<Integer, String> periodMap) {

        for (AnodeCostAccountingStatisticByLineDetail detail : byLineDetails) {
            Map<String, Object> map = new HashMap<>();
            map.put("costObject", lineMap.get(detail.getLineCoed()));
            map.put("periodName", periodMap.get(statisticHead.getPeriodCode()));
            map.put("periods", statisticHead.getPeriods());
            map.put("startTime", ComUtil.dateToString(statisticHead.getBeginTime(), "yyyy-MM-dd HH:mm"));
            map.put("endTime", ComUtil.dateToString(statisticHead.getEndTime(), "yyyy-MM-dd HH:mm"));

            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();

            for (AnodeCostAccountingStatisticByLineOnlineRawMaterial onlineRawMaterial : byLineOnlineRawMaterials) {
                if (onlineRawMaterial.getLineCode() == detail.getLineCoed()) {

                    if (onlineRawMaterial.getMaterialCode() == 1) {
                        stringBuffer.append("前驱体(kg):" + onlineRawMaterial.getFeedstock().toString() + " ");
                        stringBuffer2.append("前驱体(kg):" + onlineRawMaterial.getBalance().toString() + " ");
                    }
                    if (onlineRawMaterial.getMaterialCode() == 2) {
                        stringBuffer.append("碳酸锂(kg):" + onlineRawMaterial.getFeedstock().toString() + " ");
                        stringBuffer2.append("碳酸锂(kg):" + onlineRawMaterial.getBalance().toString() + " ");
                    }
                    if (onlineRawMaterial.getMaterialCode() == 3) {
                        stringBuffer.append("预混料(kg):" + onlineRawMaterial.getFeedstock().toString() + " ");
//                        stringBuffer.append("预混料(kg):" + onlineRawMaterial.getBalance().toString());
                    }
                    if (onlineRawMaterial.getMaterialCode() == 4) {
                        stringBuffer.append("烧结料(kg):" + onlineRawMaterial.getFeedstock().toString() + " ");
//                        stringBuffer.append("烧结料(kg):" + onlineRawMaterial.getBalance().toString());
                    }
                }
            }
            map.put("rawMaterialFeedStock", stringBuffer);
            map.put("rawMaterialBalance", stringBuffer2);

            map.put("lastGoodsInProcessFirst", detail.getLastMaterialInProcessFirst());
            map.put("currentGoodsInProcessFirst", detail.getCurrentGoodsInProcessFirst());
            map.put("lastGoodsInProcessSecond", detail.getLastGoodsInProcessSecond());
            map.put("currentGoodsInProcessSecond", detail.getCurrentGoodsInProcessSecond());

            map.put("productStorage", detail.getProductStorage());
            map.put("unitConsumption", "前驱体(kg):" + detail.getUnitConsumptionPrecursor().toString() + " " + "碳酸锂(kg):" + detail.getUnitConsumptionNeurolithium().toString());
            res.add(map);
        }

    }


}
