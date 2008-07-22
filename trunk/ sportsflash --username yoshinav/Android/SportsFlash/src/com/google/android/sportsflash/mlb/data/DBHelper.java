package com.google.android.sportsflash.mlb.data;

/**
 * DBHelper:  Database Helper Functions
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

public class DBHelper {

    private static final String CLASSTAG = DBHelper.class.getSimpleName();

    class Row extends Object {
        public long rowId;
        public String zip;
    }

    private static final String DATABASE_CREATE = "create table alert (rowid integer primary key autoincrement, "
            + "zip text unique not null);";
    private static final String DATABASE_NAME = "alertdb";
    private static final String DATABASE_TABLE = "alert";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public DBHelper(Context ctx) {
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

    public void createRow(String zip) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " create row - " + zip);
        ContentValues initialValues = new ContentValues();
        initialValues.put("zip", zip);
        db.insert(DATABASE_TABLE, null, initialValues);
    }

    public void deleteRow(long rowId) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " deleteRow - " + rowId);
        db.delete(DATABASE_TABLE, "rowid=" + rowId, null);
    }

    public void deleteRow(String zip) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " delete row - " + zip);
        db.delete(DATABASE_TABLE, "zip=" + zip, null);
    }

    public List<Row> fetchAllRows() {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " fetchAllRows");
        ArrayList<Row> ret = new ArrayList<Row>();
        try {
            Cursor c = db.query(DATABASE_TABLE, new String[] { "rowid", "zip" }, null, null, null, null, null);
            int numRows = c.count();
            c.first();
            for (int i = 0; i < numRows; ++i) {
                Row row = new Row();
                row.rowId = c.getLong(0);
                row.zip = c.getString(1);
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
        Cursor c = db.query(true, DATABASE_TABLE, new String[] { "rowid", "zip" }, where, null, null, null, null);
        row.rowId = c.getLong(0);
        row.zip = c.getString(1);
        return row;
    }

    public Row fetchRow(long rowId) {
        return fetchRow("rowid=" + rowId, false);
    }

    public Row fetchRow(String zip) {
        return fetchRow("zip=" + zip, false);
    }

    public void updateRow(long rowId, String zip) {
        Log.v(Constants.LOGTAG, " " + CLASSTAG + " updateRow");
        ContentValues args = new ContentValues();
        args.put("zip", zip);
        db.update(DATABASE_TABLE, args, "rowid=" + rowId, null);
    }
}
