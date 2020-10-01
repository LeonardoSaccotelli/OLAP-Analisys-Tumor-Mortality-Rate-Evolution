package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Sex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransformSextoTableSexTest {


    @Test
    void transformSexWithSameObject() {
        ArrayList<Sex> sexTableTest = new ArrayList<>();

        CleanedRecord rowTest = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Tumore Cervello",
                "1","Male", 125,100500);

        TransformSextoTableSex.transformSex(rowTest,sexTableTest);
        TransformSextoTableSex.transformSex(rowTest,sexTableTest);

        assertEquals(1, sexTableTest.size());
    }

    @Test
    void transformSexWithDifferentObject() {
        ArrayList<Sex> sexTableTest = new ArrayList<>();

        CleanedRecord rowTest = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Tumore Cervello",
                "1","Male", 125,100500);

        CleanedRecord rowTest2 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Tumore Cervello",
                "2","Female", 125,100500);

        TransformSextoTableSex.transformSex(rowTest,sexTableTest);
        TransformSextoTableSex.transformSex(rowTest2,sexTableTest);

        assertEquals(2, sexTableTest.size());
    }

}