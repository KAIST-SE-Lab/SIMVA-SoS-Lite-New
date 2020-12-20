import cap.ExampleFuncAction;
import data.MapCoordinateDimensionType;
import data.MapFloorDimensionType;
import kr.ac.kaist.se.controller.sim.SimEngine;
import kr.ac.kaist.se.model.abst.comm.EnumMsgType;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.abst.evnt.EnumEventType;
import kr.ac.kaist.se.model.sos.Constituent;
import kr.ac.kaist.se.model.sos.Organization;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.CommAction;
import kr.ac.kaist.se.model.sos.cap.MoveAction;
import kr.ac.kaist.se.model.sos.comm.Message;
import kr.ac.kaist.se.model.sos.data.DataVar;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
import kr.ac.kaist.se.model.sos.data.DimensionVarDomain;
import kr.ac.kaist.se.model.sos.geo.ObjectLocation;
import kr.ac.kaist.se.simdata.evnt.SimLogEvent;

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

            String tmpDimVarId;
            String tmpDimVarName;
            String tmpDimVarType;
            String tmpDimVarDefaultValue;
            String tmpDimVarCurValue;
            DimensionVarDomain tmpDimVarDomain;


            int index = 0;

            // Deep copy by clone(): they have different references
            for(DimensionVar dimVar: mySoS.getSosMap().getMapDimensions()){
                DimensionVar clonedDimVar = (DimensionVar)dimVar.clone();

                mapDims.add(clonedDimVar);
//
//                System.out.println(clonedDimVar instanceof MapCoordinateDimensionType);

//                if (dimVar instanceof MapCoordinateDimensionType){
//                    MapCoordinateDimensionType tmpDimVar = new MapCoordinateDimensionType();
//
//                    tmpDimVarId = dimVar.getVarId();
//
//                    tmpDimVarName = dimVar.getVarName();
//                    tmpDimVarType = dimVar.getVarType();
//                    tmpDimVarDefaultValue = dimVar.getDataDefaultValue();
//                    tmpDimVarCurValue = dimVar.getDataCurValue();
//                    tmpDimVarDomain = dimVar.getVarDomain();
//                    System.out.println(tmpDimVarDomain + " | " + dimVar.getVarDomain());
//
//                    tmpDimVar.setVarId(tmpDimVarId);
//                    tmpDimVar.setVarName(tmpDimVarName);
//                    tmpDimVar.setVarType(tmpDimVarType);
//                    tmpDimVar.setDataDefaultValue(tmpDimVarDefaultValue);
//                    tmpDimVar.setDataCurValue(tmpDimVarCurValue);
//                    tmpDimVar.setVarDomain(tmpDimVarDomain);
//
//                    mapDims.add(tmpDimVar);
//
////                    System.out.println("ExampleCSType: " + tmpDimVar + " | " + dimVar);
//                } else if (dimVar instanceof MapFloorDimensionType){
//                    MapFloorDimensionType tmpDimVar = new MapFloorDimensionType();
//
//                    tmpDimVarId = dimVar.getVarId();
//                    tmpDimVarName = dimVar.getVarName();
//                    tmpDimVarType = dimVar.getVarType();
//                    tmpDimVarDefaultValue = dimVar.getDataDefaultValue();
//                    tmpDimVarCurValue = dimVar.getDataCurValue();
//                    tmpDimVarDomain = dimVar.getVarDomain();
//
//                    tmpDimVar.setVarId(tmpDimVarId);
//                    tmpDimVar.setVarName(tmpDimVarName);
//                    tmpDimVar.setVarType(tmpDimVarType);
//                    tmpDimVar.setDataDefaultValue(tmpDimVarDefaultValue);
//                    tmpDimVar.setDataCurValue(tmpDimVarCurValue);
//                    tmpDimVar.setVarDomain(tmpDimVarDomain);
//
//                    mapDims.add(tmpDimVar);
////                    System.out.println("ExampleCSType: " + tmpDimVar + " | " + dimVar);
//                }


//                System.out.println("ExampleCSType: " + clonedDimVar + " | " + mapDims.get(index));
                index++;
            }

            thisObjLocation = new ObjectLocation(mapDims);
            //System.out.println(thisObjLocation.getObjLocDimVars());

            //TODO: make it automated
            thisObjLocation.getObjLocDimVars().get(0).setDataCurValue("0");
            thisObjLocation.getObjLocDimVars().get(1).setDataCurValue("0");
            thisObjLocation.getObjLocDimVars().get(2).setDataCurValue("FLOOR_1");

            objLocation = thisObjLocation;

