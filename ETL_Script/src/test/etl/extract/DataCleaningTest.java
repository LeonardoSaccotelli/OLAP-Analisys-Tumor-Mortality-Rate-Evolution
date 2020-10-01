package etl.extract;

import etl.data.CSVRecord;
import etl.data.County;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DataCleaningTest {

    ArrayList<County> listOfCounty = ReadCountiesFile.readCountyFile();

    @Test
    void cleanDatasetWithNullParameter1() {
        assertThrows(NullPointerException.class,
                ()-> {
                    ArrayList<CSVRecord> dataDirtyTest = null;
                    ArrayList<County> listOfCounty = new ArrayList<>();

                    DataCleaning.cleanDataset(dataDirtyTest,listOfCounty);
                });
    }

    @Test
    void cleanDatasetWithAllCleanRows(){
        File testFile = new File(System.getProperty("user.dir") + "\\TestingDataset\\testNotEmptyFile.csv");
        ArrayList<CSVRecord> dataTest = new ArrayList<>();

        try {
            dataTest = CSVReader.readCSVFile(testFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        int numberOfValidRows = DataCleaning.cleanDataset(dataTest, listOfCounty).size();

        assertEquals(42009,numberOfValidRows);
    }


    @Test
    void cleanDatasetWithWrongState(){
        File testFile = new File(System.getProperty("user.dir") + "\\TestingDataset\\testDataCleaning\\testNotValidState.csv");
        ArrayList<CSVRecord> dataTest = new ArrayList<>();

        try {
            dataTest = CSVReader.readCSVFile(testFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        int numberOfValidRows = DataCleaning.cleanDataset(dataTest, listOfCounty).size();

        assertEquals(40535,numberOfValidRows);  //3 righe da rimuovere
    }

    @Test
    void cleanDatasetWithWrongCounty(){
        File testFile = new File(System.getProperty("user.dir") + "\\TestingDataset\\testDataCleaning\\testNotValidCounty.csv");
        ArrayList<CSVRecord> dataTest = new ArrayList<>();

        try {
            dataTest = CSVReader.readCSVFile(testFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        ArrayList<County> listOfCounty = ReadCountiesFile.readCountyFile();

        int numberOfValidRows = DataCleaning.cleanDataset(dataTest, listOfCounty).size();

        assertEquals(40535,numberOfValidRows);
    }

    @Test
    void cleanDatasetWithWrongSex(){
        File testFile = new File(System.getProperty("user.dir") + "\\TestingDataset\\testDataCleaning\\testNotValidSex.csv");
        ArrayList<CSVRecord> dataTest = new ArrayList<>();

        try {
            dataTest = CSVReader.readCSVFile(testFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        ArrayList<County> listOfCounty = ReadCountiesFile.readCountyFile();

        int numberOfValidRows = DataCleaning.cleanDataset(dataTest, listOfCounty).size();

        assertEquals(40535,numberOfValidRows);
    }


    @Test
    void cleanDatasetWithWrongTumor(){
        File testFile = new File(System.getProperty("user.dir") + "\\TestingDataset\\testDataCleaning\\testNotValidTumor.csv");
        ArrayList<CSVRecord> dataTest = new ArrayList<>();

        try {
            dataTest = CSVReader.readCSVFile(testFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        ArrayList<County> listOfCounty = ReadCountiesFile.readCountyFile();

        int numberOfValidRows = DataCleaning.cleanDataset(dataTest, listOfCounty).size();

        assertEquals(40535,numberOfValidRows);
    }


}