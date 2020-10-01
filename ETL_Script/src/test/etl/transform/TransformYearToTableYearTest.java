package etl.transform;

import etl.data.CleanedRecord;
import etl.transform.datatable.Years;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TransformYearToTableYearTest {

    @Test
    void transformYearWithSameObject() {
        ArrayList<Years> yearsTableTest = new ArrayList<>();

        CleanedRecord rowTest = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Tumore Cervello",
                "1","Male", 125,100500);

        TransformYearToTableYear.transformYear(rowTest,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest,yearsTableTest);

        assertEquals(1, yearsTableTest.size());
    }

    @Test
    void transformYearWithDifferentObject() {
        ArrayList<Years> yearsTableTest = new ArrayList<>();

        CleanedRecord rowTest = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2000, "2","Tumore Cervello",
                "1","Male", 125,100500);

        CleanedRecord rowTest2 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2001, "2","Tumore Cervello",
                "2","Female", 125,100500);

        CleanedRecord rowTest3 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2002, "2","Tumore Cervello",
                "1","Male", 125,100500);

        CleanedRecord rowTest4 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2003, "2","Tumore Cervello",
                "2","Female", 125,100500);

        CleanedRecord rowTest5= new CleanedRecord("1234","Puglia",
                "12345","Bari", 2004, "2","Tumore Cervello",
                "1","Male", 125,100500);

        CleanedRecord rowTest6 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2005, "2","Tumore Cervello",
                "2","Female", 125,100500);

        CleanedRecord rowTest7 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2006, "2","Tumore Cervello",
                "1","Male", 125,100500);

        CleanedRecord rowTest8 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2007, "2","Tumore Cervello",
                "2","Female", 125,100500);

        CleanedRecord rowTest9 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2008, "2","Tumore Cervello",
                "1","Male", 125,100500);

        CleanedRecord rowTest10 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2009, "2","Tumore Cervello",
                "2","Female", 125,100500);

        CleanedRecord rowTest11 = new CleanedRecord("1234","Puglia",
                "12345","Bari", 2010, "2","Tumore Cervello",
                "1","Male", 125,100500);


        TransformYearToTableYear.transformYear(rowTest,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest2,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest3,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest4,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest5,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest6,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest7,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest8,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest9,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest10,yearsTableTest);
        TransformYearToTableYear.transformYear(rowTest11,yearsTableTest);

        assertEquals(11, yearsTableTest.size());
    }
}