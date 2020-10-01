package etl.extract.clean;

import etl.data.CSVRecord;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MortalityDataCleaningTest {

    @Test
    void checkMortalityEmptyField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", "","100500");

        boolean res = MortalityDataCleaning.checkMortality(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkMortalityNullField(){
        assertThrows(NullPointerException.class,
                ()-> {
                    CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                            "12345", "Bari", "2010", "2", "Tumore Cervello",
                            "1", "Uomo", null, "100500");
                    boolean res = MortalityDataCleaning.checkMortality(csvRecordTest);
                    assertFalse(res);
                });
    }

    @Test
    void checkMortalityWithPositiveFloat() {

        String mortalityTestVar = Double.toString(Math.random() * 500);

        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", mortalityTestVar,"100500");

        boolean res = MortalityDataCleaning.checkMortality(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkMortalityWithNegativeFloat() {

        String mortalityTestVar = Double.toString(-(Math.random() * 500));

        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", mortalityTestVar,"100500");

        boolean res = MortalityDataCleaning.checkMortality(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkMortalityWithPositiveInt() {

        Random random = new Random();
        String mortalityTestVar = Integer.toString(random.nextInt(1000));

        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", mortalityTestVar,"100500");

        boolean res = MortalityDataCleaning.checkMortality(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkMortalityWithText() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String mortalityTestVar = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", mortalityTestVar,"100500");

        boolean res = MortalityDataCleaning.checkMortality(csvRecordTest);
        assertFalse(res);
    }
}