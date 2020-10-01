package etl.transform.datatable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TumorTest {
    Tumor testTumor = new Tumor("1","Tumore Pancreas");

    @Test
    void getIDTumor() {
        assertEquals("1",testTumor.getIDTumor());
    }

    @Test
    void getTumorName() {
        assertEquals("Tumore Pancreas",testTumor.getTumorName());
    }

    @Test
    void testToString() {
        assertEquals("Tumor{" +
                "IDTumor='1" + '\'' +
                ", TumorName='Tumore Pancreas"  + '\'' +
                '}',testTumor.toString());
    }

    @Test
    void testEquals() {
        Tumor testOtherTumor = new Tumor("1","Tumore Pancreas");
        boolean res = testTumor.equals(testOtherTumor);
        assertTrue(res);
    }


    @Test
    void testEquals2() {
        Tumor testOtherTumor = new Tumor("2","Tumore Pancreas");
        boolean res = testTumor.equals(testOtherTumor);
        assertFalse(res);
    }

    @Test
    void testEquals3() {
        Tumor testOtherTumor = new Tumor("1","Tumore Fegato");
        boolean res = testTumor.equals(testOtherTumor);
        assertFalse(res);
    }

    @Test
    void testEquals4() {
        Tumor testOtherTumor = new Tumor("2","Tumore Fegato");
        boolean res = testTumor.equals(testOtherTumor);
        assertFalse(res);
    }

    @Test
    void testEquals5() {
        String testOtherTumor = "1,Tumore Pancreas";
        boolean res = testTumor.equals(testOtherTumor);
        assertFalse(res);
    }

}