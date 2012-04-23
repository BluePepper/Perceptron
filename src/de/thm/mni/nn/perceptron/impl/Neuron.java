package de.thm.mni.nn.perceptron.impl;



public class Neuron {
	
	private EActivationFunction activationFunction;
	private ENeuronType neuronType;
	
	public Neuron(EActivationFunction activationFunction, ENeuronType neuronType) {
		this.activationFunction = activationFunction;
		this.neuronType = neuronType;
	}

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
	



	
	
}
