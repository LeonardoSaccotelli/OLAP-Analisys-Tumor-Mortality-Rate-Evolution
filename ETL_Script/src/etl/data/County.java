package etl.data;

/**
 * La classe County viene utilizzata per rappresentare in modo
 * omogeneo le informazioni lette dal file di testo listOfCounty.
 * Questo passaggio ci serve per verificare la correttezza o meno
 * dei dati riguardo alle contee presente nei dataset.
 */
public class County {
    private final String nameState;
    private final String nameCounty;
    private final String countyFIPS;

    public County(String nameState, String nameCounty, String countyFIPS) {
        this.nameState = nameState;
        this.countyFIPS = countyFIPS;
        this.nameCounty = nameCounty;
    }

    public String getNameState() {
        return nameState;
    }

    public String getCountyFIPS() {
        return countyFIPS;
    }

    public String getNameCounty() {
        return nameCounty;
    }

    public String toString(){
        return this.nameCounty;
    }
}
