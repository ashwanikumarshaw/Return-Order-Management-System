package com.fse.ReturnOrderManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fse.ReturnOrderManagementSystem.dto.ProcessRequest;
import com.fse.ReturnOrderManagementSystem.dto.ProcessResponse;
import com.fse.ReturnOrderManagementSystem.entity.DefectiveComponentDetail;
import com.fse.ReturnOrderManagementSystem.repository.DefectiveComponentRepository;
import com.fse.ReturnOrderManagementSystem.service.ComponentProcessingService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/return")
public class ComponentProcessingController {
	@Autowired
	private ComponentProcessingService componentProcessingService;

	@Autowired
	private DefectiveComponentRepository defectiveComponentRepository;

	@PostMapping("/ProcessDetail")
	public ProcessResponse processDetail(@RequestHeader("Authorization") String token,
			@RequestBody ProcessRequest processRequest) throws Exception {

		if (componentProcessingService.Authenticate(token, processRequest.getUserName()))
			return componentProcessingService.processReturnRequest(processRequest);
		else
			throw new Exception("::: UNAUTHORIZED USER :::");
	}

	@PostMapping("/CompleteProcessing/{requestId}/{cardNumber}/{creditLimit}/{processingCharge}")
	public List<String> paymentForReturnRequest(@RequestHeader("Authorization") String token,
			@PathVariable("requestId") long requestId, @PathVariable("cardNumber") long cardNumber,
			@PathVariable("creditLimit") long creditLimit, @PathVariable("processingCharge") double processingCharge)
			throws Exception {

		DefectiveComponentDetail defectiveComponentDetail = defectiveComponentRepository.findById(requestId)
				.orElseThrow();

		if (componentProcessingService.Authenticate(token, defectiveComponentDetail.getUserName()))
			return componentProcessingService.makePayment(requestId, cardNumber, creditLimit, processingCharge);
		else
			throw new Exception("::: UNAUTHORIZED USER :::");
	}
	@GetMapping("/ProcessDetail/{userName}")
	public List<DefectiveComponentDetail> orders(@RequestHeader("Authorization") String token,
												 @PathVariable("userName") String userName) throws Exception {

		if (componentProcessingService.Authenticate(token,userName))
			return defectiveComponentRepository.findAllByUserName(userName);
		else
			throw new Exception("::: UNAUTHORIZED USER :::");
	}
}
