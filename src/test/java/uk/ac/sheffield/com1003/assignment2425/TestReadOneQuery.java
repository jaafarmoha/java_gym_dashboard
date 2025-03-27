package uk.ac.sheffield.com1003.assignment2425;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import uk.ac.sheffield.com1003.assignment2425.codeprovided.Query;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TestReadOneQuery {

    /**
     * Test single query "
     * "
     */
    @Timeout(4000)
    @Test
    public void testReadOneQuery() {
        QueryParser queryParser = new QueryParser();
        List<String> tokens = tokenizeString("select entries where max_bpm > 100 and avg_bpm > 100 and resting_bpm < 100");
        List<Query> queries = queryParser.buildQueries(tokens);
        Assertions.assertEquals(1, queries.size());
        Assertions.assertEquals(3, queries.get(0).getSubQueryList().size());
    }

    private List<String> tokenizeString(String toTokenize) {
        List<String> tokens;
        toTokenize = toTokenize.replaceAll(Pattern.compile("\\s+").pattern(), " ");
        tokens = Arrays.asList(toTokenize.split(Pattern.compile(" ").pattern()));
        return tokens;
    }
}
