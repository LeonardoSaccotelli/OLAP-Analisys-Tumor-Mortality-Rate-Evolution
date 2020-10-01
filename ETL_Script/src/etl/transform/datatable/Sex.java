package etl.transform.datatable;

/**
 * Classe utilizzata per difinire la struttura della
 * tabella delle dimensioni Sex.
 */
public class    Sex {
    private final String IDSex;
    private final String SexName;


    public Sex(String IDSex, String sexName) {
        this.IDSex = IDSex;
        SexName = sexName;
    }

    public String getIDSex() {
        return IDSex;
    }

    public String getSexName() {
        return SexName;
    }

    @Override
    public String toString() {
        return "Sex{" +
                "IDSex='" + IDSex + '\'' +
                ", SexName='" + SexName + '\'' +
                '}';
    }

    public boolean equals (Object otherObject){
        boolean isEquals = false;

        if((otherObject instanceof Sex)){
            Sex otherSex = (Sex)otherObject;
            isEquals = this.IDSex.equals(otherSex.IDSex)
                        && this.SexName.equals(otherSex.SexName);
        }
        return isEquals;
    }
}
