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
		activatedCommands.add("load"); // Temp disabled; not used since readfile
		activatedCommands.add("add");
		activatedCommands.add("propagate");
		activatedCommands.add("list");
		activatedCommands.add("dump");
		activatedCommands.add("train");
		activatedCommands.add("readfile");
		activatedCommands.add("draw");
		
		// TODO: delete function for patterns and whole perceptrons
		// TODO: dump for Patterns
		// TODO: Help Command
		// TODO: Test if there is a problem with neurons that aren't connected in propagation
		// TODO: Train: Regression Function for learning rate
		// TODO: Console History
		// TODO: Logging
		// TODO: Training Function is set for each train seperately
		
		UserInterface ui = new UserInterface(activatedCommands, true);
		Thread t = new Thread(ui);
		t.start();
	}

}
