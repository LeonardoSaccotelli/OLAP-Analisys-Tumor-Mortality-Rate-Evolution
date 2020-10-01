package etl.extract;

import etl.data.County;
import etl.extract.ReadCountiesFile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadCountiesFileTest {

    @Test
    void readCountyFileCheckNumberRows() {
        int rows = ReadCountiesFile.readCountyFile().size();
        assertEquals(3141, rows);
    }

    @Test
    void readCountyFileCheckListNotNull(){
        ArrayList<County> list = ReadCountiesFile.readCountyFile();
        assertNotNull(list);
    }
}