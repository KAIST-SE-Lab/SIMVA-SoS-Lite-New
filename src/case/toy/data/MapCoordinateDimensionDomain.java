package data;

import kr.ac.kaist.se.model.abst.data.EnumDomainType;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;

public class MapCoordinateDimensionDomain extends DimensionVarDomain {

    public MapCoordinateDimensionDomain(EnumDomainType domainType, float domainMinVal, float domainMaxVal) {
        super(domainType, domainMinVal, domainMaxVal);
    }
}
