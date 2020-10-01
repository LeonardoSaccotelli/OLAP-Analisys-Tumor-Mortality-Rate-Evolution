package sample;

import etl.data.CSVRecord;
import etl.data.CleanedRecord;
import etl.data.County;
import etl.extract.CSVReader;
import etl.extract.DataCleaning;
import etl.load.LoadDataIntoDatabase;
import etl.transform.TransformRecord;
import etl.transform.datatable.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import etl.utility.Utility;

/**
 * La classe seguente defisce i metodi che dovranno essere eseguiti
 * su threads differenti dal thread di JavaFX, il quale si occuperà
 * solo della gestione della View.
 * In altre parole, impacchettiamo all'interno di un metodo
 * un'insieme di azioni da eseguire su uno specifico thread.
 */
public class ControllerBackgroundProcess {

    /**
     * Il metodo seguente si occupa di leggere il contenuto
     * dei file selezionati dall'utente.
     * @param textArea Area di testo in cui mostrare lo stato dell'elaborazione.
     * @param indexReadFile Indice dell'ultimo file letto all'interno di dataset.
     * @param selectedFiles Struttura contenente i file selezionati dall'utente.
     * @param dataSet Struttura contenente i record letti dai file, divisi per
     *                file. Struttura di puntatori a strutture di record.
     * @param flagTransform Flag che determina l'attivazione della fase di trasformazione.
     * @param importButton Pulsante che gestisce la fase di lettura.
     * @return il numero di file validi letti rispetto a quelli selezionati dall'utente.
     */
    public static int readFileProcess(TextArea textArea, int indexReadFile, ArrayList<File> selectedFiles,
                                      ArrayList<ArrayList<CSVRecord>> dataSet,
                                      boolean[] flagTransform, Button importButton) {

        /*
         * Per ogni file presente nella struttura, procedo alle lettura.
         * Attraverso il metodo readCSVFile della classe CSVReader
         * è sufficiente passare un oggrtto di tipo file per ottenere
         * un'arraylist di oggetti di tipo CSVRecords.
         */

        textArea.setText("");
        textArea.setText("READING FROM ...\n");

        int newFileRead = 0;

        for (int i = indexReadFile; i < selectedFiles.size(); i++) {
            try {
                try {
                    Utility.appendTextArea("- Reading from: " +selectedFiles.get(i).getName(),textArea );
                    //Lettura del file
                    ArrayList<CSVRecord> table = CSVReader.readCSVFile(selectedFiles.get(i));

                    //Se il file è stato letto, aggiungo alla lista di file letti
                    if (table != null) {
                        dataSet.add(table);
                        newFileRead++;
                    }

                } catch (FileNotFoundException e) {
                    Utility.showExceptionDialogBox(e);
                }
            } catch (IOException e) {
                Utility.showExceptionDialogBox(e);
            }
        }

        if(newFileRead > 0){
            textArea.appendText("FILES READ CORRECTLY\n");
            flagTransform[0] = true;

        }else{
            importButton.setDisable(false);
        }

        return newFileRead;
    }

