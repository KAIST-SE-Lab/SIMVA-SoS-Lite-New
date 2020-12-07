package data;

import kr.ac.kaist.se.model.abst.data._SimDomain_;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;

/**
 * Class to define floor dimensions
 *
 * @author ymbaek
 */
public class MapFloorDimensionType extends DimensionVar {

    public MapFloorDimensionType(String dataId, String dataName, String dataType) {
        super(dataId, dataName, dataType);
    }

    public MapFloorDimensionType(String dataId, String dataName, String dataType, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDomain);
    }

    public MapFloorDimensionType(String dataId, String dataName, String dataType, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataCurValue, dataDomain);
    }

    public MapFloorDimensionType(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue, dataDomain);
    }

    @Override
    public void increaseValueOfDim(int diff) {

    }

    @Override
    public void decreaseValueOfDim(int diff) {

    }
}
