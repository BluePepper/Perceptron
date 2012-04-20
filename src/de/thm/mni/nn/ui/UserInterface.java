package de.thm.mni.nn.ui;

import de.thm.mni.nn.ui.actions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class UserInterface implements Runnable {

	private boolean run = true;
	private Map<String, IUIAction> commands = new HashMap<String, IUIAction>();

	
	/**
	 * The Constructor trys to instantiate a Object for every given Command Name.
	 * If the instantiation is succesfull the command Name and the matching Object is saved in the 
	 * commands HashMap.
	 * @param activatedCommands
	 */
	@SuppressWarnings("unchecked")
	public UserInterface(List<String> activatedCommands) {
		Iterator<String> it = activatedCommands.iterator();
		while (it.hasNext()) {
			String cmd = it.next();
			Class<IUIAction> cl = null;
			IUIAction action = null;
			try {
				cl = (Class<IUIAction>) Class.forName("de.thm.mni.nn.ui.actions.Action_" + cmd);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try {
				action = (IUIAction) cl.getConstructor()
						.newInstance();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			commands.put(cmd, action);

		}

	}

	/**
	 * Main Loop
	 * The Mainloop waits for new Commands.
	 * The First Word of the typed Line is interpreted as Command.
	 */
	@Override
	public void run() {
		String CurLine = ""; // Line read from standard in

		System.out.println("Enter a line of text (type 'quit' to exit): ");
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);

		while (run) {
			try {
				CurLine = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!(CurLine.equals("quit"))) {
				System.out.println("You typed: " + CurLine);
			}
		}
	}
	
	/**
	 * The Stop Method schedules the STOP-Event.
	 * After the next Command the Mainloop ends.
	 */
	public void stop() {
		run = false;
	}

}
