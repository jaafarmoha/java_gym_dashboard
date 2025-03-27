package uk.ac.sheffield.com1003.assignment2425;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Query;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;


public class TestQueryParser {

    /**
     * Helper method to tokenize a String, by splitting a String by the presence of
     * whitespace
     *
     * @param toTokenize the String to tokenize.
     * @return A list of token Strings
     */
    public static List<String> tokenizeString(String toTokenize) {
        List<String> tokens;
        toTokenize = toTokenize.replaceAll(Pattern.compile("\\s+").pattern(), " ");
        tokens = Arrays.asList(toTokenize.split(Pattern.compile(" ").pattern()));
        return tokens;
    }

    @Test
    public void testReadSingleQuery() {
        QueryParser parser = new QueryParser();
        List<Query> queries = parser.buildQueries(tokenizeString("SELECT ENTRIES WHERE AGE > 60".toLowerCase()));
        Assertions.assertEquals(1, queries.size());
        Assertions.assertEquals(1, queries.get(0).getSubQueryList().size());
    }

}
