/**
 * QueryParser.java
 * @version 1.1 16/05/2025
 * @author Jaafar Mohammed
 */

package uk.ac.sheffield.com1003.assignment2425;

import uk.ac.sheffield.com1003.assignment2425.codeprovided.AbstractQueryParser;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Query;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.EntryProperty;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.SubQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class, a QueryParser class which extends
 * AbstractQueryParser
 */
public class QueryParser extends AbstractQueryParser {
    /**
     * Method, builds queries from a list of query tokens
     * @param queryTokens Each word in the query in a list
     * @return  A list of built queries
     * @throws IllegalArgumentException If the supplied argument
     * isn't in the necessary format
     */
    @Override
    public List<Query> buildQueries(List<String> queryTokens) throws IllegalArgumentException {
        List<Query> queries = new ArrayList<>();

        // Confirm if entry starts with "select entries where"
        int i = 0;
        while (i < queryTokens.size()) {
            if (!queryTokens.get(i).equalsIgnoreCase("select") ||
            !queryTokens.get(i + 1).equalsIgnoreCase("entries") ||
            !queryTokens.get(i + 2).equalsIgnoreCase("where")) {
                throw new IllegalArgumentException("Queries must start with: select entries where");
            }

            // Skip those 3 tokens
            i += 3;

            List<SubQuery> subQueries = new ArrayList<>();
            while (i + 2 < queryTokens.size()) {
                if (queryTokens.get(i).equalsIgnoreCase("select")) { // Checking if query continues
                    break;
                }

                try {
                    EntryProperty subQueryProperty = EntryProperty.valueOf(queryTokens.get(i).toUpperCase());
                    String subQueryOperator = queryTokens.get(i + 1);
                    double subQueryValue = Double.parseDouble(queryTokens.get(i + 2));
                    SubQuery subQuery = new SubQuery(subQueryProperty, subQueryOperator, subQueryValue);
                    subQueries.add(subQuery);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid subquery starting at token: " + queryTokens.get(i));
                }

                i += 3;

                if (i < queryTokens.size() && queryTokens.get(i).equalsIgnoreCase("and")) {
                    i++;
                }
            }

            queries.add(new Query(subQueries));
        }

        return queries;
    }

}
