package de.thm.mni.nn.ui;

/**
 * The Class UI-Action defines one Action that can be called from the UI Commandline
 *
 */
public interface IUIAction {
	
	/**
	 * The callAction Method executes the Action.
	 * The returned String is displayed on the Commandline
	 */
	void callAction(String args);
	String getDescription();
}
