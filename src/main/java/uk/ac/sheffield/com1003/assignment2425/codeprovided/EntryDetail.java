package uk.ac.sheffield.com1003.assignment2425.codeprovided;

import java.util.NoSuchElementException;

// This is a helper enum with constants representing the categorical properties of an entry
// You can not change the implementation of this class as it is part of a codeprovided package

public enum EntryDetail {
    GENDER("The member's gender"),
    WORKOUT_TYPE("The type of workout that the member performs");

    private final String detailName;

    EntryDetail(String dName) {
        detailName = dName;
    }

    public String getName() {
        return this.detailName;
    }

    public static EntryDetail fromName(String name) throws NoSuchElementException {
        String pName = name.toUpperCase();
        EntryDetail entryDetail = null;
        try {
            entryDetail = EntryDetail.valueOf(pName);
        } catch (IllegalArgumentException e) {
            throw new NoSuchElementException("No such detail (" + name + ")!");
        }
        return entryDetail;
    }
}
