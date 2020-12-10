import data.MapCoordinateDimensionType;
import data.MapFloorDimensionType;
import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.data.EnumDomainType;
import kr.ac.kaist.se.model.sos.Constituent;
import kr.ac.kaist.se.model.sos.Organization;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.MoveAction;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;
import kr.ac.kaist.se.simdata.output.intermediate.RunResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

public class ExampleCSType extends Constituent {


    public ExampleCSType(SoS simModel, Organization myOrg, String csId, String csName) {
        super(simModel, myOrg, csId, csName);
    }

    public ExampleCSType(SoS simModel, Organization myOrg, String csId, String csName, boolean isStatic, boolean isActivated, boolean isAvailable) {
        super(simModel, myOrg, csId, csName, isStatic, isActivated, isAvailable);
    }


    @Override
    protected void initObjLocation() {

        ObjectLocation thisObjLocation;
        if (mySoS != null && mySoS.sosMap != null) {
            ArrayList<DimensionVar> mapDims = new ArrayList<>();

            // Deep copy by clone(): they have different references
            for(DimensionVar dimVar: mySoS.sosMap.mapDimensions){
                mapDims.add((DimensionVar)dimVar.clone());
            }

            thisObjLocation = new ObjectLocation(mapDims);
            //System.out.println(thisObjLocation.getObjLocDimVars());

            //TODO: make it automated
            thisObjLocation.getObjLocDimVars().get(0).setDataCurValue("0");
            thisObjLocation.getObjLocDimVars().get(1).setDataCurValue("0");
            thisObjLocation.getObjLocDimVars().get(2).setDataCurValue("FLOOR_1");

            objLocation = thisObjLocation;

            printCurLocation();
        }
    }

    @Override
    protected void initActions() {

        initMoveActions();

    }

    private void initMoveActions() {
        /* Define allowed dimensions from declared dimensions of its map */
        ArrayList<DimensionVar> allowedDims = new ArrayList<>();
        ArrayList<DimensionVar> allowedDims2 = new ArrayList<>();

        MapCoordinateDimensionType xDimVar;
        MapCoordinateDimensionType yDimVar;
        MapFloorDimensionType floorDimVar;

        // Deep copy by clone(): they have different references
        xDimVar = (MapCoordinateDimensionType) ToySoSMap.xDim.clone();
        yDimVar = (MapCoordinateDimensionType) ToySoSMap.yDim.clone();
        floorDimVar = (MapFloorDimensionType) ToySoSMap.floorDim.clone();

//        DimensionVarDomain xDimDomain = ToySoSMap.xDim.getVarDomain();
        xDimVar.setVarDomain(ToySoSMap.xDim.getVarDomain());
        yDimVar.setVarDomain(ToySoSMap.yDim.getVarDomain());
        floorDimVar.setVarDomain(ToySoSMap.floorDim.getVarDomain());

//        xDim.setDataCurValue("0");
//        yDim.setDataCurValue("0");
//        floorDim.setDataCurValue("FLOOR_1");

        allowedDims.add(xDimVar);
        allowedDims.add(yDimVar);

        allowedDims2.add(xDimVar);
        allowedDims2.add(yDimVar);
        allowedDims2.add(floorDimVar);

//        System.out.println(xDim);
//        System.out.println(ToySoSMap.xDim);

        MoveAction exampleCSMoveAction1 = new MoveAction(mySoS,
                this,
                "MOVEACTION01",
                "ExampleCS-MoveAction01",
                2,
                allowedDims,
                new ArrayList<Integer>(Arrays.asList(Integer.valueOf(1), Integer.valueOf(2))));

        MoveAction exampleCSMoveAction2 = new MoveAction(mySoS,
                this,
                "MOVEACTION02",
                "ExampleCS-MoveAction01",
                3,
                allowedDims2,
                new ArrayList<Integer>(Arrays.asList(Integer.valueOf(3), Integer.valueOf(2), Integer.valueOf(1))));

        capableActionList.add(exampleCSMoveAction1);
        capableActionList.add(exampleCSMoveAction2);

//        System.out.println(this.id + " >> initMoveAction() >> " + capableActionList.size());
    }


//    @Override
//    protected void selectActions() {
//
//    }

//    @Override
//    public void doAction(_SimAction_ actionObj) {
//
//    }

    @Override
    public void sendMsg(_SimMessage_ msgObj) {

    }

//    @Override
//    public void readIncomingMsgs() {
//
//    }

    @Override
    public void doDecisionMaking() {
        selectedActionList = capableActionList;
    }

    @Override
    public void move() {

    }


}
