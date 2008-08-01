package com.google.android.sportsflash.mlb.teammanagement;
/**
 * SportsFlashRosterView:  Start Activity to view Roster ... eventually manage all
 * of Team Management for SportsFlash Fantasy League.
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

import com.google.android.sportsflash.mlb.teammanagement.MLBPlayersView;
import com.google.android.sportsflash.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Menu.Item;

public class SportsFlashTeamManagement extends Activity {
	
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
	public static final int VIEWROSTER_ID = Menu.FIRST;
	public static final int VIEWLEAGUE_ID = 2;
	public static final int VIEWTEAM_ID = 3;	
	public static final int CREATEMLBLEAGUE_ID = 4;
	public static final int CREATEMLBTEAM_ID = 5;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.team_management);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
    	boolean result = super.onCreateOptionsMenu(menu);
    	menu.add(0, VIEWROSTER_ID, R.string.menu_view_players);
    	menu.add(1, VIEWLEAGUE_ID, R.string.menu_view_your_league);
    	menu.add(2, VIEWTEAM_ID, R.string.menu_view_your_team);    	
    	menu.add(3, CREATEMLBLEAGUE_ID, R.string.menu_view_league);
    	menu.add(4, CREATEMLBTEAM_ID, R.string.menu_view_team);
    	return result;
    }

    @Override
    public boolean onOptionsItemSelected(Item item) {
        // TODO Auto-generated method stub
    	switch(item.getId())
    	{
	    	case VIEWROSTER_ID:
	    		viewPlayerRoster();
	    		return true;
	    		
	    	case VIEWLEAGUE_ID:
	    		viewLeagueRoster();
	    		return true;
	    		
	    	case VIEWTEAM_ID:
	    		viewTeamRoster();
	    		return true;
	    		
	    	case CREATEMLBLEAGUE_ID:
	    		createMLBLeague();
	    		return true;
	    		
	    	case CREATEMLBTEAM_ID:
	    		createMLBTeam();
	    		return true;
    	
    	}
    	
        return super.onOptionsItemSelected(item);
    }
 
    private void viewPlayerRoster()
    {
        Intent i = new Intent(this, MLBPlayersView.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }

    private void viewLeagueRoster()
    {
        Intent i = new Intent(this, ViewCurrentLeagues.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }
    
    private void viewTeamRoster()
    {
        Intent i = new Intent(this, ViewCurrentTeams.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }
    
    private void createMLBLeague()
    {
        Intent i = new Intent(this, CreateLeague.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }
    
    private void createMLBTeam()
    {
        Intent i = new Intent(this, CreateTeam.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, String data, Bundle extras) {
        super.onActivityResult(requestCode, resultCode, data, extras);
    }
}