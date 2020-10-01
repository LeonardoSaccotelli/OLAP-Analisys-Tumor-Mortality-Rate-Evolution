package etl.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountyTest {

    private final County countyVar = new County("Puglia","Bari","12345");

    @Test
    void getNameState() {
        assertEquals("Puglia", countyVar.getNameState());
    }

    @Test
    void getCountyFIPS() {
        assertEquals("12345", countyVar.getCountyFIPS());
    }

    @Test
    void getNameCounty() {
        assertEquals("Bari", countyVar.getNameCounty());
    }

    @Test
    void testToString() {
        assertEquals("Bari", countyVar.toString());
    }
}