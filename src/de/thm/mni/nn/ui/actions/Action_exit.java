package de.thm.mni.nn.ui.actions;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.ui.Action;
import de.thm.mni.nn.ui.UserInterface;

public class Action_exit extends Action {
	
	public Action_exit(DataStore ds, UserInterface ui) {
		super(ds, ui);
	}

	@Override
	public void callAction(String args) {
		System.out.println("Exiting...");
		ui.stop();
	}

	@Override
	public String getDescription() {
		return "Exits the Program.";
	}

}
