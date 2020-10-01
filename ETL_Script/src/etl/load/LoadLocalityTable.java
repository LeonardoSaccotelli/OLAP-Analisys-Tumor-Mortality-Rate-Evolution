package etl.load;

import etl.transform.datatable.Locality;
import etl.utility.Utility;
import javafx.scene.control.TextArea;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * La classe gestisce la scrittura dei dati sulla località.
 */
public class LoadLocalityTable {

    /**
     * Il metodo permette di memorizzare nel database i dati contenuti nella struttura
     * localityTable in un opportuna tabella nel db.
     * @param connectionString Oggetto Connection che permetterà di interagire con il db.
     * @param localityTable Tabella della dimensione Locality
     * @param textArea Area di testo in cui verrà mostrato il progresso.
     * @throws SQLException Se si verificano problemi durante la scrittura.
     */
    public static void loadLocalityTableIntoDatabase(Connection connectionString,
                                                     ArrayList<Locality> localityTable,
                                                     TextArea textArea) throws SQLException {



        String sqlInsertQuery = "INSERT INTO locality (fips_county, locality_name ) " +
                                "VALUES (?,?)" +
                                "ON CONFLICT  (fips_county) DO NOTHING";

        PreparedStatement preparedStatement = connectionString.prepareStatement(sqlInsertQuery);

        int counterAddRows = 0;
        ArrayList<Integer> addedRows = new ArrayList<>();

        Utility.appendTextArea("- Locality Table exporting ...", textArea);


        for (Locality locality : localityTable) {
            preparedStatement.setString(1, locality.getFIPSCounty());
            preparedStatement.setString(2, locality.getLocalityName());
            preparedStatement.addBatch();

            counterAddRows++;

            if((counterAddRows % 1000) == 0) {

                addedRows.addAll((Arrays.stream(preparedStatement.executeBatch())
                        .boxed().collect(Collectors.toList())));

                connectionString.commit();
                /*Utility.appendTextArea("     Rows exported: " + Collections.frequency(addedRows,1) + " of " +
                                        localityTable.size(), textArea);*/
            }
        }

        //Converto un array di int in una List di Integer
        addedRows.addAll((Arrays.stream(preparedStatement.executeBatch())
                .boxed().collect(Collectors.toList())));
        Utility.appendTextArea("     Rows Exported: " + Collections.frequency(addedRows,1),textArea);

        if(addedRows.contains(0)){
            Utility.appendTextArea("     Rows NOT Exported (DUPLICATE ROWS): " + Collections.frequency(addedRows,0),textArea);
        }

        Utility.appendTextArea("     Total Rows in Locality Table : " + localityTable.size(), textArea);

        Utility.appendTextArea("- Locality Table exported",textArea);
        connectionString.commit();
    }
}