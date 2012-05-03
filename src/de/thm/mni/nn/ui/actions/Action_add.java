package de.thm.mni.nn.ui.actions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.EActivationFunction;
import de.thm.mni.nn.perceptron.impl.ENeuronType;
import de.thm.mni.nn.perceptron.impl.Perceptron;
import de.thm.mni.nn.ui.Action;
import de.thm.mni.nn.ui.UserInterface;

/**
 * The Add Action is used to add Perceptrons, Neurons and Axons
 * 
 */
public class Action_add extends Action {

	Scanner in = new Scanner(System.in);

	public Action_add(DataStore ds, UserInterface ui) {
		super(ds, ui);
	}

	/**
	 * This function chooses between adding and perceptron,
	 * neuron or axon
	 * @param args The given string from the command window
	 */
	public void callAction(String args) {
		if (args.equalsIgnoreCase("perceptron")) {
			String perceptronName;
			int perceptronLayer;

			System.out.print("Name of perceptron: ");
			perceptronName = this.inputToString();

			System.out.print("Number of layers: ");
			perceptronLayer = this.inputToInt();

			System.out.println("Choose an initial axon weight: [1/2]");
			System.out.println("\t 1. Random axon weights between 0 and 1");
			System.out.println("\t 2. Customized upper and lower boundary");
			//Choose between initial random weights and custom random weights
			switch (this.inputToInt()) {
			case 1:
				//Proof if the given perceptron name is the same as in the datastore
				if (ds.addPerceptron(perceptronName, new Perceptron(perceptronLayer))) {
					System.out.println("Added the given perceptron");
				} else {
					System.out.println("Aborting... There is already an perceptron with this name");
				}
				break;
			case 2:
				double minSeed, maxSeed;

				System.out.print("The lower boundary of the axon weight: ");
				minSeed = this.inputToDouble();

				System.out.print("The upper boundary of the axon weight: ");
				maxSeed = this.inputToDouble();
				//Proof if the given perceptron name is the same as in the datastore
				if (ds.addPerceptron(perceptronName, new Perceptron(perceptronLayer, minSeed, maxSeed))) {
					System.out.println("Added the given perceptron");
				} else {
					System.out.println("Aborting... There is already an perceptron with the given name");
				}
				break;
			default:
				System.out.println("Aborting... You havn't choosed 1 or 2");
			}
		} else if (args.equalsIgnoreCase("neuron")) {
			String perceptronName;
			int layerRow, numberOfNeurons, neuronTypeInput, activationInput;
			ENeuronType neuronType = null;
			EActivationFunction actFunc = null;

			System.out.print("Adding the neurons to this perceptron: ");
			perceptronName = this.inputToString();

			System.out.print("Adding neuron to the following layer: ");
			layerRow = this.inputToInt();

			System.out.print("Number of neurons to add: ");
			numberOfNeurons = this.inputToInt();

			System.out.println("Choose an neuron type: [1/2/3]");
			System.out.println("\t 1. Input\n\t 2. Hidden\n\t 3. Output ");
			neuronTypeInput = this.inputToInt();
			//Proofs if the given neuron type is a correct one between 1 and 
			//the number of values in ENeuronType
			if (neuronTypeInput <= ENeuronType.values().length	&& neuronTypeInput > 0) {
				neuronType = ENeuronType.values()[neuronTypeInput - 1];
			} else {
				System.out.println("No possible value! Aborting...");
				in.reset();
				return;
			}

			System.out.println("Choose an activation function: [1]");
			System.out.println("\t 1. Identity ");
			activationInput = this.inputToInt();
			//Proofs if the given activation function is a correct one between 1 and 
			//the number of values in EActivationFunction
			if (activationInput <= EActivationFunction.values().length	&& activationInput > 0) {
				actFunc = EActivationFunction.values()[activationInput - 1];
			} else {
				System.out.println("No possible value! Aborting...");
				return;
			}
			//Proof if the given perceptron name is the same as in the datastore
			if (ds.getPerceptron(perceptronName) == null) {
				System.out.println("No possible Perceptron found...");
				return;
			} else {
				ds.getPerceptron(perceptronName).addNeuron(layerRow, numberOfNeurons, neuronType, actFunc);
			}
		} else if (args.equalsIgnoreCase("axon")) {
			String perceptronName;
			int inputLayer, inputNeuron, outputLayer, outputNeuron;

			System.out.print("Adding the axon to this perceptron: ");
			perceptronName = this.inputToString();

			System.out.print("Source row of the perceptron layer: ");
			inputLayer = this.inputToInt();

			System.out.print("Source neuron of the axon: ");
			inputNeuron = this.inputToInt();

			System.out.print("Target row of the perceptron layer: ");
			outputLayer = this.inputToInt();

			System.out.print("Target neuron of the axon: ");
			outputNeuron = this.inputToInt();
			//Proof if the given perceptron name is the same as in the datastore
			if (ds.getPerceptron(perceptronName) == null) {
				System.out.println("No possible Perceptron found...");
				return;
			} else {
				ds.getPerceptron(perceptronName).addAxon(inputLayer,
						inputNeuron, outputLayer, outputNeuron);
			}
		}
	}
	
	/**
	 * This function fetches the user input and converts the value in double
	 * @return value from user input 
	 */
	private double inputToDouble() {
		double inputValue = 0;
		while (true) {
			try {
				inputValue = Double.valueOf(this.in.next().trim());
				break;
			} catch (NumberFormatException e) {
				System.out.println("That wasn't a double value");
				in.reset();
			} catch (InputMismatchException e) {
				System.out.println("That wasn't a double value");
				in.reset();
			}
		}
		return inputValue;
	}

	/**
	 * This function fetches the user input and converts the value in int
	 * @return value from user input 
	 */
	private int inputToInt() {
		int inputValue = 0;
		while (true) {
			try {
				inputValue = this.in.nextInt();
				break;
			} catch (NumberFormatException e) {
				System.out.println("That wasn't an int value. Try another one...");
				this.in.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("That wasn't an int value. Try another one...");
				this.in.nextLine();
			}
		}
		return inputValue;
	}

	/**
	 * This function fetches the user input and saves the value as an string
	 * @return value from user input 
	 */
	private String inputToString() {
		String inputValue = null;
		try {
			inputValue = this.in.next();
		} catch (NoSuchElementException e) {
			System.out.println("There isn't any string available");
			this.in.nextLine();	
		}
		return inputValue;
	}

	@Override
	public String getDescription() {
		return "Adds an 'perceptron', 'neuron' or 'axon' to the Neuronal Net";
	}

}
