package com.fse.ReturnOrderManagementSystem.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.ReturnOrderManagementSystem.dto.ProcessRequest;
import com.fse.ReturnOrderManagementSystem.dto.ProcessResponse;
import com.fse.ReturnOrderManagementSystem.entity.DefectiveComponentDetail;
import com.fse.ReturnOrderManagementSystem.repository.DefectiveComponentRepository;
import com.fse.ReturnOrderManagementSystem.service.ComponentProcessingService;
import com.fse.ReturnOrderManagementSystem.util.ComponentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ComponentProcessingController.class})
@ExtendWith(SpringExtension.class)
class ComponentProcessingControllerTest {
    @Autowired
    private ComponentProcessingController componentProcessingController;

    @MockBean
    private ComponentProcessingService componentProcessingService;

    @MockBean
    private DefectiveComponentRepository defectiveComponentRepository;

    /**
     * Method under test: {@link ComponentProcessingController#orders(String, String)}
     */
    @Test
    void testOrders() throws Exception {
        when(this.defectiveComponentRepository.findAllByUserName((String) any())).thenReturn(new ArrayList<>());
        when(this.componentProcessingService.Authenticate((String) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/return/ProcessDetail/{userName}", "janedoe")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        MockMvcBuilders.standaloneSetup(this.componentProcessingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ComponentProcessingController#orders(String, String)}
     */
    @Test
    void testOrders2() throws Exception {
        DefectiveComponentDetail defectiveComponentDetail = new DefectiveComponentDetail();
        defectiveComponentDetail.setCardNumber(2L);
        defectiveComponentDetail.setComponentName("?");
        defectiveComponentDetail.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail.setContactNumber(2L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail.setDateOfDelivery(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail.setIsPriorityRequest(true);
        defectiveComponentDetail.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail.setPaid(true);
        defectiveComponentDetail.setProcessingCharge(10.0d);
        defectiveComponentDetail.setQuantity(2L);
        defectiveComponentDetail.setRequestId(123L);
        defectiveComponentDetail.setUserName("janedoe");

        ArrayList<DefectiveComponentDetail> defectiveComponentDetailList = new ArrayList<>();
        defectiveComponentDetailList.add(defectiveComponentDetail);
        when(this.defectiveComponentRepository.findAllByUserName((String) any())).thenReturn(defectiveComponentDetailList);
        when(this.componentProcessingService.Authenticate((String) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/return/ProcessDetail/{userName}", "janedoe")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        MockMvcBuilders.standaloneSetup(this.componentProcessingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"requestId\":123,\"userName\":\"janedoe\",\"contactNumber\":2,\"componentType\":\"INTEGRAL\",\"componentName\":"
                                        + "\"?\",\"quantity\":2,\"processingCharge\":10.0,\"packageAndDeliveryCharge\":10.0,\"dateOfDelivery\":0,\"cardNumber"
                                        + "\":2,\"isPriorityRequest\":true,\"paid\":true}]"));
    }

    /**
     * Method under test: {@link ComponentProcessingController#orders(String, String)}
     */
    @Test
    void testOrders3() throws Exception {
        when(this.defectiveComponentRepository.findAllByUserName((String) any())).thenReturn(new ArrayList<>());
        when(this.componentProcessingService.Authenticate((String) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/return/ProcessDetail/{userName}", "janedoe");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.header("Authorization",
                "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        MockMvcBuilders.standaloneSetup(this.componentProcessingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ComponentProcessingController#paymentForReturnRequest(String, long, long, long, double)}
     */
    @Test
    void testPaymentForReturnRequest() throws Exception {
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
        when(this.componentProcessingService.makePayment(anyLong(), anyLong(), anyLong(), anyDouble()))
                .thenReturn(new ArrayList<>());
        when(this.componentProcessingService.Authenticate((String) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/return/CompleteProcessing/{requestId}/{cardNumber}/{creditLimit}/{processingCharge}", 123L, 1L, 1L,
                        10.0d)
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        MockMvcBuilders.standaloneSetup(this.componentProcessingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ComponentProcessingController#paymentForReturnRequest(String, long, long, long, double)}
     */
    @Test
    void testPaymentForReturnRequest2() throws Exception {
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
        when(this.componentProcessingService.makePayment(anyLong(), anyLong(), anyLong(), anyDouble()))
                .thenReturn(new ArrayList<>());
        when(this.componentProcessingService.Authenticate((String) any(), (String) any())).thenReturn(true);

        DefectiveComponentDetail defectiveComponentDetail1 = new DefectiveComponentDetail();
        defectiveComponentDetail1.setCardNumber(2L);
        defectiveComponentDetail1.setComponentName("?");
        defectiveComponentDetail1.setComponentType(ComponentType.INTEGRAL);
        defectiveComponentDetail1.setContactNumber(2L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        defectiveComponentDetail1.setDateOfDelivery(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        defectiveComponentDetail1.setIsPriorityRequest(true);
        defectiveComponentDetail1.setPackageAndDeliveryCharge(10.0d);
        defectiveComponentDetail1.setPaid(true);
        defectiveComponentDetail1.setProcessingCharge(10.0d);
        defectiveComponentDetail1.setQuantity(2L);
        defectiveComponentDetail1.setRequestId(123L);
        defectiveComponentDetail1.setUserName("janedoe");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/return/CompleteProcessing/{requestId}/{cardNumber}/{creditLimit}/{processingCharge}", 123L, 1L, 1L,
                        10.0d)
                .header("Authorization", defectiveComponentDetail1);
        MockMvcBuilders.standaloneSetup(this.componentProcessingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ComponentProcessingController#processDetail(String, ProcessRequest)}
     */
    @Test
    void testProcessDetail() throws Exception {
        ProcessResponse processResponse = new ProcessResponse();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        processResponse.setDateOfDelivery(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        processResponse.setPackageAndDeliveryCharge(10.0d);
        processResponse.setProcessingCharge(10.0d);
        processResponse.setRequestId(123L);
        when(this.componentProcessingService.processReturnRequest((ProcessRequest) any())).thenReturn(processResponse);
        when(this.componentProcessingService.Authenticate((String) any(), (String) any())).thenReturn(true);

        ProcessRequest processRequest = new ProcessRequest();
        processRequest.setComponentName("Component Name");
        processRequest.setComponentType(ComponentType.INTEGRAL);
        processRequest.setContactNumber(1L);
        processRequest.setIsPriorityRequest(true);
        processRequest.setQuantity(1L);
        processRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(processRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/return/ProcessDetail")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.componentProcessingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"requestId\":123,\"processingCharge\":10.0,\"packageAndDeliveryCharge\":10.0,\"dateOfDelivery\":0}"));
    }
}

