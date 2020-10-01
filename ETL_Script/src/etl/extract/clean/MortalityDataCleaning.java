package etl.extract.clean;

import etl.data.CSVRecord;
import etl.utility.Utility;

/**
 * Classe che si occupa della gestione delle operazioni
 * di pulitura sul campo mortalità.
 */
public class MortalityDataCleaning {

    /**
     * Metodo utilizzato per analizzare il campo mortality del dataset.
     * Il metodo verifica se il campo mortalità contenga stringhe numeriche,
     * ed eventualmente ne effettua il parsing.
     * @param record Record sottoposto all'analisi.
     * @return true/false a seconda che il controllo sia superato o meno.
     */
    public static boolean checkMortality(CSVRecord record) {

        boolean flag = false;

        String testMortality = record.getMortality();

        if (Utility.stringIsDigit(testMortality)) {

            float floatTestMortality = Float.parseFloat(testMortality);

            if (floatTestMortality>=0) {
                flag = true;
            }
        }
        return flag;
    }
}