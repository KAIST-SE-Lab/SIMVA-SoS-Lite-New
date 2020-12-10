package kr.ac.kaist.se.model.sos.data;

import kr.ac.kaist.se.model.abst.data._SimDataVariable_;
import kr.ac.kaist.se.model.abst.data._SimDomain_;

/**
 * Concrete class to define information of a geolocation
 *
 * @author ymbaek
 */
public class DataVar extends _SimDataVariable_ {

    //Domain of a dimension variable
    protected DataVarDomain varDomain;

    public DataVar(String dataId, String dataName, String dataType) {
        super(dataId, dataName, dataType);
    }

    public DataVar(String dataId, String dataName, String dataType, DataVarDomain dataDomain) {
        super(dataId, dataName, dataType);

        varDomain = dataDomain;
        this.isDomainConstrained = true;
    }

    public DataVar(String dataId, String dataName, String dataType, String dataCurValue, DataVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataCurValue);

        varDomain = dataDomain;
        this.isDomainConstrained = true;

    }

    public DataVar(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, DataVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue);

        varDomain = dataDomain;
        this.isDomainConstrained = true;

    }

    public DataVarDomain getVarDomain() {
        return varDomain;
    }

    public void setVarDomain(DataVarDomain varDomain) {
        this.varDomain = varDomain;
    }
}
