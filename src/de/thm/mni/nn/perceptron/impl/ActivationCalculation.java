package de.thm.mni.nn.perceptron.impl;

import java.security.InvalidParameterException;

/**
 * This class represents a collection of static Calculationfunctions for the
 * Activation of a Neuron.
 * 
 * @author Marcel Walden
 * 
 */
public class ActivationCalculation {

	private Boolean objectIsSetUp = false;

	private Double lowerBound;
	private Double upperBound;
	private Double threshold;
	private Double temperatureFactor;

	private EActivationFunction myAlgorithm;

	/**
	 * Sets the object up for using with Identity Function.
	 */
	public void setupIdentity() {
		objectIsSetUp = true;
		myAlgorithm = EActivationFunction.Identity;
	}

	/**
	 * Sets the object up for using with Threshold-Function.
	 * @param threshold Threshold value to specify Bound.
	 */
	public void setupThreshold(Double threshold) {
		objectIsSetUp = true;
		myAlgorithm = EActivationFunction.Threshold;
		this.threshold = threshold;
	}

	/**
	 * Sets the object up for using with Logistical Activation Function.
	 * @param threshold Threshold Value.
	 * @param Temperature Theta Value for the Function.
	 */
	public void setupLogistic(Double threshold, Double Temperature) {
		objectIsSetUp = true;
		myAlgorithm = EActivationFunction.Logistic;
		this.threshold = threshold;
		this.temperatureFactor = Temperature;
	}
	
	/**
	 * Sets up the object for using with bounded identity Function which can specify an upper and lower bound.
	 * @param lowerBound lower value bound.
	 * @param upperBound upper value bound.
	 */
	public void setupBoundedIdentity(Double lowerBound, Double upperBound){
		objectIsSetUp = true;
		myAlgorithm = EActivationFunction.BoundedIdentity;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}

	/**
	 * Calculates the Activation Value with the given Settings of the Object.
	 * 
	 * @param input
	 *            Inputvalue to calculate with
	 * @return Double value that represents the Activation value.
	 * @throws InvalidParameterException
	 *             This Exception is thrown when no setup method has been called
	 *             for the Object.
	 */
	public Double calculateActivation(Double input) {
		if (!objectIsSetUp)
			throw new InvalidParameterException(
					"No Setup method has been called! You need to call a Setup Method to set the Objects Properties.");
		switch(myAlgorithm){
		case Logistic: return Logistic(input);
		case BoundedIdentity: return SaturatedIdentity(input);
		case Identity: return Identity(input);
		case Threshold: return Threshold(input);
		}
		return 0.0;
	}

	// Private Calculation Algorithms
	/**
	 * Identity function that is extended by a lower and upper bound value.
	 * 
	 * @param input
	 *            Inputvalue for the function
	 * @return The function returns the inputvalue if its between the upper and
	 *         lower border. Otherwise either the upper or lower bound value
	 *         will be returned.
	 * 
	 */
	private Double SaturatedIdentity(Double input) {
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
	private Double Identity(Double input) {
		return input;
	}

	/**
	 * Threshold Activation Function that returns either one or zero depending
	 * on the input value and the set threshold
	 * 
	 * @param input
	 *            Inputvalue for the function
	 * @return The function returns zero if the input is lower than the
	 *         threshold and one if its equal to or larger than the threshold.
	 */
	private Double Threshold(Double input) {
		if (input >= threshold)
			return 1.0;
		else
			return 0.0;
	}

	/**
	 * Logistical Activation Function
	 * 
	 * @param inputValue
	 *            Inputvalue for the function.
	 * @return The function returns the Logistical Representation of the input
	 *         value.
	 */
	private Double Logistic(Double inputValue) {
		Double rv = 1 / (1 + (Math
				.exp((-(inputValue - threshold) / temperatureFactor))));
		return rv;
	}

}