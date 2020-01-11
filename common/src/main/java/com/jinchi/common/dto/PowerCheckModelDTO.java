package com.jinchi.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jinchi.common.domain.PowerCheckModel;
import com.jinchi.common.domain.PowerCheckModelDetail;

import java.util.List;

/**
 * @author: LiuTaoYi
 * @create_time: 2019-12-21 16:36
 * @description:
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PowerCheckModelDTO {
    String siteName;

    PowerCheckModel model;

    List<PowerCheckModelDetail> details;

    public PowerCheckModel getModel() {
        return model;
    }

    public void setModel(PowerCheckModel model) {
        this.model = model;
    }

    public List<PowerCheckModelDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PowerCheckModelDetail> details) {
        this.details = details;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
