package etl.extract.clean;

import etl.data.CSVRecord;
import etl.transform.datatable.Sex;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SexDataCleaningTest {

    @Test
    void checkSexEmptyField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","", "125.12","1253");

        boolean res=SexDataCleaning.checkSex(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkSexNullField(){
        assertThrows(NullPointerException.class,
                ()-> {
                    CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                            "12345","Bari", "2010", "2","Tumore Cervello",
                            "1",null, "125.12","1253");
                    boolean res = SexDataCleaning.checkSex(csvRecordTest);
                    assertFalse(res);
                });
    }

    @Test
    void checkSexWithRandomText() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String sexVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1",sexVarTest, "125.12","1253");

        boolean res = SexDataCleaning.checkSex(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkSexMaleField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Male", "125.12","1253");

        boolean res=SexDataCleaning.checkSex(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkSexMaleField_1() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","M,a_l e", "125.12","1253");

        boolean res=SexDataCleaning.checkSex(csvRecordTest);
        assertTrue(res);
    }


    @Test
    void checkSexMaleIdField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "3","Male", "125.12","1253");

        boolean res=SexDataCleaning.checkSex(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkSexFemaleField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "2","Female", "125.12","1253");

        boolean res=SexDataCleaning.checkSex(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkSexFemaleField_1() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "2","Fe?M,a_l e", "125.12","1253");

        boolean res=SexDataCleaning.checkSex(csvRecordTest);
        assertTrue(res);
    }


    @Test
    void checkSexFemaleIdField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Puglia",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "3","Female", "125.12","1253");

        boolean res=SexDataCleaning.checkSex(csvRecordTest);
        assertTrue(res);
    }

}