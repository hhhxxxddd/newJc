package com.jinchi.common.service;

import com.jinchi.common.domain.*;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.processParamer.*;
import com.jinchi.common.mapper.*;
import com.jinchi.common.model.factorypattern.CommonBatchFactory;
import com.jinchi.common.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.jinchi.common.constant.BatchTypeEnum.PROCESS_PARAMETER;

@Service
@Transactional
public class ProcessParamerServiceImp implements ProcessParamerService {

    @Autowired
    ProcessParametersListHeadMapper headMapper;
    @Autowired
    ProcessParametersHcDetailMapper hcDetailMapper;
    @Autowired
    ProcessParametersHcCommentsMapper hcCommentsMapper;
    @Autowired
    ProcessParametersHcExceptionDisposeMapper hcExceptionDisposeMapper;
    @Autowired
    ProcessParametersHcIntermediateGoodsStdMapper hcIntermediateGoodsStdMapper;
    @Autowired
    ProcessParametersJqjhDetailMapper jqjhDetailMapper;
    @Autowired
    ProcessParametersJqjhPrincipalComponentMapper jqjhPrincipalComponentMapper;
    @Autowired
    ProcessParametersXdDetailMapper xdDetailMapper;
    @Autowired
    ProcessParametersXdDevicesMapper xdDevicesMapper;
    @Autowired
    ProcessParametersLineSelectMapper lineSelectMapper;
    @Autowired
    ProcessParametersLineSelectHcMapper lineSelectHcMapper;
    @Autowired
    ProductionBatchRuleDetailMapper ruleDetailMapper;
    @Autowired
    BasicInfoPrecursorProductionLineMapper productionLineMapper;
    @Autowired
    CommonBatchNumberMapper commonBatchNumberMapper;
    @Autowired
    DataTaskRecordService dataTaskRecordService;
    @Autowired
    AuthUserService authUserService;
    @Autowired
    DeptManageService deptManageService;
    @Autowired
    DataTaskRecordMapper dataTaskRecordMapper;

    @Override
    public Integer saveOrCommit(ProcessParamerMainDTO processParamerMainDTO, Integer flag) {
        if (flag == 0) {
            save(processParamerMainDTO);
            return 0;
        }
        if (flag == 1) {
            commit(processParamerMainDTO);
            return 1;
        }
        return null;
    }

