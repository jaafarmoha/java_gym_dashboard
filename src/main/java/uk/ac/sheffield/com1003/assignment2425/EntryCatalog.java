/**
 * EntryCatalog.java
 * @version 1.1 16/05/2025
 * @author Jaafar Mohammed
 */

package uk.ac.sheffield.com1003.assignment2425;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Class, an EntryCatalog class which extends
 * AbstractEntryCatalog
 */
public class EntryCatalog extends AbstractEntryCatalog {

    /**
     * Constructor, creates a new EntryCatalog with the
     * entryFile supplied as a parameter
     * @param entryFile The csv file of gym entries
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public EntryCatalog(String entryFile)
            throws IllegalArgumentException, IOException {
        super(entryFile);
    }

    /**
     * Method, parses through a line in the given file
     * @param line An entry line of the file as a String
     * @return An EntryPropertyMap with all the properties populated
     * @throws IllegalArgumentException
     */
    @Override
    public EntryPropertyMap parseEntryLine(String line) throws IllegalArgumentException {
        EntryPropertyMap entryPropertyMap = new EntryPropertyMap();

        //Splitting each value by the commas
        String[] tokens = line.split(",");

        if (tokens.length != 15) {
            throw new IllegalArgumentException("Line does not contain 15 values" + line);
        }

        // Add each value to it's corresponding property in the property map
        try {
            entryPropertyMap.putProperty(EntryProperty.AGE, Double.parseDouble(tokens[0]));
            entryPropertyMap.putProperty(EntryProperty.WEIGHT, Double.parseDouble(tokens[2]));
            entryPropertyMap.putProperty(EntryProperty.HEIGHT, Double.parseDouble(tokens[3]));
            entryPropertyMap.putProperty(EntryProperty.MAX_BPM, Double.parseDouble(tokens[4]));
            entryPropertyMap.putProperty(EntryProperty.AVG_BPM, Double.parseDouble(tokens[5]));
            entryPropertyMap.putProperty(EntryProperty.RESTING_BPM, Double.parseDouble(tokens[6]));
            entryPropertyMap.putProperty(EntryProperty.SESSION_DURATION, Double.parseDouble(tokens[7]));
            entryPropertyMap.putProperty(EntryProperty.CALORIES_BURNED, Double.parseDouble(tokens[8]));
            entryPropertyMap.putProperty(EntryProperty.BODY_FAT_PERCENTAGE, Double.parseDouble(tokens[10]));
            entryPropertyMap.putProperty(EntryProperty.WATER_INTAKE, Double.parseDouble(tokens[11]));
            entryPropertyMap.putProperty(EntryProperty.WORKOUT_FREQUENCY, Double.parseDouble(tokens[12]));
            entryPropertyMap.putProperty(EntryProperty.EXPERIENCE_LEVEL, Double.parseDouble(tokens[13]));
            entryPropertyMap.putProperty(EntryProperty.BMI, Double.parseDouble(tokens[14]));
        } catch (NumberFormatException e) {
            System.out.println("Line contains an invalid value: " + line);
        }

        return entryPropertyMap;
    }

    /**
     * Method, filters a list of entries to get the entries with a
     * particular detail
     * @param filteredEntriesList A list of entries
     * @param entryDetail A supplied EntryDetail
     * @param name The type of the entry detail
     * @return A filtered list with only that entry detail type
     */
    @Override
    public List<Entry> getEntriesListByEntryDetail(List<Entry> filteredEntriesList, EntryDetail entryDetail,
                                                   String name) {
        List<Entry> filteredEntries = new ArrayList<>();

        for (Entry entry : filteredEntriesList) {
            if (entry.getEntryDetail(entryDetail) != null &&
                    entry.getEntryDetail(entryDetail).equalsIgnoreCase(name)) { // Checking if entry detail matches
                filteredEntries.add(entry);
            }
        }
        return filteredEntries;
    }

    /**
     * Method, gets the minimum value of an entry property from a
     * list of entries
     * @param entryProperty A supplied entry property
     * @param entriesList a list of entries
     * @return The minimum value of that given property in the list
     * @throws NoSuchElementException
     */
    @Override
    public double getMinimumValue(EntryProperty entryProperty, List<Entry> entriesList) throws NoSuchElementException {
        if (entriesList.isEmpty()) {
            throw new NoSuchElementException("There are no entries in the list");
        }
        double minimumValue = entriesList.get(0).getEntryProperty(entryProperty);
        for (Entry entry : entriesList) {
            double tempValue = entry.getEntryProperty(entryProperty);
            if (tempValue < minimumValue) {
                minimumValue = tempValue;
            }
        }
        return minimumValue;
    }

    /**
     * Method, gets the maximum value of an entry property from a
     * list of entries
     * @param entryProperty A supplied entry property
     * @param entriesList a list of entries
     * @return The maximum value of that given property in the list
     * @throws NoSuchElementException
     */
    @Override
    public double getMaximumValue(EntryProperty entryProperty, List<Entry> entriesList) throws NoSuchElementException {
        if (entriesList.isEmpty()) {
            throw new NoSuchElementException("There are no entries in the list");
        }
        double maximumValue = entriesList.get(0).getEntryProperty(entryProperty);
        for (Entry entry : entriesList) {
            double tempValue = entry.getEntryProperty(entryProperty);
            if (tempValue > maximumValue) {
                maximumValue = tempValue;
            }
        }
        return maximumValue;
    }

    /**
     * Method, gets the average value of an entry property from a
     * list of entries
     * @param entryProperty A supplied entry property
     * @param entriesList a list of entries
     * @return The average value of that given property in the list
     * @throws NoSuchElementException
     */
    @Override
    public double getAverageValue(EntryProperty entryProperty, List<Entry> entriesList) throws NoSuchElementException {
        if (entriesList.isEmpty()) {
            throw new NoSuchElementException("There are no entries in the list");
        }
        double total = 0.0;
        for (Entry entry : entriesList) {
            total += entry.getEntryProperty(entryProperty);
        }

        return total / entriesList.size();
    }

    /**
     * Method, gives the first 5 entries in the entries list
     * @return The 5 entries
     */
    @Override
    public List<Entry> getFirstFiveEntries() {
        List<Entry> firstFiveEntries = new ArrayList<>();

        for (Entry entry : entriesList) {
            while (firstFiveEntries.size() <= 5) {
                firstFiveEntries.add(entry);
            }
        }

        return firstFiveEntries;
    }
}
