package etl.extract.clean;

import etl.data.CSVRecord;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PopulationDataCleaningTest {

    @Test
    void checkPopulationEmptyField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.12","");

        boolean res = PopulationDataCleaning.checkPopulation(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkPopulationNullField(){
        assertThrows(NullPointerException.class,
                ()-> {
                    CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                            "12345", "Bari", "2010", "2", "Tumore Cervello",
                            "1", "Uomo", "125.12", null);
                    boolean res = PopulationDataCleaning.checkPopulation(csvRecordTest);
                    assertFalse(res);
                });
    }

    @Test
    void checkPopulationWithText() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String populationVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.122",populationVarTest);

        boolean res = PopulationDataCleaning.checkPopulation(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkPopulationWithNegativeFloat() {
        String populationTestVar = Double.toString(-(Math.random() * 1000000)+1);

        CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                "12345", "Bari", "2010", "2", "Tumore Cervello",
                "1", "Uomo", "125.55", populationTestVar);

        boolean res = PopulationDataCleaning.checkPopulation(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkPopulationWithNegativeInt() {
        Random random = new Random();
        String populationTestVar = Integer.toString(-(random.nextInt(100000000)+1));

        CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                "12345", "Bari", "2010", "2", "Tumore Cervello",
                "1", "Uomo", "125.55", populationTestVar);

        boolean res = PopulationDataCleaning.checkPopulation(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkPopulationWithPositiveInt() {
        Random random = new Random();
        String populationTestVar = Integer.toString(random.nextInt(100000000));

        CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                "12345", "Bari", "2010", "2", "Tumore Cervello",
                "1", "Uomo", "125.55", populationTestVar);

        boolean res = PopulationDataCleaning.checkPopulation(csvRecordTest);
        assertTrue(res);
    }
}