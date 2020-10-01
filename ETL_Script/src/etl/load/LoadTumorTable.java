package etl.load;

import etl.transform.datatable.Tumor;
import etl.utility.Utility;
import javafx.scene.control.TextArea;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La classe gestisce la scrittura dei dati sul tumore.
 */
public class LoadTumorTable {

    /**
     * Il metodo permette di memorizzare nel database i dati contenuti nella struttura
     * tumorTable in un opportuna tabella nel db.
     * @param connectionString Oggetto Connection che permetterà di interagire con il db.
     * @param tumorTable Tabella della dimensione Tumor
     * @param textArea Area di testo in cui verrà mostrato il progresso.
     * @throws SQLException Se si verificano problemi durante la scrittura.
     */
    public static void loadTumorTableIntoDatabase(Connection connectionString,
                                                  ArrayList<Tumor> tumorTable,
                                                  TextArea textArea) throws SQLException {


        String sqlInsertQuery = "INSERT INTO tumor (tumor_id, tumor_name ) " +
                "VALUES (?,?)" +
                "ON CONFLICT  (tumor_id) DO NOTHING";



        PreparedStatement preparedStatement = connectionString.prepareStatement(sqlInsertQuery);

        for (Tumor tumor : tumorTable) {
            preparedStatement.setString(1, tumor.getIDTumor());
            preparedStatement.setString(2, tumor.getTumorName());
            preparedStatement.addBatch();
        }
        //Converto un array di int in una List di Integer
        List<Integer> addedRows = Arrays.stream(preparedStatement.executeBatch())
                .boxed().collect(Collectors.toList());

        if(addedRows.contains(1)) {
            Utility.appendTextArea("- Tumor Table exported", textArea);
        }else{
            Utility.appendTextArea("- Data in Tumor Table already exists", textArea);
        }

        connectionString.commit();
    }
}
