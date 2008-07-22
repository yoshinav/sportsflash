package com.google.android.sportsflash.mlb.data;

import java.net.URL;
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
    
    public int CreateTeam()
    {
    	return CreateTeam(99,"Navi Team", "This is a test");
    }
    
    public int CreateTeam(int leagueid, String name, String description)
    {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " getReviews");
        long start = System.currentTimeMillis();


        if (!debug) {
            try {
            	//setup the url
                URL feedUrl = new URL(this.query + "leagueId=" + leagueid + "&name=" + name + "&description=" + description);
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
