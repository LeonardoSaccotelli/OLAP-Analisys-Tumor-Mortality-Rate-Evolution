package etl.load;

import etl.transform.datatable.Sex;
import etl.utility.Utility;
import javafx.scene.control.TextArea;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La classe gestisce la scrittura dei dati sul sesso.
 */
public class LoadSexTable {

    /**
     * Il metodo permette di memorizzare nel database i dati contenuti nella struttura
     * sextable in un opportuna tabella nel db.
     * @param connectionString Oggetto Connection che permetterà di interagire con il db.
     * @param sexTable Tabella della dimensione Sex
     * @param textArea Area di testo in cui verrà mostrato il progresso.
     * @throws SQLException Se si verificano problemi durante la scrittura.
     */
    public static void loadSexTableIntoDatabase(Connection connectionString,
                                                ArrayList<Sex> sexTable,
                                                TextArea textArea) throws SQLException {


        String sqlInsertQuery = "INSERT INTO sex (sex_id, sex_name ) " +
                                "VALUES (?,?)" +
                                "ON CONFLICT  (sex_id) DO NOTHING";


        PreparedStatement preparedStatement = connectionString.prepareStatement(sqlInsertQuery);



        for (Sex sex : sexTable) {
            preparedStatement.setString(1, sex.getIDSex());
            preparedStatement.setString(2, sex.getSexName());
            preparedStatement.addBatch();
        }

        //Converto un array di int in una List di Integer
        List<Integer> addedRows = Arrays.stream(preparedStatement.executeBatch())
                                        .boxed().collect(Collectors.toList());

        if(addedRows.contains(1)){
            Utility.appendTextArea("- Sex Table exported",textArea);
        }else{
            Utility.appendTextArea("- Data in Sex Table already exists", textArea);
        }

        connectionString.commit();
    }
}