    private void save(ProcessParamerMainDTO processParamerMainDTO) {
        ProcessParametersListHead head = processParamerMainDTO.getHead();
        ProcessParametersListHead cal = headMapper.selectByPrimaryKey(head.getCode());
        if (head.getCode() != null || cal == null) {
            delete(head.getCode());
            head.setCode(null);
        }
        head.setStatusFlag(new Integer(0).byteValue());
        head.setProcessName(processParamerMainDTO.getProcessName());
        headMapper.insertSelective(head);
        Long id = head.getCode();

        if ("合成".equals(processParamerMainDTO.getProcessName())) {
            Hc hc = processParamerMainDTO.getHc();

            //工艺参数
            Gy gy = hc.getGy();
            List<ProAndLine> lines = gy.getProAndLines();
            List<ProcessParametersHcDetail> details = gy.getDetails();
            List<Long> detailIds = new ArrayList<>();
            for (int i = 0; i < details.size(); i++) {
                ProcessParametersHcDetail detail = details.get(i);
                detail.setProcessCode(id);
                detail.setProductionCode(lines.get(i).getProductClass());
                hcDetailMapper.insertSelective(detail);
                detailIds.add(detail.getCode());
            }

            //工艺参数的产线的绑定
            for (int l = 0; l < lines.size(); l++) {
                for(int k = 0;k < lines.get(l).getLines().size();k++) {
                    ProcessParametersLineSelectHc selectHc = new ProcessParametersLineSelectHc();
                    selectHc.setHcDetailCode(detailIds.get(l));
                    selectHc.setLineCode(lines.get(l).getLines().get(k));
                    lineSelectHcMapper.insertSelective(selectHc);
                }
            }

            //异常处理
            List<ProcessParametersHcExceptionDispose> hcExceptionDisposes = hc.getExceptionDisposes();
            for (int i = 0; i < hcExceptionDisposes.size(); i++) {
                hcExceptionDisposes.get(i).setProcessCode(id);
                hcExceptionDisposeMapper.insertSelective(hcExceptionDisposes.get(i));
            }

            //中间品检验
            Zjp zjp = hc.getZjp();
            List<ProcessParametersHcIntermediateGoodsStd> hcIntermediateGoodsStds = zjp.getMediate();
            for (int i = 0; i < hcIntermediateGoodsStds.size(); i++) {
                hcIntermediateGoodsStds.get(i).setProcessCode(id);
                hcIntermediateGoodsStdMapper.insertSelective(hcIntermediateGoodsStds.get(i));
            }

            //总的备注
            ProcessParametersHcComments comments = new ProcessParametersHcComments();
            comments.setProcessCode(id);
            comments.setParametersComment(gy.getMemo());
            comments.setIntermediateGoodsComment(zjp.getMemo());
            hcCommentsMapper.insertSelective(comments);

        } else if ("制液".equals(processParamerMainDTO.getProcessName())) {
            Zy zy = processParamerMainDTO.getZy();
            //主成分
            List<ProcessParametersJqjhPrincipalComponent> components = zy.getComponents();
            for (int i = 0; i < components.size(); i++) {
                components.get(i).setProcessCode(id);
                jqjhPrincipalComponentMapper.insertSelective(components.get(i));
            }
            //杂质，其他
            ProcessParametersJqjhDetail detail = zy.getDetail();
            detail.setProcessCode(id);
            detail.setProductionCode(processParamerMainDTO.getProAndLine().getProductClass());
            jqjhDetailMapper.insertSelective(detail);

            for (int i = 0; i < processParamerMainDTO.getProAndLine().getLines().size(); i++) {
                ProcessParametersLineSelect select = new ProcessParametersLineSelect();
                select.setLineCode(processParamerMainDTO.getProAndLine().getLines().get(i));
                select.setProcessCode(id);
                lineSelectMapper.insertSelective(select);
            }

        } else if ("陈化洗涤".equals(processParamerMainDTO.getProcessName())) {
            Ch ch = processParamerMainDTO.getCh();

            ProcessParametersXdDetail detail = ch.getDetail();
            detail.setProcessCode(id);
            detail.setProductionCode(processParamerMainDTO.getProAndLine().getProductClass());
            xdDetailMapper.insertSelective(detail);

            List<ProcessParametersXdDevices> devices = ch.getDevices();
            for (int i = 0; i < devices.size(); i++) {
                devices.get(i).setProcessCode(id);
                xdDevicesMapper.insertSelective(devices.get(i));
            }

            for (int i = 0; i < processParamerMainDTO.getProAndLine().getLines().size(); i++) {
                ProcessParametersLineSelect select = new ProcessParametersLineSelect();
                select.setLineCode(processParamerMainDTO.getProAndLine().getLines().get(i));
                select.setProcessCode(id);
                lineSelectMapper.insertSelective(select);
            }
        }
    }

    private void commit(ProcessParamerMainDTO processParamerMainDTO) {
        save(processParamerMainDTO);
        ProcessParametersListHead head = processParamerMainDTO.getHead();
        head.setStatusFlag(new Integer(1).byteValue());

        Integer commbatchId = send2audit(head, processParamerMainDTO.getIsUrgent(), processParamerMainDTO.getAuditId());
        //审核挂钩
        head.setApprovalProcessCode(commbatchId);
        headMapper.updateByPrimaryKeySelective(head);
    }

