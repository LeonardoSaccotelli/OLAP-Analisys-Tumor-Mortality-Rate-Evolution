package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Locality;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransformStateCountyToTableLocalityTest {

    @Test
    void trasformStateCountyToLocalityTableWithSameObject() {
        ArrayList<Locality> localityTableTest = new ArrayList<>();

        CleanedRecord rowTest = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Liver cancer",
                "1","Male", 125,100500);

        CleanedRecord rowTest1 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Liver cancer",
                "1","Male", 125,100500);

        TransformStateCountyToTableLocality.trasformStateCountyToLocalityTable(rowTest,localityTableTest);
        TransformStateCountyToTableLocality.trasformStateCountyToLocalityTable(rowTest1,localityTableTest);

        assertEquals(1, localityTableTest.size());
    }

    @Test
    void trasformStateCountyToLocalityTableWithDifferentObject() {
        ArrayList<Locality> localityTableTest = new ArrayList<>();

        CleanedRecord rowTest = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Liver cancer",
                "1","Male", 125,100500);

        CleanedRecord rowTest1 = new CleanedRecord("1234","Puglia",
                "12346","Brindisi", 2010, "2","Liver cancer",
                "1","Male", 125,100500);

        TransformStateCountyToTableLocality.trasformStateCountyToLocalityTable(rowTest,localityTableTest);
        TransformStateCountyToTableLocality.trasformStateCountyToLocalityTable(rowTest,localityTableTest);

        assertEquals(1, localityTableTest.size());
    }

}