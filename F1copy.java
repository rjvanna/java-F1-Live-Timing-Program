package mypack;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class F1_Timing_Program {

    static Scanner scnr = new Scanner(System.in);
    static ArrayList<String> drivername = new ArrayList<String>();
    static ArrayList<Double> drivertimes = new ArrayList<Double>();
    static ArrayList<Integer> driverscores = new ArrayList<Integer>();
    static ArrayList<String> teamNames = new ArrayList<String>();
    static ArrayList<Integer> teamScores = new ArrayList<Integer>();
    static ArrayList<ArrayList<String>> teamMembers = new ArrayList<>();

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

        while (true) {
            System.out.println("--------------F1 Timing Menu-------------");
            System.out.println("1. Racer Menu (new racer, edit racer, remove racer):\n"
                    + "2. Team Menu:\n"
                    + "2. Lap Time Menu(record lap, remove lap):\n"
                    + "3. View current standings:\n"
                    + "4. Finish race & Export:\n"
                    + "5. Exit");
            int choice = scnr.nextInt();
            scnr.nextLine();

            switch(choice){
                case 1:
                    System.out.println("----------Racer Menu---------");
                    System.out.println("1. New racer\n"
                            + "2. Edit racer\n"
                            + "3. Remove racer\n"
                            + "4. Back");
                    int choice2 = scnr.nextInt();
                    scnr.nextLine();

                    switch(choice2) {
                        case 1:
                            System.out.println("Adding new racer. Enter racers name:");
                            String name=scnr.nextLine();
                            drivername.add(name);
                            drivertimes.add(0.0);
                            driverscores.add(0);

                            System.out.println(name +" has been added to the database.");
                            break;

                        case 2:
                            System.out.println("Edit racers");
                            if (drivername.isEmpty()) {
                                System.out.println("No racers found.");
                                break;
                            } else {
                                System.out.println("Enter the name of the racer to edit:");
                                String oldName = scnr.nextLine();
                                int index = drivername.indexOf(oldName);

                                if (index != -1) {
                                    System.out.println("Enter the new name:");
                                    String newName = scnr.nextLine();
                                    drivername.set(index, newName);
                                    System.out.println("Racer name updated.");
                                } else {
                                    System.out.println("Racer not found.");
                                }
                            }
                            break;

                        case 3:
                            System.out.println("Remove racer: ");
                            String nameremove = scnr.nextLine();

                            if (nameremove.isEmpty()) {
                                System.out.println("No racers found.");
                                break;
                            } else {
                                int index2 = drivername.indexOf(nameremove);

                                if (index2 != -1) {
                                    drivername.remove(index2);
                                    drivertimes.remove(index2);
                                    driverscores.remove(index2);
                                    System.out.println("Racer removed.");
                                } else {
                                    System.out.println("Racer not found.");
                                }
                            }
                            break;

                        case 4:
                            System.out.println("Going Back: ");
                            break;
                    }
                    break;

                case 2:
                    System.out.println("----------Team Menu----------");
                    System.out.println("1. Create Team\n"
                            + "2. Add Racer to Team\n"
                            + "3. Add Team Points\n"
                            + "4. View Teams\n"
                            + "5. Back");

                    int teamChoice = scnr.nextInt();
                    scnr.nextLine();

                    switch(teamChoice) {

                        case 1:
                            System.out.println("Enter team name:");
                            String tName = scnr.nextLine();

                            teamNames.add(tName);
                            teamScores.add(0);
                            teamMembers.add(new ArrayList<>());

                            System.out.println("Team created.");
                            break;

                        case 2:
                            System.out.println("Enter team name:");
                            String teamToAdd = scnr.nextLine();

                            int teamIndex = teamNames.indexOf(teamToAdd);

                            if (teamIndex != -1) {
                                System.out.println("Enter racer name:");
                                String racer = scnr.nextLine();

                                if (drivername.contains(racer)) {
                                    teamMembers.get(teamIndex).add(racer);
                                    System.out.println("Racer added to team.");
                                } else {
                                    System.out.println("Racer not found.");
                                }
                            } else {
                                System.out.println("Team not found.");
                            }
                            break;

                        case 3:
                            System.out.println("Enter team name:");
                            String teamToScore = scnr.nextLine();

                            int teamIndex2 = teamNames.indexOf(teamToScore);

                            if (teamIndex2 != -1) {
                                System.out.println("Enter points to add:");
                                int points = scnr.nextInt();
                                scnr.nextLine();

                                teamScores.set(teamIndex2, teamScores.get(teamIndex2) + points);
                                System.out.println("Points added.");
                            } else {
                                System.out.println("Team not found.");
                            }
                            break;

                        case 4:
                            if (teamNames.isEmpty()) {
                                System.out.println("No teams created.");
                            } else {
                                for (int i = 0; i < teamNames.size(); i++) {
                                    System.out.println("\nTeam: " + teamNames.get(i));
                                    System.out.println("Total Score: " + teamScores.get(i));
                                    System.out.println("Members:");

                                    for (String member : teamMembers.get(i)) {
                                        int idx = drivername.indexOf(member);
                                        double time = (idx != -1) ? drivertimes.get(idx) : 0;

                                        System.out.println(" - " + member + " (Time: " + time + ")");
                                    }
                                }
                            }
                            break;

                        case 5:
                            System.out.println("Going Back...");
                            break;
                    }
                    break;

                case 3:
                    System.out.println("----------Lap Time Menu---------- ");
                    System.out.println("1. Record a lap time:\n"
                            + "2. Remove a lap time\n"
                            + "3. Back");
                    int choice3 = scnr.nextInt();
                    scnr.nextLine();

                    switch(choice3){
                        case 1:
                            System.out.println("Record a lap time: ");
                            int laptime = scnr.nextInt();
                            scnr.nextLine();

                            System.out.println("Enter the name of the racer:");
                            String racerName = scnr.nextLine();
                            int racerIndex = drivername.indexOf(racerName);

                            if (racerIndex != -1) {
                                double currentTime = drivertimes.get(racerIndex);
                                drivertimes.set(racerIndex, currentTime + laptime);
                                System.out.println("Lap time recorded.");
                            } else {
                                System.out.println("Racer not found.");
                            }
                            break;

                        case 2:
                            System.out.println("Remove a lap time: ");
                            int removelaptime = scnr.nextInt();
                            scnr.nextLine();

                            System.out.println("Enter the name of the racer:");
                            String racerName2 = scnr.nextLine();
                            int racerIndex2 = drivername.indexOf(racerName2);

                            if (racerIndex2 != -1) {
                                double currentTime2 = drivertimes.get(racerIndex2);
                                drivertimes.set(racerIndex2, currentTime2 - removelaptime);
                                System.out.println("Lap time removed.");
                            } else {
                                System.out.println("Racer not found.");
                            }
                            break;

                        case 3:
                            System.out.println("Going Back: ");
                            break;
                    }
                    break;

                case 4:
                    System.out.println("Viewing current standings:");
                    if (drivername.isEmpty()) {
                        System.out.println("No racers in database.");
                    } else {
                        for (int i = 0; i < drivername.size(); i++) {
                            System.out.println((i + 1)+ ". " + drivername.get(i) + " - Total Time: " + drivertimes.get(i));
                        }
                    }
                    break;

                case 5:
                    System.out.println("Finishing race and exporting to file: ");

                    ArrayList<Integer> indices = new ArrayList<>();
                    for (int i = 0; i < drivername.size(); i++) {
                        indices.add(i);
                    }

                    indices.sort((a, b) -> Double.compare(drivertimes.get(a), drivertimes.get(b)));
                    for (int i = 0; i < indices.size(); i++) {
                        int driverIndex = indices.get(i);
                        int place = i + 1;
                        int points = getPoints(place);
                        driverscores.set(driverIndex, driverscores.get(driverIndex) + points);
                    }
                    for (int i = 0; i < teamNames.size(); i++) {
                        for (String member : teamMembers.get(i)) {
                            int driverIndex = drivername.indexOf(member);
                            if (driverIndex != -1) {
                                teamScores.set(i, teamScores.get(i) + driverscores.get(driverIndex));
                            }
                        }
                    }
                    try {
                        FileWriter writer = new FileWriter("RaceResults.txt");
                        for (int i = 0; i < drivername.size(); i++) {
                            writer.write(drivername.get(i) + " - " + drivertimes.get(i) + "\n");
                        }
                        writer.close();
                        System.out.println("Results successfully exported to RaceResults.txt");
                    } catch (IOException e) {
                        System.out.println("An error occurred while saving the file.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting program");
                    return;
            }
        }
    }
}