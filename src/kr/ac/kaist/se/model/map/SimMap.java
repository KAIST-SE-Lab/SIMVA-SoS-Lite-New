package kr.ac.kaist.se.model.map;

import kr.ac.kaist.se.model.abst.geo._SimMap_;
import kr.ac.kaist.se.model.abst.data._SimDataVariable_;
import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.model.sos.data.DimensionVar;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract class to represent a simulation map
 * @author ymbaek
 */
public abstract class SimMap extends _SimMap_ {

    //Dimensions of this map
    public ArrayList<DimensionVar> mapDimensions = new ArrayList<>();

    //TODO: Object location hashmap
    //Hashmap to store objects' locations
    //protected HashMap<ArrayList<_SimDataVariable_>, ArrayList<_SimObject_>> objLocationHashMap;

    public SimMap(String mapId, String mapName) {
        this.mapId = mapId;
        this.mapName = mapName;
        //objLocationHashMap = new HashMap<>();
    }

    public SimMap(String mapId, String mapName, ArrayList<DimensionVar> mapDimensions) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.mapDimensions = mapDimensions;
        //objLocationHashMap = new HashMap<>();
    }

    //TODO: check return
    protected abstract void initializeMap();


    /* Getters & Setters */

    public ArrayList<DimensionVar> getMapDimensions() {
        return mapDimensions;
    }

    public void setMapDimensions(ArrayList<DimensionVar> mapDimensions) {
        this.mapDimensions = mapDimensions;
    }

    public void addMapDimension(DimensionVar dimensionVar){
        if (dimensionVar != null) {
            this.mapDimensions.add(dimensionVar);
        }
    }
}
