package uk.ac.sheffield.com1003.assignment2425;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.*;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.AbstractGymDashboardPanel;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.GymDashboard;
import uk.ac.sheffield.com1003.assignment2425.gui.GymDashboardPanel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// This is the main class used to run the assignment's GUI.
// TODO where indicated, REPLACE default implementations by your own implementations
// TODO where indicated, KEEP default implementations as it will be used by JUnit tests during assessment
// TODO you WILL NEED to add new imports
// TODO you WILL NEED to add new methods and variables and constants
// TODO you WILL NEED to add new classes
// TODO remove the comments and tips provided with this template
// TODO add your own comments
// TODO document the class and methods with JavaDoc

public class GymDashboardApp {
    private final AbstractEntryCatalog entryCatalog;
    private final List<Query> builtQueriesList;

    // do not change the constructor
    public GymDashboardApp(String entriesFileName, String queryFileName) {
        AbstractEntryCatalog abstractEntryCatalog = null;
        List<Query> builtQueriesList = null;
        try {
            abstractEntryCatalog = new EntryCatalog(entriesFileName);
            List<String> queryTokensFromFile = AbstractQueryParser.readQueryTokensFromFile(queryFileName);
            List<String> queryTokens = new ArrayList<>(queryTokensFromFile);
            try {
                QueryParser queryParser = new QueryParser();
                List<Query> queriesList = queryParser.buildQueries(queryTokens);
                builtQueriesList = new ArrayList<>(queriesList);
            } catch (IllegalArgumentException e) { // captures case of malformed queries in query file
                System.err.println(e);
                builtQueriesList = new ArrayList<>(); // this allows the program to resume, just skipping queries
            }
        } catch (IllegalArgumentException | IOException e) {
            System.err.println(e);
            System.exit(-1);
        }
        this.entryCatalog = abstractEntryCatalog;
        this.builtQueriesList = builtQueriesList;

    }

    // do not change this main method
    public static void main(String[] args) {
        if (args.length == 0) {
            args = new String[]{
                    "./src/main/resources/gym.csv",
                    "./src/main/resources/queries.txt"
            };
        }
        GymDashboardApp gymDashboardApp = new GymDashboardApp(args[0], args[1]);
        gymDashboardApp.startCLI();
        gymDashboardApp.startGUI();
    }

    // do not change this method
    public void startCLI() {
        // Basic catalogue information
        printQuestionAnswers();

        // Queries
        executeQueries();
    }

    // do not change this method
    private void executeQueries() {
        System.out.println("\n======================================");
        System.out.println("Executing queries...");
        printNumberQueries();

        int i = 1;
        for (Query query : builtQueriesList) {
            System.out.println("---> (" + i +") " + query.toString() + ":");
            List<Entry> queryResults = query.executeQuery(entryCatalog);
            System.out.println("-> Printing 5 out of " + queryResults.size() + " matching entries...\n");
            printFirstFiveEntriesInList(queryResults);
            System.out.println();
            i++;
        }
        System.out.println("\n======================================");
    }

    // do not change this method
    private void printNumberQueries() {
        System.out.println("In total, " + builtQueriesList.size() + " queries were found.");
        System.out.println();
    }

    // do not change this method
    private void printQuestionAnswers() {
        System.out.println("\n======================================");
        System.out.println("Printing question answers...\n");
        printTotalNumberOfUniqueAges();
        printAverageBmiForTallMembers();
        printAverageFatPercentage();
        printAverageBmiForAdvancedLevelMembers();
        printMembersCountWaterIntakeAbove3Liters();
        printPercentageAboveHealthyBmi();
        printNumberOfMembersWithYogaWorkoutType();
        System.out.println("\n======================================");

        printFirstFiveEntriesInCatalog();
    }

    // TODO edit this method, keeping the lines indicated
    private void printTotalNumberOfUniqueAges() {
        // Tip: you might find handy the method getEntryProperty in the class Entry
        // implementation goes here
        int numberOfUniqueAges = 0;

        // keep the printed message exactly as it is
        System.out.println("The total number of unique ages in the dataset is : " + numberOfUniqueAges);
    }

