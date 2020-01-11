package com.jinchi.common.service;

import com.jinchi.common.domain.ProductStorageStatisticDataDetails;
import com.jinchi.common.domain.ProductStorageStatisticHead;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.ProductStorageDetailDTO;
import com.jinchi.common.dto.ProductStorageEditDTO;

import java.util.List;
import java.util.Map;

public interface ProductStorageService {

    void addUse(Long headId,String productName,Long batchId,Float ni,Float co,Float mn,Float weight);

    List getAllBatch();

    Page getInData(Long headId,Integer page,Integer size);

    Object addConfirm(ProductStorageStatisticHead head);

    String saveOrCommit(Long id,List<ProductStorageStatisticDataDetails> details,Integer flag);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    Page pageUmCommit(ProductStorageStatisticHead head,Integer page,Integer size);

    Page pageCommit(ProductStorageStatisticHead head,Integer page,Integer size);

    ProductStorageDetailDTO detail(Integer id);

    Map stastaicByLine(ProductStorageStatisticHead head);

    List periodCur(Integer lineId, ProductStorageStatisticHead head);

    List lineCur(ProductStorageStatisticHead head,Integer[] lines);

    Map nextPeriod(Integer periodId);

    ProductStorageEditDTO edit(Long id);

    List getDate(Integer periodId);

    List getPeriodAndTime(Integer periodId);

    Page defaultPage();

}
