package de.thm.mni.nn.ui;

import de.thm.mni.nn.model.DataStore;

public class Action implements IUIAction {

	@SuppressWarnings("unused")
	private DataStore ds = null;
	
	public Action(DataStore ds) {
		this.ds = ds;
	}
	
	@Override
	public void callAction(UserInterface ui, String args) {
	}
	

}
