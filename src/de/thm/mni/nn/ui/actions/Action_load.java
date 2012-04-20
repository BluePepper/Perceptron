package de.thm.mni.nn.ui.actions;

import de.thm.mni.nn.model.DataStore;
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
		
	}

	@Override
	public String getDescription() {
		return "Loads a Preset of an Neuronal Net";
	}



}
