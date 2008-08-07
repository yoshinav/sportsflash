package com.google.android.sportsflash.mlb.data;

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