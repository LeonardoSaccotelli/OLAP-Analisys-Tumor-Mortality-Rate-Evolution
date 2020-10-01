package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Locality;

import java.util.ArrayList;

/**
 * La classe si occupa di popolare la tabella Locality
 * del Dw che si è realizzato.
 */
public class TransformStateCountyToTableLocality {

    /**
     * Il metodo si occupa di creare un oggetto di tipo Locality e
     * di aggiungerlo alla tabella Locality.
     * @param record Record da convertire e aggiungere alla tabella della località.
     * @param localityTable Tabella delle dimensioni Locality contenente i dati sulle località.
     */
    public static void trasformStateCountyToLocalityTable(CleanedRecord record, ArrayList<Locality> localityTable){
        boolean flag = false;

        String locality = record.getNameState() + " : " + record.getNameCounty();
        Locality newLocality = new Locality(record.getFIPSCounty(), locality);

        if(localityTable.contains(newLocality)){
            flag=true;
        }

        if(!flag){
            localityTable.add(newLocality);
        }
    }
}
