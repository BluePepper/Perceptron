package de.thm.mni.nn.perceptron.impl;

/**
 * Axon Representation for a Neuronal Network.
 * Training is implemented inside of this class.
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
	
	//CAPSULATION METHODS
	
	/**
	 * Returns the actual Weight of the Axon.
	 * @return Weight of the Axon.
	 */
	private double getWeight() {
		return weight;
	}
	
/**
 * Sets the acutal Weight of the Neuron
 * @param weight Weight to set.
 */
	private void setWeight(double weight) {
		this.weight = weight;
	}

	// CONSTRUCTORS
	
	/**
	 * Constructor requiring two Neurons.
	 * @param Start Source-Neuron of the Axon.
	 * @param End Target-Neuron of the Axon.
	 */
	public Axon(Neuron Start, Neuron End){
		this.Source = Start;
		this.Target = End;
	}
	
}
