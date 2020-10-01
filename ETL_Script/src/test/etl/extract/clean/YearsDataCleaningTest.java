package etl.extract.clean;

import etl.data.CSVRecord;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class YearsDataCleaningTest {

    @Test
    void checkYearsWithEmptyFIeld() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "", "2","Tumore Cervello",
                "1","Uomo", "125.12","1000");

        boolean res = YearsDataCleaning.checkYears(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkYearsNullField(){
        assertThrows(NullPointerException.class,
                ()-> {
                    CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                            "12345", "Bari", null, "2", "Tumore Cervello",
                            "1", "Uomo", "125.12", "10000");
                    boolean res = YearsDataCleaning.checkYears(csvRecordTest);
                    assertFalse(res);
                });
    }

    @Test
    void checkYearsWithFiveChars() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        String yearVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                "12345", "Bari", yearVarTest, "2", "Tumore Cervello",
                "1", "Uomo", "125.122", "10256");

        boolean res = YearsDataCleaning.checkYears(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkYearsWithThreeChars() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 3;
        Random random = new Random();

        String yearVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                "12345", "Bari", yearVarTest, "2", "Tumore Cervello",
                "1", "Uomo", "125.122", "10256");

        boolean res = YearsDataCleaning.checkYears(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkYearsWithFourChars() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 4;
        Random random = new Random();

        String yearVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                "12345", "Bari", yearVarTest, "2", "Tumore Cervello",
                "1", "Uomo", "125.122", "10256");

        boolean res = YearsDataCleaning.checkYears(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkYearsValid() {
        Random random = new Random();

        String yearVarTest = Integer.toString(random.nextInt(11) +2000);

        CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                "12345", "Bari", yearVarTest, "2", "Tumore Cervello",
                "1", "Uomo", "125.122", "10256");

        boolean res = YearsDataCleaning.checkYears(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkYearsUnderValidValue() {
        Random random = new Random();

        String yearVarTest = "1999";

        CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                "12345", "Bari", yearVarTest, "2", "Tumore Cervello",
                "1", "Uomo", "125.122", "10256");

        boolean res = YearsDataCleaning.checkYears(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkYearsOverValidValue() {
        Random random = new Random();

        String yearVarTest = "2011";

        CSVRecord csvRecordTest = new CSVRecord("1234", "Puglia",
                "12345", "Bari", yearVarTest, "2", "Tumore Cervello",
                "1", "Uomo", "125.122", "10256");

        boolean res = YearsDataCleaning.checkYears(csvRecordTest);
        assertFalse(res);
    }

}