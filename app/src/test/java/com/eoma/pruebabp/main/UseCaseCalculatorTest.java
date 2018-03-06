package com.eoma.pruebabp.main;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;


public class UseCaseCalculatorTest {

    private UseCaseCalculator calculator;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    /* for Kotlin
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
     */

    @Before
    public void setUp() throws Exception {
        calculator = new UseCaseCalculator();
    }

    @Test
    public void computeTest() throws Exception {

        assertEquals(45, (int) calculator.goCompute("1234567890").getValue());
        assertEquals(89, (int) calculator.goCompute("852169734452619980").getValue());
    }

}