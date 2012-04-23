package de.thm.mni.nn.perceptron.impl;

import java.util.ArrayList;
import java.util.List;


/**
 * Class representing a Neuron in a Neuronal Network.
 * @author Marcel Walden
 */
public class Neuron {
	
	//CLASS VARIABLES
	
	//INSTANCE VARIABLES
	
	/**
	 * List all Denderites the Neuron is connected to. Needs to be filled when New
	 */
	private ArrayList<Axon> incomingDendrites;
	
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
		this.incomingDendrites = new ArrayList<Axon>() ;
		// Add neuron to Perceptrons Neuron-List
	}
	// CLASS-METHODS
	

	// INSTANCE-METHODS
	/**
	 * Inserts an Axon to the List of the Neurons incoming Dendrites.
	 * @param toConnect The Axon that should be connected to the Neuron
	 * @return TRUE when Axon was inserted, FALSE when Axon is already connected to 
	 * the Neuron.
	 */
	public Boolean connectNewDendrite(Axon toConnect){
		if (!incomingDendrites.contains(toConnect)){
		incomingDendrites.add(toConnect);
		return true;
		}
		return false;
	}

	/**
	 * 
	 */
	public void propagate() {
		
	}

	
	
}
