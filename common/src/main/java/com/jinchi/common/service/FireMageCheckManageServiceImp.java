package com.jinchi.common.service;

import com.jinchi.common.constant.AddressEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.fireMage.FireMageDetectInfoDTO;
import com.jinchi.common.dto.fireMage.FireMageTestItemsDTO;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import com.jinchi.common.utils.ExportUtil;
import com.jinchi.common.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class FireMageCheckManageServiceImp implements FireMageCheckManageService {

    @Autowired
    FireMageDetectInfoMapper infoMapper;
    @Autowired
    FireMageDeptMapper deptMapper;
    @Autowired
    FireMageTestItemsMapper itemsMapper;
    @Autowired
    FireMageBatchItemsValuesMapper itemsValuesMapper;
    @Autowired
    FireMageNumberProcessMapper processMapper;
    @Autowired
    FireMageNumberProductMapper productMapper;
    @Autowired
    FireMageLabelService labelService;

    @Override
    public void add(List<FireMageDetectInfo> infos, List<Long> ids) {
        StringBuilder codes = new StringBuilder();
        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();
        if (ids.size() != 0) {
            codes.append(ids.get(0));
            names.append(itemsMapper.selectByPrimaryKey(ids.get(0)).getName());
            values.append("-1");
            for (int i = 1; i < ids.size(); i++) {
                codes.append("," + ids.get(i));
                names.append("," + itemsMapper.selectByPrimaryKey(ids.get(i)).getName());
                values.append(",-1");
            }
        }

        for (int i = 0; i < infos.size(); i++) {
            FireMageDetectInfo info = infos.get(i);
            info.setCheckInTime(new Date());
            //info.setDetectStatus(new Integer(0).byteValue());
            info.setFlag(new Integer(0).byteValue());
            infoMapper.insertSelective(info);
            FireMageBatchItemsValues itemsValues = new FireMageBatchItemsValues();
            itemsValues.setBatchCode(info.getCode());
            itemsValues.setItemCodes(codes.toString());
            itemsValues.setItemNames(names.toString());
            itemsValues.setItemValues(values.toString());
            itemsValuesMapper.insertSelective(itemsValues);
        }
    }

    @Override
    public Boolean check(String batch) {
        FireMageDetectInfoExample example = new FireMageDetectInfoExample();
        example.createCriteria().andBatchEqualTo(batch);
        return infoMapper.selectByExample(example).size() == 0 ? true : false;
    }

    @Override
    public Page checkPage(String condition, String flag, Integer page, Integer size) {
        FireMageDetectInfoExample example = new FireMageDetectInfoExample();
        String sql = "";
        if(!flag.equals("-1")) {
            example.createCriteria().andBatchLike(condition + "%").andFlagEqualTo(Byte.parseByte(flag));
            sql = "select * from fire_mage_detect_info where batch like '" + condition + "%' and (flag = " + flag + ") order by code desc limit " + (page - 1) * size + "," + size;
        }else{
            example.createCriteria().andBatchLike(condition + "%");
            sql = "select * from fire_mage_detect_info where batch like '" + condition + "%' order by code desc limit " + (page - 1) * size + "," + size;
        }

        Integer total = infoMapper.countByExample(example);
        List<FireMageDetectInfo> infos = infoMapper.selectByCondition(sql);
        List<FireMageDetectInfoDTO> ans = new ArrayList<>();
        for (int i = 0; i < infos.size(); i++) {
            FireMageDetectInfoDTO info = new FireMageDetectInfoDTO();
            info.setHead(infos.get(i));
            FireMageDept dept = deptMapper.selectByPrimaryKey(infos.get(i).getDeptCode());
            String deptName = dept==null?"":dept.getDeptName();
            info.setDeptName(deptName);
            FireMageBatchItemsValuesExample example1 = new FireMageBatchItemsValuesExample();
            example1.createCriteria().andBatchCodeEqualTo(infos.get(i).getCode());
            List<FireMageBatchItemsValues> values = itemsValuesMapper.selectByExample(example1);
            info.setItemsSpace(values.get(0).getItemNames().replaceAll(",", " "));
            ans.add(info);
        }
        Page pageInfo = new Page(ans, 1, size);
        pageInfo.setPage(page);
        pageInfo.setTotal(total);
        return pageInfo;
    }

    @Override
    public FireMageDetectInfoDTO checkDetail(Long id) {
        FireMageDetectInfoDTO ans = new FireMageDetectInfoDTO();

        FireMageDetectInfo info = infoMapper.selectByPrimaryKey(id);
        if (info == null)
            return null;

        FireMageBatchItemsValuesExample example = new FireMageBatchItemsValuesExample();
        example.createCriteria().andBatchCodeEqualTo(id);
        FireMageBatchItemsValues itemsValues = itemsValuesMapper.selectByExample(example).get(0);
        List<String> containItems = Arrays.asList(itemsValues.getItemNames().split(","));
        List<String> containCodes = Arrays.asList(itemsValues.getItemCodes().split(","));
        FireMageDept dept = deptMapper.selectByPrimaryKey(info.getDeptCode());
        String deptName = dept==null?"":dept.getDeptName();
        ans.setDeptName(deptName);
        ans.setBatch(info.getBatch());
        ans.setCheckPeople(info.getDelieryPeople());

        List<FireMageTestItems> testItems = itemsMapper.selectByExample(new FireMageTestItemsExample());
        List<FireMageTestItemsDTO> itemsDTOS = new ArrayList<>();
        for (int i = 0; i < testItems.size(); i++) {
            FireMageTestItemsDTO itemsDTO = new FireMageTestItemsDTO();
            itemsDTO.setCode(testItems.get(i).getCode());
            itemsDTO.setName(testItems.get(i).getName());
            itemsDTO.setUnit(testItems.get(i).getUnit());
            itemsDTO.setFlag(false);
            if (containItems.contains(itemsDTO.getName()) && containCodes.contains("" + itemsDTO.getCode())) {
                itemsDTO.setFlag(true);
            }
            itemsDTOS.add(itemsDTO);
        }
        ans.setItems(itemsDTOS);
        return ans;
    }

    @Override
    public void delete(Long id) {
        FireMageBatchItemsValuesExample example = new FireMageBatchItemsValuesExample();
        example.createCriteria().andBatchCodeEqualTo(id);
        itemsValuesMapper.deleteByExample(example);

        infoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for (Long id : ids)
            delete(id);
    }

    @Override
    public List printLabels(Long id) {
        FireMageDetectInfo info = infoMapper.selectByPrimaryKey(id);

        FireMageBatchItemsValuesExample example = new FireMageBatchItemsValuesExample();
        example.createCriteria().andBatchCodeEqualTo(id);

        List<FireMageBatchItemsValues> items = itemsValuesMapper.selectByExample(example);
        if (items.size() == 0)
            return null;

        FireMageBatchItemsValues item = items.get(0);
        List<String> itemNames = Arrays.asList(item.getItemNames().split(","));
        List<FireMageLabelItems> labels = labelService.getAll("");

        List<Map> ans = new ArrayList<>();
        for (int i = 0; i < labels.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("送检时间", ComUtil.dateToString(info.getCheckInTime(), "yyyy-MM-dd HH:mm:ss"));
            List<String> labelItems = Arrays.asList(labels.get(i).getItemNames().split(","));
            List<String> temp = new ArrayList<>();
            for (int l = 0; l < itemNames.size(); l++) {
                if (labelItems.contains(itemNames.get(l)))
                    temp.add(itemNames.get(l));
            }
            map.put("检验项目", listToString(temp));
            map.put("批号",info.getBatch());
            ans.add(map);
        }
        return ans;
    }

    @Override
    public void acceptOrReject(Long id, Integer flag,String reason) {
        FireMageDetectInfo info = infoMapper.selectByPrimaryKey(id);
        info.setComfirmTime(new Date());
        info.setFlag(new Integer(flag).byteValue());
        if(flag == 2)
            info.setComment(reason);
        infoMapper.updateByPrimaryKey(info);
    }

    @Override
    public Page collectionPage(String time, Integer page, Integer size) {
        String start = "";
        String end = "";
        if ("".equals(time)) {
            time = ComUtil.dateToString(new Date(), "yyyy-MM-dd");
        }
        start += (time + " 00:00:00");
        end += (time + " 23:59:59");

        String sql = "select * from fire_mage_detect_info where check_in_time >= '" + start + "' and check_in_time <= '" + end + "' and flag = 1  order by code desc limit " + (page - 1) * size + "," + size;
        List<FireMageDetectInfo> infos = infoMapper.selectByTime(sql);
        List<FireMageDetectInfoDTO> ans = new ArrayList<>();
        for (int i = 0; i < infos.size(); i++) {
            FireMageDetectInfoDTO infoDTO = new FireMageDetectInfoDTO();
            infoDTO.setHead(infos.get(i));
            FireMageBatchItemsValues values = itemsValuesMapper.getByBatchCode(infos.get(i).getCode());
            infoDTO.setItemsSpace(values.getItemNames().replaceAll(",", " "));
            ans.add(infoDTO);
        }

        Date startTime = ComUtil.getDate(start, "yyyy-MM-dd HH:mm:ss");
        Date endTime = ComUtil.getDate(end, "yyyy-MM-dd HH:mm:ss");
        FireMageDetectInfoExample example = new FireMageDetectInfoExample();
        example.createCriteria().andCheckInTimeBetween(startTime, endTime).andFlagEqualTo(new Integer(1).byteValue());
        Integer total = infoMapper.countByExample(example);
        Page pageInfo = new Page(ans, 1, size);
        pageInfo.setPage(page);
        pageInfo.setTotal(total);
        return pageInfo;
    }

    @Override
    public List getByProcessByProduct(Integer process, Integer product, String time) {
        FireMageNumberProcess pc = processMapper.selectByPrimaryKey(process);
        FireMageNumberProduct pd = productMapper.selectByPrimaryKey(product);
        String start = "";
        String end = "";
        if ("".equals(time)) {
            time = ComUtil.dateToString(new Date(), "yyyy-MM-dd");
        }
        start += (time + " 00:00:00");
        end += (time + " 23:59:59");


        Date startTime = ComUtil.getDate(start, "yyyy-MM-dd HH:mm:ss");
        Date endTime = ComUtil.getDate(end, "yyyy-MM-dd HH:mm:ss");

        FireMageDetectInfoExample example = new FireMageDetectInfoExample();
        FireMageDetectInfoExample.Criteria criteria = example.createCriteria();
        criteria.andCheckInTimeBetween(startTime, endTime).andFlagEqualTo(new Integer(1).byteValue());
        if (pc != null) {
            criteria.andProcessEqualTo(pc.getValue());
        }
        if (pd != null) {
            criteria.andProductEqualTo(pd.getValue());
        }
        return infoMapper.selectByExample(example);
    }

    @Override
    public String export(List<String> batches, List<String> itemNames) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < batches.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("批号", batches.get(i));
            for (int l = 0; l < itemNames.size(); l++) {
                map.put(itemNames.get(l), "");
            }
            mapList.add(map);
        }
        return AddressEnum.EXCEL_FIRE_MAGE.getPath() + ExportUtil.fireMgaeCExport(mapList, AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()),itemNames);
    }

    @Override
    public String importMethod(MultipartFile excelFile, List<Long> items) throws IOException {
        String file = "";
        String filePath = "";
        if (excelFile.getOriginalFilename().endsWith(".xls")) {
            file += UploadUtil.upload(AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()), excelFile, 10, ".xls");
            filePath += (AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()) + file);
        } else if (excelFile.getOriginalFilename().endsWith(".xlsx")) {
            file += UploadUtil.upload(AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()), excelFile, 10, ".xlsx");
            filePath += (AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()) + file);
        } else {
            return "文件类型错误";
        }
        List<String> heads = ExportUtil.getExcelHead(filePath);
        Integer cnt = 0;

        List<String> itemNames = new ArrayList<>();
        for(int i=0;i<items.size();i++){
            itemNames.add(itemsMapper.selectByPrimaryKey(items.get(i)).getName());
        }

        for (int i = 0; i < itemNames.size(); i++) {
            for (int l = 0; l < heads.size(); l++) {
                if (heads.get(l).contains(itemNames.get(i))) {
                    cnt++;
                    break;
                }
            }
        }
        if (cnt != itemNames.size())
            return "存在不一致的检测项目，请检查excel表头所选项目是否包含与所选检测项目";
        List<Map<String, Object>> infos = ExportUtil.getExcelInfo(filePath, heads);
        for (int i = 0; i < infos.size(); i++) {
            Map<String, Object> info = infos.get(i);
            String batch = info.get("批号").toString();
            FireMageDetectInfo detectInfo = infoMapper.getByBatch(batch);
            if (detectInfo == null)
                continue;
            FireMageBatchItemsValues itemsValues = itemsValuesMapper.getByBatchCode(detectInfo.getCode());
            if (null == itemsValues)
                continue;
            List<String> names = Arrays.asList(itemsValues.getItemNames().split(","));
            List<String> values = Arrays.asList(itemsValues.getItemValues().split(","));
            for (String key : info.keySet()) {
                if (!"批号".equals(key)) {
                    for (int l = 0; l < names.size(); l++) {
                        if (names.get(l).equals(key)) {
                            if (!info.get(key).toString().equals("")) {
                                values.set(l, info.get(key).toString());
                            }
                        }
                    }
                }
            }
            itemsValues.setItemValues(listToString(values));
            itemsValuesMapper.updateByPrimaryKeySelective(itemsValues);
        }
        return "操作成功";
    }

    private String listToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        if (list.size() == 0)
            return sb.toString();
        sb.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            sb.append("," + list.get(i));
        }
        return sb.toString();
    }

    @Override
    public String reExport(List<String> batches) {
        List<String> head = new ArrayList<>();
        head.add("批号");

        List<FireMageTestItems> items = itemsMapper.selectByExample(new FireMageTestItemsExample());
        for (int i = 0; i < items.size(); i++) {
            head.add(items.get(i).getName());
        }
        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < batches.size(); i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("批号", batches.get(i));
            FireMageDetectInfo info = infoMapper.getByBatch(batches.get(i));
            String detectaInfo = "";
            for (int l = 1; l < head.size(); l++)
                data.put(head.get(l), "");
            if(info.getDetectStatus() == null){
                data.put("检验状态","");
            }else {
                if (info.getDetectStatus().intValue() == 0) {
                    detectaInfo = "合格";
                }
                if (info.getDetectStatus().intValue() == 1) {
                    detectaInfo = "不合格";
                }
                if (info.getDetectStatus().intValue() == 2) {
                    detectaInfo = "让步接收";
                }
                data.put("检验状态", detectaInfo);
            }
            FireMageBatchItemsValues itemsValues = itemsValuesMapper.getByBatchCode(info.getCode());
            List<String> names = Arrays.asList(itemsValues.getItemNames().split(","));
            List<String> values = Arrays.asList(itemsValues.getItemValues().split(","));
            for (int l = 0; l < names.size(); l++) {
                for (int k = 1; k < head.size(); k++) {
                    if (head.get(k).contains(names.get(l)) && data.get(head.get(k)).equals("")) {
                        if (!values.get(l).equals("-1"))
                            data.put(head.get(k), values.get(l));
                        break;
                    }
                }
            }
            datas.add(data);
        }

        return AddressEnum.EXCEL_FIRE_MAGE.getPath() + ExportUtil.fireMgaeExport(datas, AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()),items);
    }

    @Override
    public String reImport(MultipartFile excel) throws IOException {
        String file = "";
        String filePath = "";
        if (excel.getOriginalFilename().endsWith(".xls")) {
            file += UploadUtil.upload(AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()), excel, 10, ".xls");
            filePath += (AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()) + file);
        } else if (excel.getOriginalFilename().endsWith(".xlsx")) {
            file += UploadUtil.upload(AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()), excel, 10, ".xlsx");
            filePath += (AddressEnum.getCurrentPath(AddressEnum.EXCEL_FIRE_MAGE.getCode()) + file);
        } else {
            return "文件类型错误";
        }
        List<String> heads = ExportUtil.getExcelHead(filePath);
        List<Map<String, Object>> infos = ExportUtil.getExcelInfo(filePath, heads);
        for (int i = 0; i < infos.size(); i++) {
            Map<String, Object> info = infos.get(i);
            String batch = info.get("批号").toString();
            String status = info.get("检验状态").toString();
            FireMageDetectInfo detectInfo = infoMapper.getByBatch(batch);
            if (detectInfo == null)
                continue;
            if ("合格".equals(status)) {
                detectInfo.setDetectStatus(new Integer(0).byteValue());
            } else if ("不合格".equals(status)) {
                detectInfo.setDetectStatus(new Integer(1).byteValue());
            } else if ("让步接收".equals(status)) {
                detectInfo.setDetectStatus(new Integer(2).byteValue());
            }
            infoMapper.updateByPrimaryKeySelective(detectInfo);
        }
        return "操作成功";
    }

    @Override
    public Page rePage(Integer deptCode, String process, String product, String date, Integer page, Integer size) {
        if("".equals(process))
            process = null;
        if("".equals(product)){
            product=null;
        }
        if("".equals(date)){
            date=null;
        }
        Date now = new Date();
        String start = ComUtil.dateToString(now, "yyyy-MM-dd") + " 00:00:00";
        String end = ComUtil.dateToString(now, "yyyy-MM_dd") + " 23:59:59";
        List<FireMageDetectInfo> infos = new ArrayList<>();
        Integer total = null;
        if (deptCode == null && process == null && process == null && product == null && null == date) {
            LocalDateTime dateTime = ComUtil.dateToLocalDateTime(now);
            Date startDate = ComUtil.localDateTimeToDate(dateTime.withHour(0).withMinute(0).withSecond(0));
            Date endDate = ComUtil.localDateTimeToDate(dateTime.withHour(23).withMinute(59).withSecond(39));
            String sql = "select * from fire_mage_detect_info where check_in_time >= '" + start + "' and check_in_time <= '" + end + "' and flag = 1  order by code desc limit " + (page - 1) * size + "," + size;
            FireMageDetectInfoExample example = new FireMageDetectInfoExample();
            example.createCriteria().andCheckInTimeBetween(startDate, endDate).andFlagEqualTo(new Integer(1).byteValue());
            infos = infoMapper.selectByTime(sql);
            total = infoMapper.countByExample(example);
        } else {
            if (date != null) {
                start = date + " 00:00:00";
                end = date + " 23:59:59";
                infos = infoMapper.select(deptCode, process, product, start, end, (page - 1) * size, page * size);
                total = infoMapper.count(deptCode, process, product, start, end);
            }else{
                infos = infoMapper.select(deptCode, process, product, null, null, (page - 1) * size, page * size);
                total = infoMapper.count(deptCode, process, product, null, null);
            }

        }
        List<FireMageDetectInfoDTO> ans = new ArrayList<>();

        for(int i=0;i<infos.size();i++){
            FireMageDetectInfo info = infos.get(i);
            FireMageDetectInfoDTO infoDTO = new FireMageDetectInfoDTO();
            infoDTO.setHead(info);
            if("0".equals(""+info.getDetectStatus())){
                infoDTO.setStatus("合格");
            }
            if("1".equals(""+info.getDetectStatus())){
                infoDTO.setStatus("不合格");
            }
            if("2".equals(""+info.getDetectStatus())){
                infoDTO.setStatus("让步接收");
            }
            FireMageBatchItemsValues itemsValues = itemsValuesMapper.getByBatchCode(info.getCode());
            List<String> names = Arrays.asList(itemsValues.getItemNames().split(","));
            infoDTO.setItemsSpace(listToString(names));
            ans.add(infoDTO);
        }
        Page pageInfo = new Page(ans,1,size);
        pageInfo.setTotal(total);
        pageInfo.setPage(page);
        return pageInfo;
    }

    @Override
    public FireMageDetectInfoDTO reDetail(Long id) {
        FireMageDetectInfo info = infoMapper.selectByPrimaryKey(id);
        FireMageBatchItemsValues itemsValues = itemsValuesMapper.getByBatchCode(info.getCode());
        FireMageDetectInfoDTO ans = new FireMageDetectInfoDTO();
        ans.setHead(info);

        String[] codes = itemsValues.getItemCodes().split(",");
        String[] names = itemsValues.getItemNames().split(",");
        String[] values = itemsValues.getItemValues().split(",");
        List<FireMageTestItemsDTO> itemsDTOS = new ArrayList<>();
        for (int i = 0; i < codes.length; i++) {
            FireMageTestItemsDTO itemsDTO = new FireMageTestItemsDTO();
            itemsDTO.setCode(Long.parseLong(codes[i]));
            itemsDTO.setName(names[i]);
            if (values[i].equals("-1")) {
                itemsDTO.setValues(null);
            } else {
                itemsDTO.setValues(values[i]);
            }
            itemsDTOS.add(itemsDTO);
        }
        ans.setItems(itemsDTOS);
        return ans;
    }

    @Override
    public void reUpdate(Long id, List<Long> itemsCodes, List<Float> values) {
        FireMageDetectInfo info = infoMapper.selectByPrimaryKey(id);
        FireMageBatchItemsValues itemsValues = itemsValuesMapper.getByBatchCode(info.getCode());

        List<String> codes = Arrays.asList(itemsValues.getItemCodes().split(","));
        List<String> iValues = Arrays.asList(itemsValues.getItemValues().split(","));

        for (int i = 0; i < itemsCodes.size(); i++) {
            for (int j = 0; j < codes.size(); j++) {
                if (itemsCodes.get(i).equals(codes.get(j))) {
                    if(values.get(i) != null && !"".equals(values.get(i))) {
                        iValues.set(j, values.get(i).toString());
                        break;
                    }
                }
            }
        }
        String newValues = listToString(iValues);
        itemsValues.setItemValues(newValues);
        itemsValuesMapper.updateByPrimaryKeySelective(itemsValues);
    }
}

