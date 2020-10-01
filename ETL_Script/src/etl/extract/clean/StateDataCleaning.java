package etl.extract.clean;
import etl.data.State;
import etl.data.CSVRecord;
import etl.utility.Utility;

/**
 * La classe gestisce le operazioni di pulizia
 * sul campo NameState e FIPSState.
 */
public class StateDataCleaning {

    /**
     * Metodo che verifica lo status dei campi contenenti le
     * informazioni relative allo Stato.
     * @param record Record da sottoporre a controllo.
     * @return true se i dati sullo stato presenti nel record in input sono validi,
     *          altrimenti false se non è valido.
     */
    public static boolean checkState(CSVRecord record){

        boolean flag = false;
        boolean findStateOk = false;
        int indexStateList;

        State[] listState = State.values();

        indexStateList = Utility.stringBinarySearch(listState,record.getNameState(), 0, listState.length-1);

        if(indexStateList != -1){
            findStateOk = updateStateName(record, listState[indexStateList]);
        }

        if(findStateOk){
            if(record.getFIPS().equals(listState[indexStateList].getFIPS())){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Metodo utilizzato per aggiornare il campo NameState
     * dell'oggetto record.
     * @param record contenente il campo da aggiornare.
     * @param nameState oggetto contenente le informazioni con cui aggiornare il record.
     * @return true
     */
    private static boolean updateStateName(CSVRecord record, State nameState){
        String testStateName = record.getNameState();

        if(!testStateName.equals(nameState.toString())) {
            record.setNameState(nameState.toString());
        }
        return true;
    }
}
