package com.jinchi.common.service;

import com.jinchi.common.constant.QualitySampleTypeEnum;
import com.jinchi.common.domain.RepoBaseSerialNumber;
import com.jinchi.common.domain.RepoInRecord;
import com.jinchi.common.domain.RepoStock;
import com.jinchi.common.domain.TechniqueBaseRawManufacturer;
import com.jinchi.common.dto.PageBean;
import com.jinchi.common.dto.repository.RepoOutHeadDTO;
import com.jinchi.common.mapper.RepoBaseSerialNumberMapper;
import com.jinchi.common.mapper.RepoInRecordMapper;
import com.jinchi.common.mapper.RepoStockMapper;
import com.jinchi.common.mapper.TechniqueBaseRawManufacturerMapper;
import com.jinchi.common.utils.itemCode.AbstractMaterialDecoder;
import com.jinchi.common.utils.itemCode.DecoderV1;
import com.jinchi.common.utils.itemCode.MaterialCode;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author：XudongHu
 * @className:RepoInRecordServiceImp
 * @description:
 * @date:14:46 2018/11/29
 */
@Service
public class RepoInRecordServiceImp implements RepoInRecordService {
    private Logger logger = LoggerFactory.getLogger(RepoInRecordServiceImp.class);
    @Autowired
    private RepoBaseSerialNumberMapper repoBaseSerialNumberMapper;
    @Autowired
    private RepoInRecordMapper repoInRecordMapper;
    @Autowired
    private RepoStockMapper repoStockMapper;
    @Autowired
    private TechniqueBaseRawManufacturerMapper techniqueBaseRawManufacturerMapper;


    /**
     * 根据名称模糊和类型查询
     *
     * @param materialName 材料名称
     * @param materialType 原材料、中间件、成品
     * @return
     */
    @Override
    public List<Map<Object, Object>> byNameLikeAndType(String materialName, Integer materialType) {
        List<RepoInRecord> repoInRecords = repoInRecordMapper.byNameAndType(materialName, materialType);
        AbstractMaterialDecoder decoder = new DecoderV1();
        return batchConvertMap(repoInRecords, decoder);
    }

    /**
     * 根据名称模糊和类型查询-分页
     */
    @Override
    public PageBean byNameLikeAndTypeByPage(String materialName, Integer materialType, Integer page, Integer size, String fieldName, String orderType) {
        PageBean pageBean = new PageBean();
        pageBean.setSortField(fieldName);
        pageBean.setSortType(orderType);
        pageBean.setPageNumber(page);
        pageBean.setPageSize(size);

        List<RepoInRecord> repoInRecords = repoInRecordMapper.byNameAndType(materialName, materialType);
        AbstractMaterialDecoder decoder = new DecoderV1();
        List<Map<Object, Object>> data = batchConvertMap(repoInRecords, decoder);
        pageBean.setList(data);
        Integer countSum = repoInRecordMapper.countSum(materialName, materialType);
        pageBean.setTotal(countSum);

        return pageBean;
    }

    /**
     * 批量转换成dto
     *
     * @param repoInRecords 入库记录list
     * @param decoder       解码器
     * @return
     */
    private List<Map<Object, Object>> batchConvertMap(List<RepoInRecord> repoInRecords, AbstractMaterialDecoder decoder) {
        List<Map<Object, Object>> listVo = new ArrayList<>();

        for (int i = 0; i < repoInRecords.size(); i++) {
            Map mapVo = convertMap(repoInRecords.get(i), decoder);
            listVo.add(mapVo);
        }
        return listVo;
    }

    /**
     * 转换成dto
     *
     * @param repoInRecord 入库记录
     * @return
     */
    private Map convertMap(RepoInRecord repoInRecord, AbstractMaterialDecoder decoder) {
        Map<Object, Object> mapVo = new HashMap<>();

        RepoBaseSerialNumber serialNumber = repoBaseSerialNumberMapper.findById(repoInRecord.getSerialNumberId());
        String itemCode = serialNumber.getSerialNumber();

        MaterialCode.DecodedMaterialCode decodedMaterialCode = decoder.decode(itemCode);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        mapVo.put("id", repoInRecord.getId());
        mapVo.put("materialName", serialNumber.getMaterialName());
        mapVo.put("itemCode", itemCode);
        mapVo.put("bagNumber", decodedMaterialCode.getBagNumber());
        mapVo.put("batch", decodedMaterialCode.getBatch());
        mapVo.put("workShop", decodedMaterialCode.getWorkshop());
        mapVo.put("manufacturer", serialNumber.getManufacturerName());
        mapVo.put("weight", repoInRecord.getWeight());
        mapVo.put("inTime", sdf.format(repoInRecord.getInTime()));
        mapVo.put("createTime", sdf.format(repoInRecord.getCreateTime()));
        mapVo.put("operator", repoInRecord.getCreatePerson());


        return mapVo;
    }

