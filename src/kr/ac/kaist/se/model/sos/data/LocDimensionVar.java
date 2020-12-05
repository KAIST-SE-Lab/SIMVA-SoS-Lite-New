package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data._SimDataVariable_;
import kr.ac.kaist.se.model.abst.data._SimDomain_;

/**
 * Concrete class to define a dimension of a geolocation
 *
 * To enable a MoveAction,
 * increaseValueOfDim(..) and decreaseValueOfDim(..) should be implemented
 * to specify how an object can move according to the definition
 *
 * @author ymbaek
 */
public abstract class LocDimensionVar extends _SimDataVariable_ {

    public abstract void increaseValueOfDim(int diff);
    public abstract void decreaseValueOfDim(int diff);

    public LocDimensionVar(String dataId, String dataName, String dataType) {
        super(dataId, dataName, dataType);
    }

    public LocDimensionVar(String dataId, String dataName, String dataType, _SimDomain_ dataDomain) {
        super(dataId, dataName, dataType, dataDomain);
    }

    public LocDimensionVar(String dataId, String dataName, String dataType, String dataCurValue, _SimDomain_ dataDomain) {
        super(dataId, dataName, dataType, dataCurValue, dataDomain);
    }

    public LocDimensionVar(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, _SimDomain_ dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue, dataDomain);
    }
}
