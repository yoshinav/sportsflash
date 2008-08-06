package com.google.android.sportsflash;
/**
 * Configuration:  Configuration file for SportsFlash Application
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 * This class will define the API Interface to our server resources.
 * Configurable to allow for changes in the future.
 */
public final class Configuration {
	
	public static final String urlServer = "192.168.1.108";
	
	public static final String urlGetMLBPlayersByPosition = "http://" + urlServer + "/sportsflashws/serviceSF.svc/rest/GetMLBPlayersByPosition?position="; 

	public static final String urlGetMLBPlayers = "http://" + urlServer + "/sportsflashws/serviceSF.svc/rest/GetMLBPlayers"; 

	public static final String urlCreateMLBLeague = "http://" + urlServer + "/sportsflashws/serviceSF.svc/rest/CreateMLBLeague?"; 

	public static final String urlCreateMLBTeam = "http://" + urlServer + "/sportsflashws/serviceSF.svc/rest/CreateMLBTeam?"; 
	
	public static final String urlGetMLBPlayerByID = "http://" + urlServer + "/sportsflashws/serviceSF.svc/rest/GetMLBPlayerByIdentifier?id="; 
	
	public static final String urlUpdateMLBPlayerByID = "http://" + urlServer + "/sportsflashws/serviceSF.svc/rest/UpdateMLBPlayer?"; 
	
}
