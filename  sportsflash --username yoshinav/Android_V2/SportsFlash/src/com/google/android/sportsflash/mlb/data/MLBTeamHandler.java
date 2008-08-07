package com.google.android.sportsflash.mlb.data;

/**
 * MLBLeagueHandler:  SAX Handler for Team
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


public class MLBTeamHandler extends DefaultHandler {

	List<MLBTeam> _feed;
	MLBTeam _item;
	
    final int MLBTEAM_LEAGUENAME = 1;
    final int MLBTEAM_TEAMNAME = 2;
    final int MLBTEAM_DESCRIPTION = 3;
    final int MLBTEAM_SCORE = 4;
    final int MLBTEAM_ID = 5;
    final int MLBTEAM_FIRSTBASEID = 6;
    final int MLBTEAM_SECONDBASEID = 7;
    final int MLBTEAM_THIRDBASEID = 8;
    final int MLBTEAM_SHORTSTOPID = 9;    
    final int MLBTEAM_CATCHERID = 10;
    final int MLBTEAM_PITCHERID = 11;
    final int MLBTEAM_LEFTFIELDID = 12;
    final int MLBTEAM_CENTERFIELDID = 13;
    final int MLBTEAM_RIGHTFIELDID = 14;
    final int MLBTEAM_DHID = 15;
    
    int depth = 0;
    int currentstate = 0;

    private int identity_ID;
    private MLBTeam mlbTeamRecord;

    public MLBTeamHandler() {
    	mlbTeamRecord = new MLBTeam();
    }
    /*
     * getFeed - this returns our feed when all of the parsing is complete
     */
    public List<MLBTeam> getFeed()
    {
        return _feed;
    }
    
    @Override
    public void startDocument() throws SAXException {
    	_feed = new ArrayList<MLBTeam>(20);

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
            _item = new MLBTeam();
            mlbTeamRecord = new MLBTeam();
            
            currentstate = 0;
            return;
        }
        
        if (localName.equals("LeagueName"))
        {
            currentstate = 1;
            return;
        }

        if (localName.equals("TeamName"))
        {
            currentstate = 2;
            return;
        }
        
        if (localName.equals("TeamDescription"))
        {
            currentstate = 3;
            return;
        }
        
        if (localName.equals("Score"))
        {
            currentstate = 4;
            return;
        }
        
        if (localName.equals("teamid"))
        {
            currentstate = 5;
            return;
        }        
        
        if (localName.equals("_x0031_bid"))
        {
            currentstate = 6;
            return;
        }
        
        if (localName.equals("_x0032_bid"))
        {
            currentstate = 7;
            return;
        }        

        if (localName.equals("_x0033_bid"))
        {
            currentstate = 8;
            return;
        }        
        
        if (localName.equals("ssid"))
        {
            currentstate = 9;
            return;
        }        
        
        if (localName.equals("cid"))
        {
            currentstate = 10;
            return;
        }        
        
        if (localName.equals("pid"))
        {
            currentstate = 11;
            return;
        }        
        
        if (localName.equals("rfid"))
        {
            currentstate = 12;
            return;
        } 
        
        if (localName.equals("cfid"))
        {
            currentstate = 13;
            return;
        }         
        
        if (localName.equals("lfid"))
        {
            currentstate = 14;
            return;
        }         
        
        if (localName.equals("dhid"))
        {
            currentstate = 15;
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
            setMLBTeamRecord(_item);
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
            case MLBTEAM_LEAGUENAME:
                _item.setLeagueName(theString);
                currentstate = 0;
                break;
            case MLBTEAM_TEAMNAME:
                _item.setTeamName(theString);
                currentstate = 0;
                break;
            case MLBTEAM_DESCRIPTION:
                _item.setTeamDescription(theString);
                currentstate = 0;
                break;
            case MLBTEAM_SCORE:
                _item.setScore(Integer.parseInt(theString));
                currentstate = 0;
                break;
            case MLBTEAM_ID:
                _item.setTeamID(Integer.parseInt(theString));
                currentstate = 0;
                break;
            case MLBTEAM_FIRSTBASEID:
                _item.setTeam1bid(Integer.parseInt(theString));
                currentstate = 0;
                break;      
            case MLBTEAM_SECONDBASEID:
                _item.setTeam2bid(Integer.parseInt(theString));
                currentstate = 0;
                break;  
            case MLBTEAM_THIRDBASEID:
                _item.setTeam3bid(Integer.parseInt(theString));
                currentstate = 0;
                break;      
            case MLBTEAM_SHORTSTOPID:
                _item.setTeamssid(Integer.parseInt(theString));
                currentstate = 0;
                break;
            case MLBTEAM_CATCHERID:
                _item.setTeamcid(Integer.parseInt(theString));
                currentstate = 0;
                break;       
            case MLBTEAM_LEFTFIELDID:
                _item.setTeamlfid(Integer.parseInt(theString));
                currentstate = 0;
                break;
            case MLBTEAM_CENTERFIELDID:
                _item.setTeamcfid(Integer.parseInt(theString));
                currentstate = 0;
                break;
            case MLBTEAM_RIGHTFIELDID:
                _item.setTeamrfid(Integer.parseInt(theString));
                currentstate = 0;
                break; 
            case MLBTEAM_DHID:
                _item.setTeamdhid(Integer.parseInt(theString));
                currentstate = 0;
                break; 
            case MLBTEAM_PITCHERID:
                _item.setTeampid(Integer.parseInt(theString));
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
    

    public MLBTeam getMLBTeamRecord() {
        return this.mlbTeamRecord;
    }

    public void setMLBTeamRecord(MLBTeam mlbTeamRecord) {
    	this.mlbTeamRecord = mlbTeamRecord;
    }

}