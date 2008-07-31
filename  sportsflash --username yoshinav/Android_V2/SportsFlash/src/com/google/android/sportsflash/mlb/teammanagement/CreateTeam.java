package com.google.android.sportsflash.mlb.teammanagement;

import com.google.android.sportsflash.R;
import com.google.android.sportsflash.mlb.data.MLBCreateTeam;
import com.google.android.sportsflash.mlb.data.SportsFlashTeamDBHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Menu.Item;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;

public class CreateTeam extends Activity {

	private SportsFlashTeamDBHelper mDbHelper;
	private MLBCreateTeam mWSHelper;
	private EditText mTeamName;
	private String mTeamNameValue;
	private EditText mTeamDescription;
	private String mTeamNameDescription;	
    private Long mRowId;
    private static final int ACTIVITY_CREATE=0;
    
    public CreateTeam() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override    protected void onCreate(Bundle icicle) { 
    	super.onCreate(icicle); 
    	setContentView(R.layout.create_team);
    	
        mDbHelper = new SportsFlashTeamDBHelper(this);
        mWSHelper = new MLBCreateTeam();
       
        mTeamName = (EditText) findViewById(R.id.teamName);
        mTeamDescription = (EditText) findViewById(R.id.teamDescription);
       
        Button confirmButton = (Button) findViewById(R.id.teamOkButton);
        Button cancelButton = (Button) findViewById(R.id.teamCancelButton);
        
        mRowId = icicle != null ? icicle.getLong(mDbHelper.KEY_ROWID) : null;
        if(mRowId == null)
        {
        	Bundle extras = getIntent().getExtras();
        	mRowId = extras != null ? extras.getLong(mDbHelper.KEY_ROWID) : null;       	
        }
          
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) { 
                mTeamNameValue = mTeamName.getText().toString();
                mTeamNameDescription = mTeamDescription.getText().toString();              	
            	mDbHelper.createRow(mTeamNameValue, mTeamNameDescription);
            	mWSHelper.CreateTeam(99, mTeamNameValue, mTeamNameDescription);         	
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
