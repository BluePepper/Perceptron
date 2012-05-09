/**
 * 
 */
package de.thm.mni.nn.ui.actions;

import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;

import de.graphViz.GraphViz;
import de.thm.mni.nn.model.DataStore;
import de.thm.mni.nn.perceptron.impl.Axon;
import de.thm.mni.nn.perceptron.impl.Neuron;
import de.thm.mni.nn.perceptron.impl.Perceptron;
import de.thm.mni.nn.ui.Action;
import de.thm.mni.nn.ui.ShowImage;
import de.thm.mni.nn.ui.UserInterface;

/**
 * !!!!!------------------------------------------------------------------------
 * ------------ Till now It's just a basic implementation to show that it's
 * possible to draw a tree with graphViz. To see the picture it's required to
 * install. http://www.graphviz.org/ The programm is available for Linux, Mac
 * and Windows.
 * !!!!!------------------------------------------------------------
 * ------------------------ Draws a whole Perceptron including its Neurons,
 * Axons and the Axon weights in a JPanel.
 * 
 * @author Tobias Knoth
 * 
 */
public class Action_draw extends Action {

	BufferedImage image;

	/**
	 * @param ds
	 * @param ui
	 */
	public Action_draw(DataStore ds, UserInterface ui) {
		super(ds, ui);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.mni.nn.ui.IUIAction#callAction(java.lang.String)
	 */
	@Override
	public void callAction(String args) {
		GraphViz gv = new GraphViz();
		gv.addln(gv.start_graph());

		String perceptronName;
		System.out.print("Name of perceptron to dump: ");
		perceptronName = ui.inputToString();

		Perceptron perceptron = ds.getPerceptron(perceptronName);
		if (perceptron == null) {
			ui.printToConsole("Error: There is no Perceptron named "
					+ perceptronName);
			return;
		}
		ui.printToConsole("The Perceptron '" + perceptronName + "' has "
				+ perceptron.getLayerCount() + " Layers and "
				+ perceptron.getNeuronCount() + " Neurons.\n");

		for (int i = 0; i < perceptron.getLayerCount(); i++) {
			for (int s = 0; s < perceptron.getNeuronsPerLayerCount(i); s++) {
				Neuron src = perceptron.getNeuron(i, s);
				List<Axon> axons = src.getMyDendritesAsList();
				for (int j = 0; j < axons.size(); j++) {

					/* It's recommended to install graphViz version >= 0.29 to get
					 * the view of the activationValue working.
					 */
					double axonWeight = axons.get(j).getWeight();
					axonWeight = Math.round(axonWeight*1000)/1000d;
					
					gv.addln("node [color=red, fontcolor=red]");
					gv.addln("forcelabels=true");
							Neuron source = axons.get(j).getSource();
							Neuron target = axons.get(j).getSource();
							gv.addln(perceptron.getNeuronName(axons.get(j).getSource())	+ " [xlabel="+source.getActivationValue()+"];");
							gv.addln(perceptron.getNeuronName(axons.get(j).getTarget())	+ " [xlabel="+target.getActivationValue()+", color=red];");
							gv.addln(perceptron.getNeuronName(axons.get(j).getSource())
							+ " -> "
							+ perceptron.getNeuronName(axons.get(j).getTarget())
							+ "[ label="
							+ String.valueOf(axonWeight) + " ];");
				}
			}
		}

		gv.addln(gv.end_graph());

		String type = "jpg";
		File out = new File("neuronalNet." + type); // out.gif in this example
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);

		JFrame frame = new JFrame("Display image");
		Panel panel = new ShowImage(out);
		frame.getContentPane().add(panel);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.thm.mni.nn.ui.IUIAction#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Draw the tree as an image! The installation of graphWiz is recommended!";
	}

}
