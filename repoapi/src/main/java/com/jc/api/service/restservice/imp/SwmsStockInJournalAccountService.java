package com.jc.api.service.restservice.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jc.api.entity.SwmsStockInJournalAccount;
import com.jc.api.mapper.SwmsStockInJournalAccountMapper;
import com.jc.api.service.restservice.ISwmsStockInJournalAccountService;
import com.jc.api.service.restservice.ISwmsStockInLedgersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author XudongHu
 * @apiNote 入库流水实现类
 * @className SwmsStockInJournalAccountService
 * @modifier
 * @since 20.1.12日16:32
 */
@Service
@Slf4j
public class SwmsStockInJournalAccountService implements ISwmsStockInJournalAccountService {
    @Autowired
    private SwmsStockInJournalAccountMapper swmsStockInJournalAccountMapper;
    @Autowired
    private ISwmsStockInLedgersService iSwmsStockInLedgersService;

    /**
     *
     * @param materialCode 物料编码
     * @param createPerson 创建人
     * @return
     */
    @Override
    public Boolean insert(String materialCode, String createPerson) {
        log.info("入库开始============================>物料编码:" + materialCode);
        SwmsStockInJournalAccount entity = new SwmsStockInJournalAccount();
        entity
                .setMaterialCode(materialCode)
                .setCreatedPerson(createPerson)
                .setCreatedTime(new Date())
                .setFlag(false);
        boolean success = swmsStockInJournalAccountMapper.insert(entity) > 0;
        if (success) {
            log.info(entity.toString());
            log.info("入库操作成功:===============================>入库结束");
            //异步存入入库台账
            iSwmsStockInLedgersService.parsingAndInsert(entity.getId());
        } else {
            log.error("入库操作失败:==============================>数据丢失");
            log.error(entity.toString());
        }
        return success;
    }

    /**
     * 查询所有 - 名称模糊
     * @param materialCode
     * @return
     */
    @Override
    public List<SwmsStockInJournalAccount> getAll(String materialCode) {
        QueryWrapper<SwmsStockInJournalAccount> byMaterialCode = new QueryWrapper<>();
        byMaterialCode.likeRight("material_code",materialCode);
        return swmsStockInJournalAccountMapper.selectList(byMaterialCode);
    }

    /**
     * 查询所有 - 名称模糊/分页
     * @param page
     * @param materialCode
     * @return
     */
    @Override
    public IPage getAllByPage(Page page, String materialCode) {
        QueryWrapper<SwmsStockInJournalAccount> byMaterialCode = new QueryWrapper<>();
        byMaterialCode.likeRight("material_code",materialCode);
        return swmsStockInJournalAccountMapper.selectPage(page,byMaterialCode);
    }
}
