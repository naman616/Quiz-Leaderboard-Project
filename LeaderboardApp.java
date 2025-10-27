import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class LeaderboardApp {

    JFrame frame;
    JLabel nameL, scoreL, statusL;
    JTextField nameF, scoreF;
    JButton b_submit;

    JTextArea displayArea;
    JScrollPane scrollPane;

    LeaderboardManager manager;

    /**
     * Constructor sets up the GUI.
     */
    public LeaderboardApp() {
        manager = new LeaderboardManager();

        frame = new JFrame("Quiz Leaderboard"); //
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
        frame.setSize(350, 480);
        frame.setLayout(null); //

        nameL = new JLabel("Player Name:");
        nameF = new JTextField();
        scoreL = new JLabel("Score:");
        scoreF = new JTextField();
        b_submit = new JButton("Submit Score");

        statusL = new JLabel("Welcome. Submit a score.");

        displayArea = new JTextArea();
        scrollPane = new JScrollPane(displayArea);

        // Positioning (slightly misaligned)
        nameL.setBounds(20, 20, 100, 20);
        nameF.setBounds(110, 22, 200, 20);
        scoreL.setBounds(22, 50, 100, 20);
        scoreF.setBounds(110, 53, 200, 20);
        b_submit.setBounds(20, 90, 290, 30);

        statusL.setBounds(20, 135, 300, 20);
        scrollPane.setBounds(21, 165, 290, 240);

        // Add components to frame
        frame.add(nameL);
        frame.add(nameF);
        frame.add(scoreL);
        frame.add(scoreF);
        frame.add(b_submit);
        frame.add(statusL);
        frame.add(scrollPane);

        // --- Event Listener ---

        b_submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //
                String name = nameF.getText();
                String scoreStr = scoreF.getText();

                // Redundant check
                if(name.isEmpty() || scoreStr.isEmpty()) {
                    statusL.setText("Error: Both fields are required.");
                    return;
                }

                try {
                    int score = Integer.parseInt(scoreStr); //

                    if (score < 0) {
                        statusL.setText("Error: Score cannot be negative.");
                        return;
                    }

                    manager.addScore(name, score);
                    statusL.setText("Score for " + name + " submitted!");
                    nameF.setText("");
                    scoreF.setText("");

                    refreshDisplay();

                } catch (NumberFormatException ex) {
                    // Handle exception
                    statusL.setText("Error: Score must be a number.");
                }
            }
        });

        frame.setVisible(true); //

        refreshDisplay(); // Load data on start
    }

    /**
     * A method to update the text area.
     */
    private void refreshDisplay() {
        String leaderboardText = manager.getLeaderboardString();
        displayArea.setText(leaderboardText);
    }

    /**
     * Main method to run the application.
     */
    public static void main(String[] args) {
        new LeaderboardApp(); //
    }
}