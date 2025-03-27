package uk.ac.sheffield.com1003.assignment2425.codeprovided.gui;

import javax.swing.*;

// This class provides a basic implementation of the main JFrame for the GymDashboard.
// You can not change the implementation of this class as it is part of a codeprovided package

public class GymDashboard extends JFrame {
    public GymDashboard(AbstractGymDashboardPanel panel){
        setTitle("Gym Dashboard");
        add(panel);
        //maximize the JFrame to fit the entire screen.
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }
}
