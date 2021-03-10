package kr.ac.kaist.se.controller.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class to read a map file
 * @author ymbaek
 */
public class MapFileReader {
    /**
     * A method to read a map file
     * @param fileName  Name(dir and file name) of a file
     * @return String-based contents of the file read
     */
    public String readMapFile(String fileName){

        String mapInitString = "";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null){
                sb.append(line);
                line = br.readLine();
            }
            mapInitString = sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapInitString;
    }
}
