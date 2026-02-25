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
    static int score;
    Teams team;

    Racer(String name, int score) {
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

    public static Racer createRacer(ArrayList<Racer> racerList) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter racer name: ");
        String name = scanner.nextLine();

        Racer newRacer = new Racer(name, score);
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

class Race {
    private static int getPointsForPlace(int place) {
        switch (place) {
            case 1:
                return 25;
            case 2:
                return 18;
            case 3:
                return 15;
            case 4:
                return 12;
            case 5:
                return 10;
            case 6:
                return 8;
            case 7:
                return 6;
            case 8:
                return 4;
            case 9:
                return 2;
            case 10:
                return 1;
            default:
                return 0;
            }
        }

    public static void runRace(ArrayList<Racer> racers) {
        Scanner scanner = new Scanner(System.in);

        if (racers.isEmpty()) {
            System.out.println("No racers available.");
            return;
        }

        System.out.println("\nEnter placements for this race:");

        for (Racer r : racers) {
            System.out.print("Placement for " + r.name + ": ");
            int place = scanner.nextInt();

            int points = getPointsForPlace(place);
            r.addPoints(points);
        }

        System.out.println("Race completed. Points assigned.");
    }

}


public class F1ProjectWork {
    public static void main(String[] args) {


    }
}
}
