package com.google.android.sportsflash.mlb.data;

import android.widget.*;

/**
 * MyFilter:  Used to support Filtering of MLBPlayers
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class MyFilter extends Filter { 


    @Override 
    protected FilterResults performFiltering(CharSequence contrain) { 
            // TODO Auto-generated method stub 
            FilterResults fr = new FilterResults(); 
            fr.count = 8; 
            String[] a = new String[]{"abc","def", "hij", "klm", "nop", "qrs", "tuv", "wxyz"}; 
            fr.values = a; 

            return fr; 
    } 


    @Override 
    protected void publishResults(CharSequence arg0, FilterResults 
results) { 
            // TODO Auto-generated method stub 
            //Log.i("Filter",results.count+""); 
    if (results.count > 0) { 
        //notifyDataSetChanged(); 
    } else { 
        //notifyDataSetInvalidated(); 
    } 
   } 


} 

