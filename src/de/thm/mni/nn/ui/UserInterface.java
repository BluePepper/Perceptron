package de.thm.mni.nn.ui;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.ui.actions.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unused")
public class UserInterface implements Runnable {

	private boolean run = true;
	private Map<String, IUIAction> commands = new HashMap<String, IUIAction>();
	private DataStore ds = new DataStore();

	
	/**
	 * The Constructor trys to instantiate a Object for every given Command Name.
	 * If the instantiation is succesfull the command Name and the matching Object is saved in the 
	 * commands HashMap.
	 * @param activatedCommands Command Name List
	 * @param debug if debug is set to True a stackTrace is printed if anything goes wrong, otherwise the relating cmd is not loaded
	 */
	@SuppressWarnings("unchecked")
	public UserInterface(List<String> activatedCommands, boolean debug) {
		Iterator<String> it = activatedCommands.iterator();
		while (it.hasNext()) {
			String cmd = it.next();
			cmd = cmd.toLowerCase(); // Command Names always lc
			Class<IUIAction> cl = null;
			IUIAction action = null;
			try {
				cl = (Class<IUIAction>) Class.forName("de.thm.mni.nn.ui.actions.Action_" + cmd);
			} catch (ClassNotFoundException e) {
				System.out.println("Command '" + cmd + "' not Found. Command not loaded!");
				if (debug) { e.printStackTrace(); }
			}
			try {
				action = (IUIAction) cl.getConstructor(DataStore.class, UserInterface.class)
						.newInstance(ds, this);
			} catch (IllegalArgumentException e) {
				System.out.println("Command '" + cmd + "' could not be instantiated. Command not loaded!");
				if (debug) { e.printStackTrace(); }
			} catch (SecurityException e) {
				System.out.println("Command '" + cmd + "' could not be instantiated. Command not loaded!");
				if (debug) { e.printStackTrace(); }
			} catch (InstantiationException e) {
				System.out.println("Command '" + cmd + "' could not be instantiated. Command not loaded!");
				if (debug) { e.printStackTrace(); }
			} catch (IllegalAccessException e) {
				System.out.println("Command '" + cmd + "' could not be instantiated. Command not loaded!");
				if (debug) { e.printStackTrace(); }
			} catch (InvocationTargetException e) {
				System.out.println("Command '" + cmd + "' could not be instantiated. Command not loaded!");
				if (debug) { e.printStackTrace(); }
			} catch (NoSuchMethodException e) {
				System.out.println("Command '" + cmd + "' could not be instantiated. Command not loaded!");
				if (debug) { e.printStackTrace(); }
			}
			// Add Command to the Command List
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
		String[] line = null;
		String args = null;
		String cmd = null;

		System.out.println("Neuronales Netz gestartet... Es sind folgende Kommandos aktiv:");
		printCommandList(); // Geladene Kommandos ausgeben
		
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);

		while (run) {
			System.out.print("\n > ");
			try {
				CurLine = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			line = CurLine.split(" ", 2);
			cmd = line[0];
			if(line.length > 1) {
				args = line[1];
			}
			
			// search for command
			IUIAction action = getObjectForCmd(cmd);
			if (action != null) {
				action.callAction(args);
			} else {
				printToConsole("Unknown Command: '" + cmd + "'");
			}
			
			CurLine = "";
			line = null;
			cmd = null;
			args = null;
		}
	}
	
	/**
	 * Search for a given Command
	 * prints an Error Message if the given Command could not be found
	 * @param cmd
	 */
	private IUIAction getObjectForCmd(String cmd) {
		for(Iterator<String> it = commands.keySet().iterator(); it.hasNext();) {
			String actVal = it.next();
			if(actVal.equalsIgnoreCase(cmd)) {
				return commands.get(actVal);
			}
		}
		return null;
	}
	
	/**
	 * Sends text to Console without newline!
	 * BUT! A Newline is printed at the Start of the Loop.
	 * @param text
	 */
	private void printToConsole(String text) {
		System.out.print(text);
	}
	
	/**
	 * prints a List of all loaded Commands
	 */
	private void printCommandList() {
		Iterator<String> it = commands.keySet().iterator();
		while(it.hasNext()) {
			String actVal = it.next();
			printToConsole(actVal);
			printToConsole(" - ");
			printToConsole(commands.get(actVal).getDescription() + "\n");
		}
		printToConsole("\n");
	}
	
	/**
	 * The Stop Method schedules the STOP-Event.
	 * After the next Command the Mainloop ends.
	 */
	public void stop() {
		run = false;
	}

}
