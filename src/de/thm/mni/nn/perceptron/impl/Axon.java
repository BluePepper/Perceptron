package de.thm.mni.nn.perceptron.impl;

/*
 * The Axon Class represents the Connections between two Neurons by implementing Source
 * and Target. Training Operations are defined in this class.
 */
public class Axon {
	/*
	 * Source Neuron of this Axon
	 */
	private Neuron Source;
	
	/*
	 * Target Neuron of this Axon
	 */
	private Neuron Target;
	
	/*
	 * Actual Weight of the Axon
	 */
	private double weight;
	
	/*
	 * Through Training actualized Weight of the Axon
	 */
	private double weight_new;
	
	public Axon(Neuron Start, Neuron End){
		this.Source = Start;
		this.Target = End;
	}
	
}
