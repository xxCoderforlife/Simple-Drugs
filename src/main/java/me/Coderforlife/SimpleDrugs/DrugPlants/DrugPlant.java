package me.Coderforlife.SimpleDrugs.DrugPlants;

import me.Coderforlife.SimpleDrugs.Druging.Drug;

public class DrugPlant {

	private Drug harvestDrug;
	private Integer harvestAmount;
	
	public DrugPlant(Drug d, Integer i) {
		harvestDrug = d;
		harvestAmount = i;
	}
	
	public Drug getDrug() {
		return harvestDrug;
	}
	
	public Integer getHarvestAmount() {
		return harvestAmount;
	}
	
}