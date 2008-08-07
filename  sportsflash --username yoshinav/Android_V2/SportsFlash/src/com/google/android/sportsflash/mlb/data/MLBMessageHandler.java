package com.google.android.sportsflash.mlb.data;

/**
 * MLBLeagueHandler:  SAX Handler for Messages
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MLBMessageHandler extends DefaultHandler {

	List<MLBMessage> _feed;
	MLBMessage _item;
	
    final int MLBMESSAGE_TITLE = 1;
    final int MLBMESSAGE_DESCRIPTION = 2;
    
    int depth = 0;
    int currentstate = 0;

    private int identity_ID;
    private MLBMessage mlbMessageRecord;

    public MLBMessageHandler() {
    	mlbMessageRecord = new MLBMessage();
    }
    /*
     * getFeed - this returns our feed when all of the parsing is complete
     */
    public List<MLBMessage> getFeed()
    {
        return _feed;
    }
    
    @Override
    public void startDocument() throws SAXException {
    	_feed = new ArrayList<MLBMessage>(20);

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
            _item = new MLBMessage();
            mlbMessageRecord = new MLBMessage();
            
            currentstate = 0;
            return;
        }
        
        if (localName.equals("title"))
        {
            currentstate = 1;
            return;
        }

        if (localName.equals("message"))
        {
            currentstate = 2;
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
            setMLBMessageRecord(_item);
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
            case MLBMESSAGE_TITLE:
                _item.setMsgTitle(theString);
                currentstate = 0;
                break;
            case MLBMESSAGE_DESCRIPTION:
                _item.setMsgDescription(theString);
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
    

    public MLBMessage getMLBMessageRecord() {
        return this.mlbMessageRecord;
    }

    public void setMLBMessageRecord(MLBMessage mlbMessageRecord) {
    	this.mlbMessageRecord = mlbMessageRecord;
    }


}