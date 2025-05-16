/**
 * Histogram.java
 * @version 1.1 16/05/2025
 * @author Jaafar Mohammed
 */

package uk.ac.sheffield.com1003.assignment2425.gui;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.AbstractEntryCatalog;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Entry;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.EntryProperty;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.AbstractHistogram;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.HistogramBin;

import java.util.*;

/**
 * Class, Histogram which implements the
 * logic to draw the histogram
 */
public class Histogram extends AbstractHistogram {

    /**
     * Constructor, creates an instance of the Histogram class
     * @param catalog The catalog of entries
     * @param filteredEntries A list of entries
     * @param property A supplied entry property
     */
    public Histogram(AbstractEntryCatalog catalog, List<Entry> filteredEntries, EntryProperty property) {
        super(catalog, filteredEntries, property);
    }

    /**
     * Method, updates the histogram with a new entry property
     * @param property A supplied entry property
     * @param filteredEntries A list of entries
     */
    @Override
    public void updateHistogramContents(EntryProperty property, List<Entry> filteredEntries) {
        this.property = property;
        entryList = filteredEntries;
        this.entryCountsPerBin.clear();

        if (filteredEntries.isEmpty() ) {
            minPropertyValue = 0;
            maxPropertyValue = 1;
            return;
        }

        minPropertyValue = catalog.getMinimumValue(property, entryList);
        maxPropertyValue = catalog.getMaximumValue(property, entryList);

        double binSize = (maxPropertyValue - minPropertyValue) / (double) NUMBER_BINS;

        for (int i = 0; i < NUMBER_BINS; i++) {
            double lowerBound = minPropertyValue + (i * binSize);
            double upperBound = lowerBound + binSize;

            HistogramBin bin;
            if (i < NUMBER_BINS - 1){
                bin = new HistogramBin(lowerBound, upperBound, false);
            } else {
                bin = new HistogramBin(lowerBound, upperBound, true);
            }
            entryCountsPerBin.put(bin, 0);
        }

        for (Entry entry : entryList) {
            double value = entry.getEntryProperty(property);

            for (HistogramBin bin : entryCountsPerBin.keySet()) {
                if (bin.valueInBin(value)){
                    entryCountsPerBin.put(bin, entryCountsPerBin.get(bin) + 1);
                }
            }
        }
    }

    @Override
    public double getAveragePropertyValue() throws NoSuchElementException {
        try {
            return catalog.getAverageValue(property, entryList);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }
    }

}
