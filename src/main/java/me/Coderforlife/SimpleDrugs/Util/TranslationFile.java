package me.Coderforlife.SimpleDrugs.Util;

import me.Coderforlife.SimpleDrugs.Druging.Drug;

public class TranslationFile {

	public static String Error_Cant_Use_Drug;
	public static String Error_Drug_Timer_Not_Zero;
	public static String Error_Cant_Use_Command;
	public static String Error_Drug_Does_Not_Exist;
	public static String Error_Recipe_Does_Not_Exist;
	
	public static String Error_Player_Does_Not_Exist;
	public static String Error_Player_Not_Online;
	
	public static String Name_Settings_GUI;
	
	// Sanitize
	// 		Drug
	
	// Seeds
	// 		Description
	// 		Seed
	
	// Error
	// 		Can't use that command
	//		Can't use that drug
	//		Can't use drug timer
	
	public TranslationFile() {
		
	}
	
	public static String sanitize(String s, String s2) {
		s = s.replaceAll("{{STRING}}", s2);
		return s;
	}
	
	public static String sanitize(String s, Long i) {
		s = s.replaceAll("{{SECONDS}}", i.toString());
		return s;
	}
	
	public static String sanitize(String s, Drug d) {
		s = s.replaceAll("{{DRUG}}", d.getName());
		return s;
	}
	
}