    /**
     * Il metodo seguente si occupa di pulire i record letti dai file e di trasformarli
     * opportunamente in base alle scelte fatte per la realizzazione del DW.
     * @param textArea Area di testo in cui mostrare lo stato dell'elaborazione.
     * @param dataSet Struttura contenente i record letti dai file, divisi per
     *               file. Struttura di puntatori a strutture di record.
     * @param listOfCounty Struttura contente la lista delle contee americane, con FIPS e Stato
     * @param datasetCleaning Struttura contenente i record puliti divisi in base al file a cui appartengono.
     * @param flagExport flag che gestisce l'attivazione della fase di caricamento nel DB.
     * @param importButton Pulsante che gestisce la fase di lettura.
     * @param sexTable Tabella Dimensione Sesso
     * @param yearsTable Tabella Dimensione Anno
     * @param tumorTable Tabella Dimensione Tumore
     * @param localityTable Tabella Dimensione Località
     * @param mortalityTable Tabella Dimensione Mortalità
     * @param indexCleanedTransformFile Array contenente nella prima posizione l'indice dell'ultimo file pulito,
     *                                  nella seconda posizione l'indice dell'ultimo file trasformato.
     */
    public static void transform (TextArea textArea, ArrayList<ArrayList<CSVRecord>> dataSet,
                                 ArrayList<County> listOfCounty, ArrayList<ArrayList<CleanedRecord>> datasetCleaning,
                                 Button importButton, boolean[] flagExport,
                                 ArrayList<Sex> sexTable, ArrayList<Years> yearsTable,
                                 ArrayList<Tumor> tumorTable, ArrayList<Locality> localityTable,
                                 ArrayList<Mortality> mortalityTable, int[] indexCleanedTransformFile){


        textArea.appendText("\nCLEANING FILES ...\n");

        int indexElaboratedFile = indexCleanedTransformFile[0];

        int initialSizeDatasetCleaning = datasetCleaning.size();

        for (int i = indexCleanedTransformFile[0]; i < dataSet.size(); i++) {
            Utility.appendTextArea("- Cleaning " + (i - indexElaboratedFile +1) +" file of " + (dataSet.size() - indexElaboratedFile) , textArea);
            ArrayList<CleanedRecord> newCleanedDataset = DataCleaning.cleanDataset(dataSet.get(i), listOfCounty);

            if (newCleanedDataset.size() > 0) {
                datasetCleaning.add(newCleanedDataset);
                indexCleanedTransformFile[0]++;
                Utility.appendTextArea("  CLEANING STATUS: OK!\n",textArea);
            }else{
                Utility.appendTextArea("  CLEANING STATUS: FAIL (FILE NOT VALID)",textArea);
                dataSet.set(i, null);
            }
        }

        dataSet.removeIf(Objects::isNull);

        if ((datasetCleaning.size() - initialSizeDatasetCleaning) > 0){

            textArea.appendText("FILES CLEANED CORRECTLY\n");

            textArea.appendText("\nTRANSFORMING FILES ...\n");

            for (int i = indexCleanedTransformFile[1]; i < datasetCleaning.size(); i++) {
                Utility.appendTextArea("- Trasforming " +(i - indexElaboratedFile +1) + " file of " + (datasetCleaning.size() - indexElaboratedFile),textArea);
                TransformRecord.transformData(datasetCleaning.get(i), sexTable, yearsTable,
                        tumorTable, localityTable, mortalityTable);
                indexCleanedTransformFile[1]++;
            }

            textArea.appendText("FILES TRANSFORMED CORRECTLY\n");

            flagExport[0] = true;
        }else{
            Utility.appendTextArea("- CLEANING PROCESS STATUS: DATA NOT VALID!",textArea);
            importButton.setDisable(false);
        }
    }

    /**
     *
     * @param textArea Area di testo in cui mostrare lo stato dell'elaborazione.
     * @param importButton Pulsante che gestisce la fase di lettura.
     * @param connectionDbString oggetto contenente la connessione al database
     * @param sexTable Tabella Dimensione Sesso
     * @param yearTable Tabella Dimensione Anno
     * @param tumorTable Tabella Dimensione Tumore
     * @param localityTable Tabella Dimensione Località
     * @param mortalityTable Tabella Dimensione Mortalità
     */
    public static void loadingDataIntoDb(TextArea textArea, Button importButton,
                                         Connection connectionDbString,
                                         ArrayList<Sex> sexTable, ArrayList<Years> yearTable,
                                         ArrayList<Tumor> tumorTable, ArrayList<Locality> localityTable,
                                         ArrayList<Mortality> mortalityTable, long startTimer){

        textArea.appendText("\nEXPORTING TABLES ... Please wait\n");

        LoadDataIntoDatabase.loadData(textArea,connectionDbString,
                                            sexTable, yearTable,
                                            tumorTable,localityTable,
                                            mortalityTable);

        localityTable.clear();
        mortalityTable.clear();

        importButton.setDisable(false);

        CreateLogFile.writeLogFile("\n" +new Date().toString() + "\n"+textArea.getText() + "- Process ends in " +
                ((System.currentTimeMillis() - startTimer) * 0.0000167)  + " minutes\n"
        +"-------------------------------------------------------------------------------\n");
    }
}
