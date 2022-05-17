package com.fse.Authorization.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.Authorization.entity.User;
import com.fse.Authorization.service.UserService;

import java.util.HashSet;

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

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;


    /**
     * Method under test: {@link UserController#registerNewUser(User)}
     */
    @Test
    void testRegisterNewUser() throws Exception {
        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        when(this.userService.registerNewUser((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setRole(new HashSet<>());
        user1.setUserFirstName("Jane");
        user1.setUserLastName("Doe");
        user1.setUserName("janedoe");
        user1.setUserPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/registerNewUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userName\":\"janedoe\",\"userFirstName\":\"Jane\",\"userLastName\":\"Doe\",\"userPassword\":\"iloveyou\","
                                        + "\"role\":[]}"));
    }
}

