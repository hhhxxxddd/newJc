package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ProductStorageServiceImp implements ProductStorageService {

    @Autowired
    ProductStorageStatisticDataDetailsMapper dataDetailsMapper;
    @Autowired
    ProductionBatchInfoMapper batchInfoMapper;
    @Autowired
    ProductStorageStatisticHeadMapper headMapper;
    @Autowired
    GoodsInProcessStatisticHeadMapper goodsInProcessStatisticHeadMapper;
    @Autowired
    MaterialDeliveryStatisticHeadMapper materialDeliveryStatisticHeadMapper;
    @Autowired
    ProductStorageStatisticDataListMapper listMapper;
    @Autowired
    ProductStorageStatisticByLineTotalsMapper lineTotalsMapper;
    @Autowired
    ProductStorageStatisticByLineDetailMapper lineDetailMapper;
    @Autowired
    BasicInfoPrecursorProductionLineMapper lineMapper;
    @Autowired
    BasicInfoPrecursorPeriodMapper periodMapper;
    @Autowired
    AuxiliaryMaterialsStatisticHeadMapper auxMapper;

    @Override
    public void addUse(Long headId, String productName, Long batchId, Float ni, Float co, Float mn, Float weight) {
        ProductStorageStatisticDataDetails details = new ProductStorageStatisticDataDetails();
        details.setStatisticCode(headId);
        details.setProductionTypeName(productName);
        details.setStorageTime(new Date());

        ProductionBatchInfoExample example = new ProductionBatchInfoExample();
        example.createCriteria().andCodeEqualTo(batchId);
        List<ProductionBatchInfo> infos = batchInfoMapper.selectByExample(example);
        ProductionBatchInfo info = infos.get(0);
        details.setBatch(info.getBatch());
        details.setProductionTypeCode(info.getProductionType());

        String line = info.getProductionLine();
        if (line.contains("1")) {
            details.setLineCode(1);
        } else if (line.contains("2")) {
            details.setLineCode(2);
        } else if (line.contains("3")) {
            details.setLineCode(3);
        } else if (line.contains("4")) {
            details.setLineCode(4);
        }
        details.setNiConcentration(ni);
        details.setCoConcentration(co);
        details.setMnConcentration(mn);
        details.setNiMetallicity(weight * ni);
        details.setCoMetallicity(weight * co);
        details.setMnMetallicity(weight * mn);
        details.setWeights(weight);
        dataDetailsMapper.insertSelective(details);
    }

    @Override
    public List getAllBatch() {
        ProductionBatchInfoExample example = new ProductionBatchInfoExample();
        example.createCriteria();
        return batchInfoMapper.selectByExample(example);
    }

    @Override
    public Page getInData(Long headId, Integer page, Integer size) {
        ProductStorageStatisticDataDetailsExample example = new ProductStorageStatisticDataDetailsExample();
        example.createCriteria().andStatisticCodeEqualTo(headId);
        return new Page(dataDetailsMapper.selectByExample(example), page, size);
    }

    @Override
    public Object addConfirm(ProductStorageStatisticHead head) {
        head.setFlag(new Integer(0).byteValue());
        Integer periodId = head.getPeriodCode();
        Date start = head.getStartTime();
        Integer periods = head.getLineName();
        Date end = head.getEndTime();

        GoodsInProcessStatisticHeadExample example = new GoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(head.getLineName());
        List<GoodsInProcessStatisticHead> gHeads = goodsInProcessStatisticHeadMapper.selectByExample(example);

        MaterialDeliveryStatisticHeadExample example1 = new MaterialDeliveryStatisticHeadExample();
        example1.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(head.getLineName());
        List<MaterialDeliveryStatisticHead> mHeads = materialDeliveryStatisticHeadMapper.selectByExample(example1);

        AuxiliaryMaterialsStatisticHeadExample example3 = new AuxiliaryMaterialsStatisticHeadExample();
        example3.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(head.getLineName());
        List<AuxiliaryMaterialsStatisticHead> aHeads = auxMapper.selectByExample(example3);

        ProductStorageStatisticHeadExample example2 = new ProductStorageStatisticHeadExample();
        example2.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(periods);
        List<ProductStorageStatisticHead> pphead = headMapper.selectByExample(example2);

        if (pphead.size() != 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", -1);
            map.put("message", "本期数据已经存在");
            return map;
        }
        String format = "yyyy-MM-dd HH:mm:ss";

        if (gHeads.size() == 0 && mHeads.size() == 0 && aHeads.size() == 0) {
            headMapper.insertSelective(head);
            Map<String, Object> map = new HashMap<>();
            map.put("code", head.getCode());
            map.put("message", "success");
            return map;
        }
        if (gHeads.size() != 0) {
            GoodsInProcessStatisticHead temp = gHeads.get(0);
            if (temp.getEndTime().equals(end) && temp.getStartTime().equals(start)) {

            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -2);
                map.put("message", "存在不一致的统计周期，本期开始时间为: " + ComUtil.dateToString(temp.getStartTime(), format) + "本期结束时间为: " + ComUtil.dateToString(temp.getEndTime(), format));
                return map;
            }
        }
        if (mHeads.size() != 0) {
            MaterialDeliveryStatisticHead temp = mHeads.get(0);
            if (temp.getEndTime().equals(end) && temp.getStartTime().equals(start)) {
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -2);
                map.put("message", "存在不一致的统计周期，本期开始时间为: " + ComUtil.dateToString(temp.getStartTime(), format) + "本期结束时间为: " + ComUtil.dateToString(temp.getEndTime(), format));
                return map;
            }
        }
        if (aHeads.size() != 0) {
            AuxiliaryMaterialsStatisticHead temp = aHeads.get(0);
            if (temp.getEndTime().equals(end) && temp.getStartTime().equals(start)) {

            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -2);
                map.put("message", "存在不一致的统计周期，本期开始时间为: " + ComUtil.dateToString(temp.getStartTime(), format) + "本期结束时间为: " + ComUtil.dateToString(temp.getEndTime(), format));
                return map;
            }
        }
        headMapper.insertSelective(head);
        Map<String, Object> map = new HashMap<>();
        map.put("code", head.getCode());
        map.put("message", "success");
        return map;
    }

    @Override
    public String saveOrCommit(Long id, List<ProductStorageStatisticDataDetails> details, Integer flag) {
        if (flag == 0) {
            save(id, details);
            return "操作成功";
        } else {
            commit(id, details);
            return "操作成功";
        }
    }

    @Override
    public void delete(Long id) {
        deleteCascade(id);
        headMapper.deleteByPrimaryKey(id);
    }

    private void deleteCascade(Long id) {
        ProductStorageStatisticByLineDetailExample example = new ProductStorageStatisticByLineDetailExample();
        example.createCriteria().andStatisticCodeEqualTo(id);
        lineDetailMapper.deleteByExample(example);

        ProductStorageStatisticByLineTotalsExample example1 = new ProductStorageStatisticByLineTotalsExample();
        example1.createCriteria().andStatisticCodeEqualTo(id);
        lineTotalsMapper.deleteByExample(example1);

        ProductStorageStatisticDataDetailsExample example2 = new ProductStorageStatisticDataDetailsExample();
        example2.createCriteria().andStatisticCodeEqualTo(id);
        dataDetailsMapper.deleteByExample(example2);

        ProductStorageStatisticDataListExample example3 = new ProductStorageStatisticDataListExample();
        example3.createCriteria().andStatisticCodeEqualTo(id);
        listMapper.deleteByExample(example3);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for (Long id : ids) {
            delete(id);
        }
    }

    private void save(Long id, List<ProductStorageStatisticDataDetails> details) {
        ProductStorageStatisticHead head = headMapper.selectByPrimaryKey(id);
        deleteCascade(id);

        for (int i = 0; i < details.size(); i++) {
            ProductStorageStatisticDataDetails data = completeDetails(details.get(i));
            data.setStatisticCode(id);
            dataDetailsMapper.insertSelective(data);
        }
    }

    private void commit(Long id, List<ProductStorageStatisticDataDetails> details) {
        save(id, details);
        ProductStorageStatisticHead head = headMapper.selectByPrimaryKey(id);
        head.setFlag(new Integer(1).byteValue());
        headMapper.updateByPrimaryKeySelective(head);

        ProductStorageStatisticDataDetailsExample example = new ProductStorageStatisticDataDetailsExample();
        example.createCriteria().andStatisticCodeEqualTo(id);
        List<ProductStorageStatisticDataDetails> datas = dataDetailsMapper.selectByExample(example);
        Map<String, ProductStorageStatisticDataList> map = new HashMap<>();
        Float total = 0f;
        Float ni = 0f;
        Float co = 0f;
        Float mn = 0f;
        for (int i = 0; i < datas.size(); i++) {
            ProductStorageStatisticDataDetails data = datas.get(i);
            String key = data.getProductionTypeCode() + "," + data.getProductionTypeName();
            if (!map.containsKey(key)) {
                ProductStorageStatisticDataList list = new ProductStorageStatisticDataList();
                list.setCoValue(datas.get(i).getCoMetallicity());
                list.setMnValue(datas.get(i).getMnMetallicity());
                list.setNiValue(datas.get(i).getNiMetallicity());
                list.setTotals(datas.get(i).getWeights());
                list.setStatisticCode(id);
                list.setPeriods(head.getLineName());
                list.setProductionTypeCode(data.getProductionTypeCode());
                list.setProductionTypeName(data.getProductionTypeName());
                map.put(key, list);
            } else {
                ProductStorageStatisticDataList list = map.get(key);
                list.setCoValue(list.getCoValue() + data.getCoMetallicity());
                list.setMnValue(list.getMnValue() + data.getMnMetallicity());
                list.setNiValue(list.getNiValue() + data.getNiMetallicity());
                list.setTotals(list.getTotals() + data.getWeights());
                map.put(key, list);
            }
            total += data.getWeights();
            ni += data.getNiMetallicity();
            co += data.getCoMetallicity();
            mn += data.getMnMetallicity();
        }
        //插总统计表
        ProductStorageStatisticByLineTotals totals = new ProductStorageStatisticByLineTotals();
        totals.setCoValue(co);
        totals.setMnValue(mn);
        totals.setNiValue(ni);
        totals.setTotals(total);
        totals.setStatisticCode(id);
        totals.setPeriods(head.getLineName());
        lineTotalsMapper.insertSelective(totals);

        //为了已统计详情，插表list
        map.forEach((key, value) -> {
            String[] str = key.split(",");
            ProductStorageStatisticDataList list = new ProductStorageStatisticDataList();
            list.setProductionTypeCode(str[0]);
            list.setProductionTypeName(str[1]);
            list.setStatisticCode(value.getStatisticCode());
            list.setTotals(value.getTotals());
            list.setNiValue(value.getNiValue());
            list.setCoValue(value.getCoValue());
            list.setMnValue(value.getMnValue());
            list.setPeriods(value.getPeriods());
            listMapper.insertSelective(list);
        });

        //按产线统计
        BasicInfoPrecursorProductionLineExample example1 = new BasicInfoPrecursorProductionLineExample();
        example1.createCriteria();
        List<BasicInfoPrecursorProductionLine> lines = lineMapper.selectByExample(example1);

        for (int i = 0; i < lines.size(); i++) {
            ProductStorageStatisticDataDetailsExample example2 = new ProductStorageStatisticDataDetailsExample();
            example2.createCriteria().andLineCodeEqualTo(lines.get(i).getCode()).andStatisticCodeEqualTo(id);
            List<ProductStorageStatisticDataDetails> dataDetails = dataDetailsMapper.selectByExample(example2);
            Float t = 0f;
            Float n = 0f;
            Float c = 0f;
            Float m = 0f;
            for (int l = 0; l < dataDetails.size(); l++) {
                t += dataDetails.get(l).getWeights();
                n += dataDetails.get(l).getNiMetallicity();
                c += dataDetails.get(l).getCoMetallicity();
                m += dataDetails.get(l).getMnMetallicity();
            }
            ProductStorageStatisticByLineDetail lineDetail = new ProductStorageStatisticByLineDetail();
            lineDetail.setStatisticCode(id);
            lineDetail.setPeriods(head.getLineName());
            lineDetail.setLineCode(lines.get(i).getCode());
            lineDetail.setTotals(t);
            lineDetail.setNiValue(n);
            lineDetail.setCoValue(c);
            lineDetail.setMnValue(m);
            lineDetailMapper.insertSelective(lineDetail);
        }

    }

    private ProductStorageStatisticDataDetails completeDetails(ProductStorageStatisticDataDetails detail) {
        String batch = detail.getBatch();

        ProductionBatchInfoExample example = new ProductionBatchInfoExample();
        example.createCriteria().andBatchEqualTo(batch);
        List<ProductionBatchInfo> infos = batchInfoMapper.selectByExample(example);
        ProductionBatchInfo info = infos.get(0);
        detail.setProductionTypeCode(info.getProductionModel());

        String line = info.getProductionLine();
        if (line.contains("1")) {
            detail.setLineCode(1);
        } else if (line.contains("2")) {
            detail.setLineCode(2);
        } else if (line.contains("3")) {
            detail.setLineCode(3);
        } else if (line.contains("4")) {
            detail.setLineCode(4);
        }
        detail.setStorageTime(new Date());

        Float weight = detail.getWeights();
        detail.setNiMetallicity(weight * detail.getNiConcentration());
        detail.setCoMetallicity(weight * detail.getCoConcentration());
        detail.setMnMetallicity(weight * detail.getMnConcentration());

        return detail;
    }

    @Override
    public Page pageUmCommit(ProductStorageStatisticHead head, Integer page, Integer size) {
        Date start = head.getStartTime();
        Date end = head.getEndTime();
        Integer period = head.getPeriodCode();
        ProductStorageStatisticHeadExample example = new ProductStorageStatisticHeadExample();
        if (start != null) {
            example.createCriteria().andStartTimeBetween(start, end).andPeriodCodeEqualTo(period).andFlagEqualTo(new Integer(0).byteValue());
        } else {
            example.createCriteria().andPeriodCodeEqualTo(period).andFlagEqualTo(new Integer(0).byteValue());
        }
        example.setOrderByClause("line_name desc");
        List<ProductStorageStatisticHead> heads = headMapper.selectByExample(example);
        List<ProductStorageHeadDTO> headDTOS = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            ProductStorageHeadDTO headDTO = new ProductStorageHeadDTO();
            headDTO.setHead(heads.get(i));
            headDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
            headDTOS.add(headDTO);
        }
        return new Page(headDTOS, page, size);
    }

    @Override
    public Page pageCommit(ProductStorageStatisticHead head, Integer page, Integer size) {
        List<ProductStorageDetailDTO> ans = new ArrayList<>();
        Date start = head.getStartTime();
        Date end = head.getEndTime();
        Integer period = head.getPeriodCode();
        ProductStorageStatisticHeadExample example = new ProductStorageStatisticHeadExample();
        if (start != null) {
            example.createCriteria().andStartTimeBetween(start, end).andPeriodCodeEqualTo(period).andFlagEqualTo(new Integer(1).byteValue());
        } else {
            example.createCriteria().andPeriodCodeEqualTo(period).andFlagEqualTo(new Integer(1).byteValue());
        }
        example.setOrderByClause("line_name desc");
        List<ProductStorageStatisticHead> heads = headMapper.selectByExample(example);
        for (int i = 0; i < heads.size(); i++) {
            ProductStorageStatisticDataListExample example1 = new ProductStorageStatisticDataListExample();
            example1.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
            List<ProductStorageStatisticDataList> lists = listMapper.selectByExample(example1);
            for (int l = 0; l < lists.size(); l++) {
                ProductStorageDetailDTO detailDTO = new ProductStorageDetailDTO();
                detailDTO.setHead(heads.get(i));
                detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
                detailDTO.setList(lists.get(l));
                ans.add(detailDTO);
            }
        }
        return new Page(ans, page, size);
    }

    @Override
    public ProductStorageDetailDTO detail(Integer id) {
        ProductStorageDetailDTO ans = new ProductStorageDetailDTO();
        ProductStorageStatisticDataList list = listMapper.selectByPrimaryKey(id);
        Long headId = list.getStatisticCode();

        ans.setHead(headMapper.selectByPrimaryKey(headId));
        ans.setPeriodName(periodMapper.selectByPrimaryKey(ans.getHead().getPeriodCode()).getName());
        ans.setList(list);

        ProductStorageStatisticDataDetailsExample example = new ProductStorageStatisticDataDetailsExample();
        example.createCriteria().andStatisticCodeEqualTo(headId).andProductionTypeCodeEqualTo(list.getProductionTypeCode()).andProductionTypeNameEqualTo(list.getProductionTypeName());
        ans.setDataDetails(dataDetailsMapper.selectByExample(example));

        ProductStorageStatisticByLineTotalsExample example1 = new ProductStorageStatisticByLineTotalsExample();
        example1.createCriteria().andStatisticCodeEqualTo(headId);
        ans.setTotals(lineTotalsMapper.selectByExample(example1).get(0));

        return ans;
    }

    @Override
    public Map stastaicByLine(ProductStorageStatisticHead head) {
        Map<String, Object> ans = new HashMap<>();

        ProductStorageStatisticHeadExample example = new ProductStorageStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andStartTimeEqualTo(head.getStartTime()).andFlagEqualTo(new Integer(1).byteValue());
        List<ProductStorageStatisticHead> heads = headMapper.selectByExample(example);

        if (heads.size() == 0)
            return null;

        BasicInfoPrecursorProductionLineExample example1 = new BasicInfoPrecursorProductionLineExample();
        example1.createCriteria();
        List<BasicInfoPrecursorProductionLine> lines = lineMapper.selectByExample(example1);
        List<ProductStorageStatisticDTO> list = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            ProductStorageStatisticByLineDetailExample example2 = new ProductStorageStatisticByLineDetailExample();
            example2.createCriteria().andLineCodeEqualTo(lines.get(i).getCode()).andStatisticCodeEqualTo(heads.get(0).getCode());
            List<ProductStorageStatisticByLineDetail> details = lineDetailMapper.selectByExample(example2);
            ProductStorageStatisticDTO storageStatisticDTO = new ProductStorageStatisticDTO();
            storageStatisticDTO.setHead(heads.get(0));
            storageStatisticDTO.setLineDetail(details.get(0));
            storageStatisticDTO.setlName(lines.get(i).getName());
            storageStatisticDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(0).getPeriodCode()).getName());
            list.add(storageStatisticDTO);
        }
        ProductStorageStatisticByLineTotalsExample example2 = new ProductStorageStatisticByLineTotalsExample();
        example2.createCriteria().andStatisticCodeEqualTo(heads.get(0).getCode());
        List<ProductStorageStatisticByLineTotals> totals = lineTotalsMapper.selectByExample(example2);

        ans.put("list", list);
        ans.put("total", totals.get(0));
        return ans;
    }

    @Override
    public List periodCur(Integer lineId, ProductStorageStatisticHead head) {
        List<GoodInCompareDTO> ans = new ArrayList<>();

        ProductStorageStatisticHeadExample example = new ProductStorageStatisticHeadExample();
        example.createCriteria().andStartTimeBetween(head.getStartTime(), head.getEndTime()).andPeriodCodeEqualTo(head.getPeriodCode()).andFlagEqualTo(new Integer(1).byteValue());
        List<ProductStorageStatisticHead> heads = headMapper.selectByExample(example);

        for (int i = 0; i < heads.size(); i++) {
            GoodInCompareDTO compareDTO = new GoodInCompareDTO();
            ProductStorageStatisticByLineDetailExample example1 = new ProductStorageStatisticByLineDetailExample();
            example1.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode()).andLineCodeEqualTo(lineId);
            List<ProductStorageStatisticByLineDetail> details = lineDetailMapper.selectByExample(example1);
            compareDTO.setPeriodNum(heads.get(i).getLineName());
            compareDTO.setNi(details.get(0).getNiValue());
            compareDTO.setCo(details.get(0).getCoValue());
            compareDTO.setMn(details.get(0).getMnValue());
            ans.add(compareDTO);
        }
        return ans;
    }

    @Override
    public List lineCur(ProductStorageStatisticHead head, Integer[] lines) {
        ProductStorageStatisticHeadExample headExample = new ProductStorageStatisticHeadExample();
        headExample.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getLineName()).andFlagEqualTo(new Integer(1).byteValue());
        List<ProductStorageStatisticHead> heads = headMapper.selectByExample(headExample);
        head = heads.get(0);

        List<GoodInCompareDTO> ans = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            GoodInCompareDTO compareDTO = new GoodInCompareDTO();
            compareDTO.setLineName(lineMapper.selectByPrimaryKey(lines[i]).getName());
            ProductStorageStatisticByLineDetailExample example = new ProductStorageStatisticByLineDetailExample();
            example.createCriteria().andLineCodeEqualTo(lines[i]).andStatisticCodeEqualTo(head.getCode());
            List<ProductStorageStatisticByLineDetail> details = lineDetailMapper.selectByExample(example);
            compareDTO.setNi(details.get(0).getNiValue());
            compareDTO.setCo(details.get(0).getCoValue());
            compareDTO.setMn(details.get(0).getMnValue());
            ans.add(compareDTO);
        }
        return ans;
    }

    @Override
    public Map nextPeriod(Integer periodId) {
        ProductStorageStatisticHeadExample example = new ProductStorageStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andFlagEqualTo(new Integer(1).byteValue());
        List<ProductStorageStatisticHead> heads = headMapper.selectByExample(example);
        Integer max = 0;
        Date end = null;
        for (int i = 0; i < heads.size(); i++) {
            if (max < heads.get(i).getLineName()) {
                max = heads.get(i).getLineName();
                end = heads.get(i).getEndTime();
            }
        }
        Map<String, Object> ans = new HashMap<>();
        ans.put("period", max + 1);
        ans.put("endTime", end==null?null:ComUtil.dateToString(end,"yyyy-MM-dd"));
        return ans;
    }

    @Override
    public ProductStorageEditDTO edit(Long id) {
        ProductStorageEditDTO ans = new ProductStorageEditDTO();
        ans.setHead(headMapper.selectByPrimaryKey(id));
        ans.setPeriodName(periodMapper.selectByPrimaryKey(ans.getHead().getPeriodCode()).getName());
        ProductStorageStatisticDataDetailsExample example = new ProductStorageStatisticDataDetailsExample();
        example.createCriteria().andStatisticCodeEqualTo(id);
        ans.setPageInfo(dataDetailsMapper.selectByExample(example));
        return ans;
    }

    @Override
    public List getDate(Integer periodId) {
        List<String> ans = new ArrayList<>();
        ProductStorageStatisticHeadExample example = new ProductStorageStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andFlagEqualTo(new Integer(1).byteValue());
        List<ProductStorageStatisticHead> heads = headMapper.selectByExample(example);
        for (int i = 0; i < heads.size(); i++) {
            ans.add(ComUtil.dateToString(heads.get(i).getStartTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        return ans;
    }

    @Override
    public List getPeriodAndTime(Integer periodId) {
        ProductStorageStatisticHeadExample example = new ProductStorageStatisticHeadExample();
        example.createCriteria().andFlagEqualTo(new Integer(1).byteValue()).andPeriodCodeEqualTo(periodId);
        return headMapper.selectByExample(example);
    }

    @Override
    public Page defaultPage() {
        List<ProductStorageDetailDTO> ans = new ArrayList<>();
        ProductStorageStatisticHeadExample example = new ProductStorageStatisticHeadExample();
        example.createCriteria().andFlagEqualTo(new Integer(1).byteValue());
        List<ProductStorageStatisticHead> heads = headMapper.selectByExample(example);
        if (heads.size() == 0)
            return new Page(new ArrayList(), 1, 10);
        ProductStorageStatisticHead head = heads.get(0);
        for (int i = 1; i < heads.size(); i++) {
            if (head.getLineName() < heads.get(i).getLineName())
                head = heads.get(i);
        }
        ProductStorageStatisticDataListExample example1 = new ProductStorageStatisticDataListExample();
        example1.createCriteria().andStatisticCodeEqualTo(head.getCode());
        List<ProductStorageStatisticDataList> lists = listMapper.selectByExample(example1);
        for (int l = 0; l < lists.size(); l++) {
            ProductStorageDetailDTO detailDTO = new ProductStorageDetailDTO();
            detailDTO.setHead(head);
            detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
            detailDTO.setList(lists.get(l));
            ans.add(detailDTO);
        }
        return new Page(ans, 1, 10);
    }
}
