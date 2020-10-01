package etl.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CleanedRecordTest {

    private final CleanedRecord cleanedRecord = new CleanedRecord("1234","Puglia",
                                "12345","Bari", 2010, "2","Tumore Cervello",
                                "1","Uomo", 125,100500);

    @Test
    void getNameState() throws Exception {
        assertEquals("Puglia", cleanedRecord.getNameState());
    }

    @Test
    void getFIPSCounty() {
        assertEquals("12345", cleanedRecord.getFIPSCounty());
    }

    @Test
    void getNameCounty() {
        assertEquals("Bari", cleanedRecord.getNameCounty());
    }

    @Test
    void getYearID() {
        assertEquals(2010, cleanedRecord.getYearID());
    }

    @Test
    void getTumorID() {
        assertEquals("2", cleanedRecord.getTumorID());
    }

    @Test
    void getTypeTumor() {
        assertEquals("Tumore Cervello", cleanedRecord.getTypeTumor());
    }

    @Test
    void getSexID() {
        assertEquals("1", cleanedRecord.getSexID());
    }

    @Test
    void getSexName() {
        assertEquals("Uomo", cleanedRecord.getSexName());
    }

    @Test
    void getMortality() {
        assertEquals(125, cleanedRecord.getMortality());
    }

    @Test
    void getPopulation() {
        assertEquals(100500, cleanedRecord.getPopulation());
    }
}