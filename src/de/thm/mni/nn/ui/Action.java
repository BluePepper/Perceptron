package de.thm.mni.nn.ui;

import de.thm.mni.nn.model.DataStore;

abstract public class Action implements IUIAction {

	protected DataStore ds = null;
	protected UserInterface ui = null;
	
	public Action(DataStore ds, UserInterface ui) {
		this.ds = ds;
		this.ui = ui;
	}

}
