package kr.ac.kaist.se.model.sos.geo;

import kr.ac.kaist.se.controller.util.MapBuilder;
import kr.ac.kaist.se.model.abst.geo._SimMap_;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimVar;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class to represent a geographical map of an SoS
 *
 * @author ymbaek
 */
public abstract class SimMap extends _SimMap_ {

    /** Input file for map initialization (.txt) */
    protected String mapInitFile;

    /** ArrayLists for MapDimensions and MapInformation */
    //LocDimensions of this map
    protected ArrayList<DimVar> mapDimVars = new ArrayList<>();
    //LocInformations of this map
    public ArrayList<DataVar> mapDataVars = new ArrayList<>();

    /** Initialization of map */
    //HashMap to store location information
    protected HashMap<String, ArrayList<DataVar>> mapLocInfo = new HashMap<>();


    /** MapBuilder */
    protected MapBuilder mapBuilder; //MapBuilder to initialize and update a map



    public SimMap(String mapId, String mapName, String mapInitFileName) {
        super(mapId, mapName);
        this.mapInitFile = mapInitFileName;

        initMap(mapInitFileName);
    }

    public SimMap(String mapId,
                  String mapName,
                  ArrayList<DimVar> mapDimVars,
                  ArrayList<DataVar> mapDataVars,
                  String mapInitFileName) {
        super(mapId, mapName);
        this.mapDimVars = mapDimVars;
        this.mapDataVars = mapDataVars;
        this.mapInitFile = mapInitFileName;

        initMap(mapInitFileName);
    }

//    public SimMap(String mapId, String mapName) {
//        this.mapId = mapId;
//        this.mapName = mapName;
//        //objLocationHashMap = new HashMap<>();
//
//        initMap();
//    }
//
//    public SimMap(String mapId, String mapName, ArrayList<DimVar> mapDimensions) {
//        this.mapId = mapId;
//        this.mapName = mapName;
//        this.mapDimVars = mapDimensions;
//        //objLocationHashMap = new HashMap<>();
//
//        initMap();
//    }
//
//    public SimMap(String mapId, String mapName, ArrayList<DimVar> mapDimensions, ArrayList<DataVar> mapInfos) {
//        this.mapId = mapId;
//        this.mapName = mapName;
//        this.mapDimVars = mapDimensions;
//        this.mapDataVars = mapInfos;
//
//        initMap();
//    }

    /**
     * Initialization of a map by calling other methods
     */
    protected void initMap(String mapInitFileName){
        /* Initialization of map dimensions (mapDimVars) */
        initMapDimensions();

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) Dimensions (mapDimVars) are initialized (size:" + mapDimVars.size() + ")");


