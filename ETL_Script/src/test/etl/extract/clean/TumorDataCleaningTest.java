package etl.extract.clean;

import etl.data.CSVRecord;
import etl.data.Tumor;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TumorDataCleaningTest {

    @Test
    void checkTumorIdEmptyField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "12345","Bari", "2010", "","Liver cancer",
                "1","Uomo", "125.12","12536");

        boolean res = TumorDataCleaning.checkTumor(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkTumorIdNullField() {
        CSVRecord csvRecordTest = new CSVRecord("1234", "Alabama",
                "12345", "Bari", "2010", null, "Liver cancer",
                "1", "Uomo", "125.12", "12536");

        boolean res = TumorDataCleaning.checkTumor(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkTumorNameEmptyField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "12345","Bari", "2010", "1","",
                "1","Uomo", "125.12","12536");

        boolean res = TumorDataCleaning.checkTumor(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkTumorNameNullField() {
        assertThrows(NullPointerException.class,
                ()-> {
                    CSVRecord csvRecordTest = new CSVRecord("1234", "Alabama",
                            "12345", "Bari", "2010", "1", null,
                            "1", "Uomo", "125.12", "12536");

                    boolean res = TumorDataCleaning.checkTumor(csvRecordTest);
                    assertFalse(res);
                });
    }

    @Test
    void checkTumorWithRandomStringInTypeTumor(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String tumorNameVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "12345","Bari", "2010", "2",tumorNameVarTest,
                "1","Uomo", "125.122","12222");

        boolean res = TumorDataCleaning.checkTumor(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkTumorWithValidSex(){
        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "12345","Bari", "2010", "1","Ovarian cancer",
                "2","Female", "125.12","12536");

        boolean res = TumorDataCleaning.checkTumor(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkTumorWithNotValidSex(){
        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "12345","Bari", "2010", "1","Ovarian cancer",
                "1","Male", "125.12","12536");

        boolean res = TumorDataCleaning.checkTumor(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkTumorWithNotCorrectTumorType(){
        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "12345","Bari", "2010", "1","Ovar_ia,n c anc?er",
                "2","Female", "125.12","12536");

        boolean res = TumorDataCleaning.checkTumor(csvRecordTest);
        assertTrue(res);
    }

    @Test
    void checkTumorAllValidData(){
        CSVRecord[] listValidData = new CSVRecord[31];
        Tumor[] listValidTumor = Tumor.values();

        for(int i = 0; i < listValidTumor.length; i++){
            listValidData[i] = new CSVRecord("1234","Alabama",
                    "12345","Bari", "2010", listValidTumor[i].getIDTumor(),listValidTumor[i].toString(),
                    "1",listValidTumor[i].getTumorSexName(), "125.122","12222" );
        }

        boolean res = true;

        for(CSVRecord record : listValidData){
            res = TumorDataCleaning.checkTumor(record);

            if(!res){
                break;
            }
        }
        assertTrue(res);
    }
}