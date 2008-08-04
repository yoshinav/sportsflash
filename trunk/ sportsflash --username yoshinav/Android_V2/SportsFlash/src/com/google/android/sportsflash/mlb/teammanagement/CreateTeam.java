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
            progressDialog.dismiss();
            if (players1B != null && players1B.size() != 0) {
            	playerAdapter1B = new MLBPlayerAdapter(CreateTeam.this, players1B);
            	Spinner s1 = (Spinner) findViewById(R.id.teamFirstBase);
            	s1.setAdapter(playerAdapter1B);
            }
            if (players2B != null && players2B.size() != 0) {
            	playerAdapter2B = new MLBPlayerAdapter(CreateTeam.this, players2B);
            	Spinner s2 = (Spinner) findViewById(R.id.teamSecondBase);
            	s2.setAdapter(playerAdapter2B);
            }       
            if (players3B != null && players3B.size() != 0) {
            	playerAdapter3B = new MLBPlayerAdapter(CreateTeam.this, players3B);
            	Spinner s3 = (Spinner) findViewById(R.id.teamThirdBase);
            	s3.setAdapter(playerAdapter3B);
            }            
            if (playersSS != null && playersSS.size() != 0) {
            	playerAdapterSS = new MLBPlayerAdapter(CreateTeam.this, playersSS);
            	Spinner s4 = (Spinner) findViewById(R.id.teamShortStop);
            	s4.setAdapter(playerAdapterSS);
            }     
            if (playersP != null && playersP.size() != 0) {
            	playerAdapterP = new MLBPlayerAdapter(CreateTeam.this, playersP);
            	Spinner s5 = (Spinner) findViewById(R.id.teamPitcher);
            	s5.setAdapter(playerAdapterP);
            }            
            if (playersC != null && playersC.size() != 0) {
            	playerAdapterC = new MLBPlayerAdapter(CreateTeam.this, playersC);
            	Spinner s6 = (Spinner) findViewById(R.id.teamCatcher);
            	s6.setAdapter(playerAdapterC);
            }     
            if (playersLF != null && playersLF.size() != 0) {
            	playerAdapterLF = new MLBPlayerAdapter(CreateTeam.this, playersLF);
            	Spinner s7 = (Spinner) findViewById(R.id.teamLeftField);
            	s7.setAdapter(playerAdapterLF);
            }     
            if (playersCF != null && playersCF.size() != 0) {
            	playerAdapterCF = new MLBPlayerAdapter(CreateTeam.this, playersCF);
            	Spinner s8 = (Spinner) findViewById(R.id.teamCenterField);
            	s8.setAdapter(playerAdapterCF);
            }            
            if (playersRF != null && playersRF.size() != 0) {
            	playerAdapterRF = new MLBPlayerAdapter(CreateTeam.this, playersRF);
            	Spinner s9 = (Spinner) findViewById(R.id.teamRightField);
            	s9.setAdapter(playerAdapterRF);
            }            
            if (playersDH != null && playersDH.size() != 0) {
            	playerAdapterDH = new MLBPlayerAdapter(CreateTeam.this, playersDH);
            	Spinner s10 = (Spinner) findViewById(R.id.teamDHitter);
            	s10.setAdapter(playerAdapterDH);
            }            
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
                mDbHelper.createRow(SportsFlash.getCurrentLeagueID(), mTeamNameValue, mTeamDescriptionValue); 	
             	mWSHelper.CreateTeam(SportsFlash.getCurrentLeagueID(), mTeamNameValue, mTeamDescriptionValue);  
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
    
}
