package com.fse.Authorization.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fse.Authorization.dao.UserDao;
import com.fse.Authorization.entity.JwtRequest;
import com.fse.Authorization.entity.JwtResponse;
import com.fse.Authorization.entity.Role;
import com.fse.Authorization.entity.User;
import com.fse.Authorization.util.JwtUtil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtService.class})
@ExtendWith(SpringExtension.class)
class JwtServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDao userDao;

    /**
     * Method under test: {@link JwtService#createJwtToken(JwtRequest)}
     */
    @Test
    void testCreateJwtToken() throws Exception {
        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(this.userDao.findById((String) any())).thenReturn(ofResult);
        when(this.jwtUtil.generateToken((org.springframework.security.core.userdetails.UserDetails) any()))
                .thenReturn("ABC123");
        when(this.authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName("janedoe");
        jwtRequest.setUserPassword("iloveyou");
        JwtResponse actualCreateJwtTokenResult = this.jwtService.createJwtToken(jwtRequest);
        assertEquals("ABC123", actualCreateJwtTokenResult.getJwtToken());
        assertSame(user, actualCreateJwtTokenResult.getUser());
        verify(this.userDao, atLeast(1)).findById((String) any());
        verify(this.jwtUtil).generateToken((org.springframework.security.core.userdetails.UserDetails) any());
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link JwtService#createJwtToken(JwtRequest)}
     */
    @Test
    void testCreateJwtToken2() throws Exception {
        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(this.userDao.findById((String) any())).thenReturn(ofResult);
        when(this.jwtUtil.generateToken((org.springframework.security.core.userdetails.UserDetails) any()))
                .thenReturn("ABC123");
        when(this.authenticationManager.authenticate((Authentication) any()))
                .thenThrow(new UsernameNotFoundException("Msg"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName("janedoe");
        jwtRequest.setUserPassword("iloveyou");
        assertThrows(UsernameNotFoundException.class, () -> this.jwtService.createJwtToken(jwtRequest));
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link JwtService#createJwtToken(JwtRequest)}
     */
    @Test
    void testCreateJwtToken3() throws Exception {
        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(this.userDao.findById((String) any())).thenReturn(ofResult);
        when(this.jwtUtil.generateToken((org.springframework.security.core.userdetails.UserDetails) any()))
                .thenReturn("ABC123");
        when(this.authenticationManager.authenticate((Authentication) any())).thenThrow(new DisabledException("Msg"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName("janedoe");
        jwtRequest.setUserPassword("iloveyou");
        assertThrows(Exception.class, () -> this.jwtService.createJwtToken(jwtRequest));
        verify(this.authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link JwtService#createJwtToken(JwtRequest)}
     */
    @Test
    void testCreateJwtToken4() throws Exception {
        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(this.userDao.findById((String) any())).thenReturn(ofResult);
        when(this.jwtUtil.generateToken((org.springframework.security.core.userdetails.UserDetails) any()))
                .thenReturn("ABC123");
        when(this.authenticationManager.authenticate((Authentication) any())).thenThrow(new BadCredentialsException("Msg"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName("janedoe");
        jwtRequest.setUserPassword("iloveyou");
        assertThrows(Exception.class, () -> this.jwtService.createJwtToken(jwtRequest));
        verify(this.authenticationManager).authenticate((Authentication) any());
    }


    /**
     * Method under test: {@link JwtService#createJwtToken(JwtRequest)}
     */
    @Test
    void testCreateJwtToken5() throws Exception {
        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = mock(User.class);
        when(user.getUserName()).thenReturn("janedoe");
        when(user.getUserPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn(roleSet);
        doNothing().when(user).setRole((Set<Role>) any());
        doNothing().when(user).setUserFirstName((String) any());
        doNothing().when(user).setUserLastName((String) any());
        doNothing().when(user).setUserName((String) any());
        doNothing().when(user).setUserPassword((String) any());
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(this.userDao.findById((String) any())).thenReturn(ofResult);
        when(this.jwtUtil.generateToken((org.springframework.security.core.userdetails.UserDetails) any()))
                .thenReturn("ABC123");
        when(this.authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        JwtRequest jwtRequest = new JwtRequest();
        jwtRequest.setUserName("janedoe");
        jwtRequest.setUserPassword("iloveyou");
        assertEquals("ABC123", this.jwtService.createJwtToken(jwtRequest).getJwtToken());
        verify(this.userDao, atLeast(1)).findById((String) any());
        verify(user).getUserName();
        verify(user).getUserPassword();
        verify(user).getRole();
        verify(user).setRole((Set<Role>) any());
        verify(user).setUserFirstName((String) any());
        verify(user).setUserLastName((String) any());
        verify(user).setUserName((String) any());
        verify(user).setUserPassword((String) any());
        verify(this.jwtUtil).generateToken((org.springframework.security.core.userdetails.UserDetails) any());
        verify(this.authenticationManager).authenticate((Authentication) any());
    }


    /**
     * Method under test: {@link JwtService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(this.userDao.findById((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.jwtService.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.userDao).findById((String) any());
    }


    /**
     * Method under test: {@link JwtService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = mock(User.class);
        when(user.getUserName()).thenReturn("janedoe");
        when(user.getUserPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn(roleSet);
        doNothing().when(user).setRole((Set<Role>) any());
        doNothing().when(user).setUserFirstName((String) any());
        doNothing().when(user).setUserLastName((String) any());
        doNothing().when(user).setUserName((String) any());
        doNothing().when(user).setUserPassword((String) any());
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(this.userDao.findById((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.jwtService.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.userDao).findById((String) any());
        verify(user).getUserName();
        verify(user).getUserPassword();
        verify(user).getRole();
        verify(user).setRole((Set<Role>) any());
        verify(user).setUserFirstName((String) any());
        verify(user).setUserLastName((String) any());
        verify(user).setUserName((String) any());
        verify(user).setUserPassword((String) any());
    }

    /**
     * Method under test: {@link JwtService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername4() throws UsernameNotFoundException {
        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");

        Role role1 = new Role();
        role1.setRoleDescription("Role Description");
        role1.setRoleName("Role Name");

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role1);
        roleSet.add(role);
        User user = mock(User.class);
        when(user.getUserName()).thenReturn("janedoe");
        when(user.getUserPassword()).thenReturn("iloveyou");
        when(user.getRole()).thenReturn(roleSet);
        doNothing().when(user).setRole((Set<Role>) any());
        doNothing().when(user).setUserFirstName((String) any());
        doNothing().when(user).setUserLastName((String) any());
        doNothing().when(user).setUserName((String) any());
        doNothing().when(user).setUserPassword((String) any());
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        when(this.userDao.findById((String) any())).thenReturn(ofResult);
        UserDetails actualLoadUserByUsernameResult = this.jwtService.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.userDao).findById((String) any());
        verify(user).getUserName();
        verify(user).getUserPassword();
        verify(user).getRole();
        verify(user).setRole((Set<Role>) any());
        verify(user).setUserFirstName((String) any());
        verify(user).setUserLastName((String) any());
        verify(user).setUserName((String) any());
        verify(user).setUserPassword((String) any());
    }


    /**
     * Method under test: {@link JwtService#validateJwtToken(String, String)}
     */
    @Test
    void testValidateJwtToken2() throws Exception {
        assertFalse(this.jwtService.validateJwtToken("AshwaniKumarShaw", "janedoe"));
    }

    /**
     * Method under test: {@link JwtService#validateJwtToken(String, String)}
     */
    @Test
    void testValidateJwtToken3() throws Exception {
        assertFalse(this.jwtService.validateJwtToken("com.fse.Authorization.entity.User", "janedoe"));
    }
}

