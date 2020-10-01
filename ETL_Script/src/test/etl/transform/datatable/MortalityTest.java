package etl.transform.datatable;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MortalityTest {

    Mortality testMortality = new Mortality("1",2010,"1",
            "1",150,10000,50);
    @Test
    void getLocality() {
        assertEquals("1",testMortality.getLocality());
    }

    @Test
    void getYears() {
        assertEquals(2010,testMortality.getYears());
    }

    @Test
    void getSex() {
        assertEquals("1",testMortality.getSex());
    }

    @Test
    void getTumor() {
        assertEquals("1",testMortality.getTumor());
    }

    @Test
    void getMortalityRate() {
        assertEquals(150,testMortality.getMortalityRate());
    }

    @Test
    void getPopulationEstimate() {
        assertEquals(10000,testMortality.getPopulationEstimate());
    }

    @Test
    void getDeathsEstimate() {
        assertEquals(50,testMortality.getDeathsEstimate());
    }

    @Test
    void testEquals() {
        Mortality testOtherMortality = new Mortality("1",2010,"1",
                "1",150,10000,50);
        boolean res = testMortality.equals(testOtherMortality);
        assertTrue(res);
    }

    @Test
    void testEquals2() {
        Mortality testOtherMortality = new Mortality("1",2011,"1",
                "1",150,10000,50);
        boolean res = testMortality.equals(testOtherMortality);
        assertFalse(res);
    }

    @Test
    void testEquals3() {
        Mortality testOtherMortality = new Mortality("1",2010,"2",
                "1",150,10000,50);
        boolean res = testMortality.equals(testOtherMortality);
        assertFalse(res);
    }

    @Test
    void testEquals4() {
        Mortality testOtherMortality = new Mortality("1",2010,"1",
                "2",150,10000,50);
        boolean res = testMortality.equals(testOtherMortality);
        assertFalse(res);
    }

    @Test
    void testEquals5() {
        Mortality testOtherMortality = new Mortality("2",2011,"1",
                "1",150,10000,50);
        boolean res = testMortality.equals(testOtherMortality);
        assertFalse(res);
    }

    @Test
    void testEquals6() {
        Mortality testOtherMortality = new Mortality("2", 2011, "2",
                "1", 150, 10000, 50);
        boolean res = testMortality.equals(testOtherMortality);
        assertFalse(res);
    }

    @Test
    void testEquals7() {
        Mortality testOtherMortality = new Mortality("2", 2011, "2",
                "2", 150, 10000, 50);
        boolean res = testMortality.equals(testOtherMortality);
        assertFalse(res);
    }

    @Test
    void testEquals8() {
        String testOtherObject ="new String(Female)";
        boolean res = testMortality.equals(testOtherObject);
        assertFalse(res);
    }

    @Test
    void testHashCode() {
        int testHashCode = Objects.hash(testMortality.getLocality(),
                testMortality.getYears(),testMortality.getSex(),testMortality.getTumor());
        int res = testMortality.hashCode();
        assertEquals(testHashCode,res);
    }
}