    /**
     * 新增入库记录   同时增加库存
     *
     * @param repoInRecordDTO
     * @return
     * @See RepoStock
     */
    @Override
    @Transactional
    public String stockpiling(RepoOutHeadDTO repoInRecordDTO) {
        String message="操作成功";
        String itemCode = repoInRecordDTO.getStockOutRecordHeadCode();
        AbstractMaterialDecoder decoder = new DecoderV1();
        MaterialCode.DecodedMaterialCode decodedMaterialCode = decoder.decode(itemCode);

        Integer type;
        switch (decodedMaterialCode.getMaterialType().toUpperCase()){
            case "RAW":type=1;break;
            case"RAW(TS)":type =1;break;
            case "RAW(YS)":type=1;break;
            case "MED":type=2;break;
            case "PRO":type=3;break;
            case "GAR":type=4;break;
            case "RAP":type=5;break;
            case "PRP":type=6;break;
            case "IST":type=7;break;
            case "ISP":type=8;break;
            default:type = -1;
        }

        RepoBaseSerialNumber repoBaseSerialNumber = fillSerialNumber(itemCode, decodedMaterialCode).setMaterialClass(type);

        if(null!=repoBaseSerialNumberMapper.bySerialNumber(itemCode)){
            message = "入库失败,该物料编码已存在";
            return message;
        }
        if(type==-1){
            message = "入库失败,物料编码无法识别类型:"+decodedMaterialCode.getMaterialType();
            return message;
        }

        logger.info(message);
        logger.info(String.valueOf(type));


        repoBaseSerialNumberMapper.insert(repoBaseSerialNumber);

        String weightString = decodedMaterialCode.getWeight();


        int weight = Integer.parseInt(weightString.replaceAll("\\D", ""));

        RepoInRecord repoInRecord = convertDomain(repoInRecordDTO, weight, repoBaseSerialNumber.getId());
        repoInRecordMapper.insert(repoInRecord);
        //更新库存
        repoStockMapper.addNewStock(
                new RepoStock()
                        .setWeight(repoInRecord.getWeight())
                        .setMaterialType(QualitySampleTypeEnum.getTypeCodeByCode(decodedMaterialCode.getMaterialType()))
                        .setSerialNumberId(repoInRecord.getSerialNumberId()));

        return message;
    }


    /**
     * 将入库DTO装换成入库实体
     *
     * @param repoInRecordDTO 入库DTO
     * @param serialNumberId  编号id
     * @param weight          重量
     * @return
     */
    private RepoInRecord convertDomain(RepoOutHeadDTO repoInRecordDTO, Integer weight, Integer serialNumberId) {
        repoInRecordDTO.getCreatedTime();
        RepoInRecord repoInRecord = new RepoInRecord();

        repoInRecord
                .setWeight(weight)
                .setCreatePerson(repoInRecordDTO.getCreatedPerson())
                .setInTime(repoInRecordDTO.getCreatedTime())
                .setCreateTime(new Date())
                .setSerialNumberId(serialNumberId);

        return repoInRecord;
    }

    /**
     * 将编号信息存储
     *
     * @param itemCode            物料编码
     * @param decodedMaterialCode
     * @return
     */
    private RepoBaseSerialNumber fillSerialNumber(String itemCode, MaterialCode.DecodedMaterialCode decodedMaterialCode) {
        RepoBaseSerialNumber serialNumber = new RepoBaseSerialNumber();
        TechniqueBaseRawManufacturer manufacturer = techniqueBaseRawManufacturerMapper.byCode(decodedMaterialCode.getManufacturer());
        Assert.notNull(manufacturer, String.format("添加失败,不能识别代号为%s的厂商", decodedMaterialCode.getManufacturer()));

        serialNumber
                .setMaterialClass(QualitySampleTypeEnum.getTypeCodeByCode(decodedMaterialCode.getMaterialType()))
                .setMaterialName(decodedMaterialCode.getMaterialName())
                .setSerialNumber(itemCode)
                .setManufacturerName(manufacturer.getName());

        return serialNumber;
    }

}
