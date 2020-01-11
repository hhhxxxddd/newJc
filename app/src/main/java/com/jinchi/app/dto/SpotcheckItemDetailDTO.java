package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-09-06 14:56
 * @description: 点检项目详情DTO
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpotcheckItemDetailDTO {
    private Long itemId;//详情id
    private String spotcheckItem;//点检项目
    private String spotcheckContent;//点检标准
    private Integer mainValues;//点检结果
    private String mainContent;//点检异常说明
    private String spotcheckAddress;//图示地址

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getSpotcheckItem() {
        return spotcheckItem;
    }

    public void setSpotcheckItem(String spotcheckItem) {
        this.spotcheckItem = spotcheckItem;
    }

    public String getSpotcheckContent() {
        return spotcheckContent;
    }

    public void setSpotcheckContent(String spotcheckContent) {
        this.spotcheckContent = spotcheckContent;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    public Integer getMainValues() {
        return mainValues;
    }

    public void setMainValues(Integer mainValues) {
        this.mainValues = mainValues;
    }

    public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }

    public String getSpotcheckAddress() {
        return spotcheckAddress;
    }

    public void setSpotcheckAddress(String spotcheckAddress) {
        this.spotcheckAddress = spotcheckAddress;
    }
}
