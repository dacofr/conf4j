package org.conf4j.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * in your IDE you need to define a JVM variable -Ddevoxx_base_url=test.devoxx.fr
 * 
 * in maven it's define as system property for the surefire plugin
 */
public class SampleServiceWithConfTest {
    private final SampleServiceWithConf svc = new SampleServiceWithConf();

    @Test
    public void testDevoxxUnitTest_localhost() {
        assertFalse("http://localhost/display/FR12/UnitTest".equals(svc.getDevoxxUnitTest()));
    }

    @Test
    public void testDevoxxUnitTest_test_devoxx_fr() {
        assertEquals("http://test.devoxx.fr/display/FR12/UnitTest", svc.getDevoxxUnitTest());
    }
}
