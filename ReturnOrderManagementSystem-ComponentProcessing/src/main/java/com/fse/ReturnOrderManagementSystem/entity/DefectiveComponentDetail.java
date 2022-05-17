package com.fse.ReturnOrderManagementSystem.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fse.ReturnOrderManagementSystem.util.ComponentType;

import lombok.Data;

@Data
@Entity
@Table(name = "DEFECTIVE_COMPONENT_DETAIL")
public class DefectiveComponentDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long requestId;
	private String userName;
	private Long contactNumber;

	@Enumerated(EnumType.STRING)
	private ComponentType componentType;

	private String componentName;
	private Long quantity;

	@Column(nullable = true, unique = false)
	private Double processingCharge;
	@Column(nullable = true, unique = false)
	private Double packageAndDeliveryCharge;
	@Column(nullable = true, unique = false)
	private Date dateOfDelivery;

	private Long cardNumber;
	@Column(columnDefinition = "boolean default false")
	private Boolean isPriorityRequest;
	@Column(columnDefinition = "boolean default false")
	private boolean isPaid;

}
