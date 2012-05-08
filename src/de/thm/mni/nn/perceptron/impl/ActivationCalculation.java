package de.thm.mni.nn.perceptron.impl;

/**
 * This class represents a collection of static Calculationfunctions for the Activation of a Neuron.
 * @author Marcel Walden
 *
 */
public class ActivationCalculation {

	/**
	 * Identity function that is extended by a lower and upper bound value.
	 * 
	 * @param input
	 *            Inputvalue for the function
	 * @param lowerBound
	 *            The lower bound specifies a low border for the return value.
	 * @param upperBound
	 *            The upper bound specifies an upper border for the return
	 *            value.
	 * @return The function returns the inputvalue if its between the upper and
	 *         lower border. Otherwise either the upper or lower bound value
	 *         will be returned.
	 * 
	 */
	public static Double BoundedIdentity(Double input, Double lowerBound,
			Double upperBound) {
		if (input > upperBound)
			return upperBound;
		if (input < lowerBound)
			return lowerBound;
		return input;
	}

	/**
	 * Identity Function that returns the inputvalue.
	 * 
	 * @param input
	 *            Inputvalue for the function
	 * @return This function returns its Inputvalue.
	 */
	public static Double Identity(Double input) {
		return input;
	}
}
