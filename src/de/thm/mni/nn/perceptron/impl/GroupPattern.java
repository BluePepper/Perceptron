package de.thm.mni.nn.perceptron.impl;

import java.util.ArrayList;

/**
 * Class representing a list of grouped patterns
 * 
 * @author Tobias Knoth
 */

public class GroupPattern {
	/**
	 * The list with groups of patterns. Each group is a list of patterns.
	 */
	private ArrayList<Pattern> group = new ArrayList<Pattern>();

	/**
	 * Name of the Group.
	 */
	private String name = "";
	
	public GroupPattern(String groupName){
		this.name = groupName;
	}
	
	/**
	 * Delivers the name of the group.
	 * @return Name of the group.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the number of patterns in the group
	 * 
	 * @return The number of patterns in the group
	 */
	public Integer numberOfPatterns() {
		return group.size();
	}

	/**
	 * Adds one pattern to the group
	 * 
	 * @param pattern
	 *            That's the pattern that is added to the group
	 * @return If the pattern was added successfully true is returned
	 */
	public boolean addPattern(Pattern pattern) {
		if (group.contains(pattern)) {
			return false;
		}
		group.add(pattern);
		return true;
	}

	/**
	 * Returns all patterns of a group in a list
	 * 
	 * @param groupname
	 *            Needs a groupname to find the existing group
	 * @return The patterns in a group are returned
	 */
	public ArrayList<Pattern> getAllPatterns() {
		return group;
	}
}
