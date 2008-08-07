package com.google.android.sportsflash.mlb.teammanagement;

import com.google.android.sportsflash.mlb.data.*;
import com.google.android.sportsflash.R;
import com.google.android.sportsflash.SportsFlash;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Menu.Item;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;

/**
 * InviteLeagueMembers:  Invite People on your cell phone to be a part of your league 
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class InviteLeagueMembers extends Activity
{
	private SportsFlashLeagueDBHelper mDbHelper;
	private MLBCreateLeague mWSHelper;
	private EditText mLeagueName;
    private EditText mLeagueDescription;
	private String mLeagueNameValue;
    private String mLeagueDescriptionValue;
    private Long mRowId;
    private ProgressDialog progressDialog;
    
    public InviteLeagueMembers() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override    protected void onCreate(Bundle icicle) { 
    	super.onCreate(icicle); 
    	setContentView(R.layout.invite_members);
    	
        Button confirmButton = (Button) findViewById(R.id.leagueOkButton);
        Button cancelButton = (Button) findViewById(R.id.leagueCancelButton);
        
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {  
                Intent i = new Intent(InviteLeagueMembers.this, CreateTeam.class);
                startSubActivity(i, Constants.SUB_ACTIVITY_REQUEST_CODE);
           	
           }
          
        });
        
        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {        
               Intent i = new Intent(InviteLeagueMembers.this, SportsFlashTeamManagement.class);
               startSubActivity(i, Constants.SUB_ACTIVITY_REQUEST_CODE);
            	
            }
          
        });        
    }

}
