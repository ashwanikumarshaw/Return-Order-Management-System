package com.fse.ReturnOrderManagementSystem.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fse.ReturnOrderManagementSystem.service.PackagingAndDeliveryService;
import com.fse.ReturnOrderManagementSystem.util.ComponentType;
import com.sun.security.auth.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PackagingDeliveryController.class})
@ExtendWith(SpringExtension.class)
class PackagingDeliveryControllerTest {
    @MockBean
    private PackagingAndDeliveryService packagingAndDeliveryService;

    @Autowired
    private PackagingDeliveryController packagingDeliveryController;

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge2() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("350.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge3() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(0.5d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge4() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(-0.5d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("-0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge5() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(Double.NaN);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("\"NaN\""));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge6() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", "Uri Vars", "Uri Vars", "Uri Vars");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge7() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.secure(true);
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge8() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.content("AAAAAAAA".getBytes("UTF-8"));
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge9() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.content("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge10() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.contentType("https://example.org/example");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge11() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge12() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.session(new MockHttpSession());
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge13() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.principal(new UserPrincipal("principal"));
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge14() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("UTF-8");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge15() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge16() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("42");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge17() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Content-Length");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge18() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("UTF-8");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("350.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge19() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("350.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge20() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("42");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("350.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge21() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Content-Length");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("350.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge22() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(0.5d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("UTF-8");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge23() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(0.5d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge24() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(0.5d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("42");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge25() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(0.5d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Content-Length");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge26() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(-0.5d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("UTF-8");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("-0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge27() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(-0.5d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("-0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge28() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(-0.5d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("42");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("-0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge29() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(-0.5d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Content-Length");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("-0.5"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge30() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(Double.NaN);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("UTF-8");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("\"NaN\""));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge31() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(Double.NaN);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("\"NaN\""));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge32() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(Double.NaN);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("42");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("\"NaN\""));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge33() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(Double.NaN);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Content-Length");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("\"NaN\""));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge34() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", "Uri Vars", "Uri Vars", "Uri Vars");
        getResult.characterEncoding("UTF-8");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge35() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", "Uri Vars", "Uri Vars", "Uri Vars");
        getResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge36() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", "Uri Vars", "Uri Vars", "Uri Vars");
        getResult.characterEncoding("42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge37() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", "Uri Vars", "Uri Vars", "Uri Vars");
        getResult.characterEncoding("Content-Length");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge38() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(10.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Transfer-Encoding");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("10.0"));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge39() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", "Uri Vars", "Uri Vars", "Uri Vars");
        getResult.characterEncoding("UTF-8");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge40() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", "Uri Vars", "Uri Vars", "Uri Vars");
        getResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge41() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", "Uri Vars", "Uri Vars", "Uri Vars");
        getResult.characterEncoding("42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge42() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", "Uri Vars", "Uri Vars", "Uri Vars");
        getResult.characterEncoding("Content-Length");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link PackagingDeliveryController#getPackagingAndDeliveryCharge(ComponentType, Long)}
     */
    @Test
    void testGetPackagingAndDeliveryCharge43() throws Exception {
        when(this.packagingAndDeliveryService.getCharge((ComponentType) any(), (Long) any())).thenReturn(350.0d);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/GetPackagingDeliveryCharge/{componentType}/{count}", ComponentType.INTEGRAL, 3L);
        getResult.characterEncoding("Transfer-Encoding");
        MockMvcBuilders.standaloneSetup(this.packagingDeliveryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("350.0"));
    }
}

