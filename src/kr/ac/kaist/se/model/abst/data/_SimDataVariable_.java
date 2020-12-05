package kr.ac.kaist.se.model.abst.data;

import java.sql.Timestamp;
import java.util.ArrayList;

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
    private ArrayList<String> enumData;

    protected String dataDefaultValue;  //default value of a data (variable)
    protected String dataCurValue;      //current value of a data (variable)

    protected boolean isEnumData = false;
    protected boolean isValueAssigned = false;
    protected boolean isDomainConstrained = false;

//    protected _SimDomain_ dataDomain;   //domain of a data variable (min-max/enum)


    public _SimDataVariable_(String dataId, String dataName, String dataType) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.dataType = dataType;

        setActualDataTypeVar(this.dataType);
        printDataCreation();

    }


    public _SimDataVariable_(String dataId, String dataName, String dataType, String dataDefaultValue) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.dataType = dataType;
        this.dataDefaultValue = dataDefaultValue;

        this.dataCurValue = dataDefaultValue;
        isValueAssigned = true;

        setActualDataTypeVar(this.dataType);
        printDataCreation();

    }

    public _SimDataVariable_(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue) {
        this.dataId = dataId;
        this.dataName = dataName;
        this.dataType = dataType;
        this.dataDefaultValue = dataDefaultValue;
        this.dataCurValue = dataCurValue;

        isValueAssigned = true;

        setActualDataTypeVar(this.dataType);
        printDataCreation();

    }

    protected void printDataCreation(){
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ") A data object (_SimDataVariable_) is created (" +
                dataId + ", " + dataName + ", " + dataType + ", " + dataDefaultValue + ", " + dataCurValue + ").");

    }



    /**
     * A method to cast String data to data of actual type.
     * This method is called in constructors.
     *
     * @param dataType Data type of this _SimDataVariable_
     */
    private void setActualDataTypeVar(String dataType){
//        System.out.println(dataType);

        timestamp = new Timestamp(System.currentTimeMillis());


        if(dataCurValue != null) {
            //Integer data
            if (dataType.equals("INT") ||
                    dataType.equals("int") ||
                    dataType.equals("Int") ||
                    dataType.equals("INTEGER") ||
                    dataType.equals("integer")) {
                //Integer.parseInt(this.dataCurValue);
                System.out.println("INT");
                integerData = Integer.valueOf(dataCurValue);
            }
            //Float data
            else if (dataType.equals("FLOAT") ||
                    dataType.equals("float") ||
                    dataType.equals("Float") ||
                    dataType.equals("DOUBLE") ||
                    dataType.equals("double") ||
                    dataType.equals("Double")) {
                System.out.println("DOUBLE");
                floatData = Float.valueOf(dataCurValue);
            }
            //String data
            else if (dataType.equals("STRING") ||
                    dataType.equals("string") ||
                    dataType.equals("String") ||
                    dataType.equals("STR") ||
                    dataType.equals("str")) {
                System.out.println("STR");
                stringData = dataCurValue;
            }
            //Enumeration / Enumeration String data
            else if (dataType.equals("ENUM") ||
                    dataType.equals("enum") ||
                    dataType.equals("Enum") ||
                    dataType.equals("ENUMSTRING") ||
                    dataType.equals("enumstring") ||
                    dataType.equals("EnumString")) {
                System.out.println("ENUM");
                isEnumData = true;
            }
        }
        else{

        }
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ") Actual data: " + integerData + " | " + floatData + " | " + stringData + " | " + isEnumData);

    }


    /**
     * A method to check if the given value is valid according to its domain definition.
     * If the dataValue exceeds the domain or outlies from the domain, it returns false.
     *
     * @param dataValue a value to be checked
     * @return true if the given value is valid
     */
    protected boolean isValidValue(String dataValue){
        return true;
    }



    /* Getters & Setters */

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

        setActualDataTypeVar(this.dataType);
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

    public void setIntegerData(Integer integerData) {
        this.integerData = integerData;
    }

    public void setFloatData(Float floatData) {
        this.floatData = floatData;
    }

    public ArrayList<String> getEnumData() {
        return enumData;
    }

    public void setEnumData(ArrayList<String> enumData) {
        this.enumData = enumData;
    }

    public String getDataDefaultValue() {
        return dataDefaultValue;
    }

    public void setDataDefaultValue(String dataDefaultValue) {
        this.dataDefaultValue = dataDefaultValue;
    }
}
