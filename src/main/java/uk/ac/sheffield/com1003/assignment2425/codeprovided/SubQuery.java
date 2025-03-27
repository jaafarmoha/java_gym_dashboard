package uk.ac.sheffield.com1003.assignment2425.codeprovided;

import java.util.Arrays;

// This class is designed to be used to create SubQuery objects that compose an individual Query
// You can not change the implementation of this class as it is part of a codeprovided package

public class SubQuery {
    public static final String[] VALID_OPERATORS = {"<", "<=", "=", ">=", ">", "!="};

    final EntryProperty entryProperty;
    final String operator;
    final double value;

    public SubQuery(EntryProperty entryProperty, String operator, double value) {
        this.entryProperty = entryProperty;
        this.operator = operator;
        this.value = value;
    }

    public EntryProperty getEntryProperty() {
        return entryProperty;
    }

    public String getOperator() {
        return operator;
    }

    public double getValue() {
        return value;
    }

    public String toString() {
        return this.getEntryProperty() + " " +
                this.getOperator() + " " +
                this.getValue();
    }

    // checks if the provided Entry satisfies this SubQuery, returns true if there is a match, false otherwise
    protected boolean entriesMatchesSubQuery(Entry entry) {
        EntryProperty entryProperty = getEntryProperty();
        double propertyValue = getValue();

        switch (getOperator()) {

            case ">":
                if (entry.getEntryProperty(entryProperty) > propertyValue)
                    return true;
                break;
            case ">=":
                if (entry.getEntryProperty(entryProperty) >= propertyValue)
                    return true;
                break;
            case "<":
                if (entry.getEntryProperty(entryProperty) < propertyValue)
                    return true;
                break;
            case "<=":
                if (entry.getEntryProperty(entryProperty) <= propertyValue)
                    return true;
                break;
            case "=":
                if (entry.getEntryProperty(entryProperty) == propertyValue)
                    return true;
                break;
            case "!=":
                if (entry.getEntryProperty(entryProperty) != propertyValue)
                    return true;
                break;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((operator == null) ? 0 : operator.hashCode());
        long temp;
        temp = Double.doubleToLongBits(value);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((entryProperty == null) ? 0 : entryProperty.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubQuery other = (SubQuery) obj;
        if (operator == null) {
            if (other.operator != null)
                return false;
        } else if (!operator.equals(other.operator))
            return false;
        if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
            return false;
        return entryProperty == other.entryProperty;
    }

    // helper method that checks if a string is a valid operator, returns true if it is, false otherwise
    public static boolean isValidOperator(String operatorToCheck) {
        return Arrays.asList(VALID_OPERATORS).contains(operatorToCheck);
    }
}
