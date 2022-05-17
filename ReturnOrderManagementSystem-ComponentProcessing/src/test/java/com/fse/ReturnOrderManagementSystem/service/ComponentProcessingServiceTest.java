package com.fse.ReturnOrderManagementSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fse.ReturnOrderManagementSystem.dto.ProcessRequest;
import com.fse.ReturnOrderManagementSystem.entity.DefectiveComponentDetail;
import com.fse.ReturnOrderManagementSystem.repository.DefectiveComponentRepository;
import com.fse.ReturnOrderManagementSystem.util.ComponentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {ComponentProcessingService.class})
@ExtendWith(SpringExtension.class)
class ComponentProcessingServiceTest {
    @MockBean
    private BeanFactory beanFactory;

    @Autowired
    private ComponentProcessingService componentProcessingService;

    @MockBean
    private DefectiveComponentRepository defectiveComponentRepository;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link ComponentProcessingService#processReturnRequest(ProcessRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testProcessReturnRequest() {
     this.componentProcessingService.processReturnRequest(new ProcessRequest());
    }



    /**
     * Method under test: {@link ComponentProcessingService#makePayment(long, long, long, double)}
     */
    @Test
    void testMakePayment() {
        DefectiveComponentDetail defectiveComponentDetail = new DefectiveComponentDetail();
        defectiveComponentDetail.setCardNumber(1L);
        defectiveComponentDetail.setComponentName("Component Name");
        defectiveComponentDetail.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail.setContactNumber(1L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail.setDateOfDelivery(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail.setIsPriorityRequest(true);
        defectiveComponentDetail.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail.setPaid(true);
        defectiveComponentDetail.setProcessingCharge(10.0d);
        defectiveComponentDetail.setQuantity(1L);
        defectiveComponentDetail.setRequestId(123L);
        defectiveComponentDetail.setUserName("janedoe");
        Optional<DefectiveComponentDetail> ofResult = Optional.of(defectiveComponentDetail);
        when(this.defectiveComponentRepository.findById((Long) any())).thenReturn(ofResult);
        List<String> actualMakePaymentResult = this.componentProcessingService.makePayment(123L, 1L, 1L, 10.0d);
        assertEquals(1, actualMakePaymentResult.size());
        assertEquals("FAILED", actualMakePaymentResult.get(0));
        verify(this.defectiveComponentRepository).findById((Long) any());
    }


    /**
     * Method under test: {@link ComponentProcessingService#makePayment(long, long, long, double)}
     */
    @Test
    void testMakePayment2() {
        DefectiveComponentDetail defectiveComponentDetail = new DefectiveComponentDetail();
        defectiveComponentDetail.setCardNumber(1L);
        defectiveComponentDetail.setComponentName("Component Name");
        defectiveComponentDetail.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail.setContactNumber(1L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail.setDateOfDelivery(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail.setIsPriorityRequest(true);
        defectiveComponentDetail.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail.setPaid(true);
        defectiveComponentDetail.setProcessingCharge(10.0d);
        defectiveComponentDetail.setQuantity(1L);
        defectiveComponentDetail.setRequestId(123L);
        defectiveComponentDetail.setUserName("janedoe");
        Optional<DefectiveComponentDetail> ofResult = Optional.of(defectiveComponentDetail);

        DefectiveComponentDetail defectiveComponentDetail1 = new DefectiveComponentDetail();
        defectiveComponentDetail1.setCardNumber(1L);
        defectiveComponentDetail1.setComponentName("Component Name");
        defectiveComponentDetail1.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail1.setContactNumber(1L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail1.setDateOfDelivery(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail1.setIsPriorityRequest(true);
        defectiveComponentDetail1.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail1.setPaid(true);
        defectiveComponentDetail1.setProcessingCharge(10.0d);
        defectiveComponentDetail1.setQuantity(1L);
        defectiveComponentDetail1.setRequestId(123L);
        defectiveComponentDetail1.setUserName("janedoe");
        when(this.defectiveComponentRepository.save((DefectiveComponentDetail) any()))
                .thenReturn(defectiveComponentDetail1);
        when(this.defectiveComponentRepository.findById((Long) any())).thenReturn(ofResult);
        List<String> actualMakePaymentResult = this.componentProcessingService.makePayment(123L, 1L, Long.MAX_VALUE, 10.0d);
        assertEquals(1, actualMakePaymentResult.size());
        assertEquals("SUCCESS", actualMakePaymentResult.get(0));
        verify(this.defectiveComponentRepository).save((DefectiveComponentDetail) any());
        verify(this.defectiveComponentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ComponentProcessingService#makePayment(long, long, long, double)}
     */
    @Test
    void testMakePayment3() {
        DefectiveComponentDetail defectiveComponentDetail = mock(DefectiveComponentDetail.class);
        doNothing().when(defectiveComponentDetail).setCardNumber((Long) any());
        doNothing().when(defectiveComponentDetail).setComponentName((String) any());
        doNothing().when(defectiveComponentDetail).setComponentType((ComponentType) any());
        doNothing().when(defectiveComponentDetail).setContactNumber((Long) any());
        doNothing().when(defectiveComponentDetail).setDateOfDelivery((Date) any());
        doNothing().when(defectiveComponentDetail).setIsPriorityRequest((Boolean) any());
        doNothing().when(defectiveComponentDetail).setPackageAndDeliveryCharge((Double) any());
        doNothing().when(defectiveComponentDetail).setPaid(anyBoolean());
        doNothing().when(defectiveComponentDetail).setProcessingCharge((Double) any());
        doNothing().when(defectiveComponentDetail).setQuantity((Long) any());
        doNothing().when(defectiveComponentDetail).setRequestId(anyLong());
        doNothing().when(defectiveComponentDetail).setUserName((String) any());
        defectiveComponentDetail.setCardNumber(1L);
        defectiveComponentDetail.setComponentName("Component Name");
        defectiveComponentDetail.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail.setContactNumber(1L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail.setDateOfDelivery(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail.setIsPriorityRequest(true);
        defectiveComponentDetail.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail.setPaid(true);
        defectiveComponentDetail.setProcessingCharge(10.0d);
        defectiveComponentDetail.setQuantity(1L);
        defectiveComponentDetail.setRequestId(123L);
        defectiveComponentDetail.setUserName("janedoe");
        Optional<DefectiveComponentDetail> ofResult = Optional.of(defectiveComponentDetail);

        DefectiveComponentDetail defectiveComponentDetail1 = new DefectiveComponentDetail();
        defectiveComponentDetail1.setCardNumber(1L);
        defectiveComponentDetail1.setComponentName("Component Name");
        defectiveComponentDetail1.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail1.setContactNumber(1L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail1.setDateOfDelivery(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail1.setIsPriorityRequest(true);
        defectiveComponentDetail1.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail1.setPaid(true);
        defectiveComponentDetail1.setProcessingCharge(10.0d);
        defectiveComponentDetail1.setQuantity(1L);
        defectiveComponentDetail1.setRequestId(123L);
        defectiveComponentDetail1.setUserName("janedoe");
        when(this.defectiveComponentRepository.save((DefectiveComponentDetail) any()))
                .thenReturn(defectiveComponentDetail1);
        when(this.defectiveComponentRepository.findById((Long) any())).thenReturn(ofResult);
        List<String> actualMakePaymentResult = this.componentProcessingService.makePayment(123L, 1L, Long.MAX_VALUE, 10.0d);
        assertEquals(1, actualMakePaymentResult.size());
        assertEquals("SUCCESS", actualMakePaymentResult.get(0));
        verify(this.defectiveComponentRepository).save((DefectiveComponentDetail) any());
        verify(this.defectiveComponentRepository).findById((Long) any());
        verify(defectiveComponentDetail, atLeast(1)).setCardNumber((Long) any());
        verify(defectiveComponentDetail).setComponentName((String) any());
        verify(defectiveComponentDetail).setComponentType((ComponentType) any());
        verify(defectiveComponentDetail).setContactNumber((Long) any());
        verify(defectiveComponentDetail).setDateOfDelivery((Date) any());
        verify(defectiveComponentDetail).setIsPriorityRequest((Boolean) any());
        verify(defectiveComponentDetail).setPackageAndDeliveryCharge((Double) any());
        verify(defectiveComponentDetail, atLeast(1)).setPaid(anyBoolean());
        verify(defectiveComponentDetail).setProcessingCharge((Double) any());
        verify(defectiveComponentDetail).setQuantity((Long) any());
        verify(defectiveComponentDetail).setRequestId(anyLong());
        verify(defectiveComponentDetail).setUserName((String) any());
    }

    /**
     * Method under test: {@link ComponentProcessingService#makePayment(long, long, long, double)}
     */
    @Test
    void testMakePayment4() {
        DefectiveComponentDetail defectiveComponentDetail = new DefectiveComponentDetail();
        defectiveComponentDetail.setCardNumber(1L);
        defectiveComponentDetail.setComponentName("Component Name");
        defectiveComponentDetail.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail.setContactNumber(1L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail.setDateOfDelivery(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail.setIsPriorityRequest(true);
        defectiveComponentDetail.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail.setPaid(true);
        defectiveComponentDetail.setProcessingCharge(10.0d);
        defectiveComponentDetail.setQuantity(1L);
        defectiveComponentDetail.setRequestId(123L);
        defectiveComponentDetail.setUserName("janedoe");
        Optional<DefectiveComponentDetail> ofResult = Optional.of(defectiveComponentDetail);
        when(this.defectiveComponentRepository.findById((Long) any())).thenReturn(ofResult);
        List<String> actualMakePaymentResult = this.componentProcessingService.makePayment(123L, 1L, 1L, 10.0d);
        assertEquals(1, actualMakePaymentResult.size());
        assertEquals("FAILED", actualMakePaymentResult.get(0));
        verify(this.defectiveComponentRepository).findById((Long) any());
    }



    /**
     * Method under test: {@link ComponentProcessingService#makePayment(long, long, long, double)}
     */
    @Test
    void testMakePayment5() {
        DefectiveComponentDetail defectiveComponentDetail = new DefectiveComponentDetail();
        defectiveComponentDetail.setCardNumber(1L);
        defectiveComponentDetail.setComponentName("Component Name");
        defectiveComponentDetail.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail.setContactNumber(1L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail.setDateOfDelivery(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail.setIsPriorityRequest(true);
        defectiveComponentDetail.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail.setPaid(true);
        defectiveComponentDetail.setProcessingCharge(10.0d);
        defectiveComponentDetail.setQuantity(1L);
        defectiveComponentDetail.setRequestId(123L);
        defectiveComponentDetail.setUserName("janedoe");
        Optional<DefectiveComponentDetail> ofResult = Optional.of(defectiveComponentDetail);

        DefectiveComponentDetail defectiveComponentDetail1 = new DefectiveComponentDetail();
        defectiveComponentDetail1.setCardNumber(1L);
        defectiveComponentDetail1.setComponentName("Component Name");
        defectiveComponentDetail1.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail1.setContactNumber(1L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail1.setDateOfDelivery(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail1.setIsPriorityRequest(true);
        defectiveComponentDetail1.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail1.setPaid(true);
        defectiveComponentDetail1.setProcessingCharge(10.0d);
        defectiveComponentDetail1.setQuantity(1L);
        defectiveComponentDetail1.setRequestId(123L);
        defectiveComponentDetail1.setUserName("janedoe");
        when(this.defectiveComponentRepository.save((DefectiveComponentDetail) any()))
                .thenReturn(defectiveComponentDetail1);
        when(this.defectiveComponentRepository.findById((Long) any())).thenReturn(ofResult);
        List<String> actualMakePaymentResult = this.componentProcessingService.makePayment(123L, 1L, Long.MAX_VALUE, 10.0d);
        assertEquals(1, actualMakePaymentResult.size());
        assertEquals("SUCCESS", actualMakePaymentResult.get(0));
        verify(this.defectiveComponentRepository).save((DefectiveComponentDetail) any());
        verify(this.defectiveComponentRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ComponentProcessingService#makePayment(long, long, long, double)}
     */
    @Test
    void testMakePayment6() {
        DefectiveComponentDetail defectiveComponentDetail = mock(DefectiveComponentDetail.class);
        doNothing().when(defectiveComponentDetail).setCardNumber((Long) any());
        doNothing().when(defectiveComponentDetail).setComponentName((String) any());
        doNothing().when(defectiveComponentDetail).setComponentType((ComponentType) any());
        doNothing().when(defectiveComponentDetail).setContactNumber((Long) any());
        doNothing().when(defectiveComponentDetail).setDateOfDelivery((Date) any());
        doNothing().when(defectiveComponentDetail).setIsPriorityRequest((Boolean) any());
        doNothing().when(defectiveComponentDetail).setPackageAndDeliveryCharge((Double) any());
        doNothing().when(defectiveComponentDetail).setPaid(anyBoolean());
        doNothing().when(defectiveComponentDetail).setProcessingCharge((Double) any());
        doNothing().when(defectiveComponentDetail).setQuantity((Long) any());
        doNothing().when(defectiveComponentDetail).setRequestId(anyLong());
        doNothing().when(defectiveComponentDetail).setUserName((String) any());
        defectiveComponentDetail.setCardNumber(1L);
        defectiveComponentDetail.setComponentName("Component Name");
        defectiveComponentDetail.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail.setContactNumber(1L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail.setDateOfDelivery(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail.setIsPriorityRequest(true);
        defectiveComponentDetail.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail.setPaid(true);
        defectiveComponentDetail.setProcessingCharge(10.0d);
        defectiveComponentDetail.setQuantity(1L);
        defectiveComponentDetail.setRequestId(123L);
        defectiveComponentDetail.setUserName("janedoe");
        Optional<DefectiveComponentDetail> ofResult = Optional.of(defectiveComponentDetail);

        DefectiveComponentDetail defectiveComponentDetail1 = new DefectiveComponentDetail();
        defectiveComponentDetail1.setCardNumber(1L);
        defectiveComponentDetail1.setComponentName("Component Name");
        defectiveComponentDetail1.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail1.setContactNumber(1L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail1.setDateOfDelivery(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail1.setIsPriorityRequest(true);
        defectiveComponentDetail1.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail1.setPaid(true);
        defectiveComponentDetail1.setProcessingCharge(10.0d);
        defectiveComponentDetail1.setQuantity(1L);
        defectiveComponentDetail1.setRequestId(123L);
        defectiveComponentDetail1.setUserName("janedoe");
        when(this.defectiveComponentRepository.save((DefectiveComponentDetail) any()))
                .thenReturn(defectiveComponentDetail1);
        when(this.defectiveComponentRepository.findById((Long) any())).thenReturn(ofResult);
        List<String> actualMakePaymentResult = this.componentProcessingService.makePayment(123L, 1L, Long.MAX_VALUE, 10.0d);
        assertEquals(1, actualMakePaymentResult.size());
        assertEquals("SUCCESS", actualMakePaymentResult.get(0));
        verify(this.defectiveComponentRepository).save((DefectiveComponentDetail) any());
        verify(this.defectiveComponentRepository).findById((Long) any());
        verify(defectiveComponentDetail, atLeast(1)).setCardNumber((Long) any());
        verify(defectiveComponentDetail).setComponentName((String) any());
        verify(defectiveComponentDetail).setComponentType((ComponentType) any());
        verify(defectiveComponentDetail).setContactNumber((Long) any());
        verify(defectiveComponentDetail).setDateOfDelivery((Date) any());
        verify(defectiveComponentDetail).setIsPriorityRequest((Boolean) any());
        verify(defectiveComponentDetail).setPackageAndDeliveryCharge((Double) any());
        verify(defectiveComponentDetail, atLeast(1)).setPaid(anyBoolean());
        verify(defectiveComponentDetail).setProcessingCharge((Double) any());
        verify(defectiveComponentDetail).setQuantity((Long) any());
        verify(defectiveComponentDetail).setRequestId(anyLong());
        verify(defectiveComponentDetail).setUserName((String) any());
    }

    /**
     * Method under test: {@link ComponentProcessingService#packagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testPackagingAndDeliveryCharge() throws RestClientException {
        when(this.restTemplate.getForObject((String) any(), (Class<Double>) any(), (Object[]) any())).thenReturn(10.0d);
        assertEquals(10.0d, this.componentProcessingService.packagingAndDeliveryCharge(ComponentType.INTEGRAL, 1L));
        verify(this.restTemplate).getForObject((String) any(), (Class<Double>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link ComponentProcessingService#packagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testPackagingAndDeliveryCharge2() throws RestClientException {
        when(this.restTemplate.getForObject((String) any(), (Class<Double>) any(), (Object[]) any())).thenReturn(10.0d);
        assertEquals(10.0d, this.componentProcessingService.packagingAndDeliveryCharge(ComponentType.ACCESSORY, 1L));
        verify(this.restTemplate).getForObject((String) any(), (Class<Double>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link ComponentProcessingService#packagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testPackagingAndDeliveryCharge3() throws RestClientException {
        when(this.restTemplate.getForObject((String) any(), (Class<Double>) any(), (Object[]) any())).thenReturn(10.0d);
        assertEquals(10.0d, this.componentProcessingService.packagingAndDeliveryCharge(ComponentType.INTEGRAL, 1L));
        verify(this.restTemplate).getForObject((String) any(), (Class<Double>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link ComponentProcessingService#packagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testPackagingAndDeliveryCharge4() throws RestClientException {
        when(this.restTemplate.getForObject((String) any(), (Class<Double>) any(), (Object[]) any())).thenReturn(10.0d);
        assertEquals(10.0d, this.componentProcessingService.packagingAndDeliveryCharge(ComponentType.ACCESSORY, 1L));
        verify(this.restTemplate).getForObject((String) any(), (Class<Double>) any(), (Object[]) any());
    }


    /**
     * Method under test: {@link ComponentProcessingService#Authenticate(String, String)}
     */
    @Test
    void testAuthenticate1() throws Exception {
        when(this.restTemplate.exchange((String) any(), (org.springframework.http.HttpMethod) any(),
                (org.springframework.http.HttpEntity<Object>) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>("Body", HttpStatus.CONTINUE));
        assertFalse(this.componentProcessingService.Authenticate("ABC123", "janedoe"));
        verify(this.restTemplate).exchange((String) any(), (org.springframework.http.HttpMethod) any(),
                (org.springframework.http.HttpEntity<Object>) any(), (Class<Object>) any(), (Object[]) any());
    }

    /**
     * Method under test: {@link ComponentProcessingService#Authenticate(String, String)}
     */
    @Test
    void testAuthenticate2() throws Exception {
        when(this.restTemplate.exchange((String) any(), (org.springframework.http.HttpMethod) any(),
                (org.springframework.http.HttpEntity<Object>) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>("Body", HttpStatus.CONTINUE));
        assertFalse(this.componentProcessingService.Authenticate("ABC123", "janedoe"));
        verify(this.restTemplate).exchange((String) any(), (org.springframework.http.HttpMethod) any(),
                (org.springframework.http.HttpEntity<Object>) any(), (Class<Object>) any(), (Object[]) any());
    }
}

