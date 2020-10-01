package etl.transform.datatable;

import java.util.Objects;

/**
 * Classe utilizzata per difinire la struttura della
 * tabella dei fatti Mortality.
 */
public class Mortality {
    private final String locality;
    private final int years;
    private final String sex;
    private final String tumor;
    private final float mortalityRate;
    private final int populationEstimate;
    private final float deathsEstimate;

    public Mortality(String locality, int years, String sex, String tumor, float mortalityRate, int populationEstimate, float deathsEstimate) {
        this.locality = locality;
        this.years = years;
        this.sex = sex;
        this.tumor = tumor;
        this.mortalityRate = mortalityRate;
        this.populationEstimate = populationEstimate;
        this.deathsEstimate = deathsEstimate;
    }

    public String getLocality() {
        return locality;
    }

    public int getYears() {
        return years;
    }

    public String getSex() {
        return sex;
    }

    public String getTumor() {
        return tumor;
    }

    public float getMortalityRate() {
        return mortalityRate;
    }

    public int getPopulationEstimate() {
        return populationEstimate;
    }

    public float getDeathsEstimate() {
        return deathsEstimate;
    }

    public boolean equals (Object otherObject){
        boolean isEquals = false;

        if(otherObject instanceof Mortality){
            Mortality otherMortality = (Mortality) otherObject;
            isEquals = this.locality.equals(otherMortality.locality)&&
                    this.years == otherMortality.years &&
                    this.sex.equals(otherMortality.sex) &&
                    this.tumor.equals(otherMortality.tumor);

        }
        return isEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(locality, years, sex, tumor);
    }
}
