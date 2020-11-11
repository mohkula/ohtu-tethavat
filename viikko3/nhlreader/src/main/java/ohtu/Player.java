
package ohtu;

public class Player {
    private String name;
    private String team;

    private int goals;
    private int assists;

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(String team){
        this.team = team;
    }

    public void setGoals(int goals){
        this.goals = goals;
    }

    public void setAssists(int assists){
        this.assists = assists;
    }

    public int getGoals(){
        return goals;
    }

    public int getAssists(){
        return assists;
    }

    public String getName() {
        return name;
    }
    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return name + " team:  " + team + " goals: " + goals + " assists: " + assists;
    }

}
