/**
 * 
 */
package de.thm.mni.nn.perceptron.impl;

/**
 * A Pattern Object is used to Propagate or to Train a Perceptron.
 * A Pattern has a given Input and Output Values.
 * To propagate a Perceptron with a given Pattern, the pattern has to match the Perceptron.
 * This means the number of input and Output values in the Pattern has to match the number 
 * of Input and Output Neurons in the Perceptron.
 * @author Alexander Schulz
 *
 */
public class Pattern {
	
	private Double[] inputNeurons;
	private Double[] outputNeurons;
	private Boolean outputSet = false;
	
	/**
	 * Constructs a New Pattern Object with the given Activation Settings
	 * @param activation This double Array defines the Input Neuron Activation
	 */
	public Pattern(Double[] activation) {
		this.inputNeurons = activation.clone();
	}
	
	/**
	 * Adds the optional Output Pattern in the same way the InputPattern was set in the Constructor.
	 * This is need to use the Pattern for Training
	 * @param outputPattern This double Array defines the wanted output.
	 */
	public void addOutputPattern(Double[] outputPattern) {
		this.outputNeurons = outputPattern.clone();
	}
	
	/**
	 * Returns true if ad Output Pattern has been set.
	 * @return true if there is an Output Pattern
	 */
	public Boolean outputReady() {
		return outputSet;
	}
	
	/**
	 * Returns the Number of Input Neuron Settings
	 * @return the number of input Neurons defined in this Pattern
	 */
	public Integer getInputLength() {
		return inputNeurons.length;
	}

	/**
	 * Returns the Number of Output Neuron Settings
	 * @return the number of Output Neurons defined in this Pattern
	 * @throws IllegalStateException if there is no OutputPattern
	 */
	public Integer getOuputLength() throws IllegalStateException {
		if(outputSet) {
			return outputNeurons.length;
		}
		throw new IllegalStateException("There is no Output Set!");
	}
	

}
