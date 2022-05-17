package com.fse.Authorization.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fse.Authorization.dao.RoleDao;
import com.fse.Authorization.dao.UserDao;
import com.fse.Authorization.entity.Role;
import com.fse.Authorization.entity.User;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleDao roleDao;

    @MockBean
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#initRoleAndUser()}
     */
    @Test
    void testInitRoleAndUser() {
        // TODO: Complete this test.
        //   Add getters for the following fields or make them package-private:
        //     UserService.passwordEncoder
        //     UserService.roleDao
        //     UserService.userDao

        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        when(this.userDao.save((User) any())).thenReturn(user);

        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");
        when(this.roleDao.save((Role) any())).thenReturn(role);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        this.userService.initRoleAndUser();
    }

    /**
     * Method under test: {@link UserService#registerNewUser(User)}
     */
    @Test
    void testRegisterNewUser() {
        User user = new User();
        user.setRole(new HashSet<>());
        user.setUserFirstName("Jane");
        user.setUserLastName("Doe");
        user.setUserName("janedoe");
        user.setUserPassword("iloveyou");
        when(this.userDao.save((User) any())).thenReturn(user);

        Role role = new Role();
        role.setRoleDescription("Role Description");
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        when(this.roleDao.findById((String) any())).thenReturn(ofResult);
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        User user1 = new User();
        user1.setRole(new HashSet<>());
        user1.setUserFirstName("Jane");
        user1.setUserLastName("Doe");
        user1.setUserName("janedoe");
        user1.setUserPassword("iloveyou");
        assertSame(user, this.userService.registerNewUser(user1));
        verify(this.userDao).save((User) any());
        verify(this.roleDao).findById((String) any());
        verify(this.passwordEncoder).encode((CharSequence) any());
        assertEquals(1, user1.getRole().size());
        assertEquals("secret", user1.getUserPassword());
    }


    /**
     * Method under test: {@link UserService#getEncodedPassword(String)}
     */
    @Test
    void testGetEncodedPassword() {
        when(this.passwordEncoder.encode((CharSequence) any())).thenReturn("secret");
        assertEquals("secret", this.userService.getEncodedPassword("iloveyou"));
        verify(this.passwordEncoder).encode((CharSequence) any());
    }
}

