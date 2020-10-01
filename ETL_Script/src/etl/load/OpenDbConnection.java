package etl.load;

import etl.utility.Utility;

import java.sql.*;

/**
 * La classe si occupa di gestire la
 * connessione al database in cui verranno memorizzati i dati.
 */
public class OpenDbConnection {

    /**
     * Il metodo apre la connessione al database scelto
     * e disattiva l'autocommit in modo tale da gestire
     * manualmente i commit dei dati.
     * @return Oggetto connection utilizzabile per
     *         interagire con il database.
     */
    public static Connection openDbConnection(String nameDb){

        String url ="jdbc:postgresql://localhost:5432/"+nameDb;
        Connection connectionString = null;
        String user ="user", pass="pass";

        try {
            connectionString = DriverManager.getConnection(url, user, pass);

            connectionString.setAutoCommit(false);

        }catch(SQLException e){
            Utility.showExceptionDialogBox(e);
        }

        return connectionString;
    }
}
