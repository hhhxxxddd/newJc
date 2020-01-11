package com.jc.api.entity.dto;

import com.jc.api.entity.po.MaterialInfo;
import com.jc.api.entity.po.MaterialStock;
import com.jc.api.entity.po.StockOutRecord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatDeliDTO {

    List<StockOutRecord> stockOutRecord;

    MaterialInfo materialInfo;

    List<MaterialStock> materialStock;
}