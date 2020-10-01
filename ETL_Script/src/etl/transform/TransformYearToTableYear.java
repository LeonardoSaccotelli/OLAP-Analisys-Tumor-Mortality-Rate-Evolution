package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Years;

import java.util.ArrayList;

/**
 * La classe si occupa di popolare la tabella Years
 * del Dw che si è realizzato.
 */
public class TransformYearToTableYear {

    /**
     * Il metodo si occupa di creare un oggetto di tipo Years e
     * di aggiungerlo alla tabella Years.
     * @param record Record da convertire e aggiungere alla tabella degli anni.
     * @param tableYear Tabella delle dimensioni Years contenente i dati sugli anni.
     */
    public static void transformYear(CleanedRecord record, ArrayList<Years> tableYear){

        boolean flag = false;
        Years newYear = new Years(record.getYearID());

        if(tableYear.contains(newYear)){
            flag = true;
        }

        if(!flag){
            tableYear.add(newYear);
        }
    }
}
