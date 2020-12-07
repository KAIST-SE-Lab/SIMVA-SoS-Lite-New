package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data.EnumDomainType;
import kr.ac.kaist.se.model.abst.data._SimDomain_;

import java.util.ArrayList;

public abstract class DimensionVarDomain extends _SimDomain_ {


    public DimensionVarDomain(EnumDomainType domainType) {
        super(domainType);
    }

    public DimensionVarDomain(EnumDomainType domainType, float domainMinVal, float domainMaxVal) {
        super(domainType, domainMinVal, domainMaxVal);
    }

    public DimensionVarDomain(EnumDomainType domainType, ArrayList<String> domainEnumVal) {
        super(domainType, domainEnumVal);
    }
}
