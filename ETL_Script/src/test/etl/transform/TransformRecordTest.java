package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransformRecordTest {

    @Test
    void transformData() {
        ArrayList<Sex> sexTable = new ArrayList<>();
        ArrayList<Years> yearTable= new ArrayList<>();
        ArrayList<Tumor> tumorTable= new ArrayList<>();
        ArrayList<Locality> localityTable= new ArrayList<>();
        ArrayList<Mortality> mortalityTable= new ArrayList<>();

        ArrayList<CleanedRecord> dataset = new ArrayList<>();

        CleanedRecord row1 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2000, "1","Tumore Cervello",
                "1","Uomo", 125,100500);

        CleanedRecord row2 = new CleanedRecord("1234","Puglia",
                "12346","Brindisi", 2001, "2","Tumore Pancreas",
                "2","Donna", 125,100500);

        CleanedRecord row3 = new CleanedRecord("1234","Puglia",
                "12346","Brindisi", 2002, "3","Tumore Fegato",
                "2","Donna", 125,100500);

        CleanedRecord row4 = new CleanedRecord("1234","Puglia",
                "12347","Lecce", 2003, "2","Tumore Pancreas",
                "1","Uomo", 125,100500);

        CleanedRecord row5 = new CleanedRecord("1234","Puglia",
                "12347","Taranto", 2001, "4","Tumore Polmoni",
                "2","Donna", 125,100500);

        dataset.add(row1);
        dataset.add(row2);
        dataset.add(row3);
        dataset.add(row4);
        dataset.add(row5);

        TransformRecord.transformData(dataset,sexTable,yearTable,tumorTable,localityTable,mortalityTable);

        boolean result = true;

        if(!(sexTable.size() == 2 && yearTable.size() == 4 &&
                tumorTable.size() == 4 && localityTable.size() == 4 && mortalityTable.size() == 5)){
            result = false;
        }

        assertTrue(result);
    }
}