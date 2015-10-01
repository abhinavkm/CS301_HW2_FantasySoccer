package soccergame.assignment2;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class SoccerField extends MainActivity{

    private Canvas c;
    SurfaceField pitch;
    private TextView team1;
    private TextView team2;
    private TextView winDisplay;
    String yourteam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccer_field);
        //find all the views
        pitch = (SurfaceField) findViewById(R.id.field);
        team1 = (TextView) findViewById(R.id.team1);
        team2 = (TextView) findViewById(R.id.team2);
        winDisplay = (TextView) findViewById(R.id.winner);
        Bundle list = this.getIntent().getExtras();
         yourteam = (String) list.get("yourTeam");
        team1.setText(yourteam);
        //select the opponent randomly
        double selectopp = (Math.random()*2);
        if(selectopp < 1) {
            team1.setText(data.getTeam("Madrid Mammals").teamName);
        }
        else {
            team2.setText(data.getTeam("Barcelona Buffalos").teamName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_soccer_field, menu);
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

    //Return to previous activity
    public void quitGame(View v)
    {
        finish();
    }

    //Play game
    public void kickOff(View v) {

        //Randomly select winner
        double selectWin = (Math.random()*2);
        if(selectWin < 1) {
            winDisplay.setText("Winner: " + yourteam);
            winDisplay.setVisibility(View.VISIBLE);
            if(data.getTeam(yourteam) != null) {
                data.getTeam(yourteam).winner();
            }
            data.getTeam((String) team2.getText()).loser();
        }
        else {
            winDisplay.setText("Winner: " + team2.getText());
            winDisplay.setVisibility(View.VISIBLE);
            data.getTeam((String) team2.getText()).winner();
            if(data.getTeam(yourteam) != null) {
                data.getTeam(yourteam).loser();
            }
        }
    }
}
