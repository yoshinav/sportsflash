package com.google.android.sportsflash.mlb.data;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jdom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.google.android.sportsflash.mlb.teammanagement.Constants;
import com.google.android.sportsflash.Configuration;


/**
 * MLBPlayerFetcher:  Get Player roster for MLB
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */
public class MLBPlayerFetcher {

    private static final String CLASSTAG = MLBPlayerFetcher.class.getSimpleName();

    private String query;
    private String queryPlayers;
    private String queryPlayerID;
    private boolean debug;

    public MLBPlayerFetcher(boolean debug) {

        this.debug = debug;

        // build query
        this.query = Configuration.urlGetMLBPlayersByPosition;
        this.queryPlayers = Configuration.urlGetMLBPlayers;
        this.queryPlayerID = Configuration.urlGetMLBPlayerByID;
        
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " query - " + query);
    }
    
    public MLBPlayerFetcher() {
        this(false);
    }

    public MLBPlayer getMLBPlayerByID(int id) {
        long start = System.currentTimeMillis();
        MLBPlayer r = new MLBPlayer();

        if (!debug) {
            try {
                URL url = new URL(this.queryPlayerID + id);
                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();
                MLBPlayerHandler handler = new MLBPlayerHandler();
                xr.setContentHandler(handler);
                xr.parse(new InputSource(url.openStream()));
                // after parsed, get record
                r = handler.getMLBPlayerRecord();
            } catch (Exception e) {
                Log.e(Constants.LOGTAG, " " + CLASSTAG, e);
            }
        } else {
            Log.v(Constants.LOGTAG, " " + CLASSTAG + " DEBUG true, return MOCK record");
            r = getMockPlayer();
        }

        long duration = (System.currentTimeMillis() - start) / 1000;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " call duration - " + duration);
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " WeatherReport = " + r);
        return r;
    }
    
    public MLBPlayer getMLBPlayer(String position) {
        long start = System.currentTimeMillis();
        MLBPlayer r = new MLBPlayer();

        if (!debug) {
            try {
                URL url = new URL(this.query);
                SAXParserFactory spf = SAXParserFactory.newInstance();
                SAXParser sp = spf.newSAXParser();
                XMLReader xr = sp.getXMLReader();
                MLBPlayerHandler handler = new MLBPlayerHandler();
                xr.setContentHandler(handler);
                xr.parse(new InputSource(url.openStream()));
                // after parsed, get record
                r = handler.getMLBPlayerRecord();
            } catch (Exception e) {
                Log.e(Constants.LOGTAG, " " + CLASSTAG, e);
            }
        } else {
            Log.v(Constants.LOGTAG, " " + CLASSTAG + " DEBUG true, return MOCK record");
            r = getMockPlayer();
        }

        long duration = (System.currentTimeMillis() - start) / 1000;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " call duration - " + duration);
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " WeatherReport = " + r);
        return r;
    }

    
    /**
     * Call Google Base via Rome and establish Reviews.
     * 
     * @return
     */
    public List<MLBPlayer> getMLBPlayers(String position) {

        Log.v(Constants.LOGTAG, " " + CLASSTAG + " getReviews");
        long start = System.currentTimeMillis();
        List<MLBPlayer> results = null;

        if (!debug) {
            try {
            	//setup the url
                URL feedUrl = new URL(this.query + position);
                // TODO - huge delay here on build call, takes 15-20 seconds 
                // (takes < second for same class outside Android)
                
                // create the factory
                SAXParserFactory factory = SAXParserFactory.newInstance();
                // create a parser
                SAXParser parser = factory.newSAXParser();

                // create the reader (scanner)
                XMLReader xmlreader = parser.getXMLReader();
                // instantiate our handler
                MLBPlayerHandler theMLBPlayerHandler = new MLBPlayerHandler();
                // assign our handler
                xmlreader.setContentHandler(theMLBPlayerHandler);
                // get our data through the url class
                InputSource is = new InputSource(feedUrl.openStream());
                // perform the synchronous parse           
                xmlreader.parse(is);
                // get the results - should be a fully populated RSSFeed instance, 
     		   // or null on error
                return theMLBPlayerHandler.getFeed();
  
                
            } catch (Exception e) {
                Log.e(Constants.LOGTAG, " " + CLASSTAG + " getReviews ERROR", e);
            }
        } else {
            Log.v(Constants.LOGTAG, " " + CLASSTAG + " devMode true - returning MOCK reviews");
            results = this.getMockPlayers();
        }

        long duration = (System.currentTimeMillis() - start) / 1000;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " call duration - " + duration);
        return results;
    }
   
    public List<MLBPlayer> getMLBPlayers() {

        Log.v(Constants.LOGTAG, " " + CLASSTAG + " getReviews");
        long start = System.currentTimeMillis();
        List<MLBPlayer> results = null;

        if (!debug) {
            try {
            	//setup the url
                //URL feedUrl = new URL(this.queryPlayers);
            	URL feedUrl = new URL(this.query + "1b");
                // TODO - huge delay here on build call, takes 15-20 seconds 
                // (takes < second for same class outside Android)
                
                // create the factory
                SAXParserFactory factory = SAXParserFactory.newInstance();
                // create a parser
                SAXParser parser = factory.newSAXParser();

                // create the reader (scanner)
                XMLReader xmlreader = parser.getXMLReader();
                // instantiate our handler
                MLBPlayerHandler theMLBPlayerHandler = new MLBPlayerHandler();
                // assign our handler
                xmlreader.setContentHandler(theMLBPlayerHandler);
                // get our data through the url class
                InputSource is = new InputSource(feedUrl.openStream());
                // perform the synchronous parse           
                xmlreader.parse(is);
                // get the results - should be a fully populated RSSFeed instance, 
     		   // or null on error
                return theMLBPlayerHandler.getFeed();
  
                
            } catch (Exception e) {
                Log.e(Constants.LOGTAG, " " + CLASSTAG + " getReviews ERROR", e);
            }
        } else {
            Log.v(Constants.LOGTAG, " " + CLASSTAG + " devMode true - returning MOCK reviews");
            results = this.getMockPlayers();
        }

        long duration = (System.currentTimeMillis() - start) / 1000;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " call duration - " + duration);
        return results;
    }    
    
    private boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public List<MLBPlayer> getMockPlayers() {
    	
    	List<MLBPlayer> results = new ArrayList<MLBPlayer>(2);
    	
        MLBPlayer r = new MLBPlayer();
        r.setFirstName("Nav");
        r.setLastName("Alam");
        r.setPosition("P");
        r.setTeam("tor");
        r.setHR(100);
        r.setRBI(100);
        r.setAVG(345);
        results.add(r);
        
        MLBPlayer r1 = new MLBPlayer();
        r1.setFirstName("Larry");
        r1.setLastName("Walker");
        r1.setPosition("LF");
        r1.setTeam("mtl");
        r1.setHR(12);
        r1.setRBI(12);
        r1.setAVG(265);
        results.add(r1);
        
        return results;
    }

    public MLBPlayer getMockPlayer() {
    	
        MLBPlayer r = new MLBPlayer();
        r.setFirstName("Nav");
        r.setLastName("Alam");
        r.setPosition("P");
        r.setTeam("tor");
        r.setHR(100);
        r.setRBI(100);
        r.setAVG(345);
        
        return r;
    }
}