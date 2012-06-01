/**
 * 
 */
package de.thm.mni.nn.ui.actions;

import java.util.Set;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.GroupPattern;
import de.thm.mni.nn.perceptron.impl.Pattern;
import de.thm.mni.nn.ui.Action;
import de.thm.mni.nn.ui.UserInterface;

/**
 * Lists all Patterns or Perceptrons in the DataStore.
 * @author Alexander Schulz
 *
 */
public class Action_list extends Action {

	/**
	 * @param ds
	 * @param ui
	 */
	public Action_list(DataStore ds, UserInterface ui) {
		super(ds, ui);
	}

	/**
	 * Gives out a List of all Patterns or Perceptrons.
	 */
	@Override
	public void callAction(String args) { //list [Pattern, Perceptrons]
		if (args == null) {
			ui.printToConsole("Error: Syntax is list [patterns|perceptrons|groups]");
			return;
		}
		if (args.equalsIgnoreCase("Patterns")) {
			Set<String> patternNames = ds.getGenericPatternObjectNames();
			if (patternNames.size() == 0) {
				ui.printToConsole("There are no Patterns available in the DataStore.");
			} else {
				ui.printToConsole("The following patterns are available:\n");
				for(String name : patternNames) {
					if(ds.getPattern(name) instanceof Pattern) 
					{
						ui.printToConsole(name + "\n");
					}
				}
			}
			
		} else if (args.equalsIgnoreCase("Perceptrons")) {
			Set<String> perceptronNames = ds.getPerceptronNames();
			if (perceptronNames.size() == 0) {
				ui.printToConsole("There are no Perceptrons available in the DataStore.");
			} else {
				ui.printToConsole("The following Perceptrons are available:\n");
				for(String name : perceptronNames) {
					ui.printToConsole(name + "\n");
				}
			}
		} else if (args.equalsIgnoreCase("Groups")) {
			Set<String> patternNames = ds.getGenericPatternObjectNames();
			if (patternNames.size() == 0) {
				ui.printToConsole("There are no Patterngroups available in the DataStore.");
			} else {
				ui.printToConsole("The following groups of patterns are available:\n");
				for(String name : patternNames) {
					if(ds.getPattern(name) instanceof GroupPattern) 
					{
						ui.printToConsole(name + "\n");
					}
				}
			}
		} else {
			ui.printToConsole("Syntax Error: usage of list command: list [Patterns|Perceptrons|Groups]");
		}

	}

	/**
	 * @see de.thm.mni.nn.ui.IUIAction#getDescription()
	 */
	@Override
	public String getDescription() {
		return "Prints a list of alle Patterns or Perceptrons available.";
	}

}
