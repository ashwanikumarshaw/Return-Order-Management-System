package com.fse.ReturnOrderManagementSystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FallbackControllerTest {
    /**
     * Method under test: {@link FallbackController#componentReturnServiceFallback()}
     */
    @Test
    void testComponentReturnServiceFallback() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: javax.servlet.ServletContext
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        assertEquals("Return Service is down,  Try Again Later",
                (new FallbackController()).componentReturnServiceFallback());
    }

    /**
     * Method under test: {@link FallbackController#packagingAndDeliveryServiceFallback()}
     */
    @Test
    void testPackagingAndDeliveryServiceFallback() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: javax.servlet.ServletContext
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        assertEquals("Packaging & Delivery Service is down,  Try Again Later",
                (new FallbackController()).packagingAndDeliveryServiceFallback());
    }

    /**
     * Method under test: {@link FallbackController#authServiceFallback()}
     */
    @Test
    void testAuthServiceFallback() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R005 Unable to load class.
        //   Class: javax.servlet.ServletContext
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        assertEquals("Authentication Service is down,  Try Again Later", (new FallbackController()).authServiceFallback());
    }
}

