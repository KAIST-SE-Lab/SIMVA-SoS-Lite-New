package kr.ac.kaist.se.model.map;

import kr.ac.kaist.se.model.abst.obj._SimObject_;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An abstract class to represent a simulation map
 * @author ymbaek
 */
public abstract class SimMap {

    protected String name;  //name of map

    //Hashmap to store objects' locations
    protected HashMap<ObjectLocation, ArrayList<_SimObject_>> objLocationHashMap;

    public SimMap(String name) {
        this.name = name;
        objLocationHashMap = new HashMap<>();
    }
}
