package sample;

import etl.data.CSVRecord;
import etl.data.CleanedRecord;
import etl.data.County;
import etl.transform.datatable.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

public class FacadeControllerBackground {

    public static int facadeControllerBackgroundMethod(TextArea textArea, int indexReadFile, ArrayList<File> selectedFiles,
                            ArrayList<ArrayList<CSVRecord>> dataSet,
                            Button importButton,
                            ArrayList<County> listOfCounty, ArrayList<ArrayList<CleanedRecord>> datasetCleaning,
                            ArrayList<Sex> sexTable, ArrayList<Years> yearsTable,
                            ArrayList<Tumor> tumorTable, ArrayList<Locality> localityTable,
                            ArrayList<Mortality> mortalityTable, int[] indexCleanedTransformFile, boolean[] flagTransform, boolean[] flagExport,
                            Connection connectionDbString, Long startTimer){

       int indexRead = ControllerBackgroundProcess.readFileProcess(textArea,indexReadFile,selectedFiles,dataSet, flagTransform,importButton);


       if(flagTransform[0]){
           ControllerBackgroundProcess.transform(textArea,dataSet,listOfCounty,datasetCleaning,importButton,flagExport,
                   sexTable,yearsTable,tumorTable,localityTable,mortalityTable,indexCleanedTransformFile);

           if(flagExport[0]){
               ControllerBackgroundProcess.loadingDataIntoDb(textArea,importButton,connectionDbString,sexTable,yearsTable,tumorTable,localityTable,mortalityTable, startTimer);
           }
       }
        return indexRead;
    }

}
