package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data.EnumDomainType;
import kr.ac.kaist.se.model.abst.data._SimDataDomain_;

import java.util.ArrayList;

public class DataVarDomain extends _SimDataDomain_ {

    protected boolean isLogical;
    protected boolean isDiscrete;

//    protected String defaultValue;


//    public DataVarDomain(EnumDomainType domainType, boolean isLogical, boolean isDiscrete, String defaultValue) {
//        super(domainType);
//        this.isLogical = isLogical;
//        this.isDiscrete = isDiscrete;
//        this.defaultValue = defaultValue;
//    }

    //For value-range domain
    public DataVarDomain(EnumDomainType domainType, float domainMinVal, float domainMaxVal, boolean isLogical, boolean isDiscrete, String defaultValue) {
        super(domainType, domainMinVal, domainMaxVal);
        this.isLogical = isLogical;
        this.isDiscrete = isDiscrete;
//        this.defaultValue = defaultValue;
    }

    //For enumeration domain
    public DataVarDomain(EnumDomainType domainType, ArrayList<String> domainEnumVal, boolean isLogical, boolean isDiscrete, String defaultValue) {
        super(domainType, domainEnumVal);
        this.isLogical = isLogical;
        this.isDiscrete = isDiscrete;
//        this.defaultValue = defaultValue;
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

//    public String getDefaultValue() {
//        return defaultValue;
//    }
//
//    public void setDefaultValue(String defaultValue) {
//        this.defaultValue = defaultValue;
//    }
}
