package etl.data;

/**
 * La classe è utilizzata per rappresentare un
 * singolo record letto da un dataset dopo la fase di
 * pulitura del record.
 * Un oggetto di questa classe rappresenta un record del dataset
 * dopo un'attenta fase di pulitura e riparazione del dataset.
 * Solo dopo la pulitura/riparazione e ovviamente la conversione
 * dei dati ai loro tipi opportuni si procederà ad istanziare
 * questa classe.
 * ====================================================================
 * La classe fornisce i metodi get e set, e ovvimente i costruttori
 * della classe.
 * */

public class CleanedRecord {

    private final String FIPS;
    private final String nameState;
    private final String FIPSCounty;
    private final String nameCounty;
    private final int yearID;
    private final String tumorID;
    private final String typeTumor;
    private final String sexID;
    private final String sexName;
    private final float mortality;
    private final int population;

    public CleanedRecord(String myFIPS, String myNameState, String myFIPSCounty, String myNameCounty,
                     int myYearID, String myTumorID,String myTypeTumor, String mySexID,
                     String mySexName, float myMortality, int myPopulation){
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

    public String getNameState() {
        return nameState;
    }

    public String getFIPSCounty() {
        return FIPSCounty;
    }

    public String getNameCounty() {
        return nameCounty;
    }

    public int getYearID() {
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

    public float getMortality() {
        return mortality;
    }

    public int getPopulation() {
        return population;
    }
}
