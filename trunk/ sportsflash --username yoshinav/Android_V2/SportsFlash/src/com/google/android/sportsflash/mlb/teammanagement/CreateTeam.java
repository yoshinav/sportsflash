package com.google.android.sportsflash.mlb.teammanagement;

import com.google.android.sportsflash.R;
import com.google.android.sportsflash.mlb.data.MLBCreateTeam;
import com.google.android.sportsflash.mlb.data.SportsFlashTeamDBHelper;

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
 * CreateTeam:  Create MLB Fantasy League Team
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class CreateTeam extends Activity {

	private SportsFlashTeamDBHelper mDbHelper;
	private MLBCreateTeam mWSHelper;
	private EditText mTeamName;
	private String mTeamNameValue;
	private EditText mTeamDescription;
	private String mTeamDescriptionValue;	
    private Long mRowId;
    private static final int ACTIVITY_CREATE=0;
    private ProgressDialog progressDialog;
    
    // use a Handler in order to update UI thread after worker done
    // (cannot update UI thread inline (not done yet), or from separate thread)
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            progressDialog.dismiss();
        }

    };
    
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
            	CreateNewTeam(); 
                Intent i = new Intent(CreateTeam.this, SportsFlashTeamManagement.class);
                startSubActivity(i, Constants.SUB_ACTIVITY_REQUEST_CODE);             	
            }         
        });
        
        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {        
                Intent i = new Intent(CreateTeam.this, SportsFlashTeamManagement.class);
                startSubActivity(i, Constants.SUB_ACTIVITY_REQUEST_CODE);        	
            }
          
        });        
    }
	
	public void CreateNewTeam()
	{
        progressDialog = ProgressDialog.show(this, " Working...", " Creating Team", true, false);

        new Thread() {
            public void run() {
                mTeamNameValue = mTeamName.getText().toString();
                mTeamDescriptionValue = mTeamDescription.getText().toString();            	
             	mDbHelper.createRow(mTeamNameValue, mTeamDescriptionValue);
            	mWSHelper.CreateTeam(com.google.android.sportsflash.SportsFlash.currentLeagueID, mTeamNameValue, mTeamDescriptionValue);  
            	handler.sendEmptyMessage(0);
            }
        }.start();	
	}
	
}
