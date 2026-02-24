package mypack;

import java.util.ArrayList;
import java.util.Scanner;

public class F1_Timing_Program {

    static Scanner scnr = new Scanner(System.in);
    static ArrayList<String> drivername = new ArrayList<String>();
    static ArrayList<Double> drivertimes = new ArrayList<Double>();
    public static void main(String[] args) {

        while (true) {
            System.out.println("--------------F1 Timing Menu-------------");
            System.out.println("1. Racer Menu (new racer, edit racer, remove racer):\n"
                    + "2. Lap Time Menu(record lap, remove lap:\n"
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
                            }
                        case 3:
                            System.out.println("Remove racer: ");
                            String nameremove =scnr.nextLine();








                            break;








                        case 3:






                    }
                    break;

                case 2:
                    System.out.println("----------Lap Time Menu---------- ");
                    System.out.println("1. Record a lap time:\n"
                            + "2. Remove a lap time\n"
                            + "3. Back");
                    break;




                case 3:
                    System.out.println("Viewing current standings:");




                    break;
                case 4:
                    System.out.println("Finishing race and exporting the file: ");





                    break;
                case 5:
                    System.out.println("Exiting program");
                    return;
            }
        }
    }
}