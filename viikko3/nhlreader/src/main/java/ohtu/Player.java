
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String team;
    private String nationality;

    private int goals;
    private int assists;

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality){
        this.nationality = nationality;
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

     public String getNationality(){
        return nationality;
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
        return name + " team:  " + team + " " + goals + " + " + assists + " = " + (goals+ assists);
    }

    @Override
    public int compareTo(Player t) {
        return (goals + assists) - (t.goals + t.assists);
    }

}
