package kr.ac.kaist.se.controller.util;

import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimensionVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class MapBuilder {

    /*
    RULE (Syntax)
    ------------------------
    for([dim_value_cond])_[process]{[]}
    ------------------------
    1) Target specifier
        - for:
        ([dim_value_cond])
            Ex.
                for(ALL)
                for(floorNum==B1)
                for(
    2) Target
        - DimValueCond: Condition of a dimension value
            [target-keyword]|[[targetLocDimVarId] + [<|<=|==|>=|>] + [targetLocDimVarValue]]
                - targetLocDimVarId: Id of a LocDimensionVar
                - targetLocDimVarValue: Value (curValue) of a LocDimensionVar
            Ex.
                ALL
                floorNum==B1
                xPos==1

    ) Initialization
        - init: initialize a location as
            init{[targetLocDataVarId]:[targetLocDataVarValue]}
                - targetLocDataVarId: Id of a DataVar
                - targetLocDataVarValue: Value to be assigned
    3) Keyword
        A. target-keyword
            - ALL: all locations
            - (ALL_NUMERICAL_DIMS) *not implemented
            - (ALL_ENUM_DIMS) *not implemented
        -
    ------------------------
    1) for: target specifier
        For([DimValueCond])
    ------------------------
    Example:
        for(ALL)_init{dirtLevelVar:0,isWallBarVar:0,isChargingStationVarVar:0};
        for(floorNum==B1)_init{dirtLevelVar:3};
        for(xPos==0)_init{dirtLevelVar:-1,isWallBar:1,isChargingStationVar:0};
     */

    public void updateMapData(ArrayList<DimensionVar> mapDimVars,
                         ArrayList<DataVar> mapDataVars,
                         HashMap<String, ArrayList<DataVar>> hashmapToBeUpdated,
                         ArrayList<String> mapInitQueries){

//        /* EX) mapInitQueries:
//            for(ALL)_init{dirtLevelVar:0,isWallBar:0,isChargingStationVar:0};
//            for(xPos==1)_init{dirtLevelVar:3};
//            for(xPos==3&&yPos==3)_init{isChargingStationVar:1};
//         */
//
//        for (String mapInitQuery : mapInitQueries){
//            /* EX) mapInitQuery:
//                for(ALL)_init{dirtLevelVar:0,isWallBar:0,isChargingStationVar:0};
//             */
//            String[] decomposedQuery = mapInitQuery.split("_");
//
//
//            String locDimInfo = decomposedQuery[0].substring(decomposedQuery[0].indexOf("(") + 1, decomposedQuery[0].indexOf(")"));
//            String locDataInfo = decomposedQuery[1].substring(decomposedQuery[1].indexOf("{") + 1, decomposedQuery[1].indexOf("}"));
//
//            /*
//            EX) locDimInfo:
//                ALL
//                xPos==1
//                xPos==3&&yPos==3
//            EX) locDataInfo:
//                 dirtLevelVar:0,isWallBar:0,isChargingStationVar:0
//                 dirtLevelVar:3
//                 isChargingStationVar:1
//             */
//
//
//            //If locDimInfo is "ALL" (i.e., for all location points)
//            //It is not needed to find dimensions from mapDimVars
//            if (locDimInfo.equals("ALL")){
//                /* EX) dirtLevelVar:0,isWallBar:0,isChargingStationVar:0 */
//
//                //Get all locData assignment information
//                ArrayList<String> locDataAssignmentQueryList = new ArrayList<>(Arrays.asList(locDataInfo.split(",")));
//
//                //For all locData assignment queries
//                for (String locDataAssignmentQuery: locDataAssignmentQueryList){
//                    /* EX) dirtLevelVar:0 */
//
//                    String targetLocDataVarId = locDataAssignmentQuery.split(":")[0].trim();      /* EX) dirtLevelVar */
//                    String targetLocDataVarValue = locDataAssignmentQuery.split(":")[1].trim();     /* EX) 0 */
//
//                    //DataVars for a single location point
//                    ArrayList<DataVar> pointDataVars = new ArrayList<>();
////
////                    DataVar targetLocDataVar = null;
////
////                    for (DataVar mapDataVar : mapDataVars){
////                        //For all mapDataVars, add them to pointDataVars
////                        pointDataVars.add((DataVar)mapDataVar.clone());
////
////                        //If
////                        if (mapDataVar.getVarId().equals(targetLocDataVarId)){
////                            targetLocDataVar = mapDataVar;
////                        }
////                    }
////
////                    if (targetLocDataVar != null){
////
////                    }
//
//
//                    //Only if pointDataVa
//                    if (pointDataVars.size() != 0) {
//                        //Obtain all keys from the hashmap
//                        Set<String> keys = hashmapToBeUpdated.keySet();
//
//                        //Update values of the keys obtained
//                        for (String key : keys) {
//                            hashmapToBeUpdated.put(key, pointDataVars);
//                        }
//                    }
//
//                }
//            }
//
//            //locDimInfo is not "ALL" (e.g., for(xPosVar==3&&yPosVar==3))
//            //mapDimVars should be retrieved to find a proper dimension(s).
//            else{
//                //split by "&&" or "||"
//                ArrayList<String> dimValueCondList = new ArrayList<>(Arrays.asList(locDimInfo.split("&&|\\|\\|")));
//
//                for (int i = 0; i < dimValueCondList.size() - 1; i++){
//
//                }
//
//                /* EX)
//                    xPosVar==3
//                    yPosVar==3
//                */
//
//                for (String dimValueCond : dimValueCondList){
//                    /* EX) xPos==3 */
//
//                    String dimVarId = dimValueCond.split("==")[0].trim();       //xPosVar
//                    String dimVarValue = dimValueCond.split("==")[1].trim();    //3
//
//
//                    ArrayList<Integer> dimVarIndex = new ArrayList<>(mapDataVars.size());
//                    int index = 0;
//                    for (DimensionVar dimVar : mapDimVars){
//                        //if xPos found
//                        if (dimVar.getVarId().equals(dimVarId)){
//                            dimVarIndex.add(index);
//                            break;
//                        }
//                        index++;
//                    }
//
//                    //(3, 3, 0)
//
//
//
//
//
//                    //(0,3,"B1")
//                    mapDimVars.size(); //3
//
//                    String keyString = "(";
//
//
//
//                    keyString += ")";
//                }
//
//
//
//                for (DimensionVar dimVar : mapDimVars){
//
//                }
//
//            }
//        }


    }


}
