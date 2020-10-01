package etl.load;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class OpenDbConnectionTest {

    @Test
    void openDbConnection() {
        Connection connectionTest = OpenDbConnection.openDbConnection("test");

        int id = 1;
        String status="DB connection status: OK";
        Long timestamp = System.currentTimeMillis();


        String sqlQueryTest = "INSERT INTO testingtable (id,status,timestamp) " +
                                "VALUES(" + id +",'" + status +"'," + timestamp +")" +
                                "ON CONFLICT  (id) DO NOTHING";
        try {
            Statement statement = connectionTest.createStatement();
            int newRowTest = statement.executeUpdate(sqlQueryTest);
            connectionTest.commit();

            assertEquals(1,newRowTest);

            connectionTest.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void openDbConnection1() {
        Connection connectionTest = OpenDbConnection.openDbConnection("test");

        int id = 2;
        String status="DB connection status: OK";
        Long timestamp = System.currentTimeMillis();
        int resID = 0; String resStatus =""; Long resTimestamp = 0L;


        String sqlQueryTest = "INSERT INTO testingtable (id,status,timestamp) " +
                "VALUES(" + id +",'" + status +"'," + timestamp +")" +
                "ON CONFLICT  (id) DO NOTHING";
        try {
            Statement statement = connectionTest.createStatement();
            statement.executeUpdate(sqlQueryTest);
            connectionTest.commit();


            sqlQueryTest = "SELECT * FROM tastingtable WHERE id = 2";
            Statement statement1 = connectionTest.createStatement();
            ResultSet resDB = statement1.executeQuery(sqlQueryTest);

            while(resDB.next()){
                resID = resDB.getInt("id");
                resStatus = resDB.getString("status");
                resTimestamp = resDB.getLong("timestamp");
            }

            boolean resultStatusDB = true;

            if(!(resID == id && resStatus.equals(status) && resTimestamp.equals(timestamp))){
                resultStatusDB = false;
            }

            assertTrue(resultStatusDB);

            connectionTest.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}