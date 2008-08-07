package com.google.android.sportsflash.mlb.teammanagement;
/**
 * SportsFlashRosterView:  Start Activity to view Roster ... eventually manage all
 * of Team Management for SportsFlash Fantasy League.
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

import com.google.android.sportsflash.animation.Rotate3dAnimation;
import com.google.android.sportsflash.mlb.teammanagement.MLBPlayersView;
import com.google.android.sportsflash.Personalization;
import com.google.android.sportsflash.R;
import com.google.android.sportsflash.SportsFlash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu.Item;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

public class SportsFlashTeamManagement extends Activity   {

    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    
	public static final int VIEWROSTER_ID = Menu.FIRST;
	public static final int VIEWLEAGUE_ID = 2;
	public static final int VIEWTEAM_ID = 3;	
	public static final int CREATEMLBLEAGUE_ID = 4;
	public static final int CREATEMLBTEAM_ID = 5;
	public static final int HOME_ID = 6;
	public static final int MESSAGE_ID = 7;
	
    private ViewGroup mContainer;
    private ImageView mImageView;
    private ViewFlipper mFlipper;
    
    private int imagePositionCounter = 0;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.animations_mlb_main_screen);
        
        mImageView = (ImageView) findViewById(R.id.picture);
        mContainer = (ViewGroup) findViewById(R.id.container); 

        //Prepare Message Flipper
        mFlipper = ((ViewFlipper) this.findViewById(R.id.flipper));
        mFlipper.setInAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in));
        mFlipper.setOutAnimation(AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out));     
        mFlipper.startFlipping();    
        
       
        // Prepare the ImageView
        mImageView.setClickable(true);
        mImageView.setFocusable(true);
        
        mImageView.setOnClickListener(new View.OnClickListener() { 

        	public void onClick(View v) {
                imagePositionCounter++;  
            	if(imagePositionCounter >= Personalization.PHOTOS_RESOURCES_MLB.length)
            	{
            		imagePositionCounter = 0;
            	}
            	
                applyRotation(imagePositionCounter, 0, 90);            	
                mImageView.setImageResource(Personalization.PHOTOS_RESOURCES_MLB[imagePositionCounter]);         
        		}
        	}
        );
          
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
    	boolean result = super.onCreateOptionsMenu(menu);
    	menu.add(0, VIEWROSTER_ID, R.string.menu_view_players);
    	menu.add(1, VIEWLEAGUE_ID, R.string.menu_view_your_league);
    	//menu.add(2, VIEWTEAM_ID, R.string.menu_view_your_team);    	
    	menu.add(2, CREATEMLBLEAGUE_ID, R.string.menu_view_league);
    	//menu.add(4, CREATEMLBTEAM_ID, R.string.menu_view_team);
    	menu.add(3, MESSAGE_ID, R.string.message_board);
    	menu.add(4, HOME_ID, R.string.home);
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
	    		
	    	case HOME_ID:
	    		Home();
	    		return true;	    	
	    		
	    	case MESSAGE_ID:
	    		viewMessages();
	    		return true;	 	    		
    	
    	}
    	
        return super.onOptionsItemSelected(item);
    }
 
    private void viewPlayerRoster()
    {
        Intent i = new Intent(this, MLBPlayersView.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }

    private void viewMessages()
    {
        Intent i = new Intent(this, ViewMessageBoards.class);
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
    
    private void Home()
    {
        Intent i = new Intent(this, SportsFlash.class);
        startSubActivity(i, ACTIVITY_CREATE);	
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, String data, Bundle extras) {
        super.onActivityResult(requestCode, resultCode, data, extras);
    }
    
    /**
     * Setup a new 3D rotation on the container view.
     *
     * @param position the item that was clicked to show a picture, or -1 to show the list
     * @param start the start angle at which the rotation must begin
     * @param end the end angle of the rotation
     */
    private void applyRotation(int position, float start, float end) {
        // Find the center of the container
        final float centerX = mContainer.getWidth() / 2.0f;
        final float centerY = mContainer.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView());

        mImageView.startAnimation(rotation);
    }


    
    /**
     * This class listens for the end of the first half of the animation.
     * It then posts a new action that effectively swaps the views when the container
     * is rotated 90 degrees and thus invisible.
     */
    private final class DisplayNextView implements Animation.AnimationListener {

        private DisplayNextView() {
        }

        public void onAnimationStart() {
        }

        public void onAnimationEnd() {
        	mImageView.post(new SwapImages());
        }

        public void onAnimationRepeat() {
        }
    }

    /**
     * This class is responsible for swapping the views and start the second
     * half of the animation.
     */
    private final class SwapImages implements Runnable {

        public SwapImages() {
        }

        public void run() {
            final float centerX = mContainer.getWidth() / 2.0f;
            final float centerY = mContainer.getHeight() / 2.0f;
            Rotate3dAnimation rotation;

            mImageView.setVisibility(View.VISIBLE);
            mImageView.requestFocus();            	
            rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false);
           
            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());

            mImageView.startAnimation(rotation);
        }
    }
        
}