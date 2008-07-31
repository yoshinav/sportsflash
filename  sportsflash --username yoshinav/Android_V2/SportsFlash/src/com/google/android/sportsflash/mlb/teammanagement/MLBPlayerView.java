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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.google.android.sportsflash.R;
import android.app.Activity;

public class MLBPlayerView extends Activity {

	 private SportsFlashTeamDBHelper mDbHelper;
	 
	    private static final String CLASSTAG = MLBPlayerView.class.getSimpleName();
	    private static final int NUM_RESULTS_PER_PAGE = 5;
	    private static final int MENU_GET_NEXT_PAGE = Menu.FIRST;

	    private ProgressDialog progressDialog;

	    private List<MLBPlayer> players;
	    private MLBPlayerAdapter playerAdapter;

	    private TextView empty;

	    public MLBPlayerView() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " onCreate");

        setDefaultKeyMode(SHORTCUT_DEFAULT_KEYS);
        
        setContentView(R.layout.update_mlbplayer);
        
        //loadPlayer(2);
        //fillData();
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
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " loadPlayers");
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
   
}
