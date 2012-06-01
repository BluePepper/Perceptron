package de.thm.mni.nn.perceptron.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a list of grouped patterns
 * 
 * @author Tobias Knoth
 */

public class GroupPattern {
	/**
	 * The list with groups of patterns. Each group is a list of patterns.
	 */
	private Map<String, ArrayList<Pattern>> groupPattern = new HashMap<String, ArrayList<Pattern>>();

	/**
	 * Returns the number of groups
	 * 
	 * @return The number of pattern groups
	 */
	public Integer numberOfGroups() {
		return groupPattern.size();
	}

	/**
	 * Returns the number of patterns in a group
	 * 
	 * @param groupname
	 *            Needs a groupname to find the needed group
	 * @return The number of pattern in a group
	 */
	public Integer numberOfPatternPerGroup(String groupname) {
		if (groupPattern.get(groupname) == null) {
			return null;
		} else {
			return groupPattern.get(groupname).size();
		}
	}

	/**
	 * Adds one pattern to a existing patterngroup
	 * 
	 * @param groupname
	 *            Needs a groupname to find the existing group
	 * @param pattern
	 *            That's the pattern that is added to a group
	 * @return If the pattern was added successfully true is returned
	 */
	public boolean addPatternsToPatternGroup(String groupname, Pattern pattern) {
		if (groupPattern.get(groupname) == null) {
			return false;
		}
		groupPattern.get(groupname).add(pattern);
		return true;
	}

	/**
	 * Adds an empty list of patterns to a group
	 * 
	 * @param groupname
	 *            Needs a groupname to find the existing group
	 */

	public void addEmptyListOfPatternGroups(String groupname) {
		groupPattern.put(groupname, new ArrayList<Pattern>());
	}

	/**
	 * Adds a whole list of patterns to an existing pattern group
	 * 
	 * @param groupname
	 *            Needs a groupname to find the existing group
	 * @param patternList
	 *            That's the list of patterns to be added to the group
	 * @return If the patternlist was added successfully true is returned
	 */
	public boolean addArrayListOfPatternsToGroup(String groupname,
			ArrayList<Pattern> patternList) {
		if (groupPattern.get(groupname) == null) {
			return false;
		}
		groupPattern.put(groupname, patternList);
		return true;
	}

	/**
	 * Returns all patterns of a group in a list
	 * 
	 * @param groupname
	 *            Needs a groupname to find the existing group
	 * @return The patterns in a group are returned
	 */
	public ArrayList<Pattern> getAllPatternsOfGroup(String groupname) {
		if (groupPattern.get(groupname) == null) {
			return null;
		} else {
			return groupPattern.get(groupname);
		}
	}
}
