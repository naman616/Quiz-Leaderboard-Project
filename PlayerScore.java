public class PlayerScore {
    String playerName;
    int score;

    /**
     * Constructor to initialize the player's score.
     */
    PlayerScore(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    /**
     * Returns a string for saving to the file.
     */
    public String toString() {
        return this.playerName + "," + this.score;
    }
}