package de.thm.mni.nn.perceptron.impl;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Alexander Schulz
 * The Perceptron is a collection of Neurons and Axons.
 * The Perceptron also knows the sort of the Neurons and is able to start a propagate and/or learning.
 *
 */
public class Perceptron {
	List<List<Neuron>> neurons = new ArrayList<List<Neuron>>();
	
	/**
	 * Standard Constructor
	 * @param layers The number of Layers
	 */
	public Perceptron(Integer layers) {
		for (int i = 0; i < layers; i++) {
			neurons.add(new ArrayList<Neuron>());
			System.out.println("Adding ..." + i);
		}
	}
	
	/**
	 * Returns the number of LAyers in this Perceptron
	 * @return number of Layers
	 */
	public Integer getLayerCount() {
		return neurons.size();
	}
	
	/**
	 * Adds the given count of Neurons to the given layer.
	 * The Neuron-Type and the ActivationFunction applies to the added Neurons.
	 * @param layer The Neurons where added to this Layer
	 * @param count number of Neurons to add
	 * @param type Type of Neurons to add
	 * @param activationFunction Used ActivationFunction of the added Neurons
	 */
	public void addNeuron(Integer layer, Integer count, ENeuronType type, EActivationFunction activationFunction) {
		if (layer >= neurons.size()) {
			throw new IndexOutOfBoundsException("This Perceptron stores only " + (neurons.size()) + " Layers!");
		}
		for (int i = 1; i < count; i++) {
			this.neurons.get(layer).add(new Neuron(activationFunction, type));
		}
	}
	
	
	
	

}
