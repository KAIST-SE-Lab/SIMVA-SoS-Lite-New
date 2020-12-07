package data;

import kr.ac.kaist.se.model.abst.data.EnumDomainType;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;

import java.util.ArrayList;

public class MapFloorDimensionDomain extends DimensionVarDomain {
    public MapFloorDimensionDomain(EnumDomainType domainType, ArrayList<String> domainEnumVal) {
        super(domainType, domainEnumVal);
    }
}
