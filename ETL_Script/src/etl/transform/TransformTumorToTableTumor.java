package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Tumor;
import java.util.ArrayList;

/**
 * La classe si occupa di popolare la tabella Tumor
 * del Dw che si è realizzato.
 */
public class TransformTumorToTableTumor {

    /**
     * Il metodo si occupa di creare un oggetto di tipo Tumor e
     * di aggiungerlo alla tabella Tumor.
     * @param record Record da convertire e aggiungere alla tabella dei tumori.
     * @param tumorTable Tabella delle dimensioni Tumor contenente i dati sui tumori.
     */
    public static void transformTumor(CleanedRecord record, ArrayList<Tumor> tumorTable){
        boolean flag = false;

        Tumor newTumor = new Tumor(record.getTumorID(),record.getTypeTumor());

        if(tumorTable.contains(newTumor)){
            flag = true;
        }

        if(!flag){
            tumorTable.add(newTumor);
        }
    }
}




