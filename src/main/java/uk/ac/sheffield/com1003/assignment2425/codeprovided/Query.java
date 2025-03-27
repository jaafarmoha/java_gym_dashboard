package uk.ac.sheffield.com1003.assignment2425.codeprovided;

import java.util.*;

// This class is designed to be used to create Query objects from the Query List
// You can not change the implementation of this class as it is part of a codeprovided package

public class Query {

    final List<SubQuery> subQueryList;

    // subQueryList contains a list of subqueries
    public Query(List<SubQuery> subQueryList) {
        this.subQueryList = subQueryList;
    }

    // returns the list of subqueries
    public List<SubQuery> getSubQueryList() {
        return subQueryList;
    }

    // applies the query to the entryCatalog provided and returns the list of filtered entries matching the query
    public List<Entry> executeQuery(AbstractEntryCatalog entryCatalog) {
        // Start by adding all the entries with the matching type
        List<Entry> filteredEntriesList =
                new ArrayList<>(entryCatalog.getEntriesList());

        // Continuously filter the entries according to each SubQuery
        for (SubQuery subQuery : subQueryList) {
            filteredEntriesList = executeSubQuery(filteredEntriesList, subQuery);
        }
        // Return the filtered entries
        return filteredEntriesList;
    }

    // filters the provided entries using the subQuery, and returns the filtered entries matching the subquery
    private List<Entry> executeSubQuery(Collection<Entry> entries, SubQuery subQuery) {
        List<Entry> filteredEntriesList = new ArrayList<>();

        for (Entry w : entries) {
            if (subQuery.entriesMatchesSubQuery(w))
                filteredEntriesList.add(w);
        }
        return filteredEntriesList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((subQueryList == null) ? 0 : subQueryList.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return Objects.equals(subQueryList, query.subQueryList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Query: ").append("{");
        Iterator<SubQuery> subQueryIterator = subQueryList.iterator();
        while (subQueryIterator.hasNext()) {
            sb.append(subQueryIterator.next());
            if (subQueryIterator.hasNext())
                sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}