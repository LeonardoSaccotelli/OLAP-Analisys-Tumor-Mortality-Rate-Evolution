package etl.extract.clean;

import etl.data.CSVRecord;
import etl.utility.Utility;

/**
 * La classe gestisce le operazioni di pulizia
 * sul campo Year_ID.
 */
public class YearsDataCleaning {

    /**
     * Metodo utilizzato per analizzare il campo yearsID del dataset.
     * Sappiamo che l'anno è a 4 cifre, quindi il primo controllo riguarda
     * il numero di cifre. Se diverso da 4, il record va rimosso.
     * Il metodo verifica se il campo yearsID contenga stringhe numeriche,
     * ed eventualmente ne effettua il parsing.
     * Infine controlla che l'anno preso in analisi sia compreso fra 2000 e 2010,
     * diversamente i record vanno rimossi.
     * @param record record sottoposto all'analisi
     * @return true/false a seconda che il controllo sia superato o meno.
     */

    public static boolean checkYears(CSVRecord record){

        boolean flag = false;

        String testYears = record.getYearID();

        if(testYears.length() == 4) {

            if (Utility.stringIsDigit(testYears) && !testYears.contains(".")){
                int intTestYears = Integer.parseInt(testYears);

                if(intTestYears>= 2000 && intTestYears <= 2010 ){

                    flag = true;

                }
            }
        }

        return flag;
    }
}
