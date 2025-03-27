package uk.ac.sheffield.com1003.assignment2425.gui;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.*;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.AbstractGymDashboardPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

// This class provides some default code, but it needs to be completely replaced
// TODO replace default implementations by your own implementations
// TODO you WILL NEED to add new imports
// TODO you WILL NEED to add new methods and variables and constants
// TODO you WILL NEED to add new classes
// TODO remove the comments and tips provided with this template
// TODO add your own comments
// TODO document the class and methods with JavaDoc

public class GymDashboardPanel extends AbstractGymDashboardPanel {

    public GymDashboardPanel(AbstractEntryCatalog entryCatalog) {
        super(entryCatalog);
    }

    @Override
    public void executeQuery() {
        // TODO implement
    }

    @Override
    public void clearFilters() {
        // TODO implement
    }

    @Override
    public void addFilter() {
        // TODO implement
    }

    @Override
    public void populateComboBoxes() {
        // TODO delete body and implement
        comboBoxPropertyNames.addItem("Property 1");
        comboBoxPropertyNames.addItem("Property 2");
        comboBoxPropertyNames.addItem("and more...");
    }

    @Override
    public void addListeners() {
        // TODO implement
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO delete body and implement
        // Tip: you might want to keep this call to super.paintComponent(g)
        super.paintComponent(g);
    }

    @Override
    public void updateStatistics() {
        // TODO implement
    }

    @Override
    public double getMinimumValue(EntryProperty entryProperty, List<Entry> filteredEntriesList) {
        // TODO delete body and implement
        double value = 0;
        switch (entryProperty) {
            case AGE -> value = MIN_AGE;
            case HEIGHT -> value = MIN_HEIGHT;
            case WEIGHT -> value = MIN_WEIGHT;
        }
        return value;
    }

    @Override
    public double getMaximumValue(EntryProperty entryProperty, List<Entry> filteredEntriesList) {
        // TODO delete body and implement
        double value = 0;
        switch (entryProperty) {
            case AGE -> value = MAX_AGE;
            case HEIGHT -> value = MAX_HEIGHT;
            case WEIGHT -> value = MAX_WEIGHT;
        }
        return value;
    }
}
