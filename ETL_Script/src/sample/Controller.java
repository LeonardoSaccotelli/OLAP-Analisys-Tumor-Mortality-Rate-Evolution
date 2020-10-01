package sample;

import etl.extract.ReadCountiesFile;
import etl.load.OpenDbConnection;
import etl.transform.datatable.*;
import etl.transform.datatable.Tumor;
import etl.utility.FileChooserDialog;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import etl.utility.DialogBox;
import etl.data.*;
import javafx.scene.control.TextArea;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * La classe Controller si occupa di intercettare le richieste provenienti
 * dalla view e di reagire di conseguenza eseguendo le operazioni corrispondenti
 * alla richiesta dell'utente. In altre parole intercetta un evento e reagisce
 * eseguendo una serie di azioni correlate all'evento
 */
public class Controller implements Initializable {
    @FXML
    private Button helpButton;

    @FXML
    private Button importButton;

    @FXML
    private TextArea textArea;

    private final ArrayList<ArrayList<CSVRecord>> dataSet = new ArrayList<>();

    private final ArrayList<File> selectedFiles = new ArrayList<>();

    private final ArrayList<ArrayList<CleanedRecord>> datasetCleaning = new ArrayList<>();

    private int indexReadFile = 0;
    private final int[] indexCleanedTransformFile = {0,0};

    private ArrayList<County> listOfCounty;

    private final ArrayList<Sex> sexTable = new ArrayList<>();
    private final ArrayList<Years> yearsTable = new ArrayList<>();
    private final ArrayList<Tumor> tumorTable = new ArrayList<>();
    private final ArrayList<Locality> localityTable = new ArrayList<>();
    private final ArrayList<Mortality> mortalityTable = new ArrayList<>();

    private boolean checkOpenCountiesFile = true;
    private boolean checkAccessDatabase = true;

    private Connection connectionPostgresDbString = null;

    private final boolean[] flagTransform = new boolean[1];
    private final boolean[] flagExport = new boolean[1];

    /**
     * Il metodo consente di selezionare uno o più
     * file CSV, di leggere da questi file e successivamente
     * di elaborarli. Pertanto, gestirà la fase di EXTRACT della procedura ETL.
     */
    @FXML
    public void importData() {
        Long startTimer = System.currentTimeMillis();

        if (checkOpenCountiesFile) {
            listOfCounty = ReadCountiesFile.readCountyFile();
            checkOpenCountiesFile = false;
        }

        if(checkAccessDatabase){
            connectionPostgresDbString = OpenDbConnection.openDbConnection("starschema");
            checkAccessDatabase = false;
        }

        //Invoco un metodo che mi resituisce una lista di file
        List<File> duplicateSelectedFiles = FileChooserDialog.openFileDialog();

        if (duplicateSelectedFiles != null) {

            //Contatore usato per contare il numero di file selezionato dall'utente
            int countSelectedFile = 0;

            int checkCountDuplicatedFile = duplicateSelectedFiles.size();

            /*
             * Per ogni file passato dall'utente, aggiungo il file in una
             * collection che non permette l'inserimento di duplicati.
             * In questo modo impedisco a Runtime il caricamento
             * dello stesso file più volte.
             */
            for (File removeDuplicateFile : duplicateSelectedFiles) {
                if (selectedFiles.contains(removeDuplicateFile)) {
                    DialogBox.warningBox("Duplicated files", "File '" +
                            removeDuplicateFile.getName() + "' is already loaded!");
                    checkCountDuplicatedFile--;
                } else {
                    selectedFiles.add(removeDuplicateFile);
                    countSelectedFile++;
                }
            }

            if (checkCountDuplicatedFile > 0) {
                DialogBox.informationBox("Selected files",
                        "Number of files loaded: " + countSelectedFile + "\n" +
                                "The process could take from a few seconds to a few minutes.");

                if(!(DialogBox.confirmBox("Enter Files","Do you want to add other files?"))) {

                    importButton.setDisable(true);

                    Runnable task = () -> indexReadFile = indexReadFile+
                            FacadeControllerBackground.facadeControllerBackgroundMethod(textArea,indexReadFile,selectedFiles, dataSet,importButton,
                                    listOfCounty, datasetCleaning, sexTable, yearsTable,
                                    tumorTable,  localityTable, mortalityTable,
                                    indexCleanedTransformFile,
                                    flagTransform, flagExport, connectionPostgresDbString, startTimer);

                    // Run the task in a background thread
                    Thread backgroundThread = new Thread(task);

                    // Terminate the running thread if the application exists
                    backgroundThread.setDaemon(true);

                    // Start the thread
                    backgroundThread.start();
                }
            }

        }else {
            //Nessun file è stato selezionato quindi nessuna procedurà sarà eseguita.
            DialogBox.warningBox("Selected Files", "No file selected!");
        }
    }

    /**
     * Il metodo si occupa di fornire una serie di informazioni
     * relative al funzionamento dello script.
     */
    public void help(){
        textArea.setText("");
        textArea.appendText("DO YOU NEED HELP?");
        textArea.appendText("\n- Press Import Data Button if you want to load some datasets. ");
        textArea.appendText("\n  After the selection, system will give you the chance to add other files,\n  or terminate " +
                "the import data phase.");
        textArea.appendText("\n\n- Transform data button is now clickable. Press it if you want to clean\n  and transform" +
                "your dataset.");
        textArea.appendText("\n\n- Now you can press Export Data Button to transfer data in database.");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea.setText("");
        textArea.setStyle(" -fx-font-family: Consolas;\n" +
                "  -fx-highlight-fill: #000000;\n" +
                "  -fx-highlight-text-fill: #000000;\n" +
                "  -fx-text-fill: #000000;" +
                "  -fx-font-size: 18;\n");
    }
}