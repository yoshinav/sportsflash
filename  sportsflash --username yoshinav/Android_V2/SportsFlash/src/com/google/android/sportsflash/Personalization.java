package com.google.android.sportsflash;
/**
 * Configuration:  Configuration file for SportsFlash Application
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 * This class will define the personalization settings for the user.
 * 
 * ToDo:  Create an interface from the phone to allow users to customize this
 * to their personal interests.
 */

public final class Personalization {

	public Personalization()
	{}
	
    // Resource identifiers for the photos we want to display
    public static final int[] PHOTOS_RESOURCES_MAIN = new int[] {
        R.drawable.baseball,
        R.drawable.football,
        R.drawable.hockey,
        R.drawable.basketball,
        R.drawable.baseballtexture2,
        R.drawable.bballtexture2,
        R.drawable.footballtexture2,
        R.drawable.baseball
    };
    
    // Resource identifiers for the photos we want to display
    public static final int[] PHOTOS_RESOURCES_MLB = new int[] {
        R.drawable.baseballtexture2,
        R.drawable.baseball_firstbase,
        R.drawable.mario_baseball,
    };
    
}
