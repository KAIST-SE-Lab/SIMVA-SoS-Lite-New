package kr.ac.kaist.se.controller.util;

import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * A class to build, initialize, and update a map
 *
 * @author ymbaek
 */
public class MapBuilder {


    private MapFileReader fileReader;
    private MapKeyManager keyManager;

    private HashMap<String, ArrayList<DataVar>> mapLocInfoToBeUpdated;

    private String mapInitString;


    public MapBuilder(String mapInitFileName,
                      HashMap<String, ArrayList<DataVar>> mapLocInfo) {
        this.mapLocInfoToBeUpdated = mapLocInfo;

        fileReader = new MapFileReader();
        mapInitString = fileReader.readMapFile(mapInitFileName);

//        System.out.println(mapInitString.length());
    }


    /**
     * A method to update map data hashmap,
     * based on mapInitString (initialization queries), mapDimVars, and mapDataVars
     *
     * @param mapDimVars            Dimension variables of a map
     * @param mapDataVars           Data variables of a map
     */
    public void updateMapData(ArrayList<DimVar> mapDimVars,
                              ArrayList<DataVar> mapDataVars) {

        keyManager = new MapKeyManager(mapLocInfoToBeUpdated, mapDimVars);

        /* Hashmap-related variables */
        Set<String> wholeKeySet = mapLocInfoToBeUpdated.keySet();   //A set of keys from mapLocInfo of a map
        ArrayList<String> matchingKeySet;                           //Matching keys to be updated

        if (mapInitString.length() > 0) {

            /* Input query for initialization/update */
            ArrayList<String> mapInitQueryList = new ArrayList<>(Arrays.asList(mapInitString.trim().split(";")));

            System.out.println(mapInitQueryList.size() + "|" + mapInitQueryList);

            //For every query divided by ";"
            for (String mapInitQuery : mapInitQueryList) {
                //Trim for every query
                mapInitQuery = mapInitQuery.trim();

                String setString = "";      //SET(setString)
                String whereString = "";    //WHERE(whereString)

                /* Clauses parsed from the query (mapInitQuery) */
                ArrayList<String> datavarAssignmentClauses = new ArrayList<>(); //Assignment queries for dataVars
                ArrayList<String> dimvarConditionClauses = new ArrayList<>();   //Conditions of dimVars

                /* Indices and values of conDimVars and dataVars */
                ArrayList<Integer> condDimVarIndices = new ArrayList<>();
                ArrayList<String> condDimVarValues = new ArrayList<>();

                ArrayList<Integer> dataVarIndices = new ArrayList<>();
                ArrayList<String> dataVarValues = new ArrayList<>();

                //List of datavars to be stored in the hashmap (mapLocInfoToBeUpdated)
                ArrayList<DataVar> locDataVars = new ArrayList<>();



                /* PHASE 01. Get setString (SET(setString)) */
                setString = mapInitQuery.split("WHERE")[0].split("SET")[1].trim();
                setString = setString.replace("(", "").replace(")", "").trim();

                trimStrings(new ArrayList<>(Arrays.asList(setString.split(","))), datavarAssignmentClauses);


                /* PHASE 02. Check conditions based on whereString */

                //If there is WHERE clause, we have to check <dimvarConditionClauses(s)>
                if (mapInitQuery.contains("WHERE")) {

                    /* Get whereString (WHERE(whereString)) */
                    whereString = mapInitQuery.split("WHERE")[1].trim();
                    whereString = whereString.replace("(", "").replace(")", "").trim();

                    //WHERE(dimvar_cond)
                    if (!whereString.equals("ALL")) {
                        trimStrings(new ArrayList<>(Arrays.asList(whereString.split("&&"))), dimvarConditionClauses);

                        parseDimVarConditionClauses(dimvarConditionClauses, mapDimVars, condDimVarIndices, condDimVarValues);

                        //Matching keys are found based on dimvarConditions
                        matchingKeySet = findMatchingKeys(wholeKeySet, condDimVarIndices, condDimVarValues);

                        //Update the hashmap (mapLocInfoToBeUpdated)
                        updateDataVarsOfHashMap(datavarAssignmentClauses,
                                mapDataVars,
                                matchingKeySet,
                                locDataVars,
                                dataVarIndices,
                                dataVarValues);

                    }
                    //WHERE(ALL)
                    else {
                        //In this case, parsing dimVarConditions (where clause) is not needed.
                        //Since this is for all points, wholeKeySet itself will be matchingKeySet
                        matchingKeySet = new ArrayList<>(wholeKeySet);

                        //Update the hashmap (mapLocInfoToBeUpdated)
                        updateDataVarsOfHashMap(datavarAssignmentClauses,
                                mapDataVars,
                                matchingKeySet,
                                locDataVars,
                                dataVarIndices,
                                dataVarValues);

                    }
                }
                //If there is no WHERE clause, it means ALL
                else if (!mapInitQuery.contains("WHERE")) {
                    //In this case, parsing dimVarConditions (where clause) is not needed.
                    //Since this is for all points, wholeKeySet itself will be matchingKeySet
                    matchingKeySet = new ArrayList<>(wholeKeySet);

                    //Update the hashmap (mapLocInfoToBeUpdated)
                    updateDataVarsOfHashMap(datavarAssignmentClauses,
                            mapDataVars,
                            matchingKeySet,
                            locDataVars,
                            dataVarIndices,
                            dataVarValues);
                }

                System.out.println("\nupdateMapData Query: " + mapInitQuery);
                printMapLocHashMap(mapLocInfoToBeUpdated);

            }
        }
    }


