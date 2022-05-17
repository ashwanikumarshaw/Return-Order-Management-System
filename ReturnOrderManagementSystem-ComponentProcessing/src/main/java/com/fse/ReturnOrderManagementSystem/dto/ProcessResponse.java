package com.fse.ReturnOrderManagementSystem.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data

@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class ProcessResponse {

	private Long requestId;	
	private double processingCharge;
    private double packageAndDeliveryCharge;
    private Date dateOfDelivery;
}
