package etl.extract;

import etl.data.CSVRecord;
import etl.utility.Utility;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * La classe fornisce tutti i metodi necessari per leggere correttamente
 * i dati a partire da file in formato csv.
 */
public class CSVReader {
    /**
     * Il metodo riceve un File, ne legge il contenuto, lo sottopone ad una serie di
     * controlli preliminari e se superati, crea un oggetto di tipo CSVRecord a partire
     * dalla singola riga letta dal file. Infine, aggiunge l'oggetto creato ad un'apposita lista.
     * @param csvFile Singolo oggetto di tipo File, contenente il riferimento al file da cui leggere
     * @return Lista di oggetti di tipo CSVRecord
     * @throws IOException Il file non è stato trovato nel percorso specificato, oppure non è possibile leggere dal file.
     */
    public static ArrayList<CSVRecord> readCSVFile(File csvFile) throws IOException {

        ArrayList<CSVRecord> dataTable = null;


        //Creaiamo un oggetto BufferedReader per leggere il file CSV
        BufferedReader bufferCSV = new BufferedReader(new FileReader(csvFile));
        String strLine;

        //Lettura dell'intestazione del file
        String headerCSV = bufferCSV.readLine();

        //Se il file rispetta l'intestazione prevista dall'applicazione, posso leggere il contenuto del file
        if(headerCSV.equals("FIPS,NameState,FIPS_County,Name_County,Year_ID,ID_Tumor,Type_Tumor,Sex_ID,Sex_name,Mortality,Population")){

            /*
             * Istanzio un oggetto HashSet di stringhe che conterrà i singoli
             * record letti dal file. L'hashset non consente duplicati, quindi
             * al termine di questa fase siamo sicuri di non avere record duplicati
             */
            HashSet<String> setRecord = new HashSet<>();

            //Lettura di ogni singola riga del file CSV
            while ((strLine = bufferCSV.readLine()) != null) {
                /*
                 * Controllo che la riga letta dal file non sia una riga vuota;
                 * Se non lo è posso procedere a salvarla.
                 */
                if(!(strLine.replaceAll("\\p{Punct}","").replaceAll("\\s","").equals(""))) {

                    if (strLine.contains("\"")) {
                        strLine = checkStatusCSV(strLine);
                    }
                    setRecord.add(strLine);
                }
            }

            dataTable = splitRecord(setRecord);


        }else{
            Utility.showWarningDialogBox("Error header CSV","CSV header dosn't match our file structure.");
        }

        return dataTable;
    }

    /**
     * Il metodo si occupa di splittare una stringa nel formato CSV in singoli campi,
     * verifica la correttezza e validita preliminare del campo, crea un oggetto
     * di tipo CSVRecord e lo aggiunge ad una lista di CSVRecord.
     * @param setRecord Insieme di stringhe in formato csv da convertire.
     * @return Una lista di oggetti di tipo CSVRecord.
     */
    private static ArrayList<CSVRecord> splitRecord(HashSet<String> setRecord){

        ArrayList<CSVRecord> dataTable = new ArrayList<>();

        for(String record : setRecord){

           String[] tempSplitData = record.split(",");

           if (tempSplitData.length == 11) {

               CSVRecord dataValue = new CSVRecord(tempSplitData[0], tempSplitData[1],
                       tempSplitData[2], tempSplitData[3], tempSplitData[4], tempSplitData[5],
                       tempSplitData[6], tempSplitData[7], tempSplitData[8], tempSplitData[9], tempSplitData[10]);

               if (checkEmptyField(dataValue)) {
                   dataTable.add(dataValue);
               }
           }
        }

        return dataTable;
    }

    /**
     * IL metodo testa un oggetto di tipo CSVRecord per verificare
     * se uno o più campi sono vuoti.
     * @param testEmptyFieldRecord Oggetto di tipo CSVRecord da sottoporre a verifica.
     * @return true se nessun campo dell'oggetto è un campo vuoto, altrimenti false.
     */
    private static boolean checkEmptyField(CSVRecord testEmptyFieldRecord){

        boolean flag = true;

        if (testEmptyFieldRecord.getFIPS().equals("")||
                testEmptyFieldRecord.getNameState().equals("") ||
                testEmptyFieldRecord.getFIPSCounty().equals("") ||
                testEmptyFieldRecord.getNameCounty().equals("") ||
                testEmptyFieldRecord.getYearID().equals("") ||
                testEmptyFieldRecord.getTumorID().equals("") ||
                testEmptyFieldRecord.getTypeTumor().equals("") ||
                testEmptyFieldRecord.getSexID().equals("") ||
                testEmptyFieldRecord.getSexName().equals("") ||
                testEmptyFieldRecord.getMortality().equals("") ||
                testEmptyFieldRecord.getPopulation().equals("")) {
            flag = false;
        }
        return flag;
    }


    /**
     * Il metodo verifica la presenza all'interno della stringa passata
     * di eventuali citazioni contenti virgole. Queste virgole non
     * rappresentano un separatore di campo e pertanto vanno rimosse
     * primma di splittare la stringa.
     * @param csvRecord Stringa da sottoporre a test per verificare la presenza
     *                  di virgole non separatore di campo,
     * @return La stringa di partenza dopo aver rimosso virgole non separatori di campo.
     */
    private static String checkStatusCSV(String csvRecord){

        ArrayList<Character> testString = new ArrayList<>();

        int i = 0, j, z;

        while(i < csvRecord.length()) {
            if(csvRecord.charAt(i) !='\"'){
                testString.add(csvRecord.charAt(i));
            }

            if(csvRecord.charAt(i) == '\"') {

                for(j = i+1; j <csvRecord.length();j++) {
                    if(csvRecord.charAt(j) == '\"') {
                        String subCSVRecord = csvRecord.substring(i+1, j).replaceAll(",", "");

                        z = 0;
                        while(z < subCSVRecord.length()) {

                            testString.add(subCSVRecord.charAt(z));
                            z++;
                        }
                        i = j;
                        break;
                    }
                }
            }
            i++;
        }

        String correctString = testString.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        return correctString.replaceAll("\"","");
    }
}
