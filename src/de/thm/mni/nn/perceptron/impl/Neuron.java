package de.thm.mni.nn.perceptron.impl;



public class Neuron {
	
	private EActivationFunction activationFunction;
	private ENeuronType neuronType;
	private Perceptron myPerceptron;
	
	public Neuron(EActivationFunction activationFunction, ENeuronType neuronType, Perceptron myPerceptron) {
		this.activationFunction = activationFunction;
		this.neuronType = neuronType;
		this.myPerceptron = myPerceptron;
	}
	
	public void propagate() {
		
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
