package de.thm.mni.nn.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.thm.mni.nn.perceptron.impl.Perceptron;

/**
 * A DataStore Object holds any value of Perceptrons and Patterns or groups of patterns.
 * The Perceptrons, groups of patterns or Patterns are identified by a Name.
 * To get or set an entry the Name is needed.
 * @author Alexander Schulz
 *
 */
public class DataStore {
	private Map<String, Perceptron> perceptrons = new HashMap<String, Perceptron>();
	private Map<String, Object> genericPatterList = new HashMap<String, Object>();
	
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
	 * @return Set of all generic Pattern Names
	 */
	public Set<String> getGenericPatternObjectNames() {
		return genericPatterList.keySet();
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
	 * Adds a Pattern object to the Datastore.
	 * @param name the name of the Pattern
	 * @param pattern The Pattern Object (It could be an pattern or a group of patterns)
	 * @return returns false if the name is already in use
	 */
	public boolean addPatternObject(String name, Object patternType) {
		for (String a : genericPatterList.keySet()) {
			if (name.equalsIgnoreCase(a)) {
				return false;
			}
		}
		genericPatterList.put(name, patternType);
		return true;
	}
	
	/**
	 * Get a Pattern or a group of Patterns from the DataStore
	 * @param name the Name of the saved generic pattern object
	 * @return Returns the Pattern object. If the Name could not be found null is returned
	 */
	public Object getPattern(String name) {
		for (String a : genericPatterList.keySet()) {
			if (name.equalsIgnoreCase(a)) {
				return genericPatterList.get(a);
			}
		}	
		return null;
	}
}
