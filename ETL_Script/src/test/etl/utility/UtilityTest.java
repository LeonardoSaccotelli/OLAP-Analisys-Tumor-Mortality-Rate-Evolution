package etl.utility;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test //OK
    void stringIsDigitWithRandomDoubleNumber() {
        String stringTest = Double.toString(Math.random()*1000000);
        boolean res = Utility.stringIsDigit(stringTest);
        assertTrue(res);
    }

    @Test //OK
    void stringIsDigitWithRandomIntegerNumber() {
        Random random = new Random();
        String stringTest = Integer.toString(random.nextInt(100000001 - 1) + 1);
        boolean res = Utility.stringIsDigit(stringTest);
        assertTrue(res);
    }

    @Test //OK
    void stringIsDigitWithRandomStringLetter() {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String stringTest = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        boolean res = Utility.stringIsDigit(stringTest);
        assertFalse(res);
    }

    @Test //OK
    void stringIsDigirWithNullParameters(){
        assertThrows(NullPointerException.class,
                ()-> Utility.stringIsDigit(null));
    }

    @Test //OK
    void stringIsDigitWithEmptyField(){
        String stringTest = "";
        boolean res = Utility.stringIsDigit(stringTest);
        assertFalse(res);
    }


    /*@Test
    void stringBinarySearchWithRandomStringArray() {

        String[] listString = new String[10];

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        for(int j = 0; j < listString.length; j++) {
            listString[j]  = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }

        Arrays.sort(listString);

        String findString = listString[random.nextInt(listString.length)];

        int res = Utility.stringBinarySearch(listString,findString,0,listString.length-1);

        System.out.print(res);
        assertNotEquals(-1, res);
    }*/

    @Test //OK
    void stringBinarySearchWithRandomStringArrayNoStringInArray() {
        String[] listString = new String[10];

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        for(int j = 0; j < listString.length; j++) {
            listString[j]  = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }

        Arrays.sort(listString);

        String findString = random.ints(leftLimit, rightLimit + 1)
                            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                            .limit(targetStringLength)
                            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                            .toString();

        int res = Utility.stringBinarySearch(listString,findString,0,listString.length-1);

        assertEquals(-1, res);
    }

    @Test //OK
    void stringBinarySearchWithNegativeIndexStartGraterThanEnd() {
        String[] listString = new String[10];
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        for(int j = 0; j < listString.length; j++) {
            listString[j]  = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }

        Arrays.sort(listString);

        String findString = listString[random.nextInt(listString.length)];

        int res = Utility.stringBinarySearch(listString,findString,-(random.nextInt(100)+1),-(random.nextInt(200)+101));

        assertEquals(-1, res);
    }

    @Test //OK
    void stringBinarySearchWithNegativeIndexEngGraterThanStart() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                ()->{
                    String[] listString = new String[10];

                    int leftLimit = 48; // numeral '0'
                    int rightLimit = 122; // letter 'z'
                    int targetStringLength = 10;
                    Random random = new Random();

                    for(int j = 0; j < listString.length; j++) {
                        listString[j]  = random.ints(leftLimit, rightLimit + 1)
                                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                                .limit(targetStringLength)
                                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                .toString();
                    }

                    Arrays.sort(listString);

                    String findString = listString[random.nextInt(listString.length)];

                    Utility.stringBinarySearch(listString,findString,-(random.nextInt(200)+101),-(random.nextInt(100)+1));
                });
    }

    @Test //OK
    void stringBinarySearchWithStartIndexGreaterThanEndIndex() {
        String[] listString = new String[10];

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        for(int j = 0; j < listString.length; j++) {
            listString[j]  = random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
        }

        Arrays.sort(listString);

        String findString = listString[random.nextInt(listString.length)];

        int res = Utility.stringBinarySearch(listString,findString,listString.length,0);

        assertEquals(-1, res);
    }

   @Test //OK
    void stringBinarySearchWithNullFindString() {
       assertThrows(NullPointerException.class,
               ()->{
                   String[] listString = new String[10];

                   int leftLimit = 48; // numeral '0'
                   int rightLimit = 122; // letter 'z'
                   int targetStringLength = 10;
                   Random random = new Random();

                   for(int j = 0; j < listString.length; j++) {
                       listString[j]  = random.ints(leftLimit, rightLimit + 1)
                               .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                               .limit(targetStringLength)
                               .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                               .toString();
                   }

                   Arrays.sort(listString);

                   int start = 0; int end = listString.length-1;

                   Utility.stringBinarySearch(listString,null,start,end);

               });
    }

    @Test //OK
    void stringBinarySearchWithNullArray() {
        assertThrows(NullPointerException.class,
                ()->{
                    String[] listString = new String[10];
                    String findString = "Prova";

                    int start = 0; int end = listString.length-1;

                    Utility.stringBinarySearch(null,findString,start,end);

                });
    }
}