package de.thm.mni.nn.ui.actions;

import de.thm.mni.nn.perceptron.IPerceptron;
import de.thm.mni.nn.ui.IUIAction;
import de.thm.mni.nn.ui.UserInterface;

public class Action_exit implements IUIAction {

	@Override
	public void callAction(UserInterface ui, String args, IPerceptron Perceptron) {
		System.out.println("Exiting...");
		
	}

}
