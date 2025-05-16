/**
 * GymDashboardPanel.java
 * @version 1.1 16/05/2025
 * @author Jaafar Mohammed
 */

package uk.ac.sheffield.com1003.assignment2425.gui;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.*;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.AbstractGymDashboardPanel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Class, GymDashboardPanel class which generates the area
 * that edits and filters the histogram
 */
public class GymDashboardPanel extends AbstractGymDashboardPanel {

    /**
     * Constructor, creates an instance of GymDashboardPanel
     * @param entryCatalog A catalog of all the entries
     */
    public GymDashboardPanel(AbstractEntryCatalog entryCatalog) {
        super(entryCatalog);
    }

    /**
     * Method, takes the query that has been built and
     * runs it on a list of entries
     */
    @Override
    public void executeQuery() {
        List<Entry> appliedChanges = new ArrayList<>(entryCatalog.getEntriesList());

        if (!subQueryList.isEmpty() || !entryCatalog.getEntriesList().isEmpty()) {
            Query query = new Query(subQueryList);
            appliedChanges = query.executeQuery(entryCatalog);
        }

        appliedChanges = appliedChanges.stream()
                .filter(entry -> entry.getEntryProperty(EntryProperty.AGE) >= ageRangeSlider.getLowerValue() &&
                        entry.getEntryProperty(EntryProperty.AGE) <= ageRangeSlider.getUpperValue())
                .filter(entry -> entry.getEntryProperty(EntryProperty.HEIGHT) >= heightRangeSlider.getLowerValue()
                        && entry.getEntryProperty(EntryProperty.HEIGHT) <= heightRangeSlider.getUpperValue())
                .filter(entry -> entry.getEntryProperty(EntryProperty.WEIGHT) >= weightRangeSlider.getLowerValue()
                        && entry.getEntryProperty(EntryProperty.WEIGHT) <= weightRangeSlider.getUpperValue())
                .collect(Collectors.toList());

        this.filteredEntriesList = appliedChanges;
    }

    /**
     * Method, clears filters that were previously applied
     */
    @Override
    public void clearFilters() {
        subQueryList.clear();
        subQueriesTextArea.setText("");
        value.setText("");
        executeQuery();
        updateStatistics();
        updateHistogram();
        repaint();
    }

    /**
     * Method, gathers filters applied into subqueries before
     * making them into a query
     */
    @Override
    public void addFilter() {
        String selectedProperty = (String) comboQueryProperties.getSelectedItem();
        String operator = (String) comboOperators.getSelectedItem();
        String selectedValue = value.getText();

        if (selectedValue.isEmpty()) {
            JOptionPane.showMessageDialog(this,"No value selected");
            return;
        }

        double value;
        try {
            value = Double.parseDouble(selectedValue);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Please enter a valid number");
            return;
        }

        EntryProperty entryProperty = null;
        for (EntryProperty currentEntryProperty : EntryProperty.values()) {
            if(currentEntryProperty.getName().equals(selectedProperty)) {
                entryProperty = currentEntryProperty;
                break;
            }
        }

        SubQuery subQuery = new SubQuery(entryProperty, operator, value);
        subQueryList.add(subQuery);

        executeQuery();
        if (filteredEntriesList == null || filteredEntriesList.isEmpty()) {
            statisticsTextArea.setText("There are no entries that the current filters apply to");
        } else {
            updateStatistics();
        }
        updateHistogram();
        repaint();
    }

    /**
     * Method, comboBoxes are populated with entry properties
     */
    @Override
    public void populateComboBoxes() {
        for (EntryProperty property : EntryProperty.values()) {
            comboBoxPropertyNames.addItem(property.name());
        }
    }

    /**
     * Method, event listeners are added for each button, dropdown and
     * slider for the gui
     */
    @Override
    public void addListeners() {
        buttonAddFilter.addActionListener(e -> addFilter());
        buttonClearFilters.addActionListener(e -> clearFilters());

        comboBoxPropertyNames.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateHistogram();
                repaint();
            }
        });

        comboQueryProperties.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateHistogram();
                updateStatistics();
                repaint();
            }
        });

        comboOperators.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateHistogram();
                updateStatistics();
                repaint();
            }
        });

        ChangeListener sliderListener = e -> {
            Object dest = e.getSource();
            if (dest == ageRangeSlider || dest == heightRangeSlider || dest == weightRangeSlider) {
                executeQuery();
                updateHistogram();
                updateStatistics();
                repaint();
            }
        };

        ageRangeSlider.addChangeListener(sliderListener);
        heightRangeSlider.addChangeListener(sliderListener);
        weightRangeSlider.addChangeListener(sliderListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    /**
     * Method, appends text including statistics in the statistics
     * text area
     */
    @Override
    public void updateStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("Statistics for ").append(comboQueryProperties.getSelectedItem()).append(" ")
                .append("for ").append(filteredEntriesList.size()).append(" entries out of ").
                append(entryCatalog.getEntriesList().size()).append(" total entries");

        double minPropertyValue = getMinimumValue(EntryProperty.
                valueOf((String) comboQueryProperties.getSelectedItem()), filteredEntriesList);
        double maxPropertyValue = getMaximumValue(EntryProperty.valueOf((String) comboQueryProperties.getSelectedItem())
                , filteredEntriesList);

        if (minPropertyValue != 0 && maxPropertyValue != 0) {
            stats.append("\nMinimum value: ").append(minPropertyValue);
            stats.append("\nMaximum value: ").append(maxPropertyValue);
        } else {
            stats.append(": No data as of now");
        }

        statisticsTextArea.setText(stats.toString());
    }

    @Override
    public double getMinimumValue(EntryProperty entryProperty, List<Entry> filteredEntriesList) {
        double value = Double.MAX_VALUE;

        if (filteredEntriesList == null || filteredEntriesList.isEmpty()) {
            switch (entryProperty) {
                case AGE -> value = MIN_AGE;
                case HEIGHT -> value = MIN_HEIGHT;
                case WEIGHT -> value = MIN_WEIGHT;
                default -> value = 0;
            }
            return value;
        }

        for (Entry entry : filteredEntriesList) {
            double currentEntryPropertyValue = entry.getEntryProperty(entryProperty);
            if (currentEntryPropertyValue < value) {
                value = currentEntryPropertyValue;
            }
        }
        return Double.parseDouble(String.format("%.2f", value));
    }

    @Override
    public double getMaximumValue(EntryProperty entryProperty, List<Entry> filteredEntriesList) {
        double value = Double.MIN_VALUE;

        if (filteredEntriesList == null || filteredEntriesList.isEmpty()) {
            switch (entryProperty) {
                case AGE -> value = MAX_AGE;
                case HEIGHT -> value = MAX_HEIGHT;
                case WEIGHT -> value = MAX_WEIGHT;
                default -> value = 0;
            }
            return value;
        }

        for (Entry entry : filteredEntriesList) {
            double currentEntryPropertyValue = entry.getEntryProperty(entryProperty);
            if (currentEntryPropertyValue > value) {
                value = currentEntryPropertyValue;
            }
        }
        return Double.parseDouble(String.format("%.2f", value));
    }
}
