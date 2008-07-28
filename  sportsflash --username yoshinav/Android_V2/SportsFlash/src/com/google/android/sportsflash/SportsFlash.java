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
import java.net.*;
import java.io.*;
import android.graphics.*;

public class SportsFlash extends Activity {
	
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
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        
        /*
        WorkerThread worker = new WorkerThread();
        worker.start();
        while(!worker.ready){
        try{
        Thread.sleep(10);
        }catch(InterruptedException e){
        ;
        }
        }
        final String[] images = new String[] {
        "http://farm3.static.flickr.com/2026/2311989084_7d0cc111e7.jpg",
        "http://farm3.static.flickr.com/2222/2311179031_5d86804353.jpg",
        "http://farm3.static.flickr.com/2016/2317637608_fb13e58bbd.jpg",
        "http://farm4.static.flickr.com/3070/2316828415_d1c3542048.jpg"
        };
        for(int i = 0; i < images.length; i++){
        final String toFetch = images[i];
        Runnable r = new Runnable(){
        public void run(){
        try {
        URL url =
        new URL(toFetch);
        URLConnection conn = url.openConnection();
        conn.connect();
        BufferedInputStream bis =
        new BufferedInputStream(conn.getInputStream());
        final Bitmap bm = BitmapFactory.decodeStream(bis);
        bis.close();
        uiThreadHandler.post(new Runnable(){
        public void run(){
        image.setImageBitmap(bm);
        }
        });
        } catch (IOException e) {
        //Log.e("ThreadActivity", null, e);
        }
        }
        };
        worker.timerHandler.postDelayed(r , i * 10000);
        }
        
        */
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

