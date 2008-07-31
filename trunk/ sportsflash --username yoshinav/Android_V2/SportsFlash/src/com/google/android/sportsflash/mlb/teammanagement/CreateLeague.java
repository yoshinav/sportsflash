package com.google.android.sportsflash.mlb.teammanagement;

import com.google.android.sportsflash.mlb.data.*;
import com.google.android.sportsflash.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Menu.Item;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;

public class CreateLeague extends Activity
{
	private SportsFlashLeagueDBHelper mDbHelper;
	private MLBCreateLeague mWSHelper;
	private EditText mLeagueName;
    private EditText mLeagueDescription;
	private String mLeagueNameValue;
    private String mLeagueDescriptionValue;
    private Long mRowId;
    private static final int ACTIVITY_CREATE=0;
    
    public CreateLeague() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override    protected void onCreate(Bundle icicle) { 
    	super.onCreate(icicle); 
    	setContentView(R.layout.create_league);
    	
        mDbHelper = new SportsFlashLeagueDBHelper(this);
        mWSHelper = new MLBCreateLeague();
       
        mLeagueName = (EditText) findViewById(R.id.leagueName);
        mLeagueDescription = (EditText) findViewById(R.id.leagueDescription);
        mLeagueNameValue = mLeagueName.getText().toString();
        mLeagueDescriptionValue = mLeagueDescription.getText().toString();
        
        Button confirmButton = (Button) findViewById(R.id.leagueOkButton);
        Button cancelButton = (Button) findViewById(R.id.leagueCancelButton);
        
        mRowId = icicle != null ? icicle.getLong(mDbHelper.KEY_ROWID) : null;
        if(mRowId == null)
        {
        	Bundle extras = getIntent().getExtras();
        	mRowId = extras != null ? extras.getLong(mDbHelper.KEY_ROWID) : null;       	
        }
          
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {        
            	mDbHelper.createRow(mLeagueNameValue, mLeagueDescriptionValue);
            	mWSHelper.CreateLeague(mLeagueNameValue, mLeagueDescriptionValue);
            	
            }
          
        });
        
        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {        
               // Intent i = new Intent(this, SportsFlashTeamManagement.class);
               // startSubActivity(i, ACTIVITY_CREATE);
            	
            }
          
        });        
    }	

}
