/**
 * 
 */
package de.thm.mni.nn.ui.actions;

import de.thm.mni.nn.model.DataStore;
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
		}
		
		System.out.print("Name of the Pattern to use the inputValues from: ");
		patternName = ui.inputToString();
		Pattern pattern = ds.getPattern(patternName);
		if(pattern == null) {
			ui.printToConsole("Aborting... There is no Pattern named '" + patternName + "'.");
		}		
		
		perceptron.propagate(pattern);
		
	}

	/**
	 * @see de.thm.mni.nn.ui.IUIAction#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Propagate a Perceptron with a given Pattern.";
	}

}
