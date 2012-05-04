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
	private List<List<Neuron>> neurons = new ArrayList<List<Neuron>>();
	private Double seedmin, seedmax;
	private Double learningRate = 0.8;
	
	/**
	 * Constructs a new Perceptron with the given number of Layers
	 * To provide Random weights a minimum and maximum border of the weights should be provided.
	 * @param layers The number of Layers
	 * @param seedmin The Axon Random Weight min Value
	 * @param seedmax The Axon Random weight max Value
	 */
	public Perceptron(Integer layers, Double seedmin, Double seedmax) {
		for (int i = 0; i < layers; i++) {
			neurons.add(new ArrayList<Neuron>());
		}
		this.seedmin = seedmin;
		this.seedmax = seedmax;
	}
	
	/**
	 * Constructs a new Perceptron with the given number of Layers
	 * The Random Weights Border in this Constructor is set to 1.0 an 0.0
	 * @param layers The number of Layers
	 */
	public Perceptron(Integer layers) {
		for (int i = 0; i < layers; i++) {
			neurons.add(new ArrayList<Neuron>());
		}
		this.seedmin = 0.0;
		this.seedmax = 1.0;
	}
	
	/**
	 * Returns the number of neurons per layer in this Perceptron
	 * @param layer Counting the neurons of this layer 
	 * @return number of neurons per layer
	 */
	public Integer getNeuronsPerLayerCount(Integer layer) {
		return neurons.get(layer).size();
	}
	
	/**
	 * Returns the number of Layers in this Perceptron
	 * @return number of Layers
	 */
	public Integer getLayerCount() {
		return neurons.size();
	}
	
	/**
	 * Returns the number of Neurons in this Perceptron
	 * @return number of Neurons
	 */
	public Integer getNeuronCount() {
		Integer count = 0;
		for(List<Neuron> neurons : this.neurons) {
			count += neurons.size();
		}
		return count;
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
		for (int i = 0; i < count; i++) {
			this.neurons.get(layer).add(new Neuron(activationFunction, type));
		}
	}
	
	/**
	 * Adds a Axon to the Perceptron. The Start and End-Neuron is identificated by the Layer and the Column of the Neuron
	 * @param startNeuronLayer
	 * @param startNeuronColumn
	 * @param endNeuronLayer
	 * @param endNeuronColumn
	 * @throws IllegalArgumentException if any of the Columns or Layers are out of Bounds. Errormessage included!
	 */
	public void addAxon(Integer startNeuronLayer, Integer startNeuronColumn, Integer endNeuronLayer, Integer endNeuronColumn) {
		if (startNeuronLayer >= neurons.size() || endNeuronLayer >= neurons.size()) {
			throw new IllegalArgumentException("Start or End Layer equals not the size of the Perceptron.");
		}
		// TODO: Check Columns!
		Neuron startNeuron = neurons.get(startNeuronLayer).get(startNeuronColumn);
		Neuron endNeuron = neurons.get(endNeuronLayer).get(endNeuronColumn);
		new Axon(startNeuron, endNeuron, this.seedmin, this.seedmax); // the Connection between endNeuron an Axon is handled in the Axon Constructor
	}
	
	/**
	 * 
	 */
	public void propagate(Pattern p) {
		// Test if the Pattern matches the Input Length (Output Length is irrelevant for propagate
		if (!(neurons.get(0).size() == p.getInputLength())) {   
			throw new IllegalArgumentException("Given Pattern does not match Perceptrons number of Input Neurons!");
		}
		
		// Set Activation Values
		for (int i = 0; i < neurons.get(0).size(); i++) {
			neurons.get(0).get(i).setActivationValue(p.getInputNeuronsSet()[i]);
		}
		
		// Propagate by looping over all Layer except the first
		// First Layer is ignored because there are no incoming Axons
		for (int i = 1; i < neurons.size(); i++) {
			for (Neuron neuron : neurons.get(i)) {
				neuron.propagateMe();
			}
		}		
		
		// get result by looping over OutputNeurons
		List<Neuron> outputNeurons = neurons.get(neurons.size()-1);
		for (Neuron neuron : outputNeurons) {
			System.out.println("output Value: " + neuron.getActivationValue());
		}
	}
	/**
	 * Train Function
	 * TODO: Javadoc :-) 
	 * @param p
	 */
	public void train(Pattern p) {
		// propagate; set activation Values
		this.propagate(p);
		
		// run over all Layers from the bottom to the top; Except the Input Layer
		// Backpropagation
		for(int i = neurons.size()-1; i > 0; i--) {
			for(int s = 0; s < neurons.get(i).size(); i++) {
				Neuron neuron = neurons.get(i).get(s);
				if(neuron.getNeuronType() == ENeuronType.Output) {
					neuron.calculateDeltaFunctionValuesForOutputNeuron(p.getOutputNeuronsSet()[s]);
				}
				if(neuron.getNeuronType() == ENeuronType.Hidden) {
					neuron.calculateDeltaFunctionValuesForHiddenNeuron();
				}
				if(neuron.getNeuronType() == ENeuronType.Input) {
					throw new IllegalStateException("Error: Input Neuron in Layer " + i + " found!");
				}
			}
		}
		
		// Calculate new weights
		for(int i = 1; i < neurons.size(); i++) {
			for(Neuron neuron : neurons.get(i)) {
				neuron.calculateNewWeightsForMyDendrites(learningRate);
			}
		}
		
		
	}
	
	
	
	

}
