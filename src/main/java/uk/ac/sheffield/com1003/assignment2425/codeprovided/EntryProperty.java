package uk.ac.sheffield.com1003.assignment2425.codeprovided;

import java.util.NoSuchElementException;

// This is a helper enum with constants representing the numerical properties of an entry
// You can not change the implementation of this class as it is part of a codeprovided package

public enum EntryProperty {
    AGE("Age of the gym member."),
    WEIGHT("The gym member's weight in kilograms."),
    HEIGHT("Height of the gym member in meters."),
    MAX_BPM("The maximum heart rate of the gym member in beats per minute."),
    AVG_BPM("The average heart rate of the gym member in beats per minute."),
    RESTING_BPM("The resting heart rate of the gym member in beats per minute."),
    SESSION_DURATION("Duration of each workout session in hours."),
    CALORIES_BURNED("The amount of calories burned by the gym member during the session."),
    BODY_FAT_PERCENTAGE("The percentage of body fat of the member."),
    WATER_INTAKE("Daily water intake during workouts in litres."),
    WORKOUT_FREQUENCY("Number of workout sessions the member has per week."),
    EXPERIENCE_LEVEL("Level of experience, from beginner (1) to expert (3)."),
    BMI("Body Mass Index, calculated from height and weight.");

    private final String propertyName;

    EntryProperty(String pName) {
        propertyName = pName;
    }

    public String getName() {
        return this.propertyName;
    }

    // converts a name String (e.g. max_bpm) to the matching EntryProperty
    // throws a NoSuchElementException if there is no matching EntryProperty
    public static EntryProperty fromName(String name) throws NoSuchElementException {
        String pName = name.toUpperCase();
        EntryProperty entryProperty = null;
        try {
            entryProperty = EntryProperty.valueOf(pName);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("No such property (" + name + ")!");
        }
        return entryProperty;
    }

    // converts a property name String to the matching EntryProperty
    // throws a NoSuchElementException if the String does not match any EntryProperty
    public static EntryProperty fromPropertyName(String name) throws NoSuchElementException {
        for (EntryProperty p : EntryProperty.values()) {
            if (p.getName().equals(name))
                return p;
        }
        throw new NoSuchElementException("No such property (" + name + ")!");
    }
}