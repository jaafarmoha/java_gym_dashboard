/**
 * GymDashboardApp.java
 * @version 1.1 16/05/2025
 * @author Jaafar Mohammed
 */

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

import uk.ac.sheffield.com1003.assignment2425.codeprovided.Entry;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.EntryProperty;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Query;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.AbstractEntryCatalog;

/**
 * Class, GymDashboardApp which is the main class
 * that runs our overall programme
 */
public class GymDashboardApp {
    private final AbstractEntryCatalog entryCatalog;
    private final List<Query> builtQueriesList;

    /**
     * Constructor,
     * @param entriesFileName Name of the file containing all the entries
     * @param queryFileName Name of the file containing all the queries
     */
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


    private void printNumberQueries() {
        System.out.println("In total, " + builtQueriesList.size() + " queries were found.");
        System.out.println();
    }


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

    /**
     * Method, prints how many unique ages are in the
     * entries list
     */
    private void printTotalNumberOfUniqueAges() {
        // Creating a hash set of ages which won't produce duplicates
        Set<Double> uniqueAges = new HashSet<>();
        for (Entry entry : entryCatalog.getEntriesList()) {
            uniqueAges.add(entry.getEntryProperty(EntryProperty.AGE));
        }

        int numberOfUniqueAges = uniqueAges.size();

        System.out.println("The total number of unique ages in the dataset is : " + numberOfUniqueAges);
    }

    /**
     * Method, prints the average BMI for members with a
     * height of over 1.8 metres
     */
    private void printAverageBmiForTallMembers() {
        List<Entry> tallMembers = new ArrayList<>();

        for (Entry entry : entryCatalog.getEntriesList()){
            if (entry.getEntryProperty(EntryProperty.HEIGHT) > 1.80) {
                tallMembers.add(entry);
            }
        }

        double averageBmiForTallMembers;

        if (!tallMembers.isEmpty()) {
            double totalBmi = 0.0;

            for (Entry entry : tallMembers) {
                totalBmi += entry.getEntryProperty(EntryProperty.BMI);
            }
            averageBmiForTallMembers = totalBmi / tallMembers.size();
        } else {
            averageBmiForTallMembers = -1;
        }

        // Return the average BMI, or -1 if no individuals meet the criteria
        System.out.println("The average BMI of members with a height greater than 1.8 meters is : "
                + Double.parseDouble(String.format("%.2f",averageBmiForTallMembers)));
    }

    /**
     * Method, prints average fat percentage of members with a
     * workout frequency of over 4 times a week
     */
    private void printAverageFatPercentage() {
        List<Entry> overFourMembers = new ArrayList<>();

        for (Entry entry : entryCatalog.getEntriesList()){
            if (entry.getEntryProperty(EntryProperty.WORKOUT_FREQUENCY) > 4) {
                overFourMembers.add(entry);
            }
        }

        double averageFatPercentage;

        if (!overFourMembers.isEmpty()) {
            double totalFatPercentage = 0.0;

            for (Entry entry : overFourMembers) {
                totalFatPercentage += entry.getEntryProperty(EntryProperty.BMI);
            }
            averageFatPercentage = totalFatPercentage / overFourMembers.size();
        } else {
            averageFatPercentage = -1;
        }

           // if no individuals meet the criteria return -1
        System.out.println("The average fat percentage for members who workout more than 4 days a week is : "
                + Double.parseDouble(String.format("%.2f", averageFatPercentage)));
    }

    /**
     * Method, prints the average BMI for members with an
     * experience level of 3
     */
    private void printAverageBmiForAdvancedLevelMembers() {
        List<Entry> advancedMembers = new ArrayList<>();

        for (Entry entry : entryCatalog.getEntriesList()){
            if (entry.getEntryProperty(EntryProperty.EXPERIENCE_LEVEL) == 3) {
                advancedMembers.add(entry);
            }
        }

        double averageBmiForAdvancedLevelMembers;

        if (!advancedMembers.isEmpty()) {
            double totalBmi = 0.0;

            for (Entry entry : advancedMembers) {
                totalBmi += entry.getEntryProperty(EntryProperty.BMI);
            }
            averageBmiForAdvancedLevelMembers = totalBmi / advancedMembers.size();
        } else {
            averageBmiForAdvancedLevelMembers = -1;
        }

        // Return the average BMI, or -1 if no members meet the criteria
        System.out.println("The average BMI of members who have an Advanced level of experience is : "
                + Double.parseDouble(String.format("%.2f", averageBmiForAdvancedLevelMembers)));
    }

    /**
     * Method, prints members with a water intake of above
     * 3 litres a day
     */
    private void printMembersCountWaterIntakeAbove3Liters() {
        List<Entry> membersWaterAboveThreeLitres = new ArrayList<>();

        for (Entry entry : entryCatalog.getEntriesList()) {
            if (entry.getEntryProperty(EntryProperty.WATER_INTAKE) > 3.0) {
                membersWaterAboveThreeLitres.add(entry);
            }
        }

        int count = membersWaterAboveThreeLitres.size();

        // Return the count of members with water intake above 3 liters
        System.out.println("The number of members with a water intake above 3 litres is : " + count);
    }

    /**
     * Method, prints the percentage of members with a BMI
     * above 25
     */
    private void printPercentageAboveHealthyBmi() {
        List<Entry> members = new ArrayList<>();
        List<Entry> membersAboveHealthyBmi = new ArrayList<>();

        for (Entry entry : entryCatalog.getEntriesList()) {
            members.add(entry);

            if (entry.getEntryProperty(EntryProperty.BMI) > 25){
                membersAboveHealthyBmi.add(entry);
            }
        }

        double percentageAboveHealthyBmi;

        if (!membersAboveHealthyBmi.isEmpty()) {
            percentageAboveHealthyBmi = (((double) membersAboveHealthyBmi.size()) / ((double) members.size()) * 100);
        } else {
            percentageAboveHealthyBmi = -1;
        }

        // Return the percentage of members with BMI above 25
        System.out.println("The percentage of members with BMI above 25 is : "
                + Double.parseDouble(String.format("%.2f" , percentageAboveHealthyBmi)) + "%");
    }

    /**
     * Method, prints the number of members with a
     * Yoga workout type
     */
    private void printNumberOfMembersWithYogaWorkoutType() {
        List<Entry> members = new ArrayList<>(entryCatalog.getEntriesList());
        List<Entry> yogaMembers = entryCatalog.getEntriesListByEntryDetail(members, EntryDetail.WORKOUT_TYPE,
                "yoga");

        int count = yogaMembers.size();

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