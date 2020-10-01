package etl.load;

import etl.transform.datatable.Years;
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
 * La classe gestisce la scrittura dei dati sull'anno.
 */
public class LoadYearTable {

    /**
     * Il metodo permette di memorizzare nel database i dati contenuti nella struttura
     * yearTable in un opportuna tabella nel db.
     * @param connectionString Oggetto Connection che permetterà di interagire con il db.
     * @param yearTable Tabella della dimensione Year
     * @param textArea Area di testo in cui verrà mostrato il progresso.
     * @throws SQLException Se si verificano problemi durante la scrittura.
     */
    public static void loadYearTableIntoDatabase(Connection connectionString,
                                                 ArrayList<Years> yearTable,
                                                 TextArea textArea) throws SQLException {

        String sqlInsertQuery = "INSERT INTO years (years_id) " +
                "VALUES (?)" +
                "ON CONFLICT  (years_id) DO NOTHING";



        PreparedStatement preparedStatement = connectionString.prepareStatement(sqlInsertQuery);

        for (Years years : yearTable) {
            preparedStatement.setInt(1, years.getYear());
            preparedStatement.addBatch();
        }

        //Converto un array di int in una List di Integer
        List<Integer> addedRows = Arrays.stream(preparedStatement.executeBatch())
                .boxed().collect(Collectors.toList());

        if(addedRows.contains(1)) {
            Utility.appendTextArea("- Year Table exported", textArea);
        }else{
            Utility.appendTextArea("- Data in Years Table already exists", textArea);
        }

        connectionString.commit();
    }
}
