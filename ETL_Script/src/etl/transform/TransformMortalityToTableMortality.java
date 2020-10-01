package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Mortality;

import java.util.ArrayList;

/**
 * La classe si occupa di popolare la tabella Mortality
 * del Dw che si è realizzato.
 */
public class TransformMortalityToTableMortality {

    /**
     * Il metodo si occupa di creare un oggetto di tipo Mortality e
     * di aggiungerlo alla tabella della mortalità.
     * @param record Record da convertire e aggiungere alla tabella della mortalità.
     * @param mortalityTable Tabella dei fatti Mortality contenente i dati sulla mortalità.
     */
    public static void transformMortalityToMortalityTable(CleanedRecord record, ArrayList<Mortality> mortalityTable){

        float deathEstimate = (record.getMortality() * record.getPopulation())/100000;

        Mortality newMortality = new Mortality(record.getFIPSCounty(),record.getYearID(),record.getSexID(),
                                                record.getTumorID(),record.getMortality(),record.getPopulation(),deathEstimate);
        mortalityTable.add(newMortality);
    }
}
