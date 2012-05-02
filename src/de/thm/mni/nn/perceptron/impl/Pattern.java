/**
 * 
 */
package de.thm.mni.nn.perceptron.impl;

/**
 * A Pattern Object is used to Propagate or to Train a Perceptron.
 * A Pattern has a given Input and Output Values.
 * To propagate a Perceptron with a given Pattern, the pattern has to match the PErceptron.
 * This means the number of input and Output values in the Pattern has to match the number 
 * of Input and Output Neurons in the Perceptron.
 * @author Alexander Schulz
 *
 */
public class Pattern {
	
	private Boolean[] inputNeurons;
	private Boolean[] outputNeurons;
	private Boolean outputSet = false;
	
	public Pattern(Boolean[] activation) {
		this.inputNeurons = activation.clone();
	}
	
	public void addOutputPattern(Boolean[] outputPattern) {
		this.outputNeurons = outputPattern.clone();
	}
	
	public Boolean outputReady() {
		return outputSet;
	}
	
	public Integer getInputLength() {
		return inputNeurons.length;
	}
	
	public Integer getOuputLength() throws IllegalStateException {
		if(outputSet) {
			return outputNeurons.length;
		}
		throw new IllegalStateException("There is no Output Set!");
	}
	

}
