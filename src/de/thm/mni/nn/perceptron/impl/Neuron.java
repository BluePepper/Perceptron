package de.thm.mni.nn.perceptron.impl;


/**
 * 
 * @author Marcel Walden
 *
 */
public class Neuron {
	
	private EActivationFunction activationFunction;
	private ENeuronType neuronType;
	private Perceptron myPerceptron;
	private int layer;

	/**
	 * @return the activationFunction
	 */
	public EActivationFunction getActivationFunction() {
		return activationFunction;
	}

	/**
	 * @return the neuronType
	 */
	public ENeuronType getNeuronType() {
		return neuronType;
	}
	
	// Constructors
	
	public Neuron(EActivationFunction activationFunction, ENeuronType neuronType, Perceptron myPerceptron, int layer) {
		this.activationFunction = activationFunction;
		this.neuronType = neuronType;
		this.myPerceptron = myPerceptron;
		this.layer = layer;
		// Add neuron to Perceptrons Neuron-List
	}

	
	// Instance-Methods
	public void propagate() {
		
	}
	
	
	
}
