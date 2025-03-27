package uk.ac.sheffield.com1003.assignment2425.codeprovided.gui;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.AbstractEntryCatalog;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Entry;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.EntryProperty;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.SubQuery;
import uk.ac.sheffield.com1003.assignment2425.gui.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

// This class provides a basic implementation of the main elements in the GUI as discussed in the handout.
// You will  need to consider implementing the abstract methods in this class by overriding them.
// You can not change the implementation of this class as it is part of a codeprovided package

public abstract class AbstractGymDashboardPanel extends JPanel {

    protected final AbstractEntryCatalog entryCatalog;

    protected List<Entry> filteredEntriesList;

    protected List<SubQuery> subQueryList = new ArrayList<>();

    // histogram being shown
    protected AbstractHistogram histogram;

    // starting the definition of buttons
    // buttonAddFilter will add a new query condition to queryConditionList when clicked
    // clicking this button implies calling the method addFilter(...)
    protected final JButton buttonAddFilter = new JButton("Add by Property Filter");

    // buttonClearFilters will remove all query conditions in queryConditionList when clicked
    // clicking this button implies calling the method clearFilters(...)
    protected final JButton buttonClearFilters = new JButton("Clear All by Property Filters");

    protected JComboBox<String> comboBoxPropertyNames = new JComboBox<>();

    Vector<String> propertyValues = new Vector<>(
            Arrays.stream(EntryProperty.values())
                    .filter(property -> property != EntryProperty.AGE &&
                            property != EntryProperty.HEIGHT &&
                            property != EntryProperty.WEIGHT)
                    .map(EntryProperty::getName)
                    .collect(Collectors.toList())
    );

    protected JComboBox<String> comboQueryProperties = new JComboBox<>(propertyValues);

    // defining the combobox used to select the operator that will be used to build the filter (or SubQuery object)
    // than will be applied
    protected String[] operators = {">", ">=", "<", "<=", "==", "!="};
    protected JComboBox<String> comboOperators = new JComboBox<>(operators);

    // defining the textfield where the value of the SubQuery (or filter)
    protected JTextField value = new JTextField(5);

    // defining all the labels to facilitate what goes where in the GUI
    protected JLabel filterByLabel = new JLabel("Filter by :", SwingConstants.LEFT);
    protected JLabel ageRangeSelectorLabel = new JLabel("Age:", SwingConstants.LEFT);
    protected JLabel heightRangeSelectorLabel = new JLabel("Height:", SwingConstants.LEFT);
    protected JLabel weightRangeSelectorLabel = new JLabel("Weight:", SwingConstants.LEFT);

    protected JLabel subQueryLabel = new JLabel("Filter by property:", SwingConstants.LEFT);
    protected JLabel operatorLabel = new JLabel("Operator:", SwingConstants.LEFT);
    protected JLabel operatorValueLabel = new JLabel("Value:", SwingConstants.LEFT);
    protected JLabel subQueryListLabel = new JLabel("List of filters by property (or subqueries):", SwingConstants.LEFT);
    protected JLabel histogramPropertyLabel = new JLabel("Histogram entry property:", SwingConstants.LEFT);

    // defining the three JTextAreas that will need to be updated every time the buttons
    // buttonAddFilter and buttonClearFilters are clicked
    // subQueriesTextArea will show the contents of subQueryList
    protected JTextArea subQueriesTextArea = new JTextArea(1, 50);
    // statisticsTextArea will show basic summary statistics for the entries in the dataset
    // (which contains the results after executing the filters or SubQuery in subQueryList)
    protected JTextArea statisticsTextArea = new JTextArea(10, 70);

    // the three RangeSliders for age / height / weight
    protected RangeSlider ageRangeSlider;
    protected RangeSlider heightRangeSlider;
    protected RangeSlider weightRangeSlider;

