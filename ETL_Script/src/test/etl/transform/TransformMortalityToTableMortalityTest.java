package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Mortality;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransformMortalityToTableMortalityTest {

    @Test
    void transformMortalityToMortalityTable() {
        ArrayList<Mortality> mortalityTableTest = new ArrayList<>();

        CleanedRecord rowTest = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Liver cancer",
                "1","Male", 125,100500);

        CleanedRecord rowTest1 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Liver cancer",
                "1","Male", 125,100500);

        TransformMortalityToTableMortality.transformMortalityToMortalityTable(rowTest,mortalityTableTest);
        TransformMortalityToTableMortality.transformMortalityToMortalityTable(rowTest1,mortalityTableTest);

        assertEquals(2, mortalityTableTest.size());
    }
}