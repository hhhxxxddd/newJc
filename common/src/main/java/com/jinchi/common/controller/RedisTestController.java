package com.jinchi.common.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.jinchi.common.domain.*;
import com.jinchi.common.dto.RealTimeData;
import com.jinchi.common.dto.Result;
import com.jinchi.common.mapper.*;
import com.jinchi.common.utils.RealTimeUtil;
import com.jinchi.common.utils.ResultUtil;
import com.jinchi.common.utils.jwt.JWTCompressUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "redis")
@Api(tags = "各种测试controller")
public class RedisTestController {

    @Autowired
    BasicInfoQuesAnsMapper mapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    BasicInfoPrecursorMaterialPlcMapMapper plcMapMapper;
    @Autowired
    BasicInfoPrecursorPlcAddressMapper addressMapper;
    @Autowired
    BasicInfoPrecursorMaterialDetailsMapper materialDetailsMapper;
    @Autowired
    ProductionInstrumentAddressMapper instrumentAddressMapper;
    @Autowired
    PowerCheckItemMapper powerCheckItemMapper;
    @Autowired
    PowerCheckModelDetailMapper powerCheckModelDetailMapper;

    @GetMapping
    public Result compare(){

        Long start = System.currentTimeMillis();
        BasicInfoQuesAns basicInfoQuesAns = mapper.selectByPrimaryKey(new Integer(10).longValue());
        System.out.println("数据库访问时间"+(System.currentTimeMillis() - start));

        ValueOperations<String,BasicInfoQuesAns> operations = redisTemplate.opsForValue();
        String key = "basic_info_q_a" + 10;

        boolean hasKey = redisTemplate.hasKey(key);
        Long start2 = System.currentTimeMillis();
        if(hasKey){
            BasicInfoQuesAns basicInfoQuesAns1 = operations.get(key);
            System.out.println("redis访问时间"+(System.currentTimeMillis() - start2));
            System.out.println(basicInfoQuesAns.getQuestionArea());
        }else{
            operations.set(key,basicInfoQuesAns);
            System.out.println(basicInfoQuesAns);
        }
        return ResultUtil.success();
    }


    @GetMapping(value = "getV")
    public Result getV(){
       /* BasicInfoPrecursorMaterialPlcMapExample example = new BasicInfoPrecursorMaterialPlcMapExample();
        example.createCriteria();
        List<BasicInfoPrecursorMaterialPlcMap> maps = plcMapMapper.selectByExample(example);
        for(int i=0;i<maps.size();i++){
            String add = addressMapper.selectByPrimaryKey(maps.get(i).getPlcCode()).getAddress();
            System.out.println(materialDetailsMapper.selectByPrimaryKey(maps.get(i).getMaterialCode()).getMaterialName() + " "
                    + add);
            Map<String,String> map = new HashMap<>();
            map.put("tagName",add);
            System.out.println(RealTimeUtil.timelyHttpMethod("http://192.168.190.173:10086/api/Snapshot",map));
        }*/
        return null;
    }

    @GetMapping(value = "assign")
    public Result assign(){
        BasicInfoPrecursorMaterialDetailsExample example = new BasicInfoPrecursorMaterialDetailsExample();
        example.createCriteria().andProcessCodeGreaterThanOrEqualTo(7);
        List<BasicInfoPrecursorMaterialDetails> details = materialDetailsMapper.selectByExample(example);

        BasicInfoPrecursorPlcAddressExample example1 = new BasicInfoPrecursorPlcAddressExample();
        example1.createCriteria().andCodeGreaterThanOrEqualTo(45);
        List<BasicInfoPrecursorPlcAddress> addresses = addressMapper.selectByExample(example1);

        for(int i=0;i<details.size();i++){
            for(int j=0;j<addresses.size();j++){
                if(details.get(i).getMaterialName().equals(addresses.get(j).getDescription())){
                    BasicInfoPrecursorMaterialPlcMap materialPlcMap = new BasicInfoPrecursorMaterialPlcMap();
                    materialPlcMap.setMaterialCode(details.get(i).getCode());
                    materialPlcMap.setPlcCode(addresses.get(j).getCode());
                    plcMapMapper.insertSelective(materialPlcMap);
                }
            }
        }
        return null;
    }

    @GetMapping(value = "update")
    @ApiOperation(value = "更新地址表")
    public Result update(){
        /*BasicInfoPrecursorPlcAddressExample example = new BasicInfoPrecursorPlcAddressExample();
        example.createCriteria();
        List<BasicInfoPrecursorPlcAddress> addresses = addressMapper.selectByExample(example);
        for(int i=0;i<addresses.size();i++){
            StringBuilder add = new StringBuilder(addresses.get(i).getAddress());
            add.insert(7,"y");
            addresses.get(i).setAddress(add.toString());
            addressMapper.updateByPrimaryKeySelective(addresses.get(i));
        }

        ProductionInstrumentAddressExample example1 = new ProductionInstrumentAddressExample();
        example1.createCriteria();
        List<ProductionInstrumentAddress> instrumentAddresses = instrumentAddressMapper.selectByExample(example1);
        for(int i=0;i<instrumentAddresses.size();i++){
            StringBuilder add = new StringBuilder(instrumentAddresses.get(i).getAddress());
            add.insert(7,"y");
            instrumentAddresses.get(i).setAddress(add.toString());
            instrumentAddressMapper.updateByPrimaryKeySelective(instrumentAddresses.get(i));
        }*/
        return ResultUtil.success();
    }

    @GetMapping(value ="feign")
    public String feign(@RequestParam String msg){
        System.out.println(msg);
        return msg;
    }

    @GetMapping(value = "setItemCode")
    public String setItemCode(){
        List<PowerCheckItem> items = powerCheckItemMapper.selectByExample(new PowerCheckItemExample());
        List<PowerCheckModelDetail> details = powerCheckModelDetailMapper.selectByExample(new PowerCheckModelDetailExample());
        for(int i=0;i<items.size();i++){
            for(int k=0;k<details.size();k++){
                if(details.get(k).getCheckItem().equals(items.get(i).getCheckItem())
                        && details.get(k).getCheckContent().equals(items.get(i).getCheckContent())
                        && details.get(k).getFrequency().equals(items.get(i).getFrequency())){
                    PowerCheckModelDetail detail = details.get(k);
                    detail.setItemCode(items.get(i).getCode());
                    powerCheckModelDetailMapper.updateByPrimaryKey(detail);
                }
            }
        }
        return null;
    }

}