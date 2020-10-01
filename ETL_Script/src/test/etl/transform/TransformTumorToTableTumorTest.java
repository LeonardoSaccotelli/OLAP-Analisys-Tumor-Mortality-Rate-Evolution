package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Tumor;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransformTumorToTableTumorTest {

    @Test
    void transformTumorWithSameObject() {
        ArrayList<Tumor> tumorTableTest = new ArrayList<>();

        CleanedRecord rowTest = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Liver cancer",
                "1","Male", 125,100500);

        TransformTumorToTableTumor.transformTumor(rowTest,tumorTableTest);
        TransformTumorToTableTumor.transformTumor(rowTest,tumorTableTest);

        assertEquals(1, tumorTableTest.size());
    }

    @Test
    void transformTumorWithDifferentObject() {

        ArrayList<Tumor> tumorTableTest = new ArrayList<>();

        CleanedRecord[] listOfObject = new CleanedRecord[31];

        etl.data.Tumor[] listOfTumor = etl.data.Tumor.values();

        for(int i=0; i < listOfTumor.length; i++){
            listOfObject[i] = new CleanedRecord("1234","Puglia",
                    "12345","Bari", 2010, listOfTumor[i].getIDTumor(),listOfTumor[i].toString(),
                    "1","Male", 125,100500);
        }

        for(int i=0; i< listOfObject.length; i++){
            TransformTumorToTableTumor.transformTumor(listOfObject[i],tumorTableTest);
        }

        assertEquals(31,tumorTableTest.size());

    }
}