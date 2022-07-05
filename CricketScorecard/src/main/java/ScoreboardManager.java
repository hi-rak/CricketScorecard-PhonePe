import java.util.Scanner;

public class ScoreboardManager {

    public void matchDetails(Match match, Team team, int numberOfPlayers, Scanner sc) {
        int onFieldIndex = 1;
        Player b1 = team.getPlayer().get(0);
        b1.setOnStrike(true);
        b1.setPosition(1);
        Player b2 = team.getPlayer().get(1);
        b2.setOnStrike(false);
        b2.setPosition(1);
        Player onStrikeTracker = b1;
        for (int i = 0; i < match.getNumberOfOvers(); i++) {
            System.out.println("Enter the ball details for "
                    + team.getTeamName() + " in new lines");
            float ballCount = 0;

            while (ballCount < 6 && team.getWickets() < (numberOfPlayers - 1)) {
                String ball = sc.next();
                setOvers(team, ballCount);
                if (ball.equalsIgnoreCase("Nb") ||
                        ball.equalsIgnoreCase("Wd")) {
                    team.setScore(team.getScore() + 1);
                } else if (ball.equalsIgnoreCase("W")) {
                    team.setWickets(team.getWickets() + 1);
                    onStrikeTracker.setBalls(onStrikeTracker.getBalls() + 1);
                    onStrikeTracker.setPosition(0);
                    /*
                        new batsman will enter the field and the old batsman
                    old batsman
                        - onStrike = false
                        - position = 0,-1
                     new batsman
                        - onString = true
                        - position = 1

                     if number of wickets >= (number of players - 1) then the innings ends
                     */
                    onFieldIndex++;
                    try {
                        onStrikeTracker = team.getPlayer().get(onFieldIndex);
                    } catch (IndexOutOfBoundsException ae) {
                        printScoreBoard(team, i + 1);
                    }
                    if (b1.isOnStrike()) {
                        b1.setOnStrike(false);
                        b1 = onStrikeTracker;
                        b1.setOnStrike(true);
                        b1.setPosition(1);
                    } else {
                        b2.setOnStrike(false);
                        b2 = onStrikeTracker;
                        b2.setOnStrike(true);
                        b2.setPosition(1);
                    }
                    ballCount++;
                } else {
                    ballCount++;
                    int run = Integer.parseInt(ball);
                    team.setScore(team.getScore() + run);
                    if (run % 2 != 0) {
                        onStrikeTracker.setBalls(onStrikeTracker
                                .getBalls() + 1);
                        onStrikeTracker.setRuns(onStrikeTracker
                                .getRuns() + run);
                        onStrikeTracker = swapBatsman(b1, b2);
                    } else {
                        onStrikeTracker.setBalls(onStrikeTracker
                                .getBalls() + 1);
                        onStrikeTracker.setRuns(onStrikeTracker
                                .getRuns() + run);
                        if (run == 4) {
                            onStrikeTracker.setFours(onStrikeTracker
                                    .getFours() + 1);
                        } else if (run == 6) {
                            onStrikeTracker.setSixes(onStrikeTracker
                                    .getSixes() + 1);
                        }
                    }
                }
            }
            if (team.getWickets() >= (numberOfPlayers - 1)) {
                break;
            }
            printScoreBoard(team, i + 1);
            onStrikeTracker = swapBatsman(b1, b2);
        }
    }

    private void setOvers(Team team, float ballCount) {
        if(ballCount==5){
            team.setOvers((int)(team.getOvers()+1));
            return;
        }
        team.setOvers(Math.round(team.getOvers())+((ballCount+1)/10));
    }

    /*
        This method takes care or the rotation of on strike batsman after
        1s, 3s, over change
     */
    private static Player swapBatsman(Player b1, Player b2) {
        if (b1.isOnStrike()) {
            b1.setOnStrike(false);
            b2.setOnStrike(true);
            return b2;
        }
        b2.setOnStrike(false);
        b1.setOnStrike(true);
        return b1;
    }

    private static void printScoreBoard(Team team, int overNumber) {
        System.out.println("Scorecard for over " + overNumber + " for " + team.getTeamName());
        System.out.println("PlayerName  Score  4s  6s  Balls");
        for (Player player : team.getPlayer()) {
            String name = player.getName();
            if (player.getPosition() == 1)
                name = name + "*";
            System.out.println(name + "\t" + player.getRuns() + "\t" +
                    player.getFours() + "\t" + player.getSixes() + "\t" + player.getBalls());
        }
        System.out.println("Total: " + team.getScore() + "/" + team.getWickets());
        System.out.println("Overs: " + team.getOvers());

    }
}
