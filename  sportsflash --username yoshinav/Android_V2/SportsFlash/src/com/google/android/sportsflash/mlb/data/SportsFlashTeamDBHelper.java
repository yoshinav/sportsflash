package com.google.android.sportsflash.mlb.data;

/**
 * SportsFlashLeagueDBHelper:  Database Helper Functions for SportsFlash Team
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.sportsflash.mlb.teammanagement.Constants;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SportsFlashTeamDBHelper {

    private static final String CLASSTAG = SportsFlashTeamDBHelper.class.getSimpleName();
    public static final String KEY_ROWID="_id";
    
    class Row extends Object {
        public long rowId;
        public String teamName;
        public String teamDescription;     
    }

    private static final String DATABASE_CREATE = "create table sportsflashMLBFantasyTeam (rowId integer primary key autoincrement, "
            + "leagueId integer not null, teamName text not null, teamDescription text not null" +
            ", [1bid] integer null, [2bid] integer null, [3bid] integer null" +
            ", [ssid] integer null, [pid] integer null, [rfid] integer null" +
            ", [cfid] integer null, [lfid] integer null, [dhid] integer null, [cid] integer null);";
    private static final String DATABASE_NAME = "sportsflashdb";
    private static final String DATABASE_TABLE = "sportsflashMLBFantasyTeam";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public SportsFlashTeamDBHelper(Context ctx) {
    	//Check for existence of Database
        try {
            db = ctx.openDatabase(DATABASE_NAME, null);
            Log.v(Constants.LOGTAG, " " + CLASSTAG + " opened database");
        } catch (FileNotFoundException e) {
            try {
                db = ctx.createDatabase(DATABASE_NAME, DATABASE_VERSION, 0, null);
                db.execSQL(DATABASE_CREATE);
                Log.v(Constants.LOGTAG, " " + CLASSTAG + " created database");
            } catch (FileNotFoundException e1) {
                db = null;
            }
        }
        
        //Check for Existence of Table
        try
        {
        	if(db != null)
        	{
        		db.query(DATABASE_TABLE, new String[] { "rowid", "teamName", "teamDescription", "leagueId", "[1bid]", "[2bid]", "[3bid]", "[ssid]", "[pid]", "[rfid]", "[cfid]", "[lfid]", "[dhid]", "[cid]" }, null, null, null, null, null);        	}
        }
        catch (Exception e)
        {
        	db.execSQL(DATABASE_CREATE);
        }
    }

    public void close() {
        db.close();
    }

    public int createRow(int leagueId, String teamName, String teamDescription) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " create row - " + teamName + " , " + teamDescription);
        ContentValues initialValues = new ContentValues();
       //if(leagueId < 0)
        {
        	leagueId = 99;
        }
        
        initialValues.put("leagueId", leagueId);
        initialValues.put("teamName", teamName);
        initialValues.put("teamDescription", teamDescription);
        initialValues.put("[1bid]", "");
        initialValues.put("[2bid]", "");
        initialValues.put("[3bid]", "");
        initialValues.put("[ssid]", "");
        initialValues.put("[pid]", "");
        initialValues.put("[rfid]", "");
        initialValues.put("[cfid]", "");
        initialValues.put("[lfid]", "");
        initialValues.put("[dhid]", "");
        initialValues.put("[cid]", "");
        return (int) db.insert(DATABASE_TABLE, null, initialValues);
    }

    public void deleteRow(long rowId) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " deleteRow - " + rowId);
        db.delete(DATABASE_TABLE, "rowid=" + rowId, null);
    }

    public void deleteRow(String teamName) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " delete row - " + teamName);
        db.delete(DATABASE_TABLE, "teamName=" + teamName, null);
    }

    public List<MLBTeam> fetchAllRows() {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " fetchAllRows");
        ArrayList<MLBTeam> ret = new ArrayList<MLBTeam>();
        try {
            Cursor c = db.query(DATABASE_TABLE, new String[] { "rowid", "teamName", "teamDescription", "leagueId", "[1bid]", "[2bid]", "[3bid]", "[ssid]", "[pid]", "[rfid]", "[cfid]", "[lfid]", "[dhid]", "[cid]" }, null, null, null, null, null);
            int numRows = c.count();
            c.first();
            for (int i = 0; i < numRows; ++i) {
            	MLBTeam row = new MLBTeam();
                row.setLeagueID(c.getInt(0));
                row.setTeamName( c.getString(1));
                row.setTeamDescription(c.getString(2));
                ret.add(row);
                c.next();
            }
        } catch (SQLException e) {
            Log.e(Constants.LOGTAG, " " + CLASSTAG, e);
        }
        return ret;
    }

    private Row fetchRow(String where, boolean hack) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " fetchRow");
        Row row = new Row();
        Cursor c = db.query(true, DATABASE_TABLE, new String[] { "rowid", "teamName", "teamDescription", "leagueId", "[1bid]", "[2bid]", "[3bid]", "[ssid]", "[pid]", "[rfid]", "[cfid]", "[lfid]", "[dhid]", "[cid]" }, where, null, null, null, null);
        row.rowId = c.getLong(0);
        row.teamName = c.getString(1);
        row.teamDescription = c.getString(2);
        return row;
    }

    public Row fetchRow(long rowId) {
        return fetchRow("rowId=" + rowId, false);
    }

    public Row fetchRow(String teamName) {
        return fetchRow("teamName=" + teamName, false);
    }

    public void updateRow(long rowId, String teamName, String teamDescription) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " updateRow");
        ContentValues args = new ContentValues();
        args.put("teamName", teamName);
        args.put("leagueDescripton", teamDescription);
        db.update(DATABASE_TABLE, args, "rowId=" + rowId, null);
    }
    
    public void updatePlayerRow(long rowId, String teamName, String teamDescription) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " updateRow");
        ContentValues args = new ContentValues();
        args.put("teamName", teamName);
        args.put("leagueDescripton", teamDescription);
        db.update(DATABASE_TABLE, args, "rowId=" + rowId, null);
    }    
}
