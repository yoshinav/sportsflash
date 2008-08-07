package com.google.android.sportsflash.mlb.teammanagement;

import java.util.List;

import com.google.android.sportsflash.R;
import com.google.android.sportsflash.SportsFlash;
import com.google.android.sportsflash.mlb.data.MLBTeam;
import com.google.android.sportsflash.mlb.data.SportsFlashTeamDBHelper;
import com.google.android.sportsflash.mlb.data.MLBTeamFetcher;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.Menu.Item;
import android.widget.TextView;

public class ViewCurrentTeams extends ListActivity {
	 
	 private TextView empty;
	 private ProgressDialog progressDialog;
	 private SportsFlashTeamDBHelper mDbHelper;
	 private MLBTeamFetcher mWSHelper;
	 private List<MLBTeam> teams;
	 
	 private MLBTeamAdapter teamAdapter;
	 public static final int TEAM_MANAGEMENT = Menu.FIRST;
	 private static final int ACTIVITY_CREATE=0;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " onCreate");
        
        mDbHelper = new SportsFlashTeamDBHelper(this);
        mWSHelper = new MLBTeamFetcher();
        
        setDefaultKeyMode(SHORTCUT_DEFAULT_KEYS);
        
        setContentView(R.layout.mlbleague_list);
        
        // Tell the list view which view to display when the list is empty
        getListView().setEmptyView(findViewById(R.id.empty));

        empty = (TextView) findViewById(R.id.empty);

        // get the current review criteria from the Application (global state
        // placed there)
        //SportsFlashPlayersApplication application = (SportsFlashPlayersApplication) this.getApplication();
        //MLBPlayer reviewPlayer = application.getCurrentPlayer();

        loadTeams();
        //fillData();
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
	
	 private void loadTeams() {
        progressDialog = ProgressDialog.show(this, " Working...", " Retrieving teams", true, false);
        
        // get reviews in a separate thread for ProgressDialog/Handler
        // when complete send "empty" message to handler indicating thread is
        // done
        // TODO will handler clean up separate thread (or do need to implement
        // onDestroy?)
        new Thread() {
            public void run() {
            	//teams = mDbHelper.fetchAllRows();
            	teams = mWSHelper.ViewTeams(SportsFlash.getCurrentLeagueID());
                handler.sendEmptyMessage(0);
            }
        }.start();
	     
        
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
	    
    // use a Handler in order to update UI thread after worker done
    // (cannot update UI thread inline (not done yet), or from separate thread)
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

           // Log.v(Constants.LOGTAG, " " + CLASSTAG + " worker thread done, setup ReviewAdapter");
            progressDialog.dismiss();
            if (teams == null || teams.size() == 0) {
                empty.setText("No Teams");
            } else {
            	teamAdapter = new MLBTeamAdapter(ViewCurrentTeams.this, teams);
                setListAdapter(teamAdapter);
            }
        }

    };
    
}
