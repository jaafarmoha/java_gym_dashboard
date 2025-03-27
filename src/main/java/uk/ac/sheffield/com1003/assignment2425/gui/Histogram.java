package uk.ac.sheffield.com1003.assignment2425.gui;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.AbstractEntryCatalog;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Entry;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.EntryProperty;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.AbstractHistogram;

import java.util.*;

// This class provides some default code, but it needs to be completely replaced
// TODO replace default implementations by your own implementations
// TODO you WILL NEED to add new imports
// TODO you WILL NEED to add new methods and variables and constants
// TODO you WILL NEED to add new classes
// TODO remove the comments and tips provided with this template
// TODO add your own comments
// TODO document the class and methods with JavaDoc

public class Histogram extends AbstractHistogram
{

    public Histogram(AbstractEntryCatalog catalog, List<Entry> filteredEntries, EntryProperty property) {
        super(catalog, filteredEntries, property);
    }

    @Override
    public void updateHistogramContents(EntryProperty property, List<Entry> filteredEntries) {
        // TODO implement
    }

    @Override
    public double getAveragePropertyValue() throws NoSuchElementException {
        // TODO delete body and implement
        return -1;
    }

}
