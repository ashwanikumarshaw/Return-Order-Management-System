package com.fse.Authorization.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class UserTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link User}
     *   <li>{@link User#setRole(Set)}
     *   <li>{@link User#setUserFirstName(String)}
     *   <li>{@link User#setUserLastName(String)}
     *   <li>{@link User#setUserName(String)}
     *   <li>{@link User#setUserPassword(String)}
     *   <li>{@link User#getRole()}
     *   <li>{@link User#getUserFirstName()}
     *   <li>{@link User#getUserLastName()}
     *   <li>{@link User#getUserName()}
     *   <li>{@link User#getUserPassword()}
     * </ul>
     */
    @Test
    void testConstructor() {
        User actualUser = new User();
        HashSet<Role> roleSet = new HashSet<>();
        actualUser.setRole(roleSet);
        actualUser.setUserFirstName("Jane");
        actualUser.setUserLastName("Doe");
        actualUser.setUserName("janedoe");
        actualUser.setUserPassword("iloveyou");
        assertSame(roleSet, actualUser.getRole());
        assertEquals("Jane", actualUser.getUserFirstName());
        assertEquals("Doe", actualUser.getUserLastName());
        assertEquals("janedoe", actualUser.getUserName());
        assertEquals("iloveyou", actualUser.getUserPassword());
    }
}

