package data;

import kr.ac.kaist.se.model.abst.data._SimDomain_;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;

/**
 * Class to define coordinate-based dimensions (xPos, yPos, zPos, ...)
 *
 * @author ymbaek
 */
public class MapCoordinateDimensionType extends DimensionVar {


    public MapCoordinateDimensionType(String dataId, String dataName, String dataType) {
        super(dataId, dataName, dataType);
    }

    public MapCoordinateDimensionType(String dataId, String dataName, String dataType, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDomain);
    }

    public MapCoordinateDimensionType(String dataId, String dataName, String dataType, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataCurValue, dataDomain);
    }

    public MapCoordinateDimensionType(String dataId, String dataName, String dataType, String dataDefaultValue, String dataCurValue, DimensionVarDomain dataDomain) {
        super(dataId, dataName, dataType, dataDefaultValue, dataCurValue, dataDomain);
    }

    @Override
    public void updateValueOfDim(int diff) {
        int newValue = Integer.parseInt(this.dataCurValue);
        newValue += diff;

        //TODO: Value checking
        //System.out.println(varDomain.isValidValue(newValue));

        this.dataCurValue = newValue + "";
    }

//    @Override
//    public void increaseValueOfDim(int diff) {
//        int newValue = Integer.parseInt(this.dataCurValue);
//        newValue += diff;
//
//        this.dataCurValue = newValue + "";
//    }
//
//    @Override
//    public void decreaseValueOfDim(int diff) {
//        int newValue = Integer.parseInt(this.dataCurValue);
//        newValue -= diff;
//
//        this.dataCurValue = newValue + "";
//    }
}
