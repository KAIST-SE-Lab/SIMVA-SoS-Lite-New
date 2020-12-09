package kr.ac.kaist.se.model.abst.data;

import kr.ac.kaist.se.model.sos.data.DimensionVar;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Abstract class to represent a data variable
 *
 * @author ymbaek
 */
public abstract class _SimDataVariable_ implements Cloneable{

    protected Timestamp timestamp;    //Timestamp for stdout

    protected String varId;        //id of a data (variable)
    protected String varName;      //name of a data (variable)
    protected String varType;      //type of a data (variable)

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


    public _SimDataVariable_(String varId, String varName, String varType) {
        this.varId = varId;
        this.varName = varName;
        this.varType = varType;

        setActualDataTypeVar(this.varType);
        printDataCreation();

    }


    public _SimDataVariable_(String varId, String varName, String varType, String dataDefaultValue) {
        this.varId = varId;
        this.varName = varName;
        this.varType = varType;
        this.dataDefaultValue = dataDefaultValue;

        this.dataCurValue = dataDefaultValue;
        isValueAssigned = true;

        setActualDataTypeVar(this.varType);
        printDataCreation();

    }

    public _SimDataVariable_(String varId, String varName, String varType, String dataDefaultValue, String dataCurValue) {
        this.varId = varId;
        this.varName = varName;
        this.varType = varType;
        this.dataDefaultValue = dataDefaultValue;
        this.dataCurValue = dataCurValue;

        isValueAssigned = true;

        setActualDataTypeVar(this.varType);
        printDataCreation();

    }

    protected void printDataCreation(){
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("[" + timestamp + "] (" + this.getClass().getSimpleName() + ") A data object (_SimDataVariable_) is created (" +
                varId + ", " + varName + ", " + varType + ", " + dataDefaultValue + ", " + dataCurValue + ").");

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
                //System.out.println("INT");
                integerData = Integer.valueOf(dataCurValue);
            }
            //Float data
            else if (dataType.equals("FLOAT") ||
                    dataType.equals("float") ||
                    dataType.equals("Float") ||
                    dataType.equals("DOUBLE") ||
                    dataType.equals("double") ||
                    dataType.equals("Double")) {
                //System.out.println("DOUBLE");
                floatData = Float.valueOf(dataCurValue);
            }
            //String data
            else if (dataType.equals("STRING") ||
                    dataType.equals("string") ||
                    dataType.equals("String") ||
                    dataType.equals("STR") ||
                    dataType.equals("str")) {
                //System.out.println("STR");
                stringData = dataCurValue;
            }
            //Enumeration / Enumeration String data
            else if (dataType.equals("ENUM") ||
                    dataType.equals("enum") ||
                    dataType.equals("Enum") ||
                    dataType.equals("ENUMSTRING") ||
                    dataType.equals("enumstring") ||
                    dataType.equals("EnumString")) {
                //System.out.println("ENUM");
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


    /**
     *
     * @return
     */
    public _SimDataVariable_ clone(){
        _SimDataVariable_ dimVar = null;

        try {
            dimVar = (_SimDataVariable_) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return dimVar;
    }



    /* Getters & Setters */

    public String getVarId() {
        return varId;
    }

    public void setVarId(String varId) {
        this.varId = varId;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }


    public String getDataCurValue() {
        return dataCurValue;
    }

    public void setDataCurValue(String dataCurValue) {
        //TODO: Domain check
        this.dataCurValue = dataCurValue;

        setActualDataTypeVar(this.varType);
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
