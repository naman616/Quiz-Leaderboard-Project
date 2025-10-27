import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeaderboardManager {

    ArrayList<PlayerScore> scores;
    String filename = "leaderboard.txt";

    /**
     * Constructor initializes the list and loads from file.
     */
    public LeaderboardManager() {
        scores = new ArrayList<>(); //
        loadScores();
    }

    /**
     * Loads the scores from leaderboard.txt.
     */
    private void loadScores() {
        File f = new File(filename); //
        if (!f.exists()) { //
            return;
        }

        try {
            FileReader fr = new FileReader(f); //
            BufferedReader br = new BufferedReader(fr); //
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    try {
                        int score = Integer.parseInt(parts[1]); //
                        scores.add(new PlayerScore(parts[0], score));
                    } catch (NumberFormatException e) {
                        // skip bad lines
                    }
                }
            }
            br.close();
            fr.close(); //
        } catch (IOException e) {
            System.out.println("Could not read file");
        }
    }

    /**
     * Saves the entire score list back to leaderboard.txt.
     */
    private void saveScores() {
        try {
            FileWriter fw = new FileWriter(filename); //

            for (PlayerScore ps : scores) {
                fw.write(ps.toString() + "\n"); //
            }
            fw.close(); //
        } catch (IOException e) {
            System.out.println("Could not save file");
        }
    }

    /**
     * Adds a new score to the list.
     */
    public void addScore(String name, int score) {
        PlayerScore newScore = new PlayerScore(name, score);
        scores.add(newScore);
        saveScores();
    }

    /**
     * Returns a string representation of the leaderboard, sorted.
     */
    public String getLeaderboardString() {

        // Redundant "amateur" sort: sort the list every time it's displayed
        // Using a bubble sort, built from basic for/if
        for (int i = 0; i < scores.size() - 1; i++) {
            for (int j = 0; j < scores.size() - i - 1; j++) {
                // Sort high-to-low
                if (scores.get(j).score < scores.get(j + 1).score) {
                    // Swap
                    PlayerScore temp = scores.get(j);
                    scores.set(j, scores.get(j + 1));
                    scores.set(j + 1, temp);
                }
            }
        }

        // Now build the string
        StringBuffer sb = new StringBuffer("--- LEADERBOARD ---\n"); //

        int rank = 1;
        for (PlayerScore ps : scores) {
            sb.append(rank + ". " + ps.playerName + " - " + ps.score + "\n");
            rank++;
        }
        return sb.toString(); //
    }
}