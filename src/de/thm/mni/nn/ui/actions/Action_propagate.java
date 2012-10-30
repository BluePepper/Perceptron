/**
 * 
 */
package de.thm.mni.nn.ui.actions;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.GroupPattern;
import de.thm.mni.nn.perceptron.impl.Pattern;
import de.thm.mni.nn.perceptron.impl.Perceptron;
import de.thm.mni.nn.ui.Action;
import de.thm.mni.nn.ui.UserInterface;

/**
 * The Propagate Method is used to Propagate a Perceptron with a given Pattern.
 * @author Alexander Schulz
 *
 */
public class Action_propagate extends Action {

	/**
	 * @param ds
	 * @param ui
	 */
	public Action_propagate(DataStore ds, UserInterface ui) {
		super(ds, ui);
	}

	/**
	 * Propagates a Perceptron with a given Pattern.
	 * Pattern and Perceptron name is retrieved via console
	 */
	@Override
	public void callAction(String args) {
		String perceptronName;
		String patternName;
		
		System.out.print("Name of perceptron to propagate: ");
		perceptronName = ui.inputToString();
		Perceptron perceptron = ds.getPerceptron(perceptronName);
		if(perceptron == null) {
			ui.printToConsole("Aborting... There is no Perceptron named '" + perceptronName + "'.");
			return;
		}
		
		System.out.print("Name of the Pattern to use the inputValues from: ");
		patternName = ui.inputToString();
		Object patternObject = ds.getPattern(patternName);
		if(patternObject instanceof Pattern) {
			perceptron.propagate((Pattern) patternObject, true);
		} else if(patternObject instanceof GroupPattern) {
			GroupPattern groupPatter = (GroupPattern) patternObject;
			for ( Pattern patter : groupPatter.getAllPatterns()) {
				perceptron.propagate(patter, true);
			} 
		} else {
			ui.printToConsole("Aborting... There is no Pattern named '" + patternName + "'.");
			return;
		}		
		
		
		
	}

	/**
	 * @see de.thm.mni.nn.ui.IUIAction#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Propagate a Perceptron with a given Pattern.";
	}

}
