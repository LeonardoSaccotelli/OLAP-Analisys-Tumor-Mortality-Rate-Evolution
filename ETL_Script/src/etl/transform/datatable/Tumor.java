package etl.transform.datatable;

/**
 * Classe utilizzata per difinire la struttura della
 * tabella delle dimensioni Tumor.
 */
public class Tumor {

    private final String IDTumor;
    private final String TumorName;

    public Tumor(String IDTumor, String tumorName) {
        this.IDTumor = IDTumor;
        this.TumorName = tumorName;
    }

    public String getIDTumor() {
        return IDTumor;
    }

    public String getTumorName() {
        return TumorName;
    }

    @Override
    public String toString() {
        return "Tumor{" +
                "IDTumor='" + IDTumor + '\'' +
                ", TumorName='" + TumorName + '\'' +
                '}';
    }

    public boolean equals (Object otherObjet){
        boolean isEquals = false;

        if((otherObjet instanceof Tumor)){
            Tumor otherTumor = (Tumor)otherObjet;
            isEquals = this.IDTumor.equals(otherTumor.IDTumor)
                        && this.TumorName.equals(otherTumor.TumorName);
        }
        return isEquals;
    }
}
