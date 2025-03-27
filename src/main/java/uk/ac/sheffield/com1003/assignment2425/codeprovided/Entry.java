package uk.ac.sheffield.com1003.assignment2425.codeprovided;

import java.util.Iterator;

// This class is used to create objects from entries.
// You can not change the implementation of this class as it is part of a codeprovided package

public class Entry {
    private final int id;

    private final EntryPropertyMap entryPropertyMap;

    public Entry(int id, EntryPropertyMap entryPropertyMap) {
        this.id = id;
        this.entryPropertyMap = entryPropertyMap;
    }

    public int getId() {
        return id;
    }

    public EntryPropertyMap getEntryPropertyMap() {
        return entryPropertyMap;
    }

    public String getEntryDetail(EntryDetail entryDetail) {
        return entryPropertyMap.getDetail(entryDetail);
    }

    public double getEntryProperty(EntryProperty entryProperty) {
        return entryPropertyMap.getProperty(entryProperty);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entry{");

        sb.append("ID=");
        sb.append(getId());
        sb.append(", ");

        Iterator<EntryProperty> propertyIterator = entryPropertyMap.propertySet().iterator();
        while (propertyIterator.hasNext()) {
            EntryProperty p = propertyIterator.next();
            sb.append(p.getName());
            sb.append("=");
            sb.append(getEntryProperty(p));

            if (propertyIterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
