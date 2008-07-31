package com.google.android.sportsflash;
/**
 * SportsFlash:  Start Activity to view Roster
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

import com.google.android.sportsflash.mlb.teammanagement.*;
import com.google.android.sportsflash.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Menu.Item;
import android.app.AlertDialog;
import android.os.*;
import android.graphics.*;
import android.widget.*;
import android.util.Log;

public class SportsFlash extends Activity {
	
	public static int currentLeagueID = 0;
	public static int currentTeamID = 0;
	
    public SportsFlash() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
	public static final int MLB_TM = Menu.FIRST;
	public static final int NFL_TM = 2;
	public static final int NBA_TM = 3;
	public static final int NHL_TM = 4;
	
	private Handler uiThreadHandler;
	private ImageView image;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        
        //Add Sports Image Slide Show Using Our DrawAbles - Utilizing Threading and Power Management
        this.image = (ImageView) findViewById(R.id.image);
        this.uiThreadHandler = new Handler();
        
        WorkerThread worker = new WorkerThread();
        worker.start();
        while(!worker.ready){
        	try{
        		Thread.sleep(10);
        	}catch(InterruptedException e){
        		;
        	}
        }
        final int[] images = new int[] {
        R.drawable.basketball,
        R.drawable.football,
        R.drawable.hockey,
        R.drawable.baseball,
        R.drawable.baseballtexture2,
        R.drawable.bballtexture2,
        R.drawable.footballtexture2,
        R.drawable.baseball
        };
        
        //Define PowerManagement
        PowerManager power =
        	(PowerManager) getSystemService(POWER_SERVICE);
        	final PowerManager.WakeLock screenLock = power.newWakeLock(
        	PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
        	"ThreadActivity" );
        	screenLock.acquire();
        
	        for(int i = 0; i < images.length; i++){
	        	final int toFetch = images[i];
	        	Runnable r = new Runnable(){
	        		public void run(){
	        			try {
	        				final Bitmap bm = BitmapFactory.decodeResource(getResources(), toFetch);
	        				uiThreadHandler.post(new Runnable(){
	        					public void run(){
	        						image.setImageBitmap(bm);
	        					}
	        				});
	        				screenLock.release();	//Release lock
	        			} catch (Exception e) {
	        				Log.e("ThreadActivity", null, e);
	        			}
	        		}
	        	};
	
	        	worker.timerHandler.postDelayed( r, i * 5000 );        	
	        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
    	boolean result = super.onCreateOptionsMenu(menu);
    	menu.add(0, MLB_TM, R.string.menu_MLB_TM);
    	menu.add(0, NFL_TM, R.string.menu_NFL_TM);
    	menu.add(0, NBA_TM, R.string.menu_NBA_TM);
    	menu.add(0, NHL_TM, R.string.menu_NHL_TM);
    	return result;
    }

    @Override
    public boolean onOptionsItemSelected(Item item) {
        // TODO Auto-generated method stub
    	switch(item.getId())
    	{
    	case MLB_TM:
    		MLB_teamManagement();
    		return true;
    	case NFL_TM:
    		AlertDialog.show(this, "NFL Fantasy League", R.drawable.icon2, "NFL Fantasy League coming soon!", "Ok", true);
    		return true;
    	case NBA_TM:
    		AlertDialog.show(this, "NBA Fantasy League", R.drawable.icon2, "NBA Fantasy League coming soon!", "Ok", true);   		
    		return true;
    	case NHL_TM:
    		AlertDialog.show(this, "NHL Fantasy League", R.drawable.icon2, "NHL Fantasy League coming soon!", "Ok", true);   		    		
    		return true;
	    		
    	}
    	
        return super.onOptionsItemSelected(item);
    }
 
    private void MLB_teamManagement()
    {
        Intent i = new Intent(this, SportsFlashTeamManagement.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }

    //Worker Thread for SlideShow
    private static class WorkerThread extends Thread {
    	private volatile boolean ready = false;
    	private Handler timerHandler;
    	public void run(){
    	Looper.prepare();
    	this.timerHandler = new Handler();
    	this.ready = true;
    	Looper.loop();
    	}
    }    
}

