package kr.ac.kaist.se.controller.util;

import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimVar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * A class to manage keys of a map
 * @author ymbaek
 */
public class MapKeyManager {

    private HashMap<String, ArrayList<DataVar>> mapLocInfo;
    private ArrayList<DimVar> mapDimVars;

    public MapKeyManager(HashMap<String, ArrayList<DataVar>> mapLocInfo,
                         ArrayList<DimVar> mapDimVars) {
        this.mapLocInfo = mapLocInfo;
        this.mapDimVars = mapDimVars;
    }



    /**
     * A method to get dim values from string-based key (e.g., 3,2,FLOOR_3)
     * Size of returned list should be same as dimVarList.size()
     *
     * @param key key to be decomposed
     * @return A list of dim values of the given key
     */
    public ArrayList<String> getDimValuesFromKey(String key) {
        ArrayList<String> dimValListTrimmed = new ArrayList<>();
        ArrayList<String> dimValList = new ArrayList<>(Arrays.asList(key.split(",")));

        //Trim the value strings
        trimStrings(dimValList, dimValListTrimmed);

        return dimValListTrimmed;
    }

    public ArrayList<DimVar> getDimVarsFromKey(String key) {
        //Ex. key: (1,0,FLOOR_1)
        ArrayList<DimVar> dimVars = new ArrayList<>();

        ArrayList<String> valuesOfKey = getDimValuesFromKey(key);

        int index = 0;
        for (DimVar mapDimVar : mapDimVars){
            DimVar newDimVar = (DimVar) mapDimVar.clone();

            newDimVar.setDataCurValue(valuesOfKey.get(index));

            dimVars.add(newDimVar);

            index++;
        }

        return dimVars;
    }

    /**
     * A method to create a key string based on a list of strings (dimValues)
     *
     * @param dimValues a list that stores string values for every dim
     * @return key string generated
     */
    public String makeKeyFromDimValue(ArrayList<String> dimValues) {
        String keyToBeReturned = "";

        int index = 0;
        for (String dimValue : dimValues) {
            keyToBeReturned += dimValue;
            if (index + 1 < dimValues.size()) {
                keyToBeReturned += ",";
            }
            index++;
        }

        return keyToBeReturned;
    }

    /**
     * A method to trim a list of string (ArrayList<String>)
     * @param stringList            List of strings
     * @param stringListTrimmed     List of trimmed strings
     */
    private void trimStrings(ArrayList<String> stringList, ArrayList<String> stringListTrimmed) {
        for (String aString : stringList) {
            stringListTrimmed.add(aString.trim());
        }
    }
}
