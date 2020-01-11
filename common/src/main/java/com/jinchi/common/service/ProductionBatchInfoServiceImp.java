package com.jinchi.common.service;


import com.jinchi.common.domain.*;
import com.jinchi.common.dto.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ProductionBatchInfoServiceImp implements ProductionBatchInfoService {

    @Autowired
    ProductionBatchInfoMapper productionBatchInfoMapper;
    @Autowired
    ProductionBatchRuleDetailMapper productionBatchRuleDetailMapper;
    @Autowired
    ProductionBatchRuleHeadMapper productionBatchRuleHeadMapper;
    @Autowired
    AuthUserMapper authUserMapper;
    @Autowired
    ProductionBatchRetrospectInfoMapper retrospectInfoMapper;
    @Autowired
    ProductionBatchInstrumentMapMapper instrumentMapMapper;
    @Autowired
    ProductionInstrumentDataTjMapper instrumentDataTjMapper;
    @Autowired
    ProductionBatchDeviceMapMapper batchDeviceMapMapper;
    @Autowired
    ProductionBatchAssayMapMapper batchAssayMapMapper;
    @Autowired
    ProductionInstrumentDataMapper instrumentDataMapper;
    @Autowired
    QualityCommonBatchNumberExtraMapper qualityCommonBatchNumberExtraMapper;
    @Autowired
    TestReportRecordMapper testReportRecordMapper;
    @Autowired
    ProductionProcessDeviceMapMapper processDeviceMapMapper;
    @Autowired
    DeviceRepairService deviceRepairService;
    @Autowired
    DeviceMaintenanceRecordHeadService deviceMaintenanceRecordHeadService;
    @Autowired
    DeviceDocumentManageService deviceDocumentManageService;
    @Autowired
    BasicInfoDeviceBatchProcessMapMapper mapMapper;
    @Autowired
    SampleDeliveringRecordMapper sampleDeliveringRecordMapper;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    TestItemMapper testItemMapper;
    @Autowired
    DeliveryFactoryService deliveryFactoryService;
    @Autowired
    PrecursorCompoundCellVolumesService volumesService;
    @Autowired
    BasicInfoBatchConfigService configService;

    @Override
    public ProductionInstrumentDataTj getById(Long id) {
        return instrumentDataTjMapper.selectByPrimaryKey(id);
    }

    @Override
    public List getByCondition(String cellNum, String startTime, String endTime) {
        String df = "yyyy-MM-dd HH:mm:ss";
        ProductionInstrumentDataExample example = new ProductionInstrumentDataExample();
        example.createCriteria().andCellNumEqualTo(cellNum).andSampleTimeBetween(ComUtil.getDate(startTime, df), ComUtil.getDate(endTime, df));
        return instrumentDataMapper.selectByExample(example);
    }

    @Override
    public BatchDataMapDTO getAllRecordsById(Long batchId) {
        BatchDataMapDTO batchDataMapDTO = new BatchDataMapDTO();

        ProductionBatchInstrumentMapExample example = new ProductionBatchInstrumentMapExample();
        example.createCriteria().andBatchCodeEqualTo(batchId);
        batchDataMapDTO.setBatchInstrumentMapList(instrumentMapMapper.selectByExample(example));

        ProductionBatchDeviceMapExample example1 = new ProductionBatchDeviceMapExample();
        example1.createCriteria().andBatchCodeEqualTo(batchId);
        batchDataMapDTO.setBatchDeviceMapList(batchDeviceMapMapper.selectByExample(example1));

        ProductionBatchAssayMapExample example2 = new ProductionBatchAssayMapExample();
        example2.createCriteria().andBatchCodeEqualTo(batchId);
        List<ProductionBatchAssayMap> assayMaps = batchAssayMapMapper.selectByExample(example2);

        List<ProductionBatchAssayMapDTO> dtoList = new ArrayList<>();
        for (ProductionBatchAssayMap map : assayMaps) {
            ProductionBatchAssayMapDTO dto = new ProductionBatchAssayMapDTO();
            dto.setSampleCode(map.getSampleCode().intValue());
            dto.setBatch(map.getBatch());
            dto.setAssayTypeId(map.getType());
            if (map.getType() == 1) {
                dto.setAssayType("原材料");
            }
            if (map.getType() == 2) {
                dto.setAssayType("中间品");
            }
            if (map.getType() == 3) {
                dto.setAssayType("成品");
            }
            dto.setDeliveryPeople(authUserService.findById(map.getDelivererId()).getName());
            dto.setDeliveryDate(map.getSampleDeliveringDate());
            dto.setDeliveryFactoty(deliveryFactoryService.getById(map.getDeliveryFactoryId()).getName());
            String testItems = map.getTestItems();
            String[] split = testItems.split(",");
            String items = "";
            for (int i = 0; i < split.length - 1; i++) {
                items += testItemMapper.find(Integer.parseInt(split[i])).getName() + ",";
            }
            items += testItemMapper.find(Integer.parseInt(split[split.length - 1])).getName();
            dto.setTestItems(items);
            dtoList.add(dto);
        }

        batchDataMapDTO.setBatchAssayMapList(dtoList);
        return batchDataMapDTO;
    }

    @Override
    public Integer save(ProductionBatchInfoDateTostring productionBatchInfoDateTostring) {

        //batch  合并     // year      //Production_type      //month      //serialnumber     //Production_model     //Production_line    //material
        String w1 = productionBatchInfoDateTostring.getProductionBatchInfo().getYear();
        String w2 = productionBatchInfoDateTostring.getProductionBatchInfo().getProductionType();
        String w3 = productionBatchInfoDateTostring.getProductionBatchInfo().getMonth();
        String w4 = productionBatchInfoDateTostring.getProductionBatchInfo().getSerialNumber();
        String w5 = productionBatchInfoDateTostring.getProductionBatchInfo().getProductionModel();
        String w6 = productionBatchInfoDateTostring.getProductionBatchInfo().getProductionLine();
        String w7 = productionBatchInfoDateTostring.getProductionBatchInfo().getMaterial();
        String batch = w1 + w2 + w3 + w4 + w5 + w6 + w7;
        productionBatchInfoDateTostring.getProductionBatchInfo().setBatch(batch);
        //两位管理者   管理者前台传

//        String setTime = productionBatchInfoDateTostring.getSetTime();
//        String modifyTime = productionBatchInfoDateTostring.getModifyTime();
        String startTime = productionBatchInfoDateTostring.getStartTime();
//        String endTime = productionBatchInfoDateTostring.getEndTime();

        //判断batch是否已存在
        ProductionBatchInfoExample infoExample = new ProductionBatchInfoExample();
        infoExample.createCriteria().andBatchEqualTo(batch).andProcessEqualTo(productionBatchInfoDateTostring.getProductionBatchInfo().getProcess());
        List<ProductionBatchInfo> infos = productionBatchInfoMapper.selectByExample(infoExample);
        if (infos.size() > 0) {
            return -1;
        }

        //前端传的格式一定要这样
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date settime = new Date();
//        Date modifytime = new Date();
        Date starttime = null;
//        Date endtime = new Date();
        try {
//            settime = simpleDateFormat.parse(setTime);
//            modifytime = simpleDateFormat.parse(modifyTime);
            starttime = simpleDateFormat.parse(startTime);
//            endtime = simpleDateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //将获取的时间更新
        ProductionBatchInfo productionBatchInfo = productionBatchInfoDateTostring.getProductionBatchInfo();
        productionBatchInfo.setSetTime(settime);
//        productionBatchInfo.setModifyTime(modifytime);
        productionBatchInfo.setStartTime(starttime);
//        productionBatchInfo.setEndTime(endtime);
        productionBatchInfo.setEditFlag(productionBatchInfoDateTostring.getProductionBatchInfo().getEditFlag());

        productionBatchInfoMapper.insertSelective(productionBatchInfo);
        return 0;
    }

    @Override
    public Page getAllInfo(Integer page, Integer size) {

        ProductionBatchInfoExample productionBatchInfoExample = new ProductionBatchInfoExample();
        long temp = 0;
        productionBatchInfoExample.createCriteria().andCodeGreaterThan(temp);
        //主意改表之后的选取，  某一个字段为零的才选取出来***********************************************************************************************************************
        List<ProductionBatchInfo> productionBatchInfo = productionBatchInfoMapper.selectByExample(productionBatchInfoExample);
        return dealSort(productionBatchInfo, page, size);

    }

    public Page dealSort(List<ProductionBatchInfo> productionBatchInfo, Integer page, Integer size) {
        ProductionBatchInfoExample productionBatchInfoExample = new ProductionBatchInfoExample();
        productionBatchInfo.sort((ProductionBatchInfo o1, ProductionBatchInfo o2) -> (o1.getStartTime().getTime() - o2.getStartTime().getTime()) >= 0 ? 1 : -1);
//        new Comparator<ProductionBatchInfo>() {
//            @Override
//            public int compare(ProductionBatchInfo o1, ProductionBatchInfo o2) {
//                return (o1.getStartTime().getTime() - o2.getStartTime().getTime() ) >= 0 ?  1 : -1;
//            }
//        }
        //前端要求要按照 batch分类
        Set<String> con = new HashSet<>();
        for (ProductionBatchInfo p : productionBatchInfo) {
            con.add(p.getBatch());
        }
        //这里不用 con是否包含， 因为要重写  equals 和  hashcode
        //对于每一种bach信息
        List<ProductionBatchInfo> newProductionBatchInfo = new ArrayList<>();
        for (String str : con) {
            //如果有这个batch，放在一起   equals  只比较字符串string   而   contentEquals 还会比较  buffer的字符串
            for (ProductionBatchInfo p : productionBatchInfo) {
                if (str.contentEquals(p.getBatch()))
                    newProductionBatchInfo.add(p);
            }
        }
        List<ProductionBatchInfoDateTostring> productionBatchInfoDateTostrings = new ArrayList<>();
        //前端要求： 格式要求， 开一个dto出来
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ProductionBatchInfo p : newProductionBatchInfo) {
            ProductionBatchInfoDateTostring productionBatchInfoDateTostring = new ProductionBatchInfoDateTostring();
            productionBatchInfoDateTostring.setProductionBatchInfo(p);
//            productionBatchInfoDateTostring.setEndTime(format.format(p.getEndTime()));
//            productionBatchInfoDateTostring.setModifyTime(format.format(p.getModifyTime()));
            productionBatchInfoDateTostring.setSetTime(format.format(p.getSetTime()));
            productionBatchInfoDateTostring.setStartTime(format.format(p.getStartTime()));
            productionBatchInfoDateTostrings.add(productionBatchInfoDateTostring);
        }
        Page<ProductionBatchInfoDateTostring> pageInfo = new Page<>(productionBatchInfoDateTostrings, page, size);
        return pageInfo;
    }

    @Override
    public ProductionBatchRuleDetailsDTO GetAllBatchRuleData() {
        ProductionBatchRuleDetailsDTO productionBatchRuleDetailsDTO = new ProductionBatchRuleDetailsDTO();
        //规定默认是10条的，如果多于10条， 此接口无效
        ProductionBatchRuleHeadExample productionBatchRuleHeadExample = new ProductionBatchRuleHeadExample();
        productionBatchRuleHeadExample.createCriteria();
        List<ProductionBatchRuleHead> productionBatchRuleHeads = productionBatchRuleHeadMapper.selectByExample(productionBatchRuleHeadExample);
        //获取所有头表的code
        for (ProductionBatchRuleHead p : productionBatchRuleHeads) {
            Byte code = p.getCode();
            //找到当前code的所有规则
            ProductionBatchRuleDetailExample productionBatchRuleDetailExample = new ProductionBatchRuleDetailExample();
            productionBatchRuleDetailExample.createCriteria().andRuleCodeEqualTo(code);
            List<ProductionBatchRuleDetail> productionBatchRuleDetails = productionBatchRuleDetailMapper.selectByExample(productionBatchRuleDetailExample);
            List<String> temp = new ArrayList<>();
            for (ProductionBatchRuleDetail pro : productionBatchRuleDetails) {
                temp.add(pro.getRuleValue());
            }
            switch (p.getRuleName()) {
                case "年份":
                    productionBatchRuleDetailsDTO.setYear(temp);
                case "产品类型":
                    productionBatchRuleDetailsDTO.setProductionType(temp);
                case "月份":
                    productionBatchRuleDetailsDTO.setMonth(temp);
                case "流水号":
                    productionBatchRuleDetailsDTO.setSerialNumber(temp);
                case "产品型号":
                    productionBatchRuleDetailsDTO.setProductionModel(temp);
                case "生产线":
                    productionBatchRuleDetailsDTO.setProductionLine(temp);
                case "原材料类型":
                    productionBatchRuleDetailsDTO.setMaterial(temp);
                case "工序":
                    productionBatchRuleDetailsDTO.setProcess(temp);
                case "槽次":
                    productionBatchRuleDetailsDTO.setSlotnum(temp);
                case "槽号":
                    productionBatchRuleDetailsDTO.setCellNum(temp);
            }
        }
        return productionBatchRuleDetailsDTO;
    }

    @Override
    public ProductionBatchRuleDetail getByCode(Integer code) {
        ProductionBatchRuleDetailExample example = new ProductionBatchRuleDetailExample();
        example.createCriteria().andCodeEqualTo(code.shortValue());
        ProductionBatchRuleDetail detail = productionBatchRuleDetailMapper.selectByExample(example).get(0);
        return detail;
    }


    @Override
    public Page getAllInfoByCondition(String condition, Integer page, Integer size) {
//        String sql = "SELECT * FROM production_batch_info WHERE " + "year" + " LIKE  '%" + condition + "%'OR " +
//                "production_type" + " LIKE  '%" + condition + "%' OR " + "month" + " LIKE  '%" + condition + "%' OR " +
//                "serial_number" + " LIKE  '%" + condition + "%' OR " + "production_model" + " LIKE  '" + condition + "%' OR " + "production_line" + " LIKE  '%" + condition + "%' OR " +
//                "material" + " LIKE  '%" + condition + "%' OR " + "process" + " LIKE  '%" + condition + "%' OR " + "slotnum" + " LIKE  '%" + condition + "%' OR " +
//                "cell_num" + " LIKE  '%" + condition + "%' OR " + "timepoint" + " LIKE  '%" + condition + "%' OR " + "batch" + " LIKE  '%" + condition + "%' OR " +
//                "set_people" + " LIKE  '%" + condition + "%' OR " + "set_time" + " LIKE  '%" + condition + "%' OR " + "modify_people" + " LIKE  '%" + condition + "%' OR " +
//                "modify_time" + " LIKE  '%" + condition + "%' OR " + "start_time" + " LIKE  '%" + condition + "%' OR " + "end_time" + " LIKE  '%" + condition + "%' OR " +
//                "edit_flag" + " LIKE  '%" + condition + "%' ";
        String sql = "SELECT * FROM production_batch_info WHERE batch like '%" + condition + "%'";
        List<ProductionBatchInfo> productionBatchInfos = productionBatchInfoMapper.selectByConditions(sql);
        return dealSort(productionBatchInfos, page, size);
    }


    @Override
    public ProductionBatchInfoDTO getDetailByCode(long code) {
        ProductionBatchInfoDTO productionBatchInfoDTO = new ProductionBatchInfoDTO();

        ProductionBatchInfoExample productionBatchInfoExample = new ProductionBatchInfoExample();
        productionBatchInfoExample.createCriteria().andCodeEqualTo(code);
        List<ProductionBatchInfo> productionBatchInfos = productionBatchInfoMapper.selectByExample(productionBatchInfoExample);
        //主键只有一条************
        ProductionBatchInfo productionBatchInfo = productionBatchInfos.get(0);
        //前端要求找出人
        if (productionBatchInfo.getModifyPeople() != null) {
            Integer modifyPeopleCode = productionBatchInfo.getModifyPeople();
            AuthUserDTO authUserDTO = authUserMapper.byId(modifyPeopleCode);
            String name = authUserDTO.getName();
            productionBatchInfoDTO.setModify_people(name);
        }
        productionBatchInfoDTO.setProductionBatchInfo(productionBatchInfo);
        return productionBatchInfoDTO;
    }


    @Override
    public void delOneByCode(long code) {
        ProductionBatchInfoExample productionBatchInfoExample = new ProductionBatchInfoExample();
        productionBatchInfoExample.createCriteria().andCodeEqualTo(code);
        productionBatchInfoMapper.deleteByExample(productionBatchInfoExample);
    }

    //这里不用事务，测试一下没有的会怎样？
    @Override
    public void delSomeByCodes(List<Long> codes) {
        for (long l : codes) {
            delOneByCode(l);
        }
    }

    @Override
    public void update(BatchDTO batchDTO) {
        ProductionBatchInfo productionBatchInfo = batchDTO.getInfo();
        ProductionBatchInfoExample productionBatchInfoExample = new ProductionBatchInfoExample();
        productionBatchInfoExample.createCriteria().andCodeEqualTo(productionBatchInfo.getCode());
        productionBatchInfoMapper.updateByExampleSelective(productionBatchInfo, productionBatchInfoExample);

        if (productionBatchInfo.getEndTime() != null && batchDTO.getNumber() != null) {
            //表示这一工序结束 更新标志位
            productionBatchInfo.setStatusFlag(true);
            productionBatchInfoMapper.updateByExampleSelective(productionBatchInfo, productionBatchInfoExample);


            // 跳批数 -1
            //info
//            ProductionBatchInfo batchInfo = new ProductionBatchInfo();
            Integer jump = batchDTO.getNumber();
            for (int i = 1; i < jump; i++) {
                genSameRecord(productionBatchInfo);
            }


            //生成新记录
//            genNewRecord(batchInfo);
        }
    }

    @Override
    public void genNewRecord(ProductionBatchInfo productionBatchInfo) {
        //紧接着生成下一条记录 只是流水号+1
        ProductionBatchInfo batchInfo = new ProductionBatchInfo();
        batchInfo.setYear(productionBatchInfo.getYear());
        batchInfo.setProductionType(productionBatchInfo.getProductionType());
        batchInfo.setMonth(productionBatchInfo.getMonth());
        DecimalFormat decimalFormat = new DecimalFormat("000");
        //流水号加一
        batchInfo.setSerialNumber(decimalFormat.format(Integer.parseInt(productionBatchInfo.getSerialNumber()) + 1));
        batchInfo.setProductionModel(productionBatchInfo.getProductionModel());
        batchInfo.setProductionLine(productionBatchInfo.getProductionLine());
        batchInfo.setMaterial(productionBatchInfo.getMaterial());
        batchInfo.setProcess(productionBatchInfo.getProcess());
        batchInfo.setSlotnum(productionBatchInfo.getSlotnum());
        batchInfo.setCellNum(productionBatchInfo.getCellNum());
        batchInfo.setTimepoint(productionBatchInfo.getTimepoint());
        batchInfo.setBatch(batchInfo.getYear() + batchInfo.getProductionType() + batchInfo.getMonth() + batchInfo.getSerialNumber()
                + batchInfo.getProductionModel() + batchInfo.getProductionLine() + batchInfo.getMaterial());
        batchInfo.setStartTime(productionBatchInfo.getEndTime());
        batchInfo.setSetPeople("系统");
        batchInfo.setSetTime(productionBatchInfo.getEndTime());
        batchInfo.setEditFlag(true);
        batchInfo.setDeleteFlag(true);
        productionBatchInfoMapper.insertSelective(batchInfo);
    }

    @Override
    public ProductionBatchInfo genSameRecord(ProductionBatchInfo productionBatchInfo) {
        //生成一模一样 只是流水号+1的批次信息
        productionBatchInfo.setCode(null);
        DecimalFormat decimalFormat = new DecimalFormat("000");
        //流水号加一
        productionBatchInfo.setSerialNumber(decimalFormat.format(Integer.parseInt(productionBatchInfo.getSerialNumber()) + 1));
        productionBatchInfoMapper.insertSelective(productionBatchInfo);
        return productionBatchInfo;
    }

    @Override
    @Transactional
    public ProductionBatchRetrospectInfo getRecord(String creater, String batch) {

        String dateFormat = "yyyy-MM-dd HH:mm:ss";

        ProductionBatchInfoExample example = new ProductionBatchInfoExample();
        example.createCriteria().andBatchEqualTo(batch);
        List<ProductionBatchInfo> infos = productionBatchInfoMapper.selectByExample(example);

        infos.sort((ProductionBatchInfo o1, ProductionBatchInfo o2) -> (o1.getStartTime().getTime() - o2.getStartTime().getTime()) >= 0 ? 1 : -1);
        List<Boolean> statusFlags = new ArrayList<>();
        for (ProductionBatchInfo info : infos) {
            statusFlags.add(info.getStatusFlag());
        }
        if (!statusFlags.contains(false)) {
            //如果所有的标记都是已完成，则将所有记录的batch_flag变为1，再生成一条批次追溯记录
            for (ProductionBatchInfo info : infos) {
                info.setBatchFlag(true);
                productionBatchInfoMapper.updateByExampleSelective(info, example);
            }
            ProductionBatchRetrospectInfo retrospectInfo = new ProductionBatchRetrospectInfo();
            retrospectInfo.setBatch(batch);
            retrospectInfo.setStartTime(infos.get(0).getStartTime());
            retrospectInfo.setEndTime(infos.get(infos.size() - 1).getEndTime() == null ? new Date() : infos.get(infos.size() - 1).getEndTime());
            retrospectInfo.setCreateTime(new Date());
            retrospectInfo.setCreater(creater);
            retrospectInfoMapper.insertSelective(retrospectInfo);

            return retrospectInfo;
        } else {
            // 判断批次信息是否存在于生产批次追溯记录表，若存在，就删除这条记录和相关的数据记录
            ProductionBatchRetrospectInfoExample example1 = new ProductionBatchRetrospectInfoExample();
            example1.createCriteria().andBatchEqualTo(batch);
            long batchCode = 0;
            if (retrospectInfoMapper.selectByExample(example1).size() > 0) {
                //先拿到批次追溯id
                batchCode = retrospectInfoMapper.selectByExample(example1).get(0).getCode();
                //如果存在这条记录，则删除这条记录
                retrospectInfoMapper.deleteByExample(example1);
                //还要删除相关联的其他表的数据
                ProductionBatchInstrumentMapExample example2 = new ProductionBatchInstrumentMapExample();
                example2.createCriteria().andBatchCodeEqualTo(batchCode);
                if (instrumentMapMapper.selectByExample(example2).size() > 0) {
                    long instrumentCode = instrumentMapMapper.selectByExample(example2).get(0).getInstrumentCode();
                    //删除生产批次仪器仪表对照表记录
                    instrumentMapMapper.deleteByExample(example2);
                    //删除生产批次仪器仪表统计数据记录
                    instrumentDataTjMapper.deleteByPrimaryKey(instrumentCode);
                }
                //删除生产批次设备数据对照表记录
                ProductionBatchDeviceMapExample example3 = new ProductionBatchDeviceMapExample();
                example3.createCriteria().andBatchCodeEqualTo(batchCode);
                batchDeviceMapMapper.deleteByExample(example3);
                //删除生产批次化验数据对照表记录
                ProductionBatchAssayMapExample example4 = new ProductionBatchAssayMapExample();
                example4.createCriteria().andBatchCodeEqualTo(batchCode);
                batchAssayMapMapper.deleteByExample(example4);
            }

            //插入生产批次追溯信息表
            ProductionBatchRetrospectInfo retrospectInfo = new ProductionBatchRetrospectInfo();
            retrospectInfo.setBatch(batch);
            retrospectInfo.setStartTime(infos.get(0).getStartTime());
            retrospectInfo.setEndTime(infos.get(infos.size() - 1).getEndTime() == null ? new Date() : infos.get(infos.size() - 1).getEndTime());
            retrospectInfo.setCreateTime(new Date());
            retrospectInfo.setCreater(creater);
            retrospectInfoMapper.insertSelective(retrospectInfo);

            batchCode = retrospectInfo.getCode();

            //重新进行对照表绑定以及仪器仪表数据统计
            //根据批次信息字段，获取工序为“合成”工序的记录的开始时间和结束时间
            ProductionBatchInfoExample example5 = new ProductionBatchInfoExample();
            example5.createCriteria().andBatchEqualTo(batch).andProcessEqualTo("HC");
            List<ProductionBatchInfo> infoList = productionBatchInfoMapper.selectByExample(example5);
            if (infoList.size() > 0) {
                //如果有合成工序
                ProductionBatchInfo batchInfo = infoList.get(0);
                Date startTime = batchInfo.getStartTime();
                Date endTime = batchInfo.getEndTime() == null ? new Date() : batchInfo.getEndTime();
                String cellNum = batchInfo.getCellNum();
                //根据这两个时间以及合成槽号，查询仪器仪表数据表
                ProductionInstrumentDataExample example6 = new ProductionInstrumentDataExample();
                example6.createCriteria().andSampleTimeBetween(startTime, endTime).andCellNumEqualTo(cellNum);
                List<ProductionInstrumentData> instrumentDatas = instrumentDataMapper.selectByExample(example6);
                //插入统计数据,绑定仪器仪表对照表数据
                dataStatistic(instrumentDatas, batch, batchCode, startTime, endTime);
            }

            //绑定设备维修保养数据
            bindDeviceMaintainData(batch, batchCode);

            //绑定化验数据
            bindAssayData(batch, batchCode);

            return retrospectInfo;
        }
    }

    private void bindDeviceMaintainData(String batch, Long batchCode) {
        ProductionBatchInfoExample example = new ProductionBatchInfoExample();
        example.createCriteria().andBatchEqualTo(batch);
        List<ProductionBatchInfo> infos = productionBatchInfoMapper.selectByExample(example);
        for (ProductionBatchInfo info : infos) {
            String process = info.getProcess();
            Date startTime = info.getStartTime();
            Date endTime = info.getEndTime() == null ? new Date() : info.getEndTime();
            //设备批次工序映射表 通过批次工序找到设备工序id
            BasicInfoDeviceBatchProcessMapExample mapExample = new BasicInfoDeviceBatchProcessMapExample();
            mapExample.createCriteria().andRuleValueEqualTo(process);
            List<BasicInfoDeviceBatchProcessMap> maps = mapMapper.selectByExample(mapExample);
            if (maps.size() > 0) {
                BasicInfoDeviceBatchProcessMap map1 = maps.get(0);
                short processCode = map1.getProcessCode();
                Short ruleDetailCode = map1.getRuleDetailCode();

                ProductionProcessDeviceMapExample mapExample1 = new ProductionProcessDeviceMapExample();
                mapExample1.createCriteria().andProcessCodeEqualTo(processCode);
                //获取这个工序下的所有设备的id
                List<Long> deviceCodeList = new ArrayList<>();
                processDeviceMapMapper.selectByExample(mapExample1).forEach(item -> deviceCodeList.add(item.getDeviceCode()));

                deviceCodeList.forEach(deviceCode -> {
                    //根据主设备编号 开始和截止时间 查询设备维修信息和保养信息
                    List<DeviceRepairApplication> applications = deviceRepairService.getApplicationsByCondition(deviceCode, startTime, endTime);
                    List<DeviceMaintenanceRecordHead> maintenanceRecordHeads = deviceMaintenanceRecordHeadService.getRecordsByCondition(deviceCode, startTime, endTime);
                    String specification = deviceDocumentManageService.getSpecificationById(deviceCode);
                    for (DeviceRepairApplication application : applications) {
                        ProductionBatchDeviceMap map = new ProductionBatchDeviceMap();
                        map.setBatchCode(batchCode);
                        map.setBatch(batch);
                        map.setRuleDetailCode(ruleDetailCode);
                        map.setRuleValue(process);
                        map.setStartTime(startTime);
                        map.setEndTime(endTime);
                        map.setDeviceCode(deviceCode);
                        map.setDeviceName(application.getDeviceName());
                        map.setFixedassetsCode(application.getFixedassetsCode());
                        map.setSpecification(specification);
                        batchDeviceMapMapper.insertSelective(map);
                    }
                    for (DeviceMaintenanceRecordHead head : maintenanceRecordHeads) {
                        ProductionBatchDeviceMap map = new ProductionBatchDeviceMap();
                        map.setBatchCode(batchCode);
                        map.setBatch(batch);
                        map.setRuleDetailCode(ruleDetailCode);
                        map.setRuleValue(process);
                        map.setStartTime(startTime);
                        map.setEndTime(endTime);
                        map.setDeviceCode(deviceCode);
                        map.setDeviceName(head.getDeviceName());
                        map.setFixedassetsCode(head.getFixedassetsCode());
                        map.setSpecification(specification);
                        batchDeviceMapMapper.insertSelective(map);
                    }
                });
            }
        }
    }

    //绑定化验数据
    private void bindAssayData(String batch, Long batchCode) {

        QualityCommonBatchNumberExtraExample extraExample = new QualityCommonBatchNumberExtraExample();
        extraExample.createCriteria().andBatchEqualTo(batch);
        List<QualityCommonBatchNumberExtra> qualityCommonBatchNumberExtras = qualityCommonBatchNumberExtraMapper.selectByExample(extraExample);

        if (qualityCommonBatchNumberExtras.size() == 0) {
            return;
        }

        for (QualityCommonBatchNumberExtra extra : qualityCommonBatchNumberExtras) {
            Integer commonBatchId = extra.getCommonBatchId();
            SampleDeliveringRecord byId = sampleDeliveringRecordMapper.getById(commonBatchId);

            ProductionBatchAssayMap assayMap = new ProductionBatchAssayMap();
            assayMap.setBatchCode(batchCode);
            assayMap.setBatch(batch);
            assayMap.setSampleCode(byId.getId().longValue());
            assayMap.setType(byId.getType());
            assayMap.setDelivererId(byId.getDelivererId());
            assayMap.setSampleDeliveringDate(byId.getSampleDeliveringDate());
            assayMap.setDeliveryFactoryId(byId.getDeliveryFactoryId());
            assayMap.setTestItems(byId.getTestItems());
            batchAssayMapMapper.insertSelective(assayMap);
        }
    }

    //统计仪器仪表数据
    private void dataStatistic(List<ProductionInstrumentData> instrumentDatas, String batch, long batchCode, Date startTime, Date endTime) {
        List<Float> phValue = new ArrayList<>();
        List<Float> temperature = new ArrayList<>();
        List<Float> saltFlow1 = new ArrayList<>();
        List<Float> saltFlow2 = new ArrayList<>();
        List<Float> saltFlow3 = new ArrayList<>();
        List<Float> saltFlow4 = new ArrayList<>();
        List<Float> ammoniaBases1 = new ArrayList<>();
        List<Float> ammoniaBases2 = new ArrayList<>();
        List<Float> ammoniaWater = new ArrayList<>();
        List<Float> ammoniaGas = new ArrayList<>();
        List<Float> solidContainingContent = new ArrayList<>();
        List<Float> transducerShow = new ArrayList<>();
        List<Float> measured3c = new ArrayList<>();
        for (ProductionInstrumentData data : instrumentDatas) {
            phValue.add(data.getPhValue());
            temperature.add(data.getTemperature());
            saltFlow1.add(data.getSaltFlow1());
            saltFlow2.add(data.getSaltFlow2());
            saltFlow3.add(data.getSaltFlow3());
            saltFlow4.add(data.getSaltFlow4());
            ammoniaBases1.add(data.getAmmoniaBases1());
            ammoniaBases2.add(data.getAmmoniaBases2());
            ammoniaWater.add(data.getAmmoniaWater());
            ammoniaGas.add(data.getAmmoniaGas());
            solidContainingContent.add(data.getSolidContainingContent());
            transducerShow.add(data.getTransducerShow());
            measured3c.add(data.getMeasured3c());
        }
        String cellNum = instrumentDatas.get(0).getCellNum();
        float phValueMin = Collections.min(phValue);
        float phValueMax = Collections.max(phValue);
        float phValueAvg = getAverage(phValue);
        float temperatureMin = Collections.min(temperature);
        float temperatureMax = Collections.max(temperature);
        float temperatureAvg = getAverage(temperature);
        float saltFlow1Min = Collections.min(saltFlow1);
        float saltFlow1Max = Collections.max(saltFlow1);
        float saltFlow1Avg = getAverage(saltFlow1);
        float saltFlow2Min = Collections.min(saltFlow2);
        float saltFlow2Max = Collections.max(saltFlow2);
        float saltFlow2Avg = getAverage(saltFlow2);
        float saltFlow3Min = Collections.min(saltFlow3);
        float saltFlow3Max = Collections.max(saltFlow3);
        float saltFlow3Avg = getAverage(saltFlow3);
        float saltFlow4Min = Collections.min(saltFlow4);
        float saltFlow4Max = Collections.max(saltFlow4);
        float saltFlow4Avg = getAverage(saltFlow4);
        float ammoniaBases1Min = Collections.min(ammoniaBases1);
        float ammoniaBases1Max = Collections.max(ammoniaBases1);
        float ammoniaBases1Avg = getAverage(ammoniaBases1);
        float ammoniaBases2Min = Collections.min(ammoniaBases2);
        float ammoniaBases2Max = Collections.max(ammoniaBases2);
        float ammoniaBases2Avg = getAverage(ammoniaBases2);
        float ammoniaWaterMin = Collections.min(ammoniaWater);
        float ammoniaWaterMax = Collections.max(ammoniaWater);
        float ammoniaWaterAvg = getAverage(ammoniaWater);
        float ammoniaGasMin = Collections.min(ammoniaGas);
        float ammoniaGasMax = Collections.max(ammoniaGas);
        float ammoniaGasAvg = getAverage(ammoniaGas);
        float solidContainingContentMin = Collections.min(solidContainingContent);
        float solidContainingContentMax = Collections.max(solidContainingContent);
        float solidContainingContentAvg = getAverage(solidContainingContent);
        float transducerShowMin = Collections.min(transducerShow);
        float transducerShowMax = Collections.max(transducerShow);
        float transducerShowAvg = getAverage(transducerShow);
        float measured3cMin = Collections.min(measured3c);
        float measured3cMax = Collections.max(measured3c);
        float measured3cAvg = getAverage(measured3c);
        ProductionInstrumentDataTj dataTj = new ProductionInstrumentDataTj();
        dataTj.setCellNum(cellNum);
        dataTj.setPhValueMin(phValueMin);
        dataTj.setPhValueMax(phValueMax);
        dataTj.setPhValueAvg(phValueAvg);
        dataTj.setTemperatureMin(temperatureMin);
        dataTj.setTemperatureMax(temperatureMax);
        dataTj.setTemperatureAvg(temperatureAvg);
        dataTj.setSaltFlow1Min(saltFlow1Min);
        dataTj.setSaltFlow1Max(saltFlow1Max);
        dataTj.setSaltFlow1Avg(saltFlow1Avg);
        dataTj.setSaltFlow2Min(saltFlow2Min);
        dataTj.setSaltFlow2Max(saltFlow2Max);
        dataTj.setSaltFlow2Avg(saltFlow2Avg);
        dataTj.setSaltFlow3Min(saltFlow3Min);
        dataTj.setSaltFlow3Max(saltFlow3Max);
        dataTj.setSaltFlow3Avg(saltFlow3Avg);
        dataTj.setSaltFlow4Min(saltFlow4Min);
        dataTj.setSaltFlow4Max(saltFlow4Max);
        dataTj.setSaltFlow4Avg(saltFlow4Avg);
        dataTj.setAmmoniaBases1Min(ammoniaBases1Min);
        dataTj.setAmmoniaBases1Max(ammoniaBases1Max);
        dataTj.setAmmoniaBases1Avg(ammoniaBases1Avg);
        dataTj.setAmmoniaBases2Min(ammoniaBases2Min);
        dataTj.setAmmoniaBases2Max(ammoniaBases2Max);
        dataTj.setAmmoniaBases2Avg(ammoniaBases2Avg);
        dataTj.setAmmoniaWaterMin(ammoniaWaterMin);
        dataTj.setAmmoniaWaterMax(ammoniaWaterMax);
        dataTj.setAmmoniaWaterAvg(ammoniaWaterAvg);
        dataTj.setAmmoniaGasMin(ammoniaGasMin);
        dataTj.setAmmoniaGasMax(ammoniaGasMax);
        dataTj.setAmmoniaGasAvg(ammoniaGasAvg);
        dataTj.setSolidContainingContentMin(solidContainingContentMin);
        dataTj.setSolidContainingContentMax(solidContainingContentMax);
        dataTj.setSolidContainingContentAvg(solidContainingContentAvg);
        dataTj.setTransducerShowMin(transducerShowMin);
        dataTj.setTransducerShowMax(transducerShowMax);
        dataTj.setTransducerShowAvg(transducerShowAvg);
        dataTj.setMeasured3cMin(measured3cMin);
        dataTj.setMeasured3cMax(measured3cMax);
        dataTj.setMeasured3cAvg(measured3cAvg);
        //插入统计数据
        instrumentDataTjMapper.insertSelective(dataTj);
        ProductionBatchInstrumentMap map = new ProductionBatchInstrumentMap();
        map.setBatchCode(batchCode);
        map.setBatch(batch);
        map.setCellNum(cellNum);
        map.setInstrumentCode(dataTj.getCode());
        map.setStartTime(startTime);
        map.setEndTime(endTime);
        instrumentMapMapper.insert(map);
    }

    private static float getAverage(List<Float> values) {
        float sum = 0;
        int n = values.size();
        for (float value : values) {
            sum += value;
        }
        return sum / n;
    }

    @Override
    public ProductionBatchInfo getInfo(String batch) {
        ProductionBatchInfoExample example = new ProductionBatchInfoExample();
        example.createCriteria().andBatchEqualTo(batch);
        return productionBatchInfoMapper.selectByExample(example).get(0);
    }

    @Override
    public List compareByBatch(String batch) {
        String sql = "SELECT * FROM `production_batch_info` WHERE INSTR('" + batch + "',batch)";
        return productionBatchInfoMapper.selectByConditions(sql);
    }

    @Override
    public Integer getJumpBatchNumber(String process) {
        float a = volumesService.getSumOfVolumesValue();
        float b = configService.getValueByProcess(process);
        return new Float(a / b).intValue();
    }
}