    @Override
    public void delete(Long id) {
        ProcessParametersXdDevicesExample example = new ProcessParametersXdDevicesExample();
        example.createCriteria().andProcessCodeEqualTo(id);
        xdDevicesMapper.deleteByExample(example);

        ProcessParametersXdDetailExample example1 = new ProcessParametersXdDetailExample();
        example.createCriteria().andProcessCodeEqualTo(id);
        xdDetailMapper.deleteByExample(example1);

        ProcessParametersJqjhPrincipalComponentExample example2 = new ProcessParametersJqjhPrincipalComponentExample();
        example2.createCriteria().andProcessCodeEqualTo(id);
        jqjhPrincipalComponentMapper.deleteByExample(example2);

        ProcessParametersJqjhDetailExample example3 = new ProcessParametersJqjhDetailExample();
        example3.createCriteria().andProcessCodeEqualTo(id);
        jqjhDetailMapper.deleteByExample(example3);

        ProcessParametersHcCommentsExample example5 = new ProcessParametersHcCommentsExample();
        example5.createCriteria().andProcessCodeEqualTo(id);
        hcCommentsMapper.deleteByExample(example5);

        ProcessParametersHcExceptionDisposeExample example6 = new ProcessParametersHcExceptionDisposeExample();
        example6.createCriteria().andProcessCodeEqualTo(id);
        hcExceptionDisposeMapper.deleteByExample(example6);

        ProcessParametersHcIntermediateGoodsStdExample example7 = new ProcessParametersHcIntermediateGoodsStdExample();
        example7.createCriteria().andProcessCodeEqualTo(id);
        hcIntermediateGoodsStdMapper.deleteByExample(example7);

        ProcessParametersLineSelectExample example8 = new ProcessParametersLineSelectExample();
        example8.createCriteria().andProcessCodeEqualTo(id);
        lineSelectMapper.deleteByExample(example8);

        ProcessParametersHcDetailExample example4 = new ProcessParametersHcDetailExample();
        example4.createCriteria().andProcessCodeEqualTo(id);
        List<ProcessParametersHcDetail> details = hcDetailMapper.selectByExample(example4);
        for (int i = 0; i < details.size(); i++) {
            ProcessParametersLineSelectHcExample example9 = new ProcessParametersLineSelectHcExample();
            example9.createCriteria().andHcDetailCodeEqualTo(details.get(i).getCode());
            lineSelectHcMapper.deleteByExample(example9);
        }
        hcDetailMapper.deleteByExample(example4);

        headMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ProcessParamerMainDTO detail(Long id) {
        ProcessParamerMainDTO ans = new ProcessParamerMainDTO();
        ProcessParametersListHead head = headMapper.selectByPrimaryKey(id);
        ans.setHead(head);
        ans.setDeptName(deptManageService.getDeptById(head.getPlantCode()).getName());
        ans.setPrepareName(authUserService.findById(head.getPreparer()).getName());

        //问题
        ProductionBatchRuleDetailExample detailExample = new ProductionBatchRuleDetailExample();
        detailExample.createCriteria().andCodeEqualTo(head.getProcessCode());
        List<ProductionBatchRuleDetail> batchRuleDetails = ruleDetailMapper.selectByExample(detailExample);
        ans.setProcessName(batchRuleDetails.size() == 0 ? null : batchRuleDetails.get(0).getRuleValue());

        Ch ch = new Ch();
        ProcessParametersXdDetailExample example = new ProcessParametersXdDetailExample();
        example.createCriteria().andProcessCodeEqualTo(id);
        List<ProcessParametersXdDetail> xdDetails = xdDetailMapper.selectByExample(example);
        ch.setDetail(xdDetails.size() == 0 ? null : xdDetails.get(0));
        ProcessParametersXdDevicesExample example1 = new ProcessParametersXdDevicesExample();
        example1.createCriteria().andProcessCodeEqualTo(id);
        ch.setDevices(xdDevicesMapper.selectByExample(example1));
        ans.setCh(ch);

        Hc hc = new Hc();
        Gy gy = new Gy();
        ProcessParametersHcDetailExample example2 = new ProcessParametersHcDetailExample();
        example2.createCriteria().andProcessCodeEqualTo(id);
        gy.setDetails(hcDetailMapper.selectByExample(example2));

        List<ProAndLine> proAndLines = new ArrayList<>();
        for (int i = 0; i < gy.getDetails().size(); i++) {
            ProAndLine line = new ProAndLine();
            List<Integer> lines = new ArrayList<>();
            List<String> lineNames = new ArrayList<>();
            Short productClass = gy.getDetails().get(i).getProductionCode();
            ProductionBatchRuleDetailExample example3 = new ProductionBatchRuleDetailExample();
            example3.createCriteria().andCodeEqualTo(productClass);
            List<ProductionBatchRuleDetail> ruleDetails = ruleDetailMapper.selectByExample(example3);
            line.setProductClass(productClass);
            line.setProductClassName(ruleDetails.size() == 0 ? null : ruleDetails.get(0).getRuleValue());

            ProcessParametersLineSelectHcExample example4 = new ProcessParametersLineSelectHcExample();
            example4.createCriteria().andHcDetailCodeEqualTo(gy.getDetails().get(i).getCode());
            List<ProcessParametersLineSelectHc> hcs = lineSelectHcMapper.selectByExample(example4);
            for (int l = 0; l < hcs.size(); l++) {
                lines.add(hcs.get(l).getLineCode());
                lineNames.add(productionLineMapper.selectByPrimaryKey(hcs.get(l).getLineCode()).getName());
            }
            line.setLineNames(lineNames);
            line.setLines(lines);
            proAndLines.add(line);
        }
        gy.setProAndLines(proAndLines);

        Zjp zjp = new Zjp();
        ProcessParametersHcIntermediateGoodsStdExample example3 = new ProcessParametersHcIntermediateGoodsStdExample();
        example3.createCriteria().andProcessCodeEqualTo(id);
        zjp.setMediate(hcIntermediateGoodsStdMapper.selectByExample(example3));

        ProcessParametersHcExceptionDisposeExample disposeExample = new ProcessParametersHcExceptionDisposeExample();
        disposeExample.createCriteria().andProcessCodeEqualTo(id);
        List<ProcessParametersHcExceptionDispose> exceptionDisposes = hcExceptionDisposeMapper.selectByExample(disposeExample);

        ProcessParametersHcCommentsExample commentsExample = new ProcessParametersHcCommentsExample();
        commentsExample.createCriteria().andProcessCodeEqualTo(id);
        List<ProcessParametersHcComments> comments = hcCommentsMapper.selectByExample(commentsExample);

        gy.setMemo(comments.size() == 0 ? null : comments.get(0).getParametersComment());
        zjp.setMemo(comments.size() == 0 ? null : comments.get(0).getIntermediateGoodsComment());
        hc.setGy(gy);
        hc.setZjp(zjp);
        hc.setExceptionDisposes(exceptionDisposes);
        ans.setHc(hc);

        Zy zy = new Zy();
        ProcessParametersJqjhDetailExample example4 = new ProcessParametersJqjhDetailExample();
        example4.createCriteria().andProcessCodeEqualTo(id);
        List<ProcessParametersJqjhDetail> jqjhDetails = jqjhDetailMapper.selectByExample(example4);
        zy.setDetail(jqjhDetails.size() == 0 ? null : jqjhDetails.get(0));

        ProcessParametersJqjhPrincipalComponentExample example5 = new ProcessParametersJqjhPrincipalComponentExample();
        example5.createCriteria().andProcessCodeEqualTo(id);
        zy.setComponents(jqjhPrincipalComponentMapper.selectByExample(example5));
        ans.setZy(zy);

        //制液的proAndLine
        ProAndLine line = new ProAndLine();
        if (head.getProcessCode() == new Integer(48).shortValue()) {
            ProcessParametersJqjhDetail detail = zy.getDetail();
            ProductionBatchRuleDetailExample example6 = new ProductionBatchRuleDetailExample();
            example6.createCriteria().andCodeEqualTo(detail.getProductionCode());
            List<ProductionBatchRuleDetail> details = ruleDetailMapper.selectByExample(example6);
            line.setProductClass(details.size() == 0 ? null : details.get(0).getCode());
            line.setProductClassName(details.size() == 0 ? null : details.get(0).getRuleValue());
            //陈化的proAndLine
        } else if (head.getProcessCode() == new Integer(50).shortValue()) {
            ProcessParametersXdDetail detail = ch.getDetail();
            ProductionBatchRuleDetailExample example6 = new ProductionBatchRuleDetailExample();
            example6.createCriteria().andCodeEqualTo(detail.getProductionCode());
            List<ProductionBatchRuleDetail> details = ruleDetailMapper.selectByExample(example6);
            line.setProductClass(details.size() == 0 ? null : details.get(0).getCode());
            line.setProductClassName(details.size() == 0 ? null : details.get(0).getRuleValue());
        }

        List<Integer> lines = new ArrayList<>();
        List<String> lineNames = new ArrayList<>();
        ProcessParametersLineSelectExample example7 = new ProcessParametersLineSelectExample();
        example7.createCriteria().andProcessCodeEqualTo(id);
        List<ProcessParametersLineSelect> selects = lineSelectMapper.selectByExample(example7);
        for (int i = 0; i < selects.size(); i++) {
            lines.add(selects.get(i).getLineCode());
            lineNames.add(productionLineMapper.selectByPrimaryKey(selects.get(i).getLineCode()).getName());
        }
        line.setLineNames(lineNames);
        line.setLines(lines);
        ans.setProAndLine(line);

        if (head.getApprovalProcessCode() != null) {
            DataTaskRecord dataTaskRecord = dataTaskRecordMapper.findByDataBatchNumberId(head.getApprovalProcessCode());
            CommonBatchNumber commonBatchNumber = commonBatchNumberMapper.byId(dataTaskRecord.getTaskBatchNumberId());
            ans.setAuditName(commonBatchNumber.getDescription());
        }
        return ans;
    }

    public Integer send2audit(ProcessParametersListHead head, Integer isUrgent, Integer auditId) {
        String batch = NumberGenerator.batchNumberGenerator(PROCESS_PARAMETER.typeCode());
        CommonBatchNumber commonBatchNumber =
                CommonBatchFactory.initialize()
                        .setCreatePersonId(head.getPreparer())
                        .setBatchNumber(batch)
                        .setIsUrgent(isUrgent)
                        .setDescription(PROCESS_PARAMETER.description())
                        .setDataType(PROCESS_PARAMETER.typeCode());
        commonBatchNumberMapper.insert(commonBatchNumber);
        //流程
        dataTaskRecordService.send2audit(commonBatchNumber.getId(), auditId, isUrgent);
        return commonBatchNumber.getId();
    }

    @Override
    public Page page(String condition, Integer status, Integer page, Integer size) {
        String sql = "select p.* from process_parameters_list_head as p,basic_info_dept as d,production_batch_rule_detail as b";
        sql += " where p.plant_code = d.code and p.process_code = b.code and p.status_flag = " + status;
        sql += " and (d.name like '" + condition + "%' or b.rule_desc like '" + condition + "%')";
        sql += " order by p.code desc";
        sql += " limit " + (page - 1) * size + "," + size;
        List<ProcessParametersListHead> heads = headMapper.selectBycondition(sql);
        sql = sql.replaceAll("p.\\*", "count(p.code)");
        Integer total = headMapper.countBycondition(sql);
        List<ProcessParamerPage> ans = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            ProcessParamerPage processParamerPage = new ProcessParamerPage();
            processParamerPage.setHead(heads.get(i));
            processParamerPage.setDeptName(deptManageService.getDeptById(heads.get(i).getPlantCode()).getName());
            processParamerPage.setPrepareName(authUserService.findById(heads.get(i).getPreparer()).getName());
            ProductionBatchRuleDetailExample example = new ProductionBatchRuleDetailExample();
            example.createCriteria().andCodeEqualTo(heads.get(i).getProcessCode());
            processParamerPage.setProcessName(ruleDetailMapper.selectByExample(example).get(0).getRuleValue());
            ans.add(processParamerPage);
        }
        Page pageInfo = new Page(ans, 1, size);
        pageInfo.setPage(page);
        pageInfo.setTotal(total);
        return pageInfo;
    }

