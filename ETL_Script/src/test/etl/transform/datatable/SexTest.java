package etl.transform.datatable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SexTest {

    Sex testSex = new Sex("1","Male");

    @Test
    void getIDSex() {
        assertEquals("1",testSex.getIDSex());
    }

    @Test
    void getSexName() {
        assertEquals("Male",testSex.getSexName());
    }

    @Test
    void testToString() {
        assertEquals("Sex{" +
                "IDSex='1" + '\'' +
                ", SexName='Male"  + '\'' +
                '}',testSex.toString());
    }

    @Test
    void testEquals() {
        Sex testOtherSex = new Sex("1","Male");
        boolean res = testSex.equals(testOtherSex);
        assertTrue(res);
    }

    @Test
    void testEquals2() {
        Sex testOtherSex = new Sex("2","Male");
        boolean res = testSex.equals(testOtherSex);
        assertFalse(res);
    }


    @Test
    void testEquals3() {
        Sex testOtherSex = new Sex("1","Female");
        boolean res = testSex.equals(testOtherSex);
        assertFalse(res);
    }


    @Test
    void testEquals4() {
        Sex testOtherSex = new Sex("2","Female");
        boolean res = testSex.equals(testOtherSex);
        assertFalse(res);
    }


    @Test
    void testEquals5() {
        Tumor testOtherObject = new Tumor("2","Male");
        boolean res = testSex.equals(testOtherObject);
        assertFalse(res);
    }
}