import java.util.ArrayList;
import java.util.Scanner;

class Teams{
    String teamName;
    int teamScore;
    ArrayList<Racer> members;

    Teams(String team_name) {
        this.teamName = team_name;
        this.teamScore = 0;
        this.members = new ArrayList<>();
    }

    public void addRacer(Racer racer) {
        members.add(racer);
    }

    public void addTeamPoints(int points) {
        teamScore += points;
    }

    public void displayTeam(){
        System.out.println("\nTeam: " + teamName);
        System.out.println("Total Score: " + teamScore);
        System.out.println("Members:");
        for (Racer r : members) {
            System.out.println(" - " + r.name + " (" + r.score + ")");
        }
    }

}

class Racer {
    String name;
    int score;
    Teams team;

    Racer(String name) {
        this.name = name;
        this.score = 0;
    }

    public void assignTeam(Teams team) {
        this.team = team;
        team.addRacer(this);
    }

    public void addPoints(int points) {
        score += points;

        if (team != null) {
            team.addTeamPoints(points);
        }
    }

    public Racer createRacer(ArrayList<Racer> racerList) {
        Scanner scnr = new Scanner(System.in);

        System.out.print("Enter racer name: ");
        String name = scnr.nextLine();

        Racer newRacer = new Racer(name);
        racerList.add(newRacer);

        System.out.println("Racer created successfully.");
        return newRacer;
    }

    public static void assignRacerToTeam(ArrayList<Racer> racers, ArrayList<Teams> teams) {
        Scanner scanner = new Scanner(System.in);

        if (racers.isEmpty()) {
            System.out.println("No racers available.");
            return;
        }

        System.out.println("Select a Racer:");
        for (int i = 0; i < racers.size(); i++) {
            System.out.println((i + 1) + ". " + racers.get(i).name);
        }
        int racerChoice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Select a Team:");
        for (int i = 0; i < teams.size(); i++) {
            System.out.println((i + 1) + ". " + teams.get(i).teamName);
        }
        int teamChoice = scanner.nextInt();
        scanner.nextLine();

        Racer selectedRacer = racers.get(racerChoice - 1);
        Teams selectedTeam = teams.get(teamChoice - 1);

        selectedRacer.assignTeam(selectedTeam);

        System.out.println("Racer assigned successfully.");
    }

    public static void displayAllTeams(ArrayList<Teams> teams) {
        for (Teams t : teams) {
            t.displayTeam();
        }

    }

static class Race {
    private static int getPointsForPlace(int place) {
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

    public static void runRace(ArrayList<Racer> racers) {
        Scanner scnr = new Scanner(System.in);

        if (racers.isEmpty()) {
            System.out.println("No racers available.");
            return;
        }

        System.out.println("\nEnter placements for this race:");

        for (Racer r : racers) {
            System.out.println("Placement for " + r.name + ": ");
            int place = scnr.nextInt();

            int points = getPointsForPlace(place);
            r.addPoints(points);
        }

        System.out.println("Race completed. Points assigned.");
    }

}


public class F1ProjectWork {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        ArrayList<Racer> racers = new ArrayList<>();
        ArrayList<Teams> teams = new ArrayList<>();

        boolean running = true;

        while (running) {
            System.out.println("\n===== F1 MENU =====");
            System.out.println("1. Create Racer");
            System.out.println("2. Create Team");
            System.out.println("3. Assign Racer to Team");
            System.out.println("4. Run Race");
            System.out.println("5. Display All Teams");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scnr.nextInt();
            scnr.nextLine();

            switch (choice) {
                case 1:
                    Racer temp = new Racer("");
                    temp.createRacer(racers);
                    break;

                case 2:
                    System.out.print("Enter team name: ");
                    String teamName = scnr.nextLine();
                    Teams newTeam = new Teams(teamName);
                    teams.add(newTeam);
                    System.out.println("Team created successfully.");
                    break;

                case 3:
                    Racer.assignRacerToTeam(racers, teams);
                    break;

                case 4:
                    Race.runRace(racers);
                    break;

                case 5:
                    Racer.displayAllTeams(teams);
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting program");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scnr.close();
    }
}
}