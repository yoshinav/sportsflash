package com.google.android.sportsflash.mlb.data;
/**
 * MLBLeaguer:  League Data Object
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class MLBLeague {

    private int id;
    private int leagueId;
    private String name;
    private String description;
    
    public MLBLeague() {
        
    }    
    
    public int getLeagueID() {
        return id;
    }

    public void setLeagueWSID(int leagueId) {
        this.leagueId = leagueId;
    }
 
    public int getLeagueWSID() {
        return leagueId;
    }

    public void setLeagueID(int id) {
        this.id = id;
    }
    
    public String getLeagueName() {
        return name;
    }

    public void setLeagueName(String name) {
        this.name = name;
    }
    
    public String getLeagueDescription() {
        return description;
    }

    public void setLeagueDescription(String description) {
        this.description = description;
    }   
}