    // TODO edit this method, keeping the lines indicated
    private void printAverageBmiForTallMembers() {
        // Tip: you might find handy the method getEntryProperty in the class Entry
        // implementation goes here
        double averageBmiForTallMembers = -1;    // if no individuals meet the criteria return -1

        // keep the printed message exactly as it is
        // Return the average BMI, or -1 if no individuals meet the criteria
        System.out.println("The average BMI of members with a height greater than 1.8 meters is : "
                + Double.parseDouble(String.format("%.2f",averageBmiForTallMembers)));
    }


    // TODO edit this method, keeping the lines indicated
    private void printAverageFatPercentage() {
        // Tip: you might find handy the method getEntryProperty in the class Entry
        // implementation goes here
        double averageFatPercentage = -1;   // if no individuals meet the criteria return -1

        // keep the printed message exactly as it is
        // Return the average body fat percentage, or -1 if no members meet the criteria
        System.out.println("The average fat percentage for members who workout more than 4 days a week is : "
                + Double.parseDouble(String.format("%.2f", averageFatPercentage)));
    }

    // TODO edit this method, keeping the lines indicated
    private void printAverageBmiForAdvancedLevelMembers() {
        // Tip: you might find handy the method getEntryProperty in the class Entry
        // implementation goes here
        double averageBmiForAdvancedLevelMembers = -1;   // if no individuals meet the criteria return -1


        // keep the printed message exactly as it is
        // Return the average BMI, or -1 if no members meet the criteria
        System.out.println("The average BMI of members who have an Advanced level of experience is : "
                + Double.parseDouble(String.format("%.2f", averageBmiForAdvancedLevelMembers)));
    }

    // TODO edit this method, keeping the lines indicated
    private void printMembersCountWaterIntakeAbove3Liters() {
        // Tip: you might find handy the method getEntryProperty in the class Entry
        // implementation goes here
        int count = 0;

        // keep the printed message exactly as it is
        // Return the count of members with water intake above 3 liters
        System.out.println("The number of members with a water intake above 3 litres is : " + count);
    }

    // TODO edit this method, keeping the lines indicated
    private void printPercentageAboveHealthyBmi() {
        // Tip: you might find handy the method getEntryProperty in the class Entry
        // implementation goes here
        double percentageAboveHealthyBmi = -1;  // if no individuals meet the criteria return -1

        // keep the printed message exactly as it is
        // Return the percentage of members with BMI above 25
        System.out.println("The percentage of members with BMI above 25 is : " + percentageAboveHealthyBmi);
    }

    // TODO edit this method, keeping the lines indicated
    private void printNumberOfMembersWithYogaWorkoutType() {
        // Tip: you might find handy implementing the method getEntriesListByEntryDetail method in EntryCatalog
        // implementation goes here
        int count = 0;

        // keep the printed message exactly as it is
        // Return the number of members in the dataset with a Yoga workout
        System.out.println("The number of members with Yoga workout type is : " + count);
    }

    // do not change this method
    private void printFirstFiveEntriesInCatalog() {
        System.out.println("\n======================================");
        System.out.println("Printing first five gym entries in catalog...\n");
        printFirstFiveEntriesInList(entryCatalog.getFirstFiveEntries());
        System.out.println("\n======================================");
    }

    // do not change this method
    private void printFirstFiveEntriesInList(List<Entry> entriesList) {
        int count = 1;
        for (Entry e : entriesList){
            System.out.println(e);
            if (++count > 5)
                break;
        }
    }

    // do not change this method
    public void startGUI() {
        // Start GUI
        AbstractGymDashboardPanel gymDashboardPanel = new GymDashboardPanel(entryCatalog);
        GymDashboard eDashboard = new GymDashboard(gymDashboardPanel);
        eDashboard.setVisible(true);
    }
}