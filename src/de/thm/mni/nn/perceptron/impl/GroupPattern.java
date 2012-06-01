package de.thm.mni.nn.perceptron.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupPattern {
	private Map<String, ArrayList<Pattern>> groupPattern = new HashMap<String, ArrayList<Pattern>>(); 

	public Integer numberOfGroups() {
		return groupPattern.size();
	}
	
	public Integer numberOfPatternPerGroup(String groupname) {
		if (groupPattern.get(groupname) == null) {
			return null;
		} else {
			return groupPattern.get(groupname).size();
		}
	}
	
	public boolean addPatternsToPatternGroup(String groupname, Pattern pattern) {
		if(groupPattern.get(groupname) == null) {
			return false;
		}
		groupPattern.get(groupname).add(pattern);
		return true;
	}
	
	public boolean addEmptyListOfPatternGroups(String groupname) {
		groupPattern.put(groupname, new ArrayList<Pattern>());
		return true;
	}
	public boolean addArrayListOfPatternsToGroup(String groupname, ArrayList<Pattern> patternList) {
		if(groupPattern.get(groupname) == null) {
			return false;
		}
		groupPattern.put(groupname, patternList);
		return true;
	}
	
	public ArrayList<Pattern> getAllPatternsOfGroup(String groupname) {
		if(groupPattern.get(groupname) == null) {
			return null;
		} else {
			return groupPattern.get(groupname);
		}
		
	}

}
