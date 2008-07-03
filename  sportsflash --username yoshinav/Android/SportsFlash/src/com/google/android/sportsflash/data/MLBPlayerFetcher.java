package com.google.android.sportsflash.data;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.util.Log;

import com.google.android.sportsflash.Constants;

/**
 * Invoke Yahoo! Weather API and parse into WeatherRecord.
 * 
 * (Doing the parsing manually,
 * 
 * @see YWeatherHandler, had various Rome problems.) *
 * 
 * @author charliecollins
 * 
 */
public class MLBPlayerFetcher {

    private static final String CLASSTAG = MLBPlayerFetcher.class.getSimpleName();
    private static final String QBASE = "http://weather.yahooapis.com/forecastrss?p=";
    // private static final SimpleDateFormat DATE_FORMAT = new
    // SimpleDateFormat("yyyy-MM-dd");
    // private static final String TEST_LOC = "30328";

    private String query;
    private String zip;
    private boolean debug;

    public MLBPlayerFetcher(String zip, boolean debug) {

        // validate location is a zip
        if (zip == null || zip.length() != 5 || !isNumeric(zip)) {
            // TODO throw except, invalid
            return;
        }

        this.zip = zip;
        this.debug = debug;

        // build query
        this.query = QBASE + this.zip;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " query - " + query);
    }
    
    public MLBPlayerFetcher(String zip) {
        this(zip, false);
    }

    public MLBPlayerRecord getWeather() {
        long start = System.currentTimeMillis();
        MLBPlayerRecord r = new MLBPlayerRecord();

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
                r = handler.getWeatherRecord();
            } catch (Exception e) {
                Log.e(Constants.LOGTAG, " " + CLASSTAG, e);
            }
        } else {
            Log.v(Constants.LOGTAG, " " + CLASSTAG + " DEBUG true, return MOCK record");
            r = MLBPlayerFetcher.getMockRecord();
        }

        long duration = (System.currentTimeMillis() - start) / 1000;
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " call duration - " + duration);
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " WeatherReport = " + r);
        return r;
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

    public static MLBPlayerRecord getMockRecord() {
        MLBPlayerRecord r = new MLBPlayerRecord();
        r.setFirstName("Nav");
        r.setLastName("CS893");
        r.setPosition("P");
        r.setTeam("tor");
        r.setHR(100);
        r.setRBI(100);
        r.setAVG(0.345);
        return r;
    }

}