package com.jinchi.common.service;

import com.jinchi.common.domain.FireMagePpItems;
import com.jinchi.common.domain.FireMagePpItemsExample;
import com.jinchi.common.domain.FireMageTestItems;
import com.jinchi.common.domain.FireMageTestItemsExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.fireMage.FireMagePpItemsDTO;
import com.jinchi.common.dto.fireMage.FireMageTestItemsDTO;
import com.jinchi.common.mapper.FireMageNumberProcessMapper;
import com.jinchi.common.mapper.FireMageNumberProductMapper;
import com.jinchi.common.mapper.FireMagePpItemsMapper;
import com.jinchi.common.mapper.FireMageTestItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireMageBatchItemsServiceImp implements FireMageBatchItemsService {

    @Autowired
    FireMageTestItemsMapper itemsMapper;
    @Autowired
    FireMagePpItemsMapper pMapper;
    @Autowired
    FireMageNumberProcessMapper processMapper;
    @Autowired
    FireMageNumberProductMapper productMapper;

    @Override
    public FireMagePpItems add(Integer processId, Integer productId, List<Long> itemIds) {
        FireMagePpItems ppItems = new FireMagePpItems();
        ppItems.setProcessCode(processId);
        ppItems.setProcessName(processMapper.selectByPrimaryKey(processId).getValue());
        ppItems.setProductCode(productId);
        ppItems.setProductName(productMapper.selectByPrimaryKey(productId).getValue());
        StringBuilder codes = new StringBuilder();
        StringBuilder names = new StringBuilder();
        if(itemIds.size() == 0){
            ppItems.setItemCodes("");
            ppItems.setItemNames("");
        }else{
            codes.append(itemIds.get(0));
            names.append(itemsMapper.selectByPrimaryKey(itemIds.get(0)).getName());
            for(int i=1;i<itemIds.size();i++){
                codes.append(","+itemIds.get(i));
                names.append(","+itemsMapper.selectByPrimaryKey(itemIds.get(i)).getName());
            }
            ppItems.setItemCodes(codes.toString());
            ppItems.setItemNames(names.toString());
        }
        pMapper.insertSelective(ppItems);
        return ppItems;
    }

    @Override
    public void delete(Long id) {
        pMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for(Long id:ids)
            delete(id);
    }

    @Override
    public FireMagePpItemsDTO detail(Long id) {
        FireMagePpItemsDTO ans = new FireMagePpItemsDTO();
        List<FireMageTestItemsDTO> itemsDTOs = new ArrayList<>();
        ans.setHead(pMapper.selectByPrimaryKey(id));
        String[] codes = ans.getHead().getItemCodes().split(",");

        List<FireMageTestItems> items = itemsMapper.selectByExample(new FireMageTestItemsExample());
        for(int i=0;i<items.size();i++){
            FireMageTestItemsDTO itemsDTO = new FireMageTestItemsDTO();
            itemsDTO.setCode(items.get(i).getCode());
            itemsDTO.setName(items.get(i).getName());
            itemsDTO.setUnit(items.get(i).getUnit());
            itemsDTO.setFlag(false);
            for(int j=0;j<codes.length;j++){
                if(items.get(i).getCode() == Long.parseLong(codes[j])) {
                    itemsDTO.setFlag(true);
                    break;
                }
            }
            itemsDTOs.add(itemsDTO);
        }
        ans.setItems(itemsDTOs);
        return ans;
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public void update(Long id, List<Long> itemIds) {
        FireMagePpItems items = pMapper.selectByPrimaryKey(id);
        Integer processId = items.getProcessCode();
        Integer productId = items.getProductCode();
        FireMagePpItemsExample example = new FireMagePpItemsExample();
        example.createCriteria().andProcessCodeEqualTo(processId).andProductCodeEqualTo(productId);
        pMapper.deleteByExample(example);
        add(processId,productId,itemIds);
    }

    @Override
    public List getAll(String condition) {
        FireMagePpItemsExample example = new FireMagePpItemsExample();
        example.createCriteria().andProcessNameLike(condition + "%");
        example.or().andProductNameLike(condition + "%");
        return pMapper.selectByExample(example);
    }
}