    /**
     * A method to update dataVars of a given hashmap (here, mapLocInfoToBeUpdated)
     * @param datavarAssignmentClauses  Parsed clauses for data var assignment/update (used for parseDataVarAssignmentClauses)
     * @param mapDataVars               dataVars of a map (used for parseDataVarAssignmentClauses)
     * @param matchingKeySet            Matching keys found (used for updateLocDataVars)
     * @param locDataVars               List of datavars to be stored in the hashmap (used for updateLocDataVars)
     * @param dataVarIndices            Indices of dataVars to be updated (used for parseDataVarAssignmentClauses, updateLocDataVars)
     * @param dataVarValues             Values of dataVars to be updated (used for parseDataVarAssignmentClauses, updateLocDataVars)
     */
    private void updateDataVarsOfHashMap(ArrayList<String> datavarAssignmentClauses,
                                         ArrayList<DataVar> mapDataVars,
                                         ArrayList<String> matchingKeySet,
                                         ArrayList<DataVar> locDataVars,
                                         ArrayList<Integer> dataVarIndices,
                                         ArrayList<String> dataVarValues){

        //Parse clauses for dataVar assignment
        parseDataVarAssignmentClauses(datavarAssignmentClauses, mapDataVars, dataVarIndices, dataVarValues);

        //Actually update locDataVars
        updateLocDataVars(matchingKeySet, locDataVars, dataVarIndices, dataVarValues);
    }




    /**
     * A method to parse clauses of dimVar conditions (e.g., xPosVar==1&&yPosVar==2)
     * @param dimvarConditionClauses    Parsed clauses for representing conditions of dimVars
     * @param mapDimVars                dimVars of a map
     * @param condDimVarIndices         Indices of dimVars to be checked
     * @param condDimVarValues          Values of dimVars to be checked
     */
    private void parseDimVarConditionClauses(ArrayList<String> dimvarConditionClauses,
                                             ArrayList<DimVar> mapDimVars,
                                             ArrayList<Integer> condDimVarIndices,
                                             ArrayList<String> condDimVarValues) {
        //For each dimvarConditionClauses (e.g., xPosVar==3)
        for (String cond : dimvarConditionClauses) {
//                        System.out.print(cond + ": ");

            String dimId = cond.split("==")[0].trim();
            String dimValue = cond.split("==")[1].trim();

            //For enumeration case that contains double quotation marks (e.g., "FLOOR_B1")
            //Remove the double-quotation marks
            if (dimValue.contains("\"")) {
                dimValue = dimValue.replaceAll("\"", "");
            }

            int index = 0;
            for (DimVar mapDimVar : mapDimVars) {
                if (mapDimVar.getVarId().equals(dimId)) {
                    condDimVarIndices.add(index);
                    condDimVarValues.add(dimValue);
                }
                index++;
            }
        }
    }

