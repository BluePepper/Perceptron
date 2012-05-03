package de.thm.mni.nn.ui.actions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.EActivationFunction;
import de.thm.mni.nn.perceptron.impl.ENeuronType;
import de.thm.mni.nn.perceptron.impl.Pattern;
import de.thm.mni.nn.perceptron.impl.Perceptron;
import de.thm.mni.nn.ui.Action;
import de.thm.mni.nn.ui.UserInterface;

/**
 * The Add Action is used to add Perceptrons, Neurons and Axons
 * 
 */
public class Action_add extends Action {

	

	public Action_add(DataStore ds, UserInterface ui) {
		super(ds, ui);
	}

	// TODO: NeuronType is calculated by the Layer Position(Layer 0 = input/Layer between = hidden/last layer = output)
	// TODO: Number of Layers of Perceptron is minimum 2
	
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
			perceptronName = ui.inputToString();

			System.out.print("Number of layers: ");
			perceptronLayer = ui.inputToInt();

			System.out.println("Choose an initial axon weight: [1/2]");
			System.out.println("\t 1. Random axon weights between 0 and 1");
			System.out.println("\t 2. Customized upper and lower boundary");
			//Choose between initial random weights and custom random weights
			switch (ui.inputToInt()) {
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
				minSeed = ui.inputToDouble();

				System.out.print("The upper boundary of the axon weight: ");
				maxSeed = ui.inputToDouble();
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
			perceptronName = ui.inputToString();

			System.out.print("Adding neuron to the following layer: ");
			layerRow = ui.inputToInt();

			System.out.print("Number of neurons to add: ");
			numberOfNeurons = ui.inputToInt();

			System.out.println("Choose an neuron type: [1/2/3]");
			System.out.println("\t 1. Input\n\t 2. Hidden\n\t 3. Output ");
			neuronTypeInput = ui.inputToInt();
			//Proofs if the given neuron type is a correct one between 1 and 
			//the number of values in ENeuronType
			if (neuronTypeInput <= ENeuronType.values().length	&& neuronTypeInput > 0) {
				neuronType = ENeuronType.values()[neuronTypeInput - 1];
			} else {
				System.out.println("No possible value! Aborting...");
				return;
			}

			System.out.println("Choose an activation function: [1]");
			System.out.println("\t 1. Identity ");
			activationInput = ui.inputToInt();
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
			perceptronName = ui.inputToString();

			System.out.print("Source row of the perceptron layer: ");
			inputLayer = ui.inputToInt();

			System.out.print("Source neuron of the axon: ");
			inputNeuron = ui.inputToInt();

			System.out.print("Target row of the perceptron layer: ");
			outputLayer = ui.inputToInt();

			System.out.print("Target neuron of the axon: ");
			outputNeuron = ui.inputToInt();
			//Proof if the given perceptron name is the same as in the datastore
			if (ds.getPerceptron(perceptronName) == null) {
				System.out.println("No possible Perceptron found...");
				return;
			} else {
				ds.getPerceptron(perceptronName).addAxon(inputLayer,
						inputNeuron, outputLayer, outputNeuron);
			}
		} else if (args.equalsIgnoreCase("pattern")) {
			String patternName;
			int numberOfInputNeurons;
			
			System.out.print("Choose a pattern name: ");
			patternName = ui.inputToString();
			
			System.out.print("No. of inputNeurons for the pattern: ");
			numberOfInputNeurons = ui.inputToInt();
			Double inputpattern[] = new Double[numberOfInputNeurons];
			
			for (int i=0; i<numberOfInputNeurons; i++)
			{
				System.out.print("pattern for " + (i+1) + ". Input-Neuron: ");
				inputpattern[i] = ui.inputToDouble();
			}
			//Proof if the given perceptron name is the same as in the datastore
			if (ds.addPattern(patternName, new Pattern(inputpattern))) {
				System.out.println("Added the given pattern");
			} else {
				System.out.println("Aborting... There is already an pattern with this name");
			}
						
		}
	}
	


	@Override
	public String getDescription() {
		return "Adds an 'perceptron', 'neuron' or 'axon' 'pattern' to the Neuronal Net";
	}

}