    @Override
    public Page mixRecipe(String condition, Integer page, Integer size) {
        return new Page(mixRecipeList(condition), page, size);
    }

    @Override
    public Page compoundRecipe(String condition, Integer page, Integer size) {
        return new Page(compoundRecipeList(condition), page, size);
    }

    @Override
    public ProcessParamerMainDTO detailByBatch(Integer batchId) {
        ProcessParametersListHeadExample example = new ProcessParametersListHeadExample();
        example.createCriteria().andApprovalProcessCodeEqualTo(batchId);
        List<ProcessParametersListHead> heads = headMapper.selectByExample(example);
        if (heads.size() == 0)
            return null;
        return detail(heads.get(0).getCode());
    }

    @Override
    public void deleteByIds(Long[] ids) {
        for (Long id : ids)
            delete(id);
    }

    @Override
    public void publish(Long id) {
        ProcessParametersListHead head = headMapper.selectByPrimaryKey(id);
        head.setStatusFlag(new Integer(5).byteValue());
        headMapper.updateByPrimaryKeySelective(head);
    }

    @Override
    public List mixRecipeList(String condition) {
        ProcessParametersJqjhPrincipalComponentExample example = new ProcessParametersJqjhPrincipalComponentExample();
        example.createCriteria();
        List<ProcessParametersJqjhPrincipalComponent> components = jqjhPrincipalComponentMapper.selectByExample(example);

        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < components.size(); i++) {
            if (!ids.contains(components.get(i).getProcessCode())) {
                ids.add(components.get(i).getProcessCode());
            }
        }

