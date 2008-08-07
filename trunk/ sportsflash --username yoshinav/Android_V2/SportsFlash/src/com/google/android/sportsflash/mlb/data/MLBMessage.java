package com.google.android.sportsflash.mlb.data;

/**
 * MLBMessage:  MLBMessage Data Object
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class MLBMessage {

	private int id;
	private String title;
	private String message;
	
	public MLBMessage() {
	    
	}    
	
	public int getMsgD() {
	    return id;
	}
	
	public void setMsgID(int msgId) {
	    this.id = msgId;
	}
	
	
	public String getMsgTitle() {
	    return title;
	}
	
	public void setMsgTitle(String title) {
	    this.title = title;
	}
	
	public String getMsgDescription() {
	    return message;
	}
	
	public void setMsgDescription(String message) {
	    this.message = message;
	} 
}