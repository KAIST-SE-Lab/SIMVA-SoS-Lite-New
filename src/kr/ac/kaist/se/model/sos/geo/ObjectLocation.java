package kr.ac.kaist.se.model.sos.geo;

import kr.ac.kaist.se.model.sos.data.LocDimensionVar;

import java.util.ArrayList;

public class ObjectLocation {

    ArrayList<LocDimensionVar> objLocDims;

    public ArrayList<LocDimensionVar> getObjLocDims() {
        return objLocDims;
    }

    public void setObjLocDims(ArrayList<LocDimensionVar> objLocDims) {
        this.objLocDims = objLocDims;
    }
}
