package com.google.android.sportsflash;
/**
 * Configuration:  Configuration file for SportsFlash Application
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */
public final class Configuration {
	
	public static final String urlServer = "192.168.1.108";
	
	public static final String urlGetMLBPlayersByPosition = "http://" + urlServer + "/sportsflashws/serviceSF.svc/rest/GetMLBPlayersbyPosition?position="; 

	public static final String urlCreateMLBLeague = "http://" + urlServer + "/sportsflashws/serviceSF.svc/rest/CreateMLBLeague?"; 

	public static final String urlCreateMLBTeam = "http://" + urlServer + "/sportsflashws/serviceSF.svc/rest/CreateMLBTeam?"; 
	
}