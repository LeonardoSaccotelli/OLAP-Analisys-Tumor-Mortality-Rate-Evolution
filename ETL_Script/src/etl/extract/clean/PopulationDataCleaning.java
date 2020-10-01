package etl.extract.clean;

import etl.data.CSVRecord;
import etl.utility.Utility;

/**
 * Classe per la gestione delle operazioni di
 * pulitura del campo Popolazione.
 */
public class PopulationDataCleaning {

    /**
     * Metodo utilizzato per analizzare il campo population del dataset.
     * Il metodo verifica se il campo popoazione contenga stringhe numeriche,
     * ed eventualmente ne effettua il parsing.
     * Controlla quindi che il campo popolazione abbia un valore > 0
     * @param record Record sottoposto all'analisi
     * @return true/false a seconda che il controllo sia superato o meno.
     */
    public static boolean checkPopulation(CSVRecord record){

        boolean flag = false;

        String testPopulation = record.getPopulation();

        if (Utility.stringIsDigit(testPopulation) && !testPopulation.contains(".")) {
            int intPopulation = Integer.parseInt(testPopulation);

            if(intPopulation > 0) {
                flag = true;
            }
        }

        return flag;
    }
}
