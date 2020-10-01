package etl.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

/**
 * La classe fornisce i metodi per la creazione di
 * finestre di dialogo di varia natura:
 * - Information
 * - Exception
 * - Warning
 */
public class DialogBox {

    /***
     * Il metodo crea un'alert box di tipo confirmation,
     * mostra quindi due pulsanti (OK, Annulla).
     * @param header Stringa usata come intestazione della box
     * @param content Stringa usata come messaggio(contenuto) della box
     * @return true: Se l'utente clicca su OK, altrimenti false
     */
    public static boolean confirmBox (String header, String content){

        /*
         * Memorizza lo status true/false relativo alla risposta dell'utente
         * Di default è settato su False
         */
        boolean statusResponse;

        /*
         * Creo un oggetto Alert di tipo CONFIRMATION  e setto
         * i messaggi che verranno mostrati.
         */
        Alert box = new Alert(Alert.AlertType.CONFIRMATION);
        box.setTitle("Confirmation Box");
        box.setHeaderText(header);
        box.setContentText(content);

        /*
         * Specifico i pulsanti che verranno mostrati nella box
         * e visualizzo la box in attesa di risposta
         */
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        box.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);

        Optional <ButtonType> result = box.showAndWait();

        //Se l'utente conferma, aggiorno lo status della risposta
        statusResponse = result.isPresent() && result.get() == buttonTypeYes;

        //Restituisco lo status al main
        return statusResponse;
    }

    /**
     * Il metodo crea un'alert box in cui viene visualizzato
     * il tipo di eccezione e l'intero stack.
     * @param throwable Oggetto contenente l'errore/eccezione che è stata sollevata
     */
    public static void exceptionBox(Throwable throwable) {

        /*
         * Creo un'alert box di tipo ERROR
         * dove verrà mostrata l'eccezione sollevata
         */
        Alert box = new Alert(Alert.AlertType.ERROR);
        box.setTitle("Excpetion Box");
        box.setHeaderText("Thrown Exception");
        box.setContentText("Application has thrown an exception.");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        /*
         * Setto la textarea dove verrà mostrato
         * l'eccezione sollevata e lo stack error.
         * In particolare nelle istruzioni successive
         * si setta il layout, la possibilità di
         * rendere estendibile o meno il box, ecc..
         */
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        box.getDialogPane().setPrefWidth(600);
        box.getDialogPane().setExpandableContent(expContent);

        box.showAndWait();
    }


    /**
     * Il metodo crea un'alert box di tipo INFORMATION.
     * Questo box verrà semplicemente utilizzato per
     * visualizzare semplici messaggi informativi.
     * @param header Intestazione del box
     * @param content Contenuto del box
     */
    public static void informationBox(String header, String content){
        Alert box = new Alert(Alert.AlertType.INFORMATION);
        box.setTitle("Information Box");
        box.setHeaderText(header);
        box.setContentText(content);

        box.showAndWait();
    }

    /**
     * Il metodo crea un'alert box di tipo WARNING.
     * Questo box verrà semplicemente utilizzato per
     * visualizzare messaggi di avvertimento.
     * @param header Intestazione del box
     * @param content Contenuto del box
     */
    public static void warningBox(String header, String content){
        Alert box = new Alert(Alert.AlertType.WARNING);
        box.setTitle("Warning Box");
        box.setHeaderText(header);
        box.setContentText(content);

        box.showAndWait();
    }

}