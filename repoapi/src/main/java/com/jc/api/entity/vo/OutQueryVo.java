package com.jc.api.entity.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jc.api.entity.SwmsStockInventoryReallyReports;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "火法，湿法出库列表查询vo")
public class OutQueryVo {

    List<SwmsStockInventoryReallyReports> ups;

    List details;

}