    // constants to set up range sliders
    protected final double MIN_AGE = 0.0;
    protected final double MAX_AGE = 100;
    protected final double MIN_HEIGHT = 0.0;
    protected final double MAX_HEIGHT = 2.5;
    protected final double MIN_WEIGHT = 30;
    protected final double MAX_WEIGHT = 160;

    // titles for TitleBorders used to name the three main GUI areas
    protected String statisticsTitle = "GYM MEMBERS STATISTICS";
    protected String histogramTitle = "HISTOGRAM";

    public AbstractGymDashboardPanel(AbstractEntryCatalog entryCatalog) {
        Border blackLineBorder = BorderFactory.createLineBorder(Color.black);

        this.entryCatalog = entryCatalog;

        this.filteredEntriesList = entryCatalog.getEntriesList();


        subQueriesTextArea.setName("subQueries");
        comboQueryProperties.setName("entryProperties");
        value.setName("filterValue");

        statisticsTextArea.setName("entryCatalogStats");
        comboOperators.setName("operators");
        buttonAddFilter.setName("addFilter");
        buttonClearFilters.setName("clearFilters");

        // building the GUI using a combination of JPanels and a range of LayoutManagers
        // to get a structured GUI
        // Rearranged Layout
        this.setLayout(new BorderLayout());

        // Query Panel (Top)
        JPanel queryPanel = new JPanel();
        queryPanel.setLayout(new GridLayout(4, 1, 0, 0));
        queryPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        queryPanel.setBorder(blackLineBorder);

        // Type Selector Panel (within Query Panel)
        // Type Selector Panel (within Query Panel)
        ageRangeSlider = new RangeSlider(MIN_AGE, MAX_AGE,
                getMinimumValue(EntryProperty.AGE, filteredEntriesList),
                getMaximumValue(EntryProperty.AGE, filteredEntriesList));
        ageRangeSlider.setPreferredSize(new Dimension(300, 40));

        heightRangeSlider = new RangeSlider(MIN_HEIGHT, MAX_HEIGHT,
                getMinimumValue(EntryProperty.HEIGHT, filteredEntriesList),
                getMaximumValue(EntryProperty.HEIGHT, filteredEntriesList));
        heightRangeSlider.setPreferredSize(new Dimension(300, 40));

        weightRangeSlider = new RangeSlider(MIN_WEIGHT, MAX_WEIGHT,
                getMinimumValue(EntryProperty.WEIGHT, filteredEntriesList),
                getMaximumValue(EntryProperty.WEIGHT, filteredEntriesList));
        weightRangeSlider.setPreferredSize(new Dimension(300, 40));

        JPanel typeSelectorPanel = new JPanel();
        typeSelectorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        typeSelectorPanel.add(filterByLabel);
        typeSelectorPanel.add(ageRangeSelectorLabel);
        typeSelectorPanel.add(ageRangeSlider);

        typeSelectorPanel.add(heightRangeSelectorLabel);
        typeSelectorPanel.add(heightRangeSlider);

        typeSelectorPanel.add(weightRangeSelectorLabel);
        typeSelectorPanel.add(weightRangeSlider);

        queryPanel.add(typeSelectorPanel);

        // Filter Builder Panel (within Query Panel)
        JPanel filterBuilderPanel = new JPanel(new FlowLayout());
        filterBuilderPanel.add(subQueryLabel);
        filterBuilderPanel.add(comboQueryProperties);
        filterBuilderPanel.add(operatorLabel);
        filterBuilderPanel.add(comboOperators);
        filterBuilderPanel.add(operatorValueLabel);
        filterBuilderPanel.add(value);
        filterBuilderPanel.add(buttonAddFilter);
        filterBuilderPanel.add(buttonClearFilters);
        queryPanel.add(filterBuilderPanel);

        // SubQuery List Area (within Query Panel)
        queryPanel.add(subQueryListLabel);
        JScrollPane subQueryScrollPane = new JScrollPane(subQueriesTextArea);
        queryPanel.add(subQueryScrollPane);

        // Histogram plot panel
        JPanel histogramContainer = new JPanel();
        JPanel controlHistogramContainer = new JPanel();
        controlHistogramContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JPanel controlHistogramPropertyContainer = new JPanel();

        histogramContainer.setLayout(new BorderLayout());
        controlHistogramPropertyContainer.add(histogramPropertyLabel, BorderLayout.WEST);
        controlHistogramPropertyContainer.add(comboBoxPropertyNames, BorderLayout.EAST);
        controlHistogramContainer.add(controlHistogramPropertyContainer);

        histogram = new Histogram(entryCatalog, filteredEntriesList, EntryProperty.AGE);
        AbstractHistogramPanel histogramPanel = new HistogramPanel(this, histogram);
        histogramContainer.add(histogramPanel, BorderLayout.CENTER);
        histogramContainer.add(controlHistogramContainer, BorderLayout.SOUTH);

        TitledBorder tbHistogram = BorderFactory.createTitledBorder(
                blackLineBorder, histogramTitle);
        tbHistogram.setTitleJustification(TitledBorder.CENTER);
        histogramContainer.setBorder(tbHistogram);

        // Statistics Panel (bottom)
        JPanel statisticsPanel = new JPanel(new BorderLayout());
        JScrollPane statisticsScrollPane = new JScrollPane(statisticsTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        statisticsPanel.add(statisticsScrollPane, BorderLayout.CENTER);
        statisticsPanel.setBorder(BorderFactory.createTitledBorder(blackLineBorder, statisticsTitle));

        // Adjust the preferred size of the statistics panel for a smaller height
        statisticsPanel.setPreferredSize(new Dimension(300, 150));

        // Add Panels to Main Layout
        this.add(queryPanel, BorderLayout.NORTH);      // Top
        this.add(histogramContainer, BorderLayout.CENTER); // Left (Custom Chart + Statistics embedded)
        this.add(statisticsPanel, BorderLayout.SOUTH);

        // dynamically populate the comboboxes with entry details
        this.populateComboBoxes();

        // adding the listeners, you will need to implement this method to register the events generated
        // by the GUI components that will be expecting a change in the results being displayed by the GUI
        this.addListeners();
    }

    // updates the histogram
    public void updateHistogram() {
        if (comboBoxPropertyNames.getSelectedItem() == "") {
            return;
        }
        EntryProperty property = EntryProperty.fromName((String) comboBoxPropertyNames.getSelectedItem());
        histogram.updateHistogramContents(property, filteredEntriesList);
    }

    // this method is called when JButton buttonAddFilter is clicked
    // it will add a new filter (a SubQuery object) to subQueryList
    // it will update teh GUI accordingly, i.e. update the statisticsTextArea and subQueriesTextArea
    // it will update the histogram to reflect also the newly filtered entries
    public abstract void addFilter();

    // clears all filters from subQueryList ArrayList, also updates relevant GUI components
    // it is called when the button buttonClearFilters is clicked
    public abstract void clearFilters();

    // executes the complete query to the relevant entry list
    public abstract void executeQuery();

    // dynamically populates the comboboxes in the histogram panel (comboBoxPropertyNames)
    public abstract void populateComboBoxes();

    // adds all relevant listeners to the GUI components,
    // at least to the range sliders, buttonAddFilter, buttonClearFilters... (list is not complete)
    public abstract void addListeners();

    // updates contents displayed in statistics area (statisticsTextArea), it recalculates average, min, and max
    // values for each entry property (to reflect the filtered selection on entries)
    public abstract void updateStatistics();

    // get minimum value for provided EntryProperty from a list of Entry objects
    public abstract double getMinimumValue(EntryProperty entryProperty, List<Entry> filteredEntriesList);

    // get maximum value for provided EntryProperty List<Entry>
    public abstract double getMaximumValue(EntryProperty entryProperty, List<Entry> filteredEntriesList);


}
