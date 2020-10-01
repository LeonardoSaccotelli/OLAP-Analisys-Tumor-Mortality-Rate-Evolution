package etl.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TumorTest {

    Tumor[] tumorArrayTest = Tumor.values();

    @Test
    void testToString() {
        boolean res = true;

        for (Tumor tumor : tumorArrayTest) {
            if (tumor.toString().equals("")) {
                res = false;
                break;
            }
        }
        assertTrue(res);
    }

    @Test
    void getIDTumor() {
        boolean res = true;

        for (Tumor tumor : tumorArrayTest) {
            if (tumor.getIDTumor().equals("")) {
                res = false;
                break;
            }
        }
        assertTrue(res);
    }

    @Test
    void getTumorSexName() {
        boolean res = true;
        for (Tumor tumor : tumorArrayTest) {
            if (tumor.getTumorSexName().equals("")) {
                res = false;
                break;
            }
        }
        assertTrue(res);

    }
}