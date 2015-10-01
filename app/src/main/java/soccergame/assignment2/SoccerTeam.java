package soccergame.assignment2;

/**
 * Created by Abhinav on 9/19/2015.
 */
public class SoccerTeam {
    String teamName;
    int wins;
    int losses;

    public SoccerTeam(String inname) {
        teamName = inname;
        wins = 0;
        losses =0;
    }
    public void winner()
    {
        wins++;
    }
    public int getWins()
    {
        return wins;
    }
    public void loser()
    {
        losses++;
    }
    public int getLosses()
    {
        return losses;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String inteam) {
        this.teamName = inteam;
    }
}
