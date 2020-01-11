package com.jc.api.entity.vo;

import com.jc.api.entity.po.MaterialInfo;
import com.jc.api.entity.po.MaterialType;
import com.jinchi.common.web.entity.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

/**
 * @author XudongHu
 * @className MaterialInfoVo
 * @apiNote 物料信息VO
 * @modifer
 * @since 2019/10/31日3:02
 */
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "物料信息VO")
public class MaterialInfoJointVo extends BaseVo<MaterialInfo> {
    private String id;
    private String materialName; //物料名称
    private String materialNameCode; //物料名称代号
    private List<MaterialType> completeType;  //物料完整类型链条
    private Integer niFlag;
    private Integer coFlag;
    private Integer mnFlag;
    private Integer nhFlag;
    private Integer alkaliFlag;
    private Integer autoFlag;



    public MaterialInfoJointVo (MaterialInfo materialInfo){
        this.id = materialInfo.getId();
        this.materialNameCode = materialInfo.getMaterialNameCode();
        this.materialName = materialInfo.getMaterialName();
        this.niFlag = materialInfo.getNiFlag();
        this.coFlag = materialInfo.getCoFlag();
        this.mnFlag = materialInfo.getMnFlag();
        this.nhFlag = materialInfo.getNhFlag();
        this.alkaliFlag = materialInfo.getAlkaliFlag();
        this.autoFlag = materialInfo.getAutoFlag();
    }


}
