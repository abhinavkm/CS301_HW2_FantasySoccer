package soccergame.assignment2;

/**
 * Created by Abhinav on 9/20/2015.
 */
public class SoccerPlayer {
    String name;
    String team;
    int uniform;
    int goals;
    String position;
    int fouls;
    int assists;
    int saves;

    //Constructor
    public SoccerPlayer(String iname, String iteam, int iuniform, String iposition) {
       name = iname;
       team = iteam;
       uniform = iuniform;
       position = iposition;
       goals = 0;
       fouls = 0;
       assists = 0;
       saves = 0;
   }

    //Getters and setters
    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public int getUniform() {
        return uniform;
    }

    public String getPosition() {
        return position;
    }

    public int getAssists() {
        return assists;
    }

    public int getSaves() {
        return saves;
    }

    public int getFouls() {
        return fouls;
    }

    public int getGoals() {
        return goals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setUniform(int uniform) {
        this.uniform = uniform;
    }

    //Increment stats
    public void setGoals() {this.goals++;}

    public void setPosition(String position) {
        this.position = position;
    }

    public void setFouls() {
        this.fouls++;
    }

    public void setAssists() {
        this.assists++;
    }

    public void setSaves() {
        this.saves++;
    }

}
