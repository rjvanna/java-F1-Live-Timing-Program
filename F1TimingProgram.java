package mypack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class F1TimingProgram extends JFrame implements ActionListener {

    static ArrayList<String> drivername = new ArrayList<>();
    static ArrayList<Double> drivertimes = new ArrayList<>();
    static ArrayList<Integer> driverscores = new ArrayList<>();

    static ArrayList<String> teamNames = new ArrayList<>();
    static ArrayList<Integer> teamScores = new ArrayList<>();
    static ArrayList<ArrayList<String>> teamMembers = new ArrayList<>();

    private JTextArea outputArea;
    private JTextField nameField;
    private JTextField newNameField;
    private JTextField timeField;
    private JTextField teamField;
    private JButton addButton, editButton, removeButton;
    private JButton lapButton, viewButton, finishButton;
    private JButton createTeamButton, addToTeamButton, viewTeamsButton;
    private JButton removeTeamButton;
    private JButton finishSeasonButton;

    public F1TimingProgram() {

        setTitle("F1 Racing Timing");
        setSize(700, 550);
        setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Driver Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("New Name (Edit):"));
        newNameField = new JTextField();
        inputPanel.add(newNameField);

        inputPanel.add(new JLabel("Lap Time:"));
        timeField = new JTextField();
        inputPanel.add(timeField);

        inputPanel.add(new JLabel("Team Name:"));
        teamField = new JTextField();
        inputPanel.add(teamField);

        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 3));

        addButton = new JButton("Add Racer");
        editButton = new JButton("Edit Racer");
        removeButton = new JButton("Remove Racer");
        removeTeamButton = new JButton("Remove Team");
        lapButton = new JButton("Record Lap");
        viewButton = new JButton("View Standings");
        finishButton = new JButton("Finish Race");
        createTeamButton = new JButton("Create Team");
        addToTeamButton = new JButton("Add Driver to Team");
        viewTeamsButton = new JButton("View Teams");
        finishSeasonButton = new JButton("Finish Season");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(removeTeamButton);
        buttonPanel.add(lapButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(finishButton);
        buttonPanel.add(createTeamButton);
        buttonPanel.add(addToTeamButton);
        buttonPanel.add(viewTeamsButton);
        buttonPanel.add(finishSeasonButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this);
        editButton.addActionListener(this);
        removeButton.addActionListener(this);
        removeTeamButton.addActionListener(this);
        lapButton.addActionListener(this);
        viewButton.addActionListener(this);
        finishButton.addActionListener(this);
        createTeamButton.addActionListener(this);
        addToTeamButton.addActionListener(this);
        viewTeamsButton.addActionListener(this);
        finishSeasonButton.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();
        String newName = newNameField.getText();
        String timeText = timeField.getText();
        String teamName = teamField.getText();

        try {
            if (e.getSource() == addButton) {
                if (drivername.contains(name)) {
                    updateDisplay("Racer already exists.");
                } else {
                    drivername.add(name);
                    drivertimes.add(0.0);
                    driverscores.add(0);
                    updateDisplay("Added racer: " + name);
                }
            }

            else if (e.getSource() == editButton) {
                int idx = drivername.indexOf(name);
                if (idx != -1) {
                    drivername.set(idx, newName);
                    updateDisplay("Racer updated.");
                } else {
                    updateDisplay("Racer not found.");
                }
            }

            else if (e.getSource() == removeButton) {
                int idx = drivername.indexOf(name);
                if (idx != -1) {
                    drivername.remove(idx);
                    drivertimes.remove(idx);
                    driverscores.remove(idx);

                    for (ArrayList<String> members : teamMembers) {
                        members.remove(name);
                    }

                    updateDisplay("Racer removed.");
                } else {
                    updateDisplay("Racer not found.");
                }
            }

            else if (e.getSource() == removeTeamButton) {
                int teamIdx = teamNames.indexOf(teamName);

                if (teamIdx != -1) {
                    teamNames.remove(teamIdx);
                    teamScores.remove(teamIdx);
                    teamMembers.remove(teamIdx);

                    updateDisplay("Team removed: " + teamName);
                } else {
                    updateDisplay("Team not found.");
                }
            }

            else if (e.getSource() == lapButton) {
                double time = Double.parseDouble(timeText);
                int idx = drivername.indexOf(name);

                if (idx != -1) {
                    drivertimes.set(idx, drivertimes.get(idx) + time);
                    updateDisplay("Lap recorded.");
                } else {
                    updateDisplay("Racer not found.");
                }
            }

            else if (e.getSource() == viewButton) {
                updateDisplay("Leaderboard:");
            }

            else if (e.getSource() == createTeamButton) {
                if (teamNames.contains(teamName)) {
                    updateDisplay("Team already exists.");
                } else {
                    teamNames.add(teamName);
                    teamScores.add(0);
                    teamMembers.add(new ArrayList<>());
                    updateDisplay("Team created: " + teamName);
                }
            }

            else if (e.getSource() == addToTeamButton) {
                int teamIdx = teamNames.indexOf(teamName);

                if (teamIdx != -1 && drivername.contains(name)) {

                    if (teamMembers.get(teamIdx).contains(name)) {
                        updateDisplay("Driver already in team.");
                    } else {
                        teamMembers.get(teamIdx).add(name);
                        updateDisplay("Driver added to team.");
                    }

                } else {
                    updateDisplay("Team or driver not found.");
                }
            }

            else if (e.getSource() == viewTeamsButton) {
                displayTeams();
            }

            else if (e.getSource() == finishButton) {
                finishRace();
                updateDisplay("Race finished & exported.");
            }

            else if (e.getSource() == finishSeasonButton) {
                if (drivername.isEmpty() || teamNames.isEmpty()) {
                    outputArea.setText("No racers or teams available.");
                    return;
                }

                int topDriverIdx = 0;
                for (int i = 1; i < driverscores.size(); i++) {
                    if (driverscores.get(i) > driverscores.get(topDriverIdx)) {
                        topDriverIdx = i;
                    }
                }

                String topDriver = drivername.get(topDriverIdx);
                int topDriverScore = driverscores.get(topDriverIdx);

                int topTeamIdx = 0;
                for (int i = 1; i < teamScores.size(); i++) {
                    if (teamScores.get(i) > teamScores.get(topTeamIdx)) {
                        topTeamIdx = i;
                    }
                }

                String topTeam = teamNames.get(topTeamIdx);
                int topTeamScore = teamScores.get(topTeamIdx);

                StringBuilder sb = new StringBuilder();
                sb.append("=== SEASON RESULTS ===\n\n");
                sb.append("Top Driver: ").append(topDriver)
                        .append(" (").append(topDriverScore).append(" pts)\n");

                sb.append("Top Team: ").append(topTeam)
                        .append(" (").append(topTeamScore).append(" pts)\n\n");

                sb.append("Season reset.\n");

                outputArea.setText(sb.toString());

                for (int i = 0; i < driverscores.size(); i++) {
                    driverscores.set(i, 0);
                }

                for (int i = 0; i < teamScores.size(); i++) {
                    teamScores.set(i, 0);
                }
            }

        } catch (Exception ex) {
            updateDisplay("Invalid input.");
        }
    }

    private void updateDisplay(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(message).append("\n\n");

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < drivername.size(); i++) {
            indices.add(i);
        }

        indices.sort((a, b) -> Double.compare(drivertimes.get(a), drivertimes.get(b)));

        for (int i = 0; i < indices.size(); i++) {
            int idx = indices.get(i);

            sb.append((i + 1)).append(". ")
                    .append(drivername.get(idx))
                    .append(": ")
                    .append(drivertimes.get(idx))
                    .append(" Total Points: ")
                    .append(driverscores.get(idx))
                    .append("\n");
        }

        outputArea.setText(sb.toString());
    }

    private void displayTeams() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < teamNames.size(); i++) {
            sb.append("Team: ").append(teamNames.get(i)).append("\n");
            sb.append("Score: ").append(teamScores.get(i)).append("\n");

            for (String member : teamMembers.get(i)) {
                int idx = drivername.indexOf(member);
                double time = (idx != -1) ? drivertimes.get(idx) : 0;

                sb.append(" - ").append(member)
                        .append(" (Time: ").append(time).append(")\n");
            }

            sb.append("\n");
        }

        outputArea.setText(sb.toString());
    }

    private void finishRace() {

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < drivername.size(); i++) {
            indices.add(i);
        }

        indices.sort((a, b) -> Double.compare(drivertimes.get(a), drivertimes.get(b)));

        for (int i = 0; i < indices.size(); i++) {
            int place = i + 1;
            int points = getPoints(place);
            int idx = indices.get(i);
            driverscores.set(idx, driverscores.get(idx) + points);
        }

        for (int i = 0; i < teamNames.size(); i++) {
            int total = 0;
            for (String member : teamMembers.get(i)) {
                int idx = drivername.indexOf(member);
                if (idx != -1) {
                    total += driverscores.get(idx);
                }
            }
            teamScores.set(i, total);
        }

        try {
            FileWriter writer = new FileWriter("RaceResults.txt");
            for (int i = 0; i < drivername.size(); i++) {
                writer.write(drivername.get(i) + " - " + drivertimes.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            outputArea.setText("Error saving file.");
        }

        for (int i = 0; i < drivername.size(); i++){
            drivertimes.set(i, 0.0);
        }
    }

    public static int getPoints(int place) {
        return switch (place) {
            case 1 -> 25;
            case 2 -> 18;
            case 3 -> 15;
            case 4 -> 12;
            case 5 -> 10;
            case 6 -> 8;
            case 7 -> 6;
            case 8 -> 4;
            case 9 -> 2;
            case 10 -> 1;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        new F1TimingProgram();
    }
}