package kr.ac.kaist.se.model.sos.geo;

import kr.ac.kaist.se.model.sos.data.DimensionVar;

import java.util.ArrayList;

public class ObjectLocation {

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

    public void updateLocation(ArrayList<String> dimIds){

    }
}