    /**
     * A method to parse clauses of dataVar assignment/update (e.g., isWall=1)
     * @param datavarAssignmentClauses  Parsed clauses for data var assignment/update
     * @param mapDataVars               dataVars of a map
     * @param dataVarIndices            Indices of dataVars to be updated
     * @param dataVarValues             Values of dataVars to be updated
     */
    private void parseDataVarAssignmentClauses(ArrayList<String> datavarAssignmentClauses,
                                               ArrayList<DataVar> mapDataVars,
                                               ArrayList<Integer> dataVarIndices,
                                               ArrayList<String> dataVarValues) {

        for (String assignmentQuery : datavarAssignmentClauses) {

            String dataVarId = assignmentQuery.split("=")[0].trim();
            String dataVarValue = assignmentQuery.split("=")[1].trim();

            //For enumeration case that contains double quotation marks (e.g., "FLOOR_B1")
            //Remove the double-quotation marks
            if (dataVarValue.contains("\"")) {
                dataVarValue = dataVarValue.replaceAll("\"", "");
            }

            int index = 0;
            for (DataVar mapDataVar : mapDataVars) {
                if (mapDataVar.getVarId().equals(dataVarId)) {
                    dataVarIndices.add(index);
                    dataVarValues.add(dataVarValue);
                }
                index++;
            }

        }
    }


    /**
     * A method to actually update dataVars of hashMap data
     * @param matchingKeySet            Matching keys found
     * @param locDataVars               List of datavars to be stored in the hashmap
     * @param dataVarIndices            Indices of dataVars to be updated
     * @param dataVarValues             Values of dataVars to be updated
     */
    private void updateLocDataVars(ArrayList<String> matchingKeySet,
                                   ArrayList<DataVar> locDataVars,
                                   ArrayList<Integer> dataVarIndices,
                                   ArrayList<String> dataVarValues) {
        for (String matchingKey : matchingKeySet) {
            ArrayList<DataVar> dataVars = mapLocInfoToBeUpdated.get(matchingKey);

            locDataVars.clear();

            int dataVarIndex = 0;
            for (DataVar dataVar : dataVars) {

                DataVar newDataVar = (DataVar) dataVar.clone();

                int innerIndex = 0;
                for (Integer index : dataVarIndices) {

                    if (dataVarIndex == index) {
                        newDataVar.setDataCurValue(dataVarValues.get(innerIndex));
                    }

                    innerIndex++;
                }
                dataVarIndex++;
                locDataVars.add(newDataVar);
            }

            mapLocInfoToBeUpdated.put(matchingKey, locDataVars);
        }
    }


    /**
     * A method to find a list(set) of matching keys from a whole key sets,
     * based on varIndices and varValues.
     *
     * @param wholeKeySets A pool that has a whole set of keys
     * @param varIndices   List of indices contained in dimvar_cond (1 if yPosVar==3)
     * @param varValues    List of values contained in dimvar_cond (3 if yPosVar==3)
     * @return  Matching keys found
     */
    private ArrayList<String> findMatchingKeys(Set<String> wholeKeySets, ArrayList<Integer> varIndices, ArrayList<String> varValues) {
        ArrayList<String> matchingKeys = new ArrayList<>();
        boolean isMatching;

        for (String key : wholeKeySets) {
            isMatching = true;

            int varIndicesIndex = 0;
            for (Integer index : varIndices) {
                //If the key matches the given condition
                if (!(keyManager.getDimValuesFromKey(key).get(index).equals(varValues.get(varIndicesIndex)))) {
                    isMatching = false;
                }
                varIndicesIndex++;
            }

            if (isMatching) {
                matchingKeys.add(key);
            }
        }

        return matchingKeys;
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



    /**
     * A method to print a mapLocInfo hashmap
     * @param mapLocHashMap A hashmap to be printed
     */
    private void printMapLocHashMap(HashMap<String, ArrayList<DataVar>> mapLocHashMap) {
        for (HashMap.Entry<String, ArrayList<DataVar>> pair : mapLocHashMap.entrySet()) {
            System.out.print("\t" + pair.getKey() + "|");
            for (DataVar aDataVar : pair.getValue()) {
                System.out.print(aDataVar.getVarId() + "(" + aDataVar.getDataCurValue() + ")");
            }
            System.out.println();
        }
    }


}
