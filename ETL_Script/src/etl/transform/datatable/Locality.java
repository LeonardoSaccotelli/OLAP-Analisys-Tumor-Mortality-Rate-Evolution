package etl.transform.datatable;

import java.util.Objects;

/**
 * Classe utilizzata per difinire la struttura della
 * tabella delle dimensioni Locality.
 */
public class Locality {
    private final String FIPSCounty;
    private final String localityName;

    public Locality(String FIPSCounty, String localityName) {
        this.FIPSCounty = FIPSCounty;
        this.localityName = localityName;
    }

    public String getFIPSCounty() {
        return FIPSCounty;
    }

    public String getLocalityName() {
        return localityName;
    }

    @Override
    public String toString() {
        return "Locality{" +
                "FIPSCounty='" + FIPSCounty + '\'' +
                ", localityName='" + localityName + '\'' +
                '}';
    }

    public boolean equals (Object otherObject){
        boolean isEquals = false;

        if((otherObject instanceof Locality)){

            Locality otherLocality = (Locality) otherObject;

            isEquals = this.FIPSCounty.equals(otherLocality.FIPSCounty)
                        && this.localityName.equals(otherLocality.localityName);
        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(FIPSCounty, localityName);
    }
}
