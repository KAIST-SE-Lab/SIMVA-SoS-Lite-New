package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data._SimDataVariable_;

import java.sql.Timestamp;

/**
 * Abstract class to define a dimension of a geolocation
 * <p>
 * To enable a MoveAction,
 * increaseValueOfDim(..) and decreaseValueOfDim(..) should be implemented
 * to specify how an object can move according to the definition
 *
 * @author ymbaek
 */
public abstract class DimensionVar extends _SimDataVariable_ {

    //Domain of a dimension variable
    protected DimensionVarDomain varDomain;

    public DimensionVar() {
        super();
    }

    public DimensionVar(String dataId, String dataName, String dataType) {
        super(dataId, dataName, dataType);
        printDimensionVarCreation();
    }

//    public abstract void increaseValueOfDim(int diff);
//    public abstract void decreaseValueOfDim(int diff);

    public DimensionVar(String dataId, String dataName, String dataType, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType);

        this.varDomain = dataDomain;
        this.isDomainConstrained = true;

        printDimensionVarCreation();
    }

    public DimensionVar(String dataId, String dataName, String dataType, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataCurValue);

        this.varDomain = dataDomain;
        this.isDomainConstrained = true;

        printDimensionVarCreation();
    }

    public DimensionVar(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue);

        this.varDomain = dataDomain;
        this.isDomainConstrained = true;

        printDimensionVarCreation();
    }

    public abstract boolean checkUpdateValid(int diff);

    public abstract boolean updateValueOfDim(int diff);

    private void printDimensionVarCreation() {
        timestamp = new Timestamp(System.currentTimeMillis());

        if (varDomain != null) {
            System.out.println("[" + timestamp + "] (DimensionVar) A DimensionVar is initialized: " +
                    varId + " | " +
                    varName + " | " +
                    varType + " | varDomain(" +
                    varDomain.getDomainType() + ", " +
                    varDomain.getDomainMinVal() + ", " +
                    varDomain.getDomainMaxVal() + ", " +
                    varDomain.getDomainEnumVal() + ")");
        } else {
            System.out.println("[" + timestamp + "] (DimensionVar) A DimensionVar is initialized: " +
                    varId + " | " +
                    varName + " | " +
                    varType + ")");
        }
    }

//    public


    public DimensionVarDomain getVarDomain() {
        return varDomain;
    }

    public void setVarDomain(DimensionVarDomain varDomain) {
        this.varDomain = varDomain;
    }
}
