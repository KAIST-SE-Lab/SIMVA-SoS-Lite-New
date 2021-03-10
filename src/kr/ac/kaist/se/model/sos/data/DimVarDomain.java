package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data.EnumDomainType;
import kr.ac.kaist.se.model.abst.data._SimDataDomain_;

import java.util.ArrayList;

public class DimVarDomain extends _SimDataDomain_ {


    public DimVarDomain(EnumDomainType domainType) {
        super(domainType);
    }

    public DimVarDomain(EnumDomainType domainType, float domainMinVal, float domainMaxVal) {
        super(domainType, domainMinVal, domainMaxVal);
    }

    public DimVarDomain(EnumDomainType domainType, ArrayList<String> domainEnumVal) {
        super(domainType, domainEnumVal);
    }
}
