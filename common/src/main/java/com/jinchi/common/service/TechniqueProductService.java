package com.jinchi.common.service;

import com.jinchi.common.domain.TechniqueBaseProductMaterial;
import com.jinchi.common.domain.TestItemsPlus;
import com.jinchi.common.dto.ProductStandardDTO;
import com.jinchi.common.dto.TechniqueProductStandardDTO;

import java.util.List;

public interface TechniqueProductService {

    void add(String productName);

    List getItemsByProductId(Integer id);

    Integer addProductStandard(Integer productId, Integer classId, Integer createUser, List<TestItemsPlus> items, String date);

    TechniqueProductStandardDTO detailByCommonBatchId(Integer commonBatchId);

    List<TechniqueProductStandardDTO> getAllProductCommonBatch();

    List<ProductStandardDTO> getAllStandardByPIdandCId(Integer productId, Integer classId,String userName,Integer page,Integer size);

    List<TechniqueBaseProductMaterial> getAllProduct();

    Integer updateByCommonBatchId(Integer commonBatchId,String effTime, List<TestItemsPlus> items);

    List getItemsByProductStandardId(Integer standardId);
}
