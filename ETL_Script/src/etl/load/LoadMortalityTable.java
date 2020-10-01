package etl.load;

import etl.transform.datatable.Mortality;
import etl.utility.DbProgress;
import etl.utility.Utility;
import javafx.scene.control.TextArea;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * La classe gestisce la scrittura dei dati sulla mortalità.
 */
public class LoadMortalityTable {

    /**
     * Il metodo permette di memorizzare nel database i dati contenuti nella struttura
     * mortalityTable in un opportuna tabella nel db.
     * @param connectionString Oggetto Connection che permetterà di interagire con il db.
     * @param mortalityTable Tabella dei fatti Mortality
     * @param textArea Area di testo in cui verrà mostrato il progresso.
     * @throws SQLException Se si verificano problemi durante la scrittura.
     */
    public static void loadMortalityTableIntoDatabase(Connection connectionString,
                                                      ArrayList<Mortality> mortalityTable,
                                                      TextArea textArea) throws SQLException {


        String sqlInsertQuery = "INSERT INTO mortality (locality, years, sex, tumor, mortality_rate, population_estimate, deaths_estimate) " +
                                "VALUES (?,?,?,?,?,?,?) ON CONFLICT (locality,years,sex,tumor) DO NOTHING";

        PreparedStatement preparedStatement = connectionString.prepareStatement(sqlInsertQuery);

        int counterAddRows = 0;
        ArrayList<Integer> addedRows = new ArrayList<>();

        Utility.appendTextArea("- Mortality Table exporting ...", textArea);

        Timer timer = new Timer();
        TimerTask task = new DbProgress();

        timer.schedule(task,30000,60000);


        for (Mortality mortality : mortalityTable) {
            preparedStatement.setString(1, mortality.getLocality());
            preparedStatement.setInt(2, mortality.getYears());
            preparedStatement.setString(3, mortality.getSex());
            preparedStatement.setString(4, mortality.getTumor());
            preparedStatement.setFloat(5, mortality.getMortalityRate());
            preparedStatement.setInt(6, mortality.getPopulationEstimate());
            preparedStatement.setFloat(7, mortality.getDeathsEstimate());

            preparedStatement.addBatch();
            counterAddRows++;

            if ((counterAddRows % 1000) == 0) {

                addedRows.addAll((Arrays.stream(preparedStatement.executeBatch())
                        .boxed().collect(Collectors.toList())));

                connectionString.commit();
            }
        }

        timer.cancel();

        //Converto un array di int in una List di Integer
        addedRows.addAll((Arrays.stream(preparedStatement.executeBatch())
                .boxed().collect(Collectors.toList())));
        Utility.appendTextArea("     Rows Exported: " + Collections.frequency(addedRows,1),textArea);

        if (addedRows.contains(0)) {
            Utility.appendTextArea("     Rows NOT Exported (DUPLICATE ROWS): " + Collections.frequency(addedRows, 0), textArea);
        }
        Utility.appendTextArea("     Total Rows in Mortality Table : " + mortalityTable.size(), textArea);

        Utility.appendTextArea("- Mortality Table exported", textArea);
        connectionString.commit();

        Utility.appendTextArea("DATA EXPORTED CORRECTLY\n", textArea);

    }
}