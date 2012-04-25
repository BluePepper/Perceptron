package de.thm.mni.nn.perceptron.impl;

/**
 * Axon Representation for a Neuronal Network. Training is implemented inside of
 * this class.
 * 
 * @author Tobias Knoth
 * 
 */
public class Axon {
	/**
	 * Source Neuron of this Axon
	 */
	private Neuron Source;

	/**
	 * Target Neuron of the Axon
	 */
	private Neuron Target;

	/**
	 * Actual Weight of the Axon
	 */
	private double weight;

	/**
	 * Through Training actualized Weight of the Axon
	 */
	private double weight_new;

	// CAPSULATION METHODS

	/**
	 * Returns the Source Neuron of the Axon.
	 * 
	 * @return The Axons Neuron.
	 */
	public Neuron getSource() {
		return Source;
	}

	/**
	 * Returns the actual Weight of the Axon.
	 * 
	 * @return Weight of the Axon.
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the actual Weight of the Axon
	 * 
	 * @param weight
	 *            Weight to set.
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	// CONSTRUCTORS

	/**
	 * Constructor requiring two Neurons and the weight of the connection.
	 * 
	 * @param Start
	 *            Source-Neuron of the Axon.
	 * @param End
	 *            Target-Neuron of the Axon.
	 * @param weight
	 *            Weight of the Axon.
	 */
	public Axon(Neuron Start, Neuron End, double weight) {
		this.Source = Start;
		this.Target = End;
		this.weight = weight;
	}

	/**
	 * Calculates the new weight of the Axon by using the specified Training
	 * Function
	 * 
	 * @return Functionvalue of the Training Function.
	 */

	public double trainWeight() {
		throw new UnsupportedOperationException(
				"Calculation of Weight not yet implemented");
	}
}
