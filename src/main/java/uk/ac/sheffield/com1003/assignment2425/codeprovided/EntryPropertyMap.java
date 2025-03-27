package uk.ac.sheffield.com1003.assignment2425.codeprovided;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// This class provides a simplified Map wrapper to be used by an Entry
// It will store all the properties for an entry (numerical and categorical)
// This wrapper stores a HashMap rather than extending to de-expose the unneeded methods.
// You can not change the implementation of this class as it is part of a codeprovided package

public class EntryPropertyMap {

    // Consult the Javadocs for Map & HashMap for more information
    private final Map<EntryProperty, Double> propertyToValuesMap = new HashMap<>();

    // Consult the Javadocs for Map & HashMap for more information
    private final Map<EntryDetail, String> detailsMap = new HashMap<>();


    // adds a pair of EntryProperty, value. See HashMap.put() for more details
    public void putProperty(EntryProperty entryProperty, double value) {
        propertyToValuesMap.put(entryProperty, value);
    }

    // adds a pair of EntryDetail, detail. See HashMap.put() for more details
    public void putDetail(EntryDetail entryDetail, String detail) {
        detailsMap.put(entryDetail, detail);
    }

    // retrieves a value associated to a given EntryProperty. See HashMap.get() for more details
    public double getProperty(EntryProperty entryProperty) {
        return propertyToValuesMap.get(entryProperty);
    }

    // retrieves a detail associated to a given EntryDetail. See HashMap.get() for more details
    public String getDetail(EntryDetail entryDetail) {
        return detailsMap.get(entryDetail);
    }

    // gets the properties stored in the map. See HashMap.keySet() for more details
    public Set<EntryProperty> propertySet() {
        return propertyToValuesMap.keySet();
    }
}
