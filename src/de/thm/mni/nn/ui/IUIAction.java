package de.thm.mni.nn.ui;

import de.thm.mni.nn.perceptron.*;


/**
 * The Class UI-Action defines one Action that can be called from the UI Commandline
 *
 */
public interface IUIAction {
	
	/**
	 * The callAction Method executes the Action.
	 * The returned String is displayed on the Commandline
	 * @return response String
	 */
	void callAction(UserInterface ui, String args, IPerceptron Perceptron);
}
