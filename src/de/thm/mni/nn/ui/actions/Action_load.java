package de.thm.mni.nn.ui.actions;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.EActivationFunction;
import de.thm.mni.nn.perceptron.impl.ENeuronType;
import de.thm.mni.nn.perceptron.impl.Perceptron;
import de.thm.mni.nn.ui.Action;
import de.thm.mni.nn.ui.UserInterface;

/**
 * The Load Action is used to load a predefined set of Perceptrons, Neurons, ...
 *
 */
public class Action_load extends Action {

	public Action_load(DataStore ds, UserInterface ui) {
		super(ds, ui);
	}
	
	public void callAction(String args) {
		if (args.equalsIgnoreCase("NeuNet")) {
			ui.printToConsole("Loading NeuNet...");
			Perceptron perc = new Perceptron(3);
			perc.addNeuron(3, 2, ENeuronType.Input, EActivationFunction.Identity);
			perc.addNeuron(3, 5, ENeuronType.Input, EActivationFunction.Identity);
			
		} else {
			ui.printToConsole("Preset " + args + " could not be found!");
		}
	}

	@Override
	public String getDescription() {
		return "Loads a Preset of an Neuronal Net";
	}



}
