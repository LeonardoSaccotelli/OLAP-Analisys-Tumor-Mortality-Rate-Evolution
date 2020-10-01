package etl.transform.datatable;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class LocalityTest {

    Locality testLocality = new Locality("1","Puglia:Bari");

    @Test
    void getFIPSCounty() {
        assertEquals("1", testLocality.getFIPSCounty());
    }

    @Test
    void getLocalityName() {
        assertEquals("Puglia:Bari", testLocality.getLocalityName());
    }

    @Test
    void testToString() {
        assertEquals("Locality{" +
                "FIPSCounty='1" + '\'' +
                ", localityName='Puglia:Bari" + '\'' +
                '}',testLocality.toString());
    }

    @Test
    void testEquals() {
        Locality otherTestLocality = new Locality("1","Puglia:Bari");
        boolean res = testLocality.equals(otherTestLocality);
        assertTrue(res);
    }

    @Test
    void testEquals2() {
        Locality otherTestLocality = new Locality("2","Puglia:Bari");
        boolean res = testLocality.equals(otherTestLocality);
        assertFalse(res);
    }

    @Test
    void testEquals3() {
        Locality otherTestLocality = new Locality("1","Puglia:Brindisi");
        boolean res = testLocality.equals(otherTestLocality);
        assertFalse(res);
    }

    @Test
    void testEquals4() {
        Locality otherTestLocality = new Locality("2","Puglia:Brindisi");
        boolean res = testLocality.equals(otherTestLocality);
        assertFalse(res);
    }

    @Test
    void testEquals5() {
        Sex otherTestLocality = new Sex("1","Puglia:Brindisi");
        boolean res = testLocality.equals(otherTestLocality);
        assertFalse(res);
    }

    @Test
    void testHashCode() {
        int testHashCode = Objects.hash(testLocality.getFIPSCounty(),
                            testLocality.getLocalityName());
        int res = testLocality.hashCode();
        assertEquals(testHashCode,res);
    }
}