package etl.extract.clean;

import etl.data.CSVRecord;

/**
 * sul campo SexName e SexId.
 */
public class SexDataCleaning {

    private static final String SEX_ID_MALE = "1" ;
    private static final String SEX_ID_FEMALE = "2";
    private static final String SEX_NAME_MALE = "Male";
    private  static final String SEX_NAME_FEMALE = "Female";

    /**
     * Il metodo è utilizzato per analizzare i campi sexName e sexID.
     * Vengono effettuati controlli di varia natura:
     * 1) Contorllo se il campo sexName contiene le parole 'Male'/'Female'
     *    - Sì --> Il controllo è superato, controllo ed eventualmente aggiorno i sexID
     *    - No :
     *          2) Pulisco la stringa, rimuovo spazi e punteggiatura, ignoro il maiuscolo/minuscolo
     *             Contiene 'Male'/'Female' ?
     *                  - Sì -> Aggiorno sexName e sexID (se occorre)
     *                  - No -> La stringa non è riparabile, determina la perdita del record.
     *
     * @param record record da analizzare
     * @return true/false esito dell'analisi
     */
    public static boolean checkSex(CSVRecord record){

        boolean flag = false;

        String testSexName = record.getSexName();

        if(!(testSexName.equals(SEX_NAME_MALE) || testSexName.equals(SEX_NAME_FEMALE))) {

            testSexName = testSexName.replaceAll("\\p{Punct}", "").replaceAll("\\s", "");

            if (testSexName.equalsIgnoreCase(SEX_NAME_MALE) || testSexName.equalsIgnoreCase(SEX_NAME_FEMALE)) {
                updateSexName(testSexName, record);
                flag = true;
            }
        }else{
            flag = true;
        }

        if(flag) {
            if (!((record.getSexID().equals(SEX_ID_MALE) && record.getSexName().equals(SEX_NAME_MALE)) ||
                    ((record.getSexID().equals(SEX_ID_FEMALE) && record.getSexName().equals(SEX_NAME_FEMALE))))) {
                updateSexId(record);
            }
        }

        return flag;
    }

    /**
     * Il metodo è utilizzato per aggiornare il
     * campo sexID dell'oggetto record.
     * @param record Record sottoposto ad analisi
     */
    private static void updateSexId(CSVRecord record){

        String testSexName = record.getSexName();

        if(testSexName.equals(SEX_NAME_MALE)){
           record.setSexID(SEX_ID_MALE);
        }else if (testSexName.equals(SEX_NAME_FEMALE)){
            record.setSexID(SEX_ID_FEMALE);
        }
    }

    /**
     * Il metodo è utilizzato per aggiornare il
     * campo sexName dell'oggetto record.
     * @param record  Record sottoposto ad analisi
     */
    private static void updateSexName(String testString, CSVRecord record){
        if(testString.equalsIgnoreCase(SEX_NAME_MALE)){
            record.setSexName(SEX_NAME_MALE);
        }else if(testString.equalsIgnoreCase(SEX_NAME_FEMALE)){
            record.setSexName(SEX_NAME_FEMALE);
        }
    }
}