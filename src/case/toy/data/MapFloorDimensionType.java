package data;

import kr.ac.kaist.se.model.abst.data._SimDomain_;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;

import java.sql.Timestamp;

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
    public void updateValueOfDim(int diff) {
        //TODO: Loop of Enumeration or Min/Max constraints

        //Since MapFloorDimensionType has enumeration dataType,
        //it should have its proper update mechanism.
        if (stringData != null){
//            System.out.println("MapFloorDimensionType: stringData (enumData) is not null: " + stringData);

            int enumIndex = -1;
            int index = 0;

            for (String enumItem: varDomain.getDomainEnumVal()){
//                System.out.print(enumItem + ",");
                if (stringData.equals(enumItem)){
                    enumIndex = index;
                }
                index++;
            }

            //If current value is not found in the enumValue of its domain
            if (enumIndex == -1){
                timestamp = new Timestamp(System.currentTimeMillis());
                System.out.println("[" + timestamp + "] (MapFloorDimensionType) Corresponding enum value is not found.");
            }
            //If enumIndex is properly found
            else{
//                System.out.println("MapFloorDimensionType: enumIndex: " + enumIndex + " (size: " + varDomain.getDomainEnumVal().size() + ")");

                int newEnumIndex;
                //If it is out of domain
                if (enumIndex + diff >= varDomain.getDomainEnumVal().size() || enumIndex + diff < 0){
                    timestamp = new Timestamp(System.currentTimeMillis());
                    System.out.println("[" + timestamp + "] (MapFloorDimensionType) Out of domain: not moved.");
                }else{
                    newEnumIndex = enumIndex + diff;
                    //System.out.println(newEnumIndex);

                    //Update the value
                    this.setDataCurValue(varDomain.getDomainEnumVal().get(newEnumIndex));
                }


            }
        }

    }

}
