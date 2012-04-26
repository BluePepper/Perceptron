package de.thm.mni.nn.perceptron.impl;


/**
 * 
 * @author Alexander Schulz
 * The Perceptron is a collection of Neurons and Axons.
 * The Perceptron also knows the sort of the Neurons and is able to start a propagate and/or learning.
 *
 */
public class Perceptron {
	Neuron[][] neurons;
	
	/**
	 * Standard Constructor
	 * @param layers The number of Layers
	 */
	public Perceptron(Integer layers) {
		neurons = new Neuron[layers][];
	}
	
	public void addNeuron(Integer layer, Integer count, ENeuronType type, EActivationFunction activationFunction) {
		if (layer >= neurons.length) {
			throw new IndexOutOfBoundsException("This Perceptron stores only " + (this.neurons.length-1) + " Layers!");
		}
		for (int i = 1; i < count; i++) {
			Integer actElementCount = this.neurons[layer].length;
			this.neurons[layer][(actElementCount-1)+i] = new Neuron(activationFunction, type, layer);
		}
	}
	
	

}
