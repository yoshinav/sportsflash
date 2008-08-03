package com.google.android.sportsflash.mlb.data;

/**
 * MLBPlayerHandler:  SAX Handler
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class MLBPlayerHandler extends DefaultHandler {

    //private static final String CLASSTAG = YWeatherHandler.class.getSimpleName();

	List<MLBPlayer> _feed;
	MLBPlayer _item;

    final int MLBPLAYER_FIRSTNAME = 1;
    final int MLBPLAYER_LASTNAME = 2;
    final int MLBPLAYER_POSITION = 3;
    final int MLBPLAYER_TEAM = 4;
    
    int depth = 0;
    int currentstate = 0;

    private MLBPlayer mlbPlayerRecord;

    public MLBPlayerHandler() {
        mlbPlayerRecord = new MLBPlayer();
    }
    /*
     * getFeed - this returns our feed when all of the parsing is complete
     */
    public List<MLBPlayer> getFeed()
    {
        return _feed;
    }

    @Override
    public void startDocument() throws SAXException {
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " startDocument");
        // initialize our MLBPlayer ArrayList object - this will hold our parsed contents
        _feed = new ArrayList<MLBPlayer>(20);

    }

    @Override
    public void endDocument() throws SAXException {
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " endDocument");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        
    	depth++;

        if (localName.equals("Table"))
        {
            // initialize the MLBPlayer object - 
            _item = new MLBPlayer();
            
            currentstate = 0;
            return;
        }
        
        if (localName.equals("firstname"))
        {
            currentstate = 1;
            return;
        }

        if (localName.equals("lastname"))
        {
            currentstate = 2;
            return;
        }
        
        if (localName.equals("position"))
        {
            currentstate = 3;
            return;
        }
        
        if (localName.equals("team"))
        {
            currentstate = 4;
            return;
        }
        
        currentstate = 0;
        
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

    	depth--;

        if (localName.equals("Table"))
        {
            // add our item to the list!
            _feed.add(_item);
            _item = null;
            return;
        }
    	
    }

    @Override
    public void characters(char ch[], int start, int length) {
    	
        String theString = new String(ch,start,length);
        //Log.i("RSSReader","characters[" + theString + "]");
        
        switch (currentstate)
        {
            case MLBPLAYER_FIRSTNAME:
                _item.setFirstName(theString);
                currentstate = 0;
                break;
            case MLBPLAYER_LASTNAME:
                _item.setLastName(theString);
                currentstate = 0;
                break;
            case MLBPLAYER_POSITION:
                _item.setPosition(theString);
                currentstate = 0;
                break;
            case MLBPLAYER_TEAM:
                _item.setTeam(theString);
                currentstate = 0;
                break;

            default:
                return;
        }
    	
    }   

    private String getAttributeValue(String attName, Attributes atts) {
        String result = null;
        for (int i = 0; i < atts.getLength(); i++) {
            String thisAtt = atts.getLocalName(i);
            if (attName.equals(thisAtt)) {
                result = atts.getValue(i);
                break;
            }
        }
        return result;
    }
    

    public MLBPlayer getMLBPlayerRecord() {
        return mlbPlayerRecord;
    }

    public void setMLBPlayerRecord(MLBPlayer mlbPlayerRecord) {
        this.mlbPlayerRecord = mlbPlayerRecord;
    }

}