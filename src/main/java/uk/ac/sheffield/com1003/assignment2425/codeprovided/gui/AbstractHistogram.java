package uk.ac.sheffield.com1003.assignment2425.codeprovided.gui;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.AbstractEntryCatalog;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Entry;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.EntryProperty;

import java.util.*;
import java.util.stream.Collectors;

// This class provides some functionality to store and access a histogram (see handout for explanation of what a histogram is).
// Histograms split a set of values (e.g. property measurements) into a number of "bins", between the minimum
// and maximum values of the set of values. Each value is added to the bin that it matches to.
// These bins can then be plotted as bars; see AbstractHistogramPanel
// You will  need to consider implementing the abstract methods in this class by overriding them.
// You can not change the implementation of this class as it is part of a codeprovided package

public abstract class AbstractHistogram {

    protected static final int NUMBER_BINS = 11;    // by default defines 11 bins

    protected Map<HistogramBin, Integer> entryCountsPerBin;

    protected final AbstractEntryCatalog catalog;
    protected List<Entry> entryList;
    protected EntryProperty property;

    protected double minPropertyValue = 0;
    protected double maxPropertyValue = 1;

    // access to EntryCatalog helps retrieve min/max/avg values
    // access to List<Entry> provides access to the list of entries to generate the histogram,
    // EntryProperty indicates which property is used to generate the histogram
    public AbstractHistogram(AbstractEntryCatalog catalog,
                             List<Entry> entryList,
                             EntryProperty property) {
        this.catalog = catalog;
        this.entryList = entryList;
        this.property = property;
        this.entryCountsPerBin = new TreeMap<>();
        updateHistogramContents(property, entryList);
    }

    // this method should update (i.e. reset) the histogram, based on a newly selected property and list of entries
    // (see comments in constructor about the parameters of this method)
    public abstract void updateHistogramContents(EntryProperty property, List<Entry> filteredEntries);

    // returns the average of a given property from the current selection of entries
    public abstract double getAveragePropertyValue() throws NoSuchElementException;

    // returns the filled bins of the histogram (in ascending order of their boundaries)
    public List<HistogramBin> getBinsInBoundaryOrder() {
        return entryCountsPerBin.keySet().stream().sorted().collect(Collectors.toList());
    }

    // returns the number of entries in the bin passed as a parameter
    public int getNumberOfEntriesInBin(HistogramBin bin) {
        return entryCountsPerBin.get(bin);
    }

    public double getMinPropertyValue() {
        return minPropertyValue;
    }

    public double getMaxPropertyValue() {
        return maxPropertyValue;
    }

    // returns the number entries in the bin with the most entries (might be useful for scaling the histogram)
    public int largestBinCount() {
        int largestCount = 0;
        for (int count : entryCountsPerBin.values()) {
            if (count > largestCount)
                largestCount = count;
        }
        return largestCount;
    }

}
