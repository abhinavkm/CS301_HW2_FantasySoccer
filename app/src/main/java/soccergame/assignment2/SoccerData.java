package soccergame.assignment2;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;


/**
 * Created by Abhinav on 9/19/2015.
 */

public class SoccerData implements Serializable {
    Hashtable<String, SoccerPlayer> playerData;
    Hashtable<String, SoccerTeam> teamData;

    //Use hashtable to store data
    public SoccerData() {
        Hashtable<String, SoccerPlayer> playerDB = new Hashtable<String, SoccerPlayer>();
        this.playerData=playerDB;
        Hashtable<String, SoccerTeam> teamDB = new Hashtable<String, SoccerTeam>();
        this.teamData=teamDB;
    }
    //
    public void addPlayer(String name, int uniformNumber, String teamName, String position) {
        SoccerPlayer Player = new SoccerPlayer(name,teamName, uniformNumber, position);
        playerData.put(name, Player);
    }

    public void addTeam(String name) {
        SoccerTeam newTeam = new SoccerTeam(name);
        teamData.put(name, newTeam);
    }

    //Getter methods
    public SoccerPlayer getPlayer(String name)
    {
        return playerData.get(name);
    }

    public SoccerTeam getTeam(String name)
    {
        return teamData.get(name);
    }

    public String getTeamName(String name)
    {
        return teamData.get(name).teamName;
    }


    //finds the players on the team
    public SoccerPlayer playerNum(int idx, String teamName) {
        Collection<String> theTeam = Collections.list(playerData.keys());
        SoccerPlayer[] players = new SoccerPlayer[20];
        int i=0;
        for( String s : theTeam) {
            if (playerData.get(s).getTeam().equalsIgnoreCase(teamName)) {
                players[i] = playerData.get(s);
                        i++;
            }
        }
        return players[idx];
    }
}
