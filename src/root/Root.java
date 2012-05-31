package root;

import java.util.ArrayList;
import java.util.List;
import de.thm.mni.nn.ui.UserInterface;

public class Root {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> activatedCommands = new ArrayList<String>();
		
		activatedCommands.add("exit");
		//activatedCommands.add("load"); // Temp disabled; not used since readfile
		activatedCommands.add("add");
		activatedCommands.add("propagate");
		activatedCommands.add("list");
		activatedCommands.add("dump");
		activatedCommands.add("train");
		activatedCommands.add("readfile");
		activatedCommands.add("draw");
		
		// TODO: 2 (Alex) delete function for patterns and whole perceptrons
		// TODO: 4 () Help Command
		// TODO: 1 (Marcel) Test if there is a problem with neurons that aren't connected in propagation 
		// TODO: 3 (Alex) Train: Regression Function for learning rate
		// TODO: 5 () Console History
		// TODO: 5 () Logging
		// TODO: 1 (Tobias) Pattern Groups
		// TODO: 3 (Marcel) Scripting Files
		// TODO: 2 (Tobias) Rewrite Commands to single line
		// TODO: 3 (Alex) Rewrite console outputs
		// TODO: 1 (Marcel) Implement Pattern Groups in READFILE-Command
		// TODO: 2 (Tobias) Implement Pattern Groups in DUMP-Command
		// TODO: 1 (Alex) Parse Input from Task 4
		// TODO: 1 (Alex) Errorvalue implementation (in training)
		// TODO: 3 (Alle) Documentation
		// TODO: 1 (Alex) Code review for Patterngroups
		
		UserInterface ui = new UserInterface(activatedCommands, true);
		Thread t = new Thread(ui);
		t.start();
	}

}
