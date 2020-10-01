package etl.extract;

import etl.data.County;
import etl.utility.DialogBox;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * La classe si occupa di leggere il contenuto del file checkCounties.txt
 * e di creare una lista di oggetti di tipo County.
 */
public class ReadCountiesFile {

    /**
     * Il metodo permette di leggere dal file di testo, creare un oggetto di tipo
     * County partendo dal singolo record e di aggiungerlo a listOfCounties.
     * @return La lista di oggetti di tipo County.
     */
    public static ArrayList<County> readCountyFile(){

        ArrayList<County> listOfCounties = new ArrayList<>();

        String path = System.getProperty("user.dir");
        String fileName = path +"\\src\\file\\checkCounties.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            //Lettura intestazione
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] checkCounty = line.split(",");

                County newCounty = new County(checkCounty[0],checkCounty[1],checkCounty[2]);
                listOfCounties.add(newCounty);
            }

        } catch (IOException e) {
           DialogBox.exceptionBox(e);
        }

        return listOfCounties;
    }
}
