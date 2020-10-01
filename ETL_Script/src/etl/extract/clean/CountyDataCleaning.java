package etl.extract.clean;

import etl.data.CSVRecord;
import etl.data.County;
import etl.utility.Utility;
import java.util.ArrayList;

/**
 * La classe si occupa di gestire le operazioni di pulizia da eseguire
 * sui campi County_Name e FIPS_County.
 */
public class CountyDataCleaning {

    private static final int START_INDEX_COUNTY_LIST = 0;
    private static final int END_INDEX_COUNTY_LIST = 1;

    /**
     * Metodo che verifica lo status dei campi contenenti le
     * informazioni relative alla contea.
     * @param record Record da sottoporre a controllo.
     * @param listOfCounty Lista di contee da utilizzare ai fini del test.
     * @return true se i dati sulle contee presenti nel record in input sono valide,
     *          altrimenti false se non è valido.
     */
    public static boolean checkCounty(CSVRecord record, ArrayList<County> listOfCounty){

        boolean flag = false;
        boolean findCountyOk = false;

        int[] startEndIndex = new int[2];
        int indexCountyList;

        findStartEndIndexCountyList(record,listOfCounty,startEndIndex);

        indexCountyList = Utility.stringBinarySearch(listOfCounty.toArray(),record.getNameCounty(),
                            startEndIndex[START_INDEX_COUNTY_LIST], startEndIndex[END_INDEX_COUNTY_LIST]);

        if (indexCountyList != -1){
            findCountyOk = updateCountyName(record, listOfCounty.get(indexCountyList));
        }

        if(findCountyOk){
            if(record.getFIPSCounty().equals(listOfCounty.get(indexCountyList).getCountyFIPS())){
                flag = true;
            }
        }

        return flag;
    }

    /**
     * Metodo che verifica la correttezza del nome della contea, ed eventualmente
     * aggiornamento grammaticale se semanticamente il dato è esatto.
     * @param record Record contenente il campo da aggiornare.
     * @param checkCounty Oggetto Contea contenente le informazioni con cui aggiornare i dati nel record.
     * @return true, l'esito dell'aggiornamento sarà sempre positivo.
     */
    private static boolean updateCountyName(CSVRecord record, County checkCounty){
        String testCountyName = record.getNameCounty();

        if(!(testCountyName.equals(checkCounty.getNameCounty()))){
            record.setNameCounty(checkCounty.getNameCounty());
        }
        return true;
    }

    /**
     * IL metodo permette di individuare nella lista delle contee l'indice iniziale e finale
     * delle contee appartenenti allo stesso stato presente nel record.
     * @param record Record da sottoporre a controllo
     * @param listOfCounty Lista di contee da utilizzare ai fini del test.
     * @param startEndIndex Array di 2 elementi contenente nella prima posizione l'indice di partenza e
     *                      nella seconda posizione l'indice finale.
     */
    private static void findStartEndIndexCountyList(CSVRecord record, ArrayList<County> listOfCounty, int[] startEndIndex){

        for(int i = 0; i < listOfCounty.size(); i++){
            if(listOfCounty.get(i).getNameState().equals(record.getNameState())){
               startEndIndex[START_INDEX_COUNTY_LIST] = i;

                for(int j = startEndIndex[START_INDEX_COUNTY_LIST]; j < listOfCounty.size(); j++){
                    if(!(listOfCounty.get(j).getNameState().equals(record.getNameState()))){
                        startEndIndex[END_INDEX_COUNTY_LIST] = j - 1;
                        break;
                    }

                    if(j == 3140 ){
                        startEndIndex[END_INDEX_COUNTY_LIST] = 3140;
                    }
                }
                break;
            }
        }
    }
}