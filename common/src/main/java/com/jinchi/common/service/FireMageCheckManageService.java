package com.jinchi.common.service;

import com.jinchi.common.domain.FireMageDetectInfo;
import com.jinchi.common.dto.Page;
import com.jinchi.common.dto.fireMage.FireMageDetectInfoDTO;
import com.jinchi.common.dto.fireMage.FireMageTestItemsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FireMageCheckManageService {

    void add(List<FireMageDetectInfo> infos, List<Long> ids);

    Boolean check(String batch);

    Page checkPage(String condition, String flag, Integer page, Integer size);

    FireMageDetectInfoDTO checkDetail(Long id);

    void delete(Long id);

    void deleteByIds(Long[] ids);

    List printLabels(Long id);

    void acceptOrReject(Long id,Integer flag,String reason);

    Page collectionPage(String time,Integer page,Integer size);

    List getByProcessByProduct(Integer process,Integer product,String time);

    String export(List<String> batches,List<String> itemNames);

    String importMethod(MultipartFile excelFile,List<Long> items) throws IOException;

    String reExport(List<String> batches);

    String reImport(MultipartFile excel) throws IOException;

    Page rePage(Integer deptCode, String process, String product, String date,Integer page, Integer size);

    FireMageDetectInfoDTO reDetail(Long id);

    void reUpdate(Long id,List<Long> itemsCodes,List<Float> values);
}