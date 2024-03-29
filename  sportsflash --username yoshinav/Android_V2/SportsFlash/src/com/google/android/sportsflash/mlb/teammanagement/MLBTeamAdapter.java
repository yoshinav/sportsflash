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
 * MLBTeamAdapter:  Custom adapter for "Team" model objects.
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class MLBTeamAdapter extends BaseAdapter {

    private static final String CLASSTAG = MLBTeamAdapter.class.getSimpleName();

    private Context context;
    private List<MLBTeam> teams;

    public MLBTeamAdapter(Context context, List<MLBTeam> reviews) {

        Log.v(Constants.LOGTAG, " " + CLASSTAG + " ReviewAdapter const - reviews.size - " + reviews.size());

        this.context = context;
        this.teams = reviews;
    }

    public int getCount() {
        return this.teams.size();
    }

    public Object getItem(int position) {
        return this.teams.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        MLBTeam team = this.teams.get(position);
        return new MLBLeaguesListView(this.context, team.getTeamName(), team.getTeamDescription(), team.getScore());
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

        public MLBLeaguesListView(Context context, String name, String description, int score) {
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
            this.description.setText(description + " - Score: " + Integer.toString(score));
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
