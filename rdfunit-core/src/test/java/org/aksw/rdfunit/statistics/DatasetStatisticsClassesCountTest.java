package org.aksw.rdfunit.statistics;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotEquals;

/**
 * Description
 *
 * @author Dimitris Kontokostas
 * @since 6/27/15 6:52 PM
 */
public class DatasetStatisticsClassesCountTest extends DatasetStatisticsTest {

    private static final int EXPECTED_ITEMS = 2;

    @Override
    protected int getExteptedItems() {
        return EXPECTED_ITEMS;
    }

    @Override
    protected DatasetStatistics getStatisticsObject() {
        return new DatasetStatisticsClassesCount();
    }

    @Test
    public void testGetStats() throws Exception {

        for (Map.Entry<String, Integer> entry : executeBasicTest().entrySet()) {
            assertNotEquals(new Integer(0), entry.getValue());
        }
    }
}