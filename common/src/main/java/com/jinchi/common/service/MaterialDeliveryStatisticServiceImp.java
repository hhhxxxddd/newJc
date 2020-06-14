package com.jinchi.common.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.service.feignservice.IRepoOutService;
import com.jinchi.common.utils.ComUtil;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-11-04 12:28
 * @description:
 **/
@Service
public class MaterialDeliveryStatisticServiceImp implements MaterialDeliveryStatisticService {

    @Autowired
    MaterialDeliveryStatisticHeadMapper statisticHeadMapper;
    @Autowired
    MaterialDeliveryStatisticDataListMapper dataListMapper;
    @Autowired
    BasicInfoPrecursorPeriodMapper periodMapper;
    @Autowired
    GoodsInProcessStatisticHeadMapper goodsInProcessStatisticHeadMapper;
    @Autowired
    ProductStorageStatisticHeadMapper productStorageStatisticHeadMapper;
    @Autowired
    BasicInfoRawmaterialMapper basicInfoRawmaterialMapper;
    @Autowired
    MaterialDeliveryStatisticSaltMixtureLiquorMapper saltMixtureLiquorMapper;
    @Autowired
    MaterialDeliveryStatisticCrystalsMapper crystalsMapper;
    @Autowired
    MaterialDeliveryStatisticSingleCrystalLiquorMapper singleCrystalLiquorMapper;
    @Autowired
    MaterialDeliveryStatisticSulfateConcentrationMapper sulfateConcentrationMapper;
    @Autowired
    MaterialDeliveryStatisticNickelSulfateMapper nickelSulfateMapper;
    @Autowired
    MaterialDeliveryStatisticCobaltSulfateMapper cobaltSulfateMapper;
    @Autowired
    MaterialDeliveryStatisticManganeseSulfateMapper manganeseSulfateMapper;
    @Autowired
    IRepoOutService iRepoOutService;
    @Autowired
    BasicInfoRawmaterialLineWeightMapper lineWeightMapper;
    @Autowired
    MaterialDeliveryStatisticByLineDetailMapper lineDetailMapper;
    @Autowired
    BasicInfoPrecursorProductionLineMapper lineMapper;
    @Autowired
    MaterialDeliveryStatisticByLineTotalsMapper lineTotalsMapper;
    @Autowired
    AuxiliaryMaterialsStatisticHeadMapper auxiliaryHeadMapper;
    @Autowired
    PrecursorHeadTableOperationService operationService;

    @Override
    public List getPeriod(Integer periodCode) {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = new HashMap<>();
        MaterialDeliveryStatisticHeadExample example = new MaterialDeliveryStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodCode).andFlagEqualTo((byte) 1);
        List<MaterialDeliveryStatisticHead> heads = statisticHeadMapper.selectByExample(example);
        MaterialDeliveryStatisticHead head = heads.size() == 0 ? null : heads.get(heads.size() - 1);
        if (head == null) {
            map.put("periods", 1);
            map.put("entDate", "");
            list.add(map);
        } else {
            map.put("periods", head.getLineName() + 1);
            map.put("entDate", ComUtil.dateToString(head.getEndTime(), "yyyy-MM-dd"));
            list.add(map);
        }

        return list;
    }

//    @Override
//    public String getEndTime(Integer periodCode, String startTime) {
//        int length = periodMapper.selectByPrimaryKey(periodCode).getLength();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date st = null;
//        try {
//            st = sdf.parse(startTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long k = 3600;
//        Date et = new Date(st.getTime() + length * 24 * k * 1000);
//        return sdf.format(et);
//    }

    @Override
    public Object add(MaterialDeliveryStatisticHead head) {


        //查询在制品统计，成品入库统计,辅料统计的统计头表，比较是否有周期类型相同且期数相同的数据
        GoodsInProcessStatisticHeadExample example1 = new GoodsInProcessStatisticHeadExample();
        example1.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getLineName()).andFlagEqualTo((byte) 1);
        List<GoodsInProcessStatisticHead> list1 = goodsInProcessStatisticHeadMapper.selectByExample(example1);
        example1.clear();
        example1.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getLineName()).andFlagEqualTo((byte) 0);
        List<GoodsInProcessStatisticHead> list5 = goodsInProcessStatisticHeadMapper.selectByExample(example1);

        ProductStorageStatisticHeadExample example2 = new ProductStorageStatisticHeadExample();
        example2.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getLineName()).andFlagEqualTo((byte) 1);
        List<ProductStorageStatisticHead> list2 = productStorageStatisticHeadMapper.selectByExample(example2);
        example2.clear();
        example2.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getLineName()).andFlagEqualTo((byte) 0);
        List<ProductStorageStatisticHead> list6 = productStorageStatisticHeadMapper.selectByExample(example2);

        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andPeriodsEqualTo(head.getLineName()).andFlagEqualTo(true);
        List<AuxiliaryMaterialsStatisticHead> list = auxiliaryHeadMapper.selectByExample(example);
        example.clear();
        example.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andPeriodsEqualTo(head.getLineName()).andFlagEqualTo(false);
        List<AuxiliaryMaterialsStatisticHead> list7 = auxiliaryHeadMapper.selectByExample(example);


        MaterialDeliveryStatisticHeadExample example3 = new MaterialDeliveryStatisticHeadExample();
        example3.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getLineName());
        List<MaterialDeliveryStatisticHead> list3 = statisticHeadMapper.selectByExample(example3);


        if (list3.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", -1);
            map.put("message", "本期数据已经存在");
            return map;
        }

        String format = "yyyy-MM-dd HH:mm:ss";

        if (list1.size() == 0 && list2.size() == 0 && list.size() == 0) {
            //如果不存在已统计的记录
            //如果存在待提交的记录
            if (list5.size() != 0 || list6.size() != 0 || list7.size() != 0) {
                for (GoodsInProcessStatisticHead head1 : list5) {
                    if (!head1.getStartTime().equals(head.getStartTime())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("code", -2);
                        map.put("message", "第" + head1.getLineName() + "期在制品管理存在待提交记录，开始时间为: " + ComUtil.dateToString(head1.getStartTime(), format) + "本模块开始时间应与其一致");
                        return map;
                    }
                }
                for (ProductStorageStatisticHead head2 : list6) {
                    if (!head2.getStartTime().equals(head.getStartTime())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("code", -2);
                        map.put("message", "第" + head2.getLineName() + "期成品入库存在待提交记录，开始时间为: " + ComUtil.dateToString(head2.getStartTime(), format) + "本模块开始时间应与其一致");
                        return map;
                    }
                }
                for (AuxiliaryMaterialsStatisticHead head3 : list7) {
                    if (!head3.getStartTime().equals(head.getStartTime())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("code", -2);
                        map.put("message", "第" + head3.getPeriods() + "期辅料统计存在待提交记录，开始时间为: " + ComUtil.dateToString(head3.getStartTime(), format) + "本模块开始时间应与其一致");
                        return map;
                    }
                }
            }
        } else {
            //如果存在已统计的记录，但是时间信息不一致，就返回相应的提示信息
            for (GoodsInProcessStatisticHead head1 : list1) {
                if (!head1.getStartTime().equals(head.getStartTime()) || !head1.getEndTime().equals(head.getEndTime())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", -2);
                    map.put("message", "第" + head1.getLineName() + "期在制品管理已统计，开始时间为: " + ComUtil.dateToString(head1.getStartTime(), format) + "结束时间为: " + ComUtil.dateToString(head1.getEndTime(), format) + "本模块开始时间、结束时间应与其一致");
                    return map;
                }
            }
            for (ProductStorageStatisticHead head2 : list2) {
                if (!head2.getStartTime().equals(head.getStartTime()) || !head2.getEndTime().equals(head.getEndTime())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", -2);
                    map.put("message", "第" + head2.getLineName() + "期成品入库已统计，开始时间为: " + ComUtil.dateToString(head2.getStartTime(), format) + "结束时间为: " + ComUtil.dateToString(head2.getEndTime(), format) + "本模块开始时间、结束时间应与其一致");
                    return map;
                }
            }
            for (AuxiliaryMaterialsStatisticHead head3 : list) {
                if (!head3.getStartTime().equals(head.getStartTime()) || !head3.getEndTime().equals(head.getEndTime())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", -2);
                    map.put("message", "第" + head3.getPeriods() + "期辅料统计已统计，开始时间为: " + ComUtil.dateToString(head3.getStartTime(), format) + "结束时间为: " + ComUtil.dateToString(head3.getEndTime(), format) + "本模块开始时间、结束时间应与其一致");
                    return map;
                }
            }
        }

        //没有上述异常，就直接新增
        statisticHeadMapper.insertSelective(head);
        Map<String, Object> map = new HashMap<>();
        map.put("code", head.getCode());
        map.put("message", "success");
        return map;
    }

    @Override
    public MaterialDeliveryStatisticHead update(MaterialDeliveryStatisticHead head) {
        statisticHeadMapper.updateByPrimaryKeySelective(head);
        return head;
    }

    @Override
    public List getStockOutData(String startTime, String endTime) {
        BasicInfoRawmaterialExample example = new BasicInfoRawmaterialExample();
        example.createCriteria().andDataTypeEqualTo((byte) 0);
        List<BasicInfoRawmaterial> infos = basicInfoRawmaterialMapper.selectByExample(example);

        List<String> names = new ArrayList<>();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        for (BasicInfoRawmaterial info : infos) {
            //获取所有原料名称
            names.add(info.getMaterialName());
            map.add(info.getMaterialName(), info.getCode());
            map.add(info.getMaterialName(), info.getTypesCode());
        }

        //查询stock_out_record数据表
        //获取 已出库，完成时间在[开始时间，结束时间)的数据

        //根据刚才拿到的原料名称 在material_info里面根据名称 找到id
        //根据id 在material_stock表里找到物料编码

        //封装一个DTO 原料id 原料名称 物料编码 出库时间 重量 叫料点
        //
        List<StockOutDTO> stockOutDTOS = new ArrayList<>();

        String result = iRepoOutService.matOut(startTime, endTime, names);
        System.out.println(result);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("code").equals("000000")) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONArray stockOutRecord = jsonArray.getJSONObject(i).getJSONArray("stockOutRecord");
                JSONArray materialStock = jsonArray.getJSONObject(i).getJSONArray("materialStock");
                for (int j = 0; j < stockOutRecord.size(); j++) {
                    StockOutDTO stockOutDTO = new StockOutDTO();
                    String completionTime = stockOutRecord.getJSONObject(j).getString("completionTime");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    try {
                        stockOutDTO.setOutStockTime(sdf.parse(completionTime));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    stockOutDTO.setMaterialBatch(materialStock.getJSONObject(j).getString("materialCode"));
                    stockOutDTO.setWeight(materialStock.getJSONObject(j).getString("weight"));
                    stockOutDTO.setMaterialName(materialStock.getJSONObject(j).getString("materialName"));
                    stockOutDTO.setMaterialCode((Integer) map.get(stockOutDTO.getMaterialName()).get(0));
                    stockOutDTO.setMaterialTypeCode((Integer) map.get(stockOutDTO.getMaterialName()).get(1));
                    stockOutDTOS.add(stockOutDTO);
                }
            }
        }
        return stockOutDTOS;
    }

    @Override
    public MaterialDeliveryStatisticSulfateConcentration getConcentrations(Integer periodCode) {
        MaterialDeliveryStatisticHeadExample example = new MaterialDeliveryStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodCode).andFlagEqualTo((byte) 1);
        List<MaterialDeliveryStatisticHead> heads = statisticHeadMapper.selectByExample(example);
        MaterialDeliveryStatisticHead head = heads.size() == 0 ? null : heads.get(heads.size() - 1);
