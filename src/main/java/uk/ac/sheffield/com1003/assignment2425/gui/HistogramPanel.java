/**
 * HistogramPanel.java
 * @version 1.1 16/05/2025
 * @author Jaafar Mohammed
 */

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

/**
 * Class, the HistogramPanel class that displays the histogram
 * and extends AbstractHistogramPanel
 */
public class HistogramPanel extends AbstractHistogramPanel {

    /**
     * Constructor, makes an instance of the HistogramPanel
     * to display the histogram
     * @param parentPanel The background panel
     * @param histogram The histogram itself
     */
    public HistogramPanel(AbstractGymDashboardPanel parentPanel, AbstractHistogram histogram) {
        super(parentPanel, histogram);
    }

    /**
     * Method, paints the histogram onto the graphics panel
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Dimension d = getSize();
        Graphics2D g2 = (Graphics2D) g;

        int panelWidth = d.width;
        int panelHeight = d.height;

        int sidePadding = 40;
        int bottomPadding = 10;
        int topPadding = 20;
        int sideAxisPadding = 20;

        AbstractHistogram histogram = getHistogram();
        List<HistogramBin> bins = histogram.getBinsInBoundaryOrder();
        int binCount = bins.size();

        if (binCount == 0) {
            return;
        }

        int histogramWidth = panelWidth - (2 * sidePadding);
        int barWidth = histogramWidth / binCount;
        int maxBinHeight = histogram.largestBinCount();

        int maxBarHeight = (int) (panelHeight * 0.7);
        int axisY = panelHeight - bottomPadding;

        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLACK);

        // Drawing Y-axis
        g2.drawLine(sideAxisPadding, topPadding, sideAxisPadding, axisY);
        //Drawing X-axis
        g2.drawLine(sideAxisPadding, axisY, panelWidth - sideAxisPadding, axisY);

        for (int i = 0; i < binCount; i++) {
            HistogramBin bin = bins.get(i);
            int count = histogram.getNumberOfEntriesInBin(bin);
            int barHeight = (int) (((double) count / (double) maxBinHeight) * maxBarHeight);

            int x = sideAxisPadding + (i * barWidth);
            int y = axisY - barHeight;

            //bars
            g2.setColor(new Color(2, 4, 161));
            g2.fillRect(x, y, barWidth - 2, barHeight);

            g2.setColor(Color.BLACK); // bar border
            g2.drawRect(x, y, barWidth - 2, barHeight);

            //Drawing mean line
            try {
                double meanValue = histogram.getAveragePropertyValue();
                int meanX = sidePadding + (int) ((meanValue - histogram.getMinPropertyValue()) /
                        (histogram.getMaxPropertyValue() - histogram.getMinPropertyValue()) * histogramWidth);

                g2.setColor(Color.RED);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(meanX, axisY, meanX, axisY - maxBarHeight);

                g2.drawString("Mean: " + Double.parseDouble(String.format("%.2f", meanValue)), meanX - 20,
                        axisY - maxBarHeight - 5);
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException("No mean value found");
            }
        }
    }
}
