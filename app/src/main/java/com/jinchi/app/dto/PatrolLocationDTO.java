package com.jinchi.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PatrolLocationDTO {
    Long patrolLocationId;

    Integer locationId;

    String location;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date readCardTime;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Long getPatrolLocationId() {
        return patrolLocationId;
    }

    public void setPatrolLocationId(Long patrolLocationId) {
        this.patrolLocationId = patrolLocationId;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getReadCardTime() {
        return readCardTime;
    }

    public void setReadCardTime(Date readCardTime) {
        this.readCardTime = readCardTime;
    }
}
