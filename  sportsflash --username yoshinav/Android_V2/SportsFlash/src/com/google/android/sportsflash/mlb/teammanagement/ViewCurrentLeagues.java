package com.google.android.sportsflash.mlb.teammanagement;

import java.util.List;

import com.google.android.sportsflash.R;
import com.google.android.sportsflash.mlb.data.MLBLeague;
import com.google.android.sportsflash.mlb.data.SportsFlashLeagueDBHelper;
import com.google.android.sportsflash.mlb.data.SportsFlashLeagueDBHelper.Row;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ViewCurrentLeagues extends ListActivity {
	
	 private TextView empty;
	 private ProgressDialog progressDialog;
	 private SportsFlashLeagueDBHelper mDbHelper;
	 private List<MLBLeague> leagues;
	 
	 private MLBLeagueAdapter leagueAdapter;
	 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " onCreate");

        setDefaultKeyMode(SHORTCUT_DEFAULT_KEYS);
        
        setContentView(R.layout.mlbleague_list);
        
        // Tell the list view which view to display when the list is empty
        getListView().setEmptyView(findViewById(R.id.empty));

        empty = (TextView) findViewById(R.id.empty);

        // get the current review criteria from the Application (global state
        // placed there)
        //SportsFlashPlayersApplication application = (SportsFlashPlayersApplication) this.getApplication();
        //MLBPlayer reviewPlayer = application.getCurrentPlayer();

        loadLeagues();
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
	
	 private void loadLeagues() {
        progressDialog = ProgressDialog.show(this, " Working...", " Retrieving players", true, false);
        
        // get reviews in a separate thread for ProgressDialog/Handler
        // when complete send "empty" message to handler indicating thread is
        // done
        // TODO will handler clean up separate thread (or do need to implement
        // onDestroy?)
        new Thread() {
            public void run() {
            	leagues = mDbHelper.fetchAllRows();
                handler.sendEmptyMessage(0);
            }
        }.start();
	     
        
	 }
	    
    // use a Handler in order to update UI thread after worker done
    // (cannot update UI thread inline (not done yet), or from separate thread)
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

           // Log.v(Constants.LOGTAG, " " + CLASSTAG + " worker thread done, setup ReviewAdapter");
            progressDialog.dismiss();
            if (leagues == null || leagues.size() == 0) {
                empty.setText("No Players");
            } else {
            	leagueAdapter = new MLBLeagueAdapter(ViewCurrentLeagues.this, leagues);
                setListAdapter(leagueAdapter);
            }
        }

    };
}
