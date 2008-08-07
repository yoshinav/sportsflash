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
import android.view.Menu.Item;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.google.android.sportsflash.R;
import com.google.android.sportsflash.SportsFlash;

public class MLBPlayersView extends ListActivity {

	 private static final int ACTIVITY_CREATE=0;
	 public static final int TEAM_MANAGEMENT = Menu.FIRST;
	 
	    private static final String CLASSTAG = MLBPlayersView.class.getSimpleName();

	    private ProgressDialog progressDialog;

	    private List<MLBPlayer> players;
	    private MLBPlayerAdapter playerAdapter;
	    private MLBPlayerAdapter playerAdapterAC;

	    private TextView empty;

	    // use a Handler in order to update UI thread after worker done
	    // (cannot update UI thread inline (not done yet), or from separate thread)
	    private Handler handler = new Handler() {

	        @Override
	        public void handleMessage(Message msg) {

	            Log.v(Constants.LOGTAG, " " + CLASSTAG + " worker thread done, setup ReviewAdapter");
	            progressDialog.dismiss();
	            if (players == null || players.size() == 0) {
	                empty.setText("No Players");
	            } else {
	            	playerAdapter = new MLBPlayerAdapter(MLBPlayersView.this, players);
	                setListAdapter(playerAdapter);
	                
		            playerAdapterAC = new MLBPlayerAdapter(MLBPlayersView.this, players);
		            AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.edit);
		            textView.setAdapter(playerAdapterAC);	                
	            }
	            

	        }

	    };
	    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " onCreate");

        setDefaultKeyMode(SHORTCUT_DEFAULT_KEYS);
        
        setContentView(R.layout.mlbplayer_list);
        
        // Tell the list view which view to display when the list is empty
        getListView().setEmptyView(findViewById(R.id.empty));

        empty = (TextView) findViewById(R.id.empty);

        loadPlayers();

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
	
    private void loadPlayers() {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " loadPlayers");
 
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
            	
            	players = rf.getMLBPlayers();
            	//players = rf.getMLBPlayers("dh");  //Testing against Designated Hitters
            	
            	//players.add(rf.getMLBPlayer());
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
    
	protected void onListItemClick(ListView listView, View view, int position, long id)
	{
		MLBPlayer playerSelected  = (MLBPlayer)listView.obtainItem(position);
		SportsFlash.setCurrentPlayerID(playerSelected.getPlayerID());
        Intent i = new Intent(this, UpdatePlayer.class);
        startSubActivity(i, ACTIVITY_CREATE);	

	}
    
}
