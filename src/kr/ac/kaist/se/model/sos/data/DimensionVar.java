package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data._SimDataVariable_;
import kr.ac.kaist.se.model.abst.data._SimDomain_;

/**
 * Abstract class to define a dimension of a geolocation
 *
 * To enable a MoveAction,
 * increaseValueOfDim(..) and decreaseValueOfDim(..) should be implemented
 * to specify how an object can move according to the definition
 *
 * @author ymbaek
 */
public abstract class DimensionVar extends _SimDataVariable_ {

    //Domain of a dimension variable
    protected DimensionVarDomain varDomain;

    public abstract void increaseValueOfDim(int diff);
    public abstract void decreaseValueOfDim(int diff);


    public DimensionVar(String dataId, String dataName, String dataType) {
        super(dataId, dataName, dataType);
    }

    public DimensionVar(String dataId, String dataName, String dataType, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType);

        this.varDomain = dataDomain;
        this.isDomainConstrained = true;
    }

    public DimensionVar(String dataId, String dataName, String dataType, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataCurValue);

        this.varDomain = dataDomain;
        this.isDomainConstrained = true;
    }

    public DimensionVar(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue);

        this.varDomain = dataDomain;
        this.isDomainConstrained = true;
    }
}
