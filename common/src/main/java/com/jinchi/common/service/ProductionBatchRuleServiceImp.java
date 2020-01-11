package com.jinchi.common.service;


import com.jinchi.common.domain.*;
import com.jinchi.common.mapper.ProductionBatchInfoMapper;
import com.jinchi.common.mapper.ProductionBatchRuleDetailMapper;
import com.jinchi.common.mapper.ProductionBatchRuleHeadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductionBatchRuleServiceImp implements ProductionBatchRuleService {

    @Autowired
    ProductionBatchInfoMapper productionBatchInfoMapper;
    @Autowired
    ProductionBatchRuleDetailMapper productionBatchRuleDetailMapper;
    @Autowired
    ProductionBatchRuleHeadMapper productionBatchRuleHeadMapper;

    /*
     获取界面的值，  没有  状态
     */
    @Override
    public ProductionBatchInfo getInfoByCode(long code) {
        ProductionBatchInfoExample productionBatchInfoExample = new ProductionBatchInfoExample();
        productionBatchInfoExample.createCriteria().andCodeEqualTo(code);
        List<ProductionBatchInfo> productionBatchInfo = productionBatchInfoMapper.selectByExample(productionBatchInfoExample);
        return productionBatchInfo.get(0);
    }

    @Override
    public void UpdateState(Byte code, Boolean flag) {
        ProductionBatchRuleHeadExample productionBatchRuleHeadExample = new ProductionBatchRuleHeadExample();
        productionBatchRuleHeadExample.createCriteria().andCodeEqualTo(code);
        ProductionBatchRuleHead productionBatchRuleHead = new ProductionBatchRuleHead();
        productionBatchRuleHead.setRuleFlag(flag);
        productionBatchRuleHeadMapper.updateByExampleSelective(productionBatchRuleHead, productionBatchRuleHeadExample);
    }

    @Override
    public List<ProductionBatchRuleHead> getAll() {
        ProductionBatchRuleHeadExample productionBatchRuleHeadExample = new ProductionBatchRuleHeadExample();
        productionBatchRuleHeadExample.createCriteria().andCodeIsNotNull();
        List<ProductionBatchRuleHead> productionBatchRuleHead = productionBatchRuleHeadMapper.selectByExample(productionBatchRuleHeadExample);
        return productionBatchRuleHead;
    }


    //firelfy
    @Override
    public List<ProductionBatchRuleDetail> getDetailByRuleCode(Byte code) {
        ProductionBatchRuleDetailExample productionBatchRuleDetailExample = new ProductionBatchRuleDetailExample();
        productionBatchRuleDetailExample.createCriteria().andRuleCodeEqualTo(code);
        List<ProductionBatchRuleDetail> productionBatchRuleDetail =
                productionBatchRuleDetailMapper.selectByExample(productionBatchRuleDetailExample);
        return productionBatchRuleDetail;
    }

    @Override
    public void delDetailByRuleCode(Short code) {

        ProductionBatchRuleDetailExample productionBatchRuleDetailExample = new ProductionBatchRuleDetailExample();
        productionBatchRuleDetailExample.createCriteria().andCodeEqualTo(code);
        productionBatchRuleDetailMapper.deleteByExample(productionBatchRuleDetailExample);

    }

    @Override
    public void addOneDetail(ProductionBatchRuleDetail productionBatchRuleDetail) {
        productionBatchRuleDetailMapper.insertSelective(productionBatchRuleDetail);
        //rule_value 更新头表的default_value 待完成
    }

    @Override
    @Transactional
    public Map<String, Object> updateAll(List<ProductionBatchRuleDetail> productionBatchRuleDetails, Byte ruleCode) {
        //不能删除后新增，只能直接修修改
        int count = 0;
        //获取传入的参数里的code
        List<Short> codeList = new ArrayList<>();
        for (ProductionBatchRuleDetail p : productionBatchRuleDetails) {
            if (p.getCode() != null) {
                codeList.add(p.getCode());
            }
        }
        // 获取原来的记录里的所有code
        List<Short> codeListOld = new ArrayList<>();
        ProductionBatchRuleDetailExample detailExample = new ProductionBatchRuleDetailExample();
        detailExample.createCriteria().andRuleCodeEqualTo(ruleCode);
        List<ProductionBatchRuleDetail> ruleDetails = productionBatchRuleDetailMapper.selectByExample(detailExample);
        for (ProductionBatchRuleDetail ruleDetail : ruleDetails) {
            codeListOld.add(ruleDetail.getCode());
        }

        List<Short> collect = codeListOld.stream().filter(item -> !codeList.contains(item)).collect(Collectors.toList());
        //删除不存在于当前传入参数的记录
        for (Short code : collect) {
            try {
                delDetailByRuleCode(code);
            } catch (Exception e) {
                String sql = "SELECT * FROM `production_batch_rule_detail` WHERE `code` = '" + code + "'";
                ProductionBatchRuleDetail ruleDetail = productionBatchRuleDetailMapper.selectById(sql);
                String ruleValue = ruleDetail.getRuleValue();
                Map<String, Object> map = new HashMap<>();
                map.put("code", -1);
                map.put("message", "批次规则" + ruleValue + "不可删除");
                return map;
            }
        }


        for (ProductionBatchRuleDetail p : productionBatchRuleDetails) {

            if (p.getCode() == null) {
                p.setRuleCode(ruleCode);
                productionBatchRuleDetailMapper.insertSelective(p);
            } else {
                ProductionBatchRuleDetailExample productionBatchRuleDetailExample = new ProductionBatchRuleDetailExample();
                productionBatchRuleDetailExample.createCriteria().andCodeEqualTo(p.getCode());
                productionBatchRuleDetailMapper.updateByExampleSelective(p, productionBatchRuleDetailExample);
            }

            //productionBatchRuleDetailMapper.insertSelective(p);

            if (!p.getDefaultFlag()) {
                count++;
                ProductionBatchRuleHeadExample productionBatchRuleHeadExample = new ProductionBatchRuleHeadExample();
                productionBatchRuleHeadExample.createCriteria().andCodeEqualTo(p.getRuleCode());
                ProductionBatchRuleHead productionBatchRuleHead = new ProductionBatchRuleHead();
                productionBatchRuleHead.setDefaultValue(p.getRuleValue());
                productionBatchRuleHeadMapper.updateByExampleSelective(productionBatchRuleHead, productionBatchRuleHeadExample);
            }
        }
        if (count == 0) {
            ProductionBatchRuleHeadExample productionBatchRuleHeadExample = new ProductionBatchRuleHeadExample();
            productionBatchRuleHeadExample.createCriteria().andCodeEqualTo(ruleCode);
            ProductionBatchRuleHead productionBatchRuleHead = new ProductionBatchRuleHead();
            productionBatchRuleHead.setDefaultValue(null);
            productionBatchRuleHeadMapper.updateByExampleSelective(productionBatchRuleHead, productionBatchRuleHeadExample);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "");
        return map;
    }

    @Override
    public List<BatchRuleDTO> getAllInfos() {
        List<BatchRuleDTO> ans = new ArrayList<>();
        ProductionBatchRuleHeadExample example = new ProductionBatchRuleHeadExample();
        example.createCriteria().andRuleFlagEqualTo(true);
        List<ProductionBatchRuleHead> productionBatchRuleHeads = productionBatchRuleHeadMapper.selectByExample(example);
        for (int i = 0; i < productionBatchRuleHeads.size(); i++) {
            ProductionBatchRuleHead productionBatchRuleHead = productionBatchRuleHeads.get(i);
            BatchRuleDTO batchRuleDTO = new BatchRuleDTO();
            batchRuleDTO.setRule(productionBatchRuleHead.getRuleName());
            ProductionBatchRuleDetailExample example1 = new ProductionBatchRuleDetailExample();
            example1.createCriteria().andRuleCodeEqualTo(productionBatchRuleHead.getCode());
            List<ProductionBatchRuleDetail> productionBatchRuleDetails = productionBatchRuleDetailMapper.selectByExample(example1);
            List<String> temp = new ArrayList<>();
            for (int j = 0; j < productionBatchRuleDetails.size(); j++) {
                if (!productionBatchRuleDetails.get(j).getDefaultFlag()) {
                    batchRuleDTO.setDefaultValue(productionBatchRuleDetails.get(j).getRuleValue());
                }
                temp.add(productionBatchRuleDetails.get(j).getRuleValue());
            }
            batchRuleDTO.setValues(temp);
            ans.add(batchRuleDTO);
        }
        return ans;
    }


}
