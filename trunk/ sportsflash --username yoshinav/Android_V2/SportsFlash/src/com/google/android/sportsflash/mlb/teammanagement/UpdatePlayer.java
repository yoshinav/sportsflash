package com.google.android.sportsflash.mlb.teammanagement;

/**
 * MLBPlayerView:  View for MLB Players Roster
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

import java.util.List;

import com.google.android.sportsflash.mlb.data.*;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.google.android.sportsflash.R;
import android.app.Activity;

public class UpdatePlayer extends Activity {

		private SportsFlashTeamDBHelper mDbHelper;
		private MLBCreateTeam mWSHelper;

	    private ProgressDialog progressDialog;

	    private List<MLBPlayer> players;
	    private MLBPlayerAdapter playerAdapter;

	    private TextView empty;
		private EditText mPlayerHR;
		private String mPlayerHRValue;	
		private EditText mPlayerBA;
		private String mPlayerBAValue;	
		private EditText mPlayerRBI;
		private String mPlayerRBIValue;	
		private EditText mPlayerSaves;
		private String mPlayerSavesValue;	
		private EditText mPlayerWins;
		private String mPlayerWinsValue;	
		private EditText mPlayerERA;
		private String mPlayerERAValue;		
		private Long mRowId;
   
	    public UpdatePlayer() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    // use a Handler in order to update UI thread after worker done
	    // (cannot update UI thread inline (not done yet), or from separate thread)
	    private Handler handler = new Handler() {

	        @Override
	        public void handleMessage(Message msg) {
	            progressDialog.dismiss();
	        }

	    };
	    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " onCreate");

        setDefaultKeyMode(SHORTCUT_DEFAULT_KEYS);
        
        setContentView(R.layout.update_mlbplayer);
        
        mDbHelper = new SportsFlashTeamDBHelper(this);
        mWSHelper = new MLBCreateTeam();
       
        mPlayerHR = (EditText) findViewById(R.id.playerHR);
        mPlayerBA = (EditText) findViewById(R.id.playerBA);
        mPlayerRBI = (EditText) findViewById(R.id.playerRBI);
        mPlayerWins = (EditText) findViewById(R.id.playerWins);
        mPlayerSaves = (EditText) findViewById(R.id.playerSaves);
        mPlayerERA = (EditText) findViewById(R.id.playerERA);
        
        Button confirmButton = (Button) findViewById(R.id.playerOkButton);
        Button cancelButton = (Button) findViewById(R.id.playerCancelButton);
        
        mRowId = icicle != null ? icicle.getLong(mDbHelper.KEY_ROWID) : null;
        if(mRowId == null)
        {
        	Bundle extras = getIntent().getExtras();
        	mRowId = extras != null ? extras.getLong(mDbHelper.KEY_ROWID) : null;       	
        }
        
        //loadPlayer(2);
        //fillData();
        
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) { 
            	UpdatePlayer(); 
                Intent i = new Intent(UpdatePlayer.this, SportsFlashTeamManagement.class);
                startSubActivity(i, Constants.SUB_ACTIVITY_REQUEST_CODE);             	
            }         
        });
        
        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {        
                Intent i = new Intent(UpdatePlayer.this, SportsFlashTeamManagement.class);
                startSubActivity(i, Constants.SUB_ACTIVITY_REQUEST_CODE);        	
            }
          
        });
        
    }

    private void fillData() {
    	/*
        // Get all of the rows from the database and create the item list
    	Cursor notesCursor = mDbHelper.fetchAllNotes();
        startManagingCursor(notesCursor);
        
        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{LeagueDbAdapter.KEY_TITLE};
        
        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.text1};
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter notes = 
        	    new SimpleCursorAdapter(this, R.layout.notes_row, notesCursor, from, to);
        setListAdapter(notes);
        */
    }
    
	@Override
	protected void onFreeze(Bundle outState) {
		// TODO Auto-generated method stub
		super.onFreeze(outState);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}
	
    private void loadPlayer(String position) {
 /*
        final MLBPlayerFetcher rf = new MLBPlayerFetcher();
        final String playerPosition = position;

        progressDialog = ProgressDialog.show(this, " Working...", " Retrieving players", true, false);

        // get reviews in a separate thread for ProgressDialog/Handler
        // when complete send "empty" message to handler indicating thread is
        // done
        // TODO will handler clean up separate thread (or do need to implement
        // onDestroy?)
        new Thread() {
            public void run() {
                //players = rf.getMockPlayers();
            	players = rf.getMLBPlayer(position);
            	//players.add(rf.getMLBPlayer());
                handler.sendEmptyMessage(0);
            }
        }.start();
        */
    }
    
	public void UpdatePlayer()
	{
        progressDialog = ProgressDialog.show(this, " Working...", " Updating Player", true, false);

        new Thread() {
            public void run() {
            	
            	mPlayerHRValue = mPlayerHR.getText().toString();
            	mPlayerBAValue = mPlayerBA.getText().toString(); 
                mPlayerRBIValue = mPlayerRBI.getText().toString();
                mPlayerWinsValue = mPlayerWins.getText().toString();
                mPlayerSavesValue = mPlayerSaves.getText().toString();
                mPlayerERAValue = mPlayerERA.getText().toString();
                
                //ToDO
             	//mDbHelper.createRow(mTeamNameValue, mTeamDescriptionValue);
            	//mWSHelper.CreateTeam(com.google.android.sportsflash.SportsFlash.currentLeagueID, mTeamNameValue, mTeamDescriptionValue);  
            	handler.sendEmptyMessage(0);
            }
        }.start();	
	}    
   
}
