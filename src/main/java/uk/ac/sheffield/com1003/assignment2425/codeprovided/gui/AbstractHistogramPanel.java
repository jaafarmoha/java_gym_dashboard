package uk.ac.sheffield.com1003.assignment2425.codeprovided.gui;

import javax.swing.*;

// This class provides an abstract class for displaying a histogram.
// You will  need to consider implementing the abstract methods in this class by overriding them.
// You will also need to add new methods to the class implementing this abstract class (HistogramPanel)
// (e.g to display axes, bars, the vertical line showing the average, labels etc...
// You can not change the implementation of this class as it is part of a codeprovided package

public class AbstractHistogramPanel extends JPanel {
    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private final AbstractGymDashboardPanel parentPanel;
    private final AbstractHistogram histogram;

    public AbstractHistogramPanel(AbstractGymDashboardPanel parentPanel, AbstractHistogram histogram) {
        super();
        this.parentPanel = parentPanel;
        this.histogram = histogram;
    }

    public AbstractHistogram getHistogram() {
        return histogram;
    }
}
