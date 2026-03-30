package mypack;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class F1_Timing_Program {

    static Scanner scnr = new Scanner(System.in);
    static ArrayList<String> drivername = new ArrayList<String>();
    static ArrayList<Double> drivertimes = new ArrayList<Double>();

    public static void main(String[] args) {

        while (true) {
            System.out.println("--------------F1 Timing Menu-------------");
            System.out.println("1. Racer Menu (new racer, edit racer, remove racer):\n"
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

                case 3:
                    System.out.println("Viewing current standings:");
                    if (drivername.isEmpty()) {
                        System.out.println("No racers in database.");
                    } else {
                        for (int i = 0; i < drivername.size(); i++) {
                            System.out.println((i + 1)+ ". " + drivername.get(i) + " - Total Time: " + drivertimes.get(i));
                        }
                    }
                    break;

                case 4:
                    System.out.println("Finishing race and exporting to file: ");
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

                case 5:
                    System.out.println("Exiting program");
                    return;
            }
        }
    }
}