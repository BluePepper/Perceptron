package de.thm.mni.nn.perceptron.impl;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActivationFunctionsTest {

	@Test
	public void testCalculateActivation() {
		ActivationCalculation a = new ActivationCalculation();
		a.setupIdentity();
		assertTrue(1.0 == a.calculateActivation(1.0)); 
		a = new ActivationCalculation();
		a.setupLogistic(0.1,6.0);

		assertTrue(0.53743 == (double)Math.round(a.calculateActivation(1.0)*100000)/100000);		
	}

}
