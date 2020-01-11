package com.jinchi.common.service;

import com.jinchi.common.domain.FireMageLabelItems;
import com.jinchi.common.domain.FireMageLabelItemsExample;
import com.jinchi.common.domain.FireMageTestItems;
import com.jinchi.common.domain.FireMageTestItemsExample;
import com.jinchi.common.dto.fireMage.FireMageTestItemsDTO;
import com.jinchi.common.mapper.FireMageLabelItemsMapper;
import com.jinchi.common.mapper.FireMageTestItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireMageLabelServiceImp implements FireMageLabelService {
    @Autowired
    FireMageLabelItemsMapper labelItemsMapper;
    @Autowired
    FireMageTestItemsMapper itemsMapper;

    @Override
    public FireMageLabelItems add(List<Long> items) {
        FireMageLabelItems labelItems = new FireMageLabelItems();
        StringBuilder codes = new StringBuilder();
        StringBuilder names = new StringBuilder();
        if(items.size() == 0){
            labelItems.setItemCodes("");
            labelItems.setItemNames("");
        }else{
            codes.append(items.get(0));
            names.append(itemsMapper.selectByPrimaryKey(items.get(0)).getName());
            for(int i=1;i<items.size();i++){
                codes.append(","+items.get(i));
                names.append(","+itemsMapper.selectByPrimaryKey(items.get(i)).getName());
            }
            labelItems.setItemCodes(codes.toString());
            labelItems.setItemNames(names.toString());
        }
        labelItemsMapper.insertSelective(labelItems);
        return labelItems;
    }

    @Override
    public void delete(Long id) {
        labelItemsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for(Long id:ids)
            delete(id);
    }

    @Override
    public List<FireMageTestItemsDTO> detail(Long id) {
        List<FireMageTestItemsDTO> ans = new ArrayList<>();
        List<FireMageTestItems> testItems = itemsMapper.selectByExample(new FireMageTestItemsExample());

        FireMageLabelItems items = labelItemsMapper.selectByPrimaryKey(id);
        String[] codes = items.getItemCodes().split(",");

        for(int i=0;i<testItems.size();i++){
            FireMageTestItemsDTO itemsDTO = new FireMageTestItemsDTO();
            itemsDTO.setCode(testItems.get(i).getCode());
            itemsDTO.setFlag(false);
            itemsDTO.setUnit(testItems.get(i).getUnit());
            itemsDTO.setName(testItems.get(i).getName());
            for(int j=0;j<codes.length;j++){
                if(testItems.get(i).getCode() == Long.parseLong(codes[j])){
                    itemsDTO.setFlag(true);
                    break;
                }
            }
            ans.add(itemsDTO);
        }
        return ans;
    }

    @Override
    public void update(Long id, List<Long> items) {
        delete(id);
        add(items);
    }

    @Override
    public List getAll(String condition) {
        return labelItemsMapper.selectByExample(new FireMageLabelItemsExample());
    }
}
