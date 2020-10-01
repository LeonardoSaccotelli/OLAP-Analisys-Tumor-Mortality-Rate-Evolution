package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Sex;

import java.util.ArrayList;

/**
 * La classe si occupa di popolare la tabella Sex
 * del Dw che si è realizzato.
 */
public class TransformSextoTableSex {

    /**
     * Il metodo si occupa di creare un oggetto di tipo Sex e
     * di aggiungerlo alla tabella Sex.
     * @param record Record da convertire e aggiungere alla tabella del sesso.
     * @param sexTable Tabella delle dimensioni Sex contenente i dati sul sesso.
     */
    public static void transformSex(CleanedRecord record, ArrayList<Sex> sexTable){
        boolean flag = false;

        Sex newSex = new Sex(record.getSexID(), record.getSexName());

       if(sexTable.contains(newSex)){
           flag = true;
       }

        if(!flag){
            sexTable.add(newSex);
        }
    }
}
