package kr.ac.kaist.se.model.sos.geo;

import kr.ac.kaist.se.controller.util.MapBuilder;
import kr.ac.kaist.se.model.abst.geo._SimMap_;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimensionVar;

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


    //TODO: Object location hashmap
    //Hashmap to store objects' locations
    //protected HashMap<ArrayList<_SimDataVariable_>, ArrayList<_SimObject_>> objLocationHashMap;

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

    protected void initMap(){
        initMapDimensions();
        initMapInformation();

        mapBuilder.updateMapData(mapDimVars, mapDataVars, mapLocInfo, new ArrayList<String>());
    }

    //TODO: check return
    protected abstract void buildMap(String mapInitInfo);

    protected abstract void initMapDimensions();
    protected abstract void initMapInformation();

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
