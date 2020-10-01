package etl.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    State[] stateArray = State.values();

    @Test
    void testToString() {
        boolean res = true;

        for (State state : stateArray) {
            if (state.toString().equals("")) {
                res = false;
                break;
            }
        }
        assertTrue(res);
    }

    @Test
    void getFIPSNotEmpty() {
        boolean res = true;

        for (State state : stateArray) {
            if (state.getFIPS().equals("")) {
                res = false;
                break;
            }
        }
        assertTrue(res);
    }




}