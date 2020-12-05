package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data._SimDataVariable_;
import kr.ac.kaist.se.model.abst.data._SimDomain_;

/**
 * Concrete class to define information of a geolocation
 *
 * @author ymbaek
 */
public class LocInformationVar extends _SimDataVariable_ {
    public LocInformationVar(String dataId, String dataName, String dataType) {
        super(dataId, dataName, dataType);
    }

    public LocInformationVar(String dataId, String dataName, String dataType, _SimDomain_ dataDomain) {
        super(dataId, dataName, dataType, dataDomain);
    }

    public LocInformationVar(String dataId, String dataName, String dataType, String dataCurValue, _SimDomain_ dataDomain) {
        super(dataId, dataName, dataType, dataCurValue, dataDomain);
    }

    public LocInformationVar(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, _SimDomain_ dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue, dataDomain);
    }
}