//        Assert.notNull(head, "不存在统计头表数据");

        if (head == null) {
            MaterialDeliveryStatisticSulfateConcentration record = new MaterialDeliveryStatisticSulfateConcentration();
            record.setMnConcentration((float) 0);
            record.setCoConcentration((float) 0);
            record.setNiConcentration((float) 0);
            return record;
        } else {
            long code = head.getCode();

            MaterialDeliveryStatisticSulfateConcentrationExample example1 = new MaterialDeliveryStatisticSulfateConcentrationExample();
            example1.createCriteria().andStatisticCodeEqualTo(code);
            List<MaterialDeliveryStatisticSulfateConcentration> list = sulfateConcentrationMapper.selectByExample(example1);
            MaterialDeliveryStatisticSulfateConcentration record = list.size() == 0 ? null : list.get(list.size() - 1);
            if (record == null) {
                record.setMnConcentration((float) 0);
                record.setCoConcentration((float) 0);
                record.setNiConcentration((float) 0);
            }
            return record;
        }
    }

    @Override
    public List getSupplementary(Integer periodCode) {
        MaterialDeliveryStatisticHeadExample example = new MaterialDeliveryStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodCode).andFlagEqualTo((byte) 1);
        List<MaterialDeliveryStatisticHead> heads = statisticHeadMapper.selectByExample(example);
        MaterialDeliveryStatisticHead head = heads.size() == 0 ? null : heads.get(heads.size() - 1);

        List<CrystalsDTO> crystalsDTOS = new ArrayList<>();
        List<SingleCrystalLiquorDTO> singleCrystalLiquorDTOS = new ArrayList<>();
        List<SaltMixtureLiquorDTO> saltMixtureLiquorDTOS = new ArrayList<>();

        //如果不存在上期统计数据 就返回原材料基础数据里的补料数据
        if (head == null) {
            System.out.println("==============  不存在上期数据 ");
            System.out.println("==============  返回基础数据里的补料数据 ");
            //原料数据表中的晶体数据
            BasicInfoRawmaterialExample example5 = new BasicInfoRawmaterialExample();
            example5.createCriteria().andDataTypeEqualTo((byte) 1).andTypesCodeEqualTo(5);
            List<BasicInfoRawmaterial> l1 = basicInfoRawmaterialMapper.selectByExample(example5);
            for (BasicInfoRawmaterial rawmaterial : l1) {
                CrystalsDTO crystalsDTO = new CrystalsDTO();
                crystalsDTO.setMaterialCode(rawmaterial.getCode());
                crystalsDTO.setMaterialTypeCode(rawmaterial.getTypesCode());
                crystalsDTO.setMaterialName(rawmaterial.getMaterialName());
                crystalsDTO.setWeight((float) 0);
                crystalsDTO.setConcentration((float) 0);
                crystalsDTO.setNiFlag(rawmaterial.getNiFlag());
                crystalsDTO.setCoFlag(rawmaterial.getCoFlag());
                crystalsDTO.setMnFlag(rawmaterial.getMnFlag());
                crystalsDTOS.add(crystalsDTO);
            }

            //原料数据表中的混合盐溶液数据
            BasicInfoRawmaterialExample example6 = new BasicInfoRawmaterialExample();
            example6.createCriteria().andDataTypeEqualTo((byte) 1).andTypesCodeEqualTo(4);
            List<BasicInfoRawmaterial> l2 = basicInfoRawmaterialMapper.selectByExample(example6);

            for (BasicInfoRawmaterial rawmaterial : l2) {
                SaltMixtureLiquorDTO saltMixtureLiquorDTO = new SaltMixtureLiquorDTO();
                saltMixtureLiquorDTO.setMaterialCode(rawmaterial.getCode());
                saltMixtureLiquorDTO.setMaterialTypeCode(rawmaterial.getTypesCode());
                saltMixtureLiquorDTO.setMaterialName(rawmaterial.getMaterialName());
                saltMixtureLiquorDTO.setWeights((float) 0);
                saltMixtureLiquorDTO.setDensity((float) 0);
                saltMixtureLiquorDTO.setNiConcentration((float) 0);
                saltMixtureLiquorDTO.setCoConcentration((float) 0);
                saltMixtureLiquorDTO.setMnConcentration((float) 0);
                saltMixtureLiquorDTOS.add(saltMixtureLiquorDTO);
            }

            //原料数据表中的单晶体溶液数据
            BasicInfoRawmaterialExample example7 = new BasicInfoRawmaterialExample();
            example7.createCriteria().andDataTypeEqualTo((byte) 1).andTypesCodeEqualTo(6);
            List<BasicInfoRawmaterial> l3 = basicInfoRawmaterialMapper.selectByExample(example7);

            for (BasicInfoRawmaterial rawmaterial : l3) {
                SingleCrystalLiquorDTO singleCrystalLiquorDTO = new SingleCrystalLiquorDTO();
                singleCrystalLiquorDTO.setMaterialCode(rawmaterial.getCode());
                singleCrystalLiquorDTO.setMaterialName(rawmaterial.getMaterialName());
                singleCrystalLiquorDTO.setMaterialTypeCode(rawmaterial.getTypesCode());
                singleCrystalLiquorDTO.setWeights((float) 0);
                singleCrystalLiquorDTO.setDensity((float) 0);
                singleCrystalLiquorDTO.setConcentration((float) 0);
                singleCrystalLiquorDTO.setNiFlag(rawmaterial.getNiFlag());
                singleCrystalLiquorDTO.setMnFlag(rawmaterial.getMnFlag());
                singleCrystalLiquorDTO.setCoFlag(rawmaterial.getCoFlag());
                singleCrystalLiquorDTOS.add(singleCrystalLiquorDTO);
            }
        } else {
            //获取统计头表code
            long statisticCode = head.getCode();

            //原料数据表中的晶体数据
            BasicInfoRawmaterialExample example5 = new BasicInfoRawmaterialExample();
            example5.createCriteria().andDataTypeEqualTo((byte) 1).andTypesCodeEqualTo(5);
            List<BasicInfoRawmaterial> l1 = basicInfoRawmaterialMapper.selectByExample(example5);

            //遍历所有晶体数据
            for (BasicInfoRawmaterial rawmaterial : l1) {
                int materialCode = rawmaterial.getCode();
                MaterialDeliveryStatisticCrystalsExample example2 = new MaterialDeliveryStatisticCrystalsExample();
                example2.createCriteria().andStatisticCodeEqualTo(statisticCode).andMaterialCodeEqualTo(materialCode).andMaterialTypeCodeEqualTo(5);
                List<MaterialDeliveryStatisticCrystals> list2 = crystalsMapper.selectByExample(example2);
                MaterialDeliveryStatisticCrystals r2 = list2.size() == 0 ? null : list2.get(0);
                CrystalsDTO crystalsDTO = new CrystalsDTO();
                crystalsDTO.setMaterialCode(materialCode);
                crystalsDTO.setMaterialTypeCode(rawmaterial.getTypesCode());
                crystalsDTO.setMaterialName(rawmaterial.getMaterialName());
                crystalsDTO.setWeight(r2 == null ? (float) 0 : r2.getWeights());
                crystalsDTO.setConcentration(r2 == null ? (float) 0 : r2.getCoConcentration() + r2.getNiConcentration() + r2.getMnConcentration());
                crystalsDTO.setNiFlag(rawmaterial.getNiFlag());
                crystalsDTO.setCoFlag(rawmaterial.getCoFlag());
                crystalsDTO.setMnFlag(rawmaterial.getMnFlag());
                crystalsDTOS.add(crystalsDTO);
            }

            //原料数据表中的混合盐溶液数据
            BasicInfoRawmaterialExample example6 = new BasicInfoRawmaterialExample();
            example6.createCriteria().andDataTypeEqualTo((byte) 1).andTypesCodeEqualTo(4);
            List<BasicInfoRawmaterial> l2 = basicInfoRawmaterialMapper.selectByExample(example6);

            for (BasicInfoRawmaterial rawmaterial : l2) {
                int materialCode = rawmaterial.getCode();
                MaterialDeliveryStatisticSaltMixtureLiquorExample example1 = new MaterialDeliveryStatisticSaltMixtureLiquorExample();
                example1.createCriteria().andStatisticCodeEqualTo(statisticCode).andMaterialCodeEqualTo(materialCode).andMaterialTypeCodeEqualTo(4);
                List<MaterialDeliveryStatisticSaltMixtureLiquor> list1 = saltMixtureLiquorMapper.selectByExample(example1);
                MaterialDeliveryStatisticSaltMixtureLiquor r1 = list1.size() == 0 ? null : list1.get(0);

                SaltMixtureLiquorDTO saltMixtureLiquorDTO = new SaltMixtureLiquorDTO();
                saltMixtureLiquorDTO.setMaterialCode(materialCode);
                saltMixtureLiquorDTO.setMaterialTypeCode(rawmaterial.getTypesCode());
                saltMixtureLiquorDTO.setMaterialName(rawmaterial.getMaterialName());
                saltMixtureLiquorDTO.setWeights(r1 == null ? (float) 0 : r1.getWeights());
                saltMixtureLiquorDTO.setDensity(r1 == null ? (float) 0 : r1.getDensity());
                saltMixtureLiquorDTO.setNiConcentration(r1 == null ? (float) 0 : r1.getNiConcentration());
                saltMixtureLiquorDTO.setCoConcentration(r1 == null ? (float) 0 : r1.getCoConcentration());
                saltMixtureLiquorDTO.setMnConcentration(r1 == null ? (float) 0 : r1.getMnConcentration());
                saltMixtureLiquorDTOS.add(saltMixtureLiquorDTO);
            }

            //原料数据表中的单晶体溶液数据
            BasicInfoRawmaterialExample example7 = new BasicInfoRawmaterialExample();
            example7.createCriteria().andDataTypeEqualTo((byte) 1).andTypesCodeEqualTo(6);
            List<BasicInfoRawmaterial> l3 = basicInfoRawmaterialMapper.selectByExample(example7);


            for (BasicInfoRawmaterial rawmaterial : l3) {
                int materialCode = rawmaterial.getCode();
                MaterialDeliveryStatisticSingleCrystalLiquorExample example3 = new MaterialDeliveryStatisticSingleCrystalLiquorExample();
                example3.createCriteria().andStatisticCodeEqualTo(statisticCode).andMaterialCodeEqualTo(materialCode).andMaterialTypeCodeEqualTo(6);
                List<MaterialDeliveryStatisticSingleCrystalLiquor> list3 = singleCrystalLiquorMapper.selectByExample(example3);
                MaterialDeliveryStatisticSingleCrystalLiquor r3 = list3.size() == 0 ? null : list3.get(0);

                SingleCrystalLiquorDTO singleCrystalLiquorDTO = new SingleCrystalLiquorDTO();
                singleCrystalLiquorDTO.setMaterialCode(materialCode);
                singleCrystalLiquorDTO.setMaterialName(rawmaterial.getMaterialName());
                singleCrystalLiquorDTO.setMaterialTypeCode(rawmaterial.getTypesCode());
                singleCrystalLiquorDTO.setWeights(r3 == null ? (float) 0 : r3.getWeights());
                singleCrystalLiquorDTO.setDensity(r3 == null ? (float) 0 : r3.getDensity());
                singleCrystalLiquorDTO.setConcentration(r3 == null ? (float) 0 : r3.getNiConcentration() + r3.getCoConcentration() + r3.getMnConcentration());
                singleCrystalLiquorDTO.setNiFlag(rawmaterial.getNiFlag());
                singleCrystalLiquorDTO.setMnFlag(rawmaterial.getMnFlag());
                singleCrystalLiquorDTO.setCoFlag(rawmaterial.getCoFlag());
                singleCrystalLiquorDTOS.add(singleCrystalLiquorDTO);
            }
        }


        List<List> list = new ArrayList();
        list.add(saltMixtureLiquorDTOS);
        list.add(crystalsDTOS);
        list.add(singleCrystalLiquorDTOS);
        return list;
    }

    @Override
    public Page queryUncommitted(String startTime, String endTime, Integer periodCode, Integer page, Integer size) {

        String sql;
        if ((startTime == null && endTime == null) || (startTime.equals("") && endTime.equals(""))) {
            if (periodCode == null) {
                sql = "SELECT a.*,b.`name` FROM `material_delivery_statistic_head` as a,`basic_info_precursor_period` as b WHERE a.period_code = b.`code` AND a.flag = 0 ORDER BY a.start_time DESC";
            } else {
                sql = "SELECT a.*,b.`name` FROM `material_delivery_statistic_head` as a,`basic_info_precursor_period` as b WHERE a.period_code = '" + periodCode + "' AND a.period_code = b.`code` and a.flag = 0 ORDER BY a.start_time DESC";
            }
        } else {
            sql = "SELECT a.*,b.`name` FROM `material_delivery_statistic_head` as a,`basic_info_precursor_period` as b WHERE a.period_code = '" + periodCode + "' AND a.period_code = b.`code` AND a.start_time >= '" + startTime + "'and a.start_time < '" + endTime + "' and a.flag = 0 ORDER BY a.start_time DESC";
        }
        return new Page(statisticHeadMapper.selectByConditions(sql), page, size);
    }

    @Override
    public Page queryStatistics(String startTime, String endTime, Integer periodCode, Integer page, Integer size) {
        //已统计 默认显示最近一期 类型为周期基础数据第一条记录的类型
        BasicInfoPrecursorPeriodExample periodExample = new BasicInfoPrecursorPeriodExample();
        periodExample.createCriteria();
        List<BasicInfoPrecursorPeriod> records = periodMapper.selectByExample(periodExample);
        Assert.notEmpty(records, "请先维护周期类型基础数据");
        int periodCodeDefault = records.get(0).getCode();

        String sql;

        if ((startTime == null && endTime == null) || (startTime.equals("") && endTime.equals(""))) {
            if (periodCode == null) {
                sql = "SELECT a.*,b.`name` FROM `material_delivery_statistic_head` as a,`basic_info_precursor_period` as b WHERE a.period_code = '" + periodCodeDefault + "' AND a.period_code = b.`code` AND a.flag = 1 ORDER BY a.start_time DESC";
            } else {
                sql = "SELECT a.*,b.`name` FROM `material_delivery_statistic_head` as a,`basic_info_precursor_period` as b WHERE a.period_code = '" + periodCode + "' AND a.period_code = b.`code` AND a.flag = 1 ORDER BY a.start_time DESC";
            }
        } else {
            sql = "SELECT a.*,b.`name` FROM `material_delivery_statistic_head` as a,`basic_info_precursor_period` as b WHERE a.period_code = '" + periodCode + "' AND a.period_code = b.`code` AND a.start_time >= '" + startTime + "'and a.start_time < '" + endTime + "' and a.flag = 1 ORDER BY a.start_time DESC";
        }

        //获取最近的一期的统计头表记录
        List<MaterialDeliveryStatisticHeadDTO> materialDeliveryStatisticHeadDTOS = statisticHeadMapper.selectByConditions(sql);
        if (materialDeliveryStatisticHeadDTOS.size() == 0) {
            return new Page(new ArrayList(), page, size);
        }
        List resultList = new ArrayList();
        for (MaterialDeliveryStatisticHeadDTO headDTO : materialDeliveryStatisticHeadDTOS) {
            long statisticCode = headDTO.getCode();
            MaterialDeliveryStatisticDataListExample example = new MaterialDeliveryStatisticDataListExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticCode);
            List<MaterialDeliveryStatisticDataList> dataLists = dataListMapper.selectByExample(example);

            for (MaterialDeliveryStatisticDataList dataList : dataLists) {
                MaterialDeliveryStatisticsDTO statisticsDTO = new MaterialDeliveryStatisticsDTO();
                statisticsDTO.setHeadDTO(headDTO);
                statisticsDTO.setData(dataList);
                resultList.add(statisticsDTO);
            }
        }
        return new Page(resultList, page, size);
    }

    @Override
    public MaterialDeliveryCommitDTO getDetailByCode(Long statisticCode) {
        MaterialDeliveryCommitDTO ans = new MaterialDeliveryCommitDTO();

        UncommittedDetailDTO detailDTO = new UncommittedDetailDTO();
        detailDTO.setStatisticHead(statisticHeadMapper.selectByPrimaryKey(statisticCode));
        MaterialDeliveryStatisticHead head = detailDTO.getStatisticHead();
        ans.setHead(head);
        ans.setPeriodName(periodMapper.selectByPrimaryKey(detailDTO.getStatisticHead().getPeriodCode()).getName());

        MaterialDeliveryStatisticSaltMixtureLiquorExample example = new MaterialDeliveryStatisticSaltMixtureLiquorExample();
        example.createCriteria().andStatisticCodeEqualTo(statisticCode);
        detailDTO.setSaltMixtureLiquors(saltMixtureLiquorMapper.selectByExample(example));
        List<SaltMixtureLiquorDTO> liquorDTOS = new ArrayList<>();
        for (int i = 0; i < detailDTO.getSaltMixtureLiquors().size(); i++) {
            SaltMixtureLiquorDTO liquorDTO = new SaltMixtureLiquorDTO();
            MaterialDeliveryStatisticSaltMixtureLiquor liquor = detailDTO.getSaltMixtureLiquors().get(i);
            liquorDTO.setCoConcentration(liquor.getCoConcentration());
            liquorDTO.setDensity(liquor.getDensity());
            liquorDTO.setMaterialTypeCode(liquor.getMaterialTypeCode());
            liquorDTO.setMaterialCode(liquor.getMaterialCode());
            liquorDTO.setMaterialName(liquor.getMaterialName());
            liquorDTO.setMnConcentration(liquor.getMnConcentration());
            liquorDTO.setNiConcentration(liquor.getNiConcentration());
            liquorDTO.setWeights(liquor.getWeights());
            liquorDTOS.add(liquorDTO);
        }
        ans.setSaltMixtureLiquorDTOS(liquorDTOS);

        MaterialDeliveryStatisticCrystalsExample example1 = new MaterialDeliveryStatisticCrystalsExample();
        example1.createCriteria().andStatisticCodeEqualTo(statisticCode);
        detailDTO.setCrystals(crystalsMapper.selectByExample(example1));
        List<CrystalsDTO> crystalsDTOS = new ArrayList<>();
        for (int i = 0; i < detailDTO.getCrystals().size(); i++) {
            CrystalsDTO crystalsDTO = new CrystalsDTO();
            MaterialDeliveryStatisticCrystals crystals = detailDTO.getCrystals().get(i);
            BasicInfoRawmaterial rawmaterial = basicInfoRawmaterialMapper.selectByPrimaryKey(crystals.getMaterialCode());
            crystalsDTO.setConcentration(crystals.getNiConcentration() + crystals.getMnConcentration() + crystals.getCoConcentration());
            crystalsDTO.setWeight(crystals.getWeights());
            crystalsDTO.setCoFlag(rawmaterial.getCoFlag());
            crystalsDTO.setNiFlag(rawmaterial.getNiFlag());
            crystalsDTO.setMnFlag(rawmaterial.getMnFlag());
            crystalsDTO.setMaterialName(crystals.getMaterialName());
            crystalsDTO.setMaterialCode(crystals.getMaterialCode());
            crystalsDTO.setMaterialTypeCode(crystals.getMaterialTypeCode());
            crystalsDTOS.add(crystalsDTO);
        }
        ans.setCrystalsDTOS(crystalsDTOS);

        MaterialDeliveryStatisticSingleCrystalLiquorExample example2 = new MaterialDeliveryStatisticSingleCrystalLiquorExample();
        example2.createCriteria().andStatisticCodeEqualTo(statisticCode);
        detailDTO.setSingleCrystalLiquors(singleCrystalLiquorMapper.selectByExample(example2));
        detailDTO.setCrystals(crystalsMapper.selectByExample(example1));
        List<SingleCrystalLiquorDTO> singleCrystalLiquorDTOS = new ArrayList<>();
        for (int i = 0; i < detailDTO.getSingleCrystalLiquors().size(); i++) {
            SingleCrystalLiquorDTO singleCrystalLiquorDTO = new SingleCrystalLiquorDTO();
            MaterialDeliveryStatisticSingleCrystalLiquor singleCrystalLiquor = detailDTO.getSingleCrystalLiquors().get(i);
            BasicInfoRawmaterial rawmaterial = basicInfoRawmaterialMapper.selectByPrimaryKey(singleCrystalLiquor.getMaterialCode());
            singleCrystalLiquorDTO.setConcentration(singleCrystalLiquor.getCoConcentration() + singleCrystalLiquor.getMnConcentration() + singleCrystalLiquor.getNiConcentration());
            singleCrystalLiquorDTO.setDensity(singleCrystalLiquor.getDensity());
            singleCrystalLiquorDTO.setMaterialCode(singleCrystalLiquor.getMaterialCode());
            singleCrystalLiquorDTO.setMaterialName(singleCrystalLiquor.getMaterialName());
            singleCrystalLiquorDTO.setMaterialTypeCode(singleCrystalLiquor.getMaterialTypeCode());
            singleCrystalLiquorDTO.setWeights(singleCrystalLiquor.getWeights());
            singleCrystalLiquorDTO.setCoFlag(rawmaterial.getCoFlag());
            singleCrystalLiquorDTO.setMnFlag(rawmaterial.getMnFlag());
            singleCrystalLiquorDTO.setNiFlag(rawmaterial.getNiFlag());
            singleCrystalLiquorDTOS.add(singleCrystalLiquorDTO);
        }
        ans.setSingleCrystalLiquorDTOS(singleCrystalLiquorDTOS);

        List<RawMaterialSulfateBase> sulfateBases = new ArrayList<>();
        MaterialDeliveryStatisticNickelSulfateExample example3 = new MaterialDeliveryStatisticNickelSulfateExample();
        example3.createCriteria().andStatisticCodeEqualTo(statisticCode);
        List<MaterialDeliveryStatisticNickelSulfate> statisticNickelSulfates = nickelSulfateMapper.selectByExample(example3);
        sulfateBases.addAll(statisticNickelSulfates);

        MaterialDeliveryStatisticCobaltSulfateExample example4 = new MaterialDeliveryStatisticCobaltSulfateExample();
        example4.createCriteria().andStatisticCodeEqualTo(statisticCode);
        List<MaterialDeliveryStatisticCobaltSulfate> cobaltSulfates = cobaltSulfateMapper.selectByExample(example4);
        sulfateBases.addAll(cobaltSulfates);

        MaterialDeliveryStatisticManganeseSulfateExample example5 = new MaterialDeliveryStatisticManganeseSulfateExample();
        example5.createCriteria().andStatisticCodeEqualTo(statisticCode);
        List<MaterialDeliveryStatisticManganeseSulfate> manganeseSulfates = manganeseSulfateMapper.selectByExample(example5);
        sulfateBases.addAll(manganeseSulfates);
        List<StockOutDTO> stockOutDTOS = new ArrayList<>();
        for (int i = 0; i < sulfateBases.size(); i++) {
            StockOutDTO outDTO = new StockOutDTO();
            outDTO.setMaterialBatch(sulfateBases.get(i).getEncoder());
            outDTO.setMaterialCode(sulfateBases.get(i).getMaterialCode());
            outDTO.setMaterialName(sulfateBases.get(i).getMaterialName());
            outDTO.setMaterialTypeCode(sulfateBases.get(i).getMaterialTypeCode());
            outDTO.setOutStockTime(sulfateBases.get(i).getDeliveryTime());
            outDTO.setWeight(sulfateBases.get(i).getWeights() + "0");
            //outDTO.setCallMaterialPoint(sulfateBases.get(i));
            stockOutDTOS.add(outDTO);
        }
        ans.setStockOutDTOS(stockOutDTOS);
        detailDTO.setSulfates(sulfateBases);

        MaterialDeliveryStatisticSulfateConcentrationExample example6 = new MaterialDeliveryStatisticSulfateConcentrationExample();
        example6.createCriteria().andStatisticCodeEqualTo(statisticCode);
        List<MaterialDeliveryStatisticSulfateConcentration> sulfateConcentrations = sulfateConcentrationMapper.selectByExample(example6);
        detailDTO.setSulfateConcentration(sulfateConcentrations.size() == 0 ? null : sulfateConcentrations.get(0));
        if (sulfateConcentrations.size() != 0) {
            ans.setCoConcentration(detailDTO.getSulfateConcentration().getCoConcentration());
            ans.setMnConcentration(detailDTO.getSulfateConcentration().getMnConcentration());
            ans.setNiConcentration(detailDTO.getSulfateConcentration().getNiConcentration());
        }

        return ans;
    }

    @Override
    @Transactional
    public void commit(MaterialDeliveryCommitDTO commitDTO) {
        Byte flag = commitDTO.getFlag();//0 表示提交 1 表示统计
        Long statisticCode = commitDTO.getStatisticCode();

        //若存在该统计编码 先删除
        deleteAllByStatCode(statisticCode);

        //开始处理数据
        dealAllByStatCodeAndFlag(statisticCode, commitDTO, flag);

    }

    private void dealAllByStatCodeAndFlag(Long statisticCode, MaterialDeliveryCommitDTO commitDTO, Byte flag) {
        if (flag == 1) {
            MaterialDeliveryStatisticHead statisticHead = new MaterialDeliveryStatisticHead();
            statisticHead.setCode(statisticCode);
            statisticHead.setFlag((byte) 1);
            statisticHeadMapper.updateByPrimaryKeySelective(statisticHead);
            operationService.updateAllEndTime(commitDTO.getHead().getPeriodCode(), commitDTO.getHead().getLineName());
        }
        MaterialDeliveryStatisticSulfateConcentration sulfateConcentration = new MaterialDeliveryStatisticSulfateConcentration();
        sulfateConcentration.setStatisticCode(statisticCode);
        sulfateConcentration.setPeriods(commitDTO.getPeriods());
        sulfateConcentration.setNiConcentration(commitDTO.getNiConcentration());
        sulfateConcentration.setCoConcentration(commitDTO.getCoConcentration());
        sulfateConcentration.setMnConcentration(commitDTO.getMnConcentration());
        sulfateConcentrationMapper.insertSelective(sulfateConcentration);

        //总重量初始化
        float weight1 = 0;//出库NiSO4重量
        float weight2 = 0;
        float weight3 = 0;

        float repoNiMental = 0;//出库Ni金属量
        float repoCoMental = 0;
        float repoMnMental = 0;

        Integer niStatus = 0;
        Integer coStatus = 0;
        Integer mnStatus = 0;

        //初始化各产线数据值
        List<ProductionLineDataStatDTO> lineDataStatDTOS = new ArrayList<>();
        BasicInfoPrecursorProductionLineExample example = new BasicInfoPrecursorProductionLineExample();
        example.createCriteria();
        List<BasicInfoPrecursorProductionLine> lines = lineMapper.selectByExample(example);
        for (BasicInfoPrecursorProductionLine line : lines) {
            ProductionLineDataStatDTO dto = new ProductionLineDataStatDTO();
            dto.setLineCode(line.getCode());
            dto.setTotals((float) 0);
            dto.setNiValue((float) 0);
            dto.setCoValue((float) 0);
            dto.setMnValue((float) 0);
            lineDataStatDTOS.add(dto);
        }
        //获取出库数据 进行遍历 遍历的时候还要记一波重量值 以及Ni Co Mn 三个金属量 后面要用
        List<StockOutDTO> stockOutDTOS = commitDTO.getStockOutDTOS();
        for (StockOutDTO stockOutDTO : stockOutDTOS) {
            //拿到NiSO4类型的数据 插入硫酸镍详情数据表
            if (stockOutDTO.getMaterialTypeCode() == 1) {
                niStatus = 1;
                MaterialDeliveryStatisticNickelSulfate nickelSulfate = new MaterialDeliveryStatisticNickelSulfate();
                nickelSulfate.setStatisticCode(statisticCode);
                nickelSulfate.setMaterialTypeCode(stockOutDTO.getMaterialTypeCode());
                nickelSulfate.setMaterialCode(stockOutDTO.getMaterialCode());
                nickelSulfate.setMaterialName(stockOutDTO.getMaterialName());
                nickelSulfate.setEncoder(stockOutDTO.getMaterialBatch());
                nickelSulfate.setDeliveryTime(stockOutDTO.getOutStockTime());
                nickelSulfate.setWeights(Float.valueOf(stockOutDTO.getWeight()));
                nickelSulfate.setNiConcentration(commitDTO.getNiConcentration());
                nickelSulfate.setNiMetallicity(Float.valueOf(stockOutDTO.getWeight()) * commitDTO.getNiConcentration());
                nickelSulfate.setCoConcentration((float) 0);
                nickelSulfate.setCoMetallicity((float) 0);
                nickelSulfate.setMnConcentration((float) 0);
                nickelSulfate.setMnMetallicity((float) 0);
                nickelSulfateMapper.insertSelective(nickelSulfate);
                weight1 += nickelSulfate.getWeights();
                repoNiMental += nickelSulfate.getNiMetallicity();

                if (flag == 1) {
                    //按产线统计
                    BasicInfoRawmaterialLineWeightExample lineWeightExample = new BasicInfoRawmaterialLineWeightExample();
                    lineWeightExample.createCriteria().andMaterialCodeEqualTo(stockOutDTO.getMaterialCode());
                    List<BasicInfoRawmaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(lineWeightExample);
                    for (BasicInfoRawmaterialLineWeight lineWeight : lineWeights) {
                        for (ProductionLineDataStatDTO dataStatDTO : lineDataStatDTOS) {
                            if (dataStatDTO.getLineCode() == lineWeight.getLineCode()) {
                                dataStatDTO.setTotals(dataStatDTO.getTotals() + nickelSulfate.getWeights() * lineWeight.getWeightValue());
                                dataStatDTO.setNiValue(dataStatDTO.getNiValue() + nickelSulfate.getNiMetallicity() * lineWeight.getWeightValue());
                            }
                        }
                    }
                }
            }

            //拿到CoSO4类型的数据 插入硫酸钴详情数据表
            if (stockOutDTO.getMaterialTypeCode() == 2) {
                coStatus = 1;
                MaterialDeliveryStatisticCobaltSulfate cobaltSulfate = new MaterialDeliveryStatisticCobaltSulfate();
                cobaltSulfate.setStatisticCode(statisticCode);
                cobaltSulfate.setMaterialTypeCode(stockOutDTO.getMaterialTypeCode());
                cobaltSulfate.setMaterialCode(stockOutDTO.getMaterialCode());
                cobaltSulfate.setMaterialName(stockOutDTO.getMaterialName());
                cobaltSulfate.setEncoder(stockOutDTO.getMaterialBatch());
                cobaltSulfate.setDeliveryTime(stockOutDTO.getOutStockTime());
                cobaltSulfate.setWeights(Float.valueOf(stockOutDTO.getWeight()));
                cobaltSulfate.setNiConcentration((float) 0);
                cobaltSulfate.setNiMetallicity((float) 0);
                cobaltSulfate.setCoConcentration(commitDTO.getCoConcentration());
                cobaltSulfate.setCoMetallicity(Float.valueOf(stockOutDTO.getWeight()) * commitDTO.getCoConcentration());
                cobaltSulfate.setMnConcentration((float) 0);
                cobaltSulfate.setMnMetallicity((float) 0);
                cobaltSulfateMapper.insertSelective(cobaltSulfate);
                weight2 += cobaltSulfate.getWeights();
                repoCoMental += cobaltSulfate.getCoMetallicity();
                if (flag == 1) {
                    //按产线统计
                    BasicInfoRawmaterialLineWeightExample lineWeightExample = new BasicInfoRawmaterialLineWeightExample();
                    lineWeightExample.createCriteria().andMaterialCodeEqualTo(stockOutDTO.getMaterialCode());
                    List<BasicInfoRawmaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(lineWeightExample);
                    for (BasicInfoRawmaterialLineWeight lineWeight : lineWeights) {
                        for (ProductionLineDataStatDTO dataStatDTO : lineDataStatDTOS) {
                            if (dataStatDTO.getLineCode() == lineWeight.getLineCode()) {
                                dataStatDTO.setTotals(dataStatDTO.getTotals() + cobaltSulfate.getWeights() * lineWeight.getWeightValue());
                                dataStatDTO.setCoValue(dataStatDTO.getCoValue() + cobaltSulfate.getCoMetallicity() * lineWeight.getWeightValue());
                            }
                        }
                    }
                }
            }

            //拿到MnSO4类型的数据 插入硫酸锰详情数据表
            if (stockOutDTO.getMaterialTypeCode() == 3) {
                mnStatus = 1;
                MaterialDeliveryStatisticManganeseSulfate manganeseSulfate = new MaterialDeliveryStatisticManganeseSulfate();
                manganeseSulfate.setStatisticCode(statisticCode);
                manganeseSulfate.setMaterialTypeCode(stockOutDTO.getMaterialTypeCode());
                manganeseSulfate.setMaterialCode(stockOutDTO.getMaterialCode());
                manganeseSulfate.setMaterialName(stockOutDTO.getMaterialName());
                manganeseSulfate.setEncoder(stockOutDTO.getMaterialBatch());
                manganeseSulfate.setDeliveryTime(stockOutDTO.getOutStockTime());
                manganeseSulfate.setWeights(Float.valueOf(stockOutDTO.getWeight()));
                manganeseSulfate.setNiConcentration((float) 0);
                manganeseSulfate.setNiMetallicity((float) 0);
                manganeseSulfate.setCoConcentration((float) 0);
                manganeseSulfate.setCoMetallicity((float) 0);
                manganeseSulfate.setMnConcentration(commitDTO.getMnConcentration());
                manganeseSulfate.setMnMetallicity(Float.valueOf(stockOutDTO.getWeight()) * commitDTO.getMnConcentration());
                manganeseSulfateMapper.insertSelective(manganeseSulfate);
                weight3 += manganeseSulfate.getWeights();
                repoMnMental += manganeseSulfate.getMnMetallicity();
                if (flag == 1) {
                    //按产线统计
                    BasicInfoRawmaterialLineWeightExample lineWeightExample = new BasicInfoRawmaterialLineWeightExample();
                    lineWeightExample.createCriteria().andMaterialCodeEqualTo(stockOutDTO.getMaterialCode());
                    List<BasicInfoRawmaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(lineWeightExample);
                    for (BasicInfoRawmaterialLineWeight lineWeight : lineWeights) {
                        for (ProductionLineDataStatDTO dataStatDTO : lineDataStatDTOS) {
                            if (dataStatDTO.getLineCode() == lineWeight.getLineCode()) {
                                dataStatDTO.setTotals(dataStatDTO.getTotals() + manganeseSulfate.getWeights() * lineWeight.getWeightValue());
                                dataStatDTO.setMnValue(dataStatDTO.getMnValue() + manganeseSulfate.getMnMetallicity() * lineWeight.getWeightValue());
                            }
                        }
                    }
                }
            }
        }

        float crystalsWeight = 0;// 补料 晶体重量
        float saltMixtureLiquorWeight = 0;// 补料 混合盐溶液重量
        float singleCrystalLiquorWeight = 0;// 补料 单晶体溶液重量

        float supCrystalsNiMental = 0; //补料 晶体镍金属量
        float supCrystalsCoMental = 0; //补料 晶体钴金属量
        float supCrystalsMnMental = 0; //补料 晶体锰金属量

        float supSaltMixtureLiquorNiMental = 0;
        float supSaltMixtureLiquorCoMental = 0;
        float supSaltMixtureLiquorMnMental = 0;

        float supSingleCrystalLiquorNiMental = 0;
        float supSingleCrystalLiquorCoMental = 0;
        float supSingleCrystalLiquorMnMental = 0;

        //如果有补料
        List<CrystalsDTO> crystalsDTOS = commitDTO.getCrystalsDTOS();
        for (CrystalsDTO crystalsDTO : crystalsDTOS) {
            MaterialDeliveryStatisticCrystals crystals = new MaterialDeliveryStatisticCrystals();
            crystals.setStatisticCode(statisticCode);
            crystals.setMaterialTypeCode(crystalsDTO.getMaterialTypeCode());
            crystals.setMaterialCode(crystalsDTO.getMaterialCode());
            crystals.setMaterialName(crystalsDTO.getMaterialName());
            crystals.setWeights(crystalsDTO.getWeight());
            crystalsWeight += crystals.getWeights();

            //如果是Ni晶体
            if (crystalsDTO.getNiFlag()) {
                crystals.setNiConcentration(crystalsDTO.getConcentration());
                crystals.setNiMetallicity(crystalsDTO.getWeight() * crystalsDTO.getConcentration());
                crystals.setCoConcentration((float) 0);
                crystals.setCoMetallicity((float) 0);
                crystals.setMnConcentration((float) 0);
                crystals.setMnMetallicity((float) 0);
                supCrystalsNiMental += crystals.getNiMetallicity();
            }
            if (crystalsDTO.getCoFlag()) {
                crystals.setNiConcentration((float) 0);
                crystals.setNiMetallicity((float) 0);
                crystals.setCoConcentration(crystalsDTO.getConcentration());
                crystals.setCoMetallicity(crystalsDTO.getWeight() * crystalsDTO.getConcentration());
                crystals.setMnConcentration((float) 0);
                crystals.setMnMetallicity((float) 0);
                supCrystalsCoMental += crystals.getCoMetallicity();
            }
            if (crystalsDTO.getMnFlag()) {
                crystals.setNiConcentration((float) 0);
                crystals.setNiMetallicity((float) 0);
                crystals.setCoConcentration((float) 0);
                crystals.setCoMetallicity((float) 0);
                crystals.setMnConcentration(crystalsDTO.getConcentration());
                crystals.setMnMetallicity(crystalsDTO.getWeight() * crystalsDTO.getConcentration());
                supCrystalsMnMental += crystals.getMnMetallicity();
            }
            crystalsMapper.insertSelective(crystals);//晶体 插入晶体详情表
            if (flag == 1) {
                //按产线统计
                BasicInfoRawmaterialLineWeightExample lineWeightExample = new BasicInfoRawmaterialLineWeightExample();
                lineWeightExample.createCriteria().andMaterialCodeEqualTo(crystalsDTO.getMaterialCode());
                List<BasicInfoRawmaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(lineWeightExample);
                for (BasicInfoRawmaterialLineWeight lineWeight : lineWeights) {
                    for (ProductionLineDataStatDTO dataStatDTO : lineDataStatDTOS) {
                        if (dataStatDTO.getLineCode() == lineWeight.getLineCode()) {
                            dataStatDTO.setTotals(dataStatDTO.getTotals() + crystals.getWeights() * lineWeight.getWeightValue());
                            dataStatDTO.setNiValue(dataStatDTO.getNiValue() + crystals.getNiMetallicity() * lineWeight.getWeightValue());
                            dataStatDTO.setCoValue(dataStatDTO.getCoValue() + crystals.getCoMetallicity() * lineWeight.getWeightValue());
                            dataStatDTO.setMnValue(dataStatDTO.getMnValue() + crystals.getMnMetallicity() * lineWeight.getWeightValue());
                        }
                    }
                }
            }
        }

        List<SaltMixtureLiquorDTO> saltMixtureLiquorDTOS = commitDTO.getSaltMixtureLiquorDTOS();
        for (SaltMixtureLiquorDTO saltMixtureLiquorDTO : saltMixtureLiquorDTOS) {
            //混合盐溶液 插入混合盐溶液详情表
            MaterialDeliveryStatisticSaltMixtureLiquor saltMixtureLiquor = new MaterialDeliveryStatisticSaltMixtureLiquor();
            saltMixtureLiquor.setStatisticCode(statisticCode);
            saltMixtureLiquor.setMaterialTypeCode(saltMixtureLiquorDTO.getMaterialTypeCode());
            saltMixtureLiquor.setMaterialCode(saltMixtureLiquorDTO.getMaterialCode());
            saltMixtureLiquor.setMaterialName(saltMixtureLiquorDTO.getMaterialName());
            saltMixtureLiquor.setWeights(saltMixtureLiquorDTO.getWeights());
            saltMixtureLiquor.setDensity(saltMixtureLiquorDTO.getDensity());
            saltMixtureLiquor.setNiConcentration(saltMixtureLiquorDTO.getNiConcentration());
            saltMixtureLiquor.setCoConcentration(saltMixtureLiquorDTO.getCoConcentration());
            saltMixtureLiquor.setMnConcentration(saltMixtureLiquorDTO.getMnConcentration());
            if (flag == 1) {
                saltMixtureLiquor.setNiMetallicity(saltMixtureLiquorDTO.getWeights() / saltMixtureLiquorDTO.getDensity() * saltMixtureLiquorDTO.getNiConcentration() / 1000);
                saltMixtureLiquor.setCoMetallicity(saltMixtureLiquorDTO.getWeights() / saltMixtureLiquorDTO.getDensity() * saltMixtureLiquorDTO.getCoConcentration() / 1000);
                saltMixtureLiquor.setMnMetallicity(saltMixtureLiquorDTO.getWeights() / saltMixtureLiquorDTO.getDensity() * saltMixtureLiquorDTO.getMnConcentration() / 1000);

            } else {
                saltMixtureLiquor.setNiMetallicity((float) 0);
                saltMixtureLiquor.setCoMetallicity((float) 0);
                saltMixtureLiquor.setMnMetallicity((float) 0);
            }

            saltMixtureLiquorMapper.insertSelective(saltMixtureLiquor);
            saltMixtureLiquorWeight += saltMixtureLiquor.getWeights();
            supSaltMixtureLiquorNiMental += saltMixtureLiquor.getNiMetallicity();
            supSaltMixtureLiquorCoMental += saltMixtureLiquor.getCoMetallicity();
            supSaltMixtureLiquorMnMental += saltMixtureLiquor.getMnMetallicity();
            if (flag == 1) {
                BasicInfoRawmaterialLineWeightExample lineWeightExample = new BasicInfoRawmaterialLineWeightExample();
                lineWeightExample.createCriteria().andMaterialCodeEqualTo(saltMixtureLiquorDTO.getMaterialCode());
                List<BasicInfoRawmaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(lineWeightExample);
                for (BasicInfoRawmaterialLineWeight lineWeight : lineWeights) {
                    for (ProductionLineDataStatDTO dataStatDTO : lineDataStatDTOS) {
                        if (dataStatDTO.getLineCode() == lineWeight.getLineCode()) {
                            dataStatDTO.setTotals(dataStatDTO.getTotals() + saltMixtureLiquor.getWeights() * lineWeight.getWeightValue());
                            dataStatDTO.setNiValue(dataStatDTO.getNiValue() + saltMixtureLiquor.getNiMetallicity() * lineWeight.getWeightValue());
                            dataStatDTO.setCoValue(dataStatDTO.getCoValue() + saltMixtureLiquor.getCoMetallicity() * lineWeight.getWeightValue());
                            dataStatDTO.setMnValue(dataStatDTO.getMnValue() + saltMixtureLiquor.getMnMetallicity() * lineWeight.getWeightValue());
                        }
                    }
                }
            }
        }

        List<SingleCrystalLiquorDTO> singleCrystalLiquorDTOS = commitDTO.getSingleCrystalLiquorDTOS();
        for (SingleCrystalLiquorDTO singleCrystalLiquorDTO : singleCrystalLiquorDTOS) {
            //单晶体溶液 插入单晶体溶液详情表
            MaterialDeliveryStatisticSingleCrystalLiquor singleCrystalLiquor = new MaterialDeliveryStatisticSingleCrystalLiquor();
            singleCrystalLiquor.setStatisticCode(statisticCode);
            singleCrystalLiquor.setMaterialTypeCode(singleCrystalLiquorDTO.getMaterialTypeCode());
            singleCrystalLiquor.setMaterialCode(singleCrystalLiquorDTO.getMaterialCode());
            singleCrystalLiquor.setMaterialName(singleCrystalLiquorDTO.getMaterialName());
            singleCrystalLiquor.setWeights(singleCrystalLiquorDTO.getWeights());
            singleCrystalLiquor.setDensity(singleCrystalLiquorDTO.getDensity());
            singleCrystalLiquorWeight += singleCrystalLiquor.getWeights();
            if (singleCrystalLiquorDTO.getNiFlag()) {
                singleCrystalLiquor.setNiConcentration(singleCrystalLiquorDTO.getConcentration());
                if (flag == 1) {
                    singleCrystalLiquor.setNiMetallicity(singleCrystalLiquorDTO.getWeights() / singleCrystalLiquorDTO.getDensity() * singleCrystalLiquorDTO.getConcentration() / 1000);
                } else {
                    singleCrystalLiquor.setNiMetallicity((float) 0);
                }
                singleCrystalLiquor.setCoConcentration((float) 0);
                singleCrystalLiquor.setCoMetallicity((float) 0);
                singleCrystalLiquor.setMnConcentration((float) 0);
                singleCrystalLiquor.setMnMetallicity((float) 0);
                supSingleCrystalLiquorNiMental += singleCrystalLiquor.getNiMetallicity();
            }
            if (singleCrystalLiquorDTO.getCoFlag()) {
                singleCrystalLiquor.setNiConcentration((float) 0);
                singleCrystalLiquor.setNiMetallicity((float) 0);
                singleCrystalLiquor.setCoConcentration(singleCrystalLiquorDTO.getConcentration());
                if (flag == 1) {
                    singleCrystalLiquor.setCoMetallicity(singleCrystalLiquorDTO.getWeights() / singleCrystalLiquorDTO.getDensity() * singleCrystalLiquorDTO.getConcentration() / 1000);
                } else {
                    singleCrystalLiquor.setCoMetallicity((float) 0);
                }
                singleCrystalLiquor.setMnConcentration((float) 0);
                singleCrystalLiquor.setMnMetallicity((float) 0);
                supSingleCrystalLiquorCoMental += singleCrystalLiquor.getCoMetallicity();
            }
            if (singleCrystalLiquorDTO.getMnFlag()) {
                singleCrystalLiquor.setMnConcentration(singleCrystalLiquorDTO.getConcentration());
                if (flag == 1) {
                    singleCrystalLiquor.setMnMetallicity(singleCrystalLiquorDTO.getWeights() / singleCrystalLiquorDTO.getDensity() * singleCrystalLiquorDTO.getConcentration() / 1000);
                } else {
                    singleCrystalLiquor.setMnMetallicity((float) 0);
                }
                singleCrystalLiquor.setCoConcentration((float) 0);
                singleCrystalLiquor.setCoMetallicity((float) 0);
                singleCrystalLiquor.setNiConcentration((float) 0);
                singleCrystalLiquor.setNiMetallicity((float) 0);
                supSingleCrystalLiquorMnMental += singleCrystalLiquor.getMnMetallicity();
            }
            singleCrystalLiquorMapper.insertSelective(singleCrystalLiquor);
            if (flag == 1) {
                BasicInfoRawmaterialLineWeightExample lineWeightExample = new BasicInfoRawmaterialLineWeightExample();
                lineWeightExample.createCriteria().andMaterialCodeEqualTo(singleCrystalLiquorDTO.getMaterialCode());
                List<BasicInfoRawmaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(lineWeightExample);
                for (BasicInfoRawmaterialLineWeight lineWeight : lineWeights) {
                    for (ProductionLineDataStatDTO dataStatDTO : lineDataStatDTOS) {
                        if (dataStatDTO.getLineCode() == lineWeight.getLineCode()) {
                            dataStatDTO.setTotals(dataStatDTO.getTotals() + singleCrystalLiquor.getWeights() * lineWeight.getWeightValue());
                            dataStatDTO.setNiValue(dataStatDTO.getNiValue() + singleCrystalLiquor.getNiMetallicity() * lineWeight.getWeightValue());
                            dataStatDTO.setCoValue(dataStatDTO.getCoValue() + singleCrystalLiquor.getCoMetallicity() * lineWeight.getWeightValue());
                            dataStatDTO.setMnValue(dataStatDTO.getMnValue() + singleCrystalLiquor.getMnMetallicity() * lineWeight.getWeightValue());
                        }
                    }
                }
            }
        }

        //选择提交 也就是插入统计表
        if (flag == 1) {
            //对出库NiSO4进行统计
            if (niStatus == 1) {
                MaterialDeliveryStatisticDataList dataList = new MaterialDeliveryStatisticDataList();
                dataList.setStatisticCode(statisticCode);
                dataList.setPeriods(commitDTO.getPeriods());
                dataList.setDataType((byte) 0);
                dataList.setMaterialTypeCode(1);
                dataList.setMaterialTypeName("NiSO4");
                dataList.setTotals(weight1);
                dataList.setNiValue(repoNiMental);
                dataList.setCoValue((float) 0);
                dataList.setMnValue((float) 0);
                dataListMapper.insertSelective(dataList);
            }
            //对出库CoSO4进行统计
            if (coStatus == 1) {
                MaterialDeliveryStatisticDataList dataList = new MaterialDeliveryStatisticDataList();
                dataList.setStatisticCode(statisticCode);
                dataList.setPeriods(commitDTO.getPeriods());
                dataList.setDataType((byte) 0);
                dataList.setMaterialTypeCode(2);
                dataList.setMaterialTypeName("CoSO4");
                dataList.setTotals(weight2);
                dataList.setNiValue((float) 0);
                dataList.setCoValue(repoCoMental);
                dataList.setMnValue((float) 0);
                dataListMapper.insertSelective(dataList);
            }
            //对出库MnSO4进行统计
            if (mnStatus == 1) {
                MaterialDeliveryStatisticDataList dataList = new MaterialDeliveryStatisticDataList();
                dataList.setStatisticCode(statisticCode);
                dataList.setPeriods(commitDTO.getPeriods());
                dataList.setDataType((byte) 0);
                dataList.setMaterialTypeCode(3);
                dataList.setMaterialTypeName("MnSO4");
                dataList.setTotals(weight3);
                dataList.setNiValue((float) 0);
                dataList.setCoValue((float) 0);
                dataList.setMnValue(repoMnMental);
                dataListMapper.insertSelective(dataList);
            }

            //对补料-晶体进行统计
            if (crystalsDTOS.size() > 0) {
                MaterialDeliveryStatisticDataList dataList = new MaterialDeliveryStatisticDataList();
                dataList.setStatisticCode(statisticCode);
                dataList.setPeriods(commitDTO.getPeriods());
                dataList.setDataType((byte) 1);
                dataList.setMaterialTypeCode(5);
                dataList.setMaterialTypeName("晶体");
                dataList.setTotals(crystalsWeight);
                dataList.setNiValue(supCrystalsNiMental);
                dataList.setCoValue(supCrystalsCoMental);
                dataList.setMnValue(supCrystalsMnMental);
                dataListMapper.insertSelective(dataList);
            }

            //对补料-混合盐溶液进行统计
            if (saltMixtureLiquorDTOS.size() > 0) {
                MaterialDeliveryStatisticDataList dataList = new MaterialDeliveryStatisticDataList();
                dataList.setStatisticCode(statisticCode);
                dataList.setPeriods(commitDTO.getPeriods());
                dataList.setDataType((byte) 1);
                dataList.setMaterialTypeCode(4);
                dataList.setMaterialTypeName("混合盐溶液");
                dataList.setTotals(saltMixtureLiquorWeight);
                dataList.setNiValue(supSaltMixtureLiquorNiMental);
                dataList.setCoValue(supSaltMixtureLiquorCoMental);
                dataList.setMnValue(supSaltMixtureLiquorMnMental);
                dataListMapper.insertSelective(dataList);
            }

            //对补料-单晶体溶液进行统计
            if (singleCrystalLiquorDTOS.size() > 0) {
                MaterialDeliveryStatisticDataList dataList = new MaterialDeliveryStatisticDataList();
                dataList.setStatisticCode(statisticCode);
                dataList.setPeriods(commitDTO.getPeriods());
                dataList.setDataType((byte) 1);
                dataList.setMaterialTypeCode(6);
                dataList.setMaterialTypeName("单晶体溶液");
                dataList.setTotals(singleCrystalLiquorWeight);
                dataList.setNiValue(supSingleCrystalLiquorNiMental);
                dataList.setCoValue(supSingleCrystalLiquorCoMental);
                dataList.setMnValue(supSingleCrystalLiquorMnMental);
                dataListMapper.insertSelective(dataList);
            }

            //遍历按产线统计得到的数据记录集合 插入数据库
            for (ProductionLineDataStatDTO dataStatDTO : lineDataStatDTOS) {
                if (dataStatDTO.getTotals() != 0) {
                    MaterialDeliveryStatisticByLineDetail byLineDetail = new MaterialDeliveryStatisticByLineDetail();
                    byLineDetail.setStatisticCode(statisticCode);
                    byLineDetail.setLineCode(dataStatDTO.getLineCode());
                    byLineDetail.setPeriods(commitDTO.getPeriods());
                    byLineDetail.setTotals(dataStatDTO.getTotals());
                    byLineDetail.setNiValue(dataStatDTO.getNiValue());
                    byLineDetail.setCoValue(dataStatDTO.getCoValue());
                    byLineDetail.setMnValue(dataStatDTO.getMnValue());
                    lineDetailMapper.insertSelective(byLineDetail);
                }
            }

            //合计
            MaterialDeliveryStatisticByLineTotals totals = new MaterialDeliveryStatisticByLineTotals();
            totals.setStatisticCode(statisticCode);
            totals.setPeriods(commitDTO.getPeriods());
            totals.setTotals(weight1 + weight2 + weight3 + crystalsWeight + saltMixtureLiquorWeight + singleCrystalLiquorWeight);
            totals.setNiValue(repoNiMental + supCrystalsNiMental + supSaltMixtureLiquorNiMental + supSingleCrystalLiquorNiMental);
            totals.setCoValue(repoCoMental + supCrystalsCoMental + supSaltMixtureLiquorCoMental + supSingleCrystalLiquorCoMental);
            totals.setMnValue(repoMnMental + supCrystalsMnMental + supSaltMixtureLiquorMnMental + supSingleCrystalLiquorMnMental);
            lineTotalsMapper.insertSelective(totals);
        }
    }

    private void deleteAllByStatCode(Long statisticCode) {
        MaterialDeliveryStatisticSulfateConcentrationExample example = new MaterialDeliveryStatisticSulfateConcentrationExample();
        example.createCriteria().andStatisticCodeEqualTo(statisticCode);
        sulfateConcentrationMapper.deleteByExample(example);

        MaterialDeliveryStatisticNickelSulfateExample example1 = new MaterialDeliveryStatisticNickelSulfateExample();
        example1.createCriteria().andStatisticCodeEqualTo(statisticCode);
        nickelSulfateMapper.deleteByExample(example1);

        MaterialDeliveryStatisticCobaltSulfateExample example2 = new MaterialDeliveryStatisticCobaltSulfateExample();
        example2.createCriteria().andStatisticCodeEqualTo(statisticCode);
        cobaltSulfateMapper.deleteByExample(example2);

        MaterialDeliveryStatisticManganeseSulfateExample example3 = new MaterialDeliveryStatisticManganeseSulfateExample();
        example3.createCriteria().andStatisticCodeEqualTo(statisticCode);
        manganeseSulfateMapper.deleteByExample(example3);

        MaterialDeliveryStatisticCrystalsExample example4 = new MaterialDeliveryStatisticCrystalsExample();
        example4.createCriteria().andStatisticCodeEqualTo(statisticCode);
        crystalsMapper.deleteByExample(example4);

        MaterialDeliveryStatisticSaltMixtureLiquorExample example5 = new MaterialDeliveryStatisticSaltMixtureLiquorExample();
        example5.createCriteria().andStatisticCodeEqualTo(statisticCode);
        saltMixtureLiquorMapper.deleteByExample(example5);

        MaterialDeliveryStatisticSingleCrystalLiquorExample example6 = new MaterialDeliveryStatisticSingleCrystalLiquorExample();
        example6.createCriteria().andStatisticCodeEqualTo(statisticCode);
        singleCrystalLiquorMapper.deleteByExample(example6);
    }

    @Override
    public List getStatisticDetail(Long statisticCode, Integer materialTypeCode) {
        if (materialTypeCode == 1) {
            MaterialDeliveryStatisticNickelSulfateExample example = new MaterialDeliveryStatisticNickelSulfateExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticCode);
            return nickelSulfateMapper.selectByExample(example);
        }
        if (materialTypeCode == 2) {
            MaterialDeliveryStatisticCobaltSulfateExample example = new MaterialDeliveryStatisticCobaltSulfateExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticCode);
            return cobaltSulfateMapper.selectByExample(example);
        }
        if (materialTypeCode == 3) {
            MaterialDeliveryStatisticManganeseSulfateExample example = new MaterialDeliveryStatisticManganeseSulfateExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticCode);
            return manganeseSulfateMapper.selectByExample(example);
        }
        if (materialTypeCode == 4) {
            MaterialDeliveryStatisticSaltMixtureLiquorExample example = new MaterialDeliveryStatisticSaltMixtureLiquorExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticCode);
            return saltMixtureLiquorMapper.selectByExample(example);
        }
        if (materialTypeCode == 5) {
            MaterialDeliveryStatisticCrystalsExample example = new MaterialDeliveryStatisticCrystalsExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticCode);
            return crystalsMapper.selectByExample(example);
        }
        if (materialTypeCode == 6) {
            MaterialDeliveryStatisticSingleCrystalLiquorExample example = new MaterialDeliveryStatisticSingleCrystalLiquorExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticCode);
            return singleCrystalLiquorMapper.selectByExample(example);
        }
        return null;
    }

    @Override
    public void deleteUnsubmit(Long statisticCode) {

        //删除其他关联数据
        deleteAllByStatCode(statisticCode);

        //删除统计头表
        statisticHeadMapper.deleteByPrimaryKey(statisticCode);
    }

    @Override
    public ListByProductionLineDTO listByProductionLine(Integer periodCode, String startTime) {

        BasicInfoPrecursorPeriodExample example = new BasicInfoPrecursorPeriodExample();
        example.createCriteria();
        List<BasicInfoPrecursorPeriod> periodList = periodMapper.selectByExample(example);
        Assert.notEmpty(periodList, "请先维护好基础数据");
        Integer periodCodeDefault = periodList.get(0).getCode();

        Integer periodId = periodCode == null ? periodCodeDefault : periodCode;

        MaterialDeliveryStatisticHeadExample example1 = new MaterialDeliveryStatisticHeadExample();
        example1.createCriteria();
        example1.setOrderByClause("start_time desc");
        List<MaterialDeliveryStatisticHead> headList = statisticHeadMapper.selectByExample(example1);
        Assert.notEmpty(headList, "不存在统计头表信息");

        Date startDate = startTime == null ? headList.get(0).getStartTime() : ComUtil.getDate(startTime, "yyyy-MM-dd HH:mm:ss");


        MaterialDeliveryStatisticHeadExample headExample = new MaterialDeliveryStatisticHeadExample();
        headExample.createCriteria().
                andPeriodCodeEqualTo(periodId).andStartTimeEqualTo(startDate).andFlagEqualTo((byte) 1);
        List<MaterialDeliveryStatisticHead> statisticHeads = statisticHeadMapper.selectByExample(headExample);
        Assert.notEmpty(statisticHeads, "不存在统计头表信息");

        MaterialDeliveryStatisticHead statisticHead = statisticHeads.get(0);

        Long statisticCode = statisticHead.getCode();

        MaterialDeliveryStatisticByLineDetailExample example2 = new MaterialDeliveryStatisticByLineDetailExample();
        example2.createCriteria().andStatisticCodeEqualTo(statisticCode);
        List<MaterialDeliveryStatisticByLineDetail> lineDetails = lineDetailMapper.selectByExample(example2);

        MaterialDeliveryStatisticByLineTotalsExample example3 = new MaterialDeliveryStatisticByLineTotalsExample();
        example3.createCriteria().andStatisticCodeEqualTo(statisticCode);
        List<MaterialDeliveryStatisticByLineTotals> lineTotals = lineTotalsMapper.selectByExample(example3);
        Assert.notEmpty(lineTotals, "不存在产线合计信息");

        ListByProductionLineDTO lineDTO = new ListByProductionLineDTO();

        List<ProductionLineStatDTO> statDTOS = new ArrayList<>();

        String name = periodMapper.selectByPrimaryKey(statisticHead.getPeriodCode()).getName();
        for (MaterialDeliveryStatisticByLineDetail lineDetail : lineDetails) {
            ProductionLineStatDTO statDTO = new ProductionLineStatDTO();
            statDTO.setPeriodType(name);
            statDTO.setPeriodNum(statisticHead.getLineName());
            statDTO.setProductionLine(lineMapper.selectByPrimaryKey(lineDetail.getLineCode()).getName());
            statDTO.setStartTime(statisticHead.getStartTime());
            statDTO.setEndTime(statisticHead.getEndTime());
            statDTO.setTotals(lineDetail.getTotals());
            statDTO.setNiValue(lineDetail.getNiValue());
            statDTO.setCoValue(lineDetail.getCoValue());
            statDTO.setMnValue(lineDetail.getMnValue());

            statDTOS.add(statDTO);
        }

        lineDTO.setLineStatDTOS(statDTOS);
        lineDTO.setByLineTotals(lineTotals.get(0));
        return lineDTO;
    }

    @Override
    public List periodCompareCurve(Integer periodCode, Integer lineCode, String startTime, String endTime) {
        Date startDate = ComUtil.getDate(startTime, "yyyy-MM-dd HH:mm:ss");
        Date endDate = ComUtil.getDate(endTime, "yyyy-MM-dd HH:mm:ss");

        MaterialDeliveryStatisticHeadExample example = new MaterialDeliveryStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodCode).andStartTimeBetween(startDate, endDate).andFlagEqualTo((byte) 1);
        List<MaterialDeliveryStatisticHead> heads = statisticHeadMapper.selectByExample(example);

        List<MaterialDeliveryStatisticByLineDetail> lineDetails = new ArrayList<>();

        if (heads.size() > 0) {
            for (MaterialDeliveryStatisticHead head : heads) {
                Long statCode = head.getCode();
                MaterialDeliveryStatisticByLineDetailExample example1 = new MaterialDeliveryStatisticByLineDetailExample();
                example1.createCriteria().andStatisticCodeEqualTo(statCode).andLineCodeEqualTo(lineCode);
                List<MaterialDeliveryStatisticByLineDetail> byLineDetails = lineDetailMapper.selectByExample(example1);
                if (byLineDetails.size() > 0) {
                    lineDetails.add(byLineDetails.get(0));
                }
            }
        }
        return lineDetails;
    }

    @Override
    public List lineCompareCurve(Integer periodCode, Integer periodNum, Integer[] lineCodes) {
        MaterialDeliveryStatisticHeadExample example = new MaterialDeliveryStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodCode).andLineNameEqualTo(periodNum).andFlagEqualTo((byte) 1);
        List<Map> lineDetails = new ArrayList<>();
        List<MaterialDeliveryStatisticHead> heads = statisticHeadMapper.selectByExample(example);

        BasicInfoPrecursorProductionLineExample lineExample = new BasicInfoPrecursorProductionLineExample();
        lineExample.createCriteria();
        List<BasicInfoPrecursorProductionLine> lines = lineMapper.selectByExample(lineExample);

        Map<Integer, String> lineMap = new HashMap<>();
        lines.forEach(item -> lineMap.put(item.getCode(), item.getName()));

        if (heads.size() > 0) {
            Long statCode = heads.get(0).getCode();

            for (Integer lineCode : lineCodes) {
                Map<String, Object> map = new HashMap<>();
                MaterialDeliveryStatisticByLineDetailExample example1 = new MaterialDeliveryStatisticByLineDetailExample();
                example1.createCriteria().andLineCodeEqualTo(lineCode).andStatisticCodeEqualTo(statCode);
                List<MaterialDeliveryStatisticByLineDetail> details = lineDetailMapper.selectByExample(example1);
                if (details.size() > 0) {
                    MaterialDeliveryStatisticByLineDetail byLineDetail = details.get(0);
                    map.put("code", byLineDetail.getCode());
                    map.put("statisticCode", byLineDetail.getStatisticCode());
                    map.put("periods", byLineDetail.getPeriods());
                    map.put("lineCode", byLineDetail.getLineCode());
                    map.put("lineName", lineMap.get(byLineDetail.getLineCode()));
                    map.put("niValue", byLineDetail.getNiValue());
                    map.put("coValue", byLineDetail.getCoValue());
                    map.put("mnValue", byLineDetail.getMnValue());
                    lineDetails.add(map);
                }
            }

            return lineDetails;
        }
        return lineDetails;
    }

    @Override
    public List getDate(Integer periodId) {
        List<String> ans = new ArrayList<>();
        MaterialDeliveryStatisticHeadExample example = new MaterialDeliveryStatisticHeadExample();
        example.createCriteria().andFlagEqualTo(new Integer(1).byteValue()).andPeriodCodeEqualTo(periodId);
        List<MaterialDeliveryStatisticHead> heads = statisticHeadMapper.selectByExample(example);
        for (int i = 0; i < heads.size(); i++) {
            ans.add(ComUtil.dateToString(heads.get(i).getStartTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        return ans;
    }

    @Override
    public List getPeriodAndTime(Integer periodId) {

        List<Map<String, Object>> list = new ArrayList<>();


        String df = "yyyy-MM-dd HH:mm:ss";

        MaterialDeliveryStatisticHeadExample example = new MaterialDeliveryStatisticHeadExample();
        example.createCriteria().andFlagEqualTo(new Integer(1).byteValue()).andPeriodCodeEqualTo(periodId);
        List<MaterialDeliveryStatisticHead> heads = statisticHeadMapper.selectByExample(example);
        for (MaterialDeliveryStatisticHead head : heads) {
            Map<String, Object> map = new HashMap<>();
            map.put("periodNum", head.getLineName());
            map.put("startTime", ComUtil.dateToString(head.getStartTime(), df));
            map.put("endTime", ComUtil.dateToString(head.getEndTime(), df));
            list.add(map);
        }
        return list;
    }
}
