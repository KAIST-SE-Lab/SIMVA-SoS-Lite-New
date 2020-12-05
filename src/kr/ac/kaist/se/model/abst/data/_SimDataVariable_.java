package kr.ac.kaist.se.model.abst.data;

import java.sql.Timestamp;

/**
 * Abstract class to represent a data variable
 *
 * @author ymbaek
 */
public abstract class _SimDataVariable_ {

    protected Timestamp timestamp;    //Timestamp for stdout

    protected String dataId;        //id of a data (variable)
    protected String dataName;      //name of a data (variable)
    protected String dataType;      //type of a data (variable)

    private Integer integerData;
    private Float floatData;
    private String stringData;

    protected String dataDefaultValue;  //default value of a data (variable)
    protected String dataCurValue;      //current value of a data (variable)

    protected _SimDomain_ dataDomain;   //domain of a data variable (min-max/enum)


    public _SimDataVariable_(String dataId, String dataName, String dataType) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.dataType = dataType;

        setActualDataTypeVar(this.dataType);
        printDataCreation();

    }

    public _SimDataVariable_(String dataId, String dataName, String dataType, _SimDomain_ dataDomain) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.dataType = dataType;
        this.dataDomain = dataDomain;

        setActualDataTypeVar(this.dataType);
        printDataCreation();

    }

//    public _SimDataVariable_(String dataId, String dataName, String dataType, String dataCurValue, _SimDomain_ dataDomain) {
//        this.dataId = dataId;
//        this.dataName = dataName;
//        this.dataType = dataType;
//        this.dataCurValue = dataCurValue;
//        this.dataDomain = dataDomain;
//
//        printDataCreation();
//        setActualDataTypeVar(this.dataType);
//    }


    public _SimDataVariable_(String dataId, String dataName, String dataType, String dataDefaultValue, _SimDomain_ dataDomain) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.dataType = dataType;
        this.dataDefaultValue = dataDefaultValue;
        this.dataDomain = dataDomain;

        setActualDataTypeVar(this.dataType);
        printDataCreation();

    }

    public _SimDataVariable_(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, _SimDomain_ dataDomain) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.dataType = dataType;
        this.dataDefaultValue = dataDefaultValue;
        this.dataCurValue = dataCurValue;
        this.dataDomain = dataDomain;

        setActualDataTypeVar(this.dataType);
        printDataCreation();

    }

    private void printDataCreation(){
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ") A data object (_SimDataVariable_) is created (" +
                dataId + ", " + dataName + ", " + dataType + ", " + dataDefaultValue + ", " + dataCurValue + ", " + dataDomain.getDomainType() + ").");

    }


    /**
     * A method to cast String data to data of actual type.
     * This method is called in constructors.
     *
     * @param dataType Data type of this _SimDataVariable_
     */
    private void setActualDataTypeVar(String dataType){

        if(dataCurValue != null) {

            //Integer data
            if (dataType.equals("INT") ||
                    dataType.equals("int") ||
                    dataType.equals("Int") ||
                    dataType.equals("INTEGER") ||
                    dataType.equals("integer")) {
                //Integer.parseInt(this.dataCurValue);
                integerData = Integer.valueOf(dataCurValue);
            }
            //Float data
            else if (dataType.equals("FLOAT") ||
                    dataType.equals("float") ||
                    dataType.equals("Float") ||
                    dataType.equals("DOUBLE") ||
                    dataType.equals("double") ||
                    dataType.equals("Double")) {
                floatData = Float.valueOf(dataCurValue);
            }
            //String data
            else if (dataType.equals("STRING") ||
                    dataType.equals("string") ||
                    dataType.equals("String") ||
                    dataType.equals("STR") ||
                    dataType.equals("str")) {
                stringData = dataCurValue;
            }
            //Enumeration / Enumeration String data
            else if (dataType.equals("ENUM") ||
                    dataType.equals("enum") ||
                    dataType.equals("Enum") ||
                    dataType.equals("ENUMSTRING") ||
                    dataType.equals("enumstring") ||
                    dataType.equals("EnumString")) {

            }
        }
        else{

        }
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ") Actual data: " + integerData + " | " + floatData + " | " + stringData);

    }

    //    public String

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
        //TODO: Domain check
        this.dataCurValue = dataCurValue;
    }

    public _SimDomain_ getDataDomain() {
        return dataDomain;
    }

    public void setDataDomain(_SimDomain_ dataDomain) {
        this.dataDomain = dataDomain;
    }

    public int getIntegerData() {
        return integerData;
    }

    public void setIntegerData(int integerData) {
        this.integerData = integerData;
    }

    public float getFloatData() {
        return floatData;
    }

    public void setFloatData(float floatData) {
        this.floatData = floatData;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }
}
