package etl.extract.clean;

import etl.data.CSVRecord;
import etl.data.State;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class StateDataCleaningTest {

    @Test
    void checkNameStateEmptyField() {
        CSVRecord csvRecordTest = new CSVRecord("1234","",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.12","12536");

        boolean res = StateDataCleaning.checkState(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkNameStateNullField(){
        assertThrows(NullPointerException.class,
                ()-> {
                    CSVRecord csvRecordTest = new CSVRecord("1234",null,
                            "12345","Bari", "2010", "2","Tumore Cervello",
                            "1","Uomo", "125.12","12536");
                    boolean res = StateDataCleaning.checkState(csvRecordTest);
                    assertFalse(res);
                });
    }

    @Test
    void checkIDStateEmptyField() {
        CSVRecord csvRecordTest = new CSVRecord("","Alabama",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.12","12536");

        boolean res = StateDataCleaning.checkState(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkIDStateNullField(){
        assertThrows(NullPointerException.class,
                ()-> {
                    CSVRecord csvRecordTest = new CSVRecord(null,"Alabama",
                            "12345","Bari", "2010", "2","Tumore Cervello",
                            "1","Uomo", "125.12","12536");
                    boolean res = StateDataCleaning.checkState(csvRecordTest);
                    assertFalse(res);
                });
    }

    @Test
    void checkStateNameWithRandomText() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String stateNameVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234",stateNameVarTest,
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.122","12222");

        boolean res = StateDataCleaning.checkState(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkStateFipsWithRandomText() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String stateNameVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord(stateNameVarTest,"Alabama",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.122","12222");

        boolean res = StateDataCleaning.checkState(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkStateAllData(){
        CSVRecord[] testAllRecord = new CSVRecord[50];

        State[] listState = State.values();

        for(int i = 0; i < listState.length; i++){
            testAllRecord[i] = new CSVRecord(listState[i].getFIPS(),listState[i].toString(),
                    "12345","Bari", "2010", "2","Tumore Cervello",
                    "1","Uomo", "125.122","12222" );
        }

        boolean res = true;

        for(CSVRecord record : testAllRecord){
            res = StateDataCleaning.checkState(record);

            if(!res){
                break;
            }
        }

        assertTrue(res);
    }

    @Test
    void checkNameStateWithPointAndSPace() {
        CSVRecord csvRecordTest = new CSVRecord("","A_L.a,ba_Ma",
                "12345","Bari", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.12","12536");

        boolean res = StateDataCleaning.checkState(csvRecordTest);
        assertFalse(res);
    }


}