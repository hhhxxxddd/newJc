package com.jc.api.entity.dto;

import com.jc.api.utils.WeightUnitConvertUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.stream.Stream;

/**
 * 用于流量统计
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class StreamDTO {
    private Float in;
    private Float out;
    private Float store = 0f;

    public void convert(String unit){
        this.in = WeightUnitConvertUtil.convertWithUnit(in+unit, WeightUnitConvertUtil.WeightUnitEnum.kg).floatValue();
        this.out = WeightUnitConvertUtil.convertWithUnit(out+unit, WeightUnitConvertUtil.WeightUnitEnum.kg).floatValue();
        this.store = WeightUnitConvertUtil.convertWithUnit(store+unit, WeightUnitConvertUtil.WeightUnitEnum.kg).floatValue();
    }

    public StreamDTO add(StreamDTO dto){
        StreamDTO ans = new StreamDTO();
        ans.setIn(this.in + dto.getIn());
        ans.setOut(this.out + dto.getOut());
        ans.setStore(this.store + dto.getStore());
        return ans;
    }
}
