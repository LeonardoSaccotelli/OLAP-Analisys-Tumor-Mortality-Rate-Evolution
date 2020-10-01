package etl.transform.datatable;

/**
 * Classe utilizzata per difinire la struttura della
 * tabella delle dimensioni Years.
 */
public class Years {
    private final int year;

    public Years(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return Integer.toString(this.year);
    }

    public boolean equals (Object otherObject){
        boolean isEquals = false;

        if((otherObject instanceof Years)){
            Years otherYear = (Years)otherObject;
            isEquals = this.year == otherYear.year;
        }
         return isEquals;
    }
}
