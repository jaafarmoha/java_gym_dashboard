package uk.ac.sheffield.com1003.assignment2425.codeprovided.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// This class provides a basic implementation of a range slider.
// You can not change the implementation of this class as it is part of a codeprovided package

public class RangeSlider extends JSlider {

    private double lowerValue; // Lower bound of the range
    private double upperValue; // Upper bound of the range
    private final double min;
    private final double max;
    private static final int THUMB_SIZE = 12; // Size of the thumbs


    public RangeSlider(double min, double max, double lowerValue, double upperValue) {
        super(0, 100);
        this.min = min;
        this.max = max;
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;

        // Disable the default slider behavior
        setPaintTicks(false);
        setPaintLabels(false);
        setPaintTrack(false);

        // Add mouse listener to handle dragging
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouse(e);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouse(e);
            }
        });
    }

    public double getLowerValue() {
        return lowerValue;
    }

    public void setLowerValue(double value) {
        lowerValue = Math.max(min, Math.min(value, upperValue - 0.01));
        repaint();
    }

    public double getUpperValue() {
        return upperValue;
    }

    public void setUpperValue(double value) {
        upperValue = Math.min(max, Math.max(value, lowerValue + 0.01));
        repaint();
    }

    private void handleMouse(MouseEvent e) {
        int mouseX = e.getX();
        int trackWidth = getWidth() - 2 * THUMB_SIZE;

        // Map pixel position to value range
        double value = min + (mouseX - THUMB_SIZE) * (max - min) / trackWidth;

        // Determine which thumb is closer to the click
        if (Math.abs(value - lowerValue) < Math.abs(value - upperValue)) {
            setLowerValue(value);
        } else {
            setUpperValue(value);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Anti-aliasing for smooth graphics
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int trackY = getHeight() / 2 - 2;
        int trackWidth = getWidth() - 2 * THUMB_SIZE;

        // Map values to pixel positions
        int lowerX = THUMB_SIZE + (int) ((lowerValue - min) * trackWidth / (max - min));
        int upperX = THUMB_SIZE + (int) ((upperValue - min) * trackWidth / (max - min));

        // Draw track
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(THUMB_SIZE, trackY, trackWidth, 4);

        // Highlight selected range
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(lowerX, trackY, upperX - lowerX, 4);

        // Draw thumbs
        g2.setColor(SystemColor.control);
        g2.fillOval(lowerX - THUMB_SIZE / 2, trackY - THUMB_SIZE / 2, THUMB_SIZE, THUMB_SIZE);
        g2.fillOval(upperX - THUMB_SIZE / 2, trackY - THUMB_SIZE / 2, THUMB_SIZE, THUMB_SIZE);

        // Draw current values
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, 10));
        String lowerValueText = String.format("%.2f", lowerValue);
        String upperValueText = String.format("%.2f", upperValue);
        int textYOffset =10; // Adjust this value as needed to prevent clipping
        g2.drawString(lowerValueText, lowerX - THUMB_SIZE / 2, trackY - textYOffset);
        g2.drawString(upperValueText, upperX - THUMB_SIZE / 2, trackY - textYOffset);

    }
}

