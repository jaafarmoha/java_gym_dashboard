package uk.ac.sheffield.com1003.assignment2425;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.stream.Collectors;

// This class provides some default code, but it needs to be completely replaced
// TODO replace default implementations by your own implementations
// TODO you WILL NEED to add new imports
// TODO you WILL NEED to add new methods and variables and constants
// TODO you WILL NEED to add new classes
// TODO remove the comments and tips provided with this template
// TODO add your own comments
// TODO document the class and methods with JavaDoc

public class EntryCatalog extends AbstractEntryCatalog {

    public EntryCatalog(String entryFile)
            throws IllegalArgumentException, IOException {
        super(entryFile);
    }

    @Override
    public EntryPropertyMap parseEntryLine(String line) throws IllegalArgumentException {
        // TODO delete body and implement
        EntryPropertyMap entryPropertyMap = new EntryPropertyMap();
        return entryPropertyMap;
    }

    @Override
    public List<Entry> getEntriesListByEntryDetail(List<Entry> filteredEntriesList, EntryDetail entryDetail, String name) {
        // TODO delete body and implement
        return filteredEntriesList;
    }

    @Override
    public double getMinimumValue(EntryProperty entryProperty, List<Entry> entriesList) throws NoSuchElementException {
        // TODO delete body and implement
        return -1;
    }

    @Override
    public double getMaximumValue(EntryProperty entryProperty, List<Entry> entriesList) throws NoSuchElementException {
        // TODO delete body and implement
        return -1;
    }

    @Override
    public double getAverageValue(EntryProperty entryProperty, List<Entry> entriesList) throws NoSuchElementException {
        // TODO delete body and implement
        return -1;
    }

    @Override
    public List<Entry> getFirstFiveEntries() {
        // TODO delete body and implement
        return new ArrayList<>();
    }
}
