package com.fse.ReturnOrderManagementSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReplacementForAccessory.class})
@ExtendWith(SpringExtension.class)
class ReplacementForAccessoryTest {
    @Autowired
    private ReplacementForAccessory replacementForAccessory;

    /**
     * Method under test: {@link ReplacementForAccessory#getProcessingDays()}
     */
    @Test
    void testGetProcessingDays() {
        assertEquals(2L, this.replacementForAccessory.getProcessingDays().longValue());
    }

    /**
     * Method under test: {@link ReplacementForAccessory#getProcessingCharge()}
     */
    @Test
    void testGetProcessingCharge() {
        assertEquals(300.0d, this.replacementForAccessory.getProcessingCharge().doubleValue());
    }
}

