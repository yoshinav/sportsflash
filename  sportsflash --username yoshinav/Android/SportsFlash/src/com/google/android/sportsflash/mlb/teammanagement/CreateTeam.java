package com.google.android.sportsflash.mlb.teammanagement;

import com.google.android.sportsflash.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Menu.Item;


public class CreateTeam extends Activity {

    public CreateTeam() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle icicle) { 
    	super.onCreate(icicle); 
    	setContentView(R.layout.create_team);
    }
}
