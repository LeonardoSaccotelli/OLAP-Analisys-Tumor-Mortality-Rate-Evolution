package etl.extract.clean;

import etl.data.CSVRecord;
import etl.data.County;
import etl.extract.ReadCountiesFile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CountyDataCleaningTest {

    ArrayList<County> listOfCounty = ReadCountiesFile.readCountyFile();

    @Test
    void checkCountyEmptyFipsCounty() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "","Bullock County", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.12","12536");

        boolean res = StateDataCleaning.checkState(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkCountyEmptyNameCounty() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "1011","", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.12","12536");

        boolean res = StateDataCleaning.checkState(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkCountyNullFipsCounty() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                null,"Bullock County", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.12","12536");

        boolean res = StateDataCleaning.checkState(csvRecordTest);
        assertFalse(res);
    }

    @Test
    void checkCountyNullNameCounty() {
        assertThrows(NullPointerException.class,
                ()-> {
                    CSVRecord csvRecordTest = new CSVRecord("1234", "Alabama",
                            "1011", null, "2010", "2", "Tumore Cervello",
                            "1", "Uomo", "125.12", "12536");

                    boolean res = CountyDataCleaning.checkCounty(csvRecordTest,listOfCounty);
                    assertFalse(res);
                });
    }

    @Test
    void checkCountyEmptyNameSate() {
        CSVRecord csvRecordTest = new CSVRecord("1234","",
                "1011","Bullock County", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.12","12536");

        boolean res = CountyDataCleaning.checkCounty(csvRecordTest,listOfCounty);
        assertFalse(res);
    }

    @Test
    void checkCountyNullNameState() {
        CSVRecord csvRecordTest = new CSVRecord("1234", null,
                "1011", "Bullock County", "2010", "2", "Tumore Cervello",
                "1", "Uomo", "125.12", "12536");

        boolean res = CountyDataCleaning.checkCounty(csvRecordTest,listOfCounty);
        assertFalse(res);
    }

    @Test
    void checkCountyListValidInput(){
        CSVRecord[] testAllRecord = new CSVRecord[3141];

        for(int i = 0; i < listOfCounty.size(); i++){
            testAllRecord[i] = new CSVRecord("1", listOfCounty.get(i).getNameState(),
                    listOfCounty.get(i).getCountyFIPS(),listOfCounty.get(i).getNameCounty(), "2010", "2","Tumore Cervello",
                    "1","Uomo", "125.122","12222" );
        }

        boolean res = true;

        for(CSVRecord record : testAllRecord){
            res = CountyDataCleaning.checkCounty(record,listOfCounty);

            if(!res){
                break;
            }
        }
        assertTrue(res);
    }

    @Test
    void checkNameCountyWithPointAndSPace() {
        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "1011","B?u_l, l. o= c..k Co!%u_nty", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.12","12536");

        boolean res = CountyDataCleaning.checkCounty(csvRecordTest,listOfCounty);
        assertTrue(res);
    }

    @Test
    void checkCountyNameWithRandomText() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String countyNameVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                "1011",countyNameVarTest, "2010", "2","Tumore Cervello",
                "1","Uomo", "125.122","12222");

        boolean res = CountyDataCleaning.checkCounty(csvRecordTest,listOfCounty);
        assertFalse(res);
    }


    @Test
    void checkCountyFIPSWithRandomText() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String countyFIPSVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234","Alabama",
                countyFIPSVarTest,"Bullock County", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.122","12222");

        boolean res = CountyDataCleaning.checkCounty(csvRecordTest,listOfCounty);
        assertFalse(res);
    }

    @Test
    void checkNameStateWithRandomText() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String nameStateVarTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        CSVRecord csvRecordTest = new CSVRecord("1234",nameStateVarTest,
                "1011","Bullock County", "2010", "2","Tumore Cervello",
                "1","Uomo", "125.122","12222");

        boolean res = CountyDataCleaning.checkCounty(csvRecordTest,listOfCounty);
        assertFalse(res);
    }

}