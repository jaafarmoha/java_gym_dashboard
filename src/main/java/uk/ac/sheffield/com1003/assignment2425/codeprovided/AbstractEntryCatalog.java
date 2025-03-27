package uk.ac.sheffield.com1003.assignment2425.codeprovided;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// This class provides basic reading functionalities of the provided dataset with entries.
// You will need to implement the abstract methods in this class by overriding them.
// You can not change the implementation of this class as it is part of a codeprovided package

public abstract class AbstractEntryCatalog {

    protected final List<Entry> entriesList;

    // reads data from the textFileName and populates entryList, the one containing all data entries
    public AbstractEntryCatalog(String textFileName)
            throws IllegalArgumentException, IOException {
        this.entriesList = new ArrayList<>();
        List<Entry> entriesFromFile = readEntriesFromTextDataFile(textFileName);
        entriesList.addAll(entriesFromFile);
    }
    
    // reads the text file provided and creates a list of Entry objects in the data file
    // catches exception errors should they occur and delegates handling of other exceptions
    private List<Entry> readEntriesFromTextDataFile(String textFileName)
            throws IllegalArgumentException, IOException {
        List<Entry> entriesList = new ArrayList<>();
        int count = 1;

        textFileName = textFileName.replaceAll(" ", "");

        BufferedReader br = new BufferedReader(new FileReader(textFileName));
        String line = br.readLine();
        if (line == null) {
            throw new IllegalArgumentException("File is empty. Please run the programme again and provide a valid dataset.");
        }
        while ((line = br.readLine()) != null) {
            try {
                // The entry ID is created by this reader; it is not provided in the original files
                // The ID should _not_ be modified later
                int id = count;
                Entry entry = new Entry(id, parseEntryLine(line));
                entriesList.add(entry);
                count++;

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("File format is incorrect; only double values are allowed. " +
                        "See line: " + (count + 1));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Malformed entry line: " + line +
                        "\nSee line: " + (count + 1));
            }
        }
        return entriesList;
    }

    // returns the list with the entries
    public List<Entry> getEntriesList() {
        return entriesList;
    }

    // returns the list of entries extracted from the provided list with entries
    // filters by EntryDetail and name (the EntryDetail to retrieve)
    public abstract List<Entry> getEntriesListByEntryDetail(List<Entry> filteredEntriesList,
                                                            EntryDetail entryDetail, String name);

    // parses the properties from a given line in the entry catalog file
    // you CAN expect that values appear in the same order as the columns in the value, and the order will NOT change
    // IF anything in that line is malformed (undefined properties, less or more values than expected, then
    // an IllegalArgumentException should be thrown with an appropriate message
    public abstract EntryPropertyMap parseEntryLine(String line) throws IllegalArgumentException;

    // returns the minimum value of a given EntryProperty for the entries in this entry catalog
    public abstract double getMinimumValue(EntryProperty entryProperty, List<Entry> entriesList);

    // returns the maximum value of a given EntryProperty for the entries in this entry catalog
    public abstract double getMaximumValue(EntryProperty entryProperty, List<Entry> entriesList);

    // returns the average value of a given EntryProperty for the entries in this entry catalog
    public abstract double getAverageValue(EntryProperty entryProperty, List<Entry> entriesList);

    public abstract List<Entry> getFirstFiveEntries();
}
