package com.jc.api.utils;

import com.jc.api.exception.custom.DataNotFindException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XudongHu
 * @apiNote 重量转换换算工具类
 * @className WeightUnitConvertUtil
 * @modifier
 * @since 19.11.29日22:16
 */
@Slf4j
public class WeightUnitConvertUtil {
    //单位提取正则
    private static String wordRegex = "[a-zA-z]+";

    public enum WeightUnitEnum {
        mg(),
        g(),
        kg(),
        t();
    }

    /**
     * 带单位的重量转换
     * @param weightWithUnit 重量输入,如60t
     * @param convertUnit 目标单位,如 WeightUnitEnum.kg
     * @return 转换后重量,Double类型
     */
    public static Double convertWithUnit(String weightWithUnit, WeightUnitEnum convertUnit) {
        Matcher matcher = Pattern.compile(wordRegex).matcher(weightWithUnit);
        String unit = "kg";
        while (matcher.find()) unit = matcher.group();
        //单位
        unit = unit.toLowerCase();
        //重量
        String weight = weightWithUnit.replaceAll(wordRegex, "");
        try {
            WeightUnitEnum originUnit = WeightUnitEnum.valueOf(unit);
            return convert(weight, originUnit, convertUnit);
        } catch (IllegalArgumentException e) {
            throw new DataNotFindException("无法识别单位:" + unit);
        }


    }

    public static Double convert(String weight, WeightUnitEnum originUnit, WeightUnitEnum convertUnit) {
        BigDecimal weightKg;
        BigDecimal convertTo;
        switch (originUnit.name()) {
            case "mg":
                weightKg = new BigDecimal(weight).divide(new BigDecimal(1000000));
                break;
            case "g":
                weightKg = new BigDecimal(weight).divide(new BigDecimal(1000));
                break;
            case "kg":
                weightKg = new BigDecimal(weight);
                break;
            case "t":
                weightKg = new BigDecimal(weight).multiply(new BigDecimal(1000));
                break;
            default:
                throw new IllegalStateException("无法识别的重量单位: " + originUnit.name());
        }
        switch (convertUnit.name()) {
            case "mg":
                convertTo = weightKg.multiply(new BigDecimal(1000000));
                break;
            case "g":
                convertTo = weightKg.multiply(new BigDecimal(1000));
                break;
            case "kg":
                convertTo = weightKg;
                break;
            case "t":
                convertTo = weightKg.divide(new BigDecimal(1000));
                break;
            default:
                throw new IllegalStateException("无法识别的重量单位: " + convertUnit.name());
        }

        Double value = convertTo.doubleValue();
        return value;
    }

    public static Double convert(Integer weight, WeightUnitEnum originUnit, WeightUnitEnum convertUnit) {
        return convert(weight.toString(), originUnit, convertUnit);
    }

    public static Double convert(Double weight, WeightUnitEnum originUnit, WeightUnitEnum convertUnit) {
        return convert(weight.toString(), originUnit, convertUnit);
    }
}
