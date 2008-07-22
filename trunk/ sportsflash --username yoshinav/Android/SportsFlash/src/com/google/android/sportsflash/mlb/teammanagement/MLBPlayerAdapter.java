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
 * MLBPlayerAdapter:  Custom adapter for "Player" model objects.
 * 
 * @author Navdeep Alam, with credit to charliecollins
 * @version CS 893 Summer 2008 Version 1.0
 * 
 */

public class MLBPlayerAdapter extends BaseAdapter {

    private static final String CLASSTAG = MLBPlayerAdapter.class.getSimpleName();

    private Context context;
    private List<MLBPlayer> players;

    public MLBPlayerAdapter(Context context, List<MLBPlayer> reviews) {

        Log.v(Constants.LOGTAG, " " + CLASSTAG + " ReviewAdapter const - reviews.size - " + reviews.size());

        this.context = context;
        this.players = reviews;
    }

    public int getCount() {
        return this.players.size();
    }

    public Object getItem(int position) {
        return this.players.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        MLBPlayer player = this.players.get(position);
        return new MLBPlayerListView(this.context, player.getLastName(), player.getPosition());
    }

    /**
     * ReviewListView that adapter returns as it's view item per row. 
     * 
     * @author charliecollins
     *
     */
    private class MLBPlayerListView extends LinearLayout {

        private TextView name;
        private TextView rating;
        private TextView date;

        public MLBPlayerListView(Context context, String name, String rating) {
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

            this.rating = new TextView(context);
            this.rating.setText(rating);
            this.rating.setTextSize(16f);
            addView(this.rating, params);            
        }

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getRating() {
            return rating;
        }

        public void setRating(TextView rating) {
            this.rating = rating;
        }

        public TextView getDate() {
            return date;
        }

        public void setDate(TextView date) {
            this.date = date;
        }
    }
}
