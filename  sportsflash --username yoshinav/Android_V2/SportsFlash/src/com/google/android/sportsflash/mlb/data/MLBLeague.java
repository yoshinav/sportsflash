package com.google.android.sportsflash.mlb.data;

public class MLBLeague {

    private int id;
    private String name;
    private String description;
    
    public MLBLeague() {
        
    }    
    
    public int getLeagueID() {
        return id;
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
