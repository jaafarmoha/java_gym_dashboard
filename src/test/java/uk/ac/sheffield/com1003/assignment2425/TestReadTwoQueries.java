package uk.ac.sheffield.com1003.assignment2425;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Query;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TestReadTwoQueries {

    public static List<String> tokenizeString(String toTokenize) {
        List<String> tokens;
        toTokenize = toTokenize.replaceAll(Pattern.compile("\\s+").pattern(), " ");
        tokens = Arrays.asList(toTokenize.split(Pattern.compile(" ").pattern()));
        return tokens;
    }

    /**
     * Test the queries "
     * "
     */
    @Timeout(4000)
    @Test
    public void testReadTwoQueries() {
        QueryParser queryParser = new QueryParser();
        List<String> tokens = tokenizeString("select entries where avg_bpm > 30 \n select entries where max_bpm > 30 and resting_bpm > 40");
        List<Query> queries = queryParser.buildQueries(tokens);
        Assertions.assertEquals(2, queries.size());
        Assertions.assertEquals(1, queries.get(0).getSubQueryList().size());
        Assertions.assertEquals(2, queries.get(1).getSubQueryList().size());
    }
}
