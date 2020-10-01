package etl.extract.clean;
import etl.data.CSVRecord;
import etl.data.Tumor;
import etl.utility.Utility;

/**
 * La classe gestisce le operazioni di pulizia
 * sul campo Type_Tumor e Tumor_id.
 */
public class TumorDataCleaning {

    /**
     * Metodo che verifica lo status dei campi contenenti le
     * informazioni relative al tumore.
     * @param record Record da sottoporre a controllo.
     * @return true se i dati sul tumore presenti nel record in input sono validi,
     *          altrimenti false se non è valido.
     */
    public static boolean checkTumor(CSVRecord record){
        boolean flag = false;
        boolean findTumorOK = false;

        String testSexName = record.getSexName();
        Tumor[] tumorList = Tumor.values();
        int indexTumorList;

        indexTumorList = Utility.stringBinarySearch(tumorList, record.getTypeTumor(),0,tumorList.length -1);

        if(indexTumorList!= -1){
            findTumorOK = updateTypeTumor(record,tumorList[indexTumorList]);
        }

        if(findTumorOK){
            if(tumorList[indexTumorList].getTumorSexName().equals("Both")) {
                flag = true;
            }else if(testSexName.equals(tumorList[indexTumorList].getTumorSexName())){
                flag = true;
            }
        }

        if(flag){
            record.setTumorID(tumorList[indexTumorList].getIDTumor());
        }

        return flag;
    }

    /**
     * Metodo utilizzato per aggiornare il campo TypeTumor
     * dell'oggetto record.
     * @param record oggetto contenente il campo da aggiornare.
     * @return true
     */
    private static boolean updateTypeTumor(CSVRecord record, Tumor typeTumor){
        String testTumor = record.getTypeTumor();

        if(!testTumor.equals(typeTumor.toString())) {
            record.setTypeTumor(typeTumor.toString());
        }
        return true;
    }
}