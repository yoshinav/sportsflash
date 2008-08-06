package com.google.android.sportsflash.mlb.data;

/**
 * MLBLeagueHandler:  SAX Handler
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MLBTeamHandler extends DefaultHandler {

    final int MLBLEAGUE_ID = 1;
    
    int ID = 0;
    int currentstate = 0;

    private int identity_ID;

    public MLBTeamHandler() {
        
    }
    /*
     * getFeed - this returns our feed when all of the parsing is complete
     */
    public int getMLBID()
    {
    	return getIdentityID();
    }

    @Override
    public void startDocument() throws SAXException {


    }

    @Override
    public void endDocument() throws SAXException {
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " endDocument");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        
      
        if (localName.equals("ID"))
        {
            currentstate = 1;
            return;
        }
        
        currentstate = 0;
        
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

    	
    }

    @Override
    public void characters(char ch[], int start, int length) {
    	
        String theString = new String(ch,start,length);
        //Log.i("RSSReader","characters[" + theString + "]");
        
        switch (currentstate)
        {
            case MLBLEAGUE_ID:
                setIdentityID(Integer.valueOf(theString).intValue());
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
    

    private int getIdentityID() {
        return this.identity_ID;
    }

    private void setIdentityID(int identityID) {
        this.identity_ID = identityID;
    }

}