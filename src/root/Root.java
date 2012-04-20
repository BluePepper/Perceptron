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
		
		UserInterface ui = new UserInterface(activatedCommands);
		Thread t = new Thread(ui);
		t.start();

	}

}
