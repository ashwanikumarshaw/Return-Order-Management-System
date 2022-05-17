package com.fse.ReturnOrderManagementSystem.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fse.ReturnOrderManagementSystem.dto.ProcessRequest;
import com.fse.ReturnOrderManagementSystem.dto.ProcessResponse;
import com.fse.ReturnOrderManagementSystem.entity.DefectiveComponentDetail;
import com.fse.ReturnOrderManagementSystem.registry.ProcessingServiceFactory;
import com.fse.ReturnOrderManagementSystem.repository.DefectiveComponentRepository;
import com.fse.ReturnOrderManagementSystem.util.ComponentType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ComponentProcessingService {

	@Autowired
	private DefectiveComponentRepository defectiveComponentRepository;

	@Autowired
	private BeanFactory beanFactory;

	public ProcessResponse processReturnRequest(ProcessRequest processRequest) {

		DefectiveComponentDetail defectiveComponentDetail = new DefectiveComponentDetail();
		BeanUtils.copyProperties(processRequest, defectiveComponentDetail);

		log.debug(defectiveComponentDetail.toString());
		
		defectiveComponentDetail = calculateProsessing(defectiveComponentDetail);

		
		DefectiveComponentDetail returnRes = defectiveComponentRepository.save(defectiveComponentDetail);

		ProcessResponse processResponses = new ProcessResponse();
		BeanUtils.copyProperties(returnRes, processResponses);

		return processResponses;

	}

	private DefectiveComponentDetail calculateProsessing(DefectiveComponentDetail defectiveComponentDetail) {

		ProcessingServiceFactory processingService = beanFactory
				.getBean(defectiveComponentDetail.getComponentType().toString(), ProcessingServiceFactory.class);

		long processingDays = processingService.getProcessingDays();
		double processingCharge = processingService.getProcessingCharge();

		log.debug("processingCharge before :: " + processingCharge + " IsPriorityRequest ::  "
				+ defectiveComponentDetail.getIsPriorityRequest());

		if (defectiveComponentDetail.getComponentType().equals(ComponentType.INTEGRAL)
				&& defectiveComponentDetail.getIsPriorityRequest()) {

			processingCharge += 200;
			processingDays-=2;
		}

		log.debug("processingCharge after :: " + processingCharge);

		// Set ProcessingCharge
		defectiveComponentDetail.setProcessingCharge(processingCharge);
		LocalDate date = LocalDate.now().plusDays(processingDays);
		// Set DateOfDelivery
		defectiveComponentDetail.setDateOfDelivery(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		// Set PackageAndDeliveryCharge
		defectiveComponentDetail.setPackageAndDeliveryCharge(packagingAndDeliveryCharge(
				defectiveComponentDetail.getComponentType(), defectiveComponentDetail.getQuantity()));

		return defectiveComponentDetail;
	}

	public List<String> makePayment(long requestId, long cardNumber, long creditLimit, double processingCharge) {
		List<String> response=new ArrayList();
		log.info("Payment");
		DefectiveComponentDetail request = defectiveComponentRepository.findById(requestId).orElseThrow();
		if(creditLimit > processingCharge) {
			request.setPaid(true);
			request.setCardNumber(cardNumber);
			defectiveComponentRepository.save(request);
			response.add("SUCCESS");
		} else{response.add("FAILED");
		}
		return response ;
	}

	@Autowired
	private RestTemplate restTemplate;

	public double packagingAndDeliveryCharge(ComponentType componentType, Long quantity) {

		return restTemplate.getForObject(
				"http://PACKAGING-AND-DELIVERY/GetPackagingDeliveryCharge/" + componentType + "/" + quantity,
				Double.class);

	}

	public boolean Authenticate(String token, String userName) throws Exception {
		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		headers.set("Authorization", token);
		ResponseEntity<String> department = restTemplate.exchange(
				"http://AUTHORIZATION-SERVICE/auth/validate?userName=" + userName, HttpMethod.GET, entity,
				String.class);

		if (!department.getStatusCode().toString().contains("200"))
			log.info("Authorization failed statuscode - " + department.getStatusCode());
		if (department.getBody().equalsIgnoreCase("true"))
			return true;
		else
			return false;
	}
}
