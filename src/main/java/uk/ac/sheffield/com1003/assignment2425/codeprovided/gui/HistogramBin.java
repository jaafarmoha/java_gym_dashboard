package uk.ac.sheffield.com1003.assignment2425.codeprovided.gui;

// This class provides a basic implementation a histogram bin (study this class together with AbstractHistogram).
// You can not change the implementation of this class as it is part of a codeprovided package

public class HistogramBin implements Comparable<HistogramBin>
{
    private final double lowerBoundary; // lower value of the bin
    private final double upperBoundary; // upper value of the bin
    private final boolean finalBin; // true if this is the last bin in the histogram (e.g. the bin for the highest measurements)

    public HistogramBin(double lowerBoundary, double upperBoundary, boolean finalBin)
    {
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
        this.finalBin = finalBin;
    }

    // checks if valueToCheck is in this bin, returns true if the value is in the bin's range; false otherwise.
    public boolean valueInBin(double valueToCheck)
    {
        if (valueToCheck < lowerBoundary)
            return false;
        // Special case for final bin (i.e. the bin that should contain maximum values)
        if (finalBin && valueToCheck == upperBoundary)
            return true;
        //noinspection RedundantIfStatement
        if (valueToCheck >= upperBoundary)
            return false;
        return true;
    }

    public double getLowerBoundary()
    {
        return lowerBoundary;
    }

    public double getUpperBoundary()
    {
        return upperBoundary;
    }

    // Comparator for sorting bins; used in AbstractHistogram.getBinsInBoundaryOrder()
    @Override
    public int compareTo(HistogramBin other)
    {
        return Double.compare(this.lowerBoundary, other.lowerBoundary);
    }
}