        List<RecipeGoodIn> ans = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            RecipeGoodIn recipeGoodIn = new RecipeGoodIn();
            recipeGoodIn.setHead(headMapper.selectByPrimaryKey(ids.get(i)));
            ProcessParametersJqjhDetailExample example1 = new ProcessParametersJqjhDetailExample();
            example1.createCriteria().andProcessCodeEqualTo(ids.get(i));
            List<ProcessParametersJqjhDetail> details = jqjhDetailMapper.selectByExample(example1);

            recipeGoodIn.setDeptName(deptManageService.getDeptById(recipeGoodIn.getHead().getPlantCode()).getName());
            recipeGoodIn.setProcessName("净后");

            ProductionBatchRuleDetailExample example2 = new ProductionBatchRuleDetailExample();
            example2.createCriteria().andCodeEqualTo(details.get(0).getProductionCode());
            String product = ruleDetailMapper.selectByExample(example2).get(0).getRuleValue();
            recipeGoodIn.setProduct(product);

            if (product.contains(condition)) {
                ProcessParametersJqjhPrincipalComponentExample example3 = new ProcessParametersJqjhPrincipalComponentExample();
                example3.createCriteria().andProcessCodeEqualTo(ids.get(i));
                List<ProcessParametersJqjhPrincipalComponent> components1 = jqjhPrincipalComponentMapper.selectByExample(example3);
                ProcessParametersJqjhPrincipalComponent component = components1.get(components1.size() / 2);
                double ni = (new Double("" + component.getNiMax()) + new Double("" + component.getNiMin())) / 2;
                double co = (new Double("" + component.getCoMax()) + new Double("" + component.getCoMin())) / 2;
                double mn = (new Double("" + component.getMnMax()) + new Double("" + component.getMnMin())) / 2;//精度不足导致的
                recipeGoodIn.setNi(new Float(ni));
                recipeGoodIn.setCo(new Float(co));
                recipeGoodIn.setMn(new Float(mn));
                recipeGoodIn.setProduct(product);
                ans.add(recipeGoodIn);
            }
        }
        return ans;
    }

    @Override
    public List compoundRecipeList(String condition) {
        ProcessParametersListHeadExample headExample = new ProcessParametersListHeadExample();
        headExample.createCriteria().andStatusFlagEqualTo(new Integer(5).byteValue());
        List<ProcessParametersListHead> heads = headMapper.selectByExample(headExample);

        List<ProcessParametersHcDetail> details = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            ProcessParametersHcDetailExample example = new ProcessParametersHcDetailExample();
            example.createCriteria().andProcessCodeEqualTo(heads.get(i).getCode());
            details.addAll(hcDetailMapper.selectByExample(example));
        }

        List<RecipeGoodIn> ans = new ArrayList<>();
        for (int i = 0; i < details.size(); i++) {
            RecipeGoodIn recipeGoodIn = new RecipeGoodIn();
            ProductionBatchRuleDetailExample example1 = new ProductionBatchRuleDetailExample();
            example1.createCriteria().andCodeEqualTo(details.get(0).getProductionCode());
            String product = ruleDetailMapper.selectByExample(example1).get(0).getRuleValue();
            recipeGoodIn.setProduct(product);
            if (product.contains(condition)) {
                recipeGoodIn.setHead(headMapper.selectByPrimaryKey(details.get(i).getProcessCode()));
                recipeGoodIn.setProcessName("合成");
                recipeGoodIn.setDeptName(deptManageService.getDeptById(recipeGoodIn.getHead().getPlantCode()).getName());
                recipeGoodIn.setSolidContent(details.get(i).getSolidContainingContentStandard().floatValue());
                ans.add(recipeGoodIn);
            }
        }
        return ans;
    }

    @Override
    public List getProcessParam() {
        ProcessParametersListHeadExample headExample = new ProcessParametersListHeadExample();
        headExample.createCriteria().andStatusFlagEqualTo(new Integer(5).byteValue()).andProcessNameEqualTo("合成");
        List<ProcessParametersListHead> heads = headMapper.selectByExample(headExample);

        List<ProcessParametersHcDetail> details = new ArrayList<>();
        for (int i = 0; i < heads.size(); i++) {
            ProcessParametersHcDetailExample example = new ProcessParametersHcDetailExample();
            example.createCriteria().andProcessCodeEqualTo(heads.get(i).getCode());
            details.addAll(hcDetailMapper.selectByExample(example));
        }

        List<RecipeGoodIn> ans = new ArrayList<>();
        for (int i = 0; i < details.size(); i++) {
            RecipeGoodIn recipeGoodIn = new RecipeGoodIn();
            ProductionBatchRuleDetailExample example1 = new ProductionBatchRuleDetailExample();
            example1.createCriteria().andCodeEqualTo(details.get(0).getProductionCode());
            String product = ruleDetailMapper.selectByExample(example1).get(0).getRuleValue();
            recipeGoodIn.setProduct(product);
            recipeGoodIn.setHead(headMapper.selectByPrimaryKey(details.get(i).getProcessCode()));
            recipeGoodIn.setProcessName("合成");
            recipeGoodIn.setDeptName(deptManageService.getDeptById(recipeGoodIn.getHead().getPlantCode()).getName());
            recipeGoodIn.setSolidContent(details.get(i).getSolidContainingContentStandard().floatValue());
            recipeGoodIn.setNi(details.get(i).getNi());
            recipeGoodIn.setCo(details.get(i).getCo());
            recipeGoodIn.setMn(details.get(i).getMn());
            recipeGoodIn.setParamId(details.get(i).getCode());
            ans.add(recipeGoodIn);
        }
        return ans;
    }
}


