package etl.data;

/***
 * La classe è utilizzata per rappresentare un
 * singolo record letto da un dataset. Ovviamente
 * leggendo da un file csv tutti i campi sono semplici stringhe
 * pertanto l'oggetto CSVRecord altro non è che un insieme di stringhe.
 * ====================================================================
 * La classe fornisce i metodi get e set, e ovvimente i costruttori
 * della classe.
 */
public class CSVRecord {

    private final String FIPS;
    private String nameState;
    private final String FIPSCounty;
    private String nameCounty;
    private final String yearID;
    private String tumorID;
    private String typeTumor;
    private String sexID;
    private String sexName;
    private final String mortality;
    private final String population;

    public CSVRecord(String myFIPS, String myNameState, String myFIPSCounty, String myNameCounty,
                     String myYearID, String myTumorID,String myTypeTumor, String mySexID,
                     String mySexName, String myMortality, String myPopulation){
        this.FIPS = myFIPS;
        this.nameState = myNameState;
        this.FIPSCounty = myFIPSCounty;
        this.nameCounty = myNameCounty;
        this.yearID = myYearID;
        this.tumorID = myTumorID;
        this.typeTumor = myTypeTumor;
        this.sexID = mySexID;
        this.sexName = mySexName;
        this.mortality = myMortality;
        this.population = myPopulation;
    }

    public String getFIPS() {
        return FIPS;
    }

    public String getNameState() {
        return nameState;
    }

    public String getFIPSCounty() {
        return FIPSCounty;
    }

    public String getNameCounty() {
        return nameCounty;
    }

    public String getYearID() {
        return yearID;
    }

    public String getTumorID() {
        return tumorID;
    }

    public String getTypeTumor() {
        return typeTumor;
    }

    public String getSexID() {
        return sexID;
    }

    public String getSexName() {
        return sexName;
    }

    public String getMortality() {
        return mortality;
    }

    public String getPopulation() {
        return population;
    }

    public void setNameState(String nameState) {
        this.nameState = nameState;
    }


    public void setNameCounty(String nameCounty) {
        this.nameCounty = nameCounty;
    }


    public void setTumorID(String tumorID) {
        this.tumorID = tumorID;
    }

    public void setTypeTumor(String typeTumor) {
        this.typeTumor = typeTumor;
    }

    public void setSexID(String sexID) {
        this.sexID = sexID;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

}