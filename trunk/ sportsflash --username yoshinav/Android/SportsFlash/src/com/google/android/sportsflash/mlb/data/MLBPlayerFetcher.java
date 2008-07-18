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

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * MLBPlayerFetcher:  Get Player roster for MLB
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */
public class MLBPlayerFetcher {

    private static final String CLASSTAG = MLBPlayerFetcher.class.getSimpleName();
    private static final String QBASE = "http://192.168.1.108/sportsflashws/serviceSF.svc/rest/GetMLBPlayersbyPosition?position=2b";
    // private static final SimpleDateFormat DATE_FORMAT = new
    // SimpleDateFormat("yyyy-MM-dd");
    // private static final String TEST_LOC = "30328";

    private String query;
    private String zip;
    private boolean debug;

    public MLBPlayerFetcher(boolean debug) {

        this.debug = debug;

        // build query
        this.query = QBASE;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " query - " + query);
    }
    
    public MLBPlayerFetcher() {
        this(false);
    }

    public MLBPlayer getMLBPlayer() {
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
    public List<MLBPlayer> getMLBPlayers() {

        Log.v(Constants.LOGTAG, " " + CLASSTAG + " getReviews");
        long start = System.currentTimeMillis();
        List<MLBPlayer> results = null;

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

                /*
                SyndFeed feed = new SyndFeedInput().build(new XmlReader(feedUrl));
                List<SyndEntry> entries = feed.getEntries();
                for (SyndEntry e : entries) {
                    Log.v(Constants.LOGTAG, " " + CLASSTAG + " processing entry " + e.getTitle());

                    if (results == null)
                        results = new ArrayList<MLBPlayer>(3);

                    MLBPlayer player = new MLBPlayer();
                    player.setFirstName(e.getTitle());
                    player.setLastName(e.getAuthor());
                    player.setPosition("C");
                    player.setTeam(e.getLink());

                    results.add(player);
                }
                
                */
                
                
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
        r.setAVG(0.345);
        results.add(r);
        
        MLBPlayer r1 = new MLBPlayer();
        r1.setFirstName("Sabrina");
        r1.setLastName("Walker");
        r1.setPosition("C");
        r1.setTeam("bos");
        r1.setHR(12);
        r1.setRBI(12);
        r1.setAVG(0.265);
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
        r.setAVG(0.345);
        
        return r;
    }
}