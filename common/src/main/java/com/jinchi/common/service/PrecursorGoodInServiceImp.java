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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class PrecursorGoodInServiceImp implements PrecursorGoodInService {
    @Autowired
    BasicInfoPrecursorProductionLineMapper lineMapper;
    @Autowired
    BasicInfoPrecursorMaterialLineWeightMapper lineWeightMapper;
    @Autowired
    GoodsInProcessStatisticHeadMapper goodsInProcessStatisticHeadMapper;
    @Autowired
    BasicInfoPrecursorPeriodMapper basicInfoPrecursorPeriodMapper;
    @Autowired
    MaterialDeliveryStatisticHeadMapper materialDeliveryStatisticHeadMapper;
    @Autowired
    ProductStorageStatisticHeadMapper productStorageStatisticHeadMapper;
    @Autowired
    BasicInfoPrecursorProcessTypeMapper basicInfoPrecursorProcessTypeMapper;
    @Autowired
    BasicInfoPrecursorMaterialDetailsMapper basicInfoPrecursorMaterialDetailsMapper;
    @Autowired
    GoodsInProcessStatisticMonocrystalDeployMapper monocrystalDeployMapper;
    @Autowired
    GoodsInProcessStatisticAgeingMapper ageingMapper;
    @Autowired
    GoodsInProcessStatisticDryingMapper dryingMapper;
    @Autowired
    GoodsInProcessStatisticSaltMixtureDeployMapper saltMixtureDeployMapper;
    @Autowired
    GoodsInProcessStatisticOthersMapper othersMapper;
    @Autowired
    GoodsInProcessStatisticCompoundMapper compoundMapper;
    @Autowired
    GoodsInProcessStatisticByProcessDetailMapper statisticByProcessDetailMapper;
    @Autowired
    GoodsInProcessStatisticByLineDetailMapper lineDetailMapper;
    @Autowired
    GoodsInProcessStatisticByProcessTotalMapper processTotalMapper;
    @Autowired
    BasicInfoPrecursorProductionLineMapper basicInfoPrecursorProductionLineMapper;
    @Autowired
    GoodsInProcessStatisticLineProductionsMapper goodsInProcessStatisticLineProductionsMapper;
    @Autowired
    ProductionBatchRuleDetailMapper ruleDetailMapper;
    @Autowired
    BasicInfoPrecursorMaterialPlcMapMapper plcMapMapper;
    @Autowired
    BasicInfoPrecursorPlcAddressMapper addressMapper;
    @Autowired
    BasicInfoCompoundCellVolumesMapper cellVolumesMapper;
    @Autowired
    ProductionInstrumentAddressMapper instrumentAddressMapper;
    @Autowired
    AuxiliaryMaterialsStatisticHeadMapper auxiliaryHeadMapper;

    @Override
    public List getAll(String startTime, String endTime, Integer periodId, Byte flag) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        Date end = new Date();
        if (!"".equals(startTime)) {
            try {
                start = df.parse(startTime);
                end = df.parse(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        List<Integer> ids = new ArrayList<>();
        if (periodId == null) {
            BasicInfoPrecursorPeriodExample examplee = new BasicInfoPrecursorPeriodExample();
            examplee.createCriteria();
            List<BasicInfoPrecursorPeriod> precursorPeriods = basicInfoPrecursorPeriodMapper.selectByExample(examplee);
            for (int i = 0; i < precursorPeriods.size(); i++) {
                ids.add(precursorPeriods.get(i).getCode());
            }
        }
        GoodsInProcessStatisticHeadExample example = new GoodsInProcessStatisticHeadExample();
        GoodsInProcessStatisticHeadExample.Criteria criteria = example.createCriteria();
        if (!"".equals(startTime)) {
            if (periodId != null) {
                criteria.andPeriodCodeEqualTo(periodId);
            } else {
                criteria.andPeriodCodeIn(ids);
            }
            criteria.andStartTimeBetween(start, end).andFlagEqualTo(flag);
        } else {
            if (periodId != null) {
                criteria.andPeriodCodeEqualTo(periodId);
            } else {
                criteria.andPeriodCodeIn(ids);
            }
            criteria.andFlagEqualTo(flag);
        }
        example.setOrderByClause("line_name desc");
        List<GoodsInProcessStatisticHead> heads = goodsInProcessStatisticHeadMapper.selectByExample(example);
        List<GoodInHeadDTO> ans = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            GoodInHeadDTO headDTO = new GoodInHeadDTO();
            headDTO.setGoodsInProcessStatisticHead(heads.get(i));
            headDTO.setPeriod(basicInfoPrecursorPeriodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
            ans.add(headDTO);
        }
        return ans;
    }

    @Override
    public Page page(String startTime, String endTime, Integer periodId, Integer page, Integer size) {
        return new Page(getAll(startTime, endTime, periodId, new Integer(0).byteValue()), page, size);
    }

    @Override
    public Object addComfirm(Integer periodId, Integer lineName, String startTime, String endTime) {
        BasicInfoPrecursorPeriod period = basicInfoPrecursorPeriodMapper.selectByPrimaryKey(periodId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = new Date();
        Date end = new Date();
        if (!"".equals(startTime)) {
            try {
                start = df.parse(startTime);
                end = df.parse(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        MaterialDeliveryStatisticHeadExample example = new MaterialDeliveryStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(lineName);
        List<MaterialDeliveryStatisticHead> mHeads = materialDeliveryStatisticHeadMapper.selectByExample(example);

        ProductStorageStatisticHeadExample example1 = new ProductStorageStatisticHeadExample();
        example1.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(lineName);
        List<ProductStorageStatisticHead> sHeads = productStorageStatisticHeadMapper.selectByExample(example1);

        AuxiliaryMaterialsStatisticHeadExample example4 = new AuxiliaryMaterialsStatisticHeadExample();
        example4.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(lineName);
        List<AuxiliaryMaterialsStatisticHead> aHeads = auxiliaryHeadMapper.selectByExample(example4);

//        GoodsInProcessStatisticHeadExample example2 = new GoodsInProcessStatisticHeadExample();
//        example2.createCriteria().andPeriodCodeEqualTo(periodId).andStartTimeEqualTo(start);
//        List<GoodsInProcessStatisticHead> pHeads = goodsInProcessStatisticHeadMapper.selectByExample(example2);
//
//        if (pHeads.size() != 0) {
//            return null;
//        }

        GoodsInProcessStatisticHeadExample example3 = new GoodsInProcessStatisticHeadExample();
        example3.createCriteria().andPeriodCodeEqualTo(periodId).andLineNameEqualTo(lineName);
        List<GoodsInProcessStatisticHead> ppHeads = goodsInProcessStatisticHeadMapper.selectByExample(example3);
        if (ppHeads.size() != 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", -1);
            map.put("message", "本期数据已经存在");
            return map;
        }

        String format = "yyyy-MM-dd HH:mm:ss";
        if (mHeads.size() == 0 && sHeads.size() == 0 && aHeads.size() == 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", addGoodIn(start, end, periodId, lineName, period));
            map.put("message", "success");
            return map;
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
        if (sHeads.size() != 0) {
            ProductStorageStatisticHead temp = sHeads.get(0);
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
        Map<String, Object> map = new HashMap<>();
        map.put("code", addGoodIn(start, end, periodId, lineName, period));
        map.put("message", "success");
        return map;
    }

    public Long addGoodIn(Date start, Date end, Integer periodId, Integer lineName, BasicInfoPrecursorPeriod period) {
        GoodsInProcessStatisticHead head = new GoodsInProcessStatisticHead();
        head.setStartTime(start);
        head.setEndTime(end);
        head.setPeriodCode(periodId);
        head.setLineName(lineName);
        head.setFlag(new Integer(0).byteValue());
        goodsInProcessStatisticHeadMapper.insertSelective(head);
        return head.getCode();
    }

    @Override
    public GoodInTableDTO getTables(Long id) {
        GoodInTableDTO ans = new GoodInTableDTO();
        List<GoodInProcessDTO> processes = new ArrayList<>();
        BasicInfoPrecursorProcessTypeExample example = new BasicInfoPrecursorProcessTypeExample();
        example.createCriteria().andTypesEqualTo(new Integer(0).byteValue());
        List<BasicInfoPrecursorProcessType> processTypes = basicInfoPrecursorProcessTypeMapper.selectByExample(example);
        for (int i = 0; i < processTypes.size(); i++) {
            GoodInProcessDTO dto = new GoodInProcessDTO();
            dto.setProcessName(processTypes.get(i).getProcessName());
            dto.setProcessId(processTypes.get(i).getCode());
            BasicInfoPrecursorMaterialDetailsExample example1 = new BasicInfoPrecursorMaterialDetailsExample();
            example1.clear();
            example1.createCriteria().andProcessCodeEqualTo(processTypes.get(i).getCode());

            List<GoodInLineProDTO> lineProDTOS = new ArrayList<>();
            BasicInfoPrecursorProductionLineExample example2 = new BasicInfoPrecursorProductionLineExample();
            example1.createCriteria();
            List<BasicInfoPrecursorProductionLine> lines = basicInfoPrecursorProductionLineMapper.selectByExample(example2);

            ProductionBatchRuleDetailExample example3 = new ProductionBatchRuleDetailExample();
            example3.createCriteria().andRuleCodeEqualTo(new Integer(5).byteValue());//产品型号的code是5，以后也不准修改
            List<ProductionBatchRuleDetail> ruleDetails = ruleDetailMapper.selectByExample(example3);
            List<String> pros = new ArrayList<>();
            for (int l = 0; l < ruleDetails.size(); l++) {
                pros.add(ruleDetails.get(l).getRuleValue());
            }

            for (int l = 0; l < lines.size(); l++) {
                GoodInLineProDTO lineProDTO = new GoodInLineProDTO();
                lineProDTO.setLine(lines.get(l));
                lineProDTO.setProducts(pros);
                if (id != null) {
                    GoodsInProcessStatisticLineProductionsExample example4 = new GoodsInProcessStatisticLineProductionsExample();
                    example4.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(processTypes.get(i).getCode());
                    List<GoodsInProcessStatisticLineProductions> productions = goodsInProcessStatisticLineProductionsMapper.selectByExample(example4);
                    if (productions.size() != 0) {
                        lineProDTO.setProduct(productions.get(0).getProductionType());
                    }
                }
                lineProDTOS.add(lineProDTO);
            }
            dto.setLineProDTOS(lineProDTOS);

            List<BasicInfoPrecursorMaterialDetailsDTO> detailsDTOS = new ArrayList<>();
            if (processTypes.get(i).getCode() == ProcessEnum.OTHER.getProcessId()) {
                dto.setMaterialDetails(detailsDTOS);
                processes.add(dto);
                continue;
            }
            List<BasicInfoPrecursorMaterialDetails> details = basicInfoPrecursorMaterialDetailsMapper.selectByExample(example1);

            for (int j = 0; j < details.size(); j++) {
                BasicInfoPrecursorMaterialDetailsDTO detailsDTO = new BasicInfoPrecursorMaterialDetailsDTO(details.get(j));
                if (processTypes.get(i).getCode() == ProcessEnum.COMPOUND.getProcessId()) {
                    Integer matId = details.get(j).getCode();
                    BasicInfoCompoundCellVolumesExample volumesExample = new BasicInfoCompoundCellVolumesExample();
                    volumesExample.createCriteria().andMaterialCodeEqualTo(matId);
                    List<BasicInfoCompoundCellVolumes> volumes = cellVolumesMapper.selectByExample(volumesExample);
                    if (volumes.size() != 0) {
                        detailsDTO.setVolume(volumes.get(0).getVolumesValue());
                    }
                }
                detailsDTOS.add(detailsDTO);
            }

            dto.setLineProDTOS(lineProDTOS);
            dto.setMaterialDetails(detailsDTOS);
            processes.add(dto);
        }
        if (id != null) {
            GoodsInProcessStatisticHead head = goodsInProcessStatisticHeadMapper.selectByPrimaryKey(id);
            ans.setEndTime(head.getEndTime());
            ans.setStartTime(head.getStartTime());
            ans.setPeriod(basicInfoPrecursorPeriodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
            ans.setLineName(head.getLineName());
            for (int i = 0; i < processes.size(); i++) {
                List<BasicInfoPrecursorMaterialDetailsDTO> details = processes.get(i).getMaterialDetails();
                //单晶体
                if (processes.get(i).getProcessId() == ProcessEnum.MONCRYSTAL.getProcessId()) {
                    GoodsInProcessStatisticMonocrystalDeployExample example1 = new GoodsInProcessStatisticMonocrystalDeployExample();
                    for (int l = 0; l < details.size(); l++) {
                        BasicInfoPrecursorMaterialDetails material = basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(details.get(l).getCode());
                        example1.clear();
                        example1.createCriteria().andProcessCodeEqualTo(ProcessEnum.MONCRYSTAL.getProcessId()).andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(details.get(l).getCode());
                        List<GoodsInProcessStatisticMonocrystalDeploy> deploys = monocrystalDeployMapper.selectByExample(example1);
                        GoodsInProcessStatisticMonocrystalDeploy deploy = deploys.size() == 0 ? new GoodsInProcessStatisticMonocrystalDeploy() : deploys.get(0);
                        Float potency = 0f;
                        if (material.getCo().intValue() == 1) {
                            potency = deploy.getCoConcentration();
                        } else if (material.getMn().intValue() == 1) {
                            potency = deploy.getMnConcentration();
                        } else {
                            potency = deploy.getNiConcentration();
                        }
                        processes.get(i).getMaterialDetails().get(l).setVolume(deploy.getVolumes());
                        processes.get(i).getMaterialDetails().get(l).setMonPotency(potency);
                    }
                }
                //混合盐
                if (processes.get(i).getProcessId() == ProcessEnum.MIXTURE.getProcessId()) {
                    GoodsInProcessStatisticSaltMixtureDeployExample example1 = new GoodsInProcessStatisticSaltMixtureDeployExample();
                    for (int l = 0; l < details.size(); l++) {
                        BasicInfoPrecursorMaterialDetails material = basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(details.get(l).getCode());
                        example1.clear();
                        example1.createCriteria().andProcessCodeEqualTo(ProcessEnum.MIXTURE.getProcessId()).andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(details.get(l).getCode());
                        List<GoodsInProcessStatisticSaltMixtureDeploy> deploys = saltMixtureDeployMapper.selectByExample(example1);
                        GoodsInProcessStatisticSaltMixtureDeploy deploy = deploys.size() == 0 ? new GoodsInProcessStatisticSaltMixtureDeploy() : deploys.get(0);
                        if (material.getCo().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setCoPotency(deploy.getCoConcentration());
                        }
                        if (material.getMn().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setMnPotency(deploy.getMnConcentration());
                        }
                        if (material.getNi().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setNiPotency(deploy.getNiConcentration());
                        }
                        processes.get(i).getMaterialDetails().get(l).setVolume(deploy.getVolumes());
                    }
                }
                //合成工序
                if (processes.get(i).getProcessId() == ProcessEnum.COMPOUND.getProcessId()) {
                    GoodsInProcessStatisticCompoundExample example1 = new GoodsInProcessStatisticCompoundExample();
                    for (int l = 0; l < details.size(); l++) {
                        BasicInfoPrecursorMaterialDetails material = basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(details.get(l).getCode());
                        example1.clear();
                        example1.createCriteria().andProcessCodeEqualTo(ProcessEnum.COMPOUND.getProcessId()).andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(details.get(l).getCode());
                        List<GoodsInProcessStatisticCompound> compounds = compoundMapper.selectByExample(example1);
                        GoodsInProcessStatisticCompound compound = compounds.size() == 0 ? new GoodsInProcessStatisticCompound() : compounds.get(0);
                        if (material.getCo().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setCoPotency(compound.getCoConcentration());
                        }
                        if (material.getMn().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setMnPotency(compound.getMnConcentration());
                        }
                        if (material.getNi().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setNiPotency(compound.getNiConcentration());
                        }
                       // processes.get(i).getMaterialDetails().get(l).setVolume(compound.getVolumes());
                        processes.get(i).getMaterialDetails().get(l).setSolidContent(compound.getSolidContainingContent());
                    }
                }
                //陈化工序
                if (processes.get(i).getProcessId() == ProcessEnum.AGEING.getProcessId()) {
                    GoodsInProcessStatisticAgeingExample example1 = new GoodsInProcessStatisticAgeingExample();
                    for (int l = 0; l < details.size(); l++) {
                        BasicInfoPrecursorMaterialDetails material = basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(details.get(l).getCode());
                        example1.clear();
                        example1.createCriteria().andProcessCodeEqualTo(ProcessEnum.AGEING.getProcessId()).andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(details.get(l).getCode());
                        List<GoodsInProcessStatisticAgeing> ageings = ageingMapper.selectByExample(example1);
                        GoodsInProcessStatisticAgeing ageing = ageings.size() == 0 ? new GoodsInProcessStatisticAgeing() : ageings.get(0);
                        if (material.getCo().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setCoPotency(ageing.getCoConcentration());
                        }
                        if (material.getMn().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setMnPotency(ageing.getMnConcentration());
                        }
                        if (material.getNi().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setNiPotency(ageing.getNiConcentration());
                        }
                        processes.get(i).getMaterialDetails().get(l).setSolidContent(ageing.getSolidContainingContent());
                        processes.get(i).getMaterialDetails().get(l).setWeiOrVol(ageing.getVolumes() + ageing.getWeight());
                    }
                }
                //烘干工序
                if (processes.get(i).getProcessId() == ProcessEnum.DRYING.getProcessId()) {
                    GoodsInProcessStatisticDryingExample example1 = new GoodsInProcessStatisticDryingExample();
                    for (int l = 0; l < details.size(); l++) {
                        BasicInfoPrecursorMaterialDetails material = basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(details.get(l).getCode());
                        example1.clear();
                        example1.createCriteria().andProcessCodeEqualTo(ProcessEnum.DRYING.getProcessId()).andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(details.get(l).getCode());
                        List<GoodsInProcessStatisticDrying> dryings = dryingMapper.selectByExample(example1);
                        GoodsInProcessStatisticDrying drying = dryings.size() == 0 ? new GoodsInProcessStatisticDrying() : dryings.get(0);
                        if (material.getCo().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setCoPotency(drying.getCoConcentration());
                        }
                        if (material.getMn().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setMnPotency(drying.getMnConcentration());
                        }
                        if (material.getNi().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setNiPotency(drying.getNiConcentration());
                        }
                        processes.get(i).getMaterialDetails().get(l).setWeight(drying.getWeight());
                    }
                }
                //其他
                if (processes.get(i).getProcessId() == ProcessEnum.OTHER.getProcessId()) {
                    GoodsInProcessStatisticOthersExample othersExample = new GoodsInProcessStatisticOthersExample();
                    othersExample.createCriteria().andProcessCodeEqualTo(ProcessEnum.OTHER.getProcessId()).andStatisticCodeEqualTo(id);
                    List<GoodsInProcessStatisticOthers> oothers = othersMapper.selectByExample(othersExample);
                    List<BasicInfoPrecursorMaterialDetailsDTO> finalMat = new ArrayList<>();
                    for (int l = 0; l < oothers.size(); l++) {
                        BasicInfoPrecursorMaterialDetails materialDetails = basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(oothers.get(l).getMaterialCode());
                        BasicInfoPrecursorMaterialDetailsDTO materialDetailsDTO = new BasicInfoPrecursorMaterialDetailsDTO(materialDetails);
                        if (materialDetailsDTO.getCo().intValue() == 1) {
                            materialDetailsDTO.setCoPotency(oothers.get(l).getCoConcentration());
                        }
                        if (materialDetailsDTO.getMn().intValue() == 1) {
                            materialDetailsDTO.setMnPotency(oothers.get(l).getMnConcentration());
                        }
                        if (materialDetailsDTO.getNi().intValue() == 1) {
                            materialDetailsDTO.setNiPotency(oothers.get(l).getNiConcentration());
                        }
                        materialDetailsDTO.setWeight(oothers.get(l).getWeight());
                        finalMat.add(materialDetailsDTO);
                    }
                    processes.get(i).setMaterialDetails(finalMat);

                    GoodsInProcessStatisticOthersExample example1 = new GoodsInProcessStatisticOthersExample();
                    /*for (int l = 0; l < details.size(); l++) {
                        BasicInfoPrecursorMaterialDetails material = basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(details.get(l).getCode());
                        example1.clear();
                        example1.createCriteria().andProcessCodeEqualTo(ProcessEnum.OTHER.getProcessId()).andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(details.get(l).getCode());
                        List<GoodsInProcessStatisticOthers> otherss = othersMapper.selectByExample(example1);
                        if (otherss.size() == 0)
                            continue;
                        GoodsInProcessStatisticOthers others = otherss.get(0);
                        if (material.getCo().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setCoPotency(others.getCoConcentration());
                        }
                        if (material.getMn().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setMnPotency(others.getMnConcentration());
                        }
                        if (material.getNi().intValue() == 1) {
                            processes.get(i).getMaterialDetails().get(l).setNiPotency(others.getNiConcentration());
                        }
                        processes.get(i).getMaterialDetails().get(l).setWeight(others.getWeight());
                    }*/
                }
                for (int l = 0; l < processes.get(i).getLineProDTOS().size(); l++) {
                    GoodsInProcessStatisticLineProductionsExample example1 = new GoodsInProcessStatisticLineProductionsExample();
                    example1.clear();
                    example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(processes.get(i).getProcessId()).andLineCodeEqualTo(processes.get(i).getLineProDTOS().get(l).getLine().getCode());
                    List<GoodsInProcessStatisticLineProductions> productions = goodsInProcessStatisticLineProductionsMapper.selectByExample(example1);
                    if (productions.size() != 0) {
                        processes.get(i).getLineProDTOS().get(l).setProduct(productions.get(0).getProductionType());
                    }
                }
            }
        }
        ans.setGoodInProcessDTOS(processes);
        BasicInfoPrecursorMaterialDetailsExample example1 = new BasicInfoPrecursorMaterialDetailsExample();
        example1.createCriteria().andProcessCodeEqualTo(ProcessEnum.OTHER.getProcessId());
        List<BasicInfoPrecursorMaterialDetails> details = basicInfoPrecursorMaterialDetailsMapper.selectByExample(example1);
        List<BasicInfoPrecursorMaterialDetailsDTO> detailsDTOS = new ArrayList<>();
        for (int i = 0; i < details.size(); i++)
            detailsDTOS.add(new BasicInfoPrecursorMaterialDetailsDTO(details.get(i)));
        ans.setOtherMaterials(detailsDTOS);
        return ans;
    }

    @Override
    public List getLastPotency(Integer processId) {
        List<GoodsInProcessStatisticHead> stastics = new ArrayList<>();
        //单晶体
        if (processId == ProcessEnum.MONCRYSTAL.getProcessId()) {
            GoodsInProcessStatisticMonocrystalDeployExample example = new GoodsInProcessStatisticMonocrystalDeployExample();
            example.createCriteria().andProcessCodeEqualTo(processId);
            List<GoodsInProcessStatisticMonocrystalDeploy> monocrystalDeploys = monocrystalDeployMapper.selectByExample(example);
            for (int i = 0; i < monocrystalDeploys.size(); i++) {
                stastics.add(goodsInProcessStatisticHeadMapper.selectByPrimaryKey(monocrystalDeploys.get(i).getStatisticCode()));
            }
            Long lastStasticId = getLastStasticId(stastics);
            example.clear();
            example.createCriteria().andStatisticCodeEqualTo(lastStasticId);
            return monocrystalDeployMapper.selectByExample(example);
        }
        //陈化
        if (processId == ProcessEnum.AGEING.getProcessId()) {
            GoodsInProcessStatisticAgeingExample example = new GoodsInProcessStatisticAgeingExample();
            example.createCriteria().andProcessCodeEqualTo(processId);
            List<GoodsInProcessStatisticAgeing> ageings = ageingMapper.selectByExample(example);
            for (int i = 0; i < ageings.size(); i++) {
                stastics.add(goodsInProcessStatisticHeadMapper.selectByPrimaryKey(ageings.get(i).getStatisticCode()));
            }
            Long lastStasticId = getLastStasticId(stastics);
            example.clear();
            example.createCriteria().andStatisticCodeEqualTo(lastStasticId);
            return ageingMapper.selectByExample(example);
        }
        //烘干
        if (processId == ProcessEnum.DRYING.getProcessId()) {
            GoodsInProcessStatisticDryingExample example = new GoodsInProcessStatisticDryingExample();
            example.createCriteria().andProcessCodeEqualTo(processId);
            List<GoodsInProcessStatisticDrying> dryings = dryingMapper.selectByExample(example);
            for (int i = 0; i < dryings.size(); i++) {
                stastics.add(goodsInProcessStatisticHeadMapper.selectByPrimaryKey(dryings.get(i).getStatisticCode()));
            }
            Long lastStasticId = getLastStasticId(stastics);
            example.clear();
            example.createCriteria().andStatisticCodeEqualTo(lastStasticId);
            return dryingMapper.selectByExample(example);
        }
        //合成
        if (processId == ProcessEnum.COMPOUND.getProcessId()) {
            GoodsInProcessStatisticCompoundExample example = new GoodsInProcessStatisticCompoundExample();
            example.createCriteria().andProcessCodeEqualTo(processId);
            List<GoodsInProcessStatisticCompound> compounds = compoundMapper.selectByExample(example);
            for (int i = 0; i < compounds.size(); i++) {
                stastics.add(goodsInProcessStatisticHeadMapper.selectByPrimaryKey(compounds.get(i).getStatisticCode()));
            }
            Long lastStasticId = getLastStasticId(stastics);
            example.clear();
            example.createCriteria().andStatisticCodeEqualTo(lastStasticId);
            return compoundMapper.selectByExample(example);
        }
        return null;
    }

    public Long getLastStasticId(List<GoodsInProcessStatisticHead> ids) {
        if (ids.size() == 0) {
            return new Long(-1);
        }
        int ansPos = 0;
        for (int i = 1; i < ids.size(); i++) {
            if (ids.get(ansPos).getStartTime().getTime() < ids.get(i).getStartTime().getTime()) {
                ansPos = i;
            }
        }
        return ids.get(ansPos).getCode();
    }

    @Override
    public String saveOrCommit(Long stasticId, Integer flag, GoodInTableDTO goodInTableDTO) {
        String ans = null;
        if (flag == 0) {
            save(stasticId, goodInTableDTO);
            ans = "保存成功";
        } else {
            commit(stasticId, goodInTableDTO);
            ans = "提交成功";
        }
        return ans;
    }

    private void save(Long stasticId, GoodInTableDTO goodInTableDTO) {

        deleteCascade(stasticId);

        List<GoodInProcessDTO> list = goodInTableDTO.getGoodInProcessDTOS();

        for (int i = 0; i < list.size(); i++) {
            GoodInProcessDTO temp = list.get(i);
            //产线型号
            List<GoodInLineProDTO> lineProDTOs = temp.getLineProDTOS();
            for (int l = 0; l < lineProDTOs.size(); l++) {
                GoodsInProcessStatisticLineProductions productions = new GoodsInProcessStatisticLineProductions();
                productions.setLineCode(lineProDTOs.get(l).getLine().getCode());
                productions.setProcessCode(temp.getProcessId());
                productions.setStatisticCode(stasticId);
                productions.setProductionType(lineProDTOs.get(l).getProduct());
                goodsInProcessStatisticLineProductionsMapper.insertSelective(productions);
            }

            List<BasicInfoPrecursorMaterialDetailsDTO> materials = temp.getMaterialDetails();
            if (temp.getProcessId() == ProcessEnum.MONCRYSTAL.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    GoodsInProcessStatisticMonocrystalDeploy deploy = new GoodsInProcessStatisticMonocrystalDeploy();
                    deploy.setStatisticCode(stasticId);
                    deploy.setMaterialCode(info.getCode());
                    deploy.setProcessCode(ProcessEnum.MONCRYSTAL.getProcessId());
                    deploy.setVolumes(info.getVolume());
                    if (info.getMn() == 1) {
                        deploy.setMnConcentration(info.getMonPotency());
                        deploy.setMnMetallicity(info.getVolume() * info.getMonPotency() / 1000);
                    } else if (info.getCo() == 1) {
                        deploy.setCoConcentration(info.getMonPotency());
                        deploy.setCoMetallicity(info.getVolume() * info.getMonPotency() / 1000);
                    } else if (info.getNi() == 1) {
                        deploy.setNiConcentration(info.getMonPotency());
                        deploy.setNiMetallicity(info.getVolume() * info.getMonPotency() / 1000);
                    }
                    monocrystalDeployMapper.insertSelective(deploy);
                }
            }
            if (temp.getProcessId() == ProcessEnum.MIXTURE.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    GoodsInProcessStatisticSaltMixtureDeploy deploy = new GoodsInProcessStatisticSaltMixtureDeploy();
                    deploy.setStatisticCode(stasticId);
                    deploy.setMaterialCode(info.getCode());
                    deploy.setProcessCode(ProcessEnum.MIXTURE.getProcessId());
                    deploy.setVolumes(info.getVolume());
                    if (info.getMn() == 1) {
                        deploy.setMnConcentration(info.getMnPotency());
                        deploy.setMnMetallicity(info.getVolume() * info.getMnPotency() / 1000);
                    }
                    if (info.getCo() == 1) {
                        deploy.setCoConcentration(info.getCoPotency());
                        deploy.setCoMetallicity(info.getVolume() * info.getCoPotency() / 1000);
                    }
                    if (info.getNi() == 1) {
                        deploy.setNiConcentration(info.getNiPotency());
                        deploy.setNiMetallicity(info.getVolume() * info.getNiPotency() / 1000);
                    }
                    saltMixtureDeployMapper.insertSelective(deploy);
                }
            }
            if (temp.getProcessId() == ProcessEnum.COMPOUND.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    GoodsInProcessStatisticCompound compound = new GoodsInProcessStatisticCompound();
                    compound.setStatisticCode(stasticId);
                    compound.setMaterialCode(info.getCode());
                    compound.setProcessCode(ProcessEnum.COMPOUND.getProcessId());
                    compound.setVolumes(info.getVolume());
                    compound.setSolidContainingContent(info.getSolidContent());
                    Float weight = info.getVolume() * info.getSolidContent() / 1000;
                    compound.setWeight(weight);
                    if (info.getMn() == 1) {
                        compound.setMnConcentration(info.getMnPotency());
                        compound.setMnMetallicity(weight * info.getMnPotency());
                    }
                    if (info.getCo() == 1) {
                        compound.setCoConcentration(info.getCoPotency());
                        compound.setCoMetallicity(weight * info.getCoPotency());
                    }
                    if (info.getNi() == 1) {
                        compound.setNiConcentration(info.getNiPotency());
                        compound.setNiMetallicity(weight * info.getNiPotency());
                    }
                    compoundMapper.insertSelective(compound);
                }
            }
            if (temp.getProcessId() == ProcessEnum.AGEING.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    GoodsInProcessStatisticAgeing ageing = new GoodsInProcessStatisticAgeing();
                    ageing.setStatisticCode(stasticId);
                    ageing.setMaterialCode(info.getCode());
                    ageing.setProcessCode(ProcessEnum.AGEING.getProcessId());
                    if (info.getValueType() == 0) {//体积
                        ageing.setVolumes(info.getWeiOrVol());
                        ageing.setWeight(0f);
                        if (info.getMn() == 1) {
                            ageing.setMnConcentration(info.getMnPotency());
                            ageing.setMnMetallicity(ageing.getVolumes() * info.getMnPotency() / 1000);
                        }
                        if (info.getCo() == 1) {
                            ageing.setCoConcentration(info.getCoPotency());
                            ageing.setCoMetallicity(ageing.getVolumes() * info.getCoPotency() / 1000);
                        }
                        if (info.getNi() == 1) {
                            ageing.setNiConcentration(info.getNiPotency());
                            ageing.setNiMetallicity(ageing.getVolumes() * info.getNiPotency() / 1000);
                        }
                    } else {
                        /**
                         * Ni金属量（t）=重量（t）*Ni浓度（%）
                         * Co金属量（t）=重量（t）*Co浓度（%）
                         * Mn金属量（t）=重量（t）*Mn浓度（%）
                         */
                        ageing.setWeight(info.getWeiOrVol());
                        ageing.setVolumes(0f);
                        if (info.getMn() == 1) {
                            ageing.setMnConcentration(info.getMnPotency());
                            ageing.setMnMetallicity(ageing.getWeight() * info.getMnPotency());
                        }
                        if (info.getCo() == 1) {
                            ageing.setCoConcentration(info.getCoPotency());
                            ageing.setCoMetallicity(ageing.getWeight() * info.getCoPotency());
                        }
                        if (info.getNi() == 1) {
                            ageing.setNiConcentration(info.getNiPotency());
                            ageing.setNiMetallicity(ageing.getWeight() * info.getNiPotency());
                        }
                    }
                    ageing.setSolidContainingContent(info.getSolidContent());
                    ageingMapper.insertSelective(ageing);
                }
            }
            if (temp.getProcessId() == ProcessEnum.DRYING.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    GoodsInProcessStatisticDrying drying = new GoodsInProcessStatisticDrying();
                    drying.setStatisticCode(stasticId);
                    drying.setMaterialCode(info.getCode());
                    drying.setProcessCode(ProcessEnum.DRYING.getProcessId());
                    drying.setWeight(info.getWeight());
                    if (info.getMn() == 1) {
                        drying.setMnConcentration(info.getMnPotency());
                        drying.setMnMetallicity(info.getWeight() * info.getMnPotency());
                    }
                    if (info.getCo() == 1) {
                        drying.setCoConcentration(info.getCoPotency());
                        drying.setCoMetallicity(info.getWeight() * info.getCoPotency());
                    }
                    if (info.getNi() == 1) {
                        drying.setNiConcentration(info.getNiPotency());
                        drying.setNiMetallicity(info.getWeight() * info.getNiPotency());
                    }
                    dryingMapper.insertSelective(drying);
                }
            }
            if (temp.getProcessId() == ProcessEnum.OTHER.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    GoodsInProcessStatisticOthers others = new GoodsInProcessStatisticOthers();
                    others.setStatisticCode(stasticId);
                    others.setMaterialCode(info.getCode());
                    others.setProcessCode(ProcessEnum.OTHER.getProcessId());
                    others.setWeight(info.getWeight());
                    if (info.getMn() == 1) {
                        others.setMnConcentration(info.getMnPotency());
                        others.setMnMetallicity(info.getWeight() * info.getMnPotency());
                    }
                    if (info.getCo() == 1) {
                        others.setCoConcentration(info.getCoPotency());
                        others.setCoMetallicity(info.getWeight() * info.getCoPotency());
                    }
                    if (info.getNi() == 1) {
                        others.setNiConcentration(info.getNiPotency());
                        others.setNiMetallicity(info.getWeight() * info.getNiPotency());
                    }
                    othersMapper.insertSelective(others);
                }
            }
        }
    }

    private void commit(Long stasticId, GoodInTableDTO goodInTableDTO) {
        save(stasticId, goodInTableDTO);

        List<GoodInProcessDTO> list = goodInTableDTO.getGoodInProcessDTOS();
        Float totalNi = 0f;
        Float totalCo = 0f;
        Float totalMn = 0f;

        GoodsInProcessStatisticByLineDetail lineDetail = new GoodsInProcessStatisticByLineDetail();
        lineDetail.setStatisticCode(stasticId);
        lineDetail.setLineName(goodInTableDTO.getLineName());
        lineDetail.setCoValue(0f);
        lineDetail.setMnValue(0f);
        lineDetail.setNiValue(0f);
        BasicInfoPrecursorProductionLineExample example = new BasicInfoPrecursorProductionLineExample();
        example.createCriteria();
        List<BasicInfoPrecursorProductionLine> lines = lineMapper.selectByExample(example);
        for (int i = 0; i < lines.size(); i++) {
            lineDetail.setLineCode(lines.get(i).getCode());
            lineDetailMapper.insertSelective(lineDetail);
            lineDetail.setCode(null);
        }//插入按照产线统计的表，数值都为空暂时，便于后面的更新

        for (int i = 0; i < list.size(); i++) {
            GoodsInProcessStatisticByProcessDetail detail = new GoodsInProcessStatisticByProcessDetail();
            detail.setStatisticCode(stasticId);
            detail.setLineName(goodInTableDTO.getLineName());
            detail.setProcessCode(list.get(i).getProcessId());
            Float total = 0f;
            Float niValue = 0f;
            Float coValue = 0f;
            Float mnValue = 0f;
            GoodInProcessDTO temp = list.get(i);
            List<BasicInfoPrecursorMaterialDetailsDTO> materials = temp.getMaterialDetails();
            if (temp.getProcessId() == ProcessEnum.MONCRYSTAL.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    BasicInfoPrecursorMaterialLineWeightExample example7 = new BasicInfoPrecursorMaterialLineWeightExample();
                    example7.clear();
                    example7.createCriteria().andMaterialCodeEqualTo(info.getCode());
                    List<BasicInfoPrecursorMaterialLineWeight> materialLineWeights = lineWeightMapper.selectByExample(example7);
                    GoodsInProcessStatisticByLineDetailExample example8 = new GoodsInProcessStatisticByLineDetailExample();
                    example8.clear();
                    example8.createCriteria();
                    if (info.getMn() == 1) {
                        mnValue += (info.getVolume() * info.getMonPotency() / 1000);
                        for (int k = 0; k < materialLineWeights.size(); k++) {
                            example8.clear();
                            example8.createCriteria().andLineCodeEqualTo(materialLineWeights.get(k).getLineCode()).andStatisticCodeEqualTo(stasticId);
                            GoodsInProcessStatisticByLineDetail detail1 = lineDetailMapper.selectByExample(example8).get(0);
                            detail1.setMnValue(detail1.getMnValue() + (info.getVolume() * info.getMonPotency() / 1000) * materialLineWeights.get(k).getWeightValue());
                            lineDetailMapper.updateByExampleSelective(detail1, example8);
                        }//找到所有该物料的产线，产线下的金属量值进行累计计算
                    } else if (info.getCo() == 1) {
                        coValue += (info.getVolume() * info.getMonPotency() / 1000);
                        for (int k = 0; k < materialLineWeights.size(); k++) {
                            example8.clear();
                            example8.createCriteria().andLineCodeEqualTo(materialLineWeights.get(k).getLineCode()).andStatisticCodeEqualTo(stasticId);
                            GoodsInProcessStatisticByLineDetail detail1 = lineDetailMapper.selectByExample(example8).get(0);
                            detail1.setCoValue(detail1.getCoValue() + (info.getVolume() * info.getMonPotency() / 1000) * materialLineWeights.get(k).getWeightValue());
                            lineDetailMapper.updateByExampleSelective(detail1, example8);
                        }//找到所有该物料的产线，产线下的金属量值进行累计计算
                    } else if (info.getNi() == 1) {
                        niValue += (info.getVolume() * info.getMonPotency() / 1000);
                        for (int k = 0; k < materialLineWeights.size(); k++) {
                            example8.clear();
                            example8.createCriteria().andLineCodeEqualTo(materialLineWeights.get(k).getLineCode()).andStatisticCodeEqualTo(stasticId);
                            GoodsInProcessStatisticByLineDetail detail1 = lineDetailMapper.selectByExample(example8).get(0);
                            detail1.setNiValue(detail1.getMnValue() + (info.getVolume() * info.getMonPotency() / 1000) * materialLineWeights.get(k).getWeightValue());
                            lineDetailMapper.updateByExampleSelective(detail1, example8);
                        }//找到所有该物料的产线，产线下的金属量值进行累计计算
                    }
                    total += info.getVolume();//单晶体存放的是体积
                }
            }
            if (temp.getProcessId() == ProcessEnum.MIXTURE.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    BasicInfoPrecursorMaterialLineWeightExample example7 = new BasicInfoPrecursorMaterialLineWeightExample();
                    example7.clear();
                    example7.createCriteria().andMaterialCodeEqualTo(info.getCode());
                    List<BasicInfoPrecursorMaterialLineWeight> materialLineWeights = lineWeightMapper.selectByExample(example7);
                    GoodsInProcessStatisticByLineDetailExample example8 = new GoodsInProcessStatisticByLineDetailExample();
                    example8.clear();
                    example8.createCriteria();
                    if (info.getMn() == 1) {
                        mnValue += (info.getVolume() * info.getMnPotency() / 1000);
                    }
                    if (info.getCo() == 1) {
                        coValue += (info.getVolume() * info.getCoPotency() / 1000);
                    }
                    if (info.getNi() == 1) {
                        niValue += (info.getVolume() * info.getNiPotency() / 1000);
                    }
                    total += info.getVolume();//混合盐存放的是体积
                    for (int k = 0; k < materialLineWeights.size(); k++) {
                        example8.clear();
                        example8.createCriteria().andLineCodeEqualTo(materialLineWeights.get(k).getLineCode()).andStatisticCodeEqualTo(stasticId);
                        GoodsInProcessStatisticByLineDetail detail1 = lineDetailMapper.selectByExample(example8).get(0);
                        detail1.setMnValue(detail1.getMnValue() + (info.getVolume() * info.getMnPotency() / 1000) * materialLineWeights.get(k).getWeightValue());
                        detail1.setNiValue(detail1.getNiValue() + (info.getVolume() * info.getNiPotency() / 1000) * materialLineWeights.get(k).getWeightValue());
                        detail1.setCoValue(detail1.getCoValue() + (info.getVolume() * info.getCoPotency() / 1000) * materialLineWeights.get(k).getWeightValue());
                        lineDetailMapper.updateByExampleSelective(detail1, example8);
                    }//找到所有该物料的产线，产线下的金属量值进行累计计算
                }
            }
            if (temp.getProcessId() == ProcessEnum.COMPOUND.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    BasicInfoPrecursorMaterialLineWeightExample example7 = new BasicInfoPrecursorMaterialLineWeightExample();
                    example7.clear();
                    example7.createCriteria().andMaterialCodeEqualTo(info.getCode());
                    List<BasicInfoPrecursorMaterialLineWeight> materialLineWeights = lineWeightMapper.selectByExample(example7);
                    GoodsInProcessStatisticByLineDetailExample example8 = new GoodsInProcessStatisticByLineDetailExample();
                    example8.clear();
                    example8.createCriteria();
                    if (info.getMn() == 1) {
                        mnValue += (info.getVolume() * info.getMnPotency() / 1000);
                    }
                    if (info.getCo() == 1) {
                        coValue += (info.getVolume() * info.getCoPotency() / 1000);
                    }
                    if (info.getNi() == 1) {
                        niValue += (info.getVolume() * info.getNiPotency() / 1000);
                    }
                    total += (info.getVolume() * info.getSolidContent() / 1000);//合成存放的是重量
                    for (int k = 0; k < materialLineWeights.size(); k++) {
                        example8.clear();
                        example8.createCriteria().andLineCodeEqualTo(materialLineWeights.get(k).getLineCode()).andStatisticCodeEqualTo(stasticId);
                        GoodsInProcessStatisticByLineDetail detail1 = lineDetailMapper.selectByExample(example8).get(0);
                        detail1.setMnValue(detail1.getMnValue() + (info.getVolume() * info.getMnPotency() / 1000) * materialLineWeights.get(k).getWeightValue());
                        detail1.setNiValue(detail1.getNiValue() + (info.getVolume() * info.getNiPotency() / 1000) * materialLineWeights.get(k).getWeightValue());
                        detail1.setCoValue(detail1.getCoValue() + (info.getVolume() * info.getCoPotency() / 1000) * materialLineWeights.get(k).getWeightValue());
                        lineDetailMapper.updateByExampleSelective(detail1, example8);
                    }//找到所有该物料的产线，产线下的金属量值进行累计计算
                }
            }
            if (temp.getProcessId() == ProcessEnum.AGEING.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    BasicInfoPrecursorMaterialLineWeightExample example7 = new BasicInfoPrecursorMaterialLineWeightExample();
                    example7.clear();
                    example7.createCriteria().andMaterialCodeEqualTo(info.getCode());
                    List<BasicInfoPrecursorMaterialLineWeight> materialLineWeights = lineWeightMapper.selectByExample(example7);
                    GoodsInProcessStatisticByLineDetailExample example8 = new GoodsInProcessStatisticByLineDetailExample();
                    example8.clear();
                    example8.createCriteria();
                    if (info.getValueType() == 0) {//体积
                        if (info.getMn() == 1) {
                            mnValue += (info.getWeiOrVol() * info.getMnPotency() / 1000);
                        }
                        if (info.getCo() == 1) {
                            coValue += (info.getWeiOrVol() * info.getCoPotency() / 1000);
                        }
                        if (info.getNi() == 1) {
                            niValue += (info.getWeiOrVol() * info.getNiPotency() / 1000);
                        }
                        total += (info.getWeiOrVol() * info.getSolidContent() / 1000);//陈化存放的是重量
                        for (int k = 0; k < materialLineWeights.size(); k++) {
                            example8.clear();
                            example8.createCriteria().andLineCodeEqualTo(materialLineWeights.get(k).getLineCode()).andStatisticCodeEqualTo(stasticId);
                            GoodsInProcessStatisticByLineDetail detail1 = lineDetailMapper.selectByExample(example8).get(0);
                            detail1.setMnValue(detail1.getMnValue() + info.getWeiOrVol() * info.getMnPotency() / 1000 * materialLineWeights.get(k).getWeightValue());
                            detail1.setNiValue(detail1.getNiValue() + info.getWeiOrVol() * info.getNiPotency() / 1000 * materialLineWeights.get(k).getWeightValue());
                            detail1.setCoValue(detail1.getCoValue() + info.getWeiOrVol() * info.getCoPotency() / 1000 * materialLineWeights.get(k).getWeightValue());
                            lineDetailMapper.updateByExampleSelective(detail1, example8);
                        }//找到所有该物料的产线，产线下的金属量值进行累计计算
                    } else {
                        /**
                         * Ni金属量（t）=重量（t）*Ni浓度（%）
                         * Co金属量（t）=重量（t）*Co浓度（%）
                         * Mn金属量（t）=重量（t）*Mn浓度（%）
                         */
                        if (info.getMn() == 1) {
                            mnValue += (info.getWeiOrVol() * info.getMnPotency());
                        }
                        if (info.getCo() == 1) {
                            coValue += (info.getWeiOrVol() * info.getCoPotency());
                        }
                        if (info.getNi() == 1) {
                            niValue += (info.getWeiOrVol() * info.getNiPotency());
                        }
                        total += (info.getWeiOrVol());//合成存放的是重量
                        for (int k = 0; k < materialLineWeights.size(); k++) {
                            example8.clear();
                            example8.createCriteria().andLineCodeEqualTo(materialLineWeights.get(k).getLineCode()).andStatisticCodeEqualTo(stasticId);
                            GoodsInProcessStatisticByLineDetail detail1 = lineDetailMapper.selectByExample(example8).get(0);
                            detail1.setMnValue(detail1.getMnValue() + info.getWeiOrVol() * info.getMnPotency() * materialLineWeights.get(k).getWeightValue());
                            detail1.setNiValue(detail1.getNiValue() + info.getWeiOrVol() * info.getNiPotency() * materialLineWeights.get(k).getWeightValue());
                            detail1.setCoValue(detail1.getCoValue() + info.getWeiOrVol() * info.getCoPotency() * materialLineWeights.get(k).getWeightValue());
                            lineDetailMapper.updateByExampleSelective(detail1, example8);
                        }//找到所有该物料的产线，产线下的金属量值进行累计计算
                    }
                }
            }
            if (temp.getProcessId() == ProcessEnum.DRYING.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    BasicInfoPrecursorMaterialLineWeightExample example7 = new BasicInfoPrecursorMaterialLineWeightExample();
                    example7.clear();
                    example7.createCriteria().andMaterialCodeEqualTo(info.getCode());
                    List<BasicInfoPrecursorMaterialLineWeight> materialLineWeights = lineWeightMapper.selectByExample(example7);
                    GoodsInProcessStatisticByLineDetailExample example8 = new GoodsInProcessStatisticByLineDetailExample();
                    example8.clear();
                    example8.createCriteria();
                    /**
                     * Ni金属量（t）=重量（t）*Ni浓度（%）
                     * Co金属量（t）=重量（t）*Co浓度（%）
                     * Mn金属量（t）=重量（t）*Mn浓度（%）
                     */
                    if (info.getMn() == 1) {
                        mnValue += (info.getWeight() * info.getMnPotency());
                    }
                    if (info.getCo() == 1) {
                        coValue += (info.getWeight() * info.getCoPotency());
                    }
                    if (info.getNi() == 1) {
                        niValue += (info.getWeight() * info.getNiPotency());
                    }
                    total += (info.getWeight());//烘干存放的是重量
                    for (int k = 0; k < materialLineWeights.size(); k++) {
                        example8.clear();
                        example8.createCriteria().andLineCodeEqualTo(materialLineWeights.get(k).getLineCode()).andStatisticCodeEqualTo(stasticId);
                        GoodsInProcessStatisticByLineDetail detail1 = lineDetailMapper.selectByExample(example8).get(0);
                        detail1.setMnValue(detail1.getMnValue() + info.getWeight() * info.getMnPotency() * materialLineWeights.get(k).getWeightValue());
                        detail1.setNiValue(detail1.getNiValue() + info.getWeight() * info.getNiPotency() * materialLineWeights.get(k).getWeightValue());
                        detail1.setCoValue(detail1.getCoValue() + info.getWeight() * info.getCoPotency() * materialLineWeights.get(k).getWeightValue());
                        lineDetailMapper.updateByExampleSelective(detail1, example8);
                    }//找到所有该物料的产线，产线下的金属量值进行累计计算
                }
            }
            if (temp.getProcessId() == ProcessEnum.OTHER.getProcessId()) {
                for (int l = 0; l < materials.size(); l++) {
                    BasicInfoPrecursorMaterialDetailsDTO info = materials.get(l);
                    BasicInfoPrecursorMaterialLineWeightExample example7 = new BasicInfoPrecursorMaterialLineWeightExample();
                    example7.clear();
                    example7.createCriteria().andMaterialCodeEqualTo(info.getCode());
                    List<BasicInfoPrecursorMaterialLineWeight> materialLineWeights = lineWeightMapper.selectByExample(example7);
                    GoodsInProcessStatisticByLineDetailExample example8 = new GoodsInProcessStatisticByLineDetailExample();
                    example8.clear();
                    example8.createCriteria();
                    /**
                     * Ni金属量（t）=重量（t）*Ni浓度（%）
                     * Co金属量（t）=重量（t）*Co浓度（%）
                     * Mn金属量（t）=重量（t）*Mn浓度（%）
                     */
                    if (info.getMn() == 1) {
                        mnValue += (info.getWeight() * info.getMnPotency());
                    }
                    if (info.getCo() == 1) {
                        coValue += (info.getWeight() * info.getCoPotency());
                    }
                    if (info.getNi() == 1) {
                        niValue += (info.getWeight() * info.getNiPotency());
                    }
                    total += (info.getWeight());//其他存放的是重量
                    for (int k = 0; k < materialLineWeights.size(); k++) {
                        example8.clear();
                        example8.createCriteria().andLineCodeEqualTo(materialLineWeights.get(k).getLineCode()).andStatisticCodeEqualTo(stasticId);
                        GoodsInProcessStatisticByLineDetail detail1 = lineDetailMapper.selectByExample(example8).get(0);
                        detail1.setMnValue(detail1.getMnValue() + info.getWeight() * info.getMnPotency() * materialLineWeights.get(k).getWeightValue());
                        detail1.setNiValue(detail1.getNiValue() + info.getWeight() * info.getNiPotency() * materialLineWeights.get(k).getWeightValue());
                        detail1.setCoValue(detail1.getCoValue() + info.getWeight() * info.getCoPotency() * materialLineWeights.get(k).getWeightValue());
                        lineDetailMapper.updateByExampleSelective(detail1, example8);
                    }//找到所有该物料的产线，产线下的金属量值进行累计计算
                }
            }
            detail.setTotals(total);
            detail.setMnValue(mnValue);
            detail.setCoValue(coValue);
            detail.setNiValue(niValue);
            totalCo += coValue;
            totalMn += mnValue;
            totalNi += niValue;
            statisticByProcessDetailMapper.insertSelective(detail);//在制品统计按工序统计明细数据表
        }
        GoodsInProcessStatisticByProcessTotal processTotal = new GoodsInProcessStatisticByProcessTotal();
        processTotal.setStatisticCode(stasticId);
        processTotal.setLineName(goodInTableDTO.getLineName());
        processTotal.setCoValue(totalCo);
        processTotal.setNiValue(totalNi);
        processTotal.setMnValue(totalMn);
        processTotalMapper.insertSelective(processTotal);//在制品统计按工序统计合计数据表

        GoodsInProcessStatisticHead head = new GoodsInProcessStatisticHead();
        head.setCode(stasticId);
        head.setFlag(new Integer(1).byteValue());
        goodsInProcessStatisticHeadMapper.updateByPrimaryKeySelective(head);//更新统计头表
    }

    @Override
    public Page statisticPage(String startTime, String endTime, Integer peroidId, Integer page, Integer size) {
        List<GoodInHeadDTO> heads = getAll(startTime, endTime, peroidId, new Integer(1).byteValue());
        List<GoodInStatisticDetailDTO> ans = new ArrayList<>();
        GoodsInProcessStatisticByProcessDetailExample example = new GoodsInProcessStatisticByProcessDetailExample();
        example.createCriteria();
        for (int i = 0; i < heads.size(); i++) {
            Long statisticId = heads.get(i).getGoodsInProcessStatisticHead().getCode();
            example.clear();
            example.createCriteria().andStatisticCodeEqualTo(statisticId);
            List<GoodsInProcessStatisticByProcessDetail> processDetails = statisticByProcessDetailMapper.selectByExample(example);
            for (int l = 0; l < processDetails.size(); l++) {
                GoodInStatisticDetailDTO temp = new GoodInStatisticDetailDTO();
                temp.setHead(heads.get(i).getGoodsInProcessStatisticHead());
                temp.setPeriodName(basicInfoPrecursorPeriodMapper.selectByPrimaryKey(temp.getHead().getPeriodCode()).getName());
                temp.setDetail(processDetails.get(l));
                temp.setProcessName(basicInfoPrecursorProcessTypeMapper.selectByPrimaryKey(processDetails.get(l).getProcessCode()).getProcessName());
                ans.add(temp);
            }
        }
        return new Page(ans, page, size);
    }

    @Override
    public GoodInStatisticDetailDTO statisticDetail(Long processDetailId) {
        GoodInStatisticDetailDTO ans = new GoodInStatisticDetailDTO();
        GoodsInProcessStatisticByProcessDetail detail = statisticByProcessDetailMapper.selectByPrimaryKey(processDetailId);
        Long statisticId = detail.getStatisticCode();
        GoodsInProcessStatisticHead head = goodsInProcessStatisticHeadMapper.selectByPrimaryKey(statisticId);
        BasicInfoPrecursorProcessType processType = basicInfoPrecursorProcessTypeMapper.selectByPrimaryKey(detail.getProcessCode());
        ans.setDetail(detail);
        ans.setHead(head);
        ans.setProcessName(processType.getProcessName());
        ans.setPeriodName(basicInfoPrecursorPeriodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
        Float total = 0f;
        Float ni = 0f;
        Float co = 0f;
        Float mn = 0f;
        List materils = new ArrayList();
        if (detail.getProcessCode() == ProcessEnum.MONCRYSTAL.getProcessId()) {
            GoodsInProcessStatisticMonocrystalDeployExample example = new GoodsInProcessStatisticMonocrystalDeployExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticId).andProcessCodeEqualTo(processType.getCode());
            List<GoodsInProcessStatisticMonocrystalDeploy> deploys = monocrystalDeployMapper.selectByExample(example);
            for (int i = 0; i < deploys.size(); i++) {
                GoodInStatisticProcessDTO<GoodsInProcessStatisticMonocrystalDeploy> process = new GoodInStatisticProcessDTO<>();
                process.setProcess(deploys.get(i));
                process.setMaterialName(basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(deploys.get(i).getMaterialCode()).getMaterialName());
                if(deploys.get(i).getVolumes()!=null){
                    total += deploys.get(i).getVolumes();
                }
                if(deploys.get(i).getCoMetallicity()!=null){
                    co += deploys.get(i).getCoMetallicity();
                }
                if(deploys.get(i).getNiMetallicity()!=null){
                    ni += deploys.get(i).getNiMetallicity();
                }
                if(deploys.get(i).getMnMetallicity()!=null){
                    mn += deploys.get(i).getMnMetallicity();
                }
                materils.add(process);
            }
        }
        if (detail.getProcessCode() == ProcessEnum.MIXTURE.getProcessId()) {
            GoodsInProcessStatisticSaltMixtureDeployExample example = new GoodsInProcessStatisticSaltMixtureDeployExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticId).andProcessCodeEqualTo(processType.getCode());
            List<GoodsInProcessStatisticSaltMixtureDeploy> deploys = saltMixtureDeployMapper.selectByExample(example);
            for (int i = 0; i < deploys.size(); i++) {
                GoodInStatisticProcessDTO<GoodsInProcessStatisticSaltMixtureDeploy> process = new GoodInStatisticProcessDTO<>();
                process.setProcess(deploys.get(i));
                process.setMaterialName(basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(deploys.get(i).getMaterialCode()).getMaterialName());
                if(deploys.get(i).getVolumes()!=null){
                    total += deploys.get(i).getVolumes();
                }
                if(deploys.get(i).getCoMetallicity()!=null){
                    co += deploys.get(i).getCoMetallicity();
                }
                if(deploys.get(i).getNiMetallicity()!=null){
                    ni += deploys.get(i).getNiMetallicity();
                }
                if(deploys.get(i).getMnMetallicity()!=null){
                    mn += deploys.get(i).getMnMetallicity();
                }
                materils.add(process);
            }
        }
        if (detail.getProcessCode() == ProcessEnum.COMPOUND.getProcessId()) {
            GoodsInProcessStatisticCompoundExample example = new GoodsInProcessStatisticCompoundExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticId).andProcessCodeEqualTo(processType.getCode());
            List<GoodsInProcessStatisticCompound> deploys = compoundMapper.selectByExample(example);
            for (int i = 0; i < deploys.size(); i++) {
                GoodInStatisticProcessDTO<GoodsInProcessStatisticCompound> process = new GoodInStatisticProcessDTO<>();
                process.setProcess(deploys.get(i));
                if(deploys.get(i).getWeight()!=null){
                    total += deploys.get(i).getWeight();
                }
                if(deploys.get(i).getCoMetallicity()!=null){
                    co += deploys.get(i).getCoMetallicity();
                }
                if(deploys.get(i).getNiMetallicity()!=null){
                    ni += deploys.get(i).getNiMetallicity();
                }
                if(deploys.get(i).getMnMetallicity()!=null){
                    mn += deploys.get(i).getMnMetallicity();
                }
                process.setMaterialName(basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(deploys.get(i).getMaterialCode()).getMaterialName());
                materils.add(process);
            }
        }
        if (detail.getProcessCode() == ProcessEnum.AGEING.getProcessId()) {
            GoodsInProcessStatisticAgeingExample example = new GoodsInProcessStatisticAgeingExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticId).andProcessCodeEqualTo(processType.getCode());
            List<GoodsInProcessStatisticAgeing> deploys = ageingMapper.selectByExample(example);
            for (int i = 0; i < deploys.size(); i++) {
                GoodInStatisticProcessDTO<GoodsInProcessStatisticAgeing> process = new GoodInStatisticProcessDTO<>();
                process.setProcess(deploys.get(i));
                process.setMaterialName(basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(deploys.get(i).getMaterialCode()).getMaterialName());
                if(deploys.get(i).getWeight()!=null){
                    total += deploys.get(i).getWeight();
                }
                if(deploys.get(i).getCoMetallicity()!=null){
                    co += deploys.get(i).getCoMetallicity();
                }
                if(deploys.get(i).getNiMetallicity()!=null){
                    ni += deploys.get(i).getNiMetallicity();
                }
                if(deploys.get(i).getMnMetallicity()!=null){
                    mn += deploys.get(i).getMnMetallicity();
                }
                materils.add(process);
            }
        }
        if (detail.getProcessCode() == ProcessEnum.DRYING.getProcessId()) {
            GoodsInProcessStatisticDryingExample example = new GoodsInProcessStatisticDryingExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticId).andProcessCodeEqualTo(processType.getCode());
            List<GoodsInProcessStatisticDrying> deploys = dryingMapper.selectByExample(example);
            for (int i = 0; i < deploys.size(); i++) {
                GoodInStatisticProcessDTO<GoodsInProcessStatisticDrying> process = new GoodInStatisticProcessDTO<>();
                process.setProcess(deploys.get(i));
                process.setMaterialName(basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(deploys.get(i).getMaterialCode()).getMaterialName());
                if(deploys.get(i).getWeight()!=null){
                    total += deploys.get(i).getWeight();
                }
                if(deploys.get(i).getCoMetallicity()!=null){
                    co += deploys.get(i).getCoMetallicity();
                }
                if(deploys.get(i).getNiMetallicity()!=null){
                    ni += deploys.get(i).getNiMetallicity();
                }
                if(deploys.get(i).getMnMetallicity()!=null){
                    mn += deploys.get(i).getMnMetallicity();
                }
                materils.add(process);
            }
        }
        if (detail.getProcessCode() == ProcessEnum.OTHER.getProcessId()) {
            GoodsInProcessStatisticOthersExample example = new GoodsInProcessStatisticOthersExample();
            example.createCriteria().andStatisticCodeEqualTo(statisticId).andProcessCodeEqualTo(processType.getCode());
            List<GoodsInProcessStatisticOthers> deploys = othersMapper.selectByExample(example);
            for (int i = 0; i < deploys.size(); i++) {
                GoodInStatisticProcessDTO<GoodsInProcessStatisticOthers> process = new GoodInStatisticProcessDTO<>();
                process.setProcess(deploys.get(i));
                process.setMaterialName(basicInfoPrecursorMaterialDetailsMapper.selectByPrimaryKey(deploys.get(i).getMaterialCode()).getMaterialName());
                if(deploys.get(i).getWeight()!=null){
                    total += deploys.get(i).getWeight();
                }
                if(deploys.get(i).getCoMetallicity()!=null){
                    co += deploys.get(i).getCoMetallicity();
                }
                if(deploys.get(i).getNiMetallicity()!=null){
                    ni += deploys.get(i).getNiMetallicity();
                }
                if(deploys.get(i).getMnMetallicity()!=null){
                    mn += deploys.get(i).getMnMetallicity();
                }
                materils.add(process);
            }
        }
        ans.setMaterials(materils);
        ans.setTotal(total);
        ans.setTotalCo(co);
        ans.setTotalMn(mn);
        ans.setTotalNi(ni);
        return ans;
    }

    @Override
    public GoodInStatisticTotalDTO analysisProcess(Integer periodId, String startTime) {
        GoodInStatisticTotalDTO ans = new GoodInStatisticTotalDTO();
        List<GoodInStatisticDetailDTO> goodInStatisticDetailDTOS = new ArrayList<>();
        BasicInfoPrecursorPeriod period = basicInfoPrecursorPeriodMapper.selectByPrimaryKey(periodId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        try {
            start = df.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GoodsInProcessStatisticHeadExample example = new GoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andStartTimeEqualTo(start).andFlagEqualTo(new Integer(1).byteValue());
        List<GoodsInProcessStatisticHead> heads = goodsInProcessStatisticHeadMapper.selectByExample(example);
        if (heads.size() == 0) {
            return null;
        }

        BasicInfoPrecursorProcessTypeExample example2 = new BasicInfoPrecursorProcessTypeExample();
        example2.createCriteria().andTypesEqualTo(new Integer(0).byteValue());
        List<BasicInfoPrecursorProcessType> processTypes = basicInfoPrecursorProcessTypeMapper.selectByExample(example2);
        for (int k = 0; k < processTypes.size(); k++) {
            boolean flag = false;
            Float total = 0f;
            Float ni = 0f;
            Float co = 0f;
            Float mn = 0f;
            for (int i = 0; i < heads.size(); i++) {
                GoodsInProcessStatisticByProcessDetailExample example1 = new GoodsInProcessStatisticByProcessDetailExample();
                example1.clear();
                example1.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
                List<GoodsInProcessStatisticByProcessDetail> processDetails = statisticByProcessDetailMapper.selectByExample(example1);
                for (int l = 0; l < processDetails.size(); l++) {
                    if (processDetails.get(l).getProcessCode() == processTypes.get(k).getCode()) {
                        flag = true;
                        total += processDetails.get(l).getTotals();
                        ni += processDetails.get(l).getNiValue();
                        co += processDetails.get(l).getCoValue();
                        mn += processDetails.get(l).getMnValue();
                    }
                }
            }
            if (flag) {
                GoodInStatisticDetailDTO detailDTO = new GoodInStatisticDetailDTO();
                detailDTO.setHead(heads.get(0));
                detailDTO.setPeriodName(period.getName());
                detailDTO.setProcessName(processTypes.get(k).getProcessName());
                detailDTO.setTotalCo(co);
                detailDTO.setTotalNi(ni);
                detailDTO.setTotalMn(mn);
                detailDTO.setTotal(total);
                goodInStatisticDetailDTOS.add(detailDTO);
            }
        }
        GoodsInProcessStatisticByProcessTotalExample example1 = new GoodsInProcessStatisticByProcessTotalExample();
        example1.createCriteria().andStatisticCodeEqualTo(heads.get(0).getCode());
        ans.setProcessTotal(processTotalMapper.selectByExample(example1).get(0));
        ans.setDetails(goodInStatisticDetailDTOS);
        return ans;
    }

    @Override
    public GoodInStatisticTotalDTO analysisLine(Integer periodId, String startTime) {
        GoodInStatisticTotalDTO ans = new GoodInStatisticTotalDTO();
        List<GoodInStatisticDetailDTO> goodInStatisticDetailDTOS = new ArrayList<>();
        BasicInfoPrecursorPeriod period = basicInfoPrecursorPeriodMapper.selectByPrimaryKey(periodId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        try {
            start = df.parse(startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GoodsInProcessStatisticHeadExample example = new GoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andStartTimeEqualTo(start).andFlagEqualTo(new Integer(1).byteValue());
        List<GoodsInProcessStatisticHead> heads = goodsInProcessStatisticHeadMapper.selectByExample(example);
        if (heads.size() == 0) {
            return null;
        }

        BasicInfoPrecursorProductionLineExample example2 = new BasicInfoPrecursorProductionLineExample();
        example2.createCriteria();
        List<BasicInfoPrecursorProductionLine> lines = lineMapper.selectByExample(example2);
        for (int k = 0; k < lines.size(); k++) {
            boolean flag = false;
            Float total = 0f;
            Float ni = 0f;
            Float co = 0f;
            Float mn = 0f;
            for (int i = 0; i < heads.size(); i++) {
                GoodsInProcessStatisticByLineDetailExample example1 = new GoodsInProcessStatisticByLineDetailExample();
                example1.clear();
                example1.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
                List<GoodsInProcessStatisticByLineDetail> lineDetails = lineDetailMapper.selectByExample(example1);
                for (int l = 0; l < lineDetails.size(); l++) {
                    if (lineDetails.get(l).getLineCode() == lines.get(k).getCode()) {
                        flag = true;
                        // total += lineDetails.get(l).getTotals();
                        ni += lineDetails.get(l).getNiValue();
                        co += lineDetails.get(l).getCoValue();
                        mn += lineDetails.get(l).getMnValue();
                    }
                }
            }
            if (flag) {
                GoodInStatisticDetailDTO detailDTO = new GoodInStatisticDetailDTO();
                detailDTO.setHead(heads.get(0));
                detailDTO.setPeriodName(period.getName());
                detailDTO.setLineName(lines.get(k).getName());
                detailDTO.setTotalCo(co);
                detailDTO.setTotalNi(ni);
                detailDTO.setTotalMn(mn);
                detailDTO.setTotal(total);
                goodInStatisticDetailDTOS.add(detailDTO);
            }
        }
        GoodsInProcessStatisticByProcessTotalExample example1 = new GoodsInProcessStatisticByProcessTotalExample();
        example1.createCriteria().andStatisticCodeEqualTo(heads.get(0).getCode());
        ans.setProcessTotal(processTotalMapper.selectByExample(example1).get(0));
        ans.setDetails(goodInStatisticDetailDTOS);
        return ans;
    }

    @Override
    public List processCompare(Integer periodId, Integer processId, String startTime, String endTime) {
        GoodsInProcessStatisticHeadExample example = new GoodsInProcessStatisticHeadExample();
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = df.parse(startTime);
            endDate = df.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        example.createCriteria().andPeriodCodeEqualTo(periodId).andStartTimeBetween(startDate, endDate).andFlagEqualTo(new Integer(1).byteValue());
        List<GoodsInProcessStatisticHead> heads = goodsInProcessStatisticHeadMapper.selectByExample(example);
        List<GoodInCompareDTO> ans = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            GoodsInProcessStatisticByProcessDetailExample example1 = new GoodsInProcessStatisticByProcessDetailExample();
            example1.clear();
            example1.createCriteria().andStatisticCodeEqualTo(heads.get(0).getCode()).andProcessCodeEqualTo(processId);
            List<GoodsInProcessStatisticByProcessDetail> details = statisticByProcessDetailMapper.selectByExample(example1);
            if (details.size() != 0) {
                GoodsInProcessStatisticByProcessDetail detail = details.get(0);
                GoodInCompareDTO temp = new GoodInCompareDTO();
                temp.setPeriodNum(detail.getLineName());
                temp.setCo(detail.getCoValue());
                temp.setNi(detail.getNiValue());
                temp.setMn(detail.getMnValue());
                ans.add(temp);
            }
        }
        return ans;
    }

    @Override
    public List lineCompare(Integer periodId, Integer lineId, String startTime, String endTime) {
        GoodsInProcessStatisticHeadExample example = new GoodsInProcessStatisticHeadExample();
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            startDate = df.parse(startTime);
            endDate = df.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        example.createCriteria().andPeriodCodeEqualTo(periodId).andStartTimeBetween(startDate, endDate).andFlagEqualTo(new Integer(1).byteValue());
        List<GoodsInProcessStatisticHead> heads = goodsInProcessStatisticHeadMapper.selectByExample(example);
        List<GoodInCompareDTO> ans = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            GoodsInProcessStatisticByLineDetailExample example1 = new GoodsInProcessStatisticByLineDetailExample();
            example1.clear();
            example1.createCriteria().andStatisticCodeEqualTo(heads.get(0).getCode()).andLineCodeEqualTo(lineId);
            List<GoodsInProcessStatisticByLineDetail> details = lineDetailMapper.selectByExample(example1);
            if (details.size() != 0) {
                GoodsInProcessStatisticByLineDetail detail = details.get(0);
                GoodInCompareDTO temp = new GoodInCompareDTO();
                temp.setPeriodNum(detail.getLineName());
                temp.setCo(detail.getCoValue());
                temp.setNi(detail.getNiValue());
                temp.setMn(detail.getMnValue());
                ans.add(temp);
            }
        }
        return ans;
    }

    @Override
    public Map getLineNameByPeriod(Integer periodId) {
        GoodsInProcessStatisticHeadExample example = new GoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andFlagEqualTo(new Integer(1).byteValue());
        example.setOrderByClause("start_time desc");
        List<GoodsInProcessStatisticHead> heads = goodsInProcessStatisticHeadMapper.selectByExample(example);
        if (heads.size() == 0) {
            //以前都没有这个周期的数，那这就是第一期
            Map<String, Object> ans = new HashMap<>();
            ans.put("period", 1);
            ans.put("endTime", "");
            return ans;
        }
        Map<String, Object> ans = new HashMap<>();
        ans.put("period", heads.get(0).getLineName() + 1);
        ans.put("endTime", ComUtil.dateToString(heads.get(0).getEndTime(), "yyyy-MM-dd"));
        return ans;
    }

    @Override
    public List getStartTime(Integer periodId) {
        List<String> ans = new ArrayList<>();

        GoodsInProcessStatisticHeadExample example = new GoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andFlagEqualTo(new Integer(1).byteValue());
        List<GoodsInProcessStatisticHead> heads = goodsInProcessStatisticHeadMapper.selectByExample(example);
        for (int i = 0; i < heads.size(); i++) {
            ans.add(ComUtil.dateToString(heads.get(i).getStartTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        return ans;
    }

    @Override
    public void delete(Long stasticId) {
        deleteCascade(stasticId);
        goodsInProcessStatisticHeadMapper.deleteByPrimaryKey(stasticId);
    }

    public void deleteCascade(Long stasticId) {
        GoodsInProcessStatisticMonocrystalDeployExample example1 = new GoodsInProcessStatisticMonocrystalDeployExample();
        example1.createCriteria().andStatisticCodeEqualTo(stasticId);
        monocrystalDeployMapper.deleteByExample(example1);

        GoodsInProcessStatisticSaltMixtureDeployExample example2 = new GoodsInProcessStatisticSaltMixtureDeployExample();
        example2.createCriteria().andStatisticCodeEqualTo(stasticId);
        saltMixtureDeployMapper.deleteByExample(example2);

        GoodsInProcessStatisticCompoundExample example3 = new GoodsInProcessStatisticCompoundExample();
        example3.createCriteria().andStatisticCodeEqualTo(stasticId);
        compoundMapper.deleteByExample(example3);

        GoodsInProcessStatisticAgeingExample example4 = new GoodsInProcessStatisticAgeingExample();
        example4.createCriteria().andStatisticCodeEqualTo(stasticId);
        ageingMapper.deleteByExample(example4);

        GoodsInProcessStatisticDryingExample example5 = new GoodsInProcessStatisticDryingExample();
        example5.createCriteria().andStatisticCodeEqualTo(stasticId);
        dryingMapper.deleteByExample(example5);

        GoodsInProcessStatisticOthersExample example6 = new GoodsInProcessStatisticOthersExample();
        example6.createCriteria().andStatisticCodeEqualTo(stasticId);
        othersMapper.deleteByExample(example6);

        GoodsInProcessStatisticLineProductionsExample examplee = new GoodsInProcessStatisticLineProductionsExample();
        examplee.createCriteria().andStatisticCodeEqualTo(stasticId);
        goodsInProcessStatisticLineProductionsMapper.deleteByExample(examplee);
    }

    @Override
    public List getVolumeWeight(Integer processId) {
        if (processId == 11) {
            List<Map<String, Object>> ans = new ArrayList<>();
            ProductionInstrumentAddressExample example = new ProductionInstrumentAddressExample();
            example.createCriteria();
            List<ProductionInstrumentAddress> addresses = instrumentAddressMapper.selectByExample(example);
            for (int i = 0; i < addresses.size(); i++) {
                Map<String, Object> map = new HashMap<>();
                Float data = RealTimeUtil.timelyHttpMethod("http://192.168.190.162:10086/api/Snapshot?tagName", addresses.get(i).getAddress());
                map.put("name", addresses.get(i).getName());
                map.put("address", addresses.get(i).getAddress());
                if (null == data) {
                    map.put("value", -1);//有地址，没有返回值ano'de
                } else {
                    map.put("value", data);
                }
                ans.add(map);
            }
            return ans;
        }
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
                Float data = RealTimeUtil.timelyHttpMethod("http://192.168.190.162:10086/api/Snapshot?tagName", address.getAddress());
                map.put("address", address.getAddress());
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
        GoodsInProcessStatisticHeadExample example1 = new GoodsInProcessStatisticHeadExample();
        example1.createCriteria().andFlagEqualTo(new Integer(1).byteValue());
        List<GoodsInProcessStatisticHead> heads = goodsInProcessStatisticHeadMapper.selectByExample(example1);
        if (heads.size() == 0)
            return new Page(new ArrayList(), 1, 10);
        GoodsInProcessStatisticHead head = heads.get(0);
        for (int i = 1; i < heads.size(); i++) {
            if (head.getLineName() < heads.get(i).getLineName()) {
                head = heads.get(i);
            }
        }
        List<GoodInStatisticDetailDTO> ans = new ArrayList<>();
        GoodsInProcessStatisticByProcessDetailExample example = new GoodsInProcessStatisticByProcessDetailExample();
        example.createCriteria().andStatisticCodeEqualTo(head.getCode());
        List<GoodsInProcessStatisticByProcessDetail> processDetails = statisticByProcessDetailMapper.selectByExample(example);
        for (int l = 0; l < processDetails.size(); l++) {
            GoodInStatisticDetailDTO temp = new GoodInStatisticDetailDTO();
            temp.setHead(head);
            temp.setPeriodName(basicInfoPrecursorPeriodMapper.selectByPrimaryKey(temp.getHead().getPeriodCode()).getName());
            temp.setDetail(processDetails.get(l));
            temp.setProcessName(basicInfoPrecursorProcessTypeMapper.selectByPrimaryKey(processDetails.get(l).getProcessCode()).getProcessName());
            ans.add(temp);
        }
        return new Page(ans, 1, 10);
    }
}

