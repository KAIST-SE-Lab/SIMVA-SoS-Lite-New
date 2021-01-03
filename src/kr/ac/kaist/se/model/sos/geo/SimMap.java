package kr.ac.kaist.se.model.sos.geo;

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
    protected ArrayList<DimensionVar> mapDimensions = new ArrayList<>();
    //LocInformations of this map
    public ArrayList<DataVar> mapInfos = new ArrayList<>();

    /** Initialization of map */
    //HashMap to store location information
    protected HashMap<String, ArrayList<DataVar>> mapLocInfos = new HashMap<>();


    //TODO: Object location hashmap
    //Hashmap to store objects' locations
    //protected HashMap<ArrayList<_SimDataVariable_>, ArrayList<_SimObject_>> objLocationHashMap;

    public SimMap(String mapId, String mapName) {
        this.mapId = mapId;
        this.mapName = mapName;
        //objLocationHashMap = new HashMap<>();

        initMapDimensions();
        initMapInformation();
    }

    public SimMap(String mapId, String mapName, ArrayList<DimensionVar> mapDimensions) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.mapDimensions = mapDimensions;
        //objLocationHashMap = new HashMap<>();
    }

    public SimMap(String mapId, String mapName, ArrayList<DimensionVar> mapDimensions, ArrayList<DataVar> mapInfos) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.mapDimensions = mapDimensions;
        this.mapInfos = mapInfos;
    }

    protected void initMap(){
        initMapDimensions();
        initMapInformation();

        buildMap("");
    }

    //TODO: check return
    protected abstract void buildMap(String mapInitInfo);

    protected abstract void initMapDimensions();
    protected abstract void initMapInformation();

    protected void getAllLocationPoints() {
        for (DimensionVar dimVar : mapDimensions) {

        }
    }

    /* Getters & Setters */

    public ArrayList<DimensionVar> getMapDimensions() {
        return mapDimensions;
    }

    public void setMapDimensions(ArrayList<DimensionVar> mapDimensions) {
        this.mapDimensions = mapDimensions;
    }

    public void addMapDimension(DimensionVar dimensionVar) {
        if (dimensionVar != null) {
            this.mapDimensions.add(dimensionVar);
        }
    }

    public ArrayList<DataVar> getMapInfos() {
        return mapInfos;
    }

    public void setMapInfos(ArrayList<DataVar> mapInfos) {
        this.mapInfos = mapInfos;
    }

    public void addMapInfo(DataVar infoVar) {
        if (infoVar != null) {
            this.mapInfos.add(infoVar);
        }
    }
}
