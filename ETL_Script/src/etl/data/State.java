package etl.data;

/**
 * La seguente enumerazione ci consente di creare delle etichette (costanti)
 * al cui interno memorizzano le informazioni relative al nome dello stato e al relativo FIPS.
 */
public enum State {
    ALABAMA("Alabama","1"), ALASKA("Alaska", "2"),
    ARIZONA("Arizona","4"), ARKANSAS("Arkansas","5"),
    CALIFORNIA("California","6"), COLORADO("Colorado","8"),
    CONNECTICUT("Connecticut","9"), DELAWARE("Delaware","10"),
    FLORIDA("Florida","12"), GEORGIA("Georgia","13"),
    HAWAII("Hawaii","15"), IDAHO("Idaho","16"),
    ILLINOIS("Illinois","17"), INDIANA("Indiana", "18"),
    IOWA("Iowa", "19"), KANSAS("Kansas","20"),KENTUCKY("Kentucky","21"),
    LOUISIANA("Louisiana","22"),MAINE("Maine","23"),MARYLAND("Maryland","24"),
    MASSACHUSETTS("Massachusetts","25"), MICHIGAN("Michigan","26"),
    MINNESOTA("Minnesota","27"),MISSISSIPPI("Mississippi","28"),
    MISSOURI("Missouri","29"),MONTANA("Montana","30"),
    NEBRASKA("Nebraska","31"), NEVADA("Nevada", "32"),
    NEW_HAMPSHIRE("New Hampshire","33"), NEW_JERSEY("New Jersey","34"),
    NEW_MEXICO("New Mexico","35"),NEW_YORK("New York","36"),
    NORTH_CAROLINA("North Carolina","37"),NORTH_DAKOTA("North Dakota","38"),
    OHIO("Ohio","39"),OKLAHOMA("Oklahoma","40"),OREGON("Oregon","41"),
    PENNSYLVANIA("Pennsylvania","42"), RHODE_ISLAND("Rhode Island","44"),
    SOUTH_CAROLINA("South Carolina","45"),SOUTH_DAKOTA("South Dakota","46"),
    TENNESSEE("Tennessee","47"),TEXAS("Texas","48"),
    UTAH("Utah","49"),VERMONT("Vermont","50"),VIRGINIA("Virginia","51"),
    WASHINGTON("Washington","53"),WEST_VIRGINIA("West Virginia","54"),
    WISCONSIN("Wisconsin","55"), WYOMING("Wyoming","56");


    private final String name;
    private final String FIPS;

    State(String newName,String newFIPS){
        this.name = newName;
        this.FIPS = newFIPS;
    }

    public String toString(){
        return this.name;
    }

    public String getFIPS(){
        return this.FIPS;
    }
}