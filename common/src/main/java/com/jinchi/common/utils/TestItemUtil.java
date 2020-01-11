package com.jinchi.common.utils;

import com.jinchi.common.domain.TestItem;
import com.jinchi.common.mapper.TestItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：XudongHu
 * @className:TestItemUtil
 * @description:
 * @date:12:17 2019/4/3
 * @Modifer:
 */
@Component
public class TestItemUtil {
    private static final Logger logger = LoggerFactory.getLogger(TestItemUtil.class);
    @Autowired
    private TestItemMapper testItemMapper;

    /**
     * 将形如 1，2，3，4，5，6，的id组成的ids字符串转换为 名称
     *
     * @param idsString
     * @return
     */
    public String convertItemNames(String idsString) {
        StringBuffer testIdsString = new StringBuffer(idsString);
        StringBuffer testItemsString = new StringBuffer();
        logger.info("==>解析检测项目");
        for (String idString : testIdsString.toString().replaceAll("[^0-9]", ",").split(",")) {
            if (idString.length() > 0) {
                Integer itemId = Integer.parseInt(idString);
                TestItem testItem = testItemMapper.find(itemId);
                String nameString = testItem == null ? " " : testItem.getName();
                logger.info("id:{} 名称为:{}", itemId, nameString);
                testItemsString.append(nameString + ",");
            }
        }
        if(testIdsString.length()>0) testIdsString.deleteCharAt(testIdsString.length()-1);

        return testItemsString.toString();
    }

}
