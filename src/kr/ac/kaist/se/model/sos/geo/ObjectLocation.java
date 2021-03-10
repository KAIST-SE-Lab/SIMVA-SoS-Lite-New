package kr.ac.kaist.se.model.sos.geo;

import kr.ac.kaist.se.model.sos.data.DimVar;

import java.util.ArrayList;

/**
 * A concrete class to represent a specific location of an object
 *
 * @author ymbaek
 */
public class ObjectLocation implements Cloneable {

    ArrayList<DimVar> objLocDimVars;

    public ObjectLocation(ArrayList<DimVar> mapDims) {
        this.objLocDimVars = mapDims;
    }

    public ArrayList<DimVar> getObjLocDimVars() {
        return objLocDimVars;
    }

    public void setObjLocDimVars(ArrayList<DimVar> objLocDimVars) {
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
