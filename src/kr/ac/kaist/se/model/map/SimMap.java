package kr.ac.kaist.se.model.map;

import kr.ac.kaist.se.model.abst.geo._SimMap_;
import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.model.sos.data.LocDimensionVar;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract class to represent a simulation map
 * @author ymbaek
 */
public abstract class SimMap extends _SimMap_ {

    //Dimensions of this map
    protected ArrayList<LocDimensionVar> mapDimensions;

    //Hashmap to store objects' locations
    protected HashMap<ObjectLocation, ArrayList<_SimObject_>> objLocationHashMap;

    public SimMap(String mapId, String mapName) {
        this.mapId = mapId;
        this.mapName = mapName;
        objLocationHashMap = new HashMap<>();
    }

    public SimMap(String mapId, String mapName, ArrayList<LocDimensionVar> mapDimensions) {
        this.mapId = mapId;
        this.mapName = mapName;
        this.mapDimensions = mapDimensions;
        objLocationHashMap = new HashMap<>();
    }

    //TODO: check return
    protected abstract void initializeMap();

}
