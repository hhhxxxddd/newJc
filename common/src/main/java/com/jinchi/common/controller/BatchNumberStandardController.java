package com.jinchi.common.controller;

import com.jinchi.common.dto.MaterialCodeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author：XudongHu
 * @className:BatchNumberStandardController
 * @description:
 * @date:13:30 2019/3/15
 * @Modifer:
 */
@RestController
@Api(tags = "批号生成")
@RequestMapping(value = "/codeGenerate")
public class BatchNumberStandardController {
    private AtomicInteger bagNumber = new AtomicInteger(0);    // todo
    private interface Encoder {
        String encode(MaterialCodeDTO dto);
    }

    @ApiOperation(value = "批号生成")
    @GetMapping
    public String generate(MaterialCodeDTO materialCodeDTO){
        Function<MaterialCodeDTO, String> genetate = mcdto -> {
            Encoder encoder = new Encoder() {
                @Override
                public String encode(MaterialCodeDTO dto) {
                    StringBuilder result = new StringBuilder();
                    String separator = "-";
                    // how to generate the material code;
                    String batchCode = dto.getBatchCode();
                    String materialName = dto.getMaterialName();
                    String materialType = dto.getMaterialType();
                    String workShop = dto.getWorkShop();
                    String manufactureCode = dto.getManufactureCode();
                    String weight = dto.getWeight();
                    Integer bagno = bagNumber.incrementAndGet();
                    result
                            .append("MC/")
                            .append(batchCode)
                            .append(separator)
                            .append(materialName)
                            .append(separator)
                            .append(materialType)
                            .append(separator)
                            .append(workShop)
                            .append(separator)
                            .append(manufactureCode)
                            .append(separator)
                            .append(String.format("000%d", bagno))
                            .append(separator)
                            .append(weight);
                    System.out.println(result.toString());
                    return result.toString();
                }
            };

            return encoder.encode(mcdto);

        };
        return genetate.apply(materialCodeDTO);
    }
}
