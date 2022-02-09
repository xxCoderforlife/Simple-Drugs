package me.Coderforlife.SimpleDrugs.GUI;

import me.Coderforlife.SimpleDrugs.GUI.DrugCreatorTest.Util.SDObjectType;

public class CreatingName {

	private String name;
	private SDObjectType sdot;
	
	public CreatingName(SDObjectType type) {
		sdot = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String s) {
		name = s;
	}
	
	public SDObjectType getType() {
		return sdot;
	}
	
}