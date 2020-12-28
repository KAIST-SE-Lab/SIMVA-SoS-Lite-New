package kr.ac.kaist.se.model.sos.geo;

import kr.ac.kaist.se.model.sos.data.DimensionVar;

import java.util.ArrayList;

/**
 * A concrete class to represent a specific location of an object
 *
 * @author ymbaek
 */
public class ObjectLocation implements Cloneable {

    ArrayList<DimensionVar> objLocDimVars;

    public ObjectLocation(ArrayList<DimensionVar> mapDims) {
        this.objLocDimVars = mapDims;
    }

    public ArrayList<DimensionVar> getObjLocDimVars() {
        return objLocDimVars;
    }

    public void setObjLocDimVars(ArrayList<DimensionVar> objLocDimVars) {
        this.objLocDimVars = objLocDimVars;
    }

    public void updateLocation(ArrayList<String> dimIds) {

    }

    /**
     * An implemented method of Cloneable interface
     *
     * @return cloned object of this class
     */
    public ObjectLocation clone() {
        ObjectLocation clonedObjLoc = null;

        try {
            clonedObjLoc = (ObjectLocation) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clonedObjLoc;
    }
}
