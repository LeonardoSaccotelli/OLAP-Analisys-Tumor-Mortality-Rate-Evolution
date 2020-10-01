package etl.extract;

import etl.data.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CSVReaderTest {

    @Test
    void readCSVFileWithNullFileOBject() {
        assertThrows(NullPointerException.class,
                ()-> {
                    File testFile = null;

                    CSVReader.readCSVFile(testFile);
                });
    }

    @Test
    void readCSVFileWithEmptyFile() {
        File testFile = new File(System.getProperty("user.dir") + "\\..\\TestingDataset\\testEmptyFile.csv");
        try {
            ArrayList<CSVRecord> dataOnFile = CSVReader.readCSVFile(testFile);
            assertEquals(0,dataOnFile.size());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void readCSVFileWithNotEmptyFile() {
        File testFile = new File(System.getProperty("user.dir") + "\\..\\TestingDataset\\testNotEmptyFile.csv");
        try {
            ArrayList<CSVRecord> dataOnFile = CSVReader.readCSVFile(testFile);
            assertEquals(42009,dataOnFile.size());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void readCSVFileWithEmptyRowsFile() {
        File testFile = new File(System.getProperty("user.dir") + "\\..\\TestingDataset\\testEmptyRowsFile.csv");
        try {
            ArrayList<CSVRecord> dataOnFile = CSVReader.readCSVFile(testFile);
            assertEquals(40434,dataOnFile.size());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void readCSVFileWithNumberOfFieldsNotValid() {
        File testFile = new File(System.getProperty("user.dir") + "\\..\\TestingDataset\\testFileWithNumberOfFieldsNotValid.csv");
        try {
            ArrayList<CSVRecord> dataOnFile = CSVReader.readCSVFile(testFile);
            assertEquals(40515,dataOnFile.size());
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Test
    void readCSVFileWithEmptyFields() {
        File testFile = new File(System.getProperty("user.dir") + "\\..\\TestingDataset\\tesEmptyFieldsFile.csv");
        try {
            ArrayList<CSVRecord> dataOnFile = CSVReader.readCSVFile(testFile);
            assertEquals(40525,dataOnFile.size());
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}