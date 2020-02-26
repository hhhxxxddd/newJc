package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.*;
import com.jc.api.entity.dto.AuditDTO;
import com.jc.api.entity.vo.OutQueryVo;
import com.jc.api.mapper.*;
import com.jc.api.service.restservice.IFireMageOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FireMageOutService implements IFireMageOutService {
    @Autowired
    SwmsStockInventoryReallyReportsMapper reallyReportsMapper;
    @Autowired
    SwmsBasicMaterialTypeMapper typeMapper;
    @Autowired
    SwmsBasicMaterialInfoMapper infoMapper;
    @Autowired
    SwmsBasicMaterialSubTypeMapper subTypeMapper;
    @Autowired
    SwmsStockInLedgersMapper ledgersMapper;

    @Override
    public Boolean sendAudit(List<AuditDTO> mats, Integer deptCode, Integer lineCode, Integer outPoint, Integer outType, Integer isUrgent, Integer auditId) {
        return null;
    }

    @Override
    public Page getByPage(Integer deptCode, String date, Page page) {
        return null;
    }

    @Override
    public OutQueryVo getData(Integer type, Integer subType, Integer matId, Integer supplierId) {
        OutQueryVo ans = new OutQueryVo();
        QueryWrapper<SwmsStockInventoryReallyReports> reallyReportsQueryWrapper = new QueryWrapper<>();
        reallyReportsQueryWrapper
                .eq(type!=null,"material_type_id",type)
                .eq(subType!=null,"material_sub_type_id",subType)
                .eq(matId!=null,"material_name_code",matId)
                .eq(supplierId!=null,"material_supplier_code",supplierId);
        List<SwmsStockInventoryReallyReports> reallyReports = reallyReportsMapper.selectList(reallyReportsQueryWrapper);

        //求和
        Map<String,SwmsStockInventoryReallyReports> map = new HashMap<>();
        for(int i=0;i<reallyReports.size();i++){
            SwmsStockInventoryReallyReports temp = reallyReports.get(i);
            if (!map.containsKey(temp.getMaterialNameCode()+"")){
                map.put(temp.getMaterialNameCode()+"",temp);
            }else{
                SwmsStockInventoryReallyReports reports = map.get(temp.getMaterialNameCode()+"");
                reports.setRealWeight(reports.getRealWeight() + temp.getRealWeight());
                reports.setUsefulWeight(reports.getUsefulWeight() + temp.getUsefulWeight());
                map.put(temp.getMaterialNameCode()+"",reports);
            }
        }

        List<SwmsStockInventoryReallyReports> reports = new ArrayList<>();
        map.forEach((k,v)->reports.add(v));
        ans.setUps(reports);

        IPage<SwmsStockInLedgers> page = new Page<SwmsStockInLedgers>();
        if(reports.size() != 0){
            Integer mat = reports.get(0).getMaterialNameCode();
            QueryWrapper<SwmsStockInLedgers> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("material_name_code",mat);
            page = ledgersMapper.selectPage(new Page<SwmsStockInLedgers>(),queryWrapper);
        }
        ans.setDetails(page);
        return ans;
    }

    @Override
    public OutQueryVo getDataByMatid(Integer matId, Page page) {
        OutQueryVo ans = new OutQueryVo();
        QueryWrapper<SwmsStockInLedgers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("material_name_code",matId);
        ans.setDetails(ledgersMapper.selectPage(page,queryWrapper));
        return ans;
    }
}
