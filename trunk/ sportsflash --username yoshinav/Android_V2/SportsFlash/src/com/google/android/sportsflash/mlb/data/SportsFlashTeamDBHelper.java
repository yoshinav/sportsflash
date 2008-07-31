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
            + "teamName text unique not null, teamDescription text unique not null, leagueId integer unique not null" +
            ", 1bid integer unique not null, 2bid integer unique not null, 3bid integer unique not null" +
            ", ssid integer unique not null, pid integer unique not null, rfid integer unique not null" +
            ", cfid integer unique not null, lfid integer unique not null, dhid integer unique not null);";
    private static final String DATABASE_NAME = "sportsflashdb";
    private static final String DATABASE_TABLE = "sportsflashMLBFantasyTeam";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public SportsFlashTeamDBHelper(Context ctx) {
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

    public int createRow(String teamName, String teamDescription) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " create row - " + teamName + " , " + teamDescription);
        ContentValues initialValues = new ContentValues();
        initialValues.put("teamName", teamName);
        initialValues.put("teamDescription", teamDescription);
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

    public List<Row> fetchAllRows() {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " fetchAllRows");
        ArrayList<Row> ret = new ArrayList<Row>();
        try {
            Cursor c = db.query(DATABASE_TABLE, new String[] { "rowid", "teamName", "teamDescription" }, null, null, null, null, null);
            int numRows = c.count();
            c.first();
            for (int i = 0; i < numRows; ++i) {
                Row row = new Row();
                row.rowId = c.getLong(0);
                row.teamName = c.getString(1);
                row.teamDescription = c.getString(2);
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
        Cursor c = db.query(true, DATABASE_TABLE, new String[] { "rowId", "teamName", "teamDescription" }, where, null, null, null, null);
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
