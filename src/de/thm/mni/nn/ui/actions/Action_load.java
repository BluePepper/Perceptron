package de.thm.mni.nn.ui.actions;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.ActivationCalculation;
import de.thm.mni.nn.perceptron.impl.ENeuronType;
import de.thm.mni.nn.perceptron.impl.Pattern;
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
			ActivationCalculation calc = new ActivationCalculation();
			calc.setupIdentity();
			ui.printToConsole("Loading NeuNet...");
			Perceptron perc = new Perceptron(2);
			perc.addNeuron(0, 2, ENeuronType.Input, calc);
			perc.addNeuron(1, 1, ENeuronType.Output, calc);
			perc.addAxon(0, 0, 1, 0);
			perc.addAxon(0, 1, 1, 0);
			
			Double[] activationValues = new Double[2];
			activationValues[0] = 1.0;
			activationValues[1] = 0.0;
			
			Double[] outputValues = new Double[1];
			outputValues[0] = 0.0;
			Pattern p = new Pattern(activationValues);
			p.addOutputPattern(outputValues);
			ds.addPattern("pattern1", p);
			//perc.propagate(p);
			
			//perc.train(p);
			
		} else if(args.equalsIgnoreCase("TestNet")){
			ActivationCalculation calc = new ActivationCalculation();
			calc.setupIdentity();
			Perceptron perc = new Perceptron(3);
			perc.addNeuron(0, 3, ENeuronType.Input, calc);
			perc.addNeuron(1, 2, ENeuronType.Hidden, calc);
			perc.addNeuron(2, 1, ENeuronType.Output, calc);
			perc.addAxon(0, 0, 1, 0);
			perc.addAxon(0, 1, 1, 0);
			perc.addAxon(0, 2, 1, 0);
			perc.addAxon(0, 0, 1, 1);
			perc.addAxon(0, 1, 1, 1);
			perc.addAxon(0, 2, 1, 1);
			perc.addAxon(1, 0, 2, 0);
			perc.addAxon(1, 1, 2, 0);
			ds.addPerceptron("test1", perc);
		} else {
			ui.printToConsole("Preset " + args + " could not be found!");
		}
	}

	@Override
	public String getDescription() {
		return "Loads a Preset of an Neuronal Net";
	}



}
