package kr.ac.kaist.se.model.abst.data;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Abstract class to represent a domain of _SimDataVariable_
 *
 * @author ymbaek
 */
public abstract class _SimDomain_ {

    protected Timestamp timestamp;    //Timestamp for stdout

    protected EnumDomainType domainType;    //Type of domain

    protected float domainMinVal;  //Minimum value
    protected float domainMaxVal;  //Maximum value

    protected ArrayList<String> domainEnumVal = new ArrayList<>();  //Allowed enumeration values


    /**
     * A method to check if a given value is valid according to this domain.
     * This method is overloaded according to the data type of the value.
     *
     * @return true if a given value is valid
     */
    /* For integer variable */
    public boolean isValidValue(int value){
        if (domainType == EnumDomainType.VALUE_RANGE){
            //If the value is lower than domainMinVal or exceeds domainMaxVal
            if (!(value >= domainMinVal && value <= domainMaxVal)){
                return false;
            }
        }
        //Wrong domainType
        else{
            return false;
        }
        return true;
    }

    /* For float variable */
    public boolean isValidValue(float value){
        if (domainType == EnumDomainType.VALUE_RANGE){
            //If the value is lower than domainMinVal or exceeds domainMaxVal
            if (!(value >= domainMinVal && value <= domainMaxVal)){
                return false;
            }
        }
        //Wrong domainType
        else{
            return false;
        }
        return true;
    }

    /* For enumeration variable */
    public boolean isValidValue(String value){
        boolean isAllowed = false;
        if (domainType == EnumDomainType.ENUMERATION){
            //If the value is lower than domainMinVal or exceeds domainMaxVal
            for (String allowedString : domainEnumVal){
                if (value.equals(allowedString)){
                    isAllowed = true;
                }
            }
        }
        //Wrong domainType
        else{
            return false;
        }
        return isAllowed;
    }


    public EnumDomainType getDomainType() {
        return domainType;
    }

    public void setDomainType(EnumDomainType domainType) {
        this.domainType = domainType;
    }

    public double getDomainMinVal() {
        return domainMinVal;
    }

    public void setDomainMinVal(float domainMinVal) {
        this.domainMinVal = domainMinVal;
    }

    public double getDomainMaxVal() {
        return domainMaxVal;
    }

    public void setDomainMaxVal(float domainMaxVal) {
        this.domainMaxVal = domainMaxVal;
    }

    public ArrayList<String> getDomainEnumVal() {
        return domainEnumVal;
    }

    public void setDomainEnumVal(ArrayList<String> domainEnumVal) {
        this.domainEnumVal = domainEnumVal;
    }
}
