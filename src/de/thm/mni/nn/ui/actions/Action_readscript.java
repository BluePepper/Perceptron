package de.thm.mni.nn.ui.actions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.thm.mni.nn.ui.IUIAction;

public class Action_readscript implements IUIAction {

	@Override
	public void callAction(String args) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(args));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {}
		} catch (IOException e) {
			System.out.println("Inputfile not Found!");
			return;
		}
	}

	@Override
	public String getDescription() {
		return "This Action allows to read and scripts into the internal data store. The scripts can be executed with \"execute <scriptname>\"";
	}

}
