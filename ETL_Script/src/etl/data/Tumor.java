package etl.data;

/**
 * La seguente enumerazione ci consente di creare delle etichette (costanti)
 * al cui interno memorizzano le informazioni relative al nome del tumore e al relativo identificativo.
 * Gli identificativi posti saranno utilizzati per rendere univoca l'identificazione del tumore nel caso in cui
 * ci fossero dissonanze tra i vari dataset.
 */
public enum Tumor {
    ACUTE_LYMPHOID_LEUKEMIA("Acute lymphoid leukemia","28","Both"),
    ACUTE_MYELOID_LEUKEMIA("Acute myeloid leukemia","30","Both"),
    BLADDER_CANCER("Bladder cancer","20","Both"),
    BRAIN_AND_NERVOUS_SYSTEM_CANCER("Brain and nervous system cancer","21","Both"),
    BREAST_CANCER("Breast cancer","6","Both"),
    CERVICAL_CANCER("Cervical cancer","7","Both"),
    CHRONIC_LYMPHOID_LEUKEMIA("Chronic lymphoid leukemia","29","Both"),
    CHRONIC_MYELOID_LEUKEMIA("Chronic myeloid leukemia","31","Both"),
    COLON_AND_RECTUM_CANCER("Colon and rectum cancer","10","Both"),
    ESOPHAGEAL_CANCER("Esophageal cancer","1","Both"),
    GALLBLADDER_AND_BILIARY_TRACT_CANCER("Gallbladder and biliary tract cancer","13","Both"),
    HODGKIN_LYMPHOMA("Hodgkin lymphoma","24","Both"),
    KIDNEY_CANCER("Kidney cancer","19","Both"),
    LARYNX_CANCER("Larynx cancer","4","Both"),
    LEUKEMIA("Leukemia","27","Both"),
    LIP_AND_ORAL_CAVITY_CANCER("Lip and oral cavity cancer","11","Both"),
    LIVER_CANCER("Liver cancer","3","Both"),
    MALIGNANT_SKIN_MELANOMA("Malignant skin melanoma","15","Both"),
    MESOTHELIOMA("Mesothelioma","23","Both"),
    MULTIPLE_MYELOMA("Multiple myeloma","26","Both"),
    NASOPHARYNX_CANCER("Nasopharynx cancer","12","Both"),
    NON_HODGKIN_LYMPHOMA("Non-Hodgkin lymphoma","25","Both"),
    NON_MELANOMA_SKIN_CANCER("Non-melanoma skin cancer","16","Both"),
    OVARIAN_CANCER("Ovarian cancer","17","Female"),
    PANCREATIC_CANCER("Pancreatic cancer","14","Both"),
    PROSTATE_CANCER("Prostate cancer","9","Male"),
    STOMACH_CANCER("Stomach cancer","2","Both"),
    TESTICULAR_CANCER("Testicular cancer","18","Male"),
    THYROID_CANCER("Thyroid cancer","22","Both"),
    TRACHEAL_BRONCHUS_AND_LUNG_CANCER("Tracheal, bronchus and lung cancer","5","Both"),
    UTERINE_CANCER("Uterine cancer","8","Female");


    private final String typeTumor;
    private final String IDTumor;
    private final String tumorSexName;

    Tumor(String newTypeTumor,String newTumorID, String newTumorSexName){
        this.IDTumor = newTumorID;
        this.typeTumor = newTypeTumor;
        this.tumorSexName = newTumorSexName;
    }

    public String toString(){
        return this.typeTumor;
    }

    public String getIDTumor(){
        return this.IDTumor;
    }

    public String getTumorSexName() {
        return tumorSexName;
    }
}
