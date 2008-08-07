package com.google.android.sportsflash.mlb.teammanagement;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.sportsflash.mlb.data.*;

/**
 * MLBLeagueAdapter:  Custom adapter for "League" model objects.
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class MLBLeagueAdapter extends BaseAdapter {

    private static final String CLASSTAG = MLBLeagueAdapter.class.getSimpleName();

    private Context context;
    private List<MLBLeague> leagues;

    public MLBLeagueAdapter(Context context, List<MLBLeague> reviews) {

        Log.v(Constants.LOGTAG, " " + CLASSTAG + " ReviewAdapter const - reviews.size - " + reviews.size());

        this.context = context;
        this.leagues = reviews;
    }

    public int getCount() {
        return this.leagues.size();
    }

    public Object getItem(int position) {
        return this.leagues.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        MLBLeague league = this.leagues.get(position);
        return new MLBLeaguesListView(this.context, league.getLeagueName(), league.getLeagueDescription());
    }

    /**
     * ReviewListView that adapter returns as it's view item per row. 
     * 
     * @author charliecollins
     *
     */
    private class MLBLeaguesListView extends LinearLayout {

        private TextView name;
        private TextView description;

        public MLBLeaguesListView(Context context, String name, String description) {
            super(context);

            // TODO display rating as stars
            this.setOrientation(VERTICAL);
            
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            params.setMargins(5, 3, 5, 0);

            this.name = new TextView(context);
            this.name.setText(name);
            this.name.setTextSize(16f);
            this.name.setTextColor(Color.BLACK);
            addView(this.name, params);

            this.description = new TextView(context);
            this.description.setText(description);
            this.description.setTextSize(16f);
            addView(this.description, params);            
        }

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getDescription() {
            return description;
        }

        public void setDescription(TextView description) {
            this.description = description;
        }
    }
}
