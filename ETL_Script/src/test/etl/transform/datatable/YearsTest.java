package etl.transform.datatable;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class YearsTest {
    Random random = new Random();
    int yearTest = random.nextInt(2500)+1;
    Years yearVar = new Years(yearTest);

    @Test
    void getYear() {
        assertEquals(yearTest,yearVar.getYear());
    }

    @Test
    void testToString() {
        assertEquals(Integer.toString(yearTest),yearVar.toString());
    }

    @Test
    void testEqualsSameObject() {
        Years testEqulasYears = new Years(yearTest);
        boolean res = yearVar.equals(testEqulasYears);
        assertTrue(res);
    }

    @Test
    void testEqualsSameObjectDifferentValue() {
        Years testEqulasYears = new Years(random.nextInt(2500)+1);
        boolean res = yearVar.equals(testEqulasYears);
        assertFalse(res);
    }

    @Test
    void testEqualsDifferentObject() {
        String testEqulasYears = Integer.toString(yearTest);
        boolean res = yearVar.equals(testEqulasYears);
        assertFalse(res);
    }
    }