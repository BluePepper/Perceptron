package de.thm.mni.nn.ui.actions;

import java.util.ArrayList;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.ActivationCalculation;
import de.thm.mni.nn.perceptron.impl.ENeuronType;
import de.thm.mni.nn.perceptron.impl.GroupPattern;
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
			/*ActivationCalculation calc = new ActivationCalculation();
			calc.setupIdentity();
			ui.printToConsole("Loading NeuNet...");
			Perceptron perc = new Perceptron(2);
			perc.addNeuron(0, 2, ENeuronType.Input, calc);
			perc.addNeuron(1, 1, ENeuronType.Output, calc);
			perc.addAxon(0, 0, 1, 0);
			perc.addAxon(0, 1, 1, 0);*/
			
			Double[] activationValues = new Double[2];
			activationValues[0] = 0.0;
			activationValues[1] = 0.0;
			Double[] activationValues2 = new Double[2];
			activationValues2[0] = 0.0;
			activationValues2[1] = 1.0;
			Double[] activationValues3 = new Double[2];
			activationValues3[0] = 1.0;
			activationValues3[1] = 0.0;
			Double[] activationValues4 = new Double[2];
			activationValues4[0] = 1.0;
			activationValues4[1] = 1.0;
			
			Double[] outputValues = new Double[1];
			outputValues[0] = 0.0;
			Double[] outputValues2 = new Double[1];
			outputValues2[0] = 1.0;

			Pattern p = new Pattern(activationValues);
			p.addOutputPattern(outputValues);
			ds.addPatternObject("pattern1", p);
			
			Pattern p2 = new Pattern(activationValues2);
			p2.addOutputPattern(outputValues2);
			ds.addPatternObject("pattern2", p2);
			
			Pattern p3 = new Pattern(activationValues3);
			p3.addOutputPattern(outputValues2);
			ds.addPatternObject("pattern3", p3);
			
			Pattern p4 = new Pattern(activationValues4);
			p4.addOutputPattern(outputValues2);
			ds.addPatternObject("pattern4", p4);
			
			GroupPattern gp = new GroupPattern("gruppe1");
			
			//Hinzufügen einzelner Patterns zu einer gegebenen Patterngruppe:
			gp.addPattern(p);
			gp.addPattern(p2);
			gp.addPattern(p3);
			gp.addPattern(p4);
			
			ArrayList<Pattern> gruppenListe = new ArrayList<Pattern>();
			gruppenListe.add(p); 
			gruppenListe.add(p2);
			gruppenListe.add(p3); 
			gruppenListe.add(p4);
			

			
			
			//Füge anschließend die gruppenPattern dem Datastore hinzu
			ds.addPatternObject("gruppe1", gp);
			ds.addPatternObject("gruppe2", gp);
			
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
