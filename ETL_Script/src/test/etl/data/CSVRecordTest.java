package etl.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVRecordTest {

    private CSVRecord csvRecordVar = new CSVRecord("1234","Puglia",
            "12345","Bari", "2010", "2","Tumore Cervello",
            "1","Uomo", "125.12","100500");

    @Test
    void getFIPS() {
        assertEquals("1234", csvRecordVar.getFIPS());
    }

    @Test
    void getNameState() {
        assertEquals("Puglia", csvRecordVar.getNameState());
    }

    @Test
    void getFIPSCounty() {
        assertEquals("12345", csvRecordVar.getFIPSCounty());
    }

    @Test
    void getNameCounty() {
        assertEquals("Bari", csvRecordVar.getNameCounty());
    }

    @Test
    void getYearID() {
        assertEquals("2010", csvRecordVar.getYearID());
    }

    @Test
    void getTumorID() {
        assertEquals("2", csvRecordVar.getTumorID());
    }

    @Test
    void getTypeTumor() {
        assertEquals("Tumore Cervello", csvRecordVar.getTypeTumor());
    }

    @Test
    void getSexID() {
        assertEquals("1", csvRecordVar.getSexID());
    }

    @Test
    void getSexName() {
        assertEquals("Uomo", csvRecordVar.getSexName());
    }

    @Test
    void getMortality() {
        assertEquals("125.12", csvRecordVar.getMortality());
    }

    @Test
    void getPopulation() {
        assertEquals("100500", csvRecordVar.getPopulation());
    }

    @Test
    void setNameState() {
        csvRecordVar.setNameState("Campania");
        assertEquals("Campania", csvRecordVar.getNameState());
    }

    @Test
    void setNameCounty() {
        csvRecordVar.setNameCounty("Napoli");
        assertEquals("Napoli", csvRecordVar.getNameCounty());
    }

    @Test
    void setTumorID() {
        csvRecordVar.setTumorID("3");
        assertEquals("3", csvRecordVar.getTumorID());
    }

    @Test
    void setTypeTumor() {
        csvRecordVar.setTypeTumor("Tumore Pancreas");
        assertEquals("Tumore Pancreas", csvRecordVar.getTypeTumor());
    }

    @Test
    void setSexID() {
        csvRecordVar.setSexID("2");
        assertEquals("2", csvRecordVar.getSexID());
    }

    @Test
    void setSexName() {
        csvRecordVar.setSexName("Donna");
        assertEquals("Donna", csvRecordVar.getSexName());
    }
}