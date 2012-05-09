package de.thm.mni.nn.perceptron.impl;

/**
 * This class represents a collection of static Calculationfunctions for the
 * Activation of a Neuron.
 * 
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
	public static Double SaturatedIdentity(Double input, Double lowerBound,
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

	/**
	 * Threshold Activation Function that returns either one or zero depending
	 * on the input value and the set threshold
	 * 
	 * @param input
	 *            Inputvalue for the function
	 * @param thresholdValue
	 *            Thresholdvalue as zero/one boundary
	 * @return The function returns zero if the input is lower than the
	 *         threshold and one if its equal to or larger than the threshold.
	 */
	public static Double Threshold(Double input, Double thresholdValue) {
		if (input >= thresholdValue)
			return 1.0;
		else
			return 0.0;
	}

	/**
	 * Logistical Activation Function
	 * 
	 * @param inputValue
	 *            Inputvalue for the function.
	 * @param thresholdValue
	 *            Threasholdvalue as boundary.
	 * @param temperatureFactor
	 *            Temperature-Constant.
	 * @return The function returns the Logistical Representation of the input
	 *         value.
	 */
	public static Double Logistic(Double inputValue, Double thresholdValue,
			Double temperatureFactor) {
		Double rv = 1 / (1 + (Math
				.exp((-(inputValue - thresholdValue) / temperatureFactor))));
		return rv;
	}
}
