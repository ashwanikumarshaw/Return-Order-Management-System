package com.fse.ReturnOrderManagementSystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RepairForIntegralService.class})
@ExtendWith(SpringExtension.class)
class RepairForIntegralServiceTest {
    @Autowired
    private RepairForIntegralService repairForIntegralService;

    /**
     * Method under test: {@link RepairForIntegralService#getProcessingDays()}
     */
    @Test
    void testGetProcessingDays() {
        assertEquals(5L, this.repairForIntegralService.getProcessingDays().longValue());
    }

    /**
     * Method under test: {@link RepairForIntegralService#getProcessingCharge()}
     */
    @Test
    void testGetProcessingCharge() {
        assertEquals(500.0d, this.repairForIntegralService.getProcessingCharge().doubleValue());
    }
}

