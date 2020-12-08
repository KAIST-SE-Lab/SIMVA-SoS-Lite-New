import kr.ac.kaist.se.model.abst.cap._SimAction_;
import kr.ac.kaist.se.model.abst.comm._SimMessage_;
import kr.ac.kaist.se.model.sos.Constituent;
import kr.ac.kaist.se.model.sos.Organization;
import kr.ac.kaist.se.model.sos.SoS;
import kr.ac.kaist.se.model.sos.cap.MoveAction;
import kr.ac.kaist.se.model.sos.data.DimensionVar;
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
            thisObjLocation = new ObjectLocation(mySoS.sosMap.mapDimensions);
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
        //moveAction = new MoveAction(mySoS, this, "MOVEACTION01", "Move Action of ExampleCSType");

        //mySoS.sosMap.mapDimensions.

        initMoveActions();

    }

    private void initMoveActions() {
        MoveAction exampleCSMoveAction = new MoveAction(mySoS,
                this,
                "MOVEACTION01",
                "ExampleCS-MoveAction01",
                new ArrayList<>(Arrays.asList(ToySoSMap.xDim, ToySoSMap.yDim)));

        capableActionList.add(exampleCSMoveAction);
    }


    @Override
    protected void selectActions() {

    }

    @Override
    public void doAction(_SimAction_ actionObj) {

    }

    @Override
    public void sendMsg(_SimMessage_ msgObj) {

    }

    @Override
    public void readIncomingMsgs() {

    }

    @Override
    public void doDecisionMaking() {

    }

    @Override
    public void move() {

    }


}
