package etl.extract;

import etl.data.CSVRecord;
import etl.data.CleanedRecord;
import etl.data.County;
import etl.extract.clean.*;

import java.util.ArrayList;

/**
 * La classe si occupa di gestire la fase di pulizia dello script
 * ETL. In particolare, fornisce un metodo che centralizza l'utilizzo
 * di una serie di operazioni di pulizia a cui sottoporre i record
 * prelevati dai dataset.
 */
public class DataCleaning {
    /**
     * Metodo che centralizza le richieste di pulizia a cui sottoporre
     * ciascun record prelevato dai dataset caricati dall'utente.
     * @param dataDirty Lista di record rappresentante un singolo file caricato dall'utente.
     * @param listOfCounty Lista contenente oggetti di tipo County utilizzato nella fase di pulizia.
     * @return Una lista di oggetti CleanedRecord rappresentante i record del file di partenza pulito correttamente.
     */
    public static ArrayList<CleanedRecord> cleanDataset(ArrayList<CSVRecord> dataDirty, ArrayList<County> listOfCounty){

        ArrayList<CleanedRecord> cleanedDataset = new ArrayList<>();

        /*
         * Per ogni record presente nel dataset ricevuto,
         * sottopongo il record a vari controlli e
         * riparo il record laddove ci fossero errori.
         */
        for (CSVRecord csvRecord : dataDirty) {
            MortalityDataCleaning.checkMortality(csvRecord);
            PopulationDataCleaning.checkPopulation(csvRecord);
            YearsDataCleaning.checkYears(csvRecord);
            StateDataCleaning.checkState(csvRecord);
            CountyDataCleaning.checkCounty(csvRecord,listOfCounty);
            SexDataCleaning.checkSex(csvRecord);
            TumorDataCleaning.checkTumor(csvRecord);

            CleanedRecord allCleanedRecord = new CleanedRecord(csvRecord.getFIPS(), csvRecord.getNameState(),
                    csvRecord.getFIPSCounty(), csvRecord.getNameCounty(),
                    Integer.parseInt(csvRecord.getYearID()), csvRecord.getTumorID(), csvRecord.getTypeTumor(),
                    csvRecord.getSexID(), csvRecord.getSexName(),
                    Float.parseFloat(csvRecord.getMortality()), Integer.parseInt((csvRecord.getPopulation())));

            cleanedDataset.add(allCleanedRecord);
        }

        return cleanedDataset;

    }
}