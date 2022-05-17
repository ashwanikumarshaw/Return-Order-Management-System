package com.fse.ReturnOrderManagementSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fse.ReturnOrderManagementSystem.util.ComponentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PackagingAndDeliveryService.class})
@ExtendWith(SpringExtension.class)
class PackagingAndDeliveryServiceTest {
    @Autowired
    private PackagingAndDeliveryService packagingAndDeliveryService;

    /**
     * Method under test: {@link PackagingAndDeliveryService#getCharge(ComponentType, Long)}
     */
    @Test
    void testGetCharge() {
        assertEquals(1050.0d, this.packagingAndDeliveryService.getCharge(ComponentType.INTEGRAL, 3L));
        assertEquals(600.0d, this.packagingAndDeliveryService.getCharge(ComponentType.ACCESSORY, 3L));
        assertThrows(IllegalArgumentException.class,
                () -> this.packagingAndDeliveryService.getCharge(ComponentType.INTEGRAL, 0L));
    }
}

