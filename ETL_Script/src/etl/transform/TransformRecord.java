package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.*;

import java.util.ArrayList;

/**
 * La classe centralizza in un unico metodo tutte le
 * operazioni necessarie per trasformare correttamente i dati
 * e scinderli in opportune tabelle.
 */
public class TransformRecord {

    private static final int MAX_NUMBER_ROW_SEX_TABLE = 2;
    private static final int MAX_NUMBER_ROW_YEAR_TABLE = 11;
    private static final int MAX_NUMBER_ROW_TUMOR_TABLE = 31;

    /**
     * Il metodo centralizza le richieste di trasformazioni
     * della lista di record nelle diverse tabelle che comporranno il DW.
     * @param dataset Lista contenente i record ottenuti dalla fase di pulizia da sottoporre
     *                alla fase di trasformazione.
     * @param sexTable Tabella delle dimensioni Sex
     * @param yearTable Tabella delle dimensioni Years
     * @param tumorTable Tabella delle dimensioni Tumor
     * @param localityTable Tabella delle dimensioni Locality
     * @param mortalityTable Tabella dei fatti Mortality
     */
    public static void transformData(ArrayList<CleanedRecord> dataset, ArrayList<Sex> sexTable, ArrayList<Years> yearTable,
                                     ArrayList<Tumor> tumorTable, ArrayList<Locality> localityTable, ArrayList<Mortality> mortalityTable){

        for (CleanedRecord cleanedRecord : dataset) {
            if (sexTable.size() != MAX_NUMBER_ROW_SEX_TABLE) {
                TransformSextoTableSex.transformSex(cleanedRecord, sexTable);
            }

            if (yearTable.size() != MAX_NUMBER_ROW_YEAR_TABLE) {
                TransformYearToTableYear.transformYear(cleanedRecord, yearTable);
            }


            if (tumorTable.size() != MAX_NUMBER_ROW_TUMOR_TABLE) {
                TransformTumorToTableTumor.transformTumor(cleanedRecord, tumorTable);
            }

            TransformStateCountyToTableLocality.trasformStateCountyToLocalityTable(cleanedRecord, localityTable);

            TransformMortalityToTableMortality.transformMortalityToMortalityTable(cleanedRecord, mortalityTable);

        }
    }
}
