/**
 * 
 */
package de.thm.mni.nn.ui.actions;

import java.util.ArrayList;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.GroupPattern;
import de.thm.mni.nn.perceptron.impl.Pattern;
import de.thm.mni.nn.perceptron.impl.Perceptron;
import de.thm.mni.nn.ui.Action;
import de.thm.mni.nn.ui.UserInterface;

/**
 * 
 * @author Alexander Schulz
 *
 */
public class Action_train extends Action {
	
	/**
	 * @param ds
	 * @param ui
	 */
	public Action_train(DataStore ds, UserInterface ui) {
		super(ds, ui);
	}

	
	@Override
	public void callAction(String args) {
		String perceptronName;
		String patternName;
		int training_count;
		ArrayList <Pattern> trainingPattern = new ArrayList<Pattern>();
		System.out.print("Name of perceptron to train: ");
		perceptronName = ui.inputToString();
		Perceptron perceptron = ds.getPerceptron(perceptronName);
		if(perceptron == null) {
			ui.printToConsole("The given Perceptron could not be found!");
			return;
		}
		
		System.out.print("Name of the Pattern or Patterngroup to use: ");
		
		//Ask the user if he would train more pattern per training cycle
		do {
			patternName = ui.inputToString();
			if(ds.getPattern(patternName) == null) {
				ui.printToConsole("Aborting... There is no Pattern named '" + patternName + "'.");
				return;
			} else {
				if (ds.getPattern(patternName) instanceof Pattern) {
					trainingPattern.add((Pattern) ds.getPattern(patternName));
				} else if(ds.getPattern(patternName) instanceof GroupPattern) {
					GroupPattern groupPattern = (GroupPattern) ds.getPattern(patternName);
					for(Pattern pattern : groupPattern.getAllPatternsOfGroup(patternName)) {
						trainingPattern.add(pattern);
					}
				}
				
			}
			System.out.println("Train another pattern or group with this perceptron? [y/n]");
			String chooseToTrainAnother = ui.inputToString();
			if(chooseToTrainAnother.charAt(0)=='y' || chooseToTrainAnother.charAt(0)=='Y'){
				System.out.print("Name of the Pattern or Patterngroup to use: ");
				continue;
			}else {
				break;
			}
		} while(true);
		
		System.out.println("How many times should be trained?:");
		training_count = ui.inputToInt();
		for (int i = 0; i < training_count; i++){
			for (Pattern pattern : trainingPattern){
				perceptron.train(pattern);
			}
		}
		ui.printToConsole("Pattern " + patternName + " trained " + training_count + " times.");
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.nn.ui.IUIAction#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Trains a Perceptron with a given Pattern";
	}

}
