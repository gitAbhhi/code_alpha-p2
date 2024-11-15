
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Activity {

    private String name;
    private String date;
    private double cost;

    public Activity(String name, String date, double cost) {
        this.name = name;
        this.date = date;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Activity: " + name + ", Date: " + date + ", Cost: $" + cost;
    }
}

class Destination {

    private String name;
    private String arrivalDate;
    private String departureDate;
    private List<Activity> activities;

    public Destination(String name, String arrivalDate, String departureDate) {
        this.name = name;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public String getName() {
        return name;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public double getTotalCost() {
        return activities.stream().mapToDouble(Activity::getCost).sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Destination: ").append(name)
                .append(", Arrival: ").append(arrivalDate)
                .append(", Departure: ").append(departureDate)
                .append("\nActivities:\n");
        for (Activity activity : activities) {
            sb.append("  ").append(activity.toString()).append("\n");
        }
        sb.append("Total Cost for ").append(name).append(": $").append(getTotalCost()).append("\n");
        return sb.toString();
    }
}

class Itinerary {

    private List<Destination> destinations;

    public Itinerary() {
        this.destinations = new ArrayList<>();
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    public double getTotalCost() {
        return destinations.stream().mapToDouble(Destination::getTotalCost).sum();
    }

    public void showItinerary() {
        System.out.println("Your Travel Itinerary:");
        for (Destination destination : destinations) {
            System.out.println(destination);
        }
        System.out.println("Total Trip Cost: $" + getTotalCost());
    }
}

public class ItineraryPlanner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Itinerary itinerary = new Itinerary();

        System.out.println("Welcome to the Travel Itinerary Planner!");

        while (true) {
            System.out.println("Enter the destination name (or type 'done' to finish):");
            String destinationName = scanner.nextLine();
            if (destinationName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.println("Enter arrival date (YYYY-MM-DD):");
            String arrivalDate = scanner.nextLine();

            System.out.println("Enter departure date (YYYY-MM-DD):");
            String departureDate = scanner.nextLine();

            Destination destination = new Destination(destinationName, arrivalDate, departureDate);

            while (true) {
                System.out.println("Add an activity at " + destinationName + " (or type 'done' to finish):");
                String activityName = scanner.nextLine();
                if (activityName.equalsIgnoreCase("done")) {
                    break;
                }

                System.out.println("Enter activity date (YYYY-MM-DD):");
                String activityDate = scanner.nextLine();

                System.out.println("Enter activity cost:");
                double activityCost = scanner.nextDouble();
                scanner.nextLine();  // Consume the newline

                Activity activity = new Activity(activityName, activityDate, activityCost);
                destination.addActivity(activity);
            }

            itinerary.addDestination(destination);
        }

        System.out.println("\nHere is your planned itinerary:");
        itinerary.showItinerary();

        scanner.close();
    }
}
