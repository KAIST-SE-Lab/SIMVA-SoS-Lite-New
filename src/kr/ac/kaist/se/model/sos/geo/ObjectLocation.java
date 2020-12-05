package kr.ac.kaist.se.model.sos.geo;

import kr.ac.kaist.se.model.sos.data.DimensionVar;

import java.util.ArrayList;

public class ObjectLocation {

    ArrayList<DimensionVar> objLocDims;

    public ArrayList<DimensionVar> getObjLocDims() {
        return objLocDims;
    }

    public void setObjLocDims(ArrayList<DimensionVar> objLocDims) {
        this.objLocDims = objLocDims;
    }
}
