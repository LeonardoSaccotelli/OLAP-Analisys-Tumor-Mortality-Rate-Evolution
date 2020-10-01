package etl.utility;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 * La classe fornirà metodi di utilità
 * utilizzabili da tutte le classi del sistema.
 * Evitiamo quindi di riscrivere metodi comuni
 * a varie classi.
 */
public class Utility {
    /**
     * Il metodo è utilizzato per verificare se la stringa
     * passata come paramentro rappresenta un numero o meno.
     * In particolare il metodo verifica che ogni caratteria
     * sia o una cifra numerica oppure un '.' (punto).
     * Quest'ultimo ci serve per verificare che un numero
     * decimale non venga erroneamente considerata una stringa
     * non numerica.
     * @param testYears la stringa da sottoporre al controllo
     * @return isDigit (true/false) a seconda che il controllo sia superato o meno.
     */
    public static boolean stringIsDigit(String testYears){
        boolean isDigit = true;
        int countPoint = 0;

        if(!testYears.equals("")) {
            for (int i = 0; i < testYears.length(); i++) {
                if (!((Character.isDigit(testYears.charAt(i))) || (testYears.charAt(i) == '.'))) {
                    isDigit = false;
                    break;
                }

                if(testYears.charAt(i) == '.'){
                    countPoint++;
                }
            }

            if(countPoint > 1){
                isDigit = false;
            }


        }else{
            isDigit=false;
        }
        return isDigit;
    }

    /**
     * Il merodo consente di effettuare la ricerca binaria di una stringa.
     * @param listString Array di Object contenente i dati in cui effettuare la ricerca della stringa.
     * @param findString Stringa da cercare nella struttura data.
     * @param start Indice iniziale della struttura in cui effettuare la ricerca.
     * @param end Indice finale della struttura in cui effettuare la ricerca.
     * @return L'indice della posizione dell'oggtto, se esiste. Altrimenti -1.
     */
    public static int stringBinarySearch(Object[] listString, String findString, int start, int end ){

        findString = findString.replaceAll("\\p{Punct}","").replaceAll("\\s","");

        if (listString.length > 0) {
            while (start <= end) {
                int medium = start + (end - start) / 2;

                String prova = listString[medium].toString().replaceAll("\\p{Punct}", "").replaceAll("\\s", "");
                int res = findString.compareToIgnoreCase(prova);

                // Check if x is present at mid
                if (res == 0)
                    return medium;

                // If x greater, ignore left half
                if (res > 0)
                    start = medium + 1;

                    // If x is smaller, ignore right half
                else
                    end = medium - 1;
            }
        }

        return -1;
    }

    /**
     * Il metodo si occupa di stampare un messaggio nell'area di testo.
     * Il metodo viene eseguito sul Thread principale di JAvaFX.
     * @param message Stringa da stampare.
     * @param textArea Area di testo in cui mostrare lo stato dell'elaborazione.
     */
    public static void appendTextArea(String message, TextArea textArea){
        Platform.runLater(() -> textArea.appendText(message+"\n"));
    }

    /**
     * Il metodo si occupa di visualizzare una Warning Box.
     * Il metodo viene eseguito sul Thread principale di JAvaFX.
     * @param header Intestazione del messaggio.
     * @param content Contenuto del messaggio.
     */
    public static void showWarningDialogBox(String header, String content){
        Platform.runLater(() -> DialogBox.warningBox(
                header,content));
    }

    /**
     * Il metodo si occupa di visualizzare una Exception Box.
     * Il metodo viene eseguito sul Thread principale di JAvaFX.
     * @param e Oggetto contenente tutte le info sull'errore verificatosi.
     */
    public static void showExceptionDialogBox(Throwable e){
        Platform.runLater(() -> DialogBox.exceptionBox(
                e));
    }

    /**
     * Il metodo si occupa di visualizzare una Information Box.
     * Il metodo viene eseguito sul Thread principale di JAvaFX.
     * @param header Intestazione del messaggio.
     * @param content Contenuto del messaggio.
     */
    public static void showInformationBox(String header, String content){
        Platform.runLater(()-> DialogBox.informationBox(header,content));
    }
}

