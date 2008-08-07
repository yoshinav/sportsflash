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
 * MLBCreateTeam:  View MLB Fantasy Teams
 * 
 * @author Navdeep Alam
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class MLBMessageFetcher {

    private static final String CLASSTAG = MLBMessageFetcher.class.getSimpleName();

    private String query;
    private boolean debug;
    
    public MLBMessageFetcher(boolean debug) {

        this.debug = debug;

        // build query
        this.query = Configuration.GetMLBMessages;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " query - " + query);
    }
    
    public MLBMessageFetcher() {
        this(false);
    }
    
    public List<MLBMessage> ViewMessages()
    {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " getMessages");
        long start = System.currentTimeMillis();

        if (!debug) {
            try {
            	//setup the url
                URL feedUrl = new URL(this.query); 

                // TODO - huge delay here on build call, takes 15-20 seconds 
                // (takes < second for same class outside Android)

                // create the factory
                SAXParserFactory factory = SAXParserFactory.newInstance();
                // create a parser
                SAXParser parser = factory.newSAXParser();

                // create the reader (scanner)
                XMLReader xmlreader = parser.getXMLReader();
                // instantiate our handler
                MLBMessageHandler theMLBMessageHandler = new MLBMessageHandler();
                // assign our handler
                xmlreader.setContentHandler(theMLBMessageHandler);
                // get our data through the url class
                InputSource is = new InputSource(feedUrl.openStream());
                // perform the synchronous parse           
                xmlreader.parse(is);
                // get the results - should be a fully populated RSSFeed instance, 
     		   // or null on error
                return theMLBMessageHandler.getFeed();
  
                
            } catch (Exception e) {
                Log.e(Constants.LOGTAG, " " + CLASSTAG + " getReviews ERROR", e);
            }
        } else {
            Log.v(Constants.LOGTAG, " " + CLASSTAG + " devMode true - returning MOCK reviews");
            return null;
        }

        long duration = (System.currentTimeMillis() - start) / 1000;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " call duration - " + duration);
        return null;
    }
   
}
