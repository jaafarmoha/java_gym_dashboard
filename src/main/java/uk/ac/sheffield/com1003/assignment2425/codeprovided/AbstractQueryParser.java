package uk.ac.sheffield.com1003.assignment2425.codeprovided;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// This abstract class parses the text file containing the queries and builds them using the class Query.
// You can not change the implementation of this class as it is part of a codeprovided package

public abstract class AbstractQueryParser {

    // receives a text file with a list of queries
    // returns a list of tokens that will be used to build the queries that need to be executed
    public static List<String> readQueryTokensFromFile(String queryFileLocation){
        List<String> queryTokens = new ArrayList<>();
        String split = " ";

        try (BufferedReader br = new BufferedReader(new FileReader(queryFileLocation))){
            String line = br.readLine();
            if (line == null) {
                System.err.println("Queries file is empty. No queries will be executed.");
            }
            while (line != null) {
                String[] query = line.toLowerCase().split(split);
                queryTokens.addAll(Arrays.asList(query));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println(queryFileLocation + " could not be found. No queries will be executed.");
        } catch (IOException e) {
            System.err.println("File could not be handled. No queries will be executed.");
            queryTokens.clear();
        }
        return queryTokens;
    }

    // builds a list of Query objects representing the queries in the list of tokens provided as an argument
    public abstract List<Query> buildQueries(List<String> queryTokens) throws IllegalArgumentException;
}
