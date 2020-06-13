package com.jinchi.common.service;

import com.jinchi.common.constant.ProcessEnum;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import com.jinchi.common.utils.RealTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AuxiliaryServiceImp implements AuxiliaryService {

    @Autowired
    AuxiliaryMaterialsStatisticHeadMapper headMapper;
    @Autowired
    AuxiliaryMaterialsStatisticByLineDetailMapper lineDetailMapper;
    @Autowired
    AuxiliaryMaterialsStatisticByLineTotalsMapper lineTotalsMapper;
    @Autowired
    AuxiliaryMaterialsStatisticDataDetailsDeliveryMapper dataDetailsDeliveryMapper;
    @Autowired
    AuxiliaryMaterialsStatisticDataListMapper dataListMapper;
    @Autowired
    AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionMapper consumptionMapper;
    @Autowired
    AuxiliaryMaterialsStatisticDetailsPlantMapper plantMapper;
    @Autowired
    AuxiliaryMaterialsStatisticDetailsTankAreaMapper tankAreaMapper;
    @Autowired
    GoodsInProcessStatisticHeadMapper goodInHeadMapper;
    @Autowired
    MaterialDeliveryStatisticHeadMapper materialHeadMapper;
    @Autowired
    BasicInfoPrecursorProcessTypeMapper processTypeMapper;
    @Autowired
    BasicInfoPrecursorMaterialDetailsMapper materialDetailsMapper;
    @Autowired
    BasicInfoPrecursorProductionLineMapper lineMapper;
    @Autowired
    BasicInfoPrecursorMaterialLineWeightMapper lineWeightMapper;
    @Autowired
    BasicInfoPrecursorPeriodMapper periodMapper;
    @Autowired
    BasicInfoPrecursorMaterialDetailsMapper basicInfoPrecursorMaterialDetailsMapper;
    @Autowired
    BasicInfoPrecursorMaterialPlcMapMapper plcMapMapper;
    @Autowired
    BasicInfoPrecursorPlcAddressMapper addressMapper;

    @Autowired
    ProductStorageStatisticHeadMapper productMapper;

    @Autowired
    PrecursorHeadTableOperationService operationService;

    @Override
    public Object addComfirm(AuxiliaryMaterialsStatisticHead head) {
        head.setFlag(false);

        GoodsInProcessStatisticHeadExample example = new GoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getPeriods()).andFlagEqualTo((byte) 1);
        List<GoodsInProcessStatisticHead> goodsInHeads = goodInHeadMapper.selectByExample(example);
        example.clear();
        example.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getPeriods()).andFlagEqualTo((byte) 0);
        List<GoodsInProcessStatisticHead> goodsInHeads1 = goodInHeadMapper.selectByExample(example);


        MaterialDeliveryStatisticHeadExample example1 = new MaterialDeliveryStatisticHeadExample();
        example1.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getPeriods()).andFlagEqualTo((byte) 1);
        List<MaterialDeliveryStatisticHead> matHeads = materialHeadMapper.selectByExample(example1);
        example1.clear();
        example1.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getPeriods()).andFlagEqualTo((byte) 0);
        List<MaterialDeliveryStatisticHead> matHeads1 = materialHeadMapper.selectByExample(example1);


        ProductStorageStatisticHeadExample example3 = new ProductStorageStatisticHeadExample();
        example3.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getPeriods()).andFlagEqualTo((byte) 1);
        List<ProductStorageStatisticHead> pHeads = productMapper.selectByExample(example3);
        example3.clear();
        example3.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andLineNameEqualTo(head.getPeriods()).andFlagEqualTo((byte) 0);
        List<ProductStorageStatisticHead> pHeads1 = productMapper.selectByExample(example3);

        AuxiliaryMaterialsStatisticHeadExample example2 = new AuxiliaryMaterialsStatisticHeadExample();
        example2.createCriteria().andPeriodCodeEqualTo(head.getPeriodCode()).andPeriodsEqualTo(head.getPeriods());
        List<AuxiliaryMaterialsStatisticHead> aaheads = headMapper.selectByExample(example2);

        if (aaheads.size() != 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", -1);
            map.put("message", "本期数据已经存在");
            return map;
        }
        String format = "yyyy-MM-dd HH:mm:ss";

        if (goodsInHeads.size() == 0 && matHeads.size() == 0 && pHeads.size() == 0) {
            if (goodsInHeads1.size() != 0) {
                GoodsInProcessStatisticHead temp = goodsInHeads1.get(0);
                if (!temp.getStartTime().equals(head.getStartTime())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", -2);
                    map.put("message", "在制品管理存在第" + temp.getLineName() + "期待提交数据，开始时间为: " + ComUtil.dateToString(temp.getStartTime(), format) + "本模块开始时间应与其一致");
                    return map;
                }
            }
            if (matHeads1.size() != 0) {
                MaterialDeliveryStatisticHead temp = matHeads1.get(0);
                if (!temp.getStartTime().equals(head.getStartTime())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", -2);
                    map.put("message", "原料领用存在第" + temp.getLineName() + "期待提交数据，开始时间为: " + ComUtil.dateToString(temp.getStartTime(), format) + "本模块开始时间应与其一致");
                    return map;
                }
            }
            if (pHeads1.size() != 0) {
                ProductStorageStatisticHead temp = pHeads1.get(0);
                if (!temp.getStartTime().equals(head.getStartTime())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("code", -2);
                    map.put("message", "成品入库存在第" + temp.getLineName() + "期待提交数据，开始时间为: " + ComUtil.dateToString(temp.getStartTime(), format) + "本模块开始时间应与其一致");
                    return map;
                }
            }
        }
        if (matHeads.size() != 0) {
            MaterialDeliveryStatisticHead temp = matHeads.get(0);
            if (temp.getEndTime().equals(head.getEndTime()) && temp.getStartTime().equals(head.getStartTime())) {
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -2);
                map.put("message", "原料领用存在第" + temp.getLineName() + "期已统计数据，开始时间为: " + ComUtil.dateToString(temp.getStartTime(), format) + "结束时间为: " + ComUtil.dateToString(temp.getEndTime(), format)
                        + "本模块开始时间、结束时间应与其一致");
                return map;
            }
        }
        if (pHeads.size() != 0) {
            ProductStorageStatisticHead temp = pHeads.get(0);
            if (temp.getEndTime().equals(head.getEndTime()) && temp.getStartTime().equals(head.getStartTime())) {
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -2);
                map.put("message", "成品入库存在第" + temp.getLineName() + "期已统计数据，开始时间为: " + ComUtil.dateToString(temp.getStartTime(), format) + "结束时间为: " + ComUtil.dateToString(temp.getEndTime(), format)
                        + "本模块开始时间、结束时间应与其一致");
                return map;
            }
        }
        if (goodsInHeads.size() != 0) {
            GoodsInProcessStatisticHead temp = goodsInHeads.get(0);
            if (temp.getEndTime().equals(head.getEndTime()) && temp.getStartTime().equals(head.getStartTime())) {
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("code", -2);
                map.put("message", "在制品管理存在第" + temp.getLineName() + "期已统计数据，开始时间为: " + ComUtil.dateToString(temp.getStartTime(), format) + "结束时间为: " + ComUtil.dateToString(temp.getEndTime(), format)
                        + "本模块开始时间、结束时间应与其一致");
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
    public AuxiliaryMaterialsStatisticHead update(AuxiliaryMaterialsStatisticHead head) {
        headMapper.updateByPrimaryKeySelective(head);
        return head;
    }

    @Override
    public AuxiliaryAddDTO afterComfirm(Long id) {
        List<GoodInProcessDTO> processDTOs = new ArrayList<>();
        AuxiliaryAddDTO ans = new AuxiliaryAddDTO();
        if (id == null || id != null) {
            BasicInfoPrecursorProcessTypeExample example = new BasicInfoPrecursorProcessTypeExample();
            example.createCriteria().andTypesEqualTo(new Integer(1).byteValue());
            List<BasicInfoPrecursorProcessType> processTypes = processTypeMapper.selectByExample(example);

            for (int i = 0; i < processTypes.size(); i++) {
                GoodInProcessDTO processDTO = new GoodInProcessDTO();
                processDTO.setProcessId(processTypes.get(i).getCode());
                processDTO.setProcessName(processTypes.get(i).getProcessName());

                BasicInfoPrecursorMaterialDetailsExample example1 = new BasicInfoPrecursorMaterialDetailsExample();
                example1.createCriteria().andProcessCodeEqualTo(processTypes.get(i).getCode());
                List<BasicInfoPrecursorMaterialDetails> materialDetails = materialDetailsMapper.selectByExample(example1);

                List<BasicInfoPrecursorMaterialDetailsDTO> materialDetailsDTOS = new ArrayList<>();
                for (int l = 0; l < materialDetails.size(); l++) {
                    materialDetailsDTOS.add(new BasicInfoPrecursorMaterialDetailsDTO(materialDetails.get(l)));
                }
                processDTO.setMaterialDetails(materialDetailsDTOS);
                processDTOs.add(processDTO);
            }
            ans.setProcessDTOS(processDTOs);
        }
        if (id != null) {
            ans.setHead(headMapper.selectByPrimaryKey(id));
            BasicInfoPrecursorProcessTypeExample example = new BasicInfoPrecursorProcessTypeExample();
            example.createCriteria().andTypesEqualTo(new Integer(1).byteValue());
            List<BasicInfoPrecursorProcessType> processTypes = processTypeMapper.selectByExample(example);
            processDTOs.clear();
            for (int i = 0; i < processTypes.size(); i++) {
                GoodInProcessDTO processDTO = new GoodInProcessDTO();
                processDTO.setProcessId(processTypes.get(i).getCode());
                processDTO.setProcessName(processTypes.get(i).getProcessName());

                if (processTypes.get(i).getCode() == ProcessEnum.STOCKIN.getProcessId()) {
                    List<BasicInfoPrecursorMaterialDetailsDTO> materialDetailsDTOS = new ArrayList<>();
                    AuxiliaryMaterialsStatisticDataDetailsDeliveryExample example2 = new AuxiliaryMaterialsStatisticDataDetailsDeliveryExample();
                    example2.createCriteria().andStatisticCodeEqualTo(id);
                    List<AuxiliaryMaterialsStatisticDataDetailsDelivery> deliveries = dataDetailsDeliveryMapper.selectByExample(example2);
                    if (deliveries.size() == 0) {
                        break;
                    }
                    for (int l = 0; l < deliveries.size(); l++) {
                        BasicInfoPrecursorMaterialDetails info = materialDetailsMapper.selectByPrimaryKey(deliveries.get(l).getMaterialCode());
                        BasicInfoPrecursorMaterialDetailsDTO materialDetailsDTO = new BasicInfoPrecursorMaterialDetailsDTO(info);
                        materialDetailsDTO.setWeight(deliveries.get(l).getTotals());
                        materialDetailsDTO.setAmmPotency(deliveries.get(l).getAmmoniaConcentration());
                        materialDetailsDTO.setAlkPotency(deliveries.get(l).getAlkaliConcentration());
                        materialDetailsDTOS.add(materialDetailsDTO);
                    }
                    processDTO.setMaterialDetails(materialDetailsDTOS);
                }
                if (processTypes.get(i).getCode() == ProcessEnum.TANK.getProcessId()) {
                    List<BasicInfoPrecursorMaterialDetailsDTO> materialDetailsDTOS = new ArrayList<>();
                    AuxiliaryMaterialsStatisticDetailsTankAreaExample example2 = new AuxiliaryMaterialsStatisticDetailsTankAreaExample();
                    example2.createCriteria().andStatisticCodeEqualTo(id);
                    List<AuxiliaryMaterialsStatisticDetailsTankArea> areas = tankAreaMapper.selectByExample(example2);
                    for (int l = 0; l < areas.size(); l++) {
                        BasicInfoPrecursorMaterialDetails info = materialDetailsMapper.selectByPrimaryKey(areas.get(l).getMaterialCode());
                        BasicInfoPrecursorMaterialDetailsDTO materialDetailsDTO = new BasicInfoPrecursorMaterialDetailsDTO(info);
                        materialDetailsDTO.setVolume(areas.get(l).getVolumes());
                        materialDetailsDTO.setAmmPotency(areas.get(l).getAmmoniaConcentration());
                        materialDetailsDTO.setAlkPotency(areas.get(l).getAlkaliConcentration());
                        materialDetailsDTO.setMonPotency(areas.get(l).getDensity());
                        materialDetailsDTOS.add(materialDetailsDTO);
                    }
                    processDTO.setMaterialDetails(materialDetailsDTOS);
                }
                if (processTypes.get(i).getCode() == ProcessEnum.WORKSHOP.getProcessId()) {
                    List<BasicInfoPrecursorMaterialDetailsDTO> materialDetailsDTOS = new ArrayList<>();
                    AuxiliaryMaterialsStatisticDetailsPlantExample example2 = new AuxiliaryMaterialsStatisticDetailsPlantExample();
                    example2.createCriteria().andStatisticCodeEqualTo(id);
                    List<AuxiliaryMaterialsStatisticDetailsPlant> plants = plantMapper.selectByExample(example2);
                    for (int l = 0; l < plants.size(); l++) {
                        BasicInfoPrecursorMaterialDetails info = materialDetailsMapper.selectByPrimaryKey(plants.get(l).getMaterialCode());
                        BasicInfoPrecursorMaterialDetailsDTO materialDetailsDTO = new BasicInfoPrecursorMaterialDetailsDTO(info);
                        materialDetailsDTO.setVolume(plants.get(l).getVolumes());
                        materialDetailsDTO.setAmmPotency(plants.get(l).getAmmoniaConcentration());
                        materialDetailsDTO.setAlkPotency(plants.get(l).getAlkaliConcentration());
                        materialDetailsDTO.setMonPotency(plants.get(l).getDensity());
                        materialDetailsDTOS.add(materialDetailsDTO);
                    }
                    processDTO.setMaterialDetails(materialDetailsDTOS);
                }
                if (processTypes.get(i).getCode() == ProcessEnum.CONSUMPTION.getProcessId()) {
                    List<BasicInfoPrecursorMaterialDetailsDTO> materialDetailsDTOS = new ArrayList<>();
                    AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample example2 = new AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample();
                    example2.createCriteria().andStatisticCodeEqualTo(id);
                    List<AuxiliaryMaterialsStatisticDetailsIngredientsConsumption> consumptions = consumptionMapper.selectByExample(example2);
                    for (int l = 0; l < consumptions.size(); l++) {
                        BasicInfoPrecursorMaterialDetails info = materialDetailsMapper.selectByPrimaryKey(consumptions.get(l).getMaterialCode());
                        BasicInfoPrecursorMaterialDetailsDTO materialDetailsDTO = new BasicInfoPrecursorMaterialDetailsDTO(info);
                        materialDetailsDTO.setWeight(consumptions.get(l).getTotals());
                        materialDetailsDTO.setAmmPotency(consumptions.get(l).getAmmoniaConcentration());
                        materialDetailsDTO.setAlkPotency(consumptions.get(l).getAlkaliConcentration());
                        materialDetailsDTOS.add(materialDetailsDTO);
                    }
                    processDTO.setMaterialDetails(materialDetailsDTOS);
                }
                processDTOs.add(processDTO);
            }
            ans.setProcessDTOS(processDTOs);
        }
        return ans;
    }

    @Override
    public Map nextPeroidNumber(Integer periodId) {
        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andFlagEqualTo(true);
        List<AuxiliaryMaterialsStatisticHead> heads = headMapper.selectByExample(example);

        int max = 0;
        Date end = null;
        for (int i = 0; i < heads.size(); i++) {
            if (max < heads.get(i).getPeriods()) {
                max = heads.get(i).getPeriods();
                end = heads.get(i).getEndTime();
            }
        }
        Map<String, Object> ans = new HashMap<>();
        ans.put("period", max + 1);
        ans.put("endTime", end == null ? null : ComUtil.dateToString(end, "yyyy-MM-dd"));
        return ans;
    }

    @Override
    public String saveOrCommit(AuxiliaryAddDTO auxiliaryAddDTO, Integer flag) {
        if (flag == 0) {
            save(auxiliaryAddDTO);
            return "保存成功";
        } else {
            commit(auxiliaryAddDTO);
            return "提交成功";
        }
    }

    @Override
    public void delete(Long id) {
        deleteCasade(id);
        headMapper.deleteByPrimaryKey(id);
    }

    private void deleteCasade(Long id) {
        AuxiliaryMaterialsStatisticDataDetailsDeliveryExample example = new AuxiliaryMaterialsStatisticDataDetailsDeliveryExample();
        example.createCriteria().andStatisticCodeEqualTo(id);
        dataDetailsDeliveryMapper.deleteByExample(example);

        AuxiliaryMaterialsStatisticDetailsTankAreaExample example1 = new AuxiliaryMaterialsStatisticDetailsTankAreaExample();
        example1.createCriteria().andStatisticCodeEqualTo(id);
        tankAreaMapper.deleteByExample(example1);

        AuxiliaryMaterialsStatisticDetailsPlantExample example2 = new AuxiliaryMaterialsStatisticDetailsPlantExample();
        example2.createCriteria().andStatisticCodeEqualTo(id);
        plantMapper.deleteByExample(example2);

        AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample example3 = new AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample();
        example3.createCriteria().andStatisticCodeEqualTo(id);
        consumptionMapper.deleteByExample(example3);
    }

    private void save(AuxiliaryAddDTO auxiliaryAddDTO) {
        AuxiliaryMaterialsStatisticHead head = auxiliaryAddDTO.getHead();
        Long id = head.getCode();

        deleteCasade(id);

        List<GoodInProcessDTO> processDTOS = auxiliaryAddDTO.getProcessDTOS();
        for (int i = 0; i < processDTOS.size(); i++) {
            //入库量物料
            if (processDTOS.get(i).getProcessId() == ProcessEnum.STOCKIN.getProcessId()) {
                List<BasicInfoPrecursorMaterialDetailsDTO> materials = processDTOS.get(i).getMaterialDetails();
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    AuxiliaryMaterialsStatisticDataDetailsDelivery delivery = new AuxiliaryMaterialsStatisticDataDetailsDelivery();
                    delivery.setMaterialCode(info.getCode());
                    delivery.setStatisticCode(id);
                    delivery.setProcessCode(info.getProcessCode());
                    /**
                     * 氨量（t）=重量(t)*氨浓度（%）
                     * 碱量（t）=重量(t)*碱浓度（%）
                     * 这里只有氨或者碱，所以浓度是1或0
                     */
                    if (info.getAmmoniaFlag() == 1) {
                        delivery.setAmmoniaValue(info.getWeight());
                        delivery.setAmmoniaConcentration(1f);
                        delivery.setAlkaliValue(0f);
                        delivery.setAlkaliConcentration(0f);
                    }
                    if (info.getAlkaliFlag() == 1) {
                        delivery.setAlkaliValue(info.getWeight());
                        delivery.setAlkaliConcentration(1f);
                        delivery.setAmmoniaValue(0f);
                        delivery.setAmmoniaConcentration(0f);
                    }
                    delivery.setTotals(info.getWeight());
                    dataDetailsDeliveryMapper.insertSelective(delivery);
                }
            }
            if (processDTOS.get(i).getProcessId() == ProcessEnum.TANK.getProcessId()) {
                List<BasicInfoPrecursorMaterialDetailsDTO> materials = processDTOS.get(i).getMaterialDetails();
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    AuxiliaryMaterialsStatisticDetailsTankArea tankArea = new AuxiliaryMaterialsStatisticDetailsTankArea();
                    tankArea.setMaterialCode(info.getCode());
                    tankArea.setStatisticCode(id);
                    tankArea.setProcessCode(info.getProcessCode());
                    /**
                     * 在罐区详情的小计中，不统计重量值（t），只统计体积值。
                     * 计算公式：
                     * 氨水（氨水配制槽A，氨水配制槽B，氨水储槽）：
                     * 氨量（t）=体积(m3)*密度(t/m3)*氨浓度（%）
                     * 碱量（t）=0
                     * 氨碱（氨碱配制槽A，氨碱配制槽B，氨碱储槽）：
                     * 氨量（t）=体积(m3)*密度(t/m3)*氨浓度（%）
                     * 碱量（t）=体积(m3)*密度(t/m3)*碱浓度（%）/32%
                     * 碱液（碱液储槽，碱液精滤储槽）：
                     * 氨量（t）=0
                     * 碱量（t）=体积(m3)*密度(t/m3)
                     */
                    if (info.getAmmoniaFlag() == 1 && info.getAlkaliFlag() == 1) {
                        tankArea.setAmmoniaValue(info.getVolume() * info.getAmmPotency() * info.getMonPotency());
                        tankArea.setAmmoniaConcentration(info.getAmmPotency());
                        tankArea.setAlkaliValue(info.getVolume() * info.getAlkPotency() * info.getMonPotency() / 0.32f);
                        tankArea.setAlkaliConcentration(info.getAlkPotency());
                    } else {
                        tankArea.setAmmoniaValue(info.getVolume() * info.getAmmPotency() * info.getMonPotency());
                        tankArea.setAmmoniaConcentration(info.getAmmPotency());
                        tankArea.setAlkaliValue(info.getVolume() * info.getAlkPotency() * info.getMonPotency());
                        tankArea.setAlkaliConcentration(info.getAlkPotency());
                    }
                    tankArea.setDensity(info.getMonPotency());
                    tankArea.setVolumes(info.getVolume());
                    tankArea.setTotals(info.getVolume() * info.getMonPotency());
                    tankAreaMapper.insertSelective(tankArea);
                }
            }
            if (processDTOS.get(i).getProcessId() == ProcessEnum.WORKSHOP.getProcessId()) {
                List<BasicInfoPrecursorMaterialDetailsDTO> materials = processDTOS.get(i).getMaterialDetails();
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    AuxiliaryMaterialsStatisticDetailsPlant plant = new AuxiliaryMaterialsStatisticDetailsPlant();
                    plant.setMaterialCode(info.getCode());
                    plant.setStatisticCode(id);
                    plant.setProcessCode(info.getProcessCode());
                    /**
                     * 氨水（12#氨水平衡槽，34#氨水平衡槽）：
                     * 氨量（t）=体积(m3)*密度(t/m3)*氨浓度（%）
                     * 碱量（t）=0
                     * 氨碱（12#氨碱平衡槽，34#氨碱平衡槽）：
                     * 氨量（t）=体积(m3)*密度(t/m3)*氨浓度（%）
                     * 碱量（t）=体积(m3)*密度(t/m3)*碱浓度（%）/32%
                     * 碱液（碱液高位槽）：
                     * 氨量（t）=0
                     * 碱量（t）=体积(m3)*密度(t/m3)
                     * 碱液（NCA碱液配制槽，NCA碱液储槽，陈化稀碱配制槽，陈化稀碱储槽）：
                     * 氨量（t）=0
                     * 碱量（t）=体积(m3)*密度(t/m3)*碱浓度（%）/32%
                     */
                    if (info.getAmmoniaFlag() == 1 && info.getAlkaliFlag() == 1) {
                        plant.setAmmoniaValue(info.getVolume() * info.getAmmPotency() * info.getMonPotency());
                        plant.setAmmoniaConcentration(info.getAmmPotency());
                        plant.setAlkaliValue(info.getVolume() * info.getAlkPotency() * info.getMonPotency() / 0.32f);
                        plant.setAlkaliConcentration(info.getAlkPotency());
                    } else if (info.getAmmoniaFlag() == 1 && info.getAlkaliFlag() == 0) {
                        plant.setAmmoniaValue(info.getVolume() * info.getAmmPotency() * info.getMonPotency());
                        plant.setAmmoniaConcentration(info.getAmmPotency());
                        plant.setAlkaliValue(0f);
                        plant.setAlkaliConcentration(0f);
                    } else if (info.getAmmoniaFlag() == 0 && info.getAlkaliFlag() == 1) {
                        //碱液高位槽
                        if (info.getCode() == 109) {
                            plant.setAmmoniaValue(0f);
                            plant.setAmmoniaConcentration(0f);
                            plant.setAlkaliValue(info.getVolume() * info.getMonPotency());
                            plant.setAlkaliConcentration(info.getAlkPotency());
                        } else {
                            plant.setAmmoniaValue(0f);
                            plant.setAmmoniaConcentration(0f);
                            plant.setAlkaliValue(info.getVolume() * info.getMonPotency() * info.getAlkPotency() / 0.32f);
                            plant.setAlkaliConcentration(info.getAlkPotency());
                        }
                    }
                    plant.setDensity(info.getMonPotency());
                    plant.setVolumes(info.getVolume());
                    plant.setTotals(info.getVolume() * info.getMonPotency());
                    plantMapper.insertSelective(plant);
                }
            }
            if (processDTOS.get(i).getProcessId() == ProcessEnum.CONSUMPTION.getProcessId()) {
                List<BasicInfoPrecursorMaterialDetailsDTO> materials = processDTOS.get(i).getMaterialDetails();
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    AuxiliaryMaterialsStatisticDetailsIngredientsConsumption consumption = new AuxiliaryMaterialsStatisticDetailsIngredientsConsumption();
                    consumption.setMaterialCode(info.getCode());
                    consumption.setStatisticCode(id);
                    consumption.setProcessCode(info.getProcessCode());
                    /**
                     * 12#氨碱使用量：氨量（t） =重量值(t)*氨浓度（%）
                     *               碱量（t）=重量值(t)*碱浓度（%）/32%
                     * 34#氨碱使用量：氨量（t）=重量值(t)*氨浓度（%）
                     *               碱量（t）=重量值(t)*碱浓度（%）/32%
                     * 12#氨水使用量：氨量（t）=重量值(t)*氨浓度（%），此时碱浓度（%）=0；
                     * 34#氨水使用量：氨量（t）=重量值(t)*氨浓度（%），此时碱浓度（%）=0；
                     * 碱使用量：碱量（t）=重量值(t)*碱浓度（%），此时碱浓度（%）=1，氨浓度（%）=0；即：碱量（t）=重量值(t)。
                     */
                    if (info.getAlkaliFlag() == 1 && info.getAmmoniaFlag() == 1) {
                        consumption.setAmmoniaValue(info.getWeight() * info.getAmmPotency());
                        consumption.setAmmoniaConcentration(info.getAmmPotency());
                        consumption.setAlkaliValue(info.getWeight() * info.getAlkPotency() / 0.32f);
                        consumption.setAlkaliConcentration(info.getAlkPotency());
                    } else if (info.getAlkaliFlag() == 0 && info.getAmmoniaFlag() == 1) {
                        consumption.setAlkaliValue(0f);
                        consumption.setAlkaliConcentration(0f);
                        consumption.setAmmoniaValue(info.getWeight() * info.getAmmPotency());
                        consumption.setAmmoniaConcentration(info.getAmmPotency());
                    } else if (info.getAlkaliFlag() == 1 && info.getAmmoniaFlag() == 0) {
                        consumption.setAlkaliValue(info.getWeight() * info.getAlkPotency());
                        consumption.setAlkaliConcentration(info.getAlkPotency());
                        consumption.setAmmoniaValue(0f);
                        consumption.setAmmoniaConcentration(0f);
                    }
                    consumption.setTotals(info.getWeight());
                    consumptionMapper.insertSelective(consumption);
                }
            }
        }
    }

    private void commit(AuxiliaryAddDTO auxiliaryAddDTO) {
        save(auxiliaryAddDTO);

        Long id = auxiliaryAddDTO.getHead().getCode();
        Integer periods = auxiliaryAddDTO.getHead().getPeriods();
        Integer periodId = auxiliaryAddDTO.getHead().getPeriodCode();

        operationService.updateAllEndTime(periodId, periods);

        //按工序统计+全部统计
        BasicInfoPrecursorProcessTypeExample example = new BasicInfoPrecursorProcessTypeExample();
        example.createCriteria().andTypesEqualTo(new Integer(1).byteValue());
        List<BasicInfoPrecursorProcessType> processTypes = processTypeMapper.selectByExample(example);

        Float tTotal = 0f;
        Float aAmmValue = 0f;
        Float aAlkValue = 0f;
        Float tTankTotal = 0f;

        for (int i = 0; i < processTypes.size(); i++) {
            Float total = 0f;
            Float ammValue = 0f;
            Float alkValue = 0f;
            if (processTypes.get(i).getCode() == ProcessEnum.STOCKIN.getProcessId()) {
                AuxiliaryMaterialsStatisticDataDetailsDeliveryExample example1 = new AuxiliaryMaterialsStatisticDataDetailsDeliveryExample();
                example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(processTypes.get(i).getCode());
                List<AuxiliaryMaterialsStatisticDataDetailsDelivery> deliveries = dataDetailsDeliveryMapper.selectByExample(example1);
                for (int l = 0; l < deliveries.size(); l++) {
                    total += deliveries.get(l).getTotals();
                    ammValue += deliveries.get(l).getAmmoniaValue();
                    alkValue += deliveries.get(l).getAlkaliValue();
                }
            }
            if (processTypes.get(i).getCode() == ProcessEnum.TANK.getProcessId()) {
                AuxiliaryMaterialsStatisticDetailsTankAreaExample example1 = new AuxiliaryMaterialsStatisticDetailsTankAreaExample();
                example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(processTypes.get(i).getCode());
                List<AuxiliaryMaterialsStatisticDetailsTankArea> tankAreas = tankAreaMapper.selectByExample(example1);
                for (int l = 0; l < tankAreas.size(); l++) {
                    total += tankAreas.get(l).getVolumes();
                    ammValue += tankAreas.get(l).getAmmoniaValue();
                    alkValue += tankAreas.get(l).getAlkaliValue();
                    tTankTotal += tankAreas.get(l).getTotals();
                }
            }
            if (processTypes.get(i).getCode() == ProcessEnum.WORKSHOP.getProcessId()) {
                AuxiliaryMaterialsStatisticDetailsPlantExample example1 = new AuxiliaryMaterialsStatisticDetailsPlantExample();
                example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(processTypes.get(i).getCode());
                List<AuxiliaryMaterialsStatisticDetailsPlant> plants = plantMapper.selectByExample(example1);
                for (int l = 0; l < plants.size(); l++) {
                    total += plants.get(l).getTotals();
                    ammValue += plants.get(l).getAmmoniaValue();
                    alkValue += plants.get(l).getAlkaliValue();
                }
            }
            if (processTypes.get(i).getCode() == ProcessEnum.CONSUMPTION.getProcessId()) {
                AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample example1 = new AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample();
                example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(processTypes.get(i).getCode());
                List<AuxiliaryMaterialsStatisticDetailsIngredientsConsumption> consumptions = consumptionMapper.selectByExample(example1);
                for (int l = 0; l < consumptions.size(); l++) {
                    total += consumptions.get(l).getTotals();
                    ammValue += consumptions.get(l).getAmmoniaValue();
                    alkValue += consumptions.get(l).getAlkaliValue();
                }
            }
            if (processTypes.get(i).getCode() != ProcessEnum.TANK.getProcessId()) {
                tTotal += total;
            } else {
                tTotal += tTankTotal;
            }
            aAlkValue += alkValue;
            aAmmValue += ammValue;
            AuxiliaryMaterialsStatisticDataList list = new AuxiliaryMaterialsStatisticDataList();
            list.setStatisticCode(id);
            list.setPeriods(periods);
            list.setProcessCode(processTypes.get(i).getCode());
            list.setAlkaliValue(alkValue);
            list.setAmmoniaValue(ammValue);
            list.setTotals(total);
            dataListMapper.insertSelective(list);
        }
        AuxiliaryMaterialsStatisticByLineTotals totals = new AuxiliaryMaterialsStatisticByLineTotals();
        totals.setPeriods(periods);
        totals.setStatisticCode(id);
        totals.setAlkaliValue(aAlkValue);
        totals.setAmmoniaValue(aAmmValue);
        totals.setTotals(tTotal);
        lineTotalsMapper.insertSelective(totals);

        //按产线统计
        BasicInfoPrecursorProductionLineExample example1 = new BasicInfoPrecursorProductionLineExample();
        example1.createCriteria();
        List<BasicInfoPrecursorProductionLine> lines = lineMapper.selectByExample(example1);

        for (int i = 0; i < lines.size(); i++) {
            Float totalLine = 0f;
            Float ammValueLine = 0f;
            Float alkValueLine = 0f;
            AuxiliaryMaterialsStatisticDataDetailsDeliveryExample example2 = new AuxiliaryMaterialsStatisticDataDetailsDeliveryExample();
            example2.createCriteria().andStatisticCodeEqualTo(id);
            List<AuxiliaryMaterialsStatisticDataDetailsDelivery> deliveries = dataDetailsDeliveryMapper.selectByExample(example2);
            for (int l = 0; l < deliveries.size(); l++) {
                BasicInfoPrecursorMaterialLineWeightExample example3 = new BasicInfoPrecursorMaterialLineWeightExample();
                example3.createCriteria().andMaterialCodeEqualTo(deliveries.get(l).getMaterialCode()).andLineCodeEqualTo(lines.get(i).getCode());
                List<BasicInfoPrecursorMaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(example3);
                if (lineWeights.size() != 0) {
                    totalLine += deliveries.get(l).getTotals() * lineWeights.get(0).getWeightValue();
                    ammValueLine += deliveries.get(l).getAmmoniaValue() * lineWeights.get(0).getWeightValue();
                    alkValueLine += deliveries.get(l).getAlkaliValue() * lineWeights.get(0).getWeightValue();
                }
            }
            AuxiliaryMaterialsStatisticDetailsTankAreaExample areaExample = new AuxiliaryMaterialsStatisticDetailsTankAreaExample();
            areaExample.createCriteria().andStatisticCodeEqualTo(id);
            List<AuxiliaryMaterialsStatisticDetailsTankArea> tankAreas = tankAreaMapper.selectByExample(areaExample);
            for (int l = 0; l < tankAreas.size(); l++) {
                BasicInfoPrecursorMaterialLineWeightExample example3 = new BasicInfoPrecursorMaterialLineWeightExample();
                example3.createCriteria().andMaterialCodeEqualTo(tankAreas.get(l).getMaterialCode()).andLineCodeEqualTo(lines.get(i).getCode());
                List<BasicInfoPrecursorMaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(example3);
                if (lineWeights.size() != 0) {
                    totalLine += tankAreas.get(l).getTotals() * lineWeights.get(0).getWeightValue();
                    ammValueLine += tankAreas.get(l).getAmmoniaValue() * lineWeights.get(0).getWeightValue();
                    alkValueLine += tankAreas.get(l).getAlkaliValue() * lineWeights.get(0).getWeightValue();
                }
            }
            AuxiliaryMaterialsStatisticDetailsPlantExample plantExample = new AuxiliaryMaterialsStatisticDetailsPlantExample();
            plantExample.createCriteria().andStatisticCodeEqualTo(id);
            List<AuxiliaryMaterialsStatisticDetailsPlant> plants = plantMapper.selectByExample(plantExample);
            for (int l = 0; l < plants.size(); l++) {
                BasicInfoPrecursorMaterialLineWeightExample example3 = new BasicInfoPrecursorMaterialLineWeightExample();
                example3.createCriteria().andMaterialCodeEqualTo(plants.get(l).getMaterialCode()).andLineCodeEqualTo(lines.get(i).getCode());
                List<BasicInfoPrecursorMaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(example3);
                if (lineWeights.size() != 0) {
                    totalLine += plants.get(l).getTotals() * lineWeights.get(0).getWeightValue();
                    ammValueLine += plants.get(l).getAmmoniaValue() * lineWeights.get(0).getWeightValue();
                    alkValueLine += plants.get(l).getAlkaliValue() * lineWeights.get(0).getWeightValue();
                }
            }
            AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample consumptionExample = new AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample();
            consumptionExample.createCriteria().andStatisticCodeEqualTo(id);
            List<AuxiliaryMaterialsStatisticDetailsIngredientsConsumption> consumptions = consumptionMapper.selectByExample(consumptionExample);
            for (int l = 0; l < consumptions.size(); l++) {
                BasicInfoPrecursorMaterialLineWeightExample example3 = new BasicInfoPrecursorMaterialLineWeightExample();
                example3.createCriteria().andMaterialCodeEqualTo(consumptions.get(l).getMaterialCode()).andLineCodeEqualTo(lines.get(i).getCode());
                List<BasicInfoPrecursorMaterialLineWeight> lineWeights = lineWeightMapper.selectByExample(example3);
                if (lineWeights.size() != 0) {
                    totalLine += consumptions.get(l).getTotals() * lineWeights.get(0).getWeightValue();
                    ammValueLine += consumptions.get(l).getAmmoniaValue() * lineWeights.get(0).getWeightValue();
                    alkValueLine += consumptions.get(l).getAlkaliValue() * lineWeights.get(0).getWeightValue();
                }
            }
            AuxiliaryMaterialsStatisticByLineDetail lineDetail = new AuxiliaryMaterialsStatisticByLineDetail();
            lineDetail.setLineCode(lines.get(i).getCode());
            lineDetail.setPeriods(periods);
            lineDetail.setStatisticCode(id);
            lineDetail.setTotals(totalLine);
            lineDetail.setAmmoniaValue(ammValueLine);
            lineDetail.setAlkaliValue(alkValueLine);
            lineDetailMapper.insertSelective(lineDetail);

            AuxiliaryMaterialsStatisticHead head = auxiliaryAddDTO.getHead();
            head.setFlag(true);
            headMapper.updateByPrimaryKeySelective(head);
        }
    }

    @Override
    public Page getPageUnCommit(AuxiliaryMaterialsStatisticHead head, Integer page, Integer size) {
        Date start = head.getStartTime();
        Date end = head.getEndTime();
        Integer periodId = head.getPeriodCode();
        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        if (start != null) {
            example.createCriteria().andStartTimeBetween(start, end).andFlagEqualTo(false).andPeriodCodeEqualTo(periodId);
        } else {
            example.createCriteria().andFlagEqualTo(false).andPeriodCodeEqualTo(periodId);
        }
        example.setOrderByClause("periods desc");
        List<AuxiliaryMaterialsStatisticHead> heads = headMapper.selectByExample(example);
        List<AuxiliaryHeadDTO> ans = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            AuxiliaryHeadDTO auxiliaryHeadDTO = new AuxiliaryHeadDTO();
            auxiliaryHeadDTO.setHead(heads.get(i));
            auxiliaryHeadDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
            ans.add(auxiliaryHeadDTO);
        }

        return new Page(ans, page, size);
    }

    @Override
    public Page getPageCommit(AuxiliaryMaterialsStatisticHead head, Integer page, Integer size) {
        Date start = head.getStartTime();
        Date end = head.getEndTime();
        Integer periodId = head.getPeriodCode();
        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        if (start != null) {
            example.createCriteria().andStartTimeBetween(start, end).andFlagEqualTo(true).andPeriodCodeEqualTo(periodId);
        } else {
            example.createCriteria().andFlagEqualTo(true).andPeriodCodeEqualTo(periodId);
        }
        example.setOrderByClause("periods desc");
        List<AuxiliaryMaterialsStatisticHead> heads = headMapper.selectByExample(example);
        List<AuxiliaryDetailDTO> detailDTOS = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            AuxiliaryMaterialsStatisticDataListExample example1 = new AuxiliaryMaterialsStatisticDataListExample();
            example1.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
            List<AuxiliaryMaterialsStatisticDataList> lists = dataListMapper.selectByExample(example1);
            for (int l = 0; l < lists.size(); l++) {
                if (lists.get(l).getProcessCode() == ProcessEnum.STOCKIN.getProcessId()) {
                    AuxiliaryDetailDTO detailDTO = new AuxiliaryDetailDTO();
                    AuxiliaryMaterialsStatisticDataDetailsDeliveryExample example2 = new AuxiliaryMaterialsStatisticDataDetailsDeliveryExample();
                    example2.createCriteria().andProcessCodeEqualTo(ProcessEnum.STOCKIN.getProcessId()).andStatisticCodeEqualTo(heads.get(i).getCode());

                    detailDTO.setHead(heads.get(i));
                    //detailDTO.setProcesses(dataDetailsDeliveryMapper.selectByExample(example2));
                    detailDTO.setProcessName(ProcessEnum.STOCKIN.getProcessName());
                    detailDTO.setProcessTotal(lists.get(l));
                    detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
                    detailDTOS.add(detailDTO);
                }
                if (lists.get(l).getProcessCode() == ProcessEnum.TANK.getProcessId()) {
                    AuxiliaryDetailDTO detailDTO = new AuxiliaryDetailDTO();
                    AuxiliaryMaterialsStatisticDetailsTankAreaExample example2 = new AuxiliaryMaterialsStatisticDetailsTankAreaExample();
                    example2.createCriteria().andProcessCodeEqualTo(ProcessEnum.TANK.getProcessId()).andStatisticCodeEqualTo(heads.get(i).getCode());

                    detailDTO.setHead(heads.get(i));
                    //detailDTO.setProcesses(tankAreaMapper.selectByExample(example2));
                    detailDTO.setProcessName(ProcessEnum.TANK.getProcessName());
                    detailDTO.setProcessTotal(lists.get(l));
                    detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
                    detailDTOS.add(detailDTO);
                }
                if (lists.get(l).getProcessCode() == ProcessEnum.WORKSHOP.getProcessId()) {
                    AuxiliaryDetailDTO detailDTO = new AuxiliaryDetailDTO();
                    AuxiliaryMaterialsStatisticDetailsPlantExample example2 = new AuxiliaryMaterialsStatisticDetailsPlantExample();
                    example2.createCriteria().andProcessCodeEqualTo(ProcessEnum.STOCKIN.getProcessId()).andStatisticCodeEqualTo(heads.get(i).getCode());

                    detailDTO.setHead(heads.get(i));
                    //detailDTO.setProcesses(plantMapper.selectByExample(example2));
                    detailDTO.setProcessName(ProcessEnum.WORKSHOP.getProcessName());
                    detailDTO.setProcessTotal(lists.get(l));
                    detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
                    detailDTOS.add(detailDTO);
                }
                if (lists.get(l).getProcessCode() == ProcessEnum.CONSUMPTION.getProcessId()) {
                    AuxiliaryDetailDTO detailDTO = new AuxiliaryDetailDTO();
                    AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample example2 = new AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample();
                    example2.createCriteria().andProcessCodeEqualTo(ProcessEnum.CONSUMPTION.getProcessId()).andStatisticCodeEqualTo(heads.get(i).getCode());

                    detailDTO.setHead(heads.get(i));
                    //detailDTO.setProcesses(consumptionMapper.selectByExample(example2));
                    detailDTO.setProcessName(ProcessEnum.CONSUMPTION.getProcessName());
                    detailDTO.setProcessTotal(lists.get(l));
                    detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
                    detailDTOS.add(detailDTO);
                }
            }
        }
        return new Page(detailDTOS, page, size);
    }

    @Override
    public Map stasticByProcess(AuxiliaryMaterialsStatisticHead head) {
        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        example.createCriteria().andStartTimeEqualTo(head.getStartTime()).andPeriodCodeEqualTo(head.getPeriodCode()).andFlagEqualTo(true);
        List<AuxiliaryMaterialsStatisticHead> heads = headMapper.selectByExample(example);

        if (heads.size() == 0)
            return null;
        Long id = heads.get(0).getCode();
        Map<String, Object> ans = new HashMap<>();
        List<AuxiliaryStatisticDTO> processes = new ArrayList();

        BasicInfoPrecursorProcessTypeExample example1 = new BasicInfoPrecursorProcessTypeExample();
        example1.createCriteria().andTypesEqualTo(new Integer("1").byteValue());
        List<BasicInfoPrecursorProcessType> processTypes = processTypeMapper.selectByExample(example1);

        for (int i = 0; i < processTypes.size(); i++) {
            AuxiliaryStatisticDTO statisticDTO = new AuxiliaryStatisticDTO();
            AuxiliaryMaterialsStatisticDataListExample example2 = new AuxiliaryMaterialsStatisticDataListExample();
            example2.createCriteria().andProcessCodeEqualTo(processTypes.get(i).getCode()).andStatisticCodeEqualTo(id);
            List<AuxiliaryMaterialsStatisticDataList> lists = dataListMapper.selectByExample(example2);
            if (lists.size() != 0) {
                statisticDTO.setHead(heads.get(0));
                statisticDTO.setProcessName(processTypes.get(i).getProcessName());
                statisticDTO.setProcess(lists.get(0));
                statisticDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(0).getPeriodCode()).getName());
                processes.add(statisticDTO);
            }
        }

        AuxiliaryMaterialsStatisticByLineTotalsExample example2 = new AuxiliaryMaterialsStatisticByLineTotalsExample();
        example2.createCriteria().andStatisticCodeEqualTo(id);
        List<AuxiliaryMaterialsStatisticByLineTotals> totals = lineTotalsMapper.selectByExample(example2);
        if (totals.size() != 0) {
            ans.put("weight", totals.get(0).getTotals());
            ans.put("amm", totals.get(0).getAmmoniaValue());
            ans.put("alk", totals.get(0).getAlkaliValue());
        }
        ans.put("table", processes);
        return ans;
    }

    @Override
    public List stasticByLine(AuxiliaryMaterialsStatisticHead head) {
        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        example.createCriteria().andStartTimeEqualTo(head.getStartTime()).andPeriodCodeEqualTo(head.getPeriodCode()).andFlagEqualTo(true);
        List<AuxiliaryMaterialsStatisticHead> heads = headMapper.selectByExample(example);

        if (heads.size() == 0)
            return null;
        Long id = heads.get(0).getCode();
        List<AuxiliaryStatisticDTO> ans = new ArrayList();

        BasicInfoPrecursorProductionLineExample example1 = new BasicInfoPrecursorProductionLineExample();
        example1.createCriteria();
        List<BasicInfoPrecursorProductionLine> lines = lineMapper.selectByExample(example1);

        for (int i = 0; i < lines.size(); i++) {
            AuxiliaryStatisticDTO statisticDTO = new AuxiliaryStatisticDTO();
            AuxiliaryMaterialsStatisticByLineDetailExample example2 = new AuxiliaryMaterialsStatisticByLineDetailExample();
            example2.createCriteria().andLineCodeEqualTo(lines.get(i).getCode()).andStatisticCodeEqualTo(id);
            List<AuxiliaryMaterialsStatisticByLineDetail> details = lineDetailMapper.selectByExample(example2);
            if (details.size() != 0) {
                statisticDTO.setHead(heads.get(0));
                statisticDTO.setLineName(lines.get(i).getName());
                statisticDTO.setProcess(details.get(0));
                statisticDTO.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(0).getPeriodCode()).getName());
                ans.add(statisticDTO);
            }
        }
        return ans;
    }

    @Override
    public List processCur(AuxiliaryMaterialsStatisticHead head, Integer processId) {
        Integer periodId = head.getPeriodCode();
        Date start = head.getStartTime();
        Date end = head.getEndTime();

        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andStartTimeBetween(start, end);
        List<AuxiliaryMaterialsStatisticHead> heads = headMapper.selectByExample(example);

        List<GoodInCompareDTO> ans = new ArrayList<>();

        for (int i = 0; i < heads.size(); i++) {
            AuxiliaryMaterialsStatisticDataListExample example1 = new AuxiliaryMaterialsStatisticDataListExample();
            example1.createCriteria().andProcessCodeEqualTo(processId).andStatisticCodeEqualTo(heads.get(i).getCode());
            List<AuxiliaryMaterialsStatisticDataList> lists = dataListMapper.selectByExample(example1);
            if (lists.size() != 0) {
                GoodInCompareDTO compareDTO = new GoodInCompareDTO();
                compareDTO.setPeriodNum(heads.get(i).getPeriods());
                compareDTO.setAmm(lists.get(0).getAmmoniaValue());
                compareDTO.setAlk(lists.get(0).getAlkaliValue());
                ans.add(compareDTO);
            }
        }
        return ans;
    }

    @Override
    public List lineCur(AuxiliaryMaterialsStatisticHead head, Integer lineId) {
        Integer periodId = head.getPeriodCode();
        Date start = head.getStartTime();
        Date end = head.getEndTime();

        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andStartTimeBetween(start, end);
        List<AuxiliaryMaterialsStatisticHead> heads = headMapper.selectByExample(example);

        List<GoodInCompareDTO> ans = new ArrayList<>();

        for (int i = 0; i < heads.size(); i++) {
            AuxiliaryMaterialsStatisticByLineDetailExample example1 = new AuxiliaryMaterialsStatisticByLineDetailExample();
            example1.createCriteria().andLineCodeEqualTo(lineId).andStatisticCodeEqualTo(heads.get(i).getCode());
            List<AuxiliaryMaterialsStatisticByLineDetail> lists = lineDetailMapper.selectByExample(example1);
            if (lists.size() != 0) {
                GoodInCompareDTO compareDTO = new GoodInCompareDTO();
                compareDTO.setPeriodNum(heads.get(i).getPeriods());
                compareDTO.setAmm(lists.get(0).getAmmoniaValue());
                compareDTO.setAlk(lists.get(0).getAlkaliValue());
                ans.add(compareDTO);
            }
        }
        return ans;
    }

    @Override
    public AuxiliaryDetailDTO detail(Long id) {
        AuxiliaryDetailDTO ans = new AuxiliaryDetailDTO();
        AuxiliaryMaterialsStatisticDataList dataList = dataListMapper.selectByPrimaryKey(id);
        AuxiliaryMaterialsStatisticHead head = headMapper.selectByPrimaryKey(dataList.getStatisticCode());
        ans.setHead(head);
        ans.setProcessTotal(dataList);
        ans.setPeriodName(periodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
        ans.setProcessName(processTypeMapper.selectByPrimaryKey(dataList.getProcessCode()).getProcessName());
        if (dataList.getProcessCode() == ProcessEnum.STOCKIN.getProcessId()) {
            AuxiliaryMaterialsStatisticDataDetailsDeliveryExample example = new AuxiliaryMaterialsStatisticDataDetailsDeliveryExample();
            example.createCriteria().andStatisticCodeEqualTo(head.getCode());
            List<AuxiliaryMaterialsStatisticDataDetailsDelivery> deliveries = dataDetailsDeliveryMapper.selectByExample(example);
            List<AuxiliaryProcessDTO<AuxiliaryMaterialsStatisticDataDetailsDelivery>> processDTOS = new ArrayList<>();
            for (int i = 0; i < deliveries.size(); i++) {
                AuxiliaryProcessDTO<AuxiliaryMaterialsStatisticDataDetailsDelivery> processDTO = new AuxiliaryProcessDTO<>();
                processDTO.setProcess(deliveries.get(i));
                processDTO.setMaterialName(materialDetailsMapper.selectByPrimaryKey(deliveries.get(i).getMaterialCode()).getMaterialName());
                processDTOS.add(processDTO);
            }
            ans.setProcesses(processDTOS);
        }
        if (dataList.getProcessCode() == ProcessEnum.TANK.getProcessId()) {
            AuxiliaryMaterialsStatisticDetailsTankAreaExample example = new AuxiliaryMaterialsStatisticDetailsTankAreaExample();
            example.createCriteria().andStatisticCodeEqualTo(head.getCode());
            List<AuxiliaryMaterialsStatisticDetailsTankArea> tankAreas = tankAreaMapper.selectByExample(example);
            List<AuxiliaryProcessDTO<AuxiliaryMaterialsStatisticDetailsTankArea>> processDTOS = new ArrayList<>();
            for (int i = 0; i < tankAreas.size(); i++) {
                AuxiliaryProcessDTO<AuxiliaryMaterialsStatisticDetailsTankArea> processDTO = new AuxiliaryProcessDTO<>();
                processDTO.setProcess(tankAreas.get(i));
                processDTO.setMaterialName(materialDetailsMapper.selectByPrimaryKey(tankAreas.get(i).getMaterialCode()).getMaterialName());
                processDTOS.add(processDTO);
            }
            ans.setProcesses(processDTOS);
        }
        if (dataList.getProcessCode() == ProcessEnum.WORKSHOP.getProcessId()) {
            AuxiliaryMaterialsStatisticDetailsPlantExample example = new AuxiliaryMaterialsStatisticDetailsPlantExample();
            example.createCriteria().andStatisticCodeEqualTo(head.getCode());
            List<AuxiliaryMaterialsStatisticDetailsPlant> plants = plantMapper.selectByExample(example);
            List<AuxiliaryProcessDTO<AuxiliaryMaterialsStatisticDetailsPlant>> processDTOS = new ArrayList<>();
            for (int i = 0; i < plants.size(); i++) {
                AuxiliaryProcessDTO<AuxiliaryMaterialsStatisticDetailsPlant> processDTO = new AuxiliaryProcessDTO<>();
                processDTO.setProcess(plants.get(i));
                processDTO.setMaterialName(materialDetailsMapper.selectByPrimaryKey(plants.get(i).getMaterialCode()).getMaterialName());
                processDTOS.add(processDTO);
            }
            ans.setProcesses(processDTOS);
        }
        if (dataList.getProcessCode() == ProcessEnum.CONSUMPTION.getProcessId()) {
            AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample example = new AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample();
            example.createCriteria().andStatisticCodeEqualTo(head.getCode());
            List<AuxiliaryMaterialsStatisticDetailsIngredientsConsumption> consumptions = consumptionMapper.selectByExample(example);
            List<AuxiliaryProcessDTO<AuxiliaryMaterialsStatisticDetailsIngredientsConsumption>> processDTOS = new ArrayList<>();
            for (int i = 0; i < consumptions.size(); i++) {
                AuxiliaryProcessDTO<AuxiliaryMaterialsStatisticDetailsIngredientsConsumption> processDTO = new AuxiliaryProcessDTO<>();
                processDTO.setProcess(consumptions.get(i));
                processDTO.setMaterialName(materialDetailsMapper.selectByPrimaryKey(consumptions.get(i).getMaterialCode()).getMaterialName());
                processDTOS.add(processDTO);
            }
            ans.setProcesses(processDTOS);
        }
        return ans;
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for (Long id : ids)
            delete(id);
    }

    @Override
    public List getAllDate() {
        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        example.createCriteria().andFlagEqualTo(true);
        List<AuxiliaryMaterialsStatisticHead> heads = headMapper.selectByExample(example);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            ans.add(ComUtil.dateToString(heads.get(i).getStartTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        return ans;
    }

    @Override
    public List getVolumeWeight(Integer processId) {
        List<Map<String, Object>> ans = new ArrayList<>();
        BasicInfoPrecursorMaterialDetailsExample example = new BasicInfoPrecursorMaterialDetailsExample();
        example.createCriteria().andProcessCodeEqualTo(processId).andDataTypeEqualTo(new Integer(0).byteValue());

        List<BasicInfoPrecursorMaterialDetails> details = basicInfoPrecursorMaterialDetailsMapper.selectByExample(example);
        for (int i = 0; i < details.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", details.get(i).getCode());
            map.put("name", details.get(i).getMaterialName());
            BasicInfoPrecursorMaterialPlcMapExample example1 = new BasicInfoPrecursorMaterialPlcMapExample();
            example1.createCriteria().andMaterialCodeEqualTo(details.get(i).getCode());
            List<BasicInfoPrecursorMaterialPlcMap> plcMaps = plcMapMapper.selectByExample(example1);
            if (plcMaps.size() == 0) {
                map.put("value", -2);//没有这个地址
                map.put("address", "No address");
            } else {
                Integer plcId = plcMaps.get(0).getPlcCode();
                BasicInfoPrecursorPlcAddress address = addressMapper.selectByPrimaryKey(plcId);
                Float data = RealTimeUtil.timelyHttpMethod("http://192.168.190.173:10086/api/Snapshot?tagName", address.getAddress());
                if (null == data) {
                    map.put("value", -1);//有地址，没有返回值
                } else {
                    map.put("value", data);
                }
            }
            ans.add(map);
        }
        return ans;
    }

    @Override
    public Page defaultPage() {
        AuxiliaryMaterialsStatisticHeadExample example = new AuxiliaryMaterialsStatisticHeadExample();
        example.createCriteria().andFlagEqualTo(true);
        List<AuxiliaryMaterialsStatisticHead> heads = headMapper.selectByExample(example);
        if (heads.size() == 0)
            return new Page(new ArrayList(), 1, 10);
        AuxiliaryMaterialsStatisticHead head = heads.get(0);
        for (int i = 1; i < heads.size(); i++) {
            if (head.getPeriods() < heads.get(i).getPeriods())
                head = heads.get(i);
        }
        List<AuxiliaryDetailDTO> detailDTOS = new ArrayList<>();
        AuxiliaryMaterialsStatisticDataListExample example1 = new AuxiliaryMaterialsStatisticDataListExample();
        example1.createCriteria().andStatisticCodeEqualTo(head.getCode());
        List<AuxiliaryMaterialsStatisticDataList> lists = dataListMapper.selectByExample(example1);
        for (int l = 0; l < lists.size(); l++) {
            if (lists.get(l).getProcessCode() == ProcessEnum.STOCKIN.getProcessId()) {
                AuxiliaryDetailDTO detailDTO = new AuxiliaryDetailDTO();
                AuxiliaryMaterialsStatisticDataDetailsDeliveryExample example2 = new AuxiliaryMaterialsStatisticDataDetailsDeliveryExample();
                example2.createCriteria().andProcessCodeEqualTo(ProcessEnum.STOCKIN.getProcessId()).andStatisticCodeEqualTo(head.getCode());

                detailDTO.setHead(head);
                //detailDTO.setProcesses(dataDetailsDeliveryMapper.selectByExample(example2));
                detailDTO.setProcessName(ProcessEnum.STOCKIN.getProcessName());
                detailDTO.setProcessTotal(lists.get(l));
                detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
                detailDTOS.add(detailDTO);
            }
            if (lists.get(l).getProcessCode() == ProcessEnum.TANK.getProcessId()) {
                AuxiliaryDetailDTO detailDTO = new AuxiliaryDetailDTO();
                AuxiliaryMaterialsStatisticDetailsTankAreaExample example2 = new AuxiliaryMaterialsStatisticDetailsTankAreaExample();
                example2.createCriteria().andProcessCodeEqualTo(ProcessEnum.TANK.getProcessId()).andStatisticCodeEqualTo(head.getCode());

                detailDTO.setHead(head);
                //detailDTO.setProcesses(tankAreaMapper.selectByExample(example2));
                detailDTO.setProcessName(ProcessEnum.TANK.getProcessName());
                detailDTO.setProcessTotal(lists.get(l));
                detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
                detailDTOS.add(detailDTO);
            }
            if (lists.get(l).getProcessCode() == ProcessEnum.WORKSHOP.getProcessId()) {
                AuxiliaryDetailDTO detailDTO = new AuxiliaryDetailDTO();
                AuxiliaryMaterialsStatisticDetailsPlantExample example2 = new AuxiliaryMaterialsStatisticDetailsPlantExample();
                example2.createCriteria().andProcessCodeEqualTo(ProcessEnum.STOCKIN.getProcessId()).andStatisticCodeEqualTo(head.getCode());

                detailDTO.setHead(head);
                //detailDTO.setProcesses(plantMapper.selectByExample(example2));
                detailDTO.setProcessName(ProcessEnum.WORKSHOP.getProcessName());
                detailDTO.setProcessTotal(lists.get(l));
                detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
                detailDTOS.add(detailDTO);
            }
            if (lists.get(l).getProcessCode() == ProcessEnum.CONSUMPTION.getProcessId()) {
                AuxiliaryDetailDTO detailDTO = new AuxiliaryDetailDTO();
                AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample example2 = new AuxiliaryMaterialsStatisticDetailsIngredientsConsumptionExample();
                example2.createCriteria().andProcessCodeEqualTo(ProcessEnum.CONSUMPTION.getProcessId()).andStatisticCodeEqualTo(head.getCode());

                detailDTO.setHead(head);
                //detailDTO.setProcesses(consumptionMapper.selectByExample(example2));
                detailDTO.setProcessName(ProcessEnum.CONSUMPTION.getProcessName());
                detailDTO.setProcessTotal(lists.get(l));
                detailDTO.setPeriodName(periodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
                detailDTOS.add(detailDTO);
            }
        }
        return new Page(detailDTOS, 1, 10);
    }
}

