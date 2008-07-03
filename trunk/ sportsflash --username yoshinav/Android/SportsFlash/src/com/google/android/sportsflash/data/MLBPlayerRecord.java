package com.google.android.sportsflash.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean to represent a specific weather record as taken from Yahoo Weather API.
 * 
 * Units are: temperature="F" distance="mi" pressure="in" speed="mph"
 * 
 * @author charliecollins
 * 
 */
public class MLBPlayerRecord {


    

    private int id;
    private String firstName;
    private String lastName;
    private String position;
    private String team;
    private int hr;
    private int rbi;
    private double avg;
    private int saves;
    private int wins;
    private double era;
   
 
    public MLBPlayerRecord() {
       
    }


    public int getPlayerID() {
        return id;
    }

    public void setPlayerID(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getTeam() {
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    public int getHR() {
        return this.hr;
    }

    public void setHR(int hr) {
        this.hr = hr;
    }
    
    public int getRBI() {
        return this.rbi;
    }

    public void setRBI(int rbi) {
        this.rbi = rbi;
    }
    
    public double getAVG() {
        return this.avg;
    }

    public void setAVG(double avg) {
        this.avg = avg;
    }
    
    public int getSaves() {
        return this.saves;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }
    
    public int getWins() {
        return this.wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }    
    
    public double getERA() {
        return this.hr;
    }

    public void setERA(double era) {
        this.era = era;
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("MLBPlayer Record:");
        sb.append(" first name-" + this.firstName);
        sb.append(" last name-" + this.lastName);
        sb.append(" position-" + this.position);
        sb.append(" team-" + this.team);
        sb.append(" HR-" + this.hr);
        sb.append(" RBI-" + this.rbi);
        sb.append(" AVG-" + this.avg);
        sb.append(" Saves-" + this.saves);
        sb.append(" Wins-" + this.wins);
        sb.append(" ERA-" + this.era);

        return sb.toString();
    }
}