package de.thm.mni.nn.perceptron.impl;

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
	 * Defines an upper border for the activation Value if used activation
	 * function needs it.
	 */
	private double activationValueUpperBound = 0.0;

	/**
	 * Defines an lower border for the activation Value if used activation
	 * function needs it.
	 */
	private double activationValueLowerBound = 0.0;

	/**
	 * Temperature Value for logistical activationfunction calculation.
	 */
	private double temperatureValue = 0.0;

	/**
	 * Threshold value for different activationvalue calculations.
	 */
	private double threshold = 0.0;
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

	// CONSTRUCTORS
	// *****************************************************************************************

	/**
	 * Constructor to be used for creating new Neurons. Adds the new Neuron to
	 * the given Perceptrons Neuron-List for later usage. This
	 * 
	 * @param activationFunction
	 *            The Activation Function used by the Neuron.
	 * @param neuronType
	 *            Specifies the Type of the Neuron (INPUT, OUTPUT, HIDDEN)
	 */
	public Neuron(EActivationFunction activationFunction, ENeuronType neuronType) {
		this.activationFunction = activationFunction;
		this.neuronType = neuronType;
		this.incomingDendrites = new ArrayList<Axon>();
	}

	/**
	 * 
	 * @param activationFunction
	 * @param low
	 * @param high
	 * @param neuronType
	 */
	public Neuron(EActivationFunction activationFunction, double low,
			double high, ENeuronType neuronType) {
		this.activationFunction = activationFunction;
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

		switch(this.activationFunction){
		case Identity:
			return ActivationCalculation.Identity(net_input);
		case Logistic:
			return ActivationCalculation.Logistic(net_input, threshold, temperatureValue);
		//TODO: Implement the other ACT Functions.
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
		System.out.println("My Activation Value: " + this.activationValue);
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
		// TODO: Find out why we don't need new Weight Attribute!
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

	/**
	 * Getter Method for the Temperatur Value
	 * 
	 * @return Temperatur Value of the Neuron
	 */
	public double getTemperatureValue() {
		return temperatureValue;
	}

	/**
	 * Sets the temperature Value of the Neuron
	 * 
	 * @param temperatureValue
	 *            Temperatur to set.
	 */
	public void setTemperatureValue(double temperatureValue) {
		this.temperatureValue = temperatureValue;
	}

	/**
	 * Getter Method for the threshold value.
	 * 
	 * @return threshold value of the Neuron.
	 */
	public double getThreshold() {
		return threshold;
	}

	/**
	 * Sets the threshold for the neuron
	 * 
	 * @param threshold
	 *            Threshold value to set.
	 */
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	/**
	 * Getter Method for the lower bound value for different activation
	 * functions
	 * 
	 * @return The neurons lower activation bound.
	 */
	public double getActivationValueLowerBound() {
		return activationValueLowerBound;
	}

	/**
	 * Sets the lower bound of the neuron.
	 * 
	 * @param activationValueLowerBound
	 *            Lower bound of the neuron to set.
	 */
	public void setActivationValueLowerBound(double activationValueLowerBound) {
		this.activationValueLowerBound = activationValueLowerBound;
	}

	/**
	 * Getter Method of the neurons upper activation bound.
	 * 
	 * @return The neurons upper activation bound.
	 */
	public double getActivationValueUpperBound() {
		return activationValueUpperBound;
	}

	/**
	 * Sets the neurons upper activation bound.
	 * 
	 * @param activationValueUpperBound
	 *            Activation value to set as upper bound for the neuron.
	 */
	public void setActivationValueUpperBound(double activationValueUpperBound) {
		this.activationValueUpperBound = activationValueUpperBound;
	}

}
