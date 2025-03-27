package uk.ac.sheffield.com1003.assignment2425.gui;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.AbstractGymDashboardPanel;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.AbstractHistogram;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.AbstractHistogramPanel;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.gui.HistogramBin;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
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

public class HistogramPanel extends AbstractHistogramPanel {
    public HistogramPanel(AbstractGymDashboardPanel parentPanel, AbstractHistogram histogram) {
        super(parentPanel, histogram);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO delete body and implement
        super.paintComponent(g);
        Dimension d = getSize();
        Graphics2D g2 = (Graphics2D) g;

        Line2D l = new Line2D.Double(
                0,
                0,
                d.width,
                d.height
        );
        g2.draw(l);

    }
}
