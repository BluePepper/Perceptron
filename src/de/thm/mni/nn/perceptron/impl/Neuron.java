package de.thm.mni.nn.perceptron.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Neuron in a Neuronal Network.
 * 
 * @author Marcel Walden
 */
public class Neuron {

	// CLASS VARIABLES
	// *****************************************************************************************

	// INSTANCE VARIABLES
	// *****************************************************************************************
	/**
	 * Defines the Activationvalue of the Neuron. This is used when the Neuron
	 * is of Type Input.
	 */
	private double activationValue = 1.0;


	/**
	 * Specifies the actual Delta of the Neuron. Used in Backpropagation while
	 * Training.
	 */
	private double myDelta = 0.0;

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
	 * Specification of the Neurons type.
	 */
	private ENeuronType neuronType;

	private ActivationCalculation myCalculator;
	
	

	/**
	 * Getter Method for the Neuron Type.
	 * 
	 * @return the neuronType
	 */
	public ENeuronType getNeuronType() {
		return neuronType;
	}

	// CONSTRUCTORS
	// *****************************************************************************************

	/**
	 * Constructor to be used for creating new Neurons. Adds the new Neuron to
	 * the given Perceptrons Neuron-List for later usage. This
	 * 
	 * @param calculator
	 *            ActivationCalculation object that is preset to the right values to calculate the Activation Value.
	 * @param neuronType
	 *            Specifies the Type of the Neuron (INPUT, OUTPUT, HIDDEN)
	 */
	public Neuron(ActivationCalculation calculator, ENeuronType neuronType) {
		myCalculator = calculator;
		this.neuronType = neuronType;
		this.incomingDendrites = new ArrayList<Axon>();
	}

	// CLASS-METHODS
	// *****************************************************************************************

	// INSTANCE-METHODS PRIVATE
	// *****************************************************************************************


	/**
	 * Calculates the output of the Neuron by using the specified Activation
	 * Function. All of the Activationfunctions are specified here. If a not
	 * supported Function is chosen
	 * 
	 * @throws UnsupportedOperationException
	 *             The UnsupportedOperationExeption is thrown when a not
	 *             implemented Activationfunction is used.
	 * @return Functionvalue of the Activation Function.
	 */
	private double calculateOutput() {
		if (this.neuronType == ENeuronType.Input) {
			return this.activationValue;
		}
		calculateNetInput();
		return myCalculator.calculateActivation(net_input);
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
	 * Adds the given Value to the Neurons Delta.
	 * 
	 * @param additor
	 *            Value to add. Can be positive or negative.
	 */
	private void addDeltaValue(double additor) {
		this.myDelta += additor;
	}

	// INSTANCE-METHODS PUBLIC
	// *****************************************************************************************

	/**
	 * Returns the Activation Value of this Neuron.
	 * 
	 * @return This Neurons Activation Value.
	 */
	public double getActivationValue() {
		return this.activationValue;
	}

	/**
	 * Sets the Activation Value of the Neuron. This is especially needed to
	 * preset the input Neurons.
	 * 
	 * @param newValue
	 *            New Activation Value.
	 */
	public void setActivationValue(double newValue) {
		this.activationValue = newValue;
	}

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
		DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("My Activation Value: " + df.format(this.activationValue));
	}

	/**
	 * Calculates the Deltafunction for the Neuron. The Function works only for
	 * Outputneurons. Otherwise InvalidOperationException is thrown.
	 * 
	 * @param teachingInput
	 *            Is just needed for Output
	 * @throws UnsupportedOperationException
	 *             This Exception is thrown when the function is called from a
	 *             not-Output Neuron.
	 */
	public void calculateDeltaFunctionValuesForOutputNeuron(double teachingInput) {
		myDelta = 0.0;
		if (this.neuronType != ENeuronType.Output)
			throw new UnsupportedOperationException(
					"This function is only for the use with Output-Neurons! This is a "
							+ this.neuronType.toString());
		myDelta = teachingInput - this.activationValue;
	}

	/**
	 * Calculates the Deltafunction for the Neuron. This Function only works for
	 * Hidden-Neurons.
	 * 
	 * @throws UnsupportedOperationException
	 *             This Exception is thrown when the function is called from a
	 *             not-Hidden Neuron.
	 */
	public void calculateDeltaFunctionValuesForHiddenNeuron() {
		if (this.neuronType != ENeuronType.Hidden)
			throw new UnsupportedOperationException(
					"This function is only for the use with Hidden-Neurons! This is a "
							+ this.neuronType.toString());
		for (Axon ax : this.incomingDendrites) {
			ax.getSource().addDeltaValue(myDelta * ax.getWeight());
		}
	}

	/**
	 * Calculates the new Weights for the Dendrites of the Neuron.
	 * 
	 * @param learningRate
	 *            The given Learning Rate for this Perceptron.
	 */
	public void calculateNewWeightsForMyDendrites(double learningRate) {
		for (Axon ax : this.incomingDendrites) {
			double newWeight = learningRate
					* ax.getSource().getActivationValue() * this.myDelta;
			ax.setWeight(ax.getWeight() + newWeight);
		}
	}

	/**
	 * Returns all Axons that are connected as Dendrites to the Neuron
	 * 
	 * @return An Array containing all the Dendrites connected to the Neuron.
	 */
	public Axon[] getMyDendrites() {
		if (!this.incomingDendrites.isEmpty())
			return (Axon[]) this.incomingDendrites.toArray();
		Axon[] r = new Axon[0];
		return r;
	}

	/**
	 * Returns all Axons that are connected as Dendrites to the Neuron
	 * 
	 * @return A List containing all the Dendrites connected to the Neuron.
	 */
	public List<Axon> getMyDendritesAsList() {
		return this.incomingDendrites;
	}



}
