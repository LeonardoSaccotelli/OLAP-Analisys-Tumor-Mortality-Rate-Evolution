package etl.utility;

import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

/**
 * La classe si occupa della creazione di una finestra di dialogo
 * che permette di selezionare un insieme di file da cui si vuole
 * leggere i dati contenuti in essi.
 */
public class FileChooserDialog {
    /**
     * Il metodo si occupa di creare la finestra di dialogo, settare il percorso
     * e mostrare la finestra nel percorso scelto. La finestra permetterà di
     * selezionare solo file con estensione csv.
     * @return Una lista di File selezionati dall'utente, altrimenti Null
     */
    public static List<File> openFileDialog(){

        //Creo un oggetto FileChooser
        FileChooser windowFileChooser = new FileChooser();

        /*
         * Setto una eventuale directory iniziale in cui aprire il la finestra
         * windowFileChooser.setInitialDirectory(new File("D:\\\\Books"));
         */
        String filepath = System.getProperty("user.dir") + "\\" +"dataset";

        windowFileChooser.setInitialDirectory(new File(filepath));

        /*
         * Fisso una estensionse sulla tipologia di file che è possibile aprire,
         * nel nostro caso solo dati in CSV
         */
        windowFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        /*
         *  Restituisco una lista di File ottenuti direttamente
         *  dalla selezione di file fatta dall'utente
         */
        return windowFileChooser.showOpenMultipleDialog(null);
    }
}
