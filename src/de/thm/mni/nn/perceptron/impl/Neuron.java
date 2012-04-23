package de.thm.mni.nn.perceptron.impl;


/**
 * Class representing a Neuron in a Neuronal Network.
 * @author Marcel Walden
 */
public class Neuron {
	
	//CLASS VARIABLES
	
	//INSTANCE VARIABLES
	/**
	 * Activation Function used by the Neuron.
	 */
	private EActivationFunction activationFunction;
	
	/**
	 * Specification of the Neurons type.
	 */
	private ENeuronType neuronType;
	
	/**
	 * Perceptron the Neuron lives in. 
	 */
	
	private Perceptron myPerceptron;
	
	/**
	 * Layer number inside of the Perceptron.
	 */
	private int layer;
	

	/**
	 * Getter-Method for the Activation-Function used by the Neuron.
	 * @return the activationFunction
	 */
	public EActivationFunction getActivationFunction() {
		return activationFunction;
	}

	/**
	 * Getter Method for the Neuron Type.
	 * @return the neuronType
	 */
	public ENeuronType getNeuronType() {
		return neuronType;
	}
	
	// CONSTRUCTORS
	
	/**
	 * Constructor to be used for creating new Neurons. Adds the new Neuron to the
	 * given Perceptrons Neuron-List for later usage.
	 * @param activationFunction The Activation Function used by the Neuron.
	 * @param neuronType Specifies the Type of the Neuron (INPUT, OUTPUT, HIDDEN)
	 * @param myPerceptron The Perceptron in which the Neuron "lives" in.
	 * @param layer The Number of the Layer in which the Neuron lives inside of the 
	 * Perceptron.
	 */
	public Neuron(EActivationFunction activationFunction, ENeuronType neuronType, Perceptron myPerceptron, int layer) {
		this.activationFunction = activationFunction;
		this.neuronType = neuronType;
		this.myPerceptron = myPerceptron;
		this.layer = layer;
		// Add neuron to Perceptrons Neuron-List
	}
	// CLASS-METHODS
	
	// INSTANCE-METHODS
	
	
	
	
}
