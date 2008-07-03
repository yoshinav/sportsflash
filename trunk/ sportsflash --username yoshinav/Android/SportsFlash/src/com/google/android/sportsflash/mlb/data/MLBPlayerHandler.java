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

public class MLBPlayerHandler extends DefaultHandler {

    //private static final String CLASSTAG = YWeatherHandler.class.getSimpleName();

    private static final String YLOC = "yweather:location";
    private static final String YWIND = "yweather:wind";
    private static final String YATMO = "yweather:atmosphere";
    private static final String YASTRO = "yweather:astronomy";
    private static final String YCOND = "yweather:condition";
    private static final String YFCAST = "yweather:forecast";

    private int forecastCount;
    private MLBPlayer mlbPlayerRecord;

    public MLBPlayerHandler() {
        mlbPlayerRecord = new MLBPlayer();
    }

    @Override
    public void startDocument() throws SAXException {
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " startDocument");
    }

    @Override
    public void endDocument() throws SAXException {
        //Log.v(Constants.LOGTAG, " " + CLASSTAG + " endDocument");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        
    	/*
    	if (localName.equals(YLOC)) {
            mlbPlayerRecord.setCity(getAttributeValue("city", atts));
            mlbPlayerRecord.setRegion(getAttributeValue("region", atts));
            mlbPlayerRecord.setCountry(getAttributeValue("country", atts));
        }

        if (localName.equals(YWIND)) {
            mlbPlayerRecord.setWindChill(getAttributeValue("chill", atts));
            int windDirectionDegrees = Integer.parseInt(getAttributeValue("direction", atts));
            mlbPlayerRecord.setWindDirection(convertDirection(windDirectionDegrees));
            mlbPlayerRecord.setWindSpeed(Integer.parseInt(getAttributeValue("speed", atts)));
        }

        if (localName.equals(YATMO)) {
            mlbPlayerRecord.setHumidity(Integer.parseInt(getAttributeValue("humidity", atts)));
            mlbPlayerRecord.setVisibility(Integer.parseInt(getAttributeValue("visibility", atts)));
            mlbPlayerRecord.setPressure(Double.parseDouble(getAttributeValue("pressure", atts)));
            String pressureState = getAttributeValue("rising", atts);
            if (pressureState.equals("0")) {
                mlbPlayerRecord.setPressureState(MLBPlayerRecord.PRESSURE_STEADY);
            } else if (pressureState.equals("1")) {
                mlbPlayerRecord.setPressureState(MLBPlayerRecord.PRESSURE_FALLING);
            } else if (pressureState.equals("2")) {
                mlbPlayerRecord.setPressureState(MLBPlayerRecord.PRESSURE_RISING);
            }
        }

        if (localName.equals(YASTRO)) {
            mlbPlayerRecord.setSunrise(getAttributeValue("sunrise", atts));
            mlbPlayerRecord.setSunset(getAttributeValue("sunset", atts));
        }

        if (localName.equals(YCOND)) {
            mlbPlayerRecord.setTemp(Integer.parseInt(getAttributeValue("temp", atts)));
            int code = Integer.parseInt(getAttributeValue("code", atts));
            WeatherCondition cond = WeatherCondition.getWeatherCondition(code);
            mlbPlayerRecord.setCondition(cond);
            mlbPlayerRecord.setDate(getAttributeValue("date", atts));
        }

        if (localName.equals(YFCAST)) {
            if (forecastCount < 2) {
                WeatherForecast forecast = new WeatherForecast();
                forecast.setDate(getAttributeValue("date", atts));
                forecast.setDay(getAttributeValue("day", atts));
                forecast.setHigh(Integer.parseInt(getAttributeValue("high", atts)));
                forecast.setLow(Integer.parseInt(getAttributeValue("low", atts)));
                int code = Integer.parseInt(getAttributeValue("code", atts));
                WeatherCondition cond = WeatherCondition.getWeatherCondition(code);
                forecast.setCondition(cond);
                mlbPlayerRecord.getForecasts()[forecastCount] = forecast;
            }
            forecastCount++;
        }
        */
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char ch[], int start, int length) {
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