//            printCurLocation();
        }
    }

    @Override
    protected void initActions() {

        initMoveActions();
        initFuncActions();
        initCommActions();

    }



    private void initMoveActions() {
        /* Define allowed dimensions from declared dimensions of its map */
        ArrayList<DimensionVar> allowedDims = new ArrayList<>();
        ArrayList<DimensionVar> allowedDims2 = new ArrayList<>();
        ArrayList<DimensionVar> allowedDimsForFloorMoving = new ArrayList<>();

        MapCoordinateDimensionType xDimVar;
        MapCoordinateDimensionType yDimVar;
        MapFloorDimensionType floorDimVar;

        // Deep copy by clone(): they have different references
        xDimVar = (MapCoordinateDimensionType) ToySoSMap.xDim.clone();
        yDimVar = (MapCoordinateDimensionType) ToySoSMap.yDim.clone();
        floorDimVar = (MapFloorDimensionType) ToySoSMap.floorDim.clone();

        // Set domains for each dimension variable
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

        allowedDimsForFloorMoving.add(floorDimVar);

//        System.out.println(xDim);
//        System.out.println(ToySoSMap.xDim);

        MoveAction exampleCSMoveAction1 = new MoveAction(mySoS,
                this,
                "MOVEACTION01",
                "ExampleCS-MoveAction01",
                2,
                allowedDims,
                new ArrayList<Integer>(Arrays.asList(Integer.valueOf(1), Integer.valueOf(1))));

//        MoveAction exampleCSMoveAction2 = new MoveAction(mySoS,
//                this,
//                "MOVEACTION02",
//                "ExampleCS-MoveAction02",
//                3,
//                allowedDims2,
//                new ArrayList<Integer>(Arrays.asList(Integer.valueOf(4), Integer.valueOf(2), Integer.valueOf(-1))));

        MoveAction goUpOneFloorMoveAction = new MoveAction(mySoS,
                this,
                "MOVE_TO_UPSTAIR",
                "ExampleCS-goUpOneFloorMoveAction",
                1,
                allowedDimsForFloorMoving,
                new ArrayList<Integer>(Arrays.asList(Integer.valueOf(1))));

        MoveAction goDownOnFloorMoveAction = new MoveAction(mySoS,
                this,
                "MOVE_TO_DOWNSTAIR",
                "ExampleCS-goDownOnFloorMoveAction",
                1,
                allowedDimsForFloorMoving,
                new ArrayList<Integer>(Arrays.asList(Integer.valueOf(-1))));

        capableActionList.add(exampleCSMoveAction1);
//        capableActionList.add(exampleCSMoveAction2);
        capableActionList.add(goUpOneFloorMoveAction);
        capableActionList.add(goDownOnFloorMoveAction);

//        System.out.println(this.id + " >> initMoveAction() >> " + capableActionList.size());
    }

    /**
     * Initialization of functional actions (FuncAction)
     */
    private void initFuncActions(){
        ExampleFuncAction funcAction01 = new ExampleFuncAction(mySoS,
                this,
                "FUNC_ACTION_01",
                "Example Func Action - 01",
                1, 3, 5);

        capableActionList.add(funcAction01);
    }

    /**
     * Initialization of communication actions (CommAction)
     */
    public void initCommActions() {
        Message exampleMsg = new Message("MSG01", "MSG01tag", EnumMsgType.GENERAL_MSG);

        exampleMsg.setSenderId(this.getId());
        //TODO: how to access to myOrg's id?
        exampleMsg.setReceiverId("SIMOBJECT_XX");

        ArrayList<DataVar> msgDataList = new ArrayList<>();
        msgDataList.add(new DataVar("MSG01_DATAVAR01",
                "Data varabiel 01 of MSG01",
                "Int",
                "30"));

        exampleMsg.setMsgDataList(msgDataList);

        CommAction commAction01 = new CommAction(mySoS,
                this,
                "COMM_ACTION01",
                "Communication Action 01",
                exampleMsg);

        capableActionList.add(commAction01);
    }


//    @Override
//    protected void selectActions() {
//
//    }

//    @Override
//    public void doAction(_SimAction_ actionObj) {
//
//    }

//    @Override
//    public void sendMsg(_SimMessage_ msgObj) {
//
//    }

//    @Override
//    public void readIncomingMsgs() {
//
//    }

//    @Override
//    public void doDecisionMaking() {
//        selectedActionList = capableActionList;
//    }

    @Override
    public void move() {

    }


}
