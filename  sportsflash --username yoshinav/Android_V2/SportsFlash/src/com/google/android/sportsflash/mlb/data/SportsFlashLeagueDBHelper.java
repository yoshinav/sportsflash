package com.google.android.sportsflash.mlb.data;

/**
 * SportsFlashLeagueDBHelper:  Database Helper Functions for SportsFlash League
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

public class SportsFlashLeagueDBHelper {

    private static final String CLASSTAG = SportsFlashLeagueDBHelper.class.getSimpleName();
    public static final String KEY_ROWID="_id";
    
    public class Row extends Object {
        public long rowId;
        public String leagueName;
        public String leagueDescription;
    }

    private static final String DATABASE_CREATE = "create table sportsflashMLBFantasyLeague (rowId integer primary key autoincrement, "
            + "leagueName text unique not null, leagueDescription text unique not null);";
    private static final String DATABASE_NAME = "sportsflashdb";
    private static final String DATABASE_TABLE = "sportsflashMLBFantasyLeague";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public SportsFlashLeagueDBHelper(Context ctx) {
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
    }

    public void close() {
        db.close();
    }

    public int createRow(String leagueName, String leagueDescription) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " create row - " + leagueName + " , " + leagueDescription);
        ContentValues initialValues = new ContentValues();
        initialValues.put("leagueName", leagueName);
        initialValues.put("leagueDescription", leagueDescription);
        return (int) db.insert(DATABASE_TABLE, null, initialValues);
    }

    public void deleteRow(long rowId) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " deleteRow - " + rowId);
        db.delete(DATABASE_TABLE, "rowid=" + rowId, null);
    }

    public void deleteRow(String leagueName) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " delete row - " + leagueName);
        db.delete(DATABASE_TABLE, "leagueName=" + leagueName, null);
    }

    public List<MLBLeague> fetchAllRows() {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " fetchAllRows");
        ArrayList<MLBLeague> ret = new ArrayList<MLBLeague>();
        try {
            Cursor c = db.query(DATABASE_TABLE, new String[] { "rowid", "leagueName", "leagueDescription" }, null, null, null, null, null);
            int numRows = c.count();
            c.first();
            for (int i = 0; i < numRows; ++i) {
            	MLBLeague row = new MLBLeague();
                row.setLeagueID(c.getInt(0));
                row.setLeagueName( c.getString(1));
                row.setLeagueDescription(c.getString(2));
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
        Cursor c = db.query(true, DATABASE_TABLE, new String[] { "rowId", "leagueName", "leagueDescription" }, where, null, null, null, null);
        row.rowId = c.getLong(0);
        row.leagueName = c.getString(1);
        row.leagueDescription = c.getString(2);
        return row;
    }

    public Row fetchRow(long rowId) {
        return fetchRow("rowId=" + rowId, false);
    }

    public Row fetchRow(String leagueName) {
        return fetchRow("leagueName=" + leagueName, false);
    }

    public void updateRow(long rowId, String leagueName, String leagueDescription) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " updateRow");
        ContentValues args = new ContentValues();
        args.put("leagueName", leagueName);
        args.put("leagueDescripton", leagueDescription);
        db.update(DATABASE_TABLE, args, "rowId=" + rowId, null);
    }
}
