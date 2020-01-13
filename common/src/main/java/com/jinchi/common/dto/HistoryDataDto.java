package com.jinchi.common.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoryDataDto {

	@JsonProperty("Time")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss.SSS" , timezone = "GMT+8")
	private Date time;
	
	@JsonProperty("Value")
	private String value;
	
	@JsonProperty("Ms")
	private Integer ms;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getMs() {
		return ms;
	}

	public void setMs(Integer ms) {
		this.ms = ms;
	}
	
	
	
	
}
