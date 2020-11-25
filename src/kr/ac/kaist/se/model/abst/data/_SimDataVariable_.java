package kr.ac.kaist.se.model.abst.data;

/**
 * Abstract class to represent a data variable
 *
 * @author ymbaek
 */
public abstract class _SimDataVariable_ {
    protected String dataId;        //id of a data (variable)
    protected String dataName;      //name of a data (variable)
    protected String dataType;      //type of a data (variable)
    protected String dataCurValue;  //current value of a data (variable)

    protected _SimDomain_ dataDomain;   //domain of a data variable (min-max/enum)

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataCurValue() {
        return dataCurValue;
    }

    public void setDataCurValue(String dataCurValue) {
        this.dataCurValue = dataCurValue;
    }

    public _SimDomain_ getDataDomain() {
        return dataDomain;
    }

    public void setDataDomain(_SimDomain_ dataDomain) {
        this.dataDomain = dataDomain;
    }
}
