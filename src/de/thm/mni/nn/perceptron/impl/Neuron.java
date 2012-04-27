package de.thm.mni.nn.perceptron.impl;

import java.util.ArrayList;


/**
 * Class representing a Neuron in a Neuronal Network.
 * 
 * @author Marcel Walden
 */
public class Neuron {

	// CLASS VARIABLES *****************************************************************************************

	// INSTANCE VARIABLES *****************************************************************************************
	/**
	 * Defines the Activationvalue of the Neuron. This is used when the Neuron
	 * is of Type Input.
	 */
	private double activationValue;

	/**
	 * The net_input value specifies the incoming Signals of all connected
	 * Dendrites
	 */
	private double net_input;
	/**
	 * List of all Dendrites the Neuron is connected to.
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
	 * Getter-Method for the Activation-Function used by the Neuron.
	 * 
	 * @return the activationFunction
	 */
	public EActivationFunction getActivationFunction() {
		return activationFunction;
	}

	/**
	 * Getter Method for the Neuron Type.
	 * 
	 * @return the neuronType
	 */
	public ENeuronType getNeuronType() {
		return neuronType;
	}

	// CONSTRUCTORS *****************************************************************************************

	/**
	 * Constructor to be used for creating new Neurons. Adds the new Neuron to
	 * the given Perceptrons Neuron-List for later usage.
	 * 
	 * @param activationFunction
	 *            The Activation Function used by the Neuron.
	 * @param neuronType
	 *            Specifies the Type of the Neuron (INPUT, OUTPUT, HIDDEN)
	 */
	public Neuron(EActivationFunction activationFunction,
			ENeuronType neuronType) {
		this.activationFunction = activationFunction;
		this.neuronType = neuronType;

		this.incomingDendrites = new ArrayList<Axon>();
		// Add neuron to Perceptrons Neuron-List
	}

	// CLASS-METHODS  *****************************************************************************************

	// INSTANCE-METHODS PRIVATE *****************************************************************************************
	
	/**
	 * Calculates the output of the Neuron by using the specified Activation
	 * Function. 
	 * All of the Activationfunctions are specified here.
	 * If a not supported Function is chosen
	 * @throws UnsupportedOperationException
	 * 				The UnsupportedOperationExeption is thrown when a not implemented Activationfunction is used.
	 * @return Functionvalue of the Activation Function.
	 */
	private double calculateOutput() {
		if (this.neuronType == ENeuronType.Input) {
			return this.activationValue;
		}
		calculateNetInput();
		if (this.activationFunction == EActivationFunction.Identity) {
			return net_input;
		}
		return 0.00;
	}

	/**
	 * Calculates the Network Input of the Neuron by iterating over the
	 * Dendrites.
	 */
	private void calculateNetInput() {
		this.net_input = 0.0;
		for (Axon ax : incomingDendrites) {
			net_input += (ax.getWeight() * ax.getSource().getActivationValue());
		}
	}
	
	/**
	 * Returns the Activation Value of this Neuron.
	 * 
	 * @return This Neurons Activation Value.
	 */
	private double getActivationValue() {
		return this.activationValue;
	}
	
	// INSTANCE-METHODS PUBLIC *****************************************************************************************
	
	/**
	 * Inserts an Axon to the List of the Neurons incoming Dendrites.
	 * 
	 * @param toConnect
	 *            The Axon that should be connected to the Neuron
	 * @return TRUE when Axon was inserted, FALSE when Axon is already connected
	 *         to the Neuron.
	 */
	public Boolean connectNewDendrite(Axon toConnect) {
		if (!incomingDendrites.contains(toConnect)) {
			incomingDendrites.add(toConnect);
			return true;
		}
		return false;
	}

	/**
	 * Calculates the Propagation for this Neuron. Uses the sum over all
	 * Products between Axons Starting Point and the Axons Weight.
	 */
	public void propagateMe() {
		this.activationValue = this.calculateOutput();
	}

	/**
	 * Training Method for the Neuron.
	 * @throws UnsupportedOperationException
	 * 				The UnsupportedOperationExeption is thrown when a not implemented Activationfunction is used.
	 * @return Double Value that is defines the Training Value (Will be corrected later...)
	 */
	public double doTraining(double teaching_input) {
		if (this.activationFunction == EActivationFunction.Identity){
			return (teaching_input - activationValue);
		}
		throw new UnsupportedOperationException("Activation Function not Found!");
	}
	
	/**
	 * Returns all Axons that are connected as Dendrites to the Neuron
	 * @return An Array containing all the Dendrites connected to the Neuron.
	 */
	public Axon[] getMyDendrites()
	{
		return (Axon[])this.incomingDendrites.toArray();
	}

}
