public class Match {
    int numberOfOvers;

    public int getNumberOfOvers() {
        return numberOfOvers;
    }

    Team t1;
    Team t2;

    public Match(int numberOfOvers){
        this.numberOfOvers = numberOfOvers;
        t1 = new Team();
        t2 = new Team();
        t1.setTeamName("Team A");
        t2.setTeamName("Team B");
    }

    public Team getT1() {
        return t1;
    }

    public void setT1(Team t1) {
        this.t1 = t1;
    }

    public Team getT2() {
        return t2;
    }

    public void setT2(Team t2) {
        this.t2 = t2;
    }


}
