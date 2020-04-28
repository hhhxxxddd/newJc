package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.Page;
import com.jinchi.common.mapper.FireItemPositionMapper;
import com.jinchi.common.mapper.FireMageTestItemsMapper;
import com.jinchi.common.mapper.FirePositionMapper;
import com.jinchi.common.mapper.FireUserPositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FirePositionServiceImpl implements FirePositionService {

    @Autowired
    FirePositionMapper positionMapper;
    @Autowired
    FireUserPositionMapper userPositionMapper;
    @Autowired
    FireItemPositionMapper itemPositionMapper;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    FireMageTestItemsMapper fireMageTestItemsMapper;

    @Override
    public FirePosition add(String name) {
        FirePosition firePosition = new FirePosition();
        firePosition.setPosition(name);
        positionMapper.insert(firePosition);
        return firePosition;
    }

    @Override
    public void delete(Integer id) {
        positionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteIds(Integer[] ids) {
        for(Integer id:ids)
            delete(id);
    }

    @Override
    public Page page(String name, Integer page, Integer size) {
        FirePositionExample example = new FirePositionExample();
        example.createCriteria().andPositionLike(name+"%");
        List<FirePosition> ans = positionMapper.selectByExample(example);
        return new Page(ans,page,size);
    }

    @Override
    public FirePosition update(FirePosition firePosition) {
        positionMapper.updateByPrimaryKey(firePosition);
        return firePosition;
    }

    @Override
    public void assign(Integer positionId, Integer[] userIds) {
        FireUserPositionExample example = new FireUserPositionExample();
        example.createCriteria().andPidEqualTo(positionId);
        userPositionMapper.deleteByExample(example);

        for(Integer userId:userIds){
            FireUserPosition userPosition = new FireUserPosition();
            userPosition.setPid(positionId);
            userPosition.setUid(userId);
            userPositionMapper.insert(userPosition);
        }
    }

    @Override
    public void assion(Integer positionId, Long[] itemIds) {
        FireItemPositionExample example = new FireItemPositionExample();
        example.createCriteria().andPidEqualTo(positionId);
        itemPositionMapper.deleteByExample(example);

        for(Long itemId:itemIds){
            FireItemPosition itemPosition = new FireItemPosition();
            itemPosition.setPid(positionId);
            itemPosition.setItemCode(itemId.intValue());
            itemPositionMapper.insert(itemPosition);
        }
    }

    @Override
    public List getUser(Integer positionId) {
        FireUserPositionExample example = new FireUserPositionExample();
        example.createCriteria().andPidEqualTo(positionId);
        List<FireUserPosition> userPositions = userPositionMapper.selectByExample(example);

        List<Map<String,Object>> ans = new ArrayList<>();
        for(int i=0;i<userPositions.size();i++){
            Map<String,Object> info = new HashMap<>();
            info.put("userCode",userPositions.get(i).getUid());
            info.put("userName",authUserService.findById(userPositions.get(i).getUid()).getName());
            ans.add(info);
        }
        return ans;
    }

    @Override
    public List getItems(Integer positionId) {
        FireItemPositionExample example = new FireItemPositionExample();
        example.createCriteria().andPidEqualTo(positionId);
        List<FireItemPosition> itemPositions = itemPositionMapper.selectByExample(example);

        List<Map> ans = new ArrayList<>();
        for(int i=0;i<itemPositions.size();i++){
            Map info = new HashMap();
            info.put("itemCode",itemPositions.get(i).getItemCode());
            info.put("itemName",fireMageTestItemsMapper.selectByPrimaryKey(itemPositions.get(i).getItemCode().longValue()).getName());
            ans.add(info);
        }
        return ans;
    }
}
