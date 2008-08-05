package com.google.android.sportsflash.mlb.data;

import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.google.android.sportsflash.Configuration;
import com.google.android.sportsflash.mlb.teammanagement.Constants;
import com.google.android.sportsflash.mlb.data.MLBLeagueHandler;

/**
 * MLBCreateTeam:  Create MLB Fantasy Team
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class MLBCreateTeam {

    private static final String CLASSTAG = MLBCreateTeam.class.getSimpleName();

    private String query;
    private boolean debug;
    
    public MLBCreateTeam(boolean debug) {

        this.debug = debug;

        // build query
        this.query = Configuration.urlCreateMLBTeam;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " query - " + query);
    }
    
    public MLBCreateTeam() {
        this(false);
    }
    
    public int CreateTeam(int leagueid, String name, String description,
    		int firstbase, int secondbase, int thirdbase, int shortstop, int pitcher, int catcher,
    		int rightfield, int centerfield, int leftfield, int dhitter)
    {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " getReviews");
        long start = System.currentTimeMillis();

        //Check for Apostrophies and double them up to allow for safe sql against SProcs
        name = name.replaceAll("'", "''");
        description = description.replaceAll("'", "''");

        if (!debug) {
            try {
            	//setup the url
                URL feedUrl = new URL(this.query + "leagueId=" + leagueid + "&name=" + URLEncoder.encode(name,"UTF-8") + "&description=" + URLEncoder.encode(description,"UTF-8") + "&firstbase=" + firstbase + "&secondbase=" + secondbase + "&thirdbase=" + thirdbase
                		+ "&shortstop=" + shortstop + "&catcher=" + catcher + "&pitcher=" + pitcher + "&rightfield=" + rightfield + "&centerfield=" + centerfield + "&leftfield=" + leftfield + "&dhitter=" + dhitter); 

                // TODO - huge delay here on build call, takes 15-20 seconds 
                // (takes < second for same class outside Android)

                // create the factory
                SAXParserFactory factory = SAXParserFactory.newInstance();
                // create a parser
                SAXParser parser = factory.newSAXParser();

                // create the reader (scanner)
                XMLReader xmlreader = parser.getXMLReader();
                // instantiate our handler
                MLBLeagueHandler theMLBLeagueHandler = new MLBLeagueHandler();
                // assign our handler
                xmlreader.setContentHandler(theMLBLeagueHandler);
                // get our data through the url class
                InputSource is = new InputSource(feedUrl.openStream());
                // perform the synchronous parse           
                xmlreader.parse(is);
                // get the results - should be a fully populated RSSFeed instance, 
     		   // or null on error
                return theMLBLeagueHandler.getMLBID();
  
                
            } catch (Exception e) {
                Log.e(Constants.LOGTAG, " " + CLASSTAG + " getReviews ERROR", e);
            }
        } else {
            Log.v(Constants.LOGTAG, " " + CLASSTAG + " devMode true - returning MOCK reviews");
            return 0;
        }

        long duration = (System.currentTimeMillis() - start) / 1000;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " call duration - " + duration);
        return 0;
    }
}
