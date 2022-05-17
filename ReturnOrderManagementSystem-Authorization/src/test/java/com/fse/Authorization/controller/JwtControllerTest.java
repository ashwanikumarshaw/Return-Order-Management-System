package com.fse.Authorization.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.Authorization.entity.JwtRequest;
import com.fse.Authorization.entity.JwtResponse;
import com.fse.Authorization.entity.User;
import com.fse.Authorization.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {JwtController.class})
@ExtendWith(SpringExtension.class)
class JwtControllerTest {
    @Autowired
    private JwtController jwtController;

    @MockBean
    private JwtService jwtService;

    /**
     * Method under test: {@link JwtController#createJwtToken(JwtRequest)}
     */
    @Test
    void testCreateJwtToken() throws Exception {
        when(this.jwtService.createJwtToken((JwtRequest) any())).thenReturn(new JwtResponse(new User(), "ABC123"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName("janedoe");
        jwtRequest.setUserPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(jwtRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.jwtController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"user\":{\"userName\":null,\"userFirstName\":null,\"userLastName\":null,\"userPassword\":null,\"role\":null},"
                                        + "\"jwtToken\":\"ABC123\"}"));
    }

    /**
     * Method under test: {@link JwtController#validateAndReturnUser(String, String)}
     */
    @Test
    void testValidateAndReturnUser() throws Exception {
        when(this.jwtService.validateJwtToken((String) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/validate")
                .param("userName", "foo")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.jwtController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }

    /**
     * Method under test: {@link JwtController#validateAndReturnUser(String, String)}
     */
    @Test
    void testValidateAndReturnUser2() throws Exception {
        when(this.jwtService.validateJwtToken((String) any(), (String) any())).thenReturn(true);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/auth/validate");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("userName", "foo")
                .header("Authorization", "Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.jwtController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        ContentResultMatchers contentResult = MockMvcResultMatchers.content();
        resultActions.andExpect(contentResult.string(Boolean.TRUE.toString()));
    }
}

