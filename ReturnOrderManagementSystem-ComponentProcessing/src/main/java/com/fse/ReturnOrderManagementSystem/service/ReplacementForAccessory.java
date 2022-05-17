package com.fse.ReturnOrderManagementSystem.service;

import org.springframework.stereotype.Service;

import com.fse.ReturnOrderManagementSystem.registry.ProcessingServiceFactory;
@Service("ACCESSORY")
public class ReplacementForAccessory  implements ProcessingServiceFactory {

	@Override
	public Long getProcessingDays() {
		// TODO Auto-generated method stub
		return (long) 2;
	}

	@Override
	public Double getProcessingCharge() {
		// TODO Auto-generated method stub
		return (double) 300;
	}

}
