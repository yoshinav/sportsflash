package com.google.android.sportsflash.mlb.teammanagement;

import com.google.android.sportsflash.mlb.data.*;
import com.google.android.sportsflash.R;

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
    private ProgressDialog progressDialog;
    
    // use a Handler in order to update UI thread after worker done
    // (cannot update UI thread inline (not done yet), or from separate thread)
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            progressDialog.dismiss();
        }

    };
    
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
                mLeagueNameValue = mLeagueName.getText().toString();
                mLeagueDescriptionValue = mLeagueDescription.getText().toString();
            	CreateNewLeague(mLeagueNameValue, mLeagueDescriptionValue);
            	
           }
          
        });
        
        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {        
               // Intent i = new Intent(this, SportsFlashTeamManagement.class);
               // startSubActivity(i, ACTIVITY_CREATE);
            	
            }
          
        });        
    }
	
	public void CreateNewLeague(String leagueName, String leagueDescription)
	{
        progressDialog = ProgressDialog.show(this, " Working...", " Creating League", true, false);

        new Thread() {
            public void run() {
            	
                mLeagueNameValue = mLeagueName.getText().toString();
                mLeagueDescriptionValue = mLeagueDescription.getText().toString();
            	//mDbHelper.createRow(mLeagueNameValue, mLeagueDescriptionValue);
                Log.i(Constants.LOGTAG,"mLeagueNameValue= " + mLeagueNameValue + ", mLeagueDescriptionValue=" + mLeagueDescriptionValue);
            	mWSHelper.CreateLeague(mLeagueNameValue, mLeagueDescriptionValue);
            	handler.sendEmptyMessage(0);
            }
        }.start();	
	}

}
