package com.google.android.sportsflash;

import com.google.android.sportsflash.MLBPlayerView;
import com.google.android.sportsflash.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Menu.Item;

public class SportsFlash extends Activity {
	
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
	public static final int INSERT_ID = Menu.FIRST;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
    	boolean result = super.onCreateOptionsMenu(menu);
    	menu.add(0, INSERT_ID, R.string.menu_view_players);
    	return result;
    }

    @Override
    public boolean onOptionsItemSelected(Item item) {
        // TODO Auto-generated method stub
    	switch(item.getId())
    	{
    	case INSERT_ID:
    		viewPlayerRoster();
    		return true;
    	}
    	
        return super.onOptionsItemSelected(item);
    }
 
    private void viewPlayerRoster()
    {
        Intent i = new Intent(this, MLBPlayerView.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }

}