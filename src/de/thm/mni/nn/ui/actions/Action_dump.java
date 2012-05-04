/**
 * 
 */
package de.thm.mni.nn.ui.actions;

import java.util.List;

import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.Axon;
import de.thm.mni.nn.perceptron.impl.Neuron;
import de.thm.mni.nn.perceptron.impl.Perceptron;
import de.thm.mni.nn.ui.Action;
import de.thm.mni.nn.ui.UserInterface;

/**
 * Prints a whole Perceptron including its Neurons, Axons and the Axon weights.
 * @author Alexander Schulz
 *
 */
public class Action_dump extends Action {

	/**
	 * @param ds
	 * @param ui
	 */
	public Action_dump(DataStore ds, UserInterface ui) {
		super(ds, ui);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.nn.ui.IUIAction#callAction(java.lang.String)
	 */
	@Override
	public void callAction(String args) {
		String perceptronName;
		System.out.print("Name of perceptron to dump: ");
		perceptronName = ui.inputToString();
		
		Perceptron perceptron = ds.getPerceptron(perceptronName);
		if(perceptron == null) {
			ui.printToConsole("Error: There is no Perceptron named " + perceptronName);
			return;
		}
		ui.printToConsole("The Perceptron '" + perceptronName + "' has " + perceptron.getLayerCount() + " Layers and " + perceptron.getNeuronCount() + " Neurons.\n");

		// dump Neurons with Name
		for(int i = 0; i < perceptron.getLayerCount(); i++) {
			ui.printToConsole("Layer " + i + ":\n");
			for(int s = 0; s < perceptron.getNeuronsPerLayerCount(i); s++) {
				ui.printToConsole("Neuron" + i + "-" + s + "(" + perceptron.getNeuron(i, s).getActivationValue() + ")\t");
			}
			ui.printToConsole("\n");
		}
		// dump Axons
		ui.printToConsole("Axons:");
		for(int i = 0; i < perceptron.getLayerCount(); i++) {
			for(int s = 0; s < perceptron.getNeuronsPerLayerCount(i); s++) {
				Neuron src = perceptron.getNeuron(i, s);
				List<Axon> axons = src.getMyDendritesAsList();
				for (int j = 0; j < axons.size(); j++) {
					ui.printToConsole(perceptron.getNeuronName(axons.get(j).getSource()));
					ui.printToConsole(" -> ");
					ui.printToConsole(String.valueOf(axons.get(j).getWeight()));
					ui.printToConsole(" -> ");
					ui.printToConsole(perceptron.getNeuronName(axons.get(j).getTarget()) + "\n");
				}
			}
			ui.printToConsole("\n");
		}
		
		/*
		Layer 1:
			N1-1 N1-2 N1-3 N1-4
		Layer 2:
			N2-1 N2-2
			
		Axons:
			N1-1 -> 0.5 -> N2-1
		*/
				
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.nn.ui.IUIAction#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
