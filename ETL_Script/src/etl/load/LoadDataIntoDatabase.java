package etl.load;

import etl.transform.datatable.*;
import etl.utility.Utility;
import javafx.scene.control.TextArea;

import java.sql.*;
import java.util.ArrayList;

/**
 * La classe permette di centralizzare le
 * operazioni di scrittura dei dati trasformati
 * sul database in un unico metodo.
 */
public class LoadDataIntoDatabase {

    /**
     * Il metodo centralizza le operazioni di scrittura dei dati sul database,
     * occupandosi della gestione di eventuali errori che si dovessero verificare
     * durante le operazioni di scrittura.
     * @param textArea Area di testo in cui verrà mostrato il progresso.
     * @param connectionString Oggetto Connection che permetterà di interagire con il db.
     * @param sexTable Tabella dimesione Sex.
     * @param yearTable Tabella dimesione Year.
     * @param tumorTable Tabella dimesione Tumor.
     * @param localityTable Tabella dimesione Locality.
     * @param mortalityTable Tabella dei fatti Mortality.
     */
    public static void loadData(TextArea textArea,Connection connectionString,
                                ArrayList<Sex> sexTable, ArrayList<Years> yearTable,
                                ArrayList<Tumor> tumorTable, ArrayList<Locality> localityTable,
                                ArrayList<Mortality> mortalityTable){

        try{
            LoadSexTable.loadSexTableIntoDatabase(connectionString,sexTable,textArea);
            LoadYearTable.loadYearTableIntoDatabase(connectionString,yearTable,textArea);
            LoadTumorTable.loadTumorTableIntoDatabase(connectionString,tumorTable,textArea);
            LoadLocalityTable.loadLocalityTableIntoDatabase(connectionString,localityTable,textArea);
            LoadMortalityTable.loadMortalityTableIntoDatabase(connectionString,mortalityTable,textArea);

        }catch(SQLException e){
            Utility.showExceptionDialogBox(e);
        }
    }
}
