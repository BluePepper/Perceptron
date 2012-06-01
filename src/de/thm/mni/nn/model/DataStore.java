package de.thm.mni.nn.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.thm.mni.nn.perceptron.impl.GroupPattern;
import de.thm.mni.nn.perceptron.impl.Pattern;
import de.thm.mni.nn.perceptron.impl.Perceptron;

/**
 * A DataStore Object holds any value of Perceptrons and PAtterns.
 * The Perceptrons or Patterns are identified by a Name.
 * To get or set an entry the Name is needed.
 * @author Alexander Schulz
 *
 */
public class DataStore {
	private Map<String, Perceptron> perceptrons = new HashMap<String, Perceptron>();
	private Map<String, Object> genericList = new HashMap<String, Object>();
	
	/**
	 * Adds a Perceptron to the Datastore.
	 * @param name the name of the Perceptron
	 * @param perceptron The Perceptron Object
	 * @return returns false if the name is already in use
	 */
	public boolean addPerceptron(String name, Perceptron perceptron) {
		for (String a : perceptrons.keySet()) {
			if (name.equalsIgnoreCase(a)) {
				return false;
			}
		}
		perceptrons.put(name, perceptron);
		return true;
	}
	
	/**
	 * Returns a Set of String holding all Pattern Names in the DataStore.
	 * @return Set of all Pattern Names
	 */
	public Set<String> getPatternNames2() {
		return genericList.keySet();
	}
	
	/**
	 * Returns a Set of String holding all Perceptron Names in the DataStore.
	 * @return Set of all Perceptron Names
	 */
	public Set<String> getPerceptronNames() {
		return perceptrons.keySet();
	}
	
	/**
	 * Get a Perceptron from the DataStore
	 * @param name the Name of the saved Perceptron
	 * @return Returns the Perceptron. If the Name could not be found null is returned
	 */
	public Perceptron getPerceptron(String name) {
		for (String a : perceptrons.keySet()) {
			if (name.equalsIgnoreCase(a)) {
				return perceptrons.get(a);	
			}
		}	
		return null;
	}
	
	/**
	 * Adds a Pattern to the Datastore.
	 * @param name the name of the Pattern
	 * @param pattern The Pattern Object
	 * @return returns false if the name is already in use
	 */
	public boolean addPatternObject(String name, Object patternType) {
		for (String a : genericList.keySet()) {
			if (name.equalsIgnoreCase(a)) {
				return false;
			}
		}
		genericList.put(name, patternType);
		return true;
	}
	
	/**
	 * Get a Pattern from the DataStore
	 * @param name the Name of the saved Perceptron
	 * @return Returns the Perceptron. If the Name could not be found null is returned
	 */
	public Object getPattern(String name) {
		for (String a : genericList.keySet()) {
			if (name.equalsIgnoreCase(a)) {
				return genericList.get(a);
			}
		}	
		return null;
	}
}
