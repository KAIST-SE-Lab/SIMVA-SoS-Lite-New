package data;

import kr.ac.kaist.se.model.abst.data._SimDomain_;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;

import java.sql.Timestamp;

/**
 * Class to define coordinate-based dimensions (xPos, yPos, zPos, ...)
 *
 * @author ymbaek
 */
public class MapCoordinateDimensionType extends DimensionVar {

    public MapCoordinateDimensionType() {

    }

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
    public boolean checkUpdateValid(int diff) {


        int newValue = Integer.parseInt(this.getDataCurValue());
        newValue += diff;

        //Since MapCoordinateDimensionType has integer dataType, it returns false if it is not met.
        if (integerData != null){
            //If newValue is out of range of varDomain
            if(newValue < varDomain.getDomainMinVal() || newValue > varDomain.getDomainMaxVal()){
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (MapCoordinateDimensionType:checkUpdateValid) Out of domain: not moved.");

                return false;
            }
        }else{
            timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] (MapCoordinateDimensionType:checkUpdateValid) Current value is not specified yet (stringData == null).");

            return false;
        }

        return true;
    }

    @Override
    public boolean updateValueOfDim(int diff) {
        int newValue = Integer.parseInt(this.getDataCurValue());
        newValue += diff;

        //TODO: Value checking
        //System.out.println(varDomain.isValidValue(newValue));

        //Since MapCoordinateDimensionType has integer dataType, it returns false if it is not met.
        if (integerData != null){

            //If newValue is out of range of varDomain
            if(newValue < varDomain.getDomainMinVal() || newValue > varDomain.getDomainMaxVal()){
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (MapCoordinateDimensionType) Out of domain: not moved.");

                return false;
            }else{
                this.setDataCurValue(newValue + "");
            }


        }else{
            return false;
        }
        return true;
    }

}
