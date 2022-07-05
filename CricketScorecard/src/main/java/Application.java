import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of overs");
        Match match = new Match(sc.nextInt());
        System.out.println("Enter number of players each Team");
        int numberOfPlayers = sc.nextInt();
        System.out.println("Enter Player Names of team 1 in new lines");
        formTeam(sc, numberOfPlayers, match.getT1());
        System.out.println("Enter Player Names of team 1 in new lines");
        formTeam(sc, numberOfPlayers, match.getT2());
        ScoreboardManager manager = new ScoreboardManager();
        manager.matchDetails(match, match.getT1(), numberOfPlayers, sc);
        manager.matchDetails(match, match.getT2(), numberOfPlayers, sc);

    }

    private static void formTeam(Scanner sc, int numberOfPlayers, Team team){
        List<Player> players = new ArrayList<>();
        for(int i=0; i<numberOfPlayers; i++){
            Player p = new Player();
            p.setName(sc.next());
            players.add(p);
        }
        team.setPlayer(players);

    }
}
