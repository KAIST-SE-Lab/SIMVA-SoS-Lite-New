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

    protected EnumDataDomainType domainType;

    protected boolean isLogical;
    protected boolean isDiscrete;

    protected String defaultValue;

    protected double domainMinVal;
    protected double domainMaxVal;

    protected ArrayList<String> domainEnumVal;



    public EnumDataDomainType getDomainType() {
        return domainType;
    }

    public void setDomainType(EnumDataDomainType domainType) {
        this.domainType = domainType;
    }

    public boolean isLogical() {
        return isLogical;
    }

    public void setLogical(boolean logical) {
        isLogical = logical;
    }

    public boolean isDiscrete() {
        return isDiscrete;
    }

    public void setDiscrete(boolean discrete) {
        isDiscrete = discrete;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public double getDomainMinVal() {
        return domainMinVal;
    }

    public void setDomainMinVal(double domainMinVal) {
        this.domainMinVal = domainMinVal;
    }

    public double getDomainMaxVal() {
        return domainMaxVal;
    }

    public void setDomainMaxVal(double domainMaxVal) {
        this.domainMaxVal = domainMaxVal;
    }

    public ArrayList<String> getDomainEnumVal() {
        return domainEnumVal;
    }

    public void setDomainEnumVal(ArrayList<String> domainEnumVal) {
        this.domainEnumVal = domainEnumVal;
    }
}
