package com.jinchi.common.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.HistoryDataDto;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.anode.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.ComUtil;
import com.jinchi.common.utils.GzipUtil;
import com.jinchi.common.utils.RealTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Transactional
public class AnodeGoodinServiceImp implements AnodeGoodinService {

    @Autowired
    AnodeGoodsInProcessStatisticHeadMapper headMapper;
    @Autowired
    BasicInfoAnodeProcessNameMapper processNameMapper;
    @Autowired
    BasicInfoAnodeMaterialTypesMapper materialMapper;
    @Autowired
    BasicInfoAnodePeriodMapper periodMapper;
    @Autowired
    BasicInfoAnodeProductionTypeMapper typeMapper;
    @Autowired
    BasicInfoAnodeProductionLineMapper lineMapper;
    @Autowired
    AnodeGoodsInProcessStatisticOnlineRawMaterialMapper onlineRawMaterialMapper;
    @Autowired
    AnodeGoodsInProcessStatisticPremixColterMapper premixColterMapper;
    @Autowired
    AnodeGoodsInProcessStatisticPremixTemporaryStorageMapper premixTemporaryStorageMapper;
    @Autowired
    AnodeGoodsInProcessStatisticPresinteringMapper presinteringMapper;
    @Autowired
    AnodeGoodsInProcessStatisticSmashMapper smashMapper;
    @Autowired
    AnodeGoodsInProcessStatisticSecondMixMapper secondMixMapper;
    @Autowired
    AnodeGoodsInProcessStatisticSecondSinteringMapper secondSinteringMapper;
    @Autowired
    AnodeGoodsInProcessStatisticPackageMapper packageMapper;
    @Autowired
    AnodeGoodsInProcessStatisticPlantPendingMapper plantPendingMapper;
    @Autowired
    AnodeGoodsInProcessStatisticWarehousePendingMapper warehousePendingMapper;
    @Autowired
    AnodeGoodsInProcessStatisticSingleMaterialWeightsMapper singleMaterialWeightsMapper;
    @Autowired
    AnodeGoodsInProcessStatisticSingleMaterialCountsMapper singleMaterialCountsMapper;
    @Autowired
    AnodeGoodsInProcessStatisticLineProcessProductionsMapper lineProcessProductionsMapper;
    @Autowired
    BasicInfoAnodeCalculateCoefficientMapper coefficientMapper;
    @Autowired
    BasicInfoAnodeMaterialPlcMapMapper plcMapMapper;
    @Autowired
    BasicInfoAnodePlcAddressMapper plcAddressMapper;
    @Autowired
    AnodeGoodsInProcessStatisticByProcessTotalsMapper processTotalsMapper;
    @Autowired
    AnodeGoodsInProcessStatisticByLineDetailsMapper lineDetailsMapper;
    @Autowired
    AnodeGoodsInProcessStatisticByLineTotalsRawMaterialMapper rawMaterialMapper;
    @Autowired
    AnodeProductionLineService anodeProductionLineService;
    @Autowired
    AnodeProcessNameService anodeProcessNameService;
    @Autowired
    BasicInfoAnodePlcAddressMapper addressMapper;
    @Autowired
    BasicInfoAnodeMaterialPlcMapMapper mapMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Map<String, Object> addComfirm(AnodeGoodsInProcessStatisticHead head) {
        Map<String, Object> ans = new HashMap<>();
        Integer periods = head.getPeriods();
        Integer lineCode = head.getLineCode();
        Integer periodId = head.getPeriodCode();
        head.setFlag(false);
        //List<BasicInfoAnodeProductionLine> lines = lineMapper.selectByExample(new BasicInfoAnodeProductionLineExample());
        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods);
        List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(example);
        if(heads.size() != 0){
            if(heads.get(0).getBeginTime().getTime() != head.getBeginTime().getTime()){
                ans.put("message", "本周期类型的统计数据时间不正确");
                ans.put("startTime",ComUtil.dateToString(heads.get(0).getBeginTime(),"yyyy-MM-dd HH:mm:ss"));
                ans.put("endTime",ComUtil.dateToString(heads.get(0).getEndTime(),"yyyy-MM-dd HH:mm:ss"));
                ans.put("code", -2);
                return ans;
            }
        }
        example.clear();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andLineCodeEqualTo(lineCode).andPeriodsEqualTo(periods);
        if (headMapper.selectByExample(example).size() == 0) {
            headMapper.insertSelective(head);
            ans.put("message", "操作成功");
            ans.put("code", head.getCode());
            return ans;
        }
        ans.put("message","本期数据已存在");
        ans.put("code",-1);
        return ans;
    }

    @Override
    public AnodeGoodinDTO afterComfirm(Long id) {
        AnodeGoodinDTO ans = getTable(id);

        {//在线
            AnodeProcess onlineProcess = ans.getProcesses().get(0);
            List<AnodeMaterial> mats = onlineProcess.getMaterials();
            for (int i = 0; i < mats.size(); i++) {
                if(mats.get(i).getCode() == 1){
                   AnodeGoodsInProcessStatisticOnlineRawMaterialExample example = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
                   example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(1);
                   List<AnodeGoodsInProcessStatisticOnlineRawMaterial> temp = onlineRawMaterialMapper.selectByExample(example);
                   if(temp.size()!=0){
                       mats.get(i).setReceive(temp.get(0).getFeedstock());
                   }
                }
                if(mats.get(i).getCode() == 2){
                    AnodeGoodsInProcessStatisticOnlineRawMaterialExample example = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
                    example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(2);
                    List<AnodeGoodsInProcessStatisticOnlineRawMaterial> temp = onlineRawMaterialMapper.selectByExample(example);
                    if(temp.size()!=0){
                        mats.get(i).setReceive(temp.get(0).getFeedstock());
                    }
                }
                if (mats.get(i).getCode() == 3) {//预混料
                    AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                    example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(3);
                    List<AnodeGoodsInProcessStatisticSingleMaterialWeights> temp = singleMaterialWeightsMapper.selectByExample(example);
                    if (temp.size() != 0) {
                        mats.get(i).setValue(temp.get(0).getWeights());
                    }
                }
                if (mats.get(i).getCode() == 4) {//烧结料
                    AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                    example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(4);
                    List<AnodeGoodsInProcessStatisticSingleMaterialWeights> temp = singleMaterialWeightsMapper.selectByExample(example);
                    if (temp.size() != 0) {
                        mats.get(i).setValue(temp.get(0).getWeights());
                    }
                }
            }
            onlineProcess.setMaterials(mats);
            ans.getProcesses().set(0, onlineProcess);
        }

        {//预烧
            AnodeProcess onlineProcess = ans.getProcesses().get(3);
            List<AnodeMaterial> mats = onlineProcess.getMaterials();
            for (int i = 0; i < mats.size(); i++) {//预烧料
                if (mats.get(i).getCode() == 17) {
                    AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                    example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(17);
                    List<AnodeGoodsInProcessStatisticSingleMaterialWeights> temp = singleMaterialWeightsMapper.selectByExample(example);
                    if (temp.size() != 0) {
                        mats.get(i).setValue(temp.get(0).getWeights());
                    }
                }
                if (mats.get(i).getCode() == 18) {//烧结料
                    AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                    example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(18);
                    List<AnodeGoodsInProcessStatisticSingleMaterialWeights> temp = singleMaterialWeightsMapper.selectByExample(example);
                    if (temp.size() != 0) {
                        mats.get(i).setValue(temp.get(0).getWeights());
                    }
                }
            }
            onlineProcess.setMaterials(mats);
            ans.getProcesses().set(3, onlineProcess);
        }

        {//二烧
            AnodeProcess onlineProcess = ans.getProcesses().get(6);
            List<AnodeMaterial> mats = onlineProcess.getMaterials();
            for (int i = 0; i < mats.size(); i++) {//二烧
                if (mats.get(i).getCode() == 38) {
                    AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                    example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(38);
                    List<AnodeGoodsInProcessStatisticSingleMaterialWeights> temp = singleMaterialWeightsMapper.selectByExample(example);
                    if (temp.size() != 0) {
                        mats.get(i).setValue(temp.get(0).getWeights());
                    }
                }
            }
            onlineProcess.setMaterials(mats);
            ans.getProcesses().set(6, onlineProcess);
        }

        {//包装
            AnodeProcess onlineProcess = ans.getProcesses().get(7);
            List<AnodeMaterial> mats = onlineProcess.getMaterials();
            AnodeGoodsInProcessStatisticLineProcessProductionsExample pExample = new AnodeGoodsInProcessStatisticLineProcessProductionsExample();
            pExample.createCriteria().andStatisticCodeEqualTo(id);
            List<AnodeGoodsInProcessStatisticLineProcessProductions> ps = lineProcessProductionsMapper.selectByExample(pExample);
            //List<AnodeMaterial> others = onlineProcess.getOthers();
            if(ps.size()!=0){
                //others.get(0).setValue(ps.get(0).getBagCounts().floatValue());
                mats.get(6).setValue(ps.get(0).getBagCounts().floatValue()); //第六个就是包装袋数
                mats.get(7).setValue(ps.get(0).getProductionStorage()); //第七个就是产成品入库
                //others.get(0).setValue(ps.get(0).getProductionStorage());
            }
            for (int i = 0; i < mats.size(); i++) {//烧结料
                if (mats.get(i).getCode() == 47) {
                    AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                    example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(47);
                    List<AnodeGoodsInProcessStatisticSingleMaterialWeights> temp = singleMaterialWeightsMapper.selectByExample(example);
                    if (temp.size() != 0) {
                        mats.get(i).setValue(temp.get(0).getWeights());
                    }
                }
                if (mats.get(i).getCode() == 50) {//产成品待入库
                    AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                    example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(50);
                    List<AnodeGoodsInProcessStatisticSingleMaterialWeights> temp = singleMaterialWeightsMapper.selectByExample(example);
                    if (temp.size() != 0) {
                        mats.get(i).setValue(temp.get(0).getWeights());
                    }
                }
                /*if(mats.get(i).getCode() == 45){//周期内包装袋数
                    if(ps.size()!=0){
                        //others.get(0).setValue(ps.get(0).getBagCounts().floatValue());
                        mats.get(i).setValue(ps.get(0).getBagCounts().floatValue());
                    }
                }*/
            }
            onlineProcess.setMaterials(mats);
            //onlineProcess.setOthers(others);
            ans.getProcesses().set(7, onlineProcess);
        }

        {//车间
            AnodeProcess onlineProcess = ans.getProcesses().get(8);
            List<AnodeMaterial> mats = onlineProcess.getMaterials();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticPlantPendingExample example = new AnodeGoodsInProcessStatisticPlantPendingExample();
                example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(mats.get(i).getCode());
                List<AnodeGoodsInProcessStatisticPlantPending> temp = plantPendingMapper.selectByExample(example);
                if (temp.size() != 0) {
                    mats.get(i).setValue(temp.get(0).getBalance());
                }
            }
            onlineProcess.setMaterials(mats);
            ans.getProcesses().set(8, onlineProcess);
        }

        {//仓库
            AnodeProcess onlineProcess = ans.getProcesses().get(9);
            List<AnodeMaterial> mats = onlineProcess.getMaterials();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticWarehousePendingExample example = new AnodeGoodsInProcessStatisticWarehousePendingExample();
                example.createCriteria().andStatisticCodeEqualTo(id).andMaterialCodeEqualTo(mats.get(i).getCode());
                List<AnodeGoodsInProcessStatisticWarehousePending> temp = warehousePendingMapper.selectByExample(example);
                if (temp.size() != 0) {
                    mats.get(i).setValue(temp.get(0).getBalance());
                }
            }
            onlineProcess.setMaterials(mats);
            ans.getProcesses().set(9, onlineProcess);
        }
        return ans;
    }

    @Override
    public Map getNextPeriods(Integer periodId, Integer lineCode) {
        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andLineCodeEqualTo(lineCode).andFlagEqualTo(true);
        List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(example);
        int max = 0;
        Date end = null;
        for (int i = 0; i < heads.size(); i++) {
            if (max < heads.get(i).getPeriods()) {
                max = heads.get(i).getPeriods();
                end = heads.get(i).getEndTime();
            }
        }
        Map<String, Object> ans = new HashMap<>();
        ans.put("periods", max + 1);
        ans.put("endTime", ComUtil.dateToString(end, "yyyy-MM-dd HH:mm:ss"));
        return ans;
    }

    private AnodeGoodinDTO getTable(Long id) {
        AnodeGoodinDTO ans = new AnodeGoodinDTO();
        AnodeGoodsInProcessStatisticHead head = headMapper.selectByPrimaryKey(id);
        ans.setHead(head);
        ans.setPeriodName(periodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
        ans.setLine(lineMapper.selectByPrimaryKey(head.getLineCode()));
        ans.setType(typeMapper.selectByPrimaryKey(head.getTypeCode()));

        List<AnodeProcess> anodeProcesses = new ArrayList<>();

        BasicInfoAnodeProcessNameExample processExample = new BasicInfoAnodeProcessNameExample();
        List<BasicInfoAnodeProcessName> processNames = processNameMapper.selectByExample(processExample);


        for (int i = 0; i < processNames.size(); i++) {
            BasicInfoAnodeProcessName process = processNames.get(i);
            AnodeProcess anodeProcess = new AnodeProcess();

            anodeProcess.setPeriodName(process.getProcessName());
            anodeProcess.setPeriodId(process.getCode());
            BasicInfoAnodeMaterialTypesExample example = new BasicInfoAnodeMaterialTypesExample();
            example.createCriteria().andProcessCodeEqualTo(process.getCode());
            List<BasicInfoAnodeMaterialTypes> materials = materialMapper.selectByExample(example);
            List<AnodeMaterial> infos = new ArrayList<>();
            //List<AnodeMaterial> others = new ArrayList<>();

            //在线原料
            if (process.getCode() == 1) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                    if (info.getDateType() == new Integer(2).byteValue()) {//智能仓库数据,原料全是智能仓库数据
                        Float temp = getValueByNameByTime(info.getMaterialName(), head.getBeginTime(), head.getEndTime());
                        if (info.getFlag()) {//表格值
                            info.setReceive(temp);
                        } else {
                            info.setValue(temp);
                        }
                    }
                    infos.add(info);
                }
            }
            //预混（犁刀混）
            if (process.getCode() == 2) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                    if (info.getDateType() == new Integer(0).byteValue()) {//dcs数据,犁刀混全是dcs数据
                        //Float temp1 = getDcsValue(info.getCode(), head.getLineCode(), "已混量", head.getEndTime());
                        Float temp2 = getDcsValue(info.getCode(), head.getLineCode(), "结存量", head.getEndTime());
                        Integer temp3 = getDcsCount(info.getCode(),head.getLineCode(),head.getPeriodCode(),head.getEndTime());
                        //fixme 物料有两个plc地址，无法对应出来
                        info.setMix(0f);
                        info.setBalance(temp2);
                        info.setValue(temp3.floatValue());
                    }
                    infos.add(info);
                }
            }
            //预混（暂存仓）
            if (process.getCode() == 3) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                    if (info.getDateType() == new Integer(0).byteValue()) {//dcs数据,暂存仓全是dcs数据
                        Float temp1 = getDcsValue(info.getCode(), head.getLineCode(), "进料量", head.getEndTime());
                        Float temp2 = getDcsValue(info.getCode(), head.getLineCode(), "结存量", head.getEndTime());
                        //fixme
                        info.setReceive(temp1);
                        info.setBalance(temp2);
                    }
                    infos.add(info);
                }
            }
            //预烧（窑炉）
            if (process.getCode() == 4) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                    if (info.getDateType() == new Integer(0).byteValue()) {//dcs数据,预烧部分是dcs数据
                        Float temp1 = getDcsValue(info.getCode(), head.getLineCode(), "入炉排数", head.getEndTime());
                        Float temp2 = getDcsValue(info.getCode(), head.getLineCode(), "出炉排数", head.getEndTime());
                       // Float weight = getDcsValue(info.getCode(), head.getLineCode(), "重量", head.getEndTime());
                        //fixme
                        if (info.getFlag()) {
                            info.setIntoFurnace(temp1.intValue());
                            info.setOutFurnace(temp2.intValue());
                        } else {
                            info.setValue(0f);
                        }
                    }
                    infos.add(info);
                }
            }
            //粉碎
            if (process.getCode() == 5) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                    if (info.getDateType() == new Integer(0).byteValue()) {//dcs数据,粉碎全是是dcs数据
                        Float temp1 = getDcsValue(info.getCode(), head.getLineCode(), "进料量", head.getEndTime());
                        Float temp2 = getDcsValue(info.getCode(), head.getLineCode(), "结存量", head.getEndTime());
                        Integer temp3 = getDcsCount(info.getCode(), head.getLineCode(), head.getPeriodCode(),head.getEndTime());
                        //fixme
                        if (info.getFlag()) {
                            info.setReceive(temp1);
                            info.setBalance(temp2);
                        } else {
                            info.setValue(temp3.floatValue());
                        }
                    }
                    infos.add(info);
                }
            }
            //二混
            if (process.getCode() == 6) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                    if (info.getDateType() == new Integer(0).byteValue()) {//dcs数据,二混全是dcs数据
                        Float temp1 = getDcsValue(info.getCode(), head.getLineCode(), "进料量", head.getEndTime());
                        Float temp2 = getDcsValue(info.getCode(), head.getLineCode(), "结存量", head.getEndTime());
                        Integer temp3 = getDcsCount(info.getCode(), head.getLineCode(), head.getPeriodCode(), head.getEndTime());
                        //fixme
                        if (info.getFlag()) {
                            info.setReceive(temp1);
                            info.setBalance(temp2);
                        } else {
                            info.setValue(temp3.floatValue());
                        }
                    }
                    infos.add(info);
                }
            }
            //二烧
            if (process.getCode() == 7) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                    if (info.getDateType() == new Integer(0).byteValue()) {//dcs数据,二烧部分是dcs数据
                        Float temp1 = getDcsValue(info.getCode(), head.getLineCode(), "入炉排数", head.getEndTime());
                        Float temp2 = getDcsValue(info.getCode(), head.getLineCode(), "出炉排数", head.getEndTime());
                        Integer temp3 = getDcsCount(info.getCode(), head.getLineCode(), head.getPeriodCode(), head.getEndTime());
                        //fixme
                        if (info.getFlag()) {
                            info.setIntoFurnace(temp1.intValue());
                            info.setOutFurnace(temp2.intValue());
                        } else {
                            info.setValue(temp3.floatValue());
                        }
                    }
                    infos.add(info);
                }
            }
            //包装
            if (process.getCode() == 8) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                    if (info.getDateType() == new Integer(0).byteValue()) {//dcs数据,包装部分是dcs数据
                        Float temp1 = getDcsValue(info.getCode(), head.getLineCode(), "进料量", head.getEndTime());
                        Float temp2 = getDcsValue(info.getCode(), head.getLineCode(), "结存量", head.getEndTime());
                        Integer temp3 = getDcsCount(info.getCode(), head.getLineCode(), head.getPeriodCode(), head.getEndTime());
                        //fixme
                        if(info.getCode() == 39){
                            info.setReceive(temp1);
                        }
                        if (info.getFlag()) {
                            info.setBalance(temp2);
                        } else {
                            info.setValue(temp3.floatValue());
                        }
                    }
                    if(info.getDateType() == new Integer(2).byteValue()){//智能仓库数据，专为产成品
                        Float temp4 = getValueByNameByTime(info.getMaterialName(),head.getBeginTime(),head.getEndTime());
                        info.setValue(temp4);
                    }
                    infos.add(info);
                }
                //AnodeMaterial m2 = new AnodeMaterial();
                //m2.setMaterialName("产成品入库");
                //others.add(m2);
            }
            //车间待处理
            if (process.getCode() == 9) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                   // Float weight = getDcsValue(info.getCode(), head.getLineCode(), "重量", head.getEndTime());
                    info.setValue(0f);
                    infos.add(info);
                }
            }
            //仓库待处理
            if (process.getCode() == 10) {
                for (int l = 0; l < materials.size(); l++) {
                    AnodeMaterial info = new AnodeMaterial(materials.get(l));
                   // Float weight = getDcsValue(info.getCode(), head.getLineCode(), "重量", head.getEndTime());
                    info.setValue(0f);
                    infos.add(info);
                }
            }
            //anodeProcess.setOthers(others);
            anodeProcess.setMaterials(infos);
            anodeProcesses.add(anodeProcess);
        }
        ans.setProcesses(anodeProcesses);
        return ans;
    }

    @Override
    public String saveOrCommit(AnodeGoodinDTO data, Long id, Integer flag) {
        if (flag == 0) {
            save(data, id);
        }
        if (flag == 1) {
            commit(data, id);
        }
        return "操作成功";
    }

    private void save(AnodeGoodinDTO data, Long id) {
        deleteCascade(id);
        AnodeGoodsInProcessStatisticHead head = headMapper.selectByPrimaryKey(id);

        List<AnodeProcess> processes = data.getProcesses();
        for (int i = 0; i < processes.size(); i++) {
            AnodeProcess process = processes.get(i);
            List<AnodeMaterial> infos = process.getMaterials();
            //在线原料
            if (process.getPeriodId() == 1) {
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    if (info.getFlag()) {
                        AnodeGoodsInProcessStatisticOnlineRawMaterial mat = new AnodeGoodsInProcessStatisticOnlineRawMaterial();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setFeedstock(info.getReceive());
                        mat.setBalance(info.getBalance());
                        onlineRawMaterialMapper.insertSelective(mat);
                    } else {
                        AnodeGoodsInProcessStatisticSingleMaterialWeights mat = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setWeights(info.getValue());
                        singleMaterialWeightsMapper.insertSelective(mat);
                    }
                }
            }
            //预混（犁刀混）
            if (process.getPeriodId() == 2) {
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    AnodeGoodsInProcessStatisticPremixColter mat = new AnodeGoodsInProcessStatisticPremixColter();
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(process.getPeriodId());
                    //mat.setFeedstock();
                    //mat.setConsume();
                    mat.setBalance(info.getBalance());
                    // mat.set
                    premixColterMapper.insertSelective(mat);
                }
            }
            //预混（暂存仓）
            if (process.getPeriodId() == 3) {
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    AnodeGoodsInProcessStatisticPremixTemporaryStorage mat = new AnodeGoodsInProcessStatisticPremixTemporaryStorage();
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(process.getPeriodId());
                    mat.setFeedstock(info.getReceive());
                    //mat.setConsume();
                    mat.setBalance(info.getBalance());
                    premixTemporaryStorageMapper.insertSelective(mat);
                }
            }
            //预烧（窑炉）
            if (process.getPeriodId() == 4) {
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    if (info.getFlag()) {
                        AnodeGoodsInProcessStatisticPresintering mat = new AnodeGoodsInProcessStatisticPresintering();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        //mat.setFeedstock(info.getReceive());
                        mat.setIntoFurnaceNum(info.getIntoFurnace().intValue());
                        mat.setOutFurnaceNum(info.getOutFurnace().intValue());
                        mat.setBalance(info.getBalance());
                        presinteringMapper.insertSelective(mat);
                    } else {
                        AnodeGoodsInProcessStatisticSingleMaterialWeights mat = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setWeights(info.getValue());
                        singleMaterialWeightsMapper.insertSelective(mat);
                    }
                }
            }
            //粉碎
            if (process.getPeriodId() == 5) {
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    if (info.getFlag()) {
                        AnodeGoodsInProcessStatisticSmash mat = new AnodeGoodsInProcessStatisticSmash();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setFeedstock(info.getReceive());
                        mat.setBalance(info.getBalance());
                        smashMapper.insertSelective(mat);
                    } else {
                        AnodeGoodsInProcessStatisticSingleMaterialCounts mat = new AnodeGoodsInProcessStatisticSingleMaterialCounts();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setCounts(info.getValue().intValue());
                        singleMaterialCountsMapper.insertSelective(mat);
                    }
                }
            }
            //二混
            if (process.getPeriodId() == 6) {
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    if (info.getFlag()) {
                        AnodeGoodsInProcessStatisticSecondMix mat = new AnodeGoodsInProcessStatisticSecondMix();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setFeedstock(info.getReceive());
                        mat.setBalance(info.getBalance());
                        secondMixMapper.insertSelective(mat);
                    } else {
                        AnodeGoodsInProcessStatisticSingleMaterialCounts mat = new AnodeGoodsInProcessStatisticSingleMaterialCounts();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setCounts(info.getValue().intValue());
                        singleMaterialCountsMapper.insertSelective(mat);
                    }
                }
            }
            //二烧
            if (process.getPeriodId() == 7) {
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    if (info.getFlag()) {
                        AnodeGoodsInProcessStatisticSecondSintering mat = new AnodeGoodsInProcessStatisticSecondSintering();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setIntoFurnaceNum(info.getIntoFurnace().intValue());
                        mat.setOutFurnaceNum(info.getOutFurnace().intValue());
                        mat.setBalance(info.getBalance());
                        secondSinteringMapper.insertSelective(mat);
                    } else {
                        AnodeGoodsInProcessStatisticSingleMaterialWeights mat = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setWeights(info.getValue());
                        singleMaterialWeightsMapper.insertSelective(mat);
                    }
                }
            }
            //包装
            if (process.getPeriodId() == 8) {
                AnodeMaterial bags = new AnodeMaterial();
                AnodeMaterial ccp = new AnodeMaterial();
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    if(info.getCode() == 45)
                        bags = info;
                    if(info.getCode() == 46)
                        ccp = info;
                    if (info.getFlag() && info.getCode() != 45 && info.getCode() != 46) {
                        AnodeGoodsInProcessStatisticPackage mat = new AnodeGoodsInProcessStatisticPackage();
                        mat.setStatisticCode(id);
                        mat.setLineCode(head.getLineCode());
                        mat.setTypeCode(head.getTypeCode());
                        mat.setMaterialCode(info.getCode());
                        mat.setProcessCode(process.getPeriodId());
                        mat.setBalance(info.getBalance());
                        mat.setFeedstock(info.getReceive());
                        packageMapper.insertSelective(mat);
                    } else {
                        //烧结料（成品）,产成品待入库
                        if (info.getCode() == 47 || info.getCode() == 50) {
                            AnodeGoodsInProcessStatisticSingleMaterialWeights mat = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                            mat.setStatisticCode(id);
                            mat.setLineCode(head.getLineCode());
                            mat.setTypeCode(head.getTypeCode());
                            mat.setMaterialCode(info.getCode());
                            mat.setProcessCode(process.getPeriodId());
                            mat.setWeights(info.getValue());
                            singleMaterialWeightsMapper.insertSelective(mat);
                        }
                        //1#二烧正压输送次数,2#二烧正压输送次数
                        else if (info.getCode() == 48 || info.getCode() == 49) {
                            AnodeGoodsInProcessStatisticSingleMaterialCounts mat = new AnodeGoodsInProcessStatisticSingleMaterialCounts();
                            mat.setStatisticCode(id);
                            mat.setLineCode(head.getLineCode());
                            mat.setTypeCode(head.getTypeCode());
                            mat.setMaterialCode(info.getCode());
                            mat.setProcessCode(process.getPeriodId());
                            mat.setCounts(info.getValue().intValue());
                            singleMaterialCountsMapper.insertSelective(mat);
                        } else {
                        }
                    }
                }
                AnodeGoodsInProcessStatisticLineProcessProductions mat = new AnodeGoodsInProcessStatisticLineProcessProductions();
                mat.setStatisticCode(id);
                mat.setLineCode(head.getLineCode());
                mat.setTypeCode(head.getTypeCode());
                mat.setProcessCode(process.getPeriodId());
                mat.setBagCounts(bags.getValue().intValue());
                mat.setProductionStorage(ccp.getValue());
                mat.setProductionWeights(0f);
                lineProcessProductionsMapper.insertSelective(mat);
            }
            //车间待处理
            if (process.getPeriodId() == 9) {
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    AnodeGoodsInProcessStatisticPlantPending mat = new AnodeGoodsInProcessStatisticPlantPending();
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(process.getPeriodId());
                    mat.setFeedstock(0f);
                    mat.setConsume(0f);
                    mat.setBalance(info.getValue());
                    plantPendingMapper.insertSelective(mat);
                }
            }
            //仓库待处理
            if (process.getPeriodId() == 10) {
                for (int l = 0; l < infos.size(); l++) {
                    AnodeMaterial info = infos.get(l);
                    AnodeGoodsInProcessStatisticWarehousePending mat = new AnodeGoodsInProcessStatisticWarehousePending();
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(process.getPeriodId());
                    mat.setFeedstock(0f);
                    mat.setConsume(0f);
                    mat.setBalance(info.getValue());
                    warehousePendingMapper.insertSelective(mat);
                }
            }
        }
    }

    private void commit(AnodeGoodinDTO data, Long id) {
        //save(data, id);
        deleteCascade(id);
        AnodeGoodsInProcessStatisticHead head = headMapper.selectByPrimaryKey(id);

        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();
        example.createCriteria().andLineCodeEqualTo(head.getLineCode()).andPeriodCodeEqualTo(head.getPeriodCode()).andPeriodsEqualTo(head.getPeriods() - 1);
        List<AnodeGoodsInProcessStatisticHead> lastHeads = headMapper.selectByExample(example);
        AnodeGoodsInProcessStatisticHead lastHead = null;
        if (lastHeads.size() != 0)
            lastHead = lastHeads.get(0);

        BasicInfoAnodeCalculateCoefficient rate = coefficientMapper.selectByPrimaryKey(new Integer(1).byteValue());
        Float precursor = rate.getMatchingCoefficientPrecursors();
        Float lith = rate.getMatchingCoefficientLithiumCarbonate();
        Float xy[] = new Float[2];
        xy[0] = precursor / (precursor + lith); //前驱体配比系数[x/(x+y)]
        xy[1] = lith / (precursor + lith);//碳酸锂配比系数[y/(x+y)]
        Float bWeight = rate.getBowlFillWeight(); //装钵重量
        Float bNum = rate.getBowlNum().floatValue(); //每排钵数
        Float burnRate = rate.getBurningLossRate(); //烧损系数
        Float burnAnodeWeight = rate.getPresinteringWeight(); //预烧正压输送每罐重量
        Float smashAnodeWeight = rate.getSmashWeight(); //粉碎正压输每罐重量
        Float secondBurn = rate.getSecondSinteringWeight(); //二烧正压输送每罐重量
        Float highMix = rate.getHighMixingMachineWeight(); //高混机每批进料量
        Float bags = rate.getMatchingCoefficientHopPocket(); //预混配比系数布袋料
        Float lastValue = 0f;

        AnodeProcess premixCol = data.getProcesses().get(1);

        {//在线，预混（犁刀混），预混（暂存仓）
            AnodeProcess online = data.getProcesses().get(0);
            AnodeProcess col = data.getProcesses().get(1);
            AnodeProcess base = data.getProcesses().get(2);
            Float totalColMix = 0f;
            Float totalColCon = 0f;
            Float totalColBal = 0f;

            Float totalOnlFee = 0f;
            Float totalOnlCon = 0f;
            Float totalOnlBal = 0f;

            Float totalBasFee = 0f;
            Float totalBasCon = 0f;
            Float totalBasBal = 0f;

            List<Float> mixed = new ArrayList<>();

            Float colEachMix = precursor + lith;
            for (int i = 0; i < online.getMaterials().size(); i++) {
                AnodeMaterial info = online.getMaterials().get(i);
                if (!info.getFlag()) {
                    colEachMix += info.getValue();
                    AnodeGoodsInProcessStatisticSingleMaterialWeights weights = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                    weights.setLineCode(head.getLineCode());
                    weights.setStatisticCode(id);
                    weights.setMaterialCode(info.getCode());
                    weights.setTypeCode(head.getTypeCode());
                    weights.setProcessCode(1);
                    weights.setWeights(info.getValue());
                    singleMaterialWeightsMapper.insertSelective(weights);
                }
            }

            for (int i = 0; i < col.getMaterials().size(); i++) {
                AnodeMaterial info = col.getMaterials().get(i);
                if (info.getFlag()) {
                    AnodeGoodsInProcessStatisticPremixColter mat = new AnodeGoodsInProcessStatisticPremixColter();
                    Float mix = info.getValue() * colEachMix;
                    mixed.add(mix);//用于预混暂存仓
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(2);
                    mat.setConsume(mix);
                    mat.setBalance(info.getBalance());
                    mat.setFeedstock(0f);
                    premixColterMapper.insertSelective(mat);
                    totalColBal += mat.getBalance();
                    totalColCon += mat.getConsume();
                    totalColMix += info.getMix();
                }
            }
            AnodeGoodsInProcessStatisticByProcessTotals totals = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals.setStatisticCode(id);
            totals.setLineCode(head.getLineCode());
            totals.setProcessCode(2);
            totals.setPeriods(head.getPeriods());
            totals.setTypeCode(head.getTypeCode());
            totals.setBalanceTotals(totalColBal);
            totals.setFeedstockTotals(totalColMix);
            totals.setConsumeTotals(totalColCon);
            totals.setFlag(false);

            for (int i = 0; i < online.getMaterials().size(); i++) {
                AnodeMaterial info = online.getMaterials().get(i);
                if (info.getFlag()) {
                    AnodeGoodsInProcessStatisticOnlineRawMaterial mat = new AnodeGoodsInProcessStatisticOnlineRawMaterial();
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(1);
                    mat.setConsume((totalColMix + totalColBal) * xy[i]);
                    mat.setFeedstock(info.getReceive());
                    mat.setBalance(mat.getFeedstock() - mat.getConsume());
                    onlineRawMaterialMapper.insertSelective(mat);
                    totalOnlBal += mat.getBalance();
                    totalOnlCon += mat.getConsume();
                    totalOnlFee += mat.getFeedstock();
                }
            }
            AnodeGoodsInProcessStatisticByProcessTotals totals1 = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals1.setStatisticCode(id);
            totals1.setLineCode(head.getLineCode());
            totals1.setProcessCode(1);
            totals1.setPeriods(head.getPeriods());
            totals1.setTypeCode(head.getTypeCode());
            totals1.setBalanceTotals(totalOnlBal);
            totals1.setFeedstockTotals(totalOnlFee);
            totals1.setConsumeTotals(totalOnlCon);
            totals1.setFlag(true);
            processTotalsMapper.insertSelective(totals1);
            processTotalsMapper.insertSelective(totals);

            for (int i = 0; i < base.getMaterials().size(); i++) {
                AnodeMaterial info = base.getMaterials().get(i);
                if (info.getFlag()) {
                    AnodeGoodsInProcessStatisticPremixTemporaryStorage mat = new AnodeGoodsInProcessStatisticPremixTemporaryStorage();
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(3);
                    if (lastHead != null) {
                        AnodeGoodsInProcessStatisticPremixTemporaryStorageExample storageExample = new AnodeGoodsInProcessStatisticPremixTemporaryStorageExample();
                        storageExample.createCriteria().andStatisticCodeEqualTo(lastHead.getCode()).andMaterialCodeEqualTo(info.getCode());
                        lastValue = premixTemporaryStorageMapper.selectByExample(storageExample).get(0).getBalance();
                    }
                    mat.setFeedstock(mixed.get(i));
                    mat.setBalance(info.getBalance());
                    mat.setConsume(lastValue + mat.getFeedstock() - mat.getBalance());
                    premixTemporaryStorageMapper.insertSelective(mat);
                    totalBasBal += mat.getBalance();
                    totalBasCon += mat.getConsume();
                    totalBasFee += mat.getFeedstock();
                }
            }
            AnodeGoodsInProcessStatisticByProcessTotals totals2 = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals2.setStatisticCode(id);
            totals2.setLineCode(head.getLineCode());
            totals2.setProcessCode(3);
            totals2.setPeriods(head.getPeriods());
            totals2.setTypeCode(head.getTypeCode());
            totals2.setBalanceTotals(totalBasBal);
            totals2.setFeedstockTotals(totalBasFee);
            totals2.setConsumeTotals(totalBasCon);
            totals2.setFlag(false);
            processTotalsMapper.insertSelective(totals2);
        }

        {//预烧
            AnodeProcess preBurn = data.getProcesses().get(3);
            Float preBurnBalance = 0f;
            Float preBurnConsumer = 0f;
            Float preBurnReceive = 0f;
            Float lastIntoFurnace = 0f;

            for (int i = 0; i < preBurn.getMaterials().size(); i++) {
                AnodeMaterial info = preBurn.getMaterials().get(i);
                if (info.getFlag()) {
                    AnodeGoodsInProcessStatisticPresintering mat = new AnodeGoodsInProcessStatisticPresintering();
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(4);
                    mat.setIntoFurnaceNum(info.getIntoFurnace().intValue());
                    mat.setOutFurnaceNum(info.getOutFurnace().intValue());
                    mat.setBalance(info.getBalance());
                    /**
                     * 进料量=入炉排数*每排钵数*装钵重量*烧损系数
                     * 消耗量=出炉排数*每排钵数*装钵重量*烧损系数
                     * 结存量=(上期入炉排数+本期入炉排数-本期出炉排数)*每排钵数*装钵重量
                     */
                    if (lastHead != null) {
                        AnodeGoodsInProcessStatisticPresinteringExample presinteringExample = new AnodeGoodsInProcessStatisticPresinteringExample();
                        presinteringExample.createCriteria().andStatisticCodeEqualTo(lastHead.getCode()).andMaterialCodeEqualTo(info.getCode());
                        lastIntoFurnace = presinteringMapper.selectByExample(presinteringExample).get(0).getIntoFurnaceNum().floatValue();
                    }
                    Float receive = mat.getIntoFurnaceNum() * bNum * bWeight * burnRate;
                    Float consum = mat.getOutFurnaceNum() * bNum * bWeight * burnRate;
                    Float balance = (lastIntoFurnace + mat.getIntoFurnaceNum() - mat.getOutFurnaceNum()) * bNum * bWeight;
                    preBurnBalance += balance;
                    preBurnConsumer += consum;
                    preBurnReceive += receive;
                    presinteringMapper.insertSelective(mat);
                } else {
                    preBurnBalance += info.getValue();
                    AnodeGoodsInProcessStatisticSingleMaterialWeights weights = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                    weights.setLineCode(head.getLineCode());
                    weights.setStatisticCode(id);
                    weights.setMaterialCode(info.getCode());
                    weights.setTypeCode(head.getTypeCode());
                    weights.setProcessCode(4);
                    weights.setWeights(info.getValue());
                    singleMaterialWeightsMapper.insertSelective(weights);
                }
            }
            AnodeGoodsInProcessStatisticByProcessTotals totals = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals.setStatisticCode(id);
            totals.setLineCode(head.getLineCode());
            totals.setProcessCode(4);
            totals.setPeriods(head.getPeriods());
            totals.setTypeCode(head.getTypeCode());
            totals.setBalanceTotals(preBurnBalance);
            totals.setFeedstockTotals(preBurnReceive);
            totals.setConsumeTotals(preBurnConsumer);
            totals.setFlag(false);
            processTotalsMapper.insertSelective(totals);
        }

        {//粉碎
            AnodeProcess smash = data.getProcesses().get(4);
            Float totalBalance = 0f;
            Float totalReceive = 0f;
            Float totalConsum = 0f;
            Float lastBalance = 0f;

            AnodeGoodsInProcessStatisticSmash mat1 = new AnodeGoodsInProcessStatisticSmash();
            Float reveive = 0f;
            for (int i = 0; i < smash.getMaterials().size(); i++) {
                AnodeMaterial info = smash.getMaterials().get(i);
                if (!info.getFlag()) {
                    reveive += info.getValue();
                    AnodeGoodsInProcessStatisticSingleMaterialCounts counts = new AnodeGoodsInProcessStatisticSingleMaterialCounts();
                    counts.setLineCode(head.getLineCode());
                    counts.setStatisticCode(id);
                    counts.setMaterialCode(info.getCode());
                    counts.setTypeCode(head.getTypeCode());
                    counts.setProcessCode(5);
                    counts.setCounts(info.getValue().intValue());
                    singleMaterialCountsMapper.insertSelective(counts);
                }
            }
            mat1.setStatisticCode(id);
            mat1.setLineCode(head.getLineCode());
            mat1.setTypeCode(head.getTypeCode());
            mat1.setMaterialCode(smash.getMaterials().get(0).getCode());//1#5方固气分离仓
            mat1.setProcessCode(5);
            if (lastHead != null) {
                AnodeGoodsInProcessStatisticSmashExample example1 = new AnodeGoodsInProcessStatisticSmashExample();
                example1.createCriteria().andStatisticCodeEqualTo(lastHead.getCode()).andMaterialCodeEqualTo(smash.getMaterials().get(0).getCode());
                lastBalance = smashMapper.selectByExample(example1).get(0).getBalance();
            }
            mat1.setBalance(smash.getMaterials().get(0).getBalance());
            mat1.setFeedstock(reveive);
            mat1.setConsume(lastBalance + smash.getMaterials().get(0).getReceive() - smash.getMaterials().get(0).getBalance());
            smashMapper.insertSelective(mat1);

            totalReceive = mat1.getFeedstock();
            totalConsum = mat1.getConsume();

            for (int i = 1; i < smash.getMaterials().size(); i++) {
                AnodeGoodsInProcessStatisticSmash mat = new AnodeGoodsInProcessStatisticSmash();
                AnodeMaterial info = smash.getMaterials().get(i);
                if (info.getFlag()) {
                    totalBalance += info.getBalance();
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(5);
                    mat.setBalance(info.getBalance());
                    smashMapper.insertSelective(mat);
                }
            }
            AnodeGoodsInProcessStatisticByProcessTotals totals = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals.setStatisticCode(id);
            totals.setLineCode(head.getLineCode());
            totals.setProcessCode(5);
            totals.setPeriods(head.getPeriods());
            totals.setTypeCode(head.getTypeCode());
            totals.setBalanceTotals(totalBalance);
            totals.setFeedstockTotals(totalReceive);
            totals.setConsumeTotals(totalConsum);
            totals.setFlag(false);
            processTotalsMapper.insertSelective(totals);
        }

        {//二混
            AnodeProcess secondMix = data.getProcesses().get(5);
            Float totalBalance = 0f;
            Float totalReceive = 0f;
            Float totalConsum = 0f;

            AnodeMaterial cal1 = new AnodeMaterial();//1#二混固气分离仓(二方仓)
            AnodeMaterial cal2 = new AnodeMaterial();//800lL
            AnodeMaterial cal3 = new AnodeMaterial();//1#高混机
            AnodeMaterial cal4 = new AnodeMaterial();//1#二混4方暂存仓
            AnodeMaterial cal5 = new AnodeMaterial();//1#粉碎正压输送次数
            AnodeMaterial cal6 = new AnodeMaterial();//2#粉碎正压输送次数
            AnodeMaterial cal7 = new AnodeMaterial();//混料次数
            for (int i = 0; i < secondMix.getMaterials().size(); i++) {
                if (secondMix.getMaterials().get(i).getCode() == 29)
                    cal1 = secondMix.getMaterials().get(i);
                if (secondMix.getMaterials().get(i).getCode() == 30)
                    cal2 = secondMix.getMaterials().get(i);
                if (secondMix.getMaterials().get(i).getCode() == 31)
                    cal3 = secondMix.getMaterials().get(i);
                if (secondMix.getMaterials().get(i).getCode() == 32)
                    cal4 = secondMix.getMaterials().get(i);
                if (secondMix.getMaterials().get(i).getCode() == 33)
                    cal5 = secondMix.getMaterials().get(i);
                if (secondMix.getMaterials().get(i).getCode() == 34)
                    cal6 = secondMix.getMaterials().get(i);
                if (secondMix.getMaterials().get(i).getCode() == 35)
                    cal7 = secondMix.getMaterials().get(i);
            }
            cal1.setReceive(cal5.getValue() + cal6.getValue() * smashAnodeWeight);
            cal3.setReceive(cal7.getValue() * highMix);
            cal1.setConsum(cal3.getReceive() + cal3.getBalance());
            cal3.setConsum(cal3.getReceive());

            AnodeGoodsInProcessStatisticSecondMix mat = new AnodeGoodsInProcessStatisticSecondMix();
            mat.setTypeCode(head.getTypeCode());
            mat.setMaterialCode(cal1.getCode());
            mat.setLineCode(head.getLineCode());
            mat.setProcessCode(6);
            mat.setStatisticCode(id);
            mat.setFeedstock(cal1.getReceive());
            mat.setBalance(cal1.getBalance());
            mat.setConsume(cal1.getConsum());
            secondMixMapper.insertSelective(mat);

            mat = new AnodeGoodsInProcessStatisticSecondMix();
            mat.setTypeCode(head.getTypeCode());
            mat.setMaterialCode(cal3.getCode());
            mat.setLineCode(head.getLineCode());
            mat.setProcessCode(6);
            mat.setStatisticCode(id);
            mat.setFeedstock(cal3.getReceive());
            mat.setBalance(cal3.getBalance());
            mat.setConsume(cal3.getConsum());
            secondMixMapper.insertSelective(mat);

            mat = new AnodeGoodsInProcessStatisticSecondMix();
            mat.setTypeCode(head.getTypeCode());
            mat.setMaterialCode(cal2.getCode());
            mat.setLineCode(head.getLineCode());
            mat.setProcessCode(6);
            mat.setStatisticCode(id);
            mat.setBalance(cal2.getBalance());
            secondMixMapper.insertSelective(mat);

            mat = new AnodeGoodsInProcessStatisticSecondMix();
            mat.setTypeCode(head.getTypeCode());
            mat.setMaterialCode(cal4.getCode());
            mat.setLineCode(head.getLineCode());
            mat.setProcessCode(6);
            mat.setStatisticCode(id);
            mat.setBalance(cal4.getBalance());
            secondMixMapper.insertSelective(mat);

            for (int i = 0; i < secondMix.getMaterials().size(); i++) {
                List<AnodeMaterial> infos = secondMix.getMaterials();
                if (!infos.get(i).getFlag()) {
                    AnodeGoodsInProcessStatisticSingleMaterialCounts counts = new AnodeGoodsInProcessStatisticSingleMaterialCounts();
                    counts.setTypeCode(head.getTypeCode());
                    counts.setMaterialCode(infos.get(i).getCode());
                    counts.setLineCode(head.getLineCode());
                    counts.setProcessCode(6);
                    counts.setStatisticCode(id);
                    counts.setCounts(infos.get(i).getValue().intValue());
                    singleMaterialCountsMapper.insertSelective(counts);
                }
            }

            totalBalance += (cal1.getBalance() + cal2.getBalance() + cal3.getBalance() + cal4.getBalance());
            totalConsum += cal1.getConsum();
            totalReceive += cal1.getReceive();
            AnodeGoodsInProcessStatisticByProcessTotals totals = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals.setStatisticCode(id);
            totals.setLineCode(head.getLineCode());
            totals.setProcessCode(6);
            totals.setPeriods(head.getPeriods());
            totals.setTypeCode(head.getTypeCode());
            totals.setBalanceTotals(totalBalance);
            totals.setFeedstockTotals(totalReceive);
            totals.setConsumeTotals(totalConsum);
            totals.setFlag(false);
            processTotalsMapper.insertSelective(totals);
        }

        {//二烧
            AnodeProcess secondSin = data.getProcesses().get(6);

            Float totalBalance = 0f;
            Float totalReceive = 0f;
            Float totalConsum = 0f;
            Float lastIntoFurnace = 0f;


            for (int i = 0; i < secondSin.getMaterials().size(); i++) {
                AnodeMaterial info = secondSin.getMaterials().get(i);
                if (info.getFlag()) {
                    AnodeGoodsInProcessStatisticSecondSintering mat = new AnodeGoodsInProcessStatisticSecondSintering();
                    mat.setStatisticCode(id);
                    mat.setLineCode(head.getLineCode());
                    mat.setTypeCode(head.getTypeCode());
                    mat.setMaterialCode(info.getCode());
                    mat.setProcessCode(7);
                    mat.setIntoFurnaceNum(info.getIntoFurnace());
                    mat.setOutFurnaceNum(info.getOutFurnace());
                    if (lastHead != null) {
                        AnodeGoodsInProcessStatisticSecondSinteringExample sinteringExample = new AnodeGoodsInProcessStatisticSecondSinteringExample();
                        sinteringExample.createCriteria().andStatisticCodeEqualTo(lastHead.getCode()).andMaterialCodeEqualTo(info.getCode());
                        lastIntoFurnace = secondSinteringMapper.selectByExample(sinteringExample).get(0).getIntoFurnaceNum().floatValue();
                    }
                    mat.setConsume(info.getOutFurnace() * bNum * bWeight);
                    mat.setFeedstock(info.getIntoFurnace() * bNum * bWeight);
                    mat.setBalance((lastIntoFurnace + info.getIntoFurnace() - info.getOutFurnace()) * bNum * bWeight);
                    secondSinteringMapper.insertSelective(mat);
                    totalBalance += mat.getBalance();
                    totalConsum += mat.getConsume();
                    totalReceive += mat.getFeedstock();
                }
                if (!info.getFlag()) {
                    totalBalance += info.getValue();
                    AnodeGoodsInProcessStatisticSingleMaterialWeights weights = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                    weights.setLineCode(head.getLineCode());
                    weights.setStatisticCode(id);
                    weights.setMaterialCode(info.getCode());
                    weights.setTypeCode(head.getTypeCode());
                    weights.setProcessCode(7);
                    weights.setWeights(info.getValue());
                    singleMaterialWeightsMapper.insertSelective(weights);
                }
            }
            AnodeGoodsInProcessStatisticByProcessTotals totals = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals.setStatisticCode(id);
            totals.setLineCode(head.getLineCode());
            totals.setProcessCode(7);
            totals.setPeriods(head.getPeriods());
            totals.setTypeCode(head.getTypeCode());
            totals.setBalanceTotals(totalBalance);
            totals.setFeedstockTotals(totalReceive);
            totals.setConsumeTotals(totalConsum);
            totals.setFlag(false);
            processTotalsMapper.insertSelective(totals);
        }

        {//包装
            AnodeProcess pkg = data.getProcesses().get(7);
            AnodeMaterial cal1 = new AnodeMaterial(); //1#6方固气分离仓
            AnodeMaterial s1 = new AnodeMaterial(); //1烧
            AnodeMaterial s2 = new AnodeMaterial(); //2烧
            AnodeMaterial sj = new AnodeMaterial(); //烧结
            AnodeMaterial cpdrk = new AnodeMaterial(); //产品待入库
            AnodeMaterial bzds = new AnodeMaterial(); //包装袋数
            AnodeMaterial ccp = new AnodeMaterial(); //产成品
            Integer pBag = 0;
            Float pWeight = 0f;
            Float pStoragy = 0f;

            Float totalBal = 0f;
            Float totalCon = 0f;
            Float totalFee = 0f;


            for (int i = 0; i < pkg.getMaterials().size(); i++) {
                AnodeMaterial info = pkg.getMaterials().get(i);
                if (info.getCode() == 39) {
                    cal1 = info;
                }
                if (info.getCode() == 47) {
                    sj = info;
                }
                if (info.getCode() == 48) {
                    s1 = info;
                }
                if (info.getCode() == 49) {
                    s2 = info;
                }
                if (info.getCode() == 50) {
                    cpdrk = info;
                }
                if(info.getCode() == 45){
                    bzds = info;
                }
                if(info.getCode() == 46){
                    ccp = info;
                }
                if (info.getCode() == 48 || info.getCode() == 49) {
                    AnodeGoodsInProcessStatisticSingleMaterialCounts counts = new AnodeGoodsInProcessStatisticSingleMaterialCounts();
                    counts.setTypeCode(head.getTypeCode());
                    counts.setMaterialCode(info.getCode());
                    counts.setLineCode(head.getLineCode());
                    counts.setProcessCode(8);
                    counts.setStatisticCode(id);
                    counts.setCounts(info.getValue().intValue());
                    singleMaterialCountsMapper.insertSelective(counts);
                }
                if (info.getCode() == 47 || info.getCode() == 50) {
                    AnodeGoodsInProcessStatisticSingleMaterialWeights weights = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                    weights.setLineCode(head.getLineCode());
                    weights.setStatisticCode(id);
                    weights.setMaterialCode(info.getCode());
                    weights.setTypeCode(head.getTypeCode());
                    weights.setProcessCode(8);
                    weights.setWeights(info.getValue());
                    singleMaterialWeightsMapper.insertSelective(weights);
                }
            }

            AnodeGoodsInProcessStatisticPackage mat = new AnodeGoodsInProcessStatisticPackage();
            mat.setStatisticCode(id);
            mat.setLineCode(head.getLineCode());
            mat.setTypeCode(head.getTypeCode());
            mat.setMaterialCode(cal1.getCode());
            mat.setProcessCode(8);
            mat.setBalance(cal1.getBalance());
            mat.setFeedstock((s1.getValue() + s2.getValue()) * secondBurn);
            mat.setConsume(ccp.getValue() + sj.getValue() + cpdrk.getValue());
            pWeight = ccp.getValue();
            packageMapper.insertSelective(mat);
            totalCon += mat.getConsume();
            totalBal += mat.getBalance();
            totalFee += mat.getFeedstock();

            for (int i = 1; i < pkg.getMaterials().size(); i++) {
                AnodeMaterial info = pkg.getMaterials().get(i);
                if (info.getFlag()) {
                    AnodeGoodsInProcessStatisticPackage mat1 = new AnodeGoodsInProcessStatisticPackage();
                    mat1.setStatisticCode(id);
                    mat1.setLineCode(head.getLineCode());
                    mat1.setTypeCode(head.getTypeCode());
                    mat1.setMaterialCode(info.getCode());
                    mat1.setProcessCode(8);
                    mat1.setBalance(info.getBalance());
                    mat1.setConsume(info.getConsum());
                    mat1.setFeedstock(info.getReceive());
                    packageMapper.insertSelective(mat1);
                    totalBal += mat1.getBalance();
                    totalCon += mat1.getConsume();
                    totalFee += mat1.getFeedstock();
                }
            }

            //List<AnodeMaterial> others = pkg.getOthers();
            pStoragy = ccp.getValue();
            AnodeGoodsInProcessStatisticLineProcessProductions pp = new AnodeGoodsInProcessStatisticLineProcessProductions();
            pp.setStatisticCode(id);
            pp.setLineCode(head.getLineCode());
            pp.setTypeCode(head.getTypeCode());
            pp.setProcessCode(8);
            pp.setBagCounts(bzds.getValue().intValue());
            pp.setProductionStorage(pStoragy);
            pp.setProductionWeights(pWeight);
            lineProcessProductionsMapper.insertSelective(pp);

            AnodeGoodsInProcessStatisticByProcessTotals totals = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals.setStatisticCode(id);
            totals.setLineCode(head.getLineCode());
            totals.setProcessCode(8);
            totals.setPeriods(head.getPeriods());
            totals.setTypeCode(head.getTypeCode());
            totals.setBalanceTotals(totalBal);
            totals.setFeedstockTotals(totalFee);
            totals.setConsumeTotals(totalCon);
            totals.setFlag(false);
            processTotalsMapper.insertSelective(totals);
        }

        {//车间待处理
            AnodeProcess plant = data.getProcesses().get(8);

            Float totalBalance = 0f;
            Float totalReceive = 0f;
            Float totalConsum = 0f;

            for (int i = 0; i < plant.getMaterials().size(); i++) {
                AnodeMaterial info = plant.getMaterials().get(i);
                if (!info.getFlag()) {
                    totalBalance += info.getValue();
                    AnodeGoodsInProcessStatisticSingleMaterialWeights weights = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                    weights.setLineCode(head.getLineCode());
                    weights.setStatisticCode(id);
                    weights.setMaterialCode(info.getCode());
                    weights.setTypeCode(head.getTypeCode());
                    weights.setProcessCode(9);
                    weights.setWeights(info.getValue());
                    singleMaterialWeightsMapper.insertSelective(weights);
                }
            }
            AnodeGoodsInProcessStatisticByProcessTotals totals = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals.setStatisticCode(id);
            totals.setLineCode(head.getLineCode());
            totals.setProcessCode(9);
            totals.setPeriods(head.getPeriods());
            totals.setTypeCode(head.getTypeCode());
            totals.setBalanceTotals(totalBalance);
            totals.setFeedstockTotals(totalReceive);
            totals.setConsumeTotals(totalConsum);
            totals.setFlag(false);
            processTotalsMapper.insertSelective(totals);
        }

        {//仓库待处理
            AnodeProcess base = data.getProcesses().get(9);

            Float totalBalance = 0f;
            Float totalReceive = 0f;
            Float totalConsum = 0f;

            for (int i = 0; i < base.getMaterials().size(); i++) {
                AnodeMaterial info = base.getMaterials().get(i);
                if (!info.getFlag()) {
                    totalBalance += info.getValue();
                    AnodeGoodsInProcessStatisticSingleMaterialWeights weights = new AnodeGoodsInProcessStatisticSingleMaterialWeights();
                    weights.setLineCode(head.getLineCode());
                    weights.setStatisticCode(id);
                    weights.setMaterialCode(info.getCode());
                    weights.setTypeCode(head.getTypeCode());
                    weights.setProcessCode(10);
                    weights.setWeights(info.getValue());
                    singleMaterialWeightsMapper.insertSelective(weights);
                }
            }
            AnodeGoodsInProcessStatisticByProcessTotals totals = new AnodeGoodsInProcessStatisticByProcessTotals();
            totals.setStatisticCode(id);
            totals.setLineCode(head.getLineCode());
            totals.setProcessCode(10);
            totals.setPeriods(head.getPeriods());
            totals.setTypeCode(head.getTypeCode());
            totals.setBalanceTotals(totalBalance);
            totals.setFeedstockTotals(totalReceive);
            totals.setConsumeTotals(totalConsum);
            totals.setFlag(false);
            processTotalsMapper.insertSelective(totals);
        }

        head.setFlag(true);
        headMapper.updateByPrimaryKey(head);

    }

    private void deleteCascade(Long id) {

        AnodeGoodsInProcessStatisticOnlineRawMaterialExample example = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
        example.createCriteria().andStatisticCodeEqualTo(id);
        onlineRawMaterialMapper.deleteByExample(example);

        AnodeGoodsInProcessStatisticPresinteringExample example1 = new AnodeGoodsInProcessStatisticPresinteringExample();
        example1.createCriteria().andStatisticCodeEqualTo(id);
        presinteringMapper.deleteByExample(example1);

        AnodeGoodsInProcessStatisticPremixColterExample example2 = new AnodeGoodsInProcessStatisticPremixColterExample();
        example2.createCriteria().andStatisticCodeEqualTo(id);
        premixColterMapper.deleteByExample(example2);

        AnodeGoodsInProcessStatisticPremixTemporaryStorageExample example3 = new AnodeGoodsInProcessStatisticPremixTemporaryStorageExample();
        example3.createCriteria().andStatisticCodeEqualTo(id);
        premixTemporaryStorageMapper.deleteByExample(example3);

        AnodeGoodsInProcessStatisticSmashExample example4 = new AnodeGoodsInProcessStatisticSmashExample();
        example4.createCriteria().andStatisticCodeEqualTo(id);
        smashMapper.deleteByExample(example4);

        AnodeGoodsInProcessStatisticSecondMixExample example5 = new AnodeGoodsInProcessStatisticSecondMixExample();
        example5.createCriteria().andStatisticCodeEqualTo(id);
        secondMixMapper.deleteByExample(example5);

        AnodeGoodsInProcessStatisticSecondSinteringExample example6 = new AnodeGoodsInProcessStatisticSecondSinteringExample();
        example6.createCriteria().andStatisticCodeEqualTo(id);
        secondSinteringMapper.deleteByExample(example6);

        AnodeGoodsInProcessStatisticPackageExample example7 = new AnodeGoodsInProcessStatisticPackageExample();
        example7.createCriteria().andStatisticCodeEqualTo(id);
        packageMapper.deleteByExample(example7);

        AnodeGoodsInProcessStatisticPlantPendingExample example8 = new AnodeGoodsInProcessStatisticPlantPendingExample();
        example8.createCriteria().andStatisticCodeEqualTo(id);
        plantPendingMapper.deleteByExample(example8);

        AnodeGoodsInProcessStatisticWarehousePendingExample example9 = new AnodeGoodsInProcessStatisticWarehousePendingExample();
        example9.createCriteria().andStatisticCodeEqualTo(id);
        warehousePendingMapper.deleteByExample(example9);

        AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example10 = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
        example10.createCriteria().andStatisticCodeEqualTo(id);
        singleMaterialWeightsMapper.deleteByExample(example10);

        AnodeGoodsInProcessStatisticSingleMaterialCountsExample example11 = new AnodeGoodsInProcessStatisticSingleMaterialCountsExample();
        example11.createCriteria().andStatisticCodeEqualTo(id);
        singleMaterialCountsMapper.deleteByExample(example11);

        AnodeGoodsInProcessStatisticByProcessTotalsExample example12 = new AnodeGoodsInProcessStatisticByProcessTotalsExample();
        example12.createCriteria().andStatisticCodeEqualTo(id);
        processTotalsMapper.deleteByExample(example12);

        AnodeGoodsInProcessStatisticLineProcessProductionsExample example13 = new AnodeGoodsInProcessStatisticLineProcessProductionsExample();
        example13.createCriteria().andStatisticCodeEqualTo(id);
        lineProcessProductionsMapper.deleteByExample(example13);

    }

    @Override
    public void delete(Long id) {
        deleteCascade(id);
        headMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page pageUnCommit(AnodeGoodsInProcessStatisticHead head, Integer page, Integer size) {
        Integer periodId = head.getPeriodCode();
        Integer lineId = head.getLineCode();
        Date start = head.getBeginTime();
        Date end = head.getEndTime();

        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();

        if (periodId == null) {
            example.createCriteria().andPeriodCodeEqualTo(periodMapper.selectByExample(new BasicInfoAnodePeriodExample()).get(0).getCode()).andFlagEqualTo(false);
        } else {
            if (start == null) {
                if (lineId == null) {
                    example.createCriteria().andPeriodCodeEqualTo(periodId).andFlagEqualTo(false);
                } else {
                    example.createCriteria().andPeriodCodeEqualTo(periodId).andLineCodeEqualTo(lineId).andFlagEqualTo(false);
                }
            } else {
                if (lineId == null) {
                    example.createCriteria().andPeriodCodeEqualTo(periodId).andFlagEqualTo(false).andBeginTimeBetween(start, end);
                } else {
                    example.createCriteria().andPeriodCodeEqualTo(periodId).andLineCodeEqualTo(lineId).andFlagEqualTo(false).andBeginTimeBetween(start, end);
                }
            }
        }
        example.setOrderByClause("begin_time desc");
        List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(example);

        List<Map<String, Object>> ans = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("head", heads.get(i));
            map.put("periodName", periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
            map.put("lineNama", lineMapper.selectByPrimaryKey(heads.get(i).getLineCode()).getName());
            ans.add(map);
        }
        return new Page(ans, page, size);
    }

    @Override
    public Page pageCommit(AnodeGoodsInProcessStatisticHead head, Integer page, Integer size) {
        Integer periodCode = head.getPeriodCode();
        Integer lineCode = head.getLineCode();
        Date start = head.getBeginTime();
        Date end = head.getEndTime();

        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();
        if (start == null) {
            example.createCriteria().andFlagEqualTo(true).andPeriodCodeEqualTo(periodCode).andLineCodeEqualTo(lineCode);
        } else {
            example.createCriteria().andFlagEqualTo(true).andPeriodCodeEqualTo(periodCode).andLineCodeEqualTo(lineCode).andBeginTimeBetween(start, end);
        }
        example.setOrderByClause("begin_time desc");

        List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(example);

        List<AnodeStatisticDTO> ans = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            AnodeGoodsInProcessStatisticByProcessTotalsExample example1 = new AnodeGoodsInProcessStatisticByProcessTotalsExample();
            example1.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
            List<AnodeGoodsInProcessStatisticByProcessTotals> totals = processTotalsMapper.selectByExample(example1);
            for (int l = 0; l < totals.size(); l++) {
                AnodeStatisticDTO info = new AnodeStatisticDTO();
                info.setHead(heads.get(i));
                info.setLineName(lineMapper.selectByPrimaryKey(heads.get(i).getLineCode()).getName());
                info.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
                info.setTypeName(typeMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
                info.setProcessName(processNameMapper.selectByPrimaryKey(totals.get(l).getProcessCode()).getProcessName());
                info.setTotals(totals.get(l));
                if (totals.get(l).getFlag()) {
                    AnodeGoodsInProcessStatisticOnlineRawMaterialExample example2 = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
                    example2.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
                    info.setComment(onlineRawMaterialMapper.selectByExample(example2));
                }
                ans.add(info);
            }
        }
        return new Page(ans, page, size);
    }

    @Override
    public AnodeStatisticDTO commitDetail(Long totalId) {
        AnodeStatisticDTO ans = new AnodeStatisticDTO();

        AnodeGoodsInProcessStatisticByProcessTotals totals = processTotalsMapper.selectByPrimaryKey(totalId);
        Long id = totals.getStatisticCode();

        ans.setHead(headMapper.selectByPrimaryKey(id));
        ans.setProcessName(processNameMapper.selectByPrimaryKey(totals.getProcessCode()).getProcessName());
        ans.setLineName(lineMapper.selectByPrimaryKey(ans.getHead().getLineCode()).getName());
        ans.setPeriodName(periodMapper.selectByPrimaryKey(ans.getHead().getPeriodCode()).getName());
        ans.setTypeName(typeMapper.selectByPrimaryKey(ans.getHead().getTypeCode()).getName());

        if (totals.getProcessCode() == 1) {//在线
            AnodeGoodsInProcessStatisticOnlineRawMaterialExample example = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
            example.createCriteria().andStatisticCodeEqualTo(id);
            AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example1 = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
            example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(1);
            List<AnodeGoodsInProcessStatisticOnlineRawMaterial> mats = onlineRawMaterialMapper.selectByExample(example);
            List<AnodeGoodsInProcessStatisticSingleMaterialWeights> weights = singleMaterialWeightsMapper.selectByExample(example1);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticOnlineRawMaterial mat = mats.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(mats.get(i).getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (info.getFlag()) {
                    info.setReceive(mat.getFeedstock());
                    info.setConsum(mat.getConsume());
                    info.setBalance(mat.getBalance());
                    infos.add(info);
                }
            }
            for (int i = 0; i < weights.size(); i++) {
                AnodeGoodsInProcessStatisticSingleMaterialWeights weight = weights.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(weight.getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (!info.getFlag()) {
                    info.setValue(weight.getWeights());
                    infos.add(info);
                }
            }
            ans.setMats(infos);
        }
        if (totals.getProcessCode() == 2) {//预混 犁刀混
            Float tMix = 0f, tCon = 0f, tBal = 0f, tFee = 0f;
            AnodeGoodsInProcessStatisticPremixColterExample example = new AnodeGoodsInProcessStatisticPremixColterExample();
            example.createCriteria().andStatisticCodeEqualTo(id);
            List<AnodeGoodsInProcessStatisticPremixColter> mats = premixColterMapper.selectByExample(example);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticPremixColter mat = mats.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(mats.get(i).getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                info.setConsum(mat.getConsume());
                info.setBalance(mat.getBalance());
                info.setMix(mat.getConsume());
                infos.add(info);
                if (mat.getFeedstock() != null) {
                    tFee += mat.getFeedstock();
                }
                if (mat.getConsume() != null) {
                    tCon += mat.getConsume();
                }
                tBal += mat.getBalance();
            }
            ans.setMats(infos);
            ans.settCom(tCon);
            ans.settBal(tBal);
            ans.settMix(tMix);
        }
        if (totals.getProcessCode() == 3) {//预混 暂存仓
            Float tMix = 0f, tCon = 0f, tBal = 0f, tFee = 0f;
            AnodeGoodsInProcessStatisticPremixTemporaryStorageExample example = new AnodeGoodsInProcessStatisticPremixTemporaryStorageExample();
            example.createCriteria().andStatisticCodeEqualTo(id);
            List<AnodeGoodsInProcessStatisticPremixTemporaryStorage> mats = premixTemporaryStorageMapper.selectByExample(example);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticPremixTemporaryStorage mat = mats.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(mats.get(i).getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                info.setReceive(mat.getFeedstock());
                info.setConsum(mat.getConsume());
                info.setBalance(mat.getBalance());
                infos.add(info);
                if (mat.getFeedstock() != null) {
                    tFee += mat.getFeedstock();
                }
                if (mat.getConsume() != null) {
                    tCon += mat.getConsume();
                }
                tBal += mat.getBalance();
            }
            ans.setMats(infos);
            ans.settCom(tCon);
            ans.settBal(tBal);
            ans.settFee(tFee);
        }
        if (totals.getProcessCode() == 4) {//预烧
            Float tMix = 0f, tCon = 0f, tBal = 0f, tFee = 0f;
            AnodeGoodsInProcessStatisticPresinteringExample example = new AnodeGoodsInProcessStatisticPresinteringExample();
            example.createCriteria().andStatisticCodeEqualTo(id);
            AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example1 = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
            example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(4);
            List<AnodeGoodsInProcessStatisticPresintering> mats = presinteringMapper.selectByExample(example);
            List<AnodeGoodsInProcessStatisticSingleMaterialWeights> weights = singleMaterialWeightsMapper.selectByExample(example1);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticPresintering mat = mats.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(mats.get(i).getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (info.getFlag()) {
                    info.setReceive(mat.getFeedstock());
                    info.setConsum(mat.getConsume());
                    info.setBalance(mat.getBalance());
                    info.setIntoFurnace(mat.getIntoFurnaceNum());
                    info.setOutFurnace(mat.getOutFurnaceNum());
                    if (mat.getFeedstock() != null) {
                        tFee += mat.getFeedstock();
                    }
                    if (mat.getConsume() != null) {
                        tCon += mat.getConsume();
                    }
                    tBal += mat.getBalance();
                }
            }
            for (int i = 0; i < weights.size(); i++) {
                AnodeGoodsInProcessStatisticSingleMaterialWeights weight = weights.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(weight.getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (!info.getFlag()) {
                    info.setValue(weight.getWeights());
                    infos.add(info);
                }
            }
            ans.setMats(infos);
            ans.settCom(tCon);
            ans.settBal(tBal);
            ans.settFee(tFee);
        }
        if (totals.getProcessCode() == 5) {//粉碎
            Float tMix = 0f, tCon = 0f, tBal = 0f, tFee = 0f;
            AnodeGoodsInProcessStatisticSmashExample example = new AnodeGoodsInProcessStatisticSmashExample();
            example.createCriteria().andStatisticCodeEqualTo(id);
            AnodeGoodsInProcessStatisticSingleMaterialCountsExample example1 = new AnodeGoodsInProcessStatisticSingleMaterialCountsExample();
            example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(5);
            List<AnodeGoodsInProcessStatisticSmash> mats = smashMapper.selectByExample(example);
            List<AnodeGoodsInProcessStatisticSingleMaterialCounts> counts = singleMaterialCountsMapper.selectByExample(example1);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticSmash mat = mats.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(mats.get(i).getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (info.getFlag()) {
                    info.setReceive(mat.getFeedstock());
                    info.setConsum(mat.getConsume());
                    info.setBalance(mat.getBalance());
                    infos.add(info);
                    if (mat.getFeedstock() != null) {
                        tFee += mat.getFeedstock();
                    }
                    if (mat.getConsume() != null) {
                        tCon += mat.getConsume();
                    }
                    tBal += mat.getBalance();
                }
            }
            for (int i = 0; i < counts.size(); i++) {
                AnodeGoodsInProcessStatisticSingleMaterialCounts count = counts.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(count.getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (!info.getFlag()) {
                    info.setValue(count.getCounts().floatValue());
                    infos.add(info);
                }
            }
            ans.setMats(infos);
            ans.settCom(tCon);
            ans.settBal(tBal);
            ans.settFee(tFee);
        }
        if (totals.getProcessCode() == 6) {//二混
            Float tMix = 0f, tCon = 0f, tBal = 0f, tFee = 0f;
            AnodeGoodsInProcessStatisticSecondMixExample example = new AnodeGoodsInProcessStatisticSecondMixExample();
            example.createCriteria().andStatisticCodeEqualTo(id);
            AnodeGoodsInProcessStatisticSingleMaterialCountsExample example1 = new AnodeGoodsInProcessStatisticSingleMaterialCountsExample();
            example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(6);
            List<AnodeGoodsInProcessStatisticSecondMix> mats = secondMixMapper.selectByExample(example);
            List<AnodeGoodsInProcessStatisticSingleMaterialCounts> counts = singleMaterialCountsMapper.selectByExample(example1);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticSecondMix mat = mats.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(mats.get(i).getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (info.getFlag()) {
                    info.setReceive(mat.getFeedstock());
                    info.setConsum(mat.getConsume());
                    info.setBalance(mat.getBalance());
                    infos.add(info);
                    if (mat.getConsume() != null) {
                        tCon += mat.getConsume();
                    }
                    if (mat.getFeedstock() != null) {
                        tFee += mat.getFeedstock();
                    }
                    tBal += mat.getBalance();

                }
            }
            for (int i = 0; i < counts.size(); i++) {
                AnodeGoodsInProcessStatisticSingleMaterialCounts count = counts.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(count.getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (!info.getFlag()) {
                    info.setValue(count.getCounts().floatValue());
                    infos.add(info);
                }
            }
            ans.setMats(infos);
            ans.settCom(tCon);
            ans.settBal(tBal);
            ans.settFee(tFee);
        }
        if (totals.getProcessCode() == 7) {//二烧
            Float tMix = 0f, tCon = 0f, tBal = 0f, tFee = 0f;
            Integer tIn = 0,tOut = 0;
            AnodeGoodsInProcessStatisticSecondSinteringExample example = new AnodeGoodsInProcessStatisticSecondSinteringExample();
            example.createCriteria().andStatisticCodeEqualTo(id);
            AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example1 = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
            example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(7);
            List<AnodeGoodsInProcessStatisticSecondSintering> mats = secondSinteringMapper.selectByExample(example);
            List<AnodeGoodsInProcessStatisticSingleMaterialWeights> weights = singleMaterialWeightsMapper.selectByExample(example1);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticSecondSintering mat = mats.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(mats.get(i).getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (info.getFlag()) {
                    info.setReceive(mat.getFeedstock());
                    info.setConsum(mat.getConsume());
                    info.setBalance(mat.getBalance());
                    info.setIntoFurnace(mat.getIntoFurnaceNum());
                    info.setOutFurnace(mat.getOutFurnaceNum());
                    tIn += mat.getIntoFurnaceNum();
                    tOut += mat.getOutFurnaceNum();
                    infos.add(info);
                    if (mat.getFeedstock() != null) {
                        tFee += mat.getFeedstock();
                    }
                    if (mat.getConsume() != null) {
                        tCon += mat.getConsume();
                    }
                    tBal += mat.getBalance();
                }
            }
            for (int i = 0; i < weights.size(); i++) {
                AnodeGoodsInProcessStatisticSingleMaterialWeights weight = weights.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(weight.getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (!info.getFlag()) {
                    info.setValue(weight.getWeights());
                    infos.add(info);
                }
            }
            ans.setMats(infos);
            ans.settCom(tCon);
            ans.settBal(tBal);
            ans.settFee(tFee);
            ans.settIn(tIn);
            ans.settOut(tOut);
        }
        if (totals.getProcessCode() == 8) {//包装
            Float tMix = 0f, tCon = 0f, tBal = 0f, tFee = 0f;
            AnodeGoodsInProcessStatisticPackageExample example = new AnodeGoodsInProcessStatisticPackageExample();
            example.createCriteria().andStatisticCodeEqualTo(id);
            AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example1 = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
            example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(8);
            AnodeGoodsInProcessStatisticSingleMaterialCountsExample example2 = new AnodeGoodsInProcessStatisticSingleMaterialCountsExample();
            example2.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(8);
            List<AnodeGoodsInProcessStatisticPackage> mats = packageMapper.selectByExample(example);
            List<AnodeGoodsInProcessStatisticSingleMaterialWeights> weights = singleMaterialWeightsMapper.selectByExample(example1);
            List<AnodeGoodsInProcessStatisticSingleMaterialCounts> counts = singleMaterialCountsMapper.selectByExample(example2);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < mats.size(); i++) {
                AnodeGoodsInProcessStatisticPackage mat = mats.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(mats.get(i).getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (info.getFlag()) {
                    info.setReceive(mat.getFeedstock());
                    info.setConsum(mat.getConsume());
                    info.setBalance(mat.getBalance());
                    infos.add(info);
                    if (mat.getFeedstock() != null) {
                        tFee += mat.getFeedstock();
                    }
                    if (mat.getConsume() != null) {
                        tCon += mat.getConsume();
                    }
                    tBal += mat.getBalance();
                }
            }
            for (int i = 0; i < weights.size(); i++) {
                AnodeGoodsInProcessStatisticSingleMaterialWeights weight = weights.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(weight.getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (!info.getFlag()) {
                    info.setValue(weight.getWeights());
                    infos.add(info);
                }
            }
            for (int i = 0; i < counts.size(); i++) {
                AnodeGoodsInProcessStatisticSingleMaterialCounts count = counts.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(count.getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (!info.getFlag()) {
                    info.setValue(count.getCounts().floatValue());
                    infos.add(info);
                }
            }
            ans.setMats(infos);
            ans.settCom(tCon);
            ans.settBal(tBal);
            ans.settFee(tFee);

            AnodeGoodsInProcessStatisticLineProcessProductionsExample example3 = new AnodeGoodsInProcessStatisticLineProcessProductionsExample();
            example3.createCriteria().andStatisticCodeEqualTo(id);
            List<AnodeGoodsInProcessStatisticLineProcessProductions> productions = lineProcessProductionsMapper.selectByExample(example3);
            ans.setBags(productions.get(0).getBagCounts());
            ans.setProductWeight(productions.get(0).getProductionWeights());
            ans.setProductStorage(productions.get(0).getProductionStorage());
        }
        if (totals.getProcessCode() == 9) {//车间
            Float total = 0f;
            AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example1 = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
            example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(9);
            List<AnodeGoodsInProcessStatisticSingleMaterialWeights> weights = singleMaterialWeightsMapper.selectByExample(example1);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < weights.size(); i++) {
                AnodeGoodsInProcessStatisticSingleMaterialWeights weight = weights.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(weight.getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (!info.getFlag()) {
                    info.setValue(weight.getWeights());
                    total += info.getValue();
                    infos.add(info);
                }
            }
            ans.setMats(infos);
            ans.settBal(total);
        }
        if (totals.getProcessCode() == 10) {//仓库
            Float total = 0f;
            AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example1 = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
            example1.createCriteria().andStatisticCodeEqualTo(id).andProcessCodeEqualTo(10);
            List<AnodeGoodsInProcessStatisticSingleMaterialWeights> weights = singleMaterialWeightsMapper.selectByExample(example1);
            List<AnodeMaterial> infos = new ArrayList<>();
            for (int i = 0; i < weights.size(); i++) {
                AnodeGoodsInProcessStatisticSingleMaterialWeights weight = weights.get(i);
                BasicInfoAnodeMaterialTypes t = materialMapper.selectByPrimaryKey(weight.getMaterialCode());
                AnodeMaterial info = new AnodeMaterial(t);
                if (!info.getFlag()) {
                    info.setValue(weight.getWeights());
                    total += info.getValue();
                    infos.add(info);
                }
            }
            ans.setMats(infos);
            ans.settBal(total);
        }
        return ans;
    }

    @Override
    public List getDateByPeriodId(Integer periodId) {
        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andFlagEqualTo(true);
        List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(example);

        List<Map<String, Object>> ans = new ArrayList<>();
        List<Integer> p = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            if (!p.contains(heads.get(i).getPeriods())) {//同一统计周期的期数，有n个产线，有n个相同的期数和开始时间
                Map<String, Object> map = new HashMap<>();
                map.put("periods", heads.get(i).getPeriods());
                map.put("beginTime", ComUtil.dateToString(heads.get(i).getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
                map.put("endTime", ComUtil.dateToString(heads.get(i).getEndTime(), "yyyy-MM-dd HH:mm:ss"));
                ans.add(map);
                p.add(heads.get(i).getPeriods());
            }
        }
        return ans;
    }

    @Override
    public Object statisticLine(Integer periodId, Integer periods) {
        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods).andFlagEqualTo(true);
        List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(example);//有几个产线统计了，就有几条

        for (int i = 0; i < heads.size(); i++) {
            Integer lineId = heads.get(i).getLineCode();
            AnodeGoodsInProcessStatisticByLineDetailsExample example1 = new AnodeGoodsInProcessStatisticByLineDetailsExample();
            example1.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
            List<AnodeGoodsInProcessStatisticByLineDetails> details = lineDetailsMapper.selectByExample(example1);
            if (details.size() == 0) {
                //生成按产线统计数据
                AnodeGoodsInProcessStatisticByLineDetails temp = new AnodeGoodsInProcessStatisticByLineDetails();
                temp.setLineCode(lineId);
                temp.setStatisticCode(heads.get(i).getCode());
                temp.setTypeCode(heads.get(i).getTypeCode());
                temp.setPeriodCode(periodId);
                temp.setPeriods(periods);


                Float first = 0f, second = 0f, product = 0f;
                /**
                 * 前段在制品重量是指该产线从第2个工序至第4个工序的所有结存量的合计值减去第4个工序（预烧工序）中的烧结料；
                 * 后段在制品重量是指该产线从第5个工序至第10个工序的所有结存量的合计值加上第4个工序（预烧工序）中的烧结料；
                 * 产品重量是指该产线的包装工序中的”产成品入库“的重量值；
                 */
                AnodeGoodsInProcessStatisticSingleMaterialWeightsExample weightsExample = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                weightsExample.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode()).andMaterialCodeEqualTo(18);
                AnodeGoodsInProcessStatisticSingleMaterialWeights pWeight = singleMaterialWeightsMapper.selectByExample(weightsExample).get(0);

                AnodeGoodsInProcessStatisticLineProcessProductionsExample productionsExample = new AnodeGoodsInProcessStatisticLineProcessProductionsExample();
                productionsExample.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
                AnodeGoodsInProcessStatisticLineProcessProductions productions = lineProcessProductionsMapper.selectByExample(productionsExample).get(0);
                product = productions.getProductionWeights();

                for (int l = 2; l <= 4; l++) {
                    AnodeGoodsInProcessStatisticByProcessTotalsExample example2 = new AnodeGoodsInProcessStatisticByProcessTotalsExample();
                    example2.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode()).andProcessCodeEqualTo(l);
                    List<AnodeGoodsInProcessStatisticByProcessTotals> totals = processTotalsMapper.selectByExample(example2);
                    for (int k = 0; k < totals.size(); k++) {
                        first += totals.get(k).getBalanceTotals();
                    }
                }
                first -= pWeight.getWeights();

                for (int l = 5; l <= 10; l++) {
                    AnodeGoodsInProcessStatisticByProcessTotalsExample example2 = new AnodeGoodsInProcessStatisticByProcessTotalsExample();
                    example2.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode()).andProcessCodeEqualTo(l);
                    List<AnodeGoodsInProcessStatisticByProcessTotals> totals = processTotalsMapper.selectByExample(example2);
                    for (int k = 0; k < totals.size(); k++) {
                        second += totals.get(k).getBalanceTotals();
                    }
                }
                second += pWeight.getWeights();

                temp.setFirstProcess(first);
                temp.setSecondProces(second);
                temp.setProduct(product);
                temp.setRawMaterialBalance(0f);
                temp.setRawMaterial(0f);
                lineDetailsMapper.insertSelective(temp);

                AnodeGoodsInProcessStatisticOnlineRawMaterialExample oExample = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
                oExample.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
                List<AnodeGoodsInProcessStatisticOnlineRawMaterial> onlions = onlineRawMaterialMapper.selectByExample(oExample);
                Float qW = onlions.get(0).getFeedstock();
                Float qB = onlions.get(0).getBalance();//前驱体
                Float tW = onlions.get(1).getFeedstock();
                Float tB = onlions.get(1).getBalance();//碳酸锂

                AnodeGoodsInProcessStatisticSingleMaterialWeightsExample tExample = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                tExample.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
                List<AnodeGoodsInProcessStatisticSingleMaterialWeights> ww = singleMaterialWeightsMapper.selectByExample(tExample);
                Float yW = ww.get(0).getWeights();//预混
                Float sW = ww.get(1).getWeights();//烧结

                AnodeGoodsInProcessStatisticByLineTotalsRawMaterialExample rawMaterialExample = new AnodeGoodsInProcessStatisticByLineTotalsRawMaterialExample();
                rawMaterialExample.createCriteria().andMaterialCodeEqualTo(1).andLineStatisticCodeEqualTo(periodId.longValue()).andPeriodsEqualTo(periods);
                List<AnodeGoodsInProcessStatisticByLineTotalsRawMaterial> li = rawMaterialMapper.selectByExample(rawMaterialExample);
                if (li.size() == 0) {
                    AnodeGoodsInProcessStatisticByLineTotalsRawMaterial rawMaterial = new AnodeGoodsInProcessStatisticByLineTotalsRawMaterial();
                    rawMaterial.setLineStatisticCode(periodId.longValue());
                    rawMaterial.setPeriods(periods);
                    rawMaterial.setMaterialCode(1);
                    rawMaterial.setFeedstock(qW);
                    rawMaterial.setBalance(qB);
                    rawMaterialMapper.insertSelective(rawMaterial);
                } else {
                    AnodeGoodsInProcessStatisticByLineTotalsRawMaterial rawMaterial = li.get(0);
                    rawMaterial.setFeedstock(rawMaterial.getFeedstock() + qW);
                    rawMaterial.setBalance(rawMaterial.getBalance() + qB);
                    rawMaterialMapper.updateByPrimaryKeySelective(rawMaterial);
                }

                rawMaterialExample = new AnodeGoodsInProcessStatisticByLineTotalsRawMaterialExample();
                rawMaterialExample.createCriteria().andMaterialCodeEqualTo(2).andLineStatisticCodeEqualTo(periodId.longValue()).andPeriodsEqualTo(periods);
                li = rawMaterialMapper.selectByExample(rawMaterialExample);
                if (li.size() == 0) {
                    AnodeGoodsInProcessStatisticByLineTotalsRawMaterial rawMaterial = new AnodeGoodsInProcessStatisticByLineTotalsRawMaterial();
                    rawMaterial.setLineStatisticCode(periodId.longValue());
                    rawMaterial.setPeriods(periods);
                    rawMaterial.setMaterialCode(2);
                    rawMaterial.setFeedstock(tW);
                    rawMaterial.setBalance(tB);
                    rawMaterialMapper.insertSelective(rawMaterial);
                } else {
                    AnodeGoodsInProcessStatisticByLineTotalsRawMaterial rawMaterial = li.get(0);
                    rawMaterial.setFeedstock(rawMaterial.getFeedstock() + tW);
                    rawMaterial.setBalance(rawMaterial.getBalance() + tB);
                    rawMaterialMapper.updateByPrimaryKeySelective(rawMaterial);
                }

                rawMaterialExample = new AnodeGoodsInProcessStatisticByLineTotalsRawMaterialExample();
                rawMaterialExample.createCriteria().andMaterialCodeEqualTo(3).andLineStatisticCodeEqualTo(periodId.longValue()).andPeriodsEqualTo(periods);
                li = rawMaterialMapper.selectByExample(rawMaterialExample);
                if (li.size() == 0) {
                    AnodeGoodsInProcessStatisticByLineTotalsRawMaterial rawMaterial = new AnodeGoodsInProcessStatisticByLineTotalsRawMaterial();
                    rawMaterial.setLineStatisticCode(periodId.longValue());
                    rawMaterial.setPeriods(periods);
                    rawMaterial.setMaterialCode(3);
                    rawMaterial.setFeedstock(yW);
                    rawMaterial.setBalance(0f);
                    rawMaterialMapper.insertSelective(rawMaterial);
                } else {
                    AnodeGoodsInProcessStatisticByLineTotalsRawMaterial rawMaterial = li.get(0);
                    rawMaterial.setFeedstock(rawMaterial.getFeedstock() + yW);
                    rawMaterialMapper.updateByPrimaryKeySelective(rawMaterial);
                }

                rawMaterialExample = new AnodeGoodsInProcessStatisticByLineTotalsRawMaterialExample();
                rawMaterialExample.createCriteria().andMaterialCodeEqualTo(4).andLineStatisticCodeEqualTo(periodId.longValue()).andPeriodsEqualTo(periods);
                li = rawMaterialMapper.selectByExample(rawMaterialExample);
                if (li.size() == 0) {
                    AnodeGoodsInProcessStatisticByLineTotalsRawMaterial rawMaterial = new AnodeGoodsInProcessStatisticByLineTotalsRawMaterial();
                    rawMaterial.setLineStatisticCode(periodId.longValue());
                    rawMaterial.setPeriods(periods);
                    rawMaterial.setMaterialCode(4);
                    rawMaterial.setFeedstock(sW);
                    rawMaterial.setBalance(0f);
                    rawMaterialMapper.insertSelective(rawMaterial);
                } else {
                    AnodeGoodsInProcessStatisticByLineTotalsRawMaterial rawMaterial = li.get(0);
                    rawMaterial.setFeedstock(rawMaterial.getFeedstock() + sW);
                    rawMaterialMapper.updateByPrimaryKeySelective(rawMaterial);
                }
            } else {
                //查询 放外面吧
            }
        }
        Map<String, Object> ans = new HashMap<>();
        List<AnodeLineStatistic> list = new ArrayList<>();
        Float tFirst = 0f, tSecond = 0f, tW = 0f;

        for (int i = 0; i < heads.size(); i++) {
            AnodeLineStatistic temp = new AnodeLineStatistic();
            temp.setHead(heads.get(i));
            temp.setLineName(lineMapper.selectByPrimaryKey(heads.get(i).getLineCode()).getName());
            temp.setPeriodName(periodMapper.selectByPrimaryKey(heads.get(i).getPeriodCode()).getName());
            AnodeGoodsInProcessStatisticByLineDetailsExample example1 = new AnodeGoodsInProcessStatisticByLineDetailsExample();
            example1.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
            temp.setDetails(lineDetailsMapper.selectByExample(example1).get(0));

            AnodeGoodsInProcessStatisticOnlineRawMaterialExample oExample = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
            oExample.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
            List<AnodeGoodsInProcessStatisticOnlineRawMaterial> onlions = onlineRawMaterialMapper.selectByExample(oExample);

            Float qW = onlions.get(0).getFeedstock();
            Float qB = onlions.get(0).getBalance();//前驱体
            Float lW = onlions.get(1).getFeedstock();
            Float lB = onlions.get(1).getBalance();//碳酸锂

            AnodeGoodsInProcessStatisticSingleMaterialWeightsExample tExample = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
            tExample.createCriteria().andStatisticCodeEqualTo(heads.get(i).getCode());
            List<AnodeGoodsInProcessStatisticSingleMaterialWeights> ww = singleMaterialWeightsMapper.selectByExample(tExample);
            Float yW = ww.get(0).getWeights();//预混
            Float sW = ww.get(1).getWeights();//烧结

            Map<String, Object> rawW = new HashMap<>();
            Map<String, Object> rawB = new HashMap<>();
            rawW.put("前驱体(kg)", qW);
            rawW.put("碳酸锂(kg)", lW);
            rawW.put("预混料(kg)", yW);
            rawW.put("烧结料(kg)", sW);
            rawB.put("前驱体(kg)", qB);
            rawB.put("碳酸锂(kg)", lB);
            temp.setRawW(rawW);
            temp.setRawB(rawB);
            list.add(temp);
            tFirst += temp.getDetails().getFirstProcess();
            tSecond += temp.getDetails().getSecondProces();
            tW += temp.getDetails().getProduct();
        }
        ans.put("list", list);
        ans.put("前段在制品重量", tFirst);
        ans.put("后段在制品重量", tSecond);
        ans.put("产品重量", tW);
        AnodeGoodsInProcessStatisticByLineTotalsRawMaterialExample example1 = new AnodeGoodsInProcessStatisticByLineTotalsRawMaterialExample();
        example1.createCriteria().andPeriodsEqualTo(periods).andLineStatisticCodeEqualTo(periodId.longValue());
        List<AnodeGoodsInProcessStatisticByLineTotalsRawMaterial> rawMaterials = rawMaterialMapper.selectByExample(example1);
        for (int i = 0; i < rawMaterials.size(); i++) {
            if (rawMaterials.get(i).getMaterialCode() == 1) {
                ans.put("前驱体", rawMaterials.get(i));
            }
            if (rawMaterials.get(i).getMaterialCode() == 2) {
                ans.put("碳酸锂", rawMaterials.get(i));
            }
            if (rawMaterials.get(i).getMaterialCode() == 3) {
                ans.put("预混料", rawMaterials.get(i));
            }
            if (rawMaterials.get(i).getMaterialCode() == 4) {
                ans.put("烧结料", rawMaterials.get(i));
            }
        }
        return ans;
    }

    @Override
    public String judgeLine(Integer periodId, Integer periods) {
        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods).andFlagEqualTo(true);
        List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(example);//有几个产线统计了，就有几条

        BasicInfoAnodeProductionLineExample lineExample = new BasicInfoAnodeProductionLineExample();
        lineExample.createCriteria();
        List<BasicInfoAnodeProductionLine> lines = lineMapper.selectByExample(lineExample);
        List<Boolean> flag = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            flag.add(false);
            for (int j = 0; j < heads.size(); j++) {
                if (heads.get(j).getLineCode() == lines.get(i).getCode()) {
                    flag.set(i, true);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < flag.size(); i++) {
            if (!flag.get(i))
                sb.append(lines.get(i).getName() + ",");
        }
        return sb.toString();
    }

    @Override
    public List statisticProcess(Integer periodId, Integer periods, Integer lineId) {
        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();
        example.createCriteria().andPeriodCodeEqualTo(periodId).andPeriodsEqualTo(periods).andLineCodeEqualTo(lineId).andFlagEqualTo(true);
        AnodeGoodsInProcessStatisticHead head = headMapper.selectByExample(example).get(0);

        AnodeGoodsInProcessStatisticByProcessTotalsExample example1 = new AnodeGoodsInProcessStatisticByProcessTotalsExample();
        example1.createCriteria().andStatisticCodeEqualTo(head.getCode());
        List<AnodeGoodsInProcessStatisticByProcessTotals> totals = processTotalsMapper.selectByExample(example1);

        List<Map> ans = new ArrayList<>();
        for (int i = 0; i < totals.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("head", head);
            map.put("lineName", lineMapper.selectByPrimaryKey(head.getLineCode()).getName());
            map.put("processName", processNameMapper.selectByPrimaryKey(totals.get(i).getProcessCode()).getProcessName());
            map.put("periodName", periodMapper.selectByPrimaryKey(head.getPeriodCode()).getName());
            map.put("values", totals.get(i));
            if (totals.get(i).getProcessCode() == 1) {
                Map<String, Object> in = new HashMap<>();
                Map<String, Object> con = new HashMap<>();
                Map<String, Object> bal = new HashMap<>();

                AnodeGoodsInProcessStatisticOnlineRawMaterialExample example2 = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
                example2.createCriteria().andStatisticCodeEqualTo(head.getCode());
                List<AnodeGoodsInProcessStatisticOnlineRawMaterial> materials = onlineRawMaterialMapper.selectByExample(example2);
                for (int l = 0; l < materials.size(); l++) {
                    if (materials.get(l).getMaterialCode() == 1) {
                        in.put("前驱体(kg)", materials.get(l).getFeedstock());
                        con.put("前驱体(kg)", materials.get(l).getConsume());
                        bal.put("前驱体(kg)", materials.get(l).getBalance());
                    }
                    if (materials.get(l).getMaterialCode() == 2) {
                        in.put("碳酸锂(kg)", materials.get(l).getFeedstock());
                        con.put("碳酸锂(kg)", materials.get(l).getConsume());
                        bal.put("碳酸锂(kg)", materials.get(l).getBalance());
                    }
                }
                AnodeGoodsInProcessStatisticSingleMaterialWeightsExample example3 = new AnodeGoodsInProcessStatisticSingleMaterialWeightsExample();
                example3.createCriteria().andStatisticCodeEqualTo(head.getCode());
                List<AnodeGoodsInProcessStatisticSingleMaterialWeights> weights = singleMaterialWeightsMapper.selectByExample(example3);
                for (int l = 0; l < weights.size(); l++) {
                    if (weights.get(l).getMaterialCode() == 3) {
                        in.put("预混料(kg)", weights.get(l).getWeights());
                    }
                    if (weights.get(l).getMaterialCode() == 4) {
                        in.put("烧结料(kg)", weights.get(l).getWeights());
                    }
                }
                map.put("in", in);
                map.put("con", con);
                map.put("bal", bal);
            } else {

            }
            ans.add(map);
        }
        return ans;
    }

    @Override
    public Map getEnableLineAndStatisticRecord(Integer periodId, Integer periods) {
        Map<String, Object> ans = new HashMap<>();
        BasicInfoAnodeProductionLineExample example = new BasicInfoAnodeProductionLineExample();
        example.createCriteria().andFlagEqualTo(false);
        ans.put("lineEnables", lineMapper.countByExample(example));

        AnodeGoodsInProcessStatisticHeadExample example1 = new AnodeGoodsInProcessStatisticHeadExample();
        example1.createCriteria().andPeriodsEqualTo(periods).andPeriodCodeEqualTo(periodId).andFlagEqualTo(true);
        ans.put("statisticLines", headMapper.countByExample(example1));

        return ans;
    }

    @Override
    public List lineCompare(Integer periodId, String startTime, String endTime, Integer dataFlag, Integer materialFlag) {
        AnodeGoodsInProcessStatisticHeadExample example = new AnodeGoodsInProcessStatisticHeadExample();
        example.createCriteria().andBeginTimeBetween(ComUtil.getDate(startTime,"yyyy-MM-dd HH:mm:ss"),ComUtil.getDate(endTime,"yyyy-MM-dd HH:mm:ss")).andPeriodCodeEqualTo(periodId).andFlagEqualTo(true);
        List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(example);
        Set<Integer> periods = new HashSet<>();
        for(int i=0;i<heads.size();i++){
            periods.add(heads.get(i).getPeriods());
        }
        List<Integer> periodss = new ArrayList<>();
        for(Integer key:periods)
            periodss.add(key);
        periodss.sort(null);

        List<Map<String,Object>> ans = new ArrayList<>();

        if(dataFlag == 0 || dataFlag == 1){
            for(int i=0;i<periodss.size();i++){
                Map<String,Object> map = new HashMap<>();
                List<BasicInfoAnodeProductionLine> lines = new ArrayList<>();
                List<Float> values = new ArrayList<>();
                AnodeGoodsInProcessStatisticByLineDetailsExample example1 = new AnodeGoodsInProcessStatisticByLineDetailsExample();
                example1.createCriteria().andPeriodsEqualTo(periodss.get(i)).andPeriodCodeEqualTo(periodId);
                List<AnodeGoodsInProcessStatisticByLineDetails> details = lineDetailsMapper.selectByExample(example1);
                for(int l=0;l<details.size();l++){
                    AnodeGoodsInProcessStatisticOnlineRawMaterialExample example2 = new AnodeGoodsInProcessStatisticOnlineRawMaterialExample();
                    example2.createCriteria().andStatisticCodeEqualTo(details.get(l).getStatisticCode()).andMaterialCodeEqualTo(materialFlag);
                    List<AnodeGoodsInProcessStatisticOnlineRawMaterial> materials = onlineRawMaterialMapper.selectByExample(example2);
                    lines.add(lineMapper.selectByPrimaryKey(details.get(l).getLineCode()));
                    if(dataFlag == 0){
                        values.add(materials.get(0).getFeedstock());
                    }else{
                        values.add(materials.get(0).getBalance());
                    }
                    if(l == 0){
                        map.put("time",ComUtil.dateToString(headMapper.selectByPrimaryKey(details.get(0).getStatisticCode()).getBeginTime(),"yyyy-MM-dd"));
                    }
                }
                map.put("value",values);
                map.put("lines",lines);
                ans.add(map);
            }
        }else{
            for(int i=0;i<periodss.size();i++){
                Map<String,Object> map = new HashMap<>();
                List<BasicInfoAnodeProductionLine> lines = new ArrayList<>();
                List<Float> values = new ArrayList<>();
                AnodeGoodsInProcessStatisticByLineDetailsExample example1 = new AnodeGoodsInProcessStatisticByLineDetailsExample();
                example1.createCriteria().andPeriodsEqualTo(periodss.get(i)).andPeriodCodeEqualTo(periodId);
                List<AnodeGoodsInProcessStatisticByLineDetails> details = lineDetailsMapper.selectByExample(example1);
                for(int l=0;l<details.size();l++){
                   lines.add(lineMapper.selectByPrimaryKey(details.get(l).getLineCode()));
                   if(dataFlag == 2){
                        values.add(details.get(l).getFirstProcess());
                   }
                    if(dataFlag == 3){
                        values.add(details.get(l).getSecondProces());
                    }
                    if(dataFlag == 4){
                        values.add(details.get(l).getProduct());
                    }
                    if(l == 0){
                        map.put("time",ComUtil.dateToString(headMapper.selectByPrimaryKey(details.get(0).getStatisticCode()).getBeginTime(),"yyyy-MM-dd"));
                    }
                }
                map.put("value",values);
                map.put("lines",lines);
                ans.add(map);
            }
        }
        return ans;
    }

    @Override
    public List processCompare(Integer periodId, Integer lineCode, String startTime, String endTime, Integer flag) {

        String format = "yyyy-MM-dd HH:mm:ss";

        Date start = ComUtil.getDate(startTime, format);
        Date end = ComUtil.getDate(endTime, format);

        AnodeGoodsInProcessStatisticHeadExample headExample = new AnodeGoodsInProcessStatisticHeadExample();
        headExample.createCriteria().andPeriodCodeEqualTo(periodId).andBeginTimeBetween(start, end).andLineCodeEqualTo(lineCode);
        List<AnodeGoodsInProcessStatisticHead> heads = headMapper.selectByExample(headExample);

        if (heads.size() > 0) {
            List<Long> codeList = new ArrayList<>();
            List<Map<String, Object>> res = new ArrayList<>();
            //获取统计编码组
            Map<Long, String> timeMap = new HashMap<>();
            heads.forEach(item -> {
                codeList.add(item.getCode());
                timeMap.put(item.getCode(), ComUtil.dateToString(item.getBeginTime(), format));
            });

            List<BasicInfoAnodeProcessName> all = anodeProcessNameService.getAll();
            List<Integer> processCodeList = new ArrayList<>();
            Map<Integer, String> processMap = new HashMap<>();
            all.forEach(item -> {
                //共10个工序，但“在线原料”工序不参与对比分析
                if (item.getCode() != 1) {
                    processCodeList.add(item.getCode());
                }
                processMap.put(item.getCode(), item.getProcessName());
            });

            for (Long statCode : codeList) {

                Map<String, Object> map = new HashMap<>();
                map.put("time", timeMap.get(statCode));
                List<Map<String, Object>> list = new ArrayList<>();
                for (Integer processCode : processCodeList) {
                    AnodeGoodsInProcessStatisticByProcessTotalsExample example = new AnodeGoodsInProcessStatisticByProcessTotalsExample();
                    example.createCriteria().andStatisticCodeEqualTo(statCode).andProcessCodeEqualTo(processCode);
                    List<AnodeGoodsInProcessStatisticByProcessTotals> byProcessTotals = processTotalsMapper.selectByExample(example);

                    Map<String, Object> temp = new HashMap<>();
                    if (byProcessTotals.size() > 0) {
                        AnodeGoodsInProcessStatisticByProcessTotals totals = byProcessTotals.get(0);
                        if (flag == 0) {
                            temp.put("statValue", totals.getFeedstockTotals());
                        }
                        if (flag == 1) {
                            temp.put("statValue", totals.getConsumeTotals());
                        }
                        if (flag == 2) {
                            temp.put("statValue", totals.getBalanceTotals());
                        }
                        temp.put("processCode", processCode);
                        temp.put("processName", processMap.get(processCode));
                    } else {
                        temp.put("time", timeMap.get(statCode));
                        temp.put("statValue", 0);
                        temp.put("processCode", processCode);
                        temp.put("processName", processMap.get(processCode));
                    }
                    list.add(temp);
                }
                map.put("list", list);

                res.add(map);
            }
            return res;
        }
        return new ArrayList();
    }


    private Float getValueByNameByTime(String matName, Date start, Date end) {
        Double ans = Math.random() * 100 + 1;
        return ans.floatValue();
    }

    private Float getDcsValue(Integer matId, Integer lineCode, String attName, Date date) {
        BasicInfoAnodeMaterialPlcMapExample example = new BasicInfoAnodeMaterialPlcMapExample();
        example.createCriteria().andMaterialCodeEqualTo(matId).andLineCodeEqualTo(lineCode).andMaterialAttEqualTo(attName);
        List<BasicInfoAnodeMaterialPlcMap> temp = mapMapper.selectByExample(example);
        if(temp.size() == 0)
            return 0f;
        Integer plc = temp.get(0).getPlcCode();
        BasicInfoAnodePlcAddress address = addressMapper.selectByPrimaryKey(plc);
        Float ans = RealTimeUtil.dcsForAnode("http://192.168.190.162:10086/api/History",address.getPlcAddress(),date);
        return ans==null?0f:ans;
    }

    private Integer getDcsCount(Integer matId,Integer lineCode,Integer periodCode,Date date){
        String attName = "";
        if(periodCode == 1){
            attName = "白班次数";
        }else if(periodCode == 2){
            attName = "晚班次数";
        }else{
            return 0;
        }
        BasicInfoAnodeMaterialPlcMapExample example = new BasicInfoAnodeMaterialPlcMapExample();
        example.createCriteria().andMaterialCodeEqualTo(matId).andLineCodeEqualTo(lineCode).andMaterialAttEqualTo(attName);
        List<BasicInfoAnodeMaterialPlcMap> temp = mapMapper.selectByExample(example);
        if(temp.size() == 0)
            return 0;
        Integer plc = temp.get(0).getPlcCode();
        BasicInfoAnodePlcAddress address = addressMapper.selectByPrimaryKey(plc);
        Float ans = RealTimeUtil.dcsForAnode("http://192.168.190.162:10086/api/History",address.getPlcAddress(),date);
        return ans==null?0:ans.intValue();
    }
}

