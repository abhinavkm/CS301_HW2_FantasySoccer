package soccergame.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {

    private EditText playerName;
    private EditText uniformNum;
    private EditText teamName;
    private EditText playerPosition;
    private Button addPlayer;
    private Button addTeam;
    private Spinner selectTeam;
    private ArrayAdapter<String> adapter;
    public static ArrayList<String> teams;
    public static SoccerData data = new SoccerData();;
    private TextView teamInfo;
    private ImageView teamPic;
    public ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerName = (EditText) findViewById(R.id.playerName);
        uniformNum = (EditText) findViewById(R.id.playerNumber);
        teamName = (EditText) findViewById(R.id.team);
        playerPosition = (EditText) findViewById(R.id.playerPosition);
        selectTeam = (Spinner) findViewById(R.id.teamSelector);
        addPlayer = (Button) findViewById(R.id.addPlayer);
        addTeam = (Button) findViewById(R.id.addTeam);
        teamInfo = (TextView) findViewById(R.id.teamDisplay);
        teamPic = (ImageView) findViewById(R.id.teamPic);

        //Preset teams and players
        data.addTeam("Madrid Mammals");
        data.addTeam("Barcelona Buffalos");
        data.addPlayer("Harvey Specter", 10, data.getTeamName("Madrid Mammals"), "Striker");
        data.addPlayer("Jim Halpert", 5, data.getTeamName("Madrid Mammals"), "Central Midfielder");
        data.addPlayer("Tom Haverford", 1, data.getTeamName("Madrid Mammals"), "Goalkeeper");
        data.addPlayer("Perd Hapley", 25, data.getTeamName("Barcelona Buffalos"), "Right Midfielder");
        data.addPlayer("Dwight Schrute", 9, data.getTeamName("Barcelona Buffalos"), "Left Back");
        data.addPlayer("Louis Litt", 18, data.getTeamName("Barcelona Buffalos"), "Center Back");

        //Spinner
        teams = new ArrayList<String>();
        teams.add(data.getTeamName("Madrid Mammals"));
        teams.add(data.getTeamName("Barcelona Buffalos"));
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, teams.toArray(new String[0]));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTeam.setAdapter(adapter);
        addPlayer.setOnClickListener(new addPlayerListener());
        selectTeam.setOnItemSelectedListener(new teamChange());
        update(true);
    }

    //Get name of selected team
    private String getSelectedTeamName() {
        Object obj = selectTeam.getSelectedItem();
        return (String) obj;
    }

    //Add team to list and database
    public void addTeam(View v) {
        String name = teamName.getText().toString();
        if (teams.indexOf(name) >= 0) return;
        // add the name;
        data.addTeam(name);
        teams.add(data.getTeamName(name));
        Collections.sort(teams);
        // create new adapter, add the updated elements and connect to the spinner
         spinnerAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                        android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTeam.setAdapter(spinnerAdapter);
        spinnerAdapter.addAll(teams);
        spinnerAdapter.notifyDataSetChanged();
        update(true);
    }

    //Reset all fields to empty
    private void update(boolean reLoad) {
        if (reLoad) {
            playerName.setText("");
            teamName.setText("");
            uniformNum.setText("");
            playerPosition.setText("");
        }
    }

    //Get number from TextEdit
    private int getUniformNumber() {
        try {
            return Integer.parseInt(uniformNum.getText().toString().trim());
        }
        catch (NumberFormatException nfx) {
            return -100;
        }
    }

    //Change activity to Playerlist
    public void viewPlayers(View v) {
        Bundle list = new Bundle();
        list.putStringArrayList("TEAMS",teams);
        Intent switchActivity = new Intent(this, PlayerList.class);
        switchActivity.putExtras(list);
        startActivity(switchActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Add players to teams
    public class addPlayerListener  implements View.OnClickListener {
        /**
         * respond to the press of the "New player" button
         *
         * @see android.view.View.OnClickListener#onClick(android.view.View)
         */
        @Override
        public void onClick(View view) {
            // get the data from the relevant text fields
            String name = playerName.getText().toString();
            int uniformNumber = getUniformNumber();
            String position = playerPosition.getText().toString();
            // if any fields were empty or otherwise incorrect, flash and return
            if (name.isEmpty() || uniformNumber < 0) {
                update(true);
                return;
            }
            // attempt to add the player; if unsuccessful, flash and return
            data.addPlayer(name, uniformNumber, getSelectedTeamName(), position);
                update(true);
        }
    }

    //Team info
    public class teamChange implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(data.getTeam(getSelectedTeamName()) == null) {
                return;
            }
            String teamName = getSelectedTeamName();
            int wins = data.getTeam(teamName).getWins();
            int loss = data.getTeam(teamName).getLosses();
            //picture is based on length of team name
            if(teamName.length() < 20) {
                teamPic.setImageResource(R.drawable.smile_blue);
            }
            else {
                teamPic.setImageResource(R.drawable.smile);
            }
            teamInfo.setText(teamName + "\nWins: " + wins + "\nLosses: " + loss);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //no override necessary
        }
    }
}


