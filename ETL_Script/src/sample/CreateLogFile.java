package sample;

import etl.utility.DialogBox;

import java.io.FileWriter;
import java.io.IOException;

public class CreateLogFile {

    public static void writeLogFile(String text){
        try
        {
            String filepath = System.getProperty("user.dir") + "\\" +"log\\log.txt";
            FileWriter fw = new FileWriter(filepath,true); //the true will append the new data
            fw.write(text);//appends the string to the file
            fw.close();
        }
        catch(IOException e)
        {
            DialogBox.exceptionBox(e);
        }
    }
}