        /* Initialization of map data variables (mapDataVars) */
        initMapInformation();

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) MapInformation (mapDataVars) is initialized (size:" + mapDataVars.size() + ")");


        /* Initialization of map location information (mapLocInfo) */
        initMapLocInfo(mapInitFileName);

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) Location information (mapLocInfo) are initialized (size:" + mapLocInfo.size() + ")");

        if (!(mapInitFileName.equals("nofile") || mapInitFileName.equals(""))) {
            /* Initialization of map location information (mapLocInfo) */
            initMapLocInfo(mapInitFileName);
        }

    }


    /**
     * A method for initialization of map dimensions (mapDimVars)
     * This method depends on dimensionVars defined for a concrete map
     */
    protected abstract void initMapDimensions();

    /**
     * Initialization of map data variables (mapDataVars)
     * This method depends on dimensionVars defined for a concrete map
     */
    protected abstract void initMapInformation();


    /**
     * A method to initially assign data values into mapLocInfo hashmap.
     */
    protected void initMapLocInfo(String mapInitFileName){


        initMapLocKeys();

//        System.out.println(makeKeyStrings());

        mapBuilder = new MapBuilder(mapInitFileName, mapLocInfo);
        mapBuilder.updateMapData(mapDimVars, mapDataVars);

        /** Deprcated (Old Ver) */

//        /*
//        EX) xPosVar, yPosVar, floorNumVar:
//        key :=
//            (0, 0, FLOOR_1)
//            (0, 0, FLOOR_B1)
//            (0, 0, FLOOR_2)
//            ...
//         */
//
//        String key = "";
//        int index = 0;
//        for (DimVar dimVar : mapDimVars){
//            if (index < mapDimVars.size()-1){
//                key += dimVar.getDataDefaultValue() + ",";
//            }else{
//                key += dimVar.getDataDefaultValue();
//            }
//
//            if (dimVar.getVarType().equals("Int") || dimVar.getVarType().equals("Integer")){
//                DimVar tmpDimVar = (DimVar)dimVar.clone();
//                tmpDimVar.setDataCurValue((int)dimVar.getVarDomain().getDomainMinVal() + "");
//
////                while (Integer.valueOf(tmpDimVar.getDataCurValue()) <= (int)tmpDimVar.getVarDomain().getDomainMaxVal()){
////                    System.out.print(tmpDimVar.getDataCurValue() + " ");
////                    tmpDimVar.updateValueOfDim(1);
////                }
//
//                System.out.println(tmpDimVar.getDataCurValue());
//            }
//
//            System.out.print(dimVar.getVarName() + "[" + dimVar.getVarDomain().getDomainMinVal());
//
//            System.out.println("-" + dimVar.getVarDomain().getDomainMaxVal() + "]");
//
//            index++;
//        }
//
//        System.out.println(key);
//
//


    }

    /**
     * A method to initialize keys of mapLoc hashmap (mapLocInfo).
     * Initially, values of the hashmap are initialized as default values
     */
    private void initMapLocKeys(){
        initMapDimVarsAsMinVal();

        //Keys to be stored in hashmap (mapLocInfo) (deprecated)
//        ArrayList<String> keyList = makeKeyString(0);

        //New version
        ArrayList<String> keyList = makeKeyStrings();

        ArrayList<DataVar> defaultDataVars = new ArrayList<>();

        for (DataVar dataVar : mapDataVars){
            DataVar newDataVar = new DataVar(
                    dataVar.getVarId(),
                    dataVar.getVarName(),
                    dataVar.getVarType(),
                    dataVar.getDataDefaultValue(),
                    dataVar.getDataCurValue(),
                    dataVar.getVarDomain()
            );

            defaultDataVars.add(newDataVar);
        }

        //Instead of initializing as null, defaultDataVars are used.
        for (String key : keyList){
            mapLocInfo.put(key, defaultDataVars);
        }
    }



    /**
     * A method to initialize dimVars of a map as minimum values
     */
    private void initMapDimVarsAsMinVal(){
        //Set dataCurValues as minimum values allowed by their domains
        for (DimVar mapDimVar : mapDimVars){
            initMapDimVarAsMinVal(mapDimVar);
        }
    }

    /**
     * A method to initialize a dimvar as a minimum value
     * @param aDimVar A dimVar to be initialized
     */
    private void initMapDimVarAsMinVal(DimVar aDimVar){
        if (aDimVar.getVarType().equals("Int")) {
            aDimVar.setDataCurValue((int) aDimVar.getVarDomain().getDomainMinVal() + "");
        } else {
            aDimVar.setDataCurValue(aDimVar.getVarDomain().getDomainEnumVal().get(0) + "");
        }
    }


    /**
     * A method to make string-based keys of a map hashmap (mapLocInfo)
     * @return A list of keys generated
     */
    private ArrayList<String> makeKeyStrings(){

        //Keys to be returned
        ArrayList<String> keyListToReturn = new ArrayList<>();

        //Arraylist for multiplication (to count how many times a value is shown)
        ArrayList<Integer> multi = new ArrayList<>();

        //EX) xPos(0~2), yPos(0~4), floor(1~3)
        //expected output of multi: [1, 3, 15]
        multi.add(1);

        for (int i = 0; i < mapDimVars.size() - 1; i++){
            int prevMulti = multi.get(multi.size() - 1);
            DimVar aDimVar = mapDimVars.get(mapDimVars.size() - 1 - i);
            multi.add(prevMulti * aDimVar.countPossibleValues());

        }


        int numTotalKeysExceptFirstDimVar = multi.get(multi.size() - 1);
        int possibleValuesOfFirstDimVar = mapDimVars.get(0).countPossibleValues();

        int numTotalKeys = numTotalKeysExceptFirstDimVar * possibleValuesOfFirstDimVar;

        //For each key
        for (int i = 0; i < numTotalKeys; i++){
            String aKey = "";

            //For each dimVar
            for (int j = 0; j < mapDimVars.size(); j++){
                int multiForDimVar = multi.get(mapDimVars.size() - 1 - j);

                if (j != 0){
                    aKey += ",";
                }
                aKey += mapDimVars.get(j).getValueWithIndex((i / multiForDimVar) % mapDimVars.get(j).countPossibleValues());
            }
            keyListToReturn.add(aKey);
        }


        return keyListToReturn;
    }






    //TODO: check return
    protected abstract void buildMap(String mapInitInfo);

    protected void getAllLocationPoints() {
        for (DimVar dimVar : mapDimVars) {

        }
    }

    /* Getters & Setters */

    public ArrayList<DimVar> getMapDimVars() {
        return mapDimVars;
    }

    public void setMapDimVars(ArrayList<DimVar> mapDimVars) {
        this.mapDimVars = mapDimVars;
    }

    /**
     * A method to add a DimVar into mapDimVars
     * @param aDimVar DimVar to be added
     */
    protected void addDimVar(DimVar aDimVar){
        boolean isDuplicate = false;
        for (DimVar dimVar : mapDimVars){
            if (aDimVar.getVarId().equals(dimVar.getVarId())){
                isDuplicate = true;
            }
        }

        if (!isDuplicate){
            mapDimVars.add(aDimVar);
        }else{
            System.out.println("[" + this.getClass().getSimpleName() + "] addDimVar failed (duplicate id: " + aDimVar.getVarId() + ")");
        }
    }

    public ArrayList<DataVar> getMapDataVars() {
        return mapDataVars;
    }

    public void setMapDataVars(ArrayList<DataVar> mapDataVars) {
        this.mapDataVars = mapDataVars;
    }

    /**
     * A method to add a DataVar into mapDataVars
     * @param aDataVar DataVar to be added
     */
    protected void addDataVar(DataVar aDataVar){
        boolean isDuplicate = false;
        for (DataVar dataVar : mapDataVars){
            if (aDataVar.getVarId().equals(dataVar.getVarId())){
                isDuplicate = true;
            }
        }

        if (!isDuplicate){
            mapDataVars.add(aDataVar);
        }else{
            System.out.println("[" + this.getClass().getSimpleName() + "] addDataVar failed (duplicate id: " + aDataVar.getVarId() + ")");
        }
    }

    public HashMap<String, ArrayList<DataVar>> getMapLocInfo() {
        return mapLocInfo;
    }

    public void setMapLocInfo(HashMap<String, ArrayList<DataVar>> mapLocInfo) {
        this.mapLocInfo = mapLocInfo;
    }


    /**
     * Get a list of locData with a key
     * @param key   a key to be searched
     * @return      A list of locData objects
     */
    public ArrayList<DataVar> getLocDataWithKey(String key){
        return this.mapLocInfo.get(key);
    }



    /* Counters */

    /**
     * A method to count the number of all location points
     * @return number of location points in a map
     */
    protected int countAllLocPoints(){
        int count = 1;

        for (DimVar dimVar : mapDimVars) {
            int dimVarCount = 0;

            //For integer data
            if (dimVar.getVarType().equals("Int")) {
                dimVar.setDataCurValue((int)dimVar.getVarDomain().getDomainMinVal() + "");
            }
            //For enum data
            else{
                dimVar.setDataCurValue(dimVar.getVarDomain().getDomainEnumVal().get(0) + "");
            }

            boolean isInsideOfDomain = true;
            while (isInsideOfDomain) {
                //Increase value by 1
                isInsideOfDomain = dimVar.updateValueOfDim(1);
                //Count
                dimVarCount++;
            }

            count *= dimVarCount;
        }

        return count;
    }
}
