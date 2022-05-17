package com.fse.Authorization.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RoleTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Role}
     *   <li>{@link Role#setRoleDescription(String)}
     *   <li>{@link Role#setRoleName(String)}
     *   <li>{@link Role#getRoleDescription()}
     *   <li>{@link Role#getRoleName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Role actualRole = new Role();
        actualRole.setRoleDescription("Role Description");
        actualRole.setRoleName("Role Name");
        assertEquals("Role Description", actualRole.getRoleDescription());
        assertEquals("Role Name", actualRole.getRoleName());
    }
}

