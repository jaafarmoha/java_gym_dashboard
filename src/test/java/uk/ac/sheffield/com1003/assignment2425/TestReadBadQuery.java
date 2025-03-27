package uk.ac.sheffield.com1003.assignment2425;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Query;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TestReadBadQuery {

    public static List<String> tokenizeString(String toTokenize) {
        List<String> tokens;
        toTokenize = toTokenize.replaceAll(Pattern.compile("\\s+").pattern(), " ");
        tokens = Arrays.asList(toTokenize.split(Pattern.compile(" ").pattern()));
        return tokens;
    }

    /**
     */
    @Timeout(4000)
    @Test
    public void testReadBadQuery() {
        QueryParser queryParser = new QueryParser();
        List<String> tokens = tokenizeString("select entries where NOISE &= none");
        try {
            List<Query> queries = queryParser.buildQueries(tokens);
            Assertions.assertTrue(queries.isEmpty()); // This is one correct solution
        } catch (IllegalArgumentException ex) {
            // This is another correct solution
        }
    }
}
