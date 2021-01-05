package kr.ac.kaist.se.model.sos.geo;

import kr.ac.kaist.se.controller.util.MapBuilder;
import kr.ac.kaist.se.model.abst.geo._SimMap_;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimensionVar;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class to represent a geographical map of an SoS
 *
 * @author ymbaek
 */
public abstract class SimMap extends _SimMap_ {

    /** ArrayLists for MapDimensions and MapInformation */
    //LocDimensions of this map
    protected ArrayList<DimensionVar> mapDimVars = new ArrayList<>();
    //LocInformations of this map
    public ArrayList<DataVar> mapDataVars = new ArrayList<>();

    /** Initialization of map */
    //HashMap to store location information
    protected HashMap<String, ArrayList<DataVar>> mapLocInfo = new HashMap<>();
    //MapBuilder to initialize and update a map
    protected MapBuilder mapBuilder = new MapBuilder();



    public SimMap(String mapId, String mapName) {
        this.mapId = mapId;
        this.mapName = mapName;
        //objLocationHashMap = new HashMap<>();

        initMap();
    }

    public SimMap(String mapId, String mapName, ArrayList<DimensionVar> mapDimensions) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.mapDimVars = mapDimensions;
        //objLocationHashMap = new HashMap<>();

        initMap();
    }

    public SimMap(String mapId, String mapName, ArrayList<DimensionVar> mapDimensions, ArrayList<DataVar> mapInfos) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.mapDimVars = mapDimensions;
        this.mapDataVars = mapInfos;

        initMap();
    }

    /**
     * Initialization of a map by calling other methods
     */
    protected void initMap(){
        /* Initialization of map dimensions (mapDimVars) */
        initMapDimensions();

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) Dimensions (mapDimVars) are initialized (size:" + mapDimVars.size() + ")");


        /* Initialization of map data variables (mapDataVars) */
        initMapInformation();

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) MapInformation (mapDataVars) is initialized (size:" + mapDataVars.size() + ")");


        /* Initialization of map location information (mapLocInfo) */
        initMapLocInfo();

        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (ToySoSMap: initDimensions) Location information (mapLocInfo) are initialized (size:" + mapLocInfo.size() + ")");

        //TODO: MapBuilder
//        mapBuilder.updateMapData(mapDimVars, mapDataVars, mapLocInfo, new ArrayList<String>());
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
    protected void initMapLocInfo(){

        /*
        EX) xPosVar, yPosVar, floorNumVar:
        key :=
            (0, 0, FLOOR_1)
            (0, 0, FLOOR_B1)
            (0, 0, FLOOR_2)
            ...
         */

        String key = "(";

        int index = 0;
        for (DimensionVar dimVar : mapDimVars){
            if (index < mapDimVars.size()-1){
                key += dimVar.getDataDefaultValue() + ",";
            }else{
                key += dimVar.getDataDefaultValue();
            }

            if (dimVar.getVarType().equals("Int") || dimVar.getVarType().equals("Integer")){
                DimensionVar tmpDimVar = (DimensionVar)dimVar.clone();
                tmpDimVar.setDataCurValue((int)dimVar.getVarDomain().getDomainMinVal() + "");
                System.out.println(tmpDimVar.getDataCurValue());
            }

            System.out.print(dimVar.getVarName() + "[" + dimVar.getVarDomain().getDomainMinVal());
            System.out.println("-" + dimVar.getVarDomain().getDomainMaxVal() + "]");

            index++;
        }
        key += ")";

        System.out.println(key);





    }

    //TODO: check return
    protected abstract void buildMap(String mapInitInfo);

    protected void getAllLocationPoints() {
        for (DimensionVar dimVar : mapDimVars) {

        }
    }

    /* Getters & Setters */

    public ArrayList<DimensionVar> getMapDimVars() {
        return mapDimVars;
    }

    public void setMapDimVars(ArrayList<DimensionVar> mapDimVars) {
        this.mapDimVars = mapDimVars;
    }

    public void addMapDimension(DimensionVar dimensionVar) {
        if (dimensionVar != null) {
            this.mapDimVars.add(dimensionVar);
        }
    }

    public ArrayList<DataVar> getMapDataVars() {
        return mapDataVars;
    }

    public void setMapDataVars(ArrayList<DataVar> mapDataVars) {
        this.mapDataVars = mapDataVars;
    }

    public void addMapInfo(DataVar infoVar) {
        if (infoVar != null) {
            this.mapDataVars.add(infoVar);
        }
    }

    public HashMap<String, ArrayList<DataVar>> getMapLocInfo() {
        return mapLocInfo;
    }

    public void setMapLocInfo(HashMap<String, ArrayList<DataVar>> mapLocInfo) {
        this.mapLocInfo = mapLocInfo;
    }
}
