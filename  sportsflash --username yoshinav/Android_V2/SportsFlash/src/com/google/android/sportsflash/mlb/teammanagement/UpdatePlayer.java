package com.google.android.sportsflash.mlb.teammanagement;

/**
 * UpdatePlayer:  This Activity is the Form to update the players Statistics
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

import java.util.List;

import com.google.android.sportsflash.mlb.data.*;
import com.google.android.sportsflash.SportsFlash;

import android.app.AlertDialog;
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
import android.view.Menu.Item;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.google.android.sportsflash.R;
import android.app.Activity;

public class UpdatePlayer extends Activity {

		private SportsFlashTeamDBHelper mDbHelper;
		private MLBUpdatePlayer mWSHelper;
		
		public static final int TEAM_MANAGEMENT = Menu.FIRST;
		private static final int ACTIVITY_CREATE=0;

	    private ProgressDialog progressDialog;

	    private MLBPlayer player;
	    private MLBPlayerAdapter playerAdapter;

	    private TextView empty;
	    private TextView mPlayerInfo;
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
	        	
	        	mPlayerInfo.setText("Player: " + player.getLastName() + "," + player.getFirstName());
	            mPlayerHR.setText(Integer.toString(player.getHR()));
	            mPlayerBA.setText(Integer.toString(player.getAVG()));
	            mPlayerRBI.setText(Integer.toString(player.getRBI()));
	            mPlayerWins.setText(Integer.toString(player.getWins()));
	            mPlayerSaves.setText(Integer.toString(player.getSaves()));
	            mPlayerERA.setText(Integer.toString(player.getERA()));
	            
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
        mWSHelper = new MLBUpdatePlayer();
       
        loadPlayer(SportsFlash.getCurrentPlayerID());
        
        mPlayerInfo = (TextView) findViewById(R.id.playerInfo);
        mPlayerHR = (EditText) findViewById(R.id.playerHR);
        mPlayerBA = (EditText) findViewById(R.id.playerBA);
        mPlayerRBI = (EditText) findViewById(R.id.playerRBI);
        mPlayerWins = (EditText) findViewById(R.id.playerWins);
        mPlayerSaves = (EditText) findViewById(R.id.playerSaves);
        mPlayerERA = (EditText) findViewById(R.id.playerERA);
           
        Button confirmButton = (Button) findViewById(R.id.playerOkButton);
        Button cancelButton = (Button) findViewById(R.id.playerCancelButton);
        
        
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) { 
            	//Check for valid input
            	if(!AuthenticatedInput())
            	{
            		return;
            	}
            	
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
	
    private void loadPlayer(int id) {
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " loadPlayers");
        
        final MLBPlayerFetcher rf = new MLBPlayerFetcher();
        final int idValue = id;
        progressDialog = ProgressDialog.show(this, " Working...", " Retrieving player's Data", true, false);

        // get reviews in a separate thread for ProgressDialog/Handler
        // when complete send "empty" message to handler indicating thread is
        // done
        // TODO will handler clean up separate thread (or do need to implement
        // onDestroy?)
        new Thread() {
            public void run() {
            	player = rf.getMLBPlayerByID(idValue);
                handler.sendEmptyMessage(0);
            }
        }.start();
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
            	mWSHelper.UpdatePlayer(Integer.toString(SportsFlash.getCurrentPlayerID()), mPlayerHRValue, mPlayerBAValue, mPlayerRBIValue, mPlayerWinsValue, mPlayerSavesValue, mPlayerERAValue); 
            	handler.sendEmptyMessage(0);
            }
        }.start();	
	}    
   
	public boolean AuthenticatedInput()
	{
    	mPlayerHRValue = mPlayerHR.getText().toString();
    	mPlayerBAValue = mPlayerBA.getText().toString(); 
        mPlayerRBIValue = mPlayerRBI.getText().toString();
        mPlayerWinsValue = mPlayerWins.getText().toString();
        mPlayerSavesValue = mPlayerSaves.getText().toString();
        mPlayerERAValue = mPlayerERA.getText().toString();
        
    	//Check for valid fields
    	if(mPlayerHRValue == null || mPlayerHRValue.length() <= 0)
    	{
    		AlertDialog.show(UpdatePlayer.this, "Update Player Error", R.drawable.icon2, "Please specify home run (hr)", "Ok", true);
    		return false;
    	}   
    	
    	if(mPlayerBAValue == null || mPlayerBAValue.length() <= 0)
    	{
    		AlertDialog.show(UpdatePlayer.this, "Update Player Error", R.drawable.icon2, "Please specify batting average (ba)", "Ok", true);
    		return false;
    	}  
    	if(mPlayerRBIValue == null || mPlayerRBIValue.length() <= 0)
    	{
    		AlertDialog.show(UpdatePlayer.this, "Update Player Error", R.drawable.icon2, "Please specify runs batted in (rbi)", "Ok", true);
    		return false;
    	}   
    	
    	if(mPlayerWinsValue == null || mPlayerWinsValue.length() <= 0)
    	{
    		AlertDialog.show(UpdatePlayer.this, "Update Player Error", R.drawable.icon2, "Please specify wins", "Ok", true);
    		return false;
    	}  
    	if(mPlayerSavesValue == null || mPlayerSavesValue.length() <= 0)
    	{
    		AlertDialog.show(UpdatePlayer.this, "Update Player Error", R.drawable.icon2, "Please specify saves", "Ok", true);
    		return false;
    	}   
    	
    	if(mPlayerERAValue == null || mPlayerERAValue.length() <= 0)
    	{
    		AlertDialog.show(UpdatePlayer.this, "Update Player Error", R.drawable.icon2, "Please specify earned run average (era)", "Ok", true);
    		return false;
    	}    
    	
    	try
    	{
    		if(Integer.parseInt(mPlayerHRValue) < 0 || Integer.parseInt(mPlayerBAValue) < 0 || Integer.parseInt(mPlayerRBIValue) < 0 || Integer.parseInt(mPlayerWinsValue) < 0 || Integer.parseInt(mPlayerSavesValue) < 0 || Integer.parseInt(mPlayerERAValue) < 0)
    		{
        		AlertDialog.show(UpdatePlayer.this, "Update Player Error", R.drawable.icon2, "Please specify numeric values only", "Ok", true);
        		return false;
    		}
    	}
    	catch (Exception e)
    	{
    		AlertDialog.show(UpdatePlayer.this, "Update Player Error", R.drawable.icon2, "Please specify numeric values only", "Ok", true);
    		return false;	
    	}
    	
    	return true;
	} 	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
    	boolean result = super.onCreateOptionsMenu(menu);
    	menu.add(0, TEAM_MANAGEMENT, R.string.team_management);
    	return result;
    }
    
    @Override
    public boolean onOptionsItemSelected(Item item) {
        // TODO Auto-generated method stub
    	switch(item.getId())
    	{
	    	case  TEAM_MANAGEMENT:
	    		MLB_teamManagement();
	    		return true;
    	
    	}
    	
        return super.onOptionsItemSelected(item);
    }
    
    private void MLB_teamManagement()
    {
        Intent i = new Intent(this, SportsFlashTeamManagement.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }
}
