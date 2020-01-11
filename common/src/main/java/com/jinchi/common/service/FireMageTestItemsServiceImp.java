package com.jinchi.common.service;

import com.jinchi.common.domain.FireMagePpItems;
import com.jinchi.common.domain.FireMagePpItemsExample;
import com.jinchi.common.domain.FireMageTestItems;
import com.jinchi.common.domain.FireMageTestItemsExample;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.FireMagePpItemsMapper;
import com.jinchi.common.mapper.FireMageTestItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FireMageTestItemsServiceImp implements FireMageTestItemsService {

    @Autowired
    FireMageTestItemsMapper itemsMapper;
    @Autowired
    FireMagePpItemsMapper ppItemsMapper;

    @Override
    public FireMageTestItems add(FireMageTestItems manual) {
        itemsMapper.insertSelective(manual);
        return manual;
    }

    @Override
    public void delete(Long id) {
        itemsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for(Long id:ids)
            delete(id);
    }

    @Override
    public FireMageTestItems detail(Long id) {
        return itemsMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page page(String condition, Integer page, Integer size) {
        return new Page(getAll(condition),page,size);
    }

    @Override
    public FireMageTestItems update(FireMageTestItems manual) {
        itemsMapper.updateByPrimaryKeySelective(manual);
        return manual;
    }

    @Override
    public List getAll(String condition) {
        FireMageTestItemsExample example = new FireMageTestItemsExample();
        example.createCriteria().andNameLike(condition + "%");
        return itemsMapper.selectByExample(example);
    }

    @Override
    public List getAllByProcessByProdut(Integer processCode, Integer productCode) {
        FireMagePpItemsExample example = new FireMagePpItemsExample();
        example.createCriteria().andProcessCodeEqualTo(processCode).andProductCodeEqualTo(productCode);
        List<FireMagePpItems> ppItems = ppItemsMapper.selectByExample(example);
        if(ppItems.size() == 0)
            return null;

        FireMagePpItems ppItem = ppItems.get(0);
        List<String> itemsCodes = Arrays.asList(ppItem.getItemCodes().split(","));
        List<FireMageTestItems> ans = new ArrayList<>();
        for(int i=0;i<itemsCodes.size();i++){
            ans.add(this.detail(Long.parseLong(itemsCodes.get(i))));
        }
        return ans;
    }
}
