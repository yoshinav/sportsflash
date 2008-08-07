package com.google.android.sportsflash.mlb.data;


/**
 * MLBTeam:  MLBTeam Data Object
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class MLBTeam {

    private int id;
    private int leagueId;
    private int teamId;
    private String leagueName;
    private String name;
    private String description;
    private int score;
    private int firstbaseId;
    private int secondbaseId;
    private int thirdbaseId;
    private int shortstopId;
    private int pitcherId;
    private int catcherId;
    private int dhitterId;
    private int leftfieldId;
    private int centerfieldId;
    private int rightfieldId;
    
    
    public MLBTeam() {
        
    }    
    
    public int getTeamID() {
        return id;
    }

    public void setTeamWSID(int teamId) {
        this.teamId = teamId;
    }
 
    public int getTeamWSID() {
        return teamId;
    }

    public void setTeamID(int id) {
        this.id = id;
    }
    
    public int getLeagueID() {
        return leagueId;
    }

    public void setLeagueID(int id) {
        this.leagueId = leagueId;
    }
    
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public String getTeamName() {
        return name;
    }

    public void setTeamName(String name) {
        this.name = name;
    }
    
    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }
    
    public String getTeamDescription() {
        return description;
    }

    public void setTeamDescription(String description) {
        this.description = description;
    }
    
    public int getTeam1bid() {
        return firstbaseId;
    }

    public void setTeam1bid (int firstbaseId) {
        this.firstbaseId = firstbaseId;
    }     
    
    public int getTeam2bid() {
        return secondbaseId;
    }

    public void setTeam2bid (int secondbaseId) {
        this.secondbaseId = secondbaseId;
    }   
    
    public int getTeam3bid() {
        return thirdbaseId;
    }

    public void setTeam3bid (int thirdbaseId) {
        this.thirdbaseId = thirdbaseId;
    }   
    
    public int getTeamssid() {
        return shortstopId;
    }

    public void setTeamssid (int shortstopId) {
        this.shortstopId = shortstopId;
    }       

    public int getTeampid() {
        return pitcherId;
    }

    public void setTeampid (int pitcherId) {
        this.pitcherId = pitcherId;
    }   
    
    public int getTeamcid() {
        return catcherId;
    }

    public void setTeamcid (int catcherId) {
        this.catcherId = catcherId;
    }
    
    public int getTeamlfid() {
        return leftfieldId;
    }

    public void setTeamlfid (int leftfieldId) {
        this.leftfieldId = leftfieldId;
    }
    
    public int getTeamrfid() {
        return rightfieldId;
    }

    public void setTeamrfid (int rightfieldId) {
        this.rightfieldId = rightfieldId;
    }       
    
    public int getTeamcfid() {
        return centerfieldId;
    }

    public void setTeamcfid (int centerfieldId) {
        this.centerfieldId = centerfieldId;
    }    
    
    public int getTeamdhid() {
        return firstbaseId;
    }

    public void setTeamdhid (int dhitterId) {
        this.dhitterId = dhitterId;
    }    
}
