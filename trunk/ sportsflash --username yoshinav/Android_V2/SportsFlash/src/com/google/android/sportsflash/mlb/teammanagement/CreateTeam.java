package com.google.android.sportsflash.mlb.teammanagement;

import java.util.List;
import java.util.ListIterator;

import com.google.android.sportsflash.SportsFlash;
import com.google.android.sportsflash.R;
import com.google.android.sportsflash.mlb.data.MLBCreateTeam;
import com.google.android.sportsflash.mlb.data.MLBPlayer;
import com.google.android.sportsflash.mlb.data.MLBPlayerFetcher;
import com.google.android.sportsflash.mlb.data.SportsFlashTeamDBHelper;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.CursorAdapter;
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
	private Spinner s1;
	private int s1Value;
	private Spinner s2;
	private int s2Value;
	private Spinner s3;
	private int s3Value;
	private Spinner s4;
	private int s4Value;
	private Spinner s5;
	private int s5Value;
	private Spinner s6;
	private int s6Value;
	private Spinner s7;
	private int s7Value;
	private Spinner s8;
	private int s8Value;
	private Spinner s9;
	private int s9Value;
	private Spinner s10;
	private int s10Value;
    private Long mRowId;
    private static final int ACTIVITY_CREATE=0;
    private ProgressDialog progressDialog;
    private List<MLBPlayer> players1B;
    private List<MLBPlayer> players2B;
    private List<MLBPlayer> players3B;
    private List<MLBPlayer> playersSS;
    private List<MLBPlayer> playersP;
    private List<MLBPlayer> playersC;
    private List<MLBPlayer> playersLF;
    private List<MLBPlayer> playersCF;
    private List<MLBPlayer> playersRF;
    private List<MLBPlayer> playersDH;
    private MLBPlayerAdapter playerAdapter1B;
    private MLBPlayerAdapter playerAdapter2B;
    private MLBPlayerAdapter playerAdapter3B;
    private MLBPlayerAdapter playerAdapterSS;
    private MLBPlayerAdapter playerAdapterP;
    private MLBPlayerAdapter playerAdapterC;
    private MLBPlayerAdapter playerAdapterLF;
    private MLBPlayerAdapter playerAdapterCF;
    private MLBPlayerAdapter playerAdapterRF;
    private MLBPlayerAdapter playerAdapterDH;
    
    // use a Handler in order to update UI thread after worker done
    // (cannot update UI thread inline (not done yet), or from separate thread)
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (players1B != null && players1B.size() != 0) {
            	playerAdapter1B = new MLBPlayerAdapter(CreateTeam.this, players1B);
            	s1 = (Spinner) findViewById(R.id.teamFirstBase);
            	s1.setAdapter(playerAdapter1B);
            }
            if (players2B != null && players2B.size() != 0) {
            	playerAdapter2B = new MLBPlayerAdapter(CreateTeam.this, players2B);
            	s2 = (Spinner) findViewById(R.id.teamSecondBase);
            	s2.setAdapter(playerAdapter2B);
            }       
            if (players3B != null && players3B.size() != 0) {
            	playerAdapter3B = new MLBPlayerAdapter(CreateTeam.this, players3B);
            	s3 = (Spinner) findViewById(R.id.teamThirdBase);
            	s3.setAdapter(playerAdapter3B);
            }            
            if (playersSS != null && playersSS.size() != 0) {
            	playerAdapterSS = new MLBPlayerAdapter(CreateTeam.this, playersSS);
            	s4 = (Spinner) findViewById(R.id.teamShortStop);
            	s4.setAdapter(playerAdapterSS);
            }     
            if (playersP != null && playersP.size() != 0) {
            	playerAdapterP = new MLBPlayerAdapter(CreateTeam.this, playersP);
            	s5 = (Spinner) findViewById(R.id.teamPitcher);
            	s5.setAdapter(playerAdapterP);
            }            
            if (playersC != null && playersC.size() != 0) {
            	playerAdapterC = new MLBPlayerAdapter(CreateTeam.this, playersC);
            	s6 = (Spinner) findViewById(R.id.teamCatcher);
            	s6.setAdapter(playerAdapterC);
            }     
            if (playersLF != null && playersLF.size() != 0) {
            	playerAdapterLF = new MLBPlayerAdapter(CreateTeam.this, playersLF);
            	s7 = (Spinner) findViewById(R.id.teamLeftField);
            	s7.setAdapter(playerAdapterLF);
            }     
            if (playersCF != null && playersCF.size() != 0) {
            	playerAdapterCF = new MLBPlayerAdapter(CreateTeam.this, playersCF);
            	s8 = (Spinner) findViewById(R.id.teamCenterField);
            	s8.setAdapter(playerAdapterCF);
            }            
            if (playersRF != null && playersRF.size() != 0) {
            	playerAdapterRF = new MLBPlayerAdapter(CreateTeam.this, playersRF);
            	s9 = (Spinner) findViewById(R.id.teamRightField);
            	s9.setAdapter(playerAdapterRF);
            }            
            if (playersDH != null && playersDH.size() != 0) {
            	playerAdapterDH = new MLBPlayerAdapter(CreateTeam.this, playersDH);
            	s10 = (Spinner) findViewById(R.id.teamDHitter);
            	s10.setAdapter(playerAdapterDH);
            }          
            
            progressDialog.dismiss();            
        }

    };
    
    public CreateTeam() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle icicle) { 
    	super.onCreate(icicle); 
   
    	setContentView(R.layout.create_team);
   	
    	loadPlayers();
    	
    	//TESTING
    	if(SportsFlash.getCurrentLeagueID() < 0)
    	{
    		SportsFlash.setCurrentLeagueID(99);
    	}
    	
        mDbHelper = new SportsFlashTeamDBHelper(this);
        mWSHelper = new MLBCreateTeam();
       
        mTeamName = (EditText) findViewById(R.id.teamName);
        mTeamDescription = (EditText) findViewById(R.id.teamDescription);
       
        Button confirmButton = (Button) findViewById(R.id.teamOkButton);
        Button cancelButton = (Button) findViewById(R.id.teamCancelButton);
       
        confirmButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {  
            	//Check for valid input
            	if(!AuthenticatedInput())
            	{
            		return;
            	}
            	
            	//Create Team
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
                MLBPlayer p1 = (MLBPlayer)s1.getSelectedItem();
                s1Value = p1.getPlayerID();
                if(s1Value < 0)
                {
                	s1Value = 0;
                }
                p1 = null;
                p1 = (MLBPlayer)s2.getSelectedItem();
                s2Value = p1.getPlayerID();
                if(s2Value < 0)
                {
                	s2Value = 0;
                }
                p1 = null;
                p1 = (MLBPlayer)s3.getSelectedItem();
                s3Value = p1.getPlayerID();
                if(s3Value < 0)
                {
                	s3Value = 0;
                }           
                p1 = null;
                p1 = (MLBPlayer)s4.getSelectedItem();
                s4Value = p1.getPlayerID();
                if(s4Value < 0)
                {
                	s4Value = 0;
                }           
                p1 = null;
                p1 = (MLBPlayer)s5.getSelectedItem();
                s5Value = p1.getPlayerID();
                if(s5Value < 0)
                {
                	s5Value = 0;
                }           
                p1 = null;
                p1 = (MLBPlayer)s6.getSelectedItem();
                s6Value = p1.getPlayerID();
                if(s6Value < 0)
                {
                	s6Value = 0;
                }           
                p1 = null;
                p1 = (MLBPlayer)s7.getSelectedItem();
                s7Value = p1.getPlayerID();
                if(s7Value < 0)
                {
                	s7Value = 0;
                }           
                p1 = null;
                p1 = (MLBPlayer)s8.getSelectedItem();
                s8Value = p1.getPlayerID();
                if(s8Value < 0)
                {
                	s8Value = 0;
                }           
                p1 = null;
                p1 = (MLBPlayer)s9.getSelectedItem();
                s9Value = p1.getPlayerID();
                if(s9Value < 0)
                {
                	s9Value = 0;
                }           
                p1 = null;
                p1 = (MLBPlayer)s10.getSelectedItem();
	            s10Value = p1.getPlayerID();
	            if(s10Value < 0)
	            {
	            	s10Value = 0;
	            }
                
                mDbHelper.createRow(SportsFlash.getCurrentLeagueID(), mTeamNameValue, mTeamDescriptionValue, s1Value, s2Value, s3Value, s4Value, s5Value, s6Value, s7Value, s8Value, s9Value, s10Value); 	
                mWSHelper.CreateTeam(SportsFlash.getCurrentLeagueID(), mTeamNameValue, mTeamDescriptionValue, s1Value, s2Value, s3Value, s4Value, s5Value, s6Value, s7Value, s8Value, s9Value, s10Value);  
            	handler.sendEmptyMessage(0);
            }
        }.start();	
	}
	
    private void loadPlayers() {
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " loadPlayers");
 
        final MLBPlayerFetcher rf = new MLBPlayerFetcher();

        progressDialog = ProgressDialog.show(this, " Working...", " Retrieving players", true, false);

        // get reviews in a separate thread for ProgressDialog/Handler
        // when complete send "empty" message to handler indicating thread is
        // done
        // TODO will handler clean up separate thread (or do need to implement
        // onDestroy?)
        new Thread() {
            public void run() {
                //players = rf.getMockPlayers();
            	players1B = rf.getMLBPlayers("1b");
            	players2B = rf.getMLBPlayers("2b");
            	players3B = rf.getMLBPlayers("3b");
            	playersSS = rf.getMLBPlayers("ss");
            	playersP = rf.getMLBPlayers("p");
            	playersC = rf.getMLBPlayers("c");
            	playersLF = rf.getMLBPlayers("lf");
            	playersCF = rf.getMLBPlayers("cf");
            	playersRF = rf.getMLBPlayers("rf");
            	playersDH = rf.getMLBPlayers("dh");
            	//players.add(rf.getMLBPlayer());
                handler.sendEmptyMessage(0);
            }
        }.start();
    }	
    
	public boolean AuthenticatedInput()
	{
        mTeamNameValue = mTeamName.getText().toString();
        mTeamDescriptionValue = mTeamDescription.getText().toString(); 
        
    	//Check for valid fields
    	if(mTeamNameValue == null || mTeamNameValue.length() <= 0)
    	{
    		AlertDialog.show(CreateTeam.this, "Create Team Error", R.drawable.icon2, "Please give you team a name", "Ok", true);
    		return false;
    	}   
    	
    	if(mTeamDescriptionValue == null || mTeamDescriptionValue.length() <= 0)
    	{
    		AlertDialog.show(CreateTeam.this, "Create Team Error", R.drawable.icon2, "Please give you team a description", "Ok", true);
    		return false;
    	}  
    	return true;
	}    
}
