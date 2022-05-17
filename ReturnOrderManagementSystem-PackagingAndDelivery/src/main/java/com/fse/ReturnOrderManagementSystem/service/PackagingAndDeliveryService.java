package com.fse.ReturnOrderManagementSystem.service;

import org.springframework.stereotype.Service;

import com.fse.ReturnOrderManagementSystem.exception.NotFoundException;
import com.fse.ReturnOrderManagementSystem.util.ComponentType;

@Service
public class PackagingAndDeliveryService {

	 private static final double PROTECTIVE_SHEATH = 50;
	    private static final double PACKAGING_ACCESSORY = 50;
	    private static final double PACKAGING_INTEGRAL = 100;

	private static final double DELIVERY_INTEGRAL = 200;
	private static final double DELIVERY_ACCESSORY = 100;

	public double getCharge(ComponentType componentType, Long count) {
	        if (count <= 0) {
	            throw new IllegalArgumentException("count must be greater than zero");
	        }

	        if (componentType.equals(ComponentType.ACCESSORY)) {
	            return (PACKAGING_ACCESSORY+ DELIVERY_ACCESSORY + PROTECTIVE_SHEATH) * count;
	        } else if (componentType.equals(ComponentType.INTEGRAL))
	            return (PACKAGING_INTEGRAL+ DELIVERY_INTEGRAL + PROTECTIVE_SHEATH) * count;
	        else
	        	throw new NotFoundException("There is no delivery charge with this component type");
	    }

}
