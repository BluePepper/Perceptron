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
		activatedCommands.add("load");
		activatedCommands.add("add");
		activatedCommands.add("propagate");
		
		// TODO: list Function
		// TODO: print tree Function
		// TODO: list Axon weights function?
		
		UserInterface ui = new UserInterface(activatedCommands, true);
		Thread t = new Thread(ui);
		t.start();
	}

}
