package com.google.android.sportsflash.mlb.teammanagement;

import com.google.android.sportsflash.mlb.data.*;
import com.google.android.sportsflash.R;
import com.google.android.sportsflash.SportsFlash;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.app.AlertDialog;

/**
 * CreateLeague:  Create MLB Fantasy League 
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class CreateMessagePost extends Activity
{
	private MLBCreateMessage mWSHelper;
	private EditText mMessageTitle;
    private EditText mMessageDescription;
	private String mMessageTitleValue;
    private String mMessageDescriptionValue;
    private ProgressDialog progressDialog;
    public static final int TEAM_MANAGEMENT = Menu.FIRST;
    private static final int ACTIVITY_CREATE=0;
    
    // use a Handler in order to update UI thread after worker done
    // (cannot update UI thread inline (not done yet), or from separate thread)
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            progressDialog.dismiss();
        }

    };
    
    public CreateMessagePost() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override    protected void onCreate(Bundle icicle) { 
    	super.onCreate(icicle); 
    	setContentView(R.layout.create_message);
    	
        mWSHelper = new MLBCreateMessage();
       
        mMessageTitle = (EditText) findViewById(R.id.messageTitle);
        mMessageDescription = (EditText) findViewById(R.id.messageDescription);
        
        Button confirmButton = (Button) findViewById(R.id.leagueOkButton);
        Button cancelButton = (Button) findViewById(R.id.leagueCancelButton);
                
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) { 
            	
            	//Check for valid input
            	if(!AuthenticatedInput())
            	{
            		return;
            	}
            	
            	//Create League
            	CreateNewMessage();    
                Intent i = new Intent(CreateMessagePost.this, ViewMessageBoards.class);
                startSubActivity(i, Constants.SUB_ACTIVITY_REQUEST_CODE);
                //Intent i = new Intent(CreateLeague.this, SportsFlashTeamManagement.class);
                //startSubActivity(i, Constants.SUB_ACTIVITY_REQUEST_CODE);            	
          	
           }
          
        });
        
        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {        
               Intent i = new Intent(CreateMessagePost.this, SportsFlashTeamManagement.class);
               startSubActivity(i, Constants.SUB_ACTIVITY_REQUEST_CODE);
            	
            }
          
        });        
    }
	
	public void CreateNewMessage()
	{
        progressDialog = ProgressDialog.show(this, " Working...", " Creating Message", true, false);

        new Thread() {
            public void run() {
                mMessageTitleValue = mMessageTitle.getText().toString();
                mMessageDescriptionValue = mMessageDescription.getText().toString();
            	//Log.i(Constants.LOGTAG,"mLeagueNameValue= " + mLeagueNameValue + ", mLeagueDescriptionValue=" + mLeagueDescriptionValue);
            	mWSHelper.CreateLeague(mMessageTitleValue, mMessageDescriptionValue);            	
            	handler.sendEmptyMessage(0);
            }
        }.start();	
	}

	public boolean AuthenticatedInput()
	{
        mMessageTitleValue = mMessageTitle.getText().toString();
        mMessageDescriptionValue = mMessageDescription.getText().toString();
        
    	//Check for valid fields
    	if(mMessageTitleValue == null || mMessageTitleValue.length() <= 0)
    	{
    		AlertDialog.show(CreateMessagePost.this, "Create Message Error", R.drawable.icon2, "Please give your message a title", "Ok", true);
    		return false;
    	}   
    	
    	if(mMessageDescriptionValue == null || mMessageDescriptionValue.length() <= 0)
    	{
    		AlertDialog.show(CreateMessagePost.this, "Create Message Error", R.drawable.icon2, "Please give your message description", "Ok", true);